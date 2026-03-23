## Prerequisites

Requires **Python 3.11+**. Install dependencies from the project root:

```bash
pip install -r docs/requirements.txt
```

This installs: `requests`, `javalang`, `python-dotenv`, `openai`, `ollama`, `huggingface_hub`, `google-genai`, `crystalbleu`, `nltk`, `transformers`, `torch`, `numpy`.

### API Keys Configuration

Create a `.env` file in the `src/` directory and add the API key(s) for the provider you intend to use:

```env
# Chutes
CHUTES_API_KEY=your_key_here
CHUTES_CHUTE_ID=your_chute_id_here        # optional

# Ollama Cloud
OLLAMA_API_KEY=your_key_here
OLLAMA_API_KEY_2=your_fallback_key_here    # optional fallback

# Ollama Colab
OLLAMA_COLAB_URL=your_cloudflare_tunnel_url

# Google Gemini
GEMINI_API_KEY=your_key_here

# Groq
GROQ_API_KEY=your_key_here

# Hugging Face
HUGGINGFACEHUB_API_TOKEN=your_token_here
```

> **Note:** You only need to set the variables for the provider you are using. All other lines can be omitted or left empty.

---

## Reproducing Experiments (Advanced Logic)

All experiments use the 50 methods defined in `docs/TESTED METHODS.txt` (IDs 1–50, across 7 projects: CLI, Cargotracker, Codec, Compress, Daytrader, JxPath, Petclinic).

### Pre-generated Scripts (Recommended)

To replicate the original research results with the **Advanced Logic**, use the pre-generated YAML configurations located in the `scripts/` folder. You have to set a valid CHUTES_API_KEY to replicate the same experiments.

#### Batch Run Commands (Advanced Logic)

| Model | Command to run (from `src/experiments/`) |
|-------|-------------------------------------------|
| **Gemma 3 12B** | `python run_experiments.py --provider chutes --modello unsloth/gemma-3-12b-it --configs-dir "scripts/SCRIPTS GEMMA-12B ADVANCED LOGIC" --yaml-name "experiment_unsloth_gemma-3-12b-it.yaml"` |
| **Mistral Small 24B** | `python run_experiments.py --provider chutes --modello unsloth/Mistral-Small-24B-Instruct-2501 --configs-dir "scripts/SCRIPTS MISTRAL-24B ADVANCED LOGIC" --yaml-name "experiment_unsloth_Mistral-Small-24B-Instruct-2501.yaml"` |
| **Qwen 2.5 Coder 32B** | `python run_experiments.py --provider chutes --modello Qwen/Qwen2.5-Coder-32B-Instruct --configs-dir "scripts/SCRIPTS QWEN-32B ADVANCED LOGIC" --yaml-name "experiment_Qwen_Qwen2_5-Coder-32B-Instruct.yaml"` |

> [!TIP]
> You can add `--start N --end M` to these commands to run only a subset of experiments (e.g., `--start 1 --end 10`).

### Manual/Custom Generation

If you want to test a **different model** or a **custom dataset**, follow these steps:

#### Step 1: Generate YAML Configs (optional)

From the `src/experiments/` directory, run:

```bash
python generate_experiments.py --provider <PROVIDER> --model <MODEL>
```

This creates one YAML config per method in `scripts/configs/ExperimentN/experiment_<model>.yaml`.

#### Step 2: Run Experiments

From the `src/experiments/` directory:

```bash
python run_experiments.py --provider <PROVIDER> --model <MODEL> --start 1 --end 50 --continue-on-error
```

**Key options:**

| Option | Description |
|--------|-------------|
| `--start N --end M` | Run experiments N through M |
| `--only 1,5,10` | Run only specific experiment IDs |
| `--configs-dir PATH` | Base directory for experiment configs (default: `scripts/configs`) |
| `--yaml-name NAME` | Specific YAML filename (default: `experiment_{model}.yaml`) |
| `--timeout 3600` | Timeout per experiment in seconds (default: 1h) |
| `--pause 5` | Pause between experiments in seconds |
| `--continue-on-error` | Don't stop on failure |
| `--dry-run` | Preview which experiments would run |

