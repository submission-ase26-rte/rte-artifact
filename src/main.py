import os
import sys
import argparse
from dotenv import load_dotenv

if sys.platform == 'win32':
    # Stdout
    if hasattr(sys.stdout, 'reconfigure'):
        sys.stdout.reconfigure(encoding='utf-8', errors='replace')
    elif hasattr(sys.stdout, 'buffer'):
        import io
        sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')
    
    # Stderr
    if hasattr(sys.stderr, 'reconfigure'):
        sys.stderr.reconfigure(encoding='utf-8', errors='replace')
    elif hasattr(sys.stderr, 'buffer'):
        import io
        sys.stderr = io.TextIOWrapper(sys.stderr.buffer, encoding='utf-8', errors='replace')
from utils.io.yaml_loader import carica_config
from utils.io.file_utils import leggi_file, get_test_dir, get_main_dir
from utils.providers.provider_utils import get_provider_function
from core.config.experiment_config import ExperimentConfig
from core.generation.generation_with_dependencies import (
    genera_test_per_metodo_con_dipendenze,
    rigenera_metodo_dai_test_con_dipendenze,
)
from core.generation.generation import genera_test_per_metodo, rigenera_metodo_dai_test
from core.evaluation.evaluation import (
    confronta_metodi,
    salva_metriche_esperimento,
    estrai_metodo_singolo,
    aggiorna_metriche_esperimento,
    salva_metriche_solo_originale,
    gestisci_errori_sintassi,
    _remove_refinement_prompts_from_retry_history,
)
from core.retry.smart_retry import esegui_smart_retry_loop
from utils.reporting.report_generator import genera_report_html
from utils.test.test_executor import esegui_test_originale
from utils.test.test_utils import (
    verifica_errori_sintassi,
    verifica_nessun_test_passato,
    stampa_risultati_test,
    prepara_test_results_info,
)
from utils.tracking.logger import setup_console_logging
import json


def esegui_fase1_dipendenze(
    config: ExperimentConfig, provider_func, model_name: str
) -> tuple:
    print(f"\n=== Phase 1 Execution: Test generation ===")
    print(f"Test generation for single method: {config.metodo_da_testare}")

    target_class_path = config.get_target_class_path()
    test_path, selezione_info, prompt_test_completo, context = (
        genera_test_per_metodo_con_dipendenze(
            target_class_path=target_class_path,
            project_root=config.java_projects_dir,
            nome_metodo=config.metodo_da_testare,
            provider_func=provider_func,
            model_name=model_name,
            prompt_test_metodo=config.prompt_test_metodo,
            output_dir=config.output_dir,
            versione=config.versione_esperimento,
            max_dependencies=config.max_dependencies,
            metodo_selezione=config.metodo_selezione,
            top_k_selezione=config.top_k_selezione,
            nome_esperimento=config.nome_esperimento,
            metodo_signature=config.metodo_signature,
            usa_istruzioni_speciali=config.usa_istruzioni_speciali,
        )
    )

    test_generated_content = ""
    try:
        with open(test_path, "r", encoding="utf-8") as f:
            test_generated_content = f.read()
    except Exception:
        pass

    if selezione_info and selezione_info.get("attivo"):
        classi_selezionate = selezione_info.get("classi_selezionate", [])
        nome_classe_solo = config.get_nome_classe_solo()
        dipendenze_esterne = [
            c for c in classi_selezionate if c.get("classe") != nome_classe_solo
        ]
        print(
            f"Dependency selection active -> method={selezione_info.get('metodo')} top_k={selezione_info.get('top_k')} selected={len(dipendenze_esterne)}"
        )

    return test_path, selezione_info, prompt_test_completo, test_generated_content, context


def esegui_fase1_senza_dipendenze(
    config: ExperimentConfig, provider_func, model_name: str
) -> tuple:
    print(f"\n=== Phase 1 Execution: Test generation ===")
    print(f"Test generation for single method: {config.metodo_da_testare}")

    target_class_path = config.get_target_class_path()
    classe_content = leggi_file(target_class_path)
    test_dir = os.path.join(config.output_dir, "test_suite", config.nome_esperimento)
    os.makedirs(test_dir, exist_ok=True)

    test_path, prompt_test_completo = genera_test_per_metodo(
        codice_classe=classe_content,
        nome_metodo=config.metodo_da_testare,
        provider_func=provider_func,
        model_name=model_name,
        prompt_test_metodo=config.prompt_test_metodo,
        output_dir=test_dir,
        nome_classe=config.get_nome_classe_solo(),
        versione=config.versione_esperimento,
        nome_esperimento=config.nome_esperimento,
    )

    test_generated_content = ""
    try:
        with open(test_path, "r", encoding="utf-8") as f:
            test_generated_content = f.read()
    except Exception:
        pass

    return test_path, None, prompt_test_completo, test_generated_content, None


