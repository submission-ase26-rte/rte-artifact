from typing import Dict, List, Optional

_token_info = {
    "test_generation": {"input_tokens": None, "output_tokens": None},
    "method_generation": [],  # Lista per supportare multiple rigenerazioni (una per ogni refinement)
    "test_repair_initial": [],  # Lista di repair per i test iniziali
    "test_repair_after_refinement": [],  # Lista di repair dopo ogni refinement
    "test_refinement": [],  # Lista di {input_tokens, output_tokens, iteration} per ogni iterazione
}


def reset_token_info():
    """Resetta tutte le informazioni sui token."""
    global _token_info
    _token_info = {
        "test_generation": {"input_tokens": None, "output_tokens": None},
        "method_generation": [],
        "test_repair_initial": [],
        "test_repair_after_refinement": [],
        "test_refinement": [],
    }


def save_test_generation_tokens(input_tokens: int, output_tokens: int):
    """Salva le informazioni sui token per la generation test iniziale."""
    global _token_info
    _token_info["test_generation"]["input_tokens"] = input_tokens
    _token_info["test_generation"]["output_tokens"] = output_tokens


def save_method_generation_tokens(input_tokens: int, output_tokens: int, refinement_iteration: int = None):
    """
    Salva le informazioni sui token per la generation/rigenerazione metodo.
    
    Args:
        input_tokens: Token di input usati
        output_tokens: Token di output generati
        refinement_iteration: Numero dell'iterazione di refinement (None = generation iniziale, 0+ = dopo refinement)
    """
    global _token_info
    method_entry = {
        "input_tokens": input_tokens,
        "output_tokens": output_tokens,
    }
    if refinement_iteration is not None:
        method_entry["refinement_iteration"] = refinement_iteration
    else:
        method_entry["refinement_iteration"] = "initial"
    _token_info["method_generation"].append(method_entry)


def save_test_repair_tokens(input_tokens: int, output_tokens: int, iteration: int = None, after_refinement: int = None):
    """
    Salva le informazioni sui token per un'iterazione di repair.
    
    Args:
        input_tokens: Token di input usati
        output_tokens: Token di output generati
        iteration: Numero dell'iterazione di repair (1, 2, ...)
        after_refinement: Numero dell'iterazione di refinement dopo cui avviene il repair (None = repair iniziale)
    """
    global _token_info
    repair_entry = {
        "input_tokens": input_tokens,
        "output_tokens": output_tokens,
    }
    if iteration is not None:
        repair_entry["iteration"] = iteration
    
    if after_refinement is not None:
        repair_entry["after_refinement"] = after_refinement
        _token_info["test_repair_after_refinement"].append(repair_entry)
    else:
        _token_info["test_repair_initial"].append(repair_entry)


def save_test_refinement_tokens(input_tokens: int, output_tokens: int, iteration: int = None):
    """Salva le informazioni sui token per un'iterazione di refinement."""
    global _token_info
    refinement_entry = {
        "input_tokens": input_tokens,
        "output_tokens": output_tokens,
    }
    if iteration is not None:
        refinement_entry["iteration"] = iteration
    _token_info["test_refinement"].append(refinement_entry)


def _sum_tokens(entries: List[Dict]) -> Dict:
    """Somma i token da una lista di entry."""
    total_input = 0
    total_output = 0
    for entry in entries:
        if entry.get("input_tokens"):
            total_input += entry["input_tokens"]
        if entry.get("output_tokens"):
            total_output += entry["output_tokens"]
    return {"input_tokens": total_input, "output_tokens": total_output}


