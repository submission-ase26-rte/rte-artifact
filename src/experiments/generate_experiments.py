import sys
import re
import argparse
from pathlib import Path

script_dir = Path(__file__).parent
parent_dir = script_dir.parent
if str(parent_dir) not in sys.path:
    sys.path.insert(0, str(parent_dir))



YAML_TEMPLATE = '''experiment:
  name: "{experiment_name}"
  description: "{description}"

mode: "dependencies"

class_to_test: "{class_to_test}"
max_dependencies: 10

dependencies:
  selection: "structural"     
  top_k: 5             
  use_dependencies_second_phase: true

smart_retry:
  enable: true                       
  max_retries_repair: 2                    
  max_retries_refinement: -1
  min_weighted_pass_rate_threshold: 0.7  # 70% minimum weighted pass rate to regenerate
  min_coverage_threshold: 0.3            # 30% minimum coverage to regenerate

generation_phases:
  run_first_phase: true
  run_second_phase: true
  test_file_for_second_phase: ""
  previous_generated_method: ""
  include_previous_method: false

metrics:
  similarity_threshold: 0.7
  crystalbleu_stopwords: true
  use_dynamic_threshold: true  # If true, computes threshold based on method SLOC
  similarity_weights:          # Optional: custom weights for similarity metrics (must sum to 1.0)
    embedding_similarity: 0.35
    ast_similarity: 0.22
    crystalbleu_similarity: 0.20
    token_similarity: 0.15
    string_similarity: 0.08

test_generation:
  mode: "single_method"
  method_to_test: "{method_to_test}"
  method_signature: "{method_signature}"

provider:
  name: "{provider_name}"
  model: "{provider_model}"

prompt:
  test_generation_prompt: |
    None
  method_regeneration_prompt: "None"
  use_special_instructions: true  # Enables conditional instructions for Servlet/Spring/EJB

paths:
  base_dir: "./"
  java_projects_dir: "{java_projects_dir}"
  output_dir: "experiments/results"
  logs_dir: "experiments/results/logs"
'''



def parse_methods_file(file_path: str) -> list:
    """
    Parses the TESTED METHODS.txt file.
    Returns a list of dicts with info for each method.
    Format: ID | Method Signature | File Path | Project
    """
    methods = []
    
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Pattern: ID | Method | File Path | Project (4 columns)
    pattern = r'^(\d+)\s*\|\s*([^\|]+)\s*\|\s*([^\|]+)\s*\|\s*(.+)$'
    
    for line in content.split('\n'):
        line = line.strip()
        if not line or line.startswith('#'):
            continue
        
        match = re.match(pattern, line)
        if match:
            method_id = int(match.group(1))
            method_full = match.group(2).strip()
            file_path_val = match.group(3).strip()
            project = match.group(4).strip()
            
            signature = method_full

            # Extract method name (without parameters)
            method_name = re.sub(r'\([^)]*\)', '', method_full).strip()
            
            # If there's a dot, extract the class (old format)
            if '.' in method_name:
                parts = method_name.split('.')
                class_name = parts[0]
                method_name = parts[1]
            else:
                # Extract class name from file path
                class_name = file_path_val.split('/')[-1].replace('.java', '')
            
            methods.append({
                'id': method_id,
                'class': class_name,
                'method': method_name,
                'method_full': method_full,
                'signature': signature, 
                'path': file_path_val,
                'project': project
            })
    
    return methods





def generate_yaml(method_info: dict, provider_name: str, provider_model: str,
                  base_path: str = "../../data/aster-main/java/aster/GPT-4",
                  sub_dir: str = "Run-1") -> str:
    """Generates the YAML content for a method."""
    
    method_id = method_info['id']
    project = method_info['project']
    project_subdir = f"{project}/{sub_dir}" if sub_dir else project
    
    java_projects_dir = f"{base_path}/{project_subdir}"
    class_to_test = f"{java_projects_dir}/{method_info['path']}"
    
    experiment_name = f"Experiment{method_id}"
    description = f"Test {method_info['method_full']} - {provider_model}"
    
    yaml_content = YAML_TEMPLATE.format(
        experiment_name=experiment_name,
        description=description,
        class_to_test=class_to_test,
        method_to_test=method_info['method'],
        method_signature=method_info.get('signature', ''), 
        provider_name=provider_name,
        provider_model=provider_model,
        java_projects_dir=java_projects_dir
    )
    
    return yaml_content