def esegui_fase2_dipendenze(
    config: ExperimentConfig,
    provider_func,
    model_name: str,
    test_path: str,
    test_results_originale,
    metodo_precedente_path: str = None,
    retry_count: int = None,
    metodo_originale_backup: str = None,  # Backup del metodo originale (per non rifarlo ogni volta)
    precomputed_context: str = None, # Context precalcolato
    metodo_precedente_codice: str = None,
) -> tuple:
    print(f"\n=== Phase 2 Execution: Method regeneration from tests ===")

    target_class_path = config.get_target_class_path()
    nome_classe_solo = config.get_nome_classe_solo()


    offusca_nome = not config.esegui_prima_fase

    metodo_path, test_info, prompt_rigenera_completo = rigenera_metodo_dai_test_con_dipendenze(
        test_file_path=test_path,
        project_root=config.java_projects_dir,
        nome_metodo=config.metodo_da_testare,
        provider_func=provider_func,
        model_name=model_name,
        prompt_rigenera_metodo=config.prompt_rigenera_metodo,
        output_dir=config.output_dir,
        versione=config.versione_esperimento,
        max_dependencies=config.max_dependencies,
        metodo_precedente_path=metodo_precedente_path,
        target_class_path=target_class_path,
        test_results_originale=test_results_originale,
        nome_esperimento=config.nome_esperimento,
        offusca_nome_metodo=offusca_nome,
        usa_dipendenze=config.usa_dipendenze_seconda_fase,
        retry_count=retry_count,
        metodo_signature=config.metodo_signature,  # Per distinguere overload
        precomputed_context=precomputed_context,
        metodo_precedente_codice=metodo_precedente_codice,
    )
    
    # Estrai il codice del metodo rigenerato e il backup
    metodo_rigenerato_codice = test_info.get("metodo_rigenerato_codice")
    backup = test_info.get("metodo_originale_backup") if test_info else None
    
    if metodo_originale_backup is None and backup:
        metodo_originale_backup = backup

    # Ritorna il CODICE invece del PATH come primo argomento
    return metodo_rigenerato_codice, prompt_rigenera_completo, metodo_originale_backup


def esegui_fase2_senza_dipendenze(
    config: ExperimentConfig,
    provider_func,
    model_name: str,
    test_path: str,
    test_results_originale,
    metodo_precedente_path: str = None,
    retry_count: int = None,
    metodo_originale_backup: str = None,  # Backup del metodo originale (per non rifarlo ogni volta)
    precomputed_context: str = None, # Ignorato qui
    metodo_precedente_codice: str = None,
) -> tuple:
    print(f"\n=== Phase 2 Execution: Method regeneration from tests ===")

    target_class_path = config.get_target_class_path()
    nome_classe_solo = config.get_nome_classe_solo()

    if (
        metodo_precedente_path
        and os.path.exists(metodo_precedente_path)
        and test_results_originale is None
    ):
        print(f"\n=== Phase 2 Execution: Test execution on previous method ===")
        # Ora il metodo rigenerato è nella classe originale, usa esegui_test_originale
        test_results_originale = esegui_test_originale(
            test_path,
            config.java_projects_dir,
            target_class_path,
            nome_classe_solo,
            config.metodo_da_testare,
            config.nome_esperimento,
        )
        stampa_risultati_test(test_results_originale, "metodo precedente")

    test_content = leggi_file(test_path)
    classe_content_originale = leggi_file(target_class_path)
    main_dir = get_main_dir(test_path, config.java_projects_dir)

    if not metodo_precedente_path and config.versione_esperimento:
        try:
            prev_version = str(int(config.versione_esperimento) - 1)
            possible_prev_path = os.path.join(
                main_dir,
                f"{nome_classe_solo}_{config.metodo_da_testare}Generated{prev_version}.java",
            )
            if os.path.exists(possible_prev_path):
                metodo_precedente_path = possible_prev_path
        except ValueError:
            pass

    offusca_nome = not config.esegui_prima_fase

    metodo_path, prompt_rigenera_completo, backup = rigenera_metodo_dai_test(
        codice_test=test_content,
        nome_metodo=config.metodo_da_testare,
        provider_func=provider_func,
        model_name=model_name,
        prompt_rigenera_metodo=config.prompt_rigenera_metodo,
        output_dir=main_dir,
        nome_classe=nome_classe_solo,
        versione=config.versione_esperimento,
        codice_classe_originale=classe_content_originale,
        metodo_precedente_path=metodo_precedente_path,
        test_results_originale=test_results_originale,
        retry_count=retry_count,
        nome_esperimento=config.nome_esperimento,
        offusca_nome_metodo=offusca_nome,
        target_class_path=config.classe_da_testare, 
        metodo_precedente_codice=metodo_precedente_codice,
    )
    
    # Usa il backup solo se non ne abbiamo già uno
    if metodo_originale_backup is None and backup:
        metodo_originale_backup = backup

    return metodo_path, prompt_rigenera_completo, metodo_originale_backup