### Running a Single Experiment

You can also run a single experiment directly:

```bash
cd src
python main.py --config ../scripts/SCRIPTS_PATH/ExperimentN/yaml_filename.yaml
```


> **Note:** Special characters in model names (`.`, `/`) are replaced with `_` in filenames. For example, `Qwen/Qwen2.5-Coder-32B-Instruct` becomes `experiment_Qwen_Qwen2_5-Coder-32B-Instruct.yaml`.

### Creating New Experiments

#### Option A: Batch generation with `generate_experiments.py`

The script reads methods from `docs/TESTED METHODS.txt` (format: `ID | method_signature | relative/path/to/Class.java | ProjectName`) and generates one YAML config per method.

**For the ASTER dataset** (default):

```bash
python generate_experiments.py --provider chutes --model Qwen/Qwen2.5-Coder-32B-Instruct
```

This uses the default base path (`data/aster-main/java/aster/GPT-4/<Project>/Run-1/`).

**For a custom dataset**, use `--base-path` and `--sub-dir`:

```bash
python generate_experiments.py --provider chutes --model Qwen/Qwen2.5-Coder-32B-Instruct \
  --base-path ../../data/my-dataset --sub-dir ""
```

This will look for projects under `data/my-dataset/<ProjectName>/`.

To add a new method, simply add a line to `docs/TESTED METHODS.txt`:

```
51 | myMethod(String arg) | src/main/java/com/example/MyClass.java | MyProject
```

#### Option B: Manual YAML config (single experiment)

