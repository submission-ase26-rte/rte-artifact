# Artifact for “RTE-Test: Exploiting Round-Trip Engineering for Effective LLM-Based Unit Test Generation”

This repository contains the artifact accompanying the ASE 2026 submission  
**“RTE-Test: Exploiting Round-Trip Engineering for Effective LLM-Based Unit Test Generation.”**  
It includes the full implementation of RTE-Test, all subject systems, and the scripts required to reproduce the experimental results reported in the paper.

---

## Contents of the Artifact

The repository provides:

- **RTE-Test implementation**  
  Complete source code of the framework, including context extraction, generation pipeline, compiler-guided repair, iterative refinement, and round-trip semantic validation.

- **Subject systems**  
  Extracted from 7 real-world open‑source projects used in the evaluation.

- **Experiment scripts**  
  Fully automated pipelines for:
  - structural-context construction  
  - initial test generation  
  - compilation-based repair  
  - iterative refinement  
  - round-trip regeneration and similarity computation

- **Collected data and logs**  
  Coverage reports, similarity scores, compilation logs, and output of all executions.

---

## Documentation

| Document | Description |
|----------|-------------|
| [INSTRUCTIONS.md](docs/INSTRUCTIONS.md) | Full usage guide: prerequisites, how to reproduce experiments, configuration reference, dependency selection modes, smart retry, and supported LLM providers. |
| [LOGIC_DETAILS.md](docs/LOGIC_DETAILS.md) | Describes the logic: test generation, repair loop, refinement loop with adaptive thresholds, similarity enhancement mode, dynamic similarity threshold, and failure handling.|
| [PROMPT_TEMPLATES.md](docs/PROMPT_TEMPLATES.md) | Documents the 4 prompt templates used by the system: test generation, repair, refinement (improvement and optimization), and method regeneration, including conditional/dynamic sections. |
| [REPOSITORY_STRUCTURE.md](docs/REPOSITORY_STRUCTURE.md) | Complete directory tree of the repository with descriptions of each module: core logic, providers, utilities, experiments, scripts, and data. |