def verifica_risultati_finali(
    config: ExperimentConfig,
    test_results_originale,
    test_path: str,
    target_class_path: str,
    selezione_info,
    prompt_test_metodo: str,
    test_generated_content: str,
    retry_history: list,
    soglia_similarita_dinamica: float = None,
) -> bool:
    line_coverage_finale = test_results_originale.get("line_coverage", 0.0)
    has_compilation_errors_finale = test_results_originale.get(
        "has_compilation_errors", False
    )

    if has_compilation_errors_finale:
        print(f"\n ERROR: There are still compilation errors.")
        print(f"   The experiment will be interrupted and will not proceed to phase 2.")
        salva_metriche_solo_originale(
            test_results_originale,
            target_class_path,
            config.output_dir,
            config.get_nome_classe_solo(),
            config.metodo_da_testare,
            config.provider_nome,
            config.versione_esperimento,
            soglia_similarita_dinamica if soglia_similarita_dinamica is not None else config.soglia_similarita,
            prompt_test_metodo,
            selezione_info,
            test_generated_content,
            config.cb_use_stopwords,
            config.nome_esperimento,
            descrizione_esperimento=config.descrizione,
            model_name=config.model_name,
            prompt_test_completo=prompt_test_metodo,
            retry_history=retry_history,
            soglia_coverage=config.soglia_coverage,
            usa_soglia_dinamica=config.usa_soglia_dinamica,
        )
        return False

    if config.soglia_coverage > 0.0 and line_coverage_finale < config.soglia_coverage:
        print(f"\n ERROR: The line coverage ({line_coverage_finale:.2f}%)")
        print(
            f"   is still below the required threshold ({config.soglia_coverage:.2f}%)."
        )
        print(f"   The experiment will be interrupted and will not proceed to phase 2.")
        salva_metriche_solo_originale(
            test_results_originale,
            target_class_path,
            config.output_dir,
            config.get_nome_classe_solo(),
            config.metodo_da_testare,
            config.provider_nome,
            config.versione_esperimento,
            soglia_similarita_dinamica if soglia_similarita_dinamica is not None else config.soglia_similarita,
            prompt_test_metodo,
            selezione_info,
            test_generated_content,
            config.cb_use_stopwords,
            config.nome_esperimento,
            descrizione_esperimento=config.descrizione,
            model_name=config.model_name,
            prompt_test_completo=prompt_test_metodo,
            retry_history=retry_history,
            soglia_coverage=config.soglia_coverage,
            usa_soglia_dinamica=config.usa_soglia_dinamica,
        )
        return False

    if not test_results_originale["success"]:
        if verifica_errori_sintassi(test_results_originale):
            return gestisci_errori_sintassi(
                test_results_originale,
                target_class_path,
                config.output_dir,
                config.get_nome_classe_solo(),
                config.metodo_da_testare,
                config.provider_nome,
                config.versione_esperimento,
                config.versione_esperimento,
                soglia_similarita_dinamica if soglia_similarita_dinamica is not None else config.soglia_similarita,
                prompt_test_metodo,
                test_path,
                selezione_info,
                config.nome_esperimento,
                usa_soglia_dinamica=config.usa_soglia_dinamica,
            )
        elif verifica_nessun_test_passato(test_results_originale):
            # Continua comunque con la fase 2, anche se nessun test passa
            print(f"\nWARNING: No tests passed on the original method!")
        else:
            print(
                f"\nWARNING: Some tests did not pass on the original method!"
            )
            print(f"   Continuing with method regeneration...")

    return True


def genera_report_fallimento(
    config: ExperimentConfig,
    test_path: str,
    target_class_path: str,
    selezione_info,
    retry_history: list,
    motivo: str = "Errore sconosciuto"
):
    """
    Genera un report JSON anche quando l'esperimento fallisce.
    """
    from datetime import datetime
    import json
    import os
    
    nome_classe_solo = config.get_nome_classe_solo()
    
    # Costruisci metriche di fallimento
    metriche = {
        "esperimento": {
            "nome": config.nome_esperimento,
            "descrizione": config.descrizione,
            "timestamp": datetime.now().isoformat(),
            "successo": False,
            "motivo_fallimento": motivo,
        },
        "configurazione": {
            "classe": nome_classe_solo,
            "metodo": config.metodo_da_testare,
            "provider": config.provider_nome,
            "modello": config.model_name,
            "max_retries_repair": config.max_retries_repair,
            "max_retries_refinement": config.max_retries_refinement,
        },
        "test_results_originale": None,
        "test_results_rigenerato": None,
        "similarita": None,
        "retry_history": retry_history,
        "selezione_dipendenze": selezione_info if selezione_info else {},
    }
    
    # Crea directory output se non esiste (usa lo stesso schema di salva_metriche_finali)
    sanitized_model_name = config.model_name.replace(":", "_").replace("/", "_").replace("\\", "_") if config.model_name else None
    
    if config.nome_esperimento:
        if sanitized_model_name:
            output_dir = os.path.join(
                config.output_dir, "metrics", config.nome_esperimento, sanitized_model_name
            )
        else:
            output_dir = os.path.join(
                config.output_dir, "metrics", config.nome_esperimento
            )
    else:
        if sanitized_model_name:
            output_dir = os.path.join(config.output_dir, "metrics", sanitized_model_name)
        else:
            output_dir = os.path.join(config.output_dir, "metrics")
    os.makedirs(output_dir, exist_ok=True)
    
    # Salva JSON
    json_path = os.path.join(
        output_dir, f"Esperimento_{nome_classe_solo}_{config.metodo_da_testare}_{config.provider_nome}_FAILED.json"
    )
    with open(json_path, 'w', encoding='utf-8') as f:
        json.dump(metriche, f, indent=2, ensure_ascii=False, default=str)
    
    print(f" Failure report saved: {json_path}")
    
    # Genera anche report HTML di fallimento
    try:
        html_path = json_path.replace('.json', '_report.html')
        genera_report_html(json_path, html_path)
        print(f" Failure HTML report: {html_path}")
    except Exception as e:
        print(f"WARNING:  Failed to generate HTML report: {e}")