To run a single experiment without `generate_experiments.py`, create a YAML configuration file manually in `scripts/configs/` and set the `class_to_test` and `paths.java_projects_dir` fields to point to your project. See the [Configuration](#configuration) section below for the full format.

---

## Main Features

The system can operate in two modes:

- **Mode with dependencies**: Automatically analyzes all dependent classes of a method and generates tests considering part of the project context. Uses various techniques to select only the most relevant dependencies.

- **Mode without dependencies**: Generates tests and regenerates methods without analyzing dependencies, useful for simpler and isolated methods.

## How It Works

The system runs experiments in two consecutive phases:

### Phase 1: Test Generation

1. The LLM generates a complete JUnit 5 test suite for the specified method.
2. The **intelligent context** is dynamically built and includes:
   - The target method code
   - The fields and constructors of the host class
   - The relevant external dependencies (selected via TF-IDF, BM25, etc.)
   - **Abstract class handling**: If the target class is abstract, the prompt includes explicit instructions on how to instantiate it via an anonymous class or subclass, specifying which abstract methods must be implemented with dummy implementations for the tests
   - **Custom exceptions**: All custom Exception classes used by the method are automatically included in the context (with constructors and fields), allowing the LLM to correctly handle tests that need to catch or verify specific exceptions
3. The tests are automatically executed on the original method of the class.
4. The coverage (line and branch coverage) of the tested method is calculated.
5. The system outputs the results: tests executed, passed, failed, and coverage achieved.
6. If some tests do not pass on the original method, the experiment continues unless the generated tests failed due to syntax/import errors.

#### "Structural" Mode Detail (Context Generation)
When using `dependencies.selection: "structural"`, the system builds the prompt surgically to save tokens and reduce noise:
1. **Static Analysis**: Uses `javalang` to analyze the AST of the target method and identify all calls to external methods.
2. **Type Resolution**: Resolves the type of every object on which methods are called (mapping local variables, parameters, and class fields).
3. **Selective Extraction**:
   - For the **target class**: Includes fields, constructors, and *full implementation* of internal methods called (plus getter/setter useful for assertions).
   - For **dependent classes**: Includes *only* the fields, constructors, and *signatures* of methods actually invoked by the target method.
4. **Result**: The prompt contains only the class skeletons needed to compile and run the tests, avoiding the inclusion of untested method bodies or irrelevant code.

#### Smart Retry

If enabled in the configuration, the system uses a **Smart Retry** strategy to automatically improve generated tests. Here is how it works:

- **When it activates**: The retry loop starts when:
  - The similarity between original and regenerated method is below the configured threshold (e.g., if at least 80% is desired but only 60% is achieved)
  - There are compilation errors in the generated tests that prevent them from being executed
  - If the initially generated tests do not have 100% coverage and/or do not all pass on the original method

- **What it does at each refinement iteration**:
  1. Prepares a refinement prompt that includes:
     - The failed tests (with complete implementation) and the reason for their failure (failures or errors)
     - The passing tests (signatures only, to not modify them)
     - Compilation errors if present
     - The target method code specifying with a comment each line not covered by the previous tests
  2. Sends the prompt to the LLM to request test improvements
  3. Verifies that the LLM response compiles correctly
  4. **Intelligent test merging**:
     - The LLM generates new improved tests to replace the failed ones or to add to the test suite to cover uncovered lines of the method.
     - The system **keeps** all tests that already passed in the previous suite (to not lose already achieved coverage)
     - **Replaces** only the problematic tests with new improved versions
     - The final suite contains: `old_valid_tests + new_improved_tests`
  5. Runs the tests on the complete unified suite and checks if another retry is needed

#### Repair Phase (Compilation Fix)

When generated tests contain compilation errors, the **Repair Loop** is automatically activated:

- **Objective**: Make the tests compilable before executing them
- **Process**:
  1. The system extracts compilation errors from the Maven log
  2. Builds a repair prompt that includes:
     - The test code with errors
     - The exact compilation errors (e.g., "cannot find symbol", "incompatible types")
     - The target class structure (if abstract, includes suggestions for mocking)
  3. The LLM attempts to fix the errors
  4. Repeats for a maximum of `max_retries_repair` times (default: 2)

- **Unrecoverable error handling**: If after `max_retries_repair` attempts some tests still do not compile:
  - The system **precisely identifies** which tests compile and which do not (using Maven output)
  - **Keeps in the test suite ONLY the tests that compile** (both passed and failed with assert)
  - **Automatically discards** tests with unrecoverable compilation errors
  - This prevents the experiment from being completely blocked by malformed tests
  - The reduced suite is saved and used for method regeneration
  - If the error is structural (i.e., missing imports or helper methods with errors) the system discards the test suite and uses the last valid one.

In practice, it is like having an automatic feedback loop that improves the tests until they are good enough. Each iteration tries to solve the problems found in the previous one.

### Robustness and Infinite Loop Handling

To prevent poorly generated tests from blocking the computer (e.g., infinite loops), the system includes automatic protections:

- **Automatic timeout**: Each test execution has a maximum time of **60 seconds**. If a test suite takes longer, it is automatically interrupted.
- **Memory limit**: The system automatically modifies the `pom.xml` to limit test memory to **512MB** (`-Xmx512m`). This is to immediately stop tests that allocate too much memory in an infinite loop.

### Phase 2: Method Regeneration

1. The LLM regenerates the method based on the tests generated in phase 1 (or on manually provided tests if only phase 2 is executed).
2. The system always includes in the prompt a **CONTEXT - TARGET CLASS STRUCTURE** section that contains:
   - Original class name
   - Target class fields
   - Target class constructors
   - Signatures of internal methods used by the target method (excluding the target method itself, obviously)
3. If configured (`use_dependencies_second_phase: true`), the system also passes the external dependencies used by the original method, including:
   - External class name
   - External class fields
   - External class constructors
   - Signatures of external methods actually called
4. If only phase 2 is executed and a previously generated method is provided, the system:
   - Runs the tests on that method to obtain coverage and valid/invalid tests
   - Obfuscates the method name in the prompt (calling it 'x') to avoid bias
   - Passes the test results (coverage, valid/invalid tests) to the regeneration prompt
5. The regeneration produces a new method without any other code.
6. The same tests are executed on the regenerated method.
7. The coverage of the regenerated method is calculated.
8. The system outputs the detailed results for the regenerated method.

### Comparison and Metrics

After both phases, the system automatically calculates various similarity metrics between the original and regenerated method:

- **AST similarity**: Compares the syntax tree structure (if, loops, method calls, variables, etc.)
- **UniXcoder similarity**: Semantic code analysis using neural embeddings via UniXcoder model (NLP)
- **CrystalBLEU similarity**: Similarity metrics based on n-grams
- **Token and String similarity**: Percentage of tokens and strings that are the same
- **Line and branch coverage**: Percentage of code covered by tests
- **Pass rate**: Percentage of tests that passed on the method

All results are saved in JSON format and an HTML report is automatically generated. The HTML report shows for both methods (original and regenerated):
- Complete list of passed tests
- Complete list of failed tests
- Coverage achieved (line and branch)
- **Side-by-side comparison**: The original method and the regenerated method are shown side by side to facilitate visual comparison.

## Configuration

The system uses YAML files to configure experiments. Configuration files are stored in:

```
scripts/
└── configs/
    ├── Experiment1/
    │   └── experiment_<model_name>.yaml
    ├── Experiment2/
    │   └── experiment_<model_name>.yaml
    └── ...
```

When using `generate_experiments.py`, configs are created automatically. For manual experiments, create a folder (e.g., `scripts/configs/MyExperiment/`) and place your YAML file inside it.

### Complete YAML Example

```yaml
experiment: 
  name: "Experiment5"
  description: "Test for dependency selection"

mode: "dependencies"  # "dependencies" or "no_dependencies"

class_to_test: "../../data/my-project/src/main/java/com/example/MyClass.java"
max_dependencies: 10

dependencies:
  selection: "structural"   # "exact", "tfidf", "bm25", "pagerank", "hybrid", "summary", "structural"
  top_k: 5                  # Max dependencies to select (ignored for "exact", "summary", "structural")
  weights: [0.45, 0.45, 0.10]  # Only for "hybrid": [tfidf, bm25, pagerank] weights
  use_dependencies_second_phase: false  # Pass dependencies also in phase 2

smart_retry:
  enable: true
  max_retries_repair: 2        # Max attempts to fix compilation errors
  max_retries_refinement: -1   # -1 = adaptive mode, or a fixed number (e.g., 1, 2)

metrics:
  similarity_threshold: 0.7
  crystalbleu_stopwords: true

test_generation:
  mode: "single_method"
  method_to_test: "myMethodName"

generation_phases:
  run_first_phase: true
  run_second_phase: true
  test_file_for_second_phase: ""       # Optional: path to existing test file (phase 2 only)
  previous_generated_method: ""        # Optional: path to previously generated method (phase 2 only)

provider:
  name: "chutes"  # "chutes", "ollama_cloud", "ollama_colab", "ollama_local", "huggingface", "google_gemini", "groq"
  model: "Qwen/Qwen2.5-Coder-32B-Instruct"

prompt:
  test_generation_prompt: "Generate complete JUnit 5 tests..."
  method_regeneration_prompt: "Regenerate the method from provided tests..."

paths:
  base_dir: "./"                          # Working directory (usually src/)
  java_projects_dir: "../../data/my-project"  # Root of the Java project (must contain pom.xml)
  output_dir: "results/outputs"
```

### Field Reference

| Field | Description |
|-------|-------------|
| `class_to_test` | Path to the `.java` file containing the target method (relative to `src/` or absolute) |
| `paths.java_projects_dir` | Root directory of the Java/Maven project (the folder containing `pom.xml`) |
| `mode` | `"dependencies"` to include context from related classes, `"no_dependencies"` for isolated methods |
| `dependencies.selection` | Algorithm to select relevant dependencies (see [Dependency Selection](#dependency-selection) below) |
| `test_generation.method_to_test` | Name of the method to generate tests for |
| `provider.name` | LLM provider to use (see [Supported Providers](#supported-providers)) |
| `provider.model` | Model identifier (provider-specific) |
| `smart_retry.max_retries_refinement` | Number of refinement iterations, or `-1` for adaptive mode (see [Smart Retry](#smart-retry-configuration)) |
| `generation_phases.run_first_phase` | Set to `false` to skip test generation (phase 1) |
| `generation_phases.run_second_phase` | Set to `false` to skip method regeneration (phase 2) |

### Dependency Selection

When using `mode: "dependencies"`, you can choose how to select the most relevant classes (the class containing the target method is always passed to the LLM):

- **`exact`**: Selects only the classes actually used in the method (exact matching). More precise but may exclude useful classes.
- **`tfidf`**: Uses TF-IDF to find classes with terms similar to the query. Good for lexical similarity.
- **`bm25`**: Uses BM25 which is similar to TF-IDF but often more effective.
- **`pagerank`**: Selects important classes in the dependency graph. Useful for finding central classes.
- **`hybrid`**: Combines tfidf, bm25, and pagerank with customizable weights.
- **`summary`**: Mode that passes the complete source of the target class and only the signatures (without implementation) of external methods actually used by the method to test, along with fields and constructors of external classes. Useful when you want to give the LLM the complete context of the class but only the interfaces of called external methods.
- **`structural`**: Minimal mode (for token saving) that passes only the necessary structural elements: all imports of the target class, class name, fields, constructors, signatures of internal methods used by the target method (excluding the target method itself), signatures of external methods used (with import, class name, fields, and constructors of external classes), and the complete target method with implementation and javadoc if present. Useful when you want to give the LLM only the essential structural information without the complete code of the target class. Additionally includes:
     - **Fields and Constructors** of used classes (fundamental for instantiating objects).
     - **Cast Detection**: Automatically includes "hidden" classes behind casts (e.g., `(Customer) obj`).
     - **Generics**: Extracts types from lists and maps (e.g., `List<Order>` -> includes `Order`).
     - **Internal Methods**: Includes the *complete* implementation (not just signatures) of called helper methods.
     - **Superclasses**: Includes inherited fields and methods if the class extends a base class.
     - **Static Constants**: Detects accesses to `Class.CONSTANT`.
     Ideal for unit tests that require complex setup of data objects (DTO/Value Objects).

The `top_k` parameter controls how many dependencies to select (e.g., `top_k: 5` selects the top 5). Note: `top_k` is ignored when using `summary`, `exact`, or `structural`.

### Smart Retry Configuration

**Available parameters:**
- `max_retries_repair`: Maximum number of attempts to fix compilation errors (default: 2)
- `max_retries_refinement`: Maximum number of iterations to improve coverage/similarity
  - Normal values (e.g., `1`, `2`): Fixed number of attempts
  - **Special value `-1`**: Activates **adaptive mode** that automatically continues refining tests

#### Adaptive Mode (`max_retries_refinement: -1`)

When set to `-1`, the system enters an "intelligent" mode that automatically decides whether to continue refining tests based on the quality of the **regenerated method**.

The core idea is: *better tests produce a better regenerated method*. The system keeps iterating until the regenerated method is similar enough to the original, or a safety limit is reached.

**Stop conditions (checked in order):**

1. **Similarity threshold reached** → **STOP**
   - The similarity between the original and the regenerated method is ≥ `similarity_threshold`
   - This is the **primary goal**: once the regenerated method is close enough to the original, the process stops immediately regardless of coverage or pass rate

2. **Perfect test suite but similarity still below threshold** → **CONTINUE**
   - Line coverage = 100% on the original method AND all tests pass (tests_passed = tests_total)
   - BUT similarity < `similarity_threshold`
   - The system **keeps refining the tests** hoping that improved tests will lead to a better regeneration (and therefore higher similarity)

3. **Hard cap of 5 iterations** → **STOP**
   - Absolute maximum limit to prevent infinite loops
   - The process stops even if the similarity threshold has not been reached

**Practical behavior:**

Example: `max_retries_refinement: -1` and `similarity_threshold: 0.7`

*Iteration 1:*
- Coverage: 60%, Tests passed: 8/10, Similarity: 0.50
- → **CONTINUE** (similarity < 0.7, tests not yet optimal)

*Iteration 2:*
- Coverage: 85%, Tests passed: 9/10, Similarity: 0.65
- → **CONTINUE** (similarity < 0.7)

*Iteration 3:*
- Coverage: 100%, Tests passed: 10/10, Similarity: 0.68
- → **CONTINUE** (tests are perfect, but similarity 0.68 < 0.7 — the system refines tests further to improve the regenerated method)

*Iteration 4:*
- Coverage: 100%, Tests passed: 11/11, Similarity: 0.73
- → **STOP** ✅ (similarity 0.73 ≥ 0.7 — goal reached)

*Alternative — hard cap scenario after 5 iterations:*
- Coverage: 95%, Tests passed: 9/10, Similarity: 0.62
- → **STOP** (hard cap reached, even though similarity < 0.7)


### Similarity Threshold Calculation

The system evaluates the quality of the regenerated method by comparing it with the original using a similarity threshold (`similarity_threshold`). This threshold can be **fixed** or **dynamic**.

#### 1. Fixed Threshold (`use_dynamic_threshold: false`)
The exact value specified in the YAML file is used:
*   Default: `0.70` (70%)
*   If the `metrics.similarity_threshold` attribute is set to `0.5`, the system will require at least 50% overall similarity.

#### 2. Dynamic Threshold (`use_dynamic_threshold: true`)
The threshold is automatically recalculated based on the complexity of the original method, measured in **SLOC** (Source Lines of Code, excluding comments and empty lines).

**Calculation Logic:**
The system applies a "logarithmic decrement" to the base threshold to account for natural code variability:
*   **Small Methods (< 10 SLOC)**: Require a very high similarity (~80%), as there is less room for implementative variability.
*   **Medium Methods (~20 SLOC)**: Maintain approximately the set base threshold (e.g., 70%).
*   **Large Methods (> 50 SLOC)**: The threshold is automatically lowered (~60%), as long methods can be correctly implemented in different ways while maintaining the same semantics.

**Mathematical Formula:**
```python
decremento = 0.05 * log10(max(sloc, 10) / 10)
final_threshold = base_threshold - decremento
```

**Safety Limits (Caps):**
Regardless of the calculation, the final threshold is always constrained between:
*   **Minimum**: `0.55` (55%) - Below this threshold, the method is considered too different.
*   **Maximum**: `0.80` (80%) - Above this threshold, the requirement becomes unrealistic for complex methods.

> [!IMPORTANT]
> If you set a very low base threshold (e.g., `0.5`) in the YAML, the dynamic calculation might fall below 0.55. In this case, the system will still apply the **minimum cap of 0.55**.

#### Fixed Mode (e.g., `max_retries_refinement: 2`)

Example: `max_retries_refinement: 2` and `similarity_threshold: 0.7`

1. Generates initial tests
2. If the similarity between original and regenerated method is < 70% or there are errors, performs refinement retry 1
3. If similarity is still < 70% or there are errors, performs retry 2
4. After retry 2, stops even if 70% has not been reached (because max_retries has been reached)

---

## Usage

```bash
cd src
python main.py --config ../scripts/configs/Experiment1/experiment1.yaml
```

You can also execute only one of the two phases by setting `run_first_phase: false` or `run_second_phase: false` in the configuration file.

## Output

The system generates various outputs:

- **Test file**: Saved in the `src/test/java` folder of the Java project
- **Metrics**: JSON file in `results/outputs/metrics/` containing all calculated metrics, including the names of passed and failed tests
- **HTML report**: Visual report automatically generated from the metrics JSON file that shows:
  - Passed and failed tests for the original method
  - Passed and failed tests for the regenerated method
  - Coverage and similarity metrics
- **Logs**: Complete prompts sent to the LLM and responses saved in `results/logs/prompts/`
- **Console output**: Detailed logs during execution showing the status of each phase, test results, and coverage


## Supported Providers

The system supports various LLM providers:

- **Chutes**: Chutes.ai cloud provider offering access to various open-source models (e.g., openai/gpt-oss-20b).
- **Ollama Cloud**: Ollama cloud provider with support for automatic fallback to a secondary API key.
- **Ollama Colab**: Connection to Ollama instances on Google Colab via Cloudflare tunnel.
- **Ollama Local**: Local version of Ollama.
- **Hugging Face**: Uses models from Hugging Face.
- **Google Gemini**: Uses the Google Gemini API.
- **Groq**: Uses the Groq API (e.g., llama-3.3-70b, qwen2.5-coder).

