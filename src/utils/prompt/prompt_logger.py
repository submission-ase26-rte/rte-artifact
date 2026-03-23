import os
from config import RESULTS_DIR

from datetime import datetime


def _normalizza_versione(versione: str) -> str:
    if not versione or versione.strip() == "":
        return ""
    if versione.startswith("v"):
        return versione
    return f"v{versione}"


def salva_prompt_log(
    prompt: str,
    tipo: str,
    class_name: str,
    versione: str,
    nome_esperimento: str = None,
    retry_count: int = None,
    metodo_da_testare: str = None,
    model_name: str = None,
):
    """
    Salva il prompt in un file di log organizzato per esperimento e modello LLM.
    """
    # Sanitizza model_name se presente
    if model_name:
        model_name = model_name.replace(":", "_").replace("/", "_").replace("\\", "_")

    # Costruisci il percorso base
    if nome_esperimento:
        if model_name:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "prompts", nome_esperimento, model_name)
        else:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "prompts", nome_esperimento)

    else:
        if model_name:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "prompts", model_name)
        else:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "prompts")

    os.makedirs(logs_dir, exist_ok=True)

    versione_norm = _normalizza_versione(versione)

    # Costruisci il nome del file includendo il metodo se disponibile
    if metodo_da_testare:
        if versione_norm:
            base_name = f"{tipo}_{class_name}_{metodo_da_testare}_{versione_norm}"
        else:
            base_name = f"{tipo}_{class_name}_{metodo_da_testare}"
    else:
        if versione_norm:
            base_name = f"{tipo}_{class_name}_{versione_norm}"
        else:
            base_name = f"{tipo}_{class_name}"

    # Per refinement, repair e method_generation, retry_count parte da 0 ma i file devono partire da r1
    # Quindi usiamo retry_count + 1
    if (
        tipo in ["Test_refinement", "Test_repair", "Method_generation"]
        and retry_count is not None
    ):
        # retry_count = 0 → r1, retry_count = 1 → r2, ecc.
        retry_num = retry_count + 1
        # Per Test_repair, aggiungi timestamp al nome del file
        if tipo == "Test_repair":
            timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
            filename = f"{base_name}_r{retry_num}_{timestamp}.txt"
        else:
            filename = f"{base_name}_r{retry_num}.txt"
    elif retry_count is not None and retry_count > 0:
        filename = f"{base_name}_r{retry_count}.txt"
    else:
        filename = f"{base_name}.txt"

    filepath = os.path.join(logs_dir, filename)

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(prompt)


def salva_risposta_llm(
    risposta: str,
    tipo: str,
    class_name: str,
    versione: str,
    retry_count: int = None,
    nome_esperimento: str = None,
    metodo_da_testare: str = None,
    model_name: str = None,
):
    """
    Salva la risposta dell'LLM in un file di log organizzato per esperimento e modello LLM.
    """
    # Sanitizza model_name se presente
    if model_name:
        model_name = model_name.replace(":", "_").replace("/", "_").replace("\\", "_")

    # Costruisci il percorso base
    if nome_esperimento:
        if model_name:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "responses", nome_esperimento, model_name)
        else:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "responses", nome_esperimento)

    else:
        if model_name:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "responses", model_name)
        else:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "responses")

    os.makedirs(logs_dir, exist_ok=True)

    versione_norm = _normalizza_versione(versione)

    # Costruisci il nome del file includendo il metodo se disponibile
    if metodo_da_testare:
        if versione_norm:
            base_name = f"{tipo}_{class_name}_{metodo_da_testare}_{versione_norm}"
        else:
            base_name = f"{tipo}_{class_name}_{metodo_da_testare}"
    else:
        if versione_norm:
            base_name = f"{tipo}_{class_name}_{versione_norm}"
        else:
            base_name = f"{tipo}_{class_name}"

    # Per refinement, repair e method_generation, retry_count parte da 0 ma i file devono partire da r1
    # Quindi usiamo retry_count + 1
    if (
        tipo in ["Test_refinement", "Test_repair", "Method_generation"]
        and retry_count is not None
    ):
        # retry_count = 0 → r1, retry_count = 1 → r2, ecc.
        retry_num = retry_count + 1
        # Per Test_repair, aggiungi timestamp al nome del file
        if tipo == "Test_repair":
            timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
            filename = f"{base_name}_r{retry_num}_{timestamp}.txt"
        else:
            filename = f"{base_name}_r{retry_num}.txt"
    elif retry_count is not None and retry_count > 0:
        filename = f"{base_name}_r{retry_count}.txt"
    else:
        filename = f"{base_name}.txt"

    filepath = os.path.join(logs_dir, filename)

    # Gestisci risposta None (può succedere se LLM fallisce o timeout)
    if risposta is None:
        risposta = "[ERROR: LLM returned None response]"

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(risposta)

    return filepath


def salva_test_suite(
    test_suite: str,
    class_name: str,
    versione: str,
    retry_count: int,
    nome_esperimento: str = None,
    metodo_da_testare: str = None,
    test_output: str = None,  # Parametro mantenuto per retrocompatibilità ma ignorato
    model_name: str = None,
):
    """
    Salva la test suite completa in un file di log organizzato per esperimento e modello LLM.
    NOTA: test_output non viene più salvato per ridurre i log.
    """
    # Sanitizza model_name se presente
    if model_name:
        model_name = model_name.replace(":", "_").replace("/", "_").replace("\\", "_")

    # Costruisci il percorso base
    if nome_esperimento:
        if model_name:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "test_suite", nome_esperimento, model_name)
        else:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "test_suite", nome_esperimento)

    else:
        if model_name:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "test_suite", model_name)
        else:
            logs_dir = os.path.join(RESULTS_DIR, "logs", "test_suite")

    os.makedirs(logs_dir, exist_ok=True)

    versione_norm = _normalizza_versione(versione)

    # Costruisci il nome del file includendo il metodo se disponibile
    if metodo_da_testare:
        if versione_norm:
            base_name = f"Test_suite_{class_name}_{metodo_da_testare}_{versione_norm}"
        else:
            base_name = f"Test_suite_{class_name}_{metodo_da_testare}"
    else:
        if versione_norm:
            base_name = f"Test_suite_{class_name}_{versione_norm}"
        else:
            base_name = f"Test_suite_{class_name}"

    if retry_count is not None:
        # retry_count = 0 → r1, retry_count = 1 → r2, ecc.
        refinement_num = retry_count + 1
        filename = f"{base_name}_r{refinement_num}.txt"
    else:
        filename = f"{base_name}.txt"

    filepath = os.path.join(logs_dir, filename)

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(test_suite)

    return filepath