def salva_metriche_finali(
    config: ExperimentConfig,
    result: dict,
    target_class_path: str,
    selezione_info,
    prompt_test_completo: str,
    prompt_rigenera_completo: str,
    test_generated_content: str,
    retry_history: list,
    fase2_gia_eseguita: bool = False,
    test_file_path: str = None,
    soglia_similarita_dinamica: float = None,
):
    if config.modalita_generazione != "singolo_metodo" or not config.metodo_da_testare:
        return

    metodo_path = result["metodo_path"]
    
    # Controlla se metodo_path è un percorso file o il codice del metodo
    # Se è una stringa lunga che sembra codice Java, probabilmente è il codice stesso
    is_code_not_path = (
        isinstance(metodo_path, str) 
        and not os.path.exists(metodo_path) 
        and (len(metodo_path) > 100 or ("{" in metodo_path and "}" in metodo_path))
    )
    
    if not is_code_not_path and not os.path.exists(metodo_path):
        print("Regenerated file not found for evaluation")
        # Prova a usare il codice dalla classe target o dal result
        if result.get("metodo_rigenerato_codice"):
            print("Using regenerated method code from result")
        else:
            return

    nome_classe_solo = config.get_nome_classe_solo()

    # Importa funzioni per estrazione metodo con JavaDoc
    from core.evaluation.evaluation import estrai_metodo_con_javadoc
    
    # Leggi la classe originale per estrarre il metodo CON JavaDoc (per il report)
    with open(target_class_path, "r", encoding="utf-8") as f:
        classe_originale = f.read()
    metodo_originale_con_javadoc = estrai_metodo_con_javadoc(classe_originale, config.metodo_da_testare, signature=config.metodo_signature)
    
    # Usa il backup se disponibile (senza JavaDoc, per le metriche)
    metodo_originale_backup = result.get("metodo_originale_backup")
    if metodo_originale_backup:
        metodo_originale = metodo_originale_backup
    else:
        metodo_originale = estrai_metodo_singolo(classe_originale, config.metodo_da_testare, signature=config.metodo_signature)

    # Usa il metodo rigenerato salvato prima del ripristino
    metodo_rigenerato_codice = result.get("metodo_rigenerato_codice")
    if metodo_rigenerato_codice:
        metodo_rigenerato = metodo_rigenerato_codice
    else:
        # Fallback: la classe ora contiene l'originale (ripristinato), quindi il confronto è errato
        # ma proviamo comunque a leggere dalla retry_history
        metodo_rigenerato = ""
        if retry_history:
            for entry in reversed(retry_history):
                if entry.get("regenerated_method"):
                    metodo_rigenerato = entry.get("regenerated_method")
                    break
    
    # Salva anche la versione con JavaDoc per il report
    result["metodo_originale_con_javadoc"] = metodo_originale_con_javadoc

    sanitized_model_name = config.model_name.replace(":", "_").replace("/", "_").replace("\\", "_") if config.model_name else None

    if config.nome_esperimento:
        if sanitized_model_name:
            metrics_dir = os.path.join(
                config.output_dir, "metrics", config.nome_esperimento, sanitized_model_name
            )
        else:
            metrics_dir = os.path.join(
                config.output_dir, "metrics", config.nome_esperimento
            )
    else:
        if sanitized_model_name:
            metrics_dir = os.path.join(config.output_dir, "metrics", sanitized_model_name)
        else:
            metrics_dir = os.path.join(config.output_dir, "metrics")
    os.makedirs(metrics_dir, exist_ok=True)
    metrics_path = os.path.join(
        metrics_dir,
        f"Esperimento_{nome_classe_solo}_{config.metodo_da_testare}_{config.provider_nome}.json",
    )

    fasi_eseguite = {
        "first_phase": config.esegui_prima_fase,
        "second_phase": fase2_gia_eseguita or config.esegui_seconda_fase,
    }

    test_results_info = prepara_test_results_info(result)

    if not config.esegui_prima_fase and config.esegui_seconda_fase:
        aggiorna_metriche_esperimento(
            target_class_path,
            metodo_path,
            metodo_originale,
            metodo_rigenerato,
            metrics_path,
            config.metodo_da_testare,
            soglia_similarita_dinamica if soglia_similarita_dinamica is not None else config.soglia_similarita,
            config.versione_esperimento,
            fasi_eseguite,
            config.prompt_test_metodo,
            config.prompt_rigenera_metodo,
            None,
            test_generated_content,
            config.cb_use_stopwords,
            test_results_info,
            prompt_test_completo=prompt_test_completo,
            prompt_rigenera_completo=prompt_rigenera_completo,
            retry_history=retry_history,
            model_name=config.model_name,
            test_file_path=test_file_path,
            metodo_originale_con_javadoc=metodo_originale_con_javadoc,  # Con commenti per report
            usa_soglia_dinamica=config.usa_soglia_dinamica,
        )
    else:
        salva_metriche_esperimento(
            target_class_path,
            metodo_path,
            metodo_originale,
            metodo_rigenerato,
            metrics_path,
            config.metodo_da_testare,
            soglia_similarita_dinamica if soglia_similarita_dinamica is not None else config.soglia_similarita,
            fasi_eseguite,
            config.versione_esperimento,
            config.prompt_test_metodo,
            config.prompt_rigenera_metodo,
            selezione_info,
            test_generated_content,
            config.cb_use_stopwords,
            test_results_info,
            config.nome_esperimento,
            descrizione_esperimento=config.descrizione,
            provider_nome=config.provider_nome,
            model_name=config.model_name,
            prompt_test_completo=prompt_test_completo if prompt_test_completo else None,
            prompt_rigenera_completo=prompt_rigenera_completo
            if prompt_rigenera_completo
            else None,
            retry_history=retry_history if retry_history else None,
            soglia_coverage=config.soglia_coverage
            if config.soglia_coverage > 0
            else None,
            test_file_path=test_file_path,
            metodo_originale_con_javadoc=metodo_originale_con_javadoc,  # Con commenti per report
            usa_soglia_dinamica=config.usa_soglia_dinamica,
        )

    print(f"\n=== HTML REPORT GENERATION ===")
    try:
        report_html_path = genera_report_html(metrics_path)
        if report_html_path:
            print(f"HTML Report generated: {report_html_path}")
    except Exception as e:
        print(f"Error during HTML report generation: {e}")

    try:
        with open(metrics_path, "r", encoding="utf-8") as f:
            metrics_data = json.load(f)

        esperimenti = metrics_data.get("esperimenti", {})
        for _, esperimento in esperimenti.items():
            retry_history_data = esperimento.get("retry_history", [])
            if retry_history_data:
                esperimento["retry_history"] = (
                    _remove_refinement_prompts_from_retry_history(
                        retry_history_data, remove_methods=True
                    )
                )

        with open(metrics_path, "w", encoding="utf-8") as f:
            json.dump(metrics_data, f, indent=2, ensure_ascii=False)

    except Exception as e:
        print(f"WARNING:  Error during method removal from JSON: {e}")


