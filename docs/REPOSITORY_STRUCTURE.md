## Repository Structure

```
RTE - ASE 2026/
├── README.md
├── data/                                    # Java projects used as test subjects
│   └── aster-main/
│       └── java/                            # ASTER dataset (7 projects: CLI, Cargotracker, Codec, etc.)
│
├── docs/                                    # Documentation and analysis outputs
│   ├── INSTRUCTIONS.md                      # Full usage & configuration guide
│   ├── LOGIC_DETAILS.md                     # Description of the Test Generation Logic
│   ├── PROMPT_TEMPLATES.md                  # All prompt templates used by the system
│   ├── TESTED METHODS.txt                   # 50 target methods (ID | signature | path | project)
│   ├── requirements.txt                     # Python dependencies
│   └── graphs/                              # Pre-generated analysis plots (PNG)
│
├── experiments/                             # Raw experiment results (JSON + HTML reports)
│   ├── RESULTS RTE-Test/                    # Results using the RTE-Test agent
│   │   ├── GEMMA 12B/
│   │   ├── MISTRAL 24B/
│   │   └── QWEN 32B/
│   └── RESULTS ASTER/                       # Original ASTER benchmark results
│       └── Experiment1..50/
│
├── scripts/                                 # Pre-generated YAML configs for batch runs
│   ├── SCRIPTS GEMMA-12B/                   # Configs for Gemma 3 12B
│   ├── SCRIPTS MISTRAL-24B/                 # Configs for Mistral Small 24B
│   └── SCRIPTS QWEN-32B/                    # Configs for Qwen 2.5 Coder 32B
│
└── src/                                     # Source code
    ├── main.py                              # Entry point — runs a single experiment
    ├── config.py                            # Global settings and paths
    ├── .env                                 # API keys (not committed)
    │
    ├── core/                                # Core experiment logic
    │   ├── analysis/
    │   │   └── dependency_analyzer.py       # Static analysis & dependency extraction
    │   ├── config/
    │   │   └── experiment_config.py         # YAML config loader & validation
    │   ├── evaluation/
    │   │   └── evaluation.py                # Similarity metrics (AST, UniXcoder, CrystalBLEU, etc.)
    │   ├── generation/
    │   │   ├── generation.py                # Test generation (no-dependency mode)
    │   │   └── generation_with_dependencies.py  # Test generation (dependency mode)
    │   ├── retry/
    │   │   ├── smart_retry.py               # Smart retry loop (adaptive & fixed)
    │   │   └── refinement.py                # Test refinement logic
    │   └── test/
    │       └── test_suite.py                # Test suite data model
    │
    ├── experiments/                         # Batch experiment management
    │   ├── generate_experiments.py           # Generates YAML configs for all 50 methods
    │   └── run_experiments.py                # Runs experiments in batch with error handling
    │
    ├── providers/                            # LLM provider adapters
    │   ├── provider_base.py                 # Abstract base class
    │   ├── chutes_provider.py               # Chutes.ai
    │   ├── ollama_cloud_provider.py         # Ollama Cloud
    │   ├── ollama_colab_provider.py         # Ollama via Colab + Cloudflare
    │   ├── ollama_local_provider.py         # Ollama Local
    │   ├── google_gemini_provider.py        # Google Gemini
    │   ├── groq_provider.py                 # Groq
    │   └── huggingface_provider.py          # Hugging Face
    │
    └── utils/                               # Shared utilities
        ├── code/
        │   ├── code_analysis.py             # Java code parsing helpers
        │   ├── dependency_selector.py       # TF-IDF / BM25 / PageRank selectors
        │   └── error_extractor.py           # Maven compilation error extraction
        ├── io/
        │   ├── file_utils.py                # File read/write helpers
        │   ├── safe_file_manager.py         # Atomic file operations
        │   └── yaml_loader.py               # YAML loading utilities
        ├── prompt/
        │   ├── prompt_helpers.py            # Prompt construction utilities
        │   ├── prompt_logger.py             # Prompt/response logging
        │   └── refinement_prompt_builder.py # Builds refinement prompts
        ├── providers/
        │   └── provider_utils.py            # Provider helper functions
        ├── reporting/
        │   └── report_generator.py          # HTML report generation
        ├── test/
        │   ├── test_executor.py             # Maven test execution wrapper
        │   ├── test_runner_with_coverage.py  # Coverage-aware test runner
        │   └── test_utils.py                # Test parsing & manipulation
        ├── text/
        │   ├── text_utils.py                # String/text processing
        │   └── output_utils.py              # Console output formatting
        └── tracking/
            ├── logger.py                    # Experiment logging
            ├── naturalness_evaluator.py     # Code naturalness scoring
            └── token_tracker.py             # LLM token usage tracking
```
