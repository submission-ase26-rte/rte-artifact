import sys
import time
import argparse
import subprocess
from pathlib import Path
from datetime import datetime

script_dir = Path(__file__).parent
src_dir = script_dir.parent
if str(src_dir) not in sys.path:
    sys.path.insert(0, str(src_dir))

from utils.text.text_utils import sanitize_model_name

if sys.platform == 'win32':
    try:
        sys.stdout.reconfigure(encoding='utf-8', errors='replace')
        sys.stderr.reconfigure(encoding='utf-8', errors='replace')
    except AttributeError:
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
        sys.stderr = io.TextIOWrapper(sys.stderr.buffer, encoding='utf-8', errors='replace')


from utils.text.text_utils import sanitize_model_name


def get_experiment_ids(args) -> list:
    """Restituisce la lista degli ID esperimenti da eseguire."""
    if args.only:
        # Lista specifica di ID
        return [int(x.strip()) for x in args.only.split(',')]
    else:
        # Range da start a end
        return list(range(args.start, args.end + 1))


def run_experiment(config_path: str, timeout: int = None) -> dict:
    start_time = time.time()
    
    try:
        # Esegue main.py come sottoprocesso
        # Cambio: eseguiamo da script_dir (src/experiments) per far sì che i path relativi nel YAML (../../data) funzionino
        result = subprocess.run(
            [sys.executable, '../main.py', '--config', str(config_path)],
            capture_output=True,
            text=True,
            encoding='utf-8',
            errors='replace',
            timeout=timeout,
            cwd=script_dir
        )
        
        duration = time.time() - start_time
        
        return {
            'success': result.returncode == 0,
            'returncode': result.returncode,
            'stdout': result.stdout,
            'stderr': result.stderr,
            'duration': duration,
            'error': None
        }
    
    except subprocess.TimeoutExpired:
        return {
            'success': False,
            'returncode': -1,
            'stdout': '',
            'stderr': 'Timeout expired',
            'duration': timeout,
            'error': 'TIMEOUT'
        }
    
    except Exception as e:
        return {
            'success': False,
            'returncode': -1,
            'stdout': '',
            'stderr': str(e),
            'duration': time.time() - start_time,
            'error': str(e)
        }