def esegui_esperimento(esperimento_cfg_path: str):
    try:
        config = ExperimentConfig(esperimento_cfg_path)
    except ValueError as e:
        print(f" {e}")
        return False

    # Initialize console logging to file
    setup_console_logging(config.nome_esperimento)

    provider_func = get_provider_function(config.provider_nome)
    if provider_func is None:
        print(f" Provider {config.provider_nome} not supported")
        return False

    target_class_path = config.get_target_class_path()
    if not os.path.exists(target_class_path):
        print(f"Class not found: {target_class_path}")
        return False

    nome_classe_solo = config.get_nome_classe_solo()
    result = None
    test_results_originale = None
    test_path = None
    selezione_info = None
    test_generated_content = ""
    prompt_test_completo = None
    prompt_rigenera_completo = None
    retry_history = []
    fase2_gia_eseguita = False

    if config.esegui_prima_fase:
        if (
            config.modalita == "dipendenze"
            and config.modalita_generazione == "singolo_metodo"
            and config.metodo_da_testare
        ):
            test_path, selezione_info, prompt_test_completo, test_generated_content, context_completo = (
                esegui_fase1_dipendenze(config, provider_func, config.model_name)
            )

            soglia_similarita_dinamica = None
            if config.abilita_smart_retry:
                (
                    test_results_originale,
                    test_path,
                    retry_history,
                    metodo_path_smart,
                    test_results_rigenerato_smart,
                    soglia_similarita_dinamica,
                ) = esegui_smart_retry_loop(
                    test_path,
                    config,
                    provider_func,
                    config.model_name,
                    target_class_path,
                    nome_classe_solo,
                    config.metodo_da_testare,
                    config.java_projects_dir,
                    None,
                    initial_context=context_completo,
                )

                # Se nessun test compila, genera report di fallimento e interrompi
                if test_results_originale is None:
                    print(f"\n EXPERIMENT FAILED: No compilable tests.")
                    print(f"   Generating failure report...")
                    
                    # Genera report JSON anche in caso di fallimento
                    genera_report_fallimento(
                        config,
                        test_path,
                        target_class_path,
                        selezione_info,
                        retry_history if retry_history else [],
                        motivo="Nessun test compilabile dopo tutti i tentativi di repair"
                    )
                    
                    # Disabilita test suite non compilabile per permettere esperimenti successivi
                    if test_path and os.path.exists(test_path):
                        disabled_path = test_path + ".disabled"
                        try:
                            os.rename(test_path, disabled_path)
                            print(f"   WARNING:  Test suite disabled: {os.path.basename(disabled_path)}")
                        except Exception as e:
                            print(f"   WARNING:  Error disabling test suite: {e}")
                    
                    return False

                # Estrai metodo originale e rigenerato dalla retry_history
                metodo_originale_from_history = None
                metodo_rigenerato_from_history = None
                for entry in reversed(retry_history):
                    if entry.get("metodo_originale"):
                        metodo_originale_from_history = entry.get("metodo_originale")
                    if entry.get("regenerated_method"):
                        metodo_rigenerato_from_history = entry.get("regenerated_method")
                    if metodo_originale_from_history and metodo_rigenerato_from_history:
                        break
                
                # La fase 2 è già stata eseguita se:
                # 1. metodo_path_smart esiste E test_results_rigenerato_smart esiste, OPPURE
                # 2. retry_history contiene un metodo rigenerato (similarità raggiunta)
                ha_metodo_rigenerato_in_history = metodo_rigenerato_from_history is not None
                
                if config.esegui_seconda_fase and (metodo_path_smart or ha_metodo_rigenerato_in_history):
                    result = {
                        "test_path": test_path,
                        "metodo_path": metodo_path_smart if metodo_path_smart else target_class_path,
                        "metodo_precedente_path": None,
                        "test_results_originale": test_results_originale,
                        "test_results_rigenerato": test_results_rigenerato_smart,
                        "metodo_originale_backup": metodo_originale_from_history,
                        "metodo_rigenerato_codice": metodo_rigenerato_from_history,
                    }
                    fase2_gia_eseguita = True
                else:
                    fase2_gia_eseguita = False
            else:
                test_results_originale = esegui_test_originale(
                    test_path,
                    config.java_projects_dir,
                    target_class_path,
                    nome_classe_solo,
                    config.metodo_da_testare,
                    config.nome_esperimento,
                )
                stampa_risultati_test(test_results_originale, "metodo originale")
                retry_history = []
                fase2_gia_eseguita = False

            if not verifica_risultati_finali(
                config,
                test_results_originale,
                test_path,
                target_class_path,
                selezione_info,
                config.prompt_test_metodo,
                test_generated_content,
                retry_history,
                soglia_similarita_dinamica=soglia_similarita_dinamica,
            ):
                return False

        elif (
            config.modalita == "senza_dipendenze"
            and config.modalita_generazione == "singolo_metodo"
            and config.metodo_da_testare
        ):
            test_path, selezione_info, prompt_test_completo, test_generated_content, context_completo = (
                esegui_fase1_senza_dipendenze(config, provider_func, config.model_name)
            )

            soglia_similarita_dinamica = None
            if config.abilita_smart_retry:
                (
                    test_results_originale,
                    test_path,
                    retry_history,
                    metodo_path_smart,
                    test_results_rigenerato_smart,
                    soglia_similarita_dinamica,
                ) = esegui_smart_retry_loop(
                    test_path,
                    config,
                    provider_func,
                    config.model_name,
                    target_class_path,
                    nome_classe_solo,
                    config.metodo_da_testare,
                    config.java_projects_dir,
                    None,
                    initial_context=context_completo,
                )

                # Se nessun test compila, genera report di fallimento e interrompi
                if test_results_originale is None:
                    print(f"\n EXPERIMENT FAILED: No compilable tests.")
                    print(f"   Generating failure report...")
                    
                    genera_report_fallimento(
                        config,
                        test_path,
                        target_class_path,
                        selezione_info,
                        retry_history if retry_history else [],
                        motivo="Nessun test compilabile dopo tutti i tentativi di repair"
                    )
                    
                    # Disabilita test suite non compilabile per permettere esperimenti successivi
                    if test_path and os.path.exists(test_path):
                        disabled_path = test_path + ".disabled"
                        try:
                            os.rename(test_path, disabled_path)
                            print(f"   WARNING:  Test suite disabled: {os.path.basename(disabled_path)}")
                        except Exception as e:
                            print(f"   WARNING:  Error disabling test suite: {e}")
                    
                    return False

                # Estrai metodo originale e rigenerato dalla retry_history
                metodo_originale_from_history = None
                metodo_rigenerato_from_history = None
                for entry in reversed(retry_history):
                    if entry.get("metodo_originale"):
                        metodo_originale_from_history = entry.get("metodo_originale")
                    if entry.get("regenerated_method"):
                        metodo_rigenerato_from_history = entry.get("regenerated_method")
                    if metodo_originale_from_history and metodo_rigenerato_from_history:
                        break
                
                # La fase 2 è già stata eseguita se:
                # 1. metodo_path_smart esiste E test_results_rigenerato_smart esiste, OPPURE
                # 2. retry_history contiene un metodo rigenerato (similarità raggiunta)
                ha_metodo_rigenerato_in_history = metodo_rigenerato_from_history is not None
                
                if config.esegui_seconda_fase and (metodo_path_smart or ha_metodo_rigenerato_in_history):
                    result = {
                        "test_path": test_path,
                        "metodo_path": metodo_path_smart if metodo_path_smart else target_class_path,
                        "metodo_precedente_path": None,
                        "test_results_originale": test_results_originale,
                        "test_results_rigenerato": test_results_rigenerato_smart,
                        "metodo_originale_backup": metodo_originale_from_history,
                        "metodo_rigenerato_codice": metodo_rigenerato_from_history,
                    }
                    fase2_gia_eseguita = True
                else:
                    # La fase 2 È stata tentata (anche se fallita) - NON ritentare con fallback
                    # Questo evita chiamate LLM ridondanti quando il provider restituisce None
                    fase2_gia_eseguita = True
                    print(f"   Fase 2 attempted in smart_retry but without valid result. Fallback skipped.")
            else:
                test_results_originale = esegui_test_originale(
                    test_path,
                    config.java_projects_dir,
                    target_class_path,
                    nome_classe_solo,
                    config.metodo_da_testare,
                    config.nome_esperimento,
                )
                stampa_risultati_test(test_results_originale, "metodo originale")
                retry_history = []
                fase2_gia_eseguita = False

            if not verifica_risultati_finali(
                config,
                test_results_originale,
                test_path,
                target_class_path,
                selezione_info,
                config.prompt_test_metodo,
                test_generated_content,
                retry_history,
                soglia_similarita_dinamica=soglia_similarita_dinamica,
            ):
                return False
        else:
            print(
                f"Modality {config.modalita} not supported or invalid generation modality"
            )
            return False

    if config.esegui_seconda_fase and not fase2_gia_eseguita:
        if config.esegui_prima_fase:
            test_path_to_use = test_path
            metodo_precedente_path = None
            test_results_originale_value = test_results_originale
        else:
            test_path_to_use = config.file_test_per_seconda_fase
            metodo_precedente_path = config.metodo_generato_precedente
            test_results_originale_value = None

        if config.modalita == "dipendenze":
            metodo_rigenerato_codice_from_fase2, prompt_rigenera_completo, metodo_originale_backup = esegui_fase2_dipendenze(
                config,
                provider_func,
                config.model_name,
                test_path_to_use,
                test_results_originale_value,
                metodo_precedente_path,
            )
            # Con dipendenze, il primo valore è il CODICE, non il PATH
            metodo_path = target_class_path  # Usa il path della classe target come riferimento
        elif config.modalita == "senza_dipendenze":
            metodo_path, prompt_rigenera_completo, metodo_originale_backup = esegui_fase2_senza_dipendenze(
                config,
                provider_func,
                config.model_name,
                test_path_to_use,
                test_results_originale_value,
                metodo_precedente_path,
            )
            metodo_rigenerato_codice_from_fase2 = None  # Con senza dipendenze, estraiamo dal file
        else:
            print(f"Modality {config.modalita} not supported")
            return False

        print(f"\n=== TEST EXECUTION ON REGENERATED METHOD ===")
        # Ora il metodo rigenerato è nella classe originale, usa esegui_test_originale
        test_results_rigenerato = esegui_test_originale(
            test_path_to_use,
            config.java_projects_dir,
            target_class_path,  # La classe originale contiene ora il metodo rigenerato
            nome_classe_solo,
            config.metodo_da_testare,
            config.nome_esperimento,
        )
        stampa_risultati_test(test_results_rigenerato, "metodo rigenerato")
        
        # Salva il metodo rigenerato PRIMA di ripristinare l'originale (per le metriche)
        metodo_rigenerato_codice = None
        
        if config.modalita == "dipendenze":
            # Con dipendenze: estrai dalla classe target o usa il codice già estratto
            if metodo_rigenerato_codice_from_fase2:
                metodo_rigenerato_codice = metodo_rigenerato_codice_from_fase2
            else:
                # Fallback: estrai dalla classe target
                target_class_path = config.get_target_class_path()
                if os.path.exists(target_class_path):
                    try:
                        metodo_rigenerato_codice = estrai_metodo_singolo(
                            leggi_file(target_class_path), config.metodo_da_testare, signature=config.metodo_signature
                        )
                    except Exception as e:
                        print(f"WARNING: Failed to extract regenerated method from class: {e}")
        else:
            # Senza dipendenze: estrai dal file
            if metodo_path and os.path.exists(metodo_path):
                metodo_rigenerato_codice = estrai_metodo_singolo(
                    leggi_file(metodo_path), config.metodo_da_testare, signature=config.metodo_signature
                )
        
        # === SIMILARITY CALCULATION (Fallback Phase 2) ===
        # This section ensures similarity is calculated and printed in the log
        # even when the refinement loop fails and fallback phase 2 is executed
        if metodo_originale_backup and metodo_rigenerato_codice:
            print(f"\n=== SIMILARITY CALCULATION (Fallback Phase 2) ===")
            try:
                from core.evaluation.evaluation import confronta_metodi, calcola_complexity_loc, calcola_soglia_dinamica
                
                # Calculate dynamic threshold based on original method's SLOC
                complexity_info = calcola_complexity_loc(metodo_originale_backup)
                sloc_originale = complexity_info.get("sloc", 0)
                
                if config.usa_soglia_dinamica:
                    soglia_dinamica = calcola_soglia_dinamica(sloc_originale, base=config.soglia_similarita)
                else:
                    soglia_dinamica = config.soglia_similarita
                
                metriche_similarita = confronta_metodi(
                    metodo_originale_backup,
                    metodo_rigenerato_codice,
                    soglia_dinamica,
                    config.cb_use_stopwords,
                    similarity_weights=config.similarity_weights,
                )
                overall_similarity = metriche_similarita.get("overall_similarity", 0.0)
                passa_soglia = metriche_similarita.get("passes_threshold", False)
                print(f"Overall similarity: {overall_similarity:.4f}")
                if config.usa_soglia_dinamica:
                    print(f"Dynamic threshold: {soglia_dinamica:.2f} (SLOC: {sloc_originale})")
                else:
                    print(f"Dynamic threshold: DISABLED (Base usage: {soglia_dinamica:.2f})")
                print(f"Passes threshold: {'✓ YES' if passa_soglia else '✗ NO'}")
            except Exception as e:
                print(f"WARNING: Error calculating similarity (fallback): {e}")
        
        # Restore original method after test execution
        if metodo_originale_backup:
            from utils.code.code_analysis import ripristina_metodo_originale
            target_class_path = config.get_target_class_path()
            if ripristina_metodo_originale(target_class_path, config.metodo_da_testare, metodo_originale_backup):
                print(f"\n✓ Original method restored in class")

        result = {
            "test_path": test_path_to_use,
            "metodo_path": metodo_path,
            "metodo_precedente_path": metodo_precedente_path,
            "test_results_originale": test_results_originale_value,
            "test_results_rigenerato": test_results_rigenerato,
            "metodo_originale_backup": metodo_originale_backup,  # Per confronto metriche
            "metodo_rigenerato_codice": metodo_rigenerato_codice,  # Codice del metodo rigenerato
        }

    elif not config.esegui_seconda_fase or (config.esegui_seconda_fase and not result):
        if test_results_originale:
            salva_metriche_solo_originale(
                test_results_originale,
                target_class_path,
                config.output_dir,
                nome_classe_solo,
                config.metodo_da_testare,
                config.provider_nome,
                config.versione_esperimento,
                config.soglia_similarita,
                config.prompt_test_metodo,
                selezione_info,
                test_generated_content,
                config.cb_use_stopwords,
                config.nome_esperimento,
                descrizione_esperimento=config.descrizione,
                model_name=config.model_name,
                retry_history=retry_history if retry_history else None,
                soglia_coverage=config.soglia_coverage,
                usa_soglia_dinamica=config.usa_soglia_dinamica,
            )
        if not fase2_gia_eseguita:
            print("Second phase not executed")
            return True

    if result:
        if fase2_gia_eseguita and not prompt_rigenera_completo:
            prompt_rigenera_completo = None

        salva_metriche_finali(
            config,
            result,
            target_class_path,
            selezione_info,
            prompt_test_completo,
            prompt_rigenera_completo,
            test_generated_content,
            retry_history,
            fase2_gia_eseguita,
            test_file_path=result.get("test_path") if result else None,
            soglia_similarita_dinamica=soglia_similarita_dinamica,
        )
    # Verifica se la test suite finale compila, altrimenti disabilitala
    # per evitare interferenze con esperimenti successivi
    final_test_path = None
    if result and result.get("test_path"):
        final_test_path = result.get("test_path")
    elif test_path_iniziale:
        final_test_path = test_path_iniziale
    
    if final_test_path and os.path.exists(final_test_path):
        # Verifica se la test suite compila
        try:
            final_test_results = esegui_test_originale(
                final_test_path,
                config.java_projects_dir,
                target_class_path,
                nome_classe_solo,
                config.metodo_da_testare,
                config.nome_esperimento,
            )
            has_compilation_errors = final_test_results.get("has_compilation_errors", False)
            tests_total = final_test_results.get("tests_total", 0)
            
            if has_compilation_errors or tests_total == 0:
                print(f"\nWARNING:  Final test suite does not compile. Disabling it to allow subsequent experiments...")
                disabled_path = final_test_path + ".disabled"
                os.rename(final_test_path, disabled_path)
                print(f"   Test suite disabled: {os.path.basename(disabled_path)}")
        except Exception as e:
            print(f"   WARNING:  Error during final compilation check: {e}")

    # Log quota usage per provider Chutes
    if config.provider_nome == "chutes":
        try:
            from providers.chutes_provider import get_chutes_quota_usage
            quota_info = get_chutes_quota_usage()
            if quota_info:
                print(f"\n=== CHUTES QUOTA USAGE ===")
                print(f"   {json.dumps(quota_info, indent=2)}")
        except Exception as e:
            print(f"   WARNING: Error during Chutes quota retrieval: {e}")

    print(f"\nExperiment {config.nome_esperimento} completed!")
    return True


def main():
    load_dotenv()
    parser = argparse.ArgumentParser(
        description="Agente Tester - Generation of JUnit 5 tests and code regeneration with LLM",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples of use:
  python main.py --config configs/Esperimento1/esperimento1.yaml
  python main.py --config configs/Esperimento2/esperimento1.yaml
        """,
    )
    parser.add_argument(
        "--config",
        type=str,
        help="Path to the experiment YAML configuration file",
    )
    args = parser.parse_args()

    success = False
    if args.config:
        success = esegui_esperimento(args.config)
    else:
        print("WARNING:  No arguments provided, using default configuration")
        success = esegui_esperimento("configs/Esperimento4/esperimento1.yaml")
    
    if not success:
        sys.exit(1)


if __name__ == "__main__":
    main()
