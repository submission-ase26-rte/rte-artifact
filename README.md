This repository contains the artifact for an anonymous ASE 2026 submission.
It includes the implementation, subject systems, and scripts to reproduce the experiments.

## Documentation

| Document | Description |
|----------|-------------|
| [INSTRUCTIONS.md](docs/INSTRUCTIONS.md) | Full usage guide: prerequisites, how to reproduce experiments, configuration reference, dependency selection modes, smart retry, and supported LLM providers. |
| [ADVANCED_LOGIC.md](docs/ADVANCED_LOGIC.md) | Describes the Advanced Logic: test generation, repair loop, refinement loop with adaptive thresholds, similarity enhancement mode, dynamic similarity threshold, and failure handling. |
| [BASELINE_LOGIC.md](docs/BASELINE_LOGIC.md) | Describes the Baseline Logic and its main differences compared to the Advanced Logic, including its limitations. |
| [PROMPT_TEMPLATES.md](docs/PROMPT_TEMPLATES.md) | Documents the 4 prompt templates used by the system: test generation, repair, refinement (improvement and optimization), and method regeneration, including conditional/dynamic sections. |
| [REPOSITORY_STRUCTURE.md](docs/REPOSITORY_STRUCTURE.md) | Complete directory tree of the repository with descriptions of each module: core logic, providers, utilities, experiments, scripts, and data. |