def main():
    from utils.text.text_utils import sanitize_model_name
    
    parser = argparse.ArgumentParser(description='Generate experiment configurations')
    parser.add_argument('--provider', type=str, required=True,
                        help='Provider name (e.g., ollama_cloud, groq, chutes)')
    parser.add_argument('--model', type=str, required=True,
                        help='Model name (e.g., Qwen/Qwen2.5-Coder-32B-Instruct)')
    parser.add_argument('--methods-file', type=str, 
                        default='../../docs/TESTED METHODS.txt',
                        help='Path to the file with methods to test')
    parser.add_argument('--output-dir', type=str, 
                        default='../../scripts/configs',
                        help='Output directory for configurations')
    parser.add_argument('--base-path', type=str,
                        default='../../data/aster-main/java/aster/GPT-4',
                        help='Base path to Java projects (default: ASTER dataset path)')
    parser.add_argument('--sub-dir', type=str,
                        default='Run-1',
                        help='Subdirectory within each project folder (default: Run-1, use empty string for none)')
    parser.add_argument('--dry-run', action='store_true',
                        help='Show only what would be created without creating files')
    parser.add_argument('--range-start', type=int, default=None,
                        help='Start ID of method range (inclusive)')
    parser.add_argument('--range-end', type=int, default=None,
                        help='End ID of method range (inclusive)')
    
    args = parser.parse_args()
    
    script_dir = Path(__file__).parent
    project_root = script_dir.parent.parent  # Root of the project (artifact/)
    methods_file = script_dir / args.methods_file
    
    if not methods_file.exists():
        # Fallback: project_root/docs/TESTED METHODS.txt
        methods_file = project_root / 'docs' / 'TESTED METHODS.txt'

    if not methods_file.exists():
        # Fallback: project_root/TESTED METHODS.txt
        methods_file = project_root / 'TESTED METHODS.txt'
    
    if not methods_file.exists():
        methods_file = Path('TESTED METHODS.txt')
    
    if not methods_file.exists():
        print(f" Methods file not found: {methods_file}")
        print("   Try specifying the path with --methods-file")
        print(f"   Searched in: {script_dir / args.methods_file}, {project_root / 'docs'}")
        return
    
    print(f"Reading methods from: {methods_file}")
    methods = parse_methods_file(str(methods_file))
    print(f"Found {len(methods)} total methods")
    
    # Filter by range if specified
    if args.range_start is not None or args.range_end is not None:
        start = args.range_start if args.range_start is not None else 1
        end = args.range_end if args.range_end is not None else 999999
        print(f"Filtering ID range: {start} -> {end}")
        methods = [m for m in methods if start <= m['id'] <= end]
        print(f"Selected methods: {len(methods)}")
    
    if len(methods) == 0:
        print(" No methods found in the specified range!")
        return
    
    output_base = script_dir / args.output_dir
    
    model_name_safe = sanitize_model_name(args.model)
    yaml_filename = f"experiment_{model_name_safe}.yaml"
    
    print(f"\nConfiguration:")
    print(f"Provider: {args.provider}")
    print(f"Model: {args.model}")
    print(f"YAML file: {yaml_filename}")
    print(f"Output: {output_base}")
    
    if args.dry_run:
        print("\n DRY RUN - No files will be created\n")
    
    created = 0
    updated = 0
    
    for method in methods:
        method_id = method['id']
        experiment_dir = output_base / f"Experiment{method_id}"
        file_yaml = experiment_dir / yaml_filename
        
        if args.dry_run:
            exists = "✓" if file_yaml.exists() else "+"
            print(f"   {exists} Experiment{method_id}/{yaml_filename} - {method['method_full']}")
            continue
        
        experiment_dir.mkdir(parents=True, exist_ok=True)
        
        yaml_content = generate_yaml(method, args.provider, args.model,
                                      base_path=args.base_path, sub_dir=args.sub_dir)
        
        if file_yaml.exists():
            updated += 1
        else:
            created += 1
        
        with open(file_yaml, 'w', encoding='utf-8') as f:
            f.write(yaml_content)
    
    if not args.dry_run:
        print(f"\nDone!")
        print(f"Folders created/updated: {len(methods)}")
        print(f"YAML files created: {created}")
        print(f"YAML files updated: {updated}")
        print(f"\nTo run an experiment:")
        print(f"python main.py --config configs/Experiment1/{yaml_filename}")

"""
Usage (run from src/experiments/):
    python generate_experiments.py --provider chutes --model Qwen/Qwen2.5-Coder-32B-Instruct
    python generate_experiments.py --provider chutes --model unsloth/gemma-3-12b-it
    python generate_experiments.py --provider chutes --model unsloth/Mistral-Small-24B-Instruct-2501
"""

if __name__ == '__main__':
    main()