def get_token_info_for_report() -> Optional[Dict]:
    """
    Restituisce le informazioni sui token formattate per il report JSON.
    Include breakdown per categoria e totali.
    Restituisce None se nessuna informazione è disponibile.
    
    Struttura output:
    {
        "test_generation": { "input_tokens": X, "output_tokens": Y },
        "method_generation": { "calls": N, "details": [...], "total_input_tokens": X, "total_output_tokens": Y },
        "test_repair_initial": { "calls": N, "details": [...], "total_input_tokens": X, "total_output_tokens": Y },
        "test_refinement": { "calls": N, "details": [...], "total_input_tokens": X, "total_output_tokens": Y },
        "test_repair_after_refinement": { "calls": N, "details": [...], "total_input_tokens": X, "total_output_tokens": Y },
        "totals": { "total_input_tokens": X, "total_output_tokens": Y, "total_tokens": Z }
    }
    """
    has_data = False
    result = {}
    
    total_input = 0
    total_output = 0
    
    # Test Generation (singola chiamata)
    test_gen = _token_info.get("test_generation", {})
    if test_gen.get("input_tokens") is not None or test_gen.get("output_tokens") is not None:
        has_data = True
        result["test_generation"] = {
            "input_tokens": test_gen.get("input_tokens") or 0,
            "output_tokens": test_gen.get("output_tokens") or 0,
        }
        total_input += test_gen.get("input_tokens") or 0
        total_output += test_gen.get("output_tokens") or 0
    
    # Method Generation (può avere multiple chiamate: iniziale + una per ogni refinement)
    method_gen_list = _token_info.get("method_generation", [])
    if method_gen_list:
        has_data = True
        method_totals = _sum_tokens(method_gen_list)
        result["method_generation"] = {
            "calls": len(method_gen_list),
            "details": method_gen_list,
            "total_input_tokens": method_totals["input_tokens"],
            "total_output_tokens": method_totals["output_tokens"],
        }
        total_input += method_totals["input_tokens"]
        total_output += method_totals["output_tokens"]
    
    # Test Repair Initial (repair per i test iniziali, prima del refinement loop)
    test_repair_initial_list = _token_info.get("test_repair_initial", [])
    if test_repair_initial_list:
        has_data = True
        repair_initial_totals = _sum_tokens(test_repair_initial_list)
        result["test_repair_initial"] = {
            "calls": len(test_repair_initial_list),
            "details": test_repair_initial_list,
            "total_input_tokens": repair_initial_totals["input_tokens"],
            "total_output_tokens": repair_initial_totals["output_tokens"],
        }
        total_input += repair_initial_totals["input_tokens"]
        total_output += repair_initial_totals["output_tokens"]
    
    # Test Refinement (può avere multiple iterazioni)
    test_refinement_list = _token_info.get("test_refinement", [])
    if test_refinement_list:
        has_data = True
        refinement_totals = _sum_tokens(test_refinement_list)
        result["test_refinement"] = {
            "calls": len(test_refinement_list),
            "details": test_refinement_list,
            "total_input_tokens": refinement_totals["input_tokens"],
            "total_output_tokens": refinement_totals["output_tokens"],
        }
        total_input += refinement_totals["input_tokens"]
        total_output += refinement_totals["output_tokens"]
    
    # Test Repair After Refinement (repair per i test dopo ogni refinement)
    test_repair_after_list = _token_info.get("test_repair_after_refinement", [])
    if test_repair_after_list:
        has_data = True
        repair_after_totals = _sum_tokens(test_repair_after_list)
        result["test_repair_after_refinement"] = {
            "calls": len(test_repair_after_list),
            "details": test_repair_after_list,
            "total_input_tokens": repair_after_totals["input_tokens"],
            "total_output_tokens": repair_after_totals["output_tokens"],
        }
        total_input += repair_after_totals["input_tokens"]
        total_output += repair_after_totals["output_tokens"]
    
    # Totali
    if has_data:
        result["totals"] = {
            "total_input_tokens": total_input,
            "total_output_tokens": total_output,
            "total_tokens": total_input + total_output,
        }
    
    return result if has_data else None


def capture_llm_tokens(operation_type: str = None, phase: str = None, iteration: int = None, 
                       after_refinement: int = None, refinement_iteration: int = None):
    """
    Cattura i token usati dall'ultima chiamata LLM (Ollama Cloud, Ollama Colab o Groq).
    
    Supporta due stili di chiamata per retrocompatibilità:
    1. Stile vecchio: phase="test_generation" o "method_generation", refinement_iteration
    2. Stile nuovo: operation_type="repair", "refinement", "test_generation", "method_generation"
    
    Args:
        operation_type: Tipo di operazione ("repair", "refinement", "test_generation", "method_generation")
        phase: Fase (deprecato, usa operation_type) - "test_generation" o "method_generation"
        iteration: Numero dell'iterazione (per repair/refinement)
        after_refinement: Numero dell'iterazione di refinement dopo cui avviene il repair
        refinement_iteration: Numero dell'iterazione di refinement (per method_generation)
    """
    input_tokens = None
    output_tokens = None
    
    # Normalizza operation_type da phase per retrocompatibilità
    if phase and not operation_type:
        operation_type = phase
    
    # Prova Ollama Cloud
    try:
        from providers.ollama_cloud_provider import get_last_token_usage as get_ollama_cloud_tokens
        token_usage = get_ollama_cloud_tokens()
        if token_usage.get("prompt_eval_count") is not None:
            input_tokens = token_usage.get("prompt_eval_count")
            output_tokens = token_usage.get("eval_count")
    except ImportError:
        pass
    except Exception:
        pass
    
    # Prova Ollama Colab se non ha dati
    if input_tokens is None:
        try:
            from providers.ollama_colab_provider import get_last_token_usage as get_ollama_colab_tokens
            token_usage = get_ollama_colab_tokens()
            if token_usage.get("prompt_eval_count") is not None:
                input_tokens = token_usage.get("prompt_eval_count")
                output_tokens = token_usage.get("eval_count")
        except ImportError:
            pass
        except Exception:
            pass
    
    # Prova Groq se Ollama non ha dati
    if input_tokens is None:
        try:
            from providers.groq_provider import get_last_token_usage as get_groq_tokens
            token_usage = get_groq_tokens()
            if token_usage.get("prompt_tokens") is not None:
                input_tokens = token_usage.get("prompt_tokens")
                output_tokens = token_usage.get("completion_tokens")
        except ImportError:
            pass
        except Exception:
            pass
    
    # Salva i token se disponibili
    if input_tokens is not None or output_tokens is not None:
        if operation_type == "repair":
            save_test_repair_tokens(input_tokens, output_tokens, iteration, after_refinement)
        elif operation_type == "refinement":
            save_test_refinement_tokens(input_tokens, output_tokens, iteration)
        elif operation_type == "test_generation":
            save_test_generation_tokens(input_tokens, output_tokens)
        elif operation_type == "method_generation":
            save_method_generation_tokens(input_tokens, output_tokens, refinement_iteration)