def format_duration(seconds: float) -> str:
    """Formatta la durata in formato leggibile."""
    if seconds < 60:
        return f"{seconds:.1f}s"
    elif seconds < 3600:
        minutes = int(seconds // 60)
        secs = int(seconds % 60)
        return f"{minutes}m {secs}s"
    else:
        hours = int(seconds // 3600)
        minutes = int((seconds % 3600) // 60)
        return f"{hours}h {minutes}m"


def main():
    parser = argparse.ArgumentParser(description='Esegue gli esperimenti in sequenza')
    parser.add_argument('--provider', type=str, required=True,
                        help='Provider name (es: ollama_cloud, groq, ollama_colab)')
    parser.add_argument('--model', type=str, required=True,
                        help='Model name (es: gpt-oss:20b-cloud)')
    parser.add_argument('--start', type=int, default=1,
                        help='Start ID of the experiment (default: 1)')
    parser.add_argument('--end', type=int, default=50,
                        help='End ID of the experiment (default: 50)')
    parser.add_argument('--only', type=str, default=None,
                        help='List of specific IDs separated by comma (es: 1,5,10)')
    parser.add_argument('--timeout', type=int, default=3600,
                        help='Timeout for each experiment in seconds (default: 3600 = 1 h)')
    parser.add_argument('--pause', type=int, default=5,
                        help='Pause between experiments in seconds (default: 5)')
    parser.add_argument('--configs-dir', type=str, default=None,
                        help='Base directory for experiment configs (default: scripts/configs)')
    parser.add_argument('--yaml-name', type=str, default=None,
                        help='Specific YAML filename (default: experiment_{model}.yaml)')
    parser.add_argument('--continue-on-error', action='store_true',
                        help='Continue even if an experiment fails')
    parser.add_argument('--dry-run', action='store_true',
                        help='Show only what would be executed without executing')
    parser.add_argument('--log-file', type=str, default=None,
                        help='Log file for the results')

    
    args = parser.parse_args()
    
    script_dir = Path(__file__).parent
    project_root = script_dir.parent.parent
    
    # Risoluzione directory config
    if args.configs_dir:
        configs_dir = Path(args.configs_dir)
        if not configs_dir.is_absolute():
            configs_dir = project_root / args.configs_dir
    else:
        configs_dir = project_root / 'scripts' / 'configs'
    
    model_name_safe = sanitize_model_name(args.model)
    
    # Risoluzione nome file YAML
    if args.yaml_name:
        yaml_filename = args.yaml_name
    else:
        yaml_filename = f"experiment_{model_name_safe}.yaml"

    
    experiment_ids = get_experiment_ids(args)
    
    print("=" * 70)
    print("RUNNING ASTER EXPERIMENTS")
    print("=" * 70)
    print(f"   Provider: {args.provider}")
    print(f"   Model: {args.model}")
    print(f"   YAML file: {yaml_filename}")
    print(f"   Experiments: {len(experiment_ids)} ({min(experiment_ids)} - {max(experiment_ids)})")
    print(f"   Timeout: {format_duration(args.timeout)}")
    print(f"   Pause: {args.pause}s")
    print("=" * 70)
    
    if args.dry_run:
        print("\n DRY RUN - No experiments will be executed\n")
        for exp_id in experiment_ids:
            config_path = configs_dir / f"Experiment{exp_id}" / yaml_filename
            exists = "✓" if config_path.exists() else "✗"
            print(f"   [{exists}] Experiment{exp_id}: {config_path}")
        return
    
    if args.log_file:
        log_path = Path(args.log_file)
    else:
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        # Cambio: i risultati ora vanno in /experiments/results
        project_root = script_dir.parent.parent
        results_dir = project_root / 'experiments' / 'results'
        log_path = results_dir / 'logs' / f"batch_run_{model_name_safe}_{timestamp}.log"

    
    log_path.parent.mkdir(parents=True, exist_ok=True)

    
    # Statistiche
    stats = {
        'total': len(experiment_ids),
        'completed': 0,
        'success': 0,
        'failed': 0,
        'skipped': 0,
        'timeout': 0
    }
    
    results = []
    start_batch_time = time.time()
    
    print(f"\n Log: {log_path}\n")
    
    with open(log_path, 'w', encoding='utf-8') as log_file:
        log_file.write(f"BATCH RUN - {datetime.now().isoformat()}\n")
        log_file.write(f"Provider: {args.provider}\n")
        log_file.write(f"Model: {args.model}\n")
        log_file.write(f"Experiments: {experiment_ids}\n")
        log_file.write("=" * 70 + "\n\n")
        
        for i, exp_id in enumerate(experiment_ids, 1):
            config_path = configs_dir / f"Experiment{exp_id}" / yaml_filename
            
            print(f"[{i}/{stats['total']}] Experiment{exp_id}...", end=" ", flush=True)
            log_file.write(f"[{datetime.now().isoformat()}] Experiment{exp_id}\n")
            
            if not config_path.exists():
                print("SKIP (config not found)")
                log_file.write(f"   SKIP: Config not found: {config_path}\n\n")
                stats['skipped'] += 1
                continue
            
            result = run_experiment(str(config_path), timeout=args.timeout)
            stats['completed'] += 1
            
            if result['success']:
                print(f"OK ({format_duration(result['duration'])})")
                log_file.write(f"   SUCCESS in {format_duration(result['duration'])}\n")
                stats['success'] += 1
            elif result['error'] == 'TIMEOUT':
                print(f"TIMEOUT ({format_duration(result['duration'])})")
                log_file.write(f"   TIMEOUT after {format_duration(result['duration'])}\n")
                stats['timeout'] += 1
                stats['failed'] += 1
            else:
                print(f"FAIL ({format_duration(result['duration'])})")
                log_file.write(f"   FAILED in {format_duration(result['duration'])}\n")
                log_file.write(f"   Error: {result['stderr'][:500]}\n")
                stats['failed'] += 1
            
            log_file.write("\n")
            log_file.flush()
            
            results.append({
                'id': exp_id,
                **result
            })
            
            if not result['success'] and not args.continue_on_error:
                print("\n⛔ Interruption due to error (use --continue-on-error to ignore)")
                break
                
            if i < len(experiment_ids) and args.pause > 0:
                time.sleep(args.pause)
        
        total_time = time.time() - start_batch_time
        
        summary = f"""
{'=' * 70}
SUMMARY
{'=' * 70}
    Total experiments: {stats['total']}
    Completed: {stats['completed']}
    Success: {stats['success']}
    Failed: {stats['failed']}
    Skipped: {stats['skipped']}
    Timeout: {stats['timeout']}
   
    Total time: {format_duration(total_time)}
    Average time: {format_duration(total_time / max(stats['completed'], 1))}
{'=' * 70}
"""
        print(summary)
        log_file.write(summary)
    
    print(f"\n Log saved in: {log_path}")
    
    if stats['failed'] > 0:
        sys.exit(1)

"""
Usage:
    python run_experiments.py --provider ollama_cloud -- model gpt-oss:20b-cloud
    python run_experiments.py --provider groq --model llama-3.3-70b-versatile --start 1 --end 50
    python run_experiments.py --provider ollama_colab --model granite3-dense:8b --only 1,5,10
"""

if __name__ == '__main__':
    main()

