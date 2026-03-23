import yaml

# Mapping: English YAML key -> Italian key (used internally by ExperimentConfig)
EN_TO_IT_KEYS = {
    # Top-level sections
    "experiment": "experiment",
    "mode": "modalita",
    "class_to_test": "classe_da_testare",
    "dependencies": "dipendenze",
    "generation_phases": "fasi_generazione",
    "test_generation": "generazione_test",
    "metrics": "metrics",
    "paths": "paths",

    # experiment.*
    "name": "name",
    "description": "description",
    "version": "versione",

    # dependencies.*
    "selection": "selezione",
    "use_dependencies_second_phase": "usa_dipendenze_seconda_fase",

    # smart_retry.*
    "enable": "abilita",
    "min_weighted_pass_rate_threshold": "soglia_min_weighted_pass_rate",
    "min_coverage_threshold": "soglia_min_coverage",

    # generation_phases.*
    "run_first_phase": "esegui_prima_fase",
    "run_second_phase": "esegui_seconda_fase",
    "test_file_for_second_phase": "file_test_per_seconda_fase",
    "previous_generated_method": "metodo_generato_precedente",
    "include_previous_method": "includi_metodo_precedente",

    # metrics.*
    "similarity_threshold": "similarity_threshold",
    "use_dynamic_threshold": "usa_soglia_dinamica",

    # test_generation.*
    "method_to_test": "metodo_da_testare",
    "method_signature": "metodo_signature",

    # provider.*
    "model": "model",

    # prompt.*
    "test_generation_prompt": "generazione_test_metodo",
    "method_regeneration_prompt": "rigenerazione_metodo",
    "use_special_instructions": "usa_istruzioni_speciali",

    # paths.*
    "logs_dir": "logs_dir",
    "output_dir": "output_dir",
    "base_dir": "base_dir",
}

# Mapping: English YAML values -> Italian values (for fields whose values are compared in code)
EN_TO_IT_VALUES = {
    "dependencies": "dipendenze",
    "no_dependencies": "senza_dipendenze",
    "single_method": "singolo_metodo",
}


def _translate_keys(data):
    """
    Recursively translates English YAML keys and specific values to Italian.
    Keys/values not in the mapping are kept as-is (already Italian or shared).
    """
    if not isinstance(data, dict):
        return data

    translated = {}
    for key, value in data.items():
        new_key = EN_TO_IT_KEYS.get(key, key)
        if isinstance(value, dict):
            translated[new_key] = _translate_keys(value)
        elif isinstance(value, str):
            translated[new_key] = EN_TO_IT_VALUES.get(value, value)
        else:
            translated[new_key] = value
    return translated


def carica_config(percorso_config: str) -> dict:
    try:
        with open(percorso_config, "r", encoding="utf-8") as f:
            raw_config = yaml.safe_load(f)
    except FileNotFoundError:
        print(f" Configuration file not found: {percorso_config}")
        return {}
    except yaml.YAMLError as e:
        print(f" YAML parsing error: {e}")
        return {}

    if not raw_config:
        return {}

    # Translate English keys/values to Italian (no-op if already Italian)
    return _translate_keys(raw_config)
