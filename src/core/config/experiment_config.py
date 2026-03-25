import os
from typing import Dict, Optional, Tuple
from utils.io.yaml_loader import carica_config


class ExperimentConfig:
    """Class to manage the configuration of an experiment"""
    
    def __init__(self, esperimento_cfg_path: str):
        self.cfg_path = esperimento_cfg_path
        self.cfg = carica_config(esperimento_cfg_path)
        
        if not self.cfg:
            raise ValueError(f"Configuration file not found: {esperimento_cfg_path}")
        
        self._load_config()
        self._validate()
    
    def _load_config(self):
        """Carica tutte le configurazioni dal file YAML (già tradotte in italiano da yaml_loader)"""
        esperimento = self.cfg.get("experiment", {})
        dip_cfg = self.cfg.get("dipendenze", {})
        fasi_generazione = self.cfg.get("fasi_generazione", {})
        metriche = self.cfg.get("metrics", {})
        smart_retry_cfg = self.cfg.get("smart_retry", {})
        generazione_test = self.cfg.get("generazione_test", {})
        provider_cfg = self.cfg.get("provider", {})
        prompt_cfg = self.cfg.get("prompt", {})
        percorsi = self.cfg.get("paths", {})
        
        # Configurazione base
        self.modalita = self.cfg.get("modalita", "dipendenze")
        self.classe_da_testare = self.cfg.get("classe_da_testare", "")
        self.max_dependencies = self.cfg.get("max_dependencies", 10)
        
        # Dipendenze
        self.metodo_selezione = dip_cfg.get("selezione")
        self.top_k_selezione = dip_cfg.get("top_k")
        self.usa_dipendenze_seconda_fase = dip_cfg.get("usa_dipendenze_seconda_fase", False)
        
        # Fasi
        self.esegui_prima_fase = fasi_generazione.get("esegui_prima_fase", True)
        self.esegui_seconda_fase = fasi_generazione.get("esegui_seconda_fase", True)
        self.file_test_per_seconda_fase = fasi_generazione.get("file_test_per_seconda_fase", "")
        self.metodo_generato_precedente = fasi_generazione.get("metodo_generato_precedente", "")
        self.includi_metodo_precedente = fasi_generazione.get("includi_metodo_precedente", False)
        
        # Metriche
        self.soglia_similarita = metriche.get("similarity_threshold", 0.7)
        self.cb_use_stopwords = metriche.get("crystalbleu_stopwords", True)
        self.usa_soglia_dinamica = metriche.get("usa_soglia_dinamica", True)
        
        # Pesi metriche di similarità
        DEFAULT_WEIGHTS = {
            "crystalbleu_similarity": 0.20,
            "string_similarity": 0.08,
            "token_similarity": 0.15,
            "ast_similarity": 0.22,
            "embedding_similarity": 0.35,
        }
        raw_weights = metriche.get("similarity_weights", None)
        if raw_weights and isinstance(raw_weights, dict):
            valid_keys = set(DEFAULT_WEIGHTS.keys())
            filtered = {k: float(v) for k, v in raw_weights.items() if k in valid_keys}
            if filtered:
                # Normalizza a 1.0
                total = sum(filtered.values())
                if total > 0:
                    self.similarity_weights = {k: v / total for k, v in filtered.items()}
                else:
                    self.similarity_weights = DEFAULT_WEIGHTS
            else:
                self.similarity_weights = DEFAULT_WEIGHTS
        else:
            self.similarity_weights = None  # Usa i default in evaluation.py
        
        # SmartRetry
        self.max_retries_repair = smart_retry_cfg.get("max_retries_repair", 2)
        self.max_retries_refinement = smart_retry_cfg.get("max_retries_refinement", 3)
        self.soglia_coverage = smart_retry_cfg.get("soglia_coverage", 0.0)
        self.abilita_smart_retry = smart_retry_cfg.get("abilita", False)
        
        # Soglie minime per rigenerazione metodo
        self.soglia_min_weighted_pass_rate = smart_retry_cfg.get("soglia_min_weighted_pass_rate", 0.7)
        self.soglia_min_coverage = smart_retry_cfg.get("soglia_min_coverage", 0.3)
        
        # Generazione test
        self.modalita_generazione = generazione_test.get("modalita", "singolo_metodo")
        self.metodo_da_testare = generazione_test.get("metodo_da_testare", "")
        self.metodo_signature = generazione_test.get("metodo_signature", "")
        
        # Provider
        self.provider_nome = provider_cfg.get("name", "ollama_cloud")
        self.model_name = provider_cfg.get("model", "gpt-oss:120b-cloud")
        
        # Prompt
        self.prompt_test_metodo = prompt_cfg.get("generazione_test_metodo", "")
        self.prompt_rigenera_metodo = prompt_cfg.get("rigenerazione_metodo", "")
        self.usa_istruzioni_speciali = prompt_cfg.get("usa_istruzioni_speciali", True)
        
        # Percorsi
        self.base_dir = percorsi.get("base_dir", "./")
        self.java_projects_dir = percorsi.get("java_projects_dir")
        
        # Progetto root (salendo da src/core/config)
        from config import RESULTS_DIR, LOGS_DIR
        
        raw_output_dir = percorsi.get("output_dir", "results/outputs")
        # Forza i risultati in experiments/results se il path è relativo o quello di default
        if raw_output_dir == "results/outputs" or not os.path.isabs(raw_output_dir):
            self.output_dir = RESULTS_DIR
        else:
            self.output_dir = raw_output_dir

            
        self.logs_dir = percorsi.get("logs_dir", LOGS_DIR)

        
        # Esperimento
        self.versione_esperimento = esperimento.get("versione", "")  # Default vuoto - versione opzionale
        self.nome_esperimento = esperimento.get("name")
        self.descrizione = esperimento.get("description", "")
        
        # Deriva nome esperimento se non specificato
        if not self.nome_esperimento:
            config_dir = os.path.dirname(self.cfg_path)
            self.nome_esperimento = os.path.basename(config_dir) if config_dir else "Esperimento"
            if self.nome_esperimento == "" or self.nome_esperimento == ".":
                self.nome_esperimento = os.path.basename(self.cfg_path).replace('.yaml', '').replace('.yml', '')
    
    def _validate(self):
        """Validate the configuration"""
        if not self.esegui_prima_fase and not self.esegui_seconda_fase:
            raise ValueError("No generation phase active")
        
        if self.modalita_generazione == "singolo_metodo" and not self.metodo_da_testare:
            raise ValueError("Method to test not specified")
        
        if not self.java_projects_dir:
            raise ValueError("'java_projects_dir' must be specified in the YAML configuration")
    
    def get_target_class_path(self) -> str:
        """Returns the full path of the target class"""
        return os.path.join(self.base_dir, self.classe_da_testare)
    
    def get_nome_classe_solo(self) -> str:
        """Returns only the class name (without path)"""
        target_path = self.get_target_class_path()
        return os.path.basename(target_path).replace('.java', '')
