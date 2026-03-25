import os
from typing import Dict, Optional, List, Tuple
from utils.test.test_executor import esegui_test_originale
from utils.test.test_utils import (
    stampa_risultati_test,
    estrai_nomi_test_da_codice,
    estrai_codice_test_per_nome,
    estrai_metodo_con_riga_inizio,
    get_coverage_class_name,
)
from utils.text.text_utils import (
    estrai_codice_java,
    rimuovi_suffisso_llmcheck_da_nome_classe,
)
from utils.io.file_utils import get_test_dir, salva_file, leggi_file
from utils.prompt.prompt_logger import salva_prompt_log, salva_risposta_llm
from utils.code.error_extractor import estrai_errori_compilazione, estrai_test_con_errori_compilazione
from utils.test.test_runner_with_coverage import find_jacoco_report, extract_uncovered_lines
from core.test.test_suite import leggi_codice_test_attuale, disabilita_test_suite, riabilita_test_suite, estrai_test_validi_completi
from core.retry.refinement import prepara_refinement_prompt
from utils.test.test_utils import unisci_test_validi_e_rigenerati
from core.evaluation.evaluation import confronta_metodi
from utils.tracking.token_tracker import (
    capture_llm_tokens,
)
from utils.io.safe_file_manager import SafeClassInjector


def _capture_llm_tokens(operation_type: str, iteration: int = None, after_refinement: int = None, refinement_iteration: int = None):
    """
    Cattura i token usati dall'ultima chiamata LLM (Ollama Cloud, Ollama Colab o Groq).
    Wrapper per retrocompatibilità - ora usa la funzione unificata in token_tracker.
    """
    capture_llm_tokens(operation_type=operation_type, iteration=iteration, after_refinement=after_refinement, refinement_iteration=refinement_iteration)


def esegui_smart_retry_loop(
    test_path: str,
    config: "ExperimentConfig",
    provider_func,
    model_name: str,
    target_class_path: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    project_path: str,
    test_results_originale: Optional[Dict],
    initial_context: str = None,  # Contesto iniziale (dipendenze, ecc.)
) -> Tuple[Dict, str, List, Optional[str], Optional[Dict], Optional[float]]:
    """
    Esegue il loop Smart Retry con Repair e Refinement separati.

    Flusso:
    1. REPAIR LOOP: rendere test compilabili
    2. Esegue test su metodo originale
    3. Rigenera metodo (sostituisce direttamente nella classe originale)
    4. Esegue test su metodo rigenerato
    5. Confronta similarità (usando il backup del metodo originale)
    6. REFINEMENT LOOP: se similarità insufficiente, migliora test suite
    7. Ripristina metodo originale alla fine
    """
    retry_history = []
    metodo_path = None
    test_results_rigenerato = None
    metodo_originale_backup = None  # Backup per ripristino finale

    # STEP 1: REPAIR LOOP - rendere test compilabili (solo se necessario)
    print(f"\n=== VERIFYING INITIAL TEST COMPILATION ===")
    test_results_verifica = esegui_test_originale(
        test_path,
        project_path,
        target_class_path,
        nome_classe_solo,
        metodo_da_testare,
        config.nome_esperimento,
    )

    has_compilation_errors = test_results_verifica.get("has_compilation_errors", False)

    if has_compilation_errors:
        # Tenta di usare gli errori già estratti da test_executor
        errori_comp_check = test_results_verifica.get("extracted_errors")
        
        # Fallback
        if not errori_comp_check:
            errori_comp_check = estrai_errori_compilazione(
                test_results_verifica.get("output", "")
            )
        if (
            not errori_comp_check
            or errori_comp_check.strip() == ""
            or errori_comp_check.strip().lower() == "none"
        ):
            print(
                f"Tests compile correctly (no compilation errors found)."
            )
            has_compilation_errors = False

    if has_compilation_errors:
        print(f"\n=== REPAIR LOOP: Making tests compilable ===")
        result = esegui_repair_loop(
            test_path,
            config,
            provider_func,
            model_name,
            target_class_path,
            nome_classe_solo,
            metodo_da_testare,
            project_path,
            initial_test_results=test_results_verifica,
        )
        
        # Gestisci il caso in cui nessun test compila
        if result is None or result[0] is None:
            print(f"\n EXPERIMENT INTERRUPTED: No compilable tests after all attempts.")
            return None, test_path, [], None, None, None
        
        test_path, repair_history = result
        # Aggiungi tipo per distinguere repair da refinement
        for repair_info in repair_history:
            repair_info["type"] = "repair"
        retry_history.extend(repair_history)
    else:
        print(f"Tests compile correctly. Repair loop not necessary.")
        repair_history = []

    # STEP 2: Esegui test su metodo originale
    print(f"\n=== EXECUTING TESTS ON ORIGINAL METHOD ===")
    
    if not repair_history and test_results_verifica:
         print(f"Reusing initial test verification results (no repair needed).")
         test_results_originale = test_results_verifica
    else:    
        test_results_originale = esegui_test_originale(
            test_path,
            project_path,
            target_class_path,
            nome_classe_solo,
            metodo_da_testare,
            config.nome_esperimento,
        )
    stampa_risultati_test(test_results_originale, "metodo originale")

  
    from utils.test.test_runner_with_coverage import find_jacoco_report, extract_uncovered_lines
    from utils.test.test_utils import estrai_metodo_con_riga_inizio, get_coverage_class_name
    
    line_coverage = test_results_originale.get('line_coverage', 0.0)
    if line_coverage < 100.0:
        report_path = find_jacoco_report(project_path)
        if report_path:
            try:
                codice_classe_completo = leggi_file(target_class_path)
                metodo_originale, riga_inizio_metodo = estrai_metodo_con_riga_inizio(codice_classe_completo, metodo_da_testare, config.metodo_signature)
                if metodo_originale:
                    righe_metodo = metodo_originale.split('\n')
                    riga_fine_metodo = riga_inizio_metodo + len(righe_metodo) - 1
                    
                    coverage_class_name = get_coverage_class_name(target_class_path, project_path)
                    righe_non_coperte = extract_uncovered_lines(
                        report_path, coverage_class_name, metodo_da_testare,
                        riga_inizio_metodo=riga_inizio_metodo,
                        riga_fine_metodo=riga_fine_metodo,
                        codice_classe_completo=codice_classe_completo
                    )
                    test_results_originale['uncovered_lines'] = righe_non_coperte if righe_non_coperte else []
            except Exception as e:
                test_results_originale['uncovered_lines'] = []
    else:
        test_results_originale['uncovered_lines'] = []

    # Aggiungi test results iniziali al retry_history
    # Estrai test_info con tutti i campi necessari (test_falliti_assert, test_errori_runtime)
    test_info_orig_initial = test_results_originale.get("test_info", {})
    initial_test_info = {
        "type": "initial_test_execution",
        "original_test_results": {
            "tests_passed": test_results_originale.get("tests_passed", 0),
            "tests_total": test_results_originale.get("tests_total", 0),
            "tests_failed": test_results_originale.get("tests_total", 0)
            - test_results_originale.get("tests_passed", 0),
            "line_coverage": test_results_originale.get("line_coverage", 0.0),
            "branch_coverage": test_results_originale.get("branch_coverage", 0.0),
            # error_test_names rimosso - usare solo test_info.test_errori_runtime
            "test_info": {
                "valid_tests": test_info_orig_initial.get("valid_tests", []),
                "invalid_tests": test_info_orig_initial.get("invalid_tests", []),
                "failed_assert_tests": test_info_orig_initial.get("failed_assert_tests", []),
                "runtime_error_tests": test_info_orig_initial.get("runtime_error_tests", []),
            },
        },
    }
    retry_history.append(initial_test_info)

    # Salva test suite iniziale (dopo repair loop) in results/logs/test_suite
    from utils.prompt.prompt_logger import salva_test_suite

    codice_test_iniziale = leggi_codice_test_attuale(test_path, False)
    if codice_test_iniziale:
        # retry_count = None per la test suite iniziale (prima rigenerazione)
        test_output = test_results_originale.get("output", "")
        salva_test_suite(
            codice_test_iniziale,
            nome_classe_solo,
            config.versione_esperimento,
            None,
            config.nome_esperimento,
            metodo_da_testare,
            test_output,
            model_name=model_name,
        )

    # STEP 3-5: Rigenera metodo, esegui test, confronta (solo se fase 2 abilitata)
    if config.esegui_seconda_fase:
        # Determina funzione fase 2
        if config.modalita == "dipendenze":
            from main import esegui_fase2_dipendenze

            esegui_fase2_func = esegui_fase2_dipendenze
        else:
            from main import esegui_fase2_senza_dipendenze

            esegui_fase2_func = esegui_fase2_senza_dipendenze

        # Prepare Safe Injector
        injector = SafeClassInjector(target_class_path)
        
        # Salva il metodo originale PRIMA della prima rigenerazione (per confronto e ripristino)
        from utils.code.code_analysis import backup_metodo_originale
        
        if metodo_originale_backup is None:
            # Assicuriamoci che non ci siano residui .disabled prima di iniziare
            if os.path.exists(target_class_path + ".disabled"):
                 injector.restore()

            metodo_originale_backup = backup_metodo_originale(target_class_path, metodo_da_testare, config.metodo_signature)
            if metodo_originale_backup:
                print(f"Backup of original method saved for final restoration")
            else:
                print(f"CRITICAL ERROR: Unable to backup method {metodo_da_testare}. Immediate interruption to prevent code corruption.")
                return test_results_originale, test_path, retry_history, None, None, None

        # Calculate dynamic threshold based on original method's SLOC
        from core.evaluation.evaluation import calcola_complexity_loc, calcola_soglia_dinamica
        
        if metodo_originale_backup:
            complexity_info = calcola_complexity_loc(metodo_originale_backup)
            sloc_originale = complexity_info.get("sloc", 0)
            
            if config.usa_soglia_dinamica:
                soglia_similarita_dinamica = calcola_soglia_dinamica(sloc_originale, base=config.soglia_similarita)
                print(f"   Original method SLOC: {sloc_originale} -> Dynamic similarity threshold: {soglia_similarita_dinamica:.2f}")
            else:
                soglia_similarita_dinamica = config.soglia_similarita
                print(f"   Original method SLOC: {sloc_originale} -> Dynamic threshold DISABLED (Base usage: {soglia_similarita_dinamica:.2f})")
        else:
            soglia_similarita_dinamica = config.soglia_similarita
            sloc_originale = 0

        try:
            # STEP 3.5: Verifica soglie minime PRIMA della rigenerazione
            # Il metodo viene rigenerato SOLO se la test suite è di qualità sufficiente
            from utils.test.test_utils import calcola_weighted_pass_rate
            
            weighted_pass_rate, wpr_dettagli = calcola_weighted_pass_rate(test_results_originale)
            line_coverage_iniziale = test_results_originale.get("line_coverage", 0.0) / 100.0  # Normalizza a 0-1
            
            soglie_rispettate = (
                weighted_pass_rate >= config.soglia_min_weighted_pass_rate
                and line_coverage_iniziale >= config.soglia_min_coverage
            )
            
            print(f"\n=== VERIFYING MINIMUM THRESHOLDS ===")
            print(f"   Weighted Pass Rate: {weighted_pass_rate:.2%} (threshold: {config.soglia_min_weighted_pass_rate:.0%})")
            print(f"     - PASSED: {wpr_dettagli['test_passati']} (weight 1.0)")
            print(f"     - ERROR:   {wpr_dettagli['runtime_error_tests']} (weight 0.5)")
            print(f"     - FAILURE: {wpr_dettagli['failed_assert_tests']} (weight 0.0)")
            print(f"   Line Coverage: {line_coverage_iniziale:.2%} (threshold: {config.soglia_min_coverage:.0%})")
            print(f"   Thresholds respected: {'YES' if soglie_rispettate else 'NO'}")
            
            # Variabili per tracciare stato rigenerazione
            metodo_rigenerato_codice = None
            test_results_rigenerato = None
            overall_similarity = None
            passa_soglia = False
            has_valid_similarity = False
            
            if soglie_rispettate:
                # STEP 4: Rigenera metodo (prima rigenerazione dai test iniziali, retry_count=None)
                print(f"\n=== METHOD REGENERATION ===")
                metodo_rigenerato_codice, _, backup = esegui_fase2_func(
                    config,
                    provider_func,
                    model_name,
                    test_path,
                    test_results_originale,
                    None,
                    retry_count=None,
                    metodo_originale_backup=metodo_originale_backup,
                    precomputed_context=initial_context,
                )
                
                if not metodo_rigenerato_codice:
                    print(f"WARNING:  Method generation failed (no code returned).")
                    return test_results_originale, test_path, retry_history, None, None, soglia_similarita_dinamica

                # Aggiorna backup se non ce l'avevamo
                if metodo_originale_backup is None and backup:
                    metodo_originale_backup = backup
                
                # SAFE INJECTION
                if not injector.inject_and_backup(metodo_rigenerato_codice, metodo_da_testare):
                    print(f" Error in SafeClassInjector during injection. Aborting.")
                    return test_results_originale, test_path, retry_history, None, None, soglia_similarita_dinamica

                metodo_path = target_class_path # Nominalmente è lo stesso file

                # Esegui test su metodo rigenerato (che ora è INIETTATO nel file class)
                print(f"\n=== EXECUTING TESTS ON REGENERATED METHOD ===")

                test_results_rigenerato = esegui_test_originale(
                    test_path,
                    project_path,
                    target_class_path,  # La classe originale contiene ora il metodo rigenerato
                    nome_classe_solo,
                    metodo_da_testare,
                    config.nome_esperimento,
                )
                stampa_risultati_test(test_results_rigenerato, "metodo rigenerato")

                test_info_regen_initial = test_results_rigenerato.get("test_info", {})
                initial_regen_test_info = {
                    "type": "initial_regenerated_test_execution",
                    "regenerated_test_results": {
                        "tests_passed": test_results_rigenerato.get("tests_passed", 0),
                        "tests_total": test_results_rigenerato.get("tests_total", 0),
                        "tests_failed": test_results_rigenerato.get("tests_total", 0)
                        - test_results_rigenerato.get("tests_passed", 0),
                        "line_coverage": test_results_rigenerato.get("line_coverage", 0.0),
                        "branch_coverage": test_results_rigenerato.get("branch_coverage", 0.0),
                        "test_info": {
                            "valid_tests": test_info_regen_initial.get("valid_tests", []),
                            "invalid_tests": test_info_regen_initial.get("invalid_tests", []),
                            "failed_assert_tests": test_info_regen_initial.get("failed_assert_tests", []),
                            "runtime_error_tests": test_info_regen_initial.get("runtime_error_tests", []),
                        },
                    },
                }
                retry_history.append(initial_regen_test_info)

                # STEP 5: Confronta similarità
                print(f"\n=== SIMILARITY CALCULATION ===")
            
                # Calcolo Similarità: usa stringhe in memoria
                metodo_originale = metodo_originale_backup
                metodo_rigenerato = metodo_rigenerato_codice
                

                if metodo_originale and metodo_rigenerato:
                    metriche_similarita = confronta_metodi(
                        metodo_originale,
                        metodo_rigenerato,
                        soglia_similarita_dinamica,
                        config.cb_use_stopwords,
                        similarity_weights=config.similarity_weights,
                    )
                    overall_similarity = metriche_similarita.get("overall_similarity", 0.0)
                    passa_soglia = metriche_similarita.get("passes_threshold", False)
                    has_valid_similarity = True

                    print(f"Overall similarity: {overall_similarity:.4f}")
                    if config.usa_soglia_dinamica:
                        print(f"Dynamic threshold: {soglia_similarita_dinamica:.2f} (SLOC: {sloc_originale})")
                    else:
                        print(f"Dynamic threshold: DISABLED (Base usage: {soglia_similarita_dinamica:.2f})")
                    print(f"Passes threshold: {' YES' if passa_soglia else ' NO'}")


                    # Salva anche il metodo iniziale rigenerato per tracciare l'evoluzione
                    # Estrai test_info con tutti i campi necessari
                    test_info_orig_method = test_results_originale.get("test_info", {})
                    test_info_regen_method = test_results_rigenerato.get("test_info", {})
                    initial_method_info = {
                        "type": "initial_method_generation",
                        "refinement_iteration": 0,  # Iterazione 0 = metodo iniziale
                        "original_method": metodo_originale,
                        "regenerated_method": metodo_rigenerato,
                        "overall_similarity": overall_similarity,
                        "passes_threshold": passa_soglia,
                        "similarity_metrics": metriche_similarita,
                        "original_test_results": {
                            "tests_passed": test_results_originale.get("tests_passed", 0),
                            "tests_total": test_results_originale.get("tests_total", 0),
                            "tests_failed": test_results_originale.get("tests_failed", 0),
                            "line_coverage": test_results_originale.get(
                                "line_coverage", 0.0
                            ),
                            "branch_coverage": test_results_originale.get(
                                "branch_coverage", 0.0
                            ),
                            "test_info": {
                                "valid_tests": test_info_orig_method.get("valid_tests", []),
                                "invalid_tests": test_info_orig_method.get("invalid_tests", []),
                                "failed_assert_tests": test_info_orig_method.get("failed_assert_tests", []),
                                "runtime_error_tests": test_info_orig_method.get("runtime_error_tests", []),
                            },
                        },
                        "regenerated_test_results": {
                            "tests_passed": test_results_rigenerato.get("tests_passed", 0),
                            "tests_total": test_results_rigenerato.get("tests_total", 0),
                            "tests_failed": test_results_rigenerato.get("tests_failed", 0),
                            "line_coverage": test_results_rigenerato.get(
                                "line_coverage", 0.0
                            ),
                            "branch_coverage": test_results_rigenerato.get(
                                "branch_coverage", 0.0
                            ),
                            "test_info": {
                                "valid_tests": test_info_regen_method.get("valid_tests", []),
                                "invalid_tests": test_info_regen_method.get("invalid_tests", []),
                                "failed_assert_tests": test_info_regen_method.get("failed_assert_tests", []),
                                "runtime_error_tests": test_info_regen_method.get("runtime_error_tests", []),
                            },
                        },
                    }
                    retry_history.append(initial_method_info)
                else:
                    print(f"WARNING:  Impossible to extract methods for comparison.")
                    has_valid_similarity = False
                    passa_soglia = False
                    overall_similarity = 0.0
                
                # Restore original after comparison
                if injector:
                    injector.restore()
            else:
                # SOGLIE NON RISPETTATE: non rigenerare il metodo, vai diretto al refinement
                print(f"\n=== THRESHOLDS NOT MET: Skip regeneration, direct refinement ===")
                print(f"   The test suite is not of sufficient quality to regenerate the method.")
                print(f"   Entering refinement loop to improve the test suite...")
                has_valid_similarity = False
                passa_soglia = False

            # STEP 6: REFINEMENT LOOP
            # Verifica condizioni per refinement loop
            line_coverage = test_results_originale.get("line_coverage", 0.0)
            tests_passed = test_results_originale.get("tests_passed", 0)
            tests_total = test_results_originale.get("tests_total", 0)

            condizioni_ottimali = (
                line_coverage >= 100.0
                and tests_passed == tests_total
                and tests_total > 0
            )

            # Entra nel refinement se:
            # 1. Similarità non disponibile (soglie non rispettate) -> migliora test suite
            # 2. Similarità sotto soglia -> migliora test suite per migliorare similarità
            if not passa_soglia:
                # Prepara similarity_info se disponibile
                if has_valid_similarity and condizioni_ottimali:
                    print(f"\n=== REFINEMENT LOOP: Improve similarity (coverage/pass rate already optimal) ===")
                    print(f"   Coverage: {line_coverage:.2f}% | Tests: {tests_passed}/{tests_total}")
                    print(f"   Similarity: {overall_similarity:.4f} < {soglia_similarita_dinamica:.4f} (below dynamic threshold)")
                    print(f"   Generating additional tests to better capture semantics...")
                    similarity_info_param = {
                        "is_enhancement_mode": True,
                        "similarity_current": overall_similarity,
                        "similarity_threshold": soglia_similarita_dinamica
                    }
                elif has_valid_similarity:
                    print(f"\n=== REFINEMENT LOOP: Improve similarity ===")
                    similarity_info_param = {
                        "is_enhancement_mode": False,
                        "similarity_current": overall_similarity,
                        "similarity_threshold": soglia_similarita_dinamica
                    }
                else:
                    print(f"\n=== REFINEMENT LOOP: Improve test suite (similarity not calculated) ===")
                    similarity_info_param = None  # Similarity not available
                
                (
                    test_results_originale,
                    test_path,
                    refinement_history,
                    metodo_path_ref,
                    test_results_rigenerato,
                ) = esegui_refinement_loop(
                    test_path,
                    config,
                    provider_func,
                    model_name,
                    target_class_path,
                    nome_classe_solo,
                    metodo_da_testare,
                    project_path,
                    test_results_originale,
                    esegui_fase2_func,
                    target_class_path, # metodo_path_iniziale (nominal)
                    metodo_originale_backup,  # Passa il backup per confronto
                    initial_context,
                    injector=injector,
                    metodo_precedente_codice=metodo_rigenerato_codice,
                    similarity_info=similarity_info_param,  # Per similarity enhancement mode
                    soglia_similarita_dinamica=soglia_similarita_dinamica,
                    sloc_originale=sloc_originale,
                )
                # Aggiungi tipo per distinguere refinement da repair
                for refinement_info in refinement_history:
                    if "type" not in refinement_info:
                        refinement_info["type"] = "refinement"
                retry_history.extend(refinement_history)
            else:
                print(
                    f"\n Sufficient similarity! Refinement loop not necessary."
                )
        except Exception as e:
            print(f"WARNING:  Error in main loop (phase 2): {e}")
        finally:
            if injector:
                try:
                    if os.path.exists(target_class_path):
                         final_method_path = os.path.join(config.output_dir, f"Final_{nome_classe_solo}_{metodo_da_testare}.java")
                         src_content = leggi_file(target_class_path)
                         if src_content:
                             with open(final_method_path, 'w', encoding='utf-8') as dst:
                                 dst.write(src_content)
                         metodo_path = final_method_path
                except Exception as ex:
                    print(f"WARNING:  Error saving final method: {ex}")

                if injector.restore():
                     print(f"\n Original method restored in class (SafeClassInjector)")
                else:
                     print(f"\nWARNING:  Warning: SafeClassInjector restore reported false (maybe already clean)")

    return (
        test_results_originale,
        test_path,
        retry_history,
        metodo_path,
        test_results_rigenerato,
        soglia_similarita_dinamica,
    )


def esegui_repair_loop(
    test_path: str,
    config: "ExperimentConfig",
    provider_func,
    model_name: str,
    target_class_path: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    project_path: str,
    after_refinement: int = None,  # Numero dell'iterazione di refinement dopo cui avviene il repair
    initial_test_results: Dict = None, # Risultati test iniziali (se disponibili)
) -> Tuple[str, List[Dict]]:
    """
    Loop di Repair: rendere i test compilabili.
    Scarta test irrecuperabili dopo max_retries_repair tentativi.
    """
    repair_count = 1  # Parte da 1 invece di 0
    repair_history = []

    context_msg = f" (after refinement {after_refinement})" if after_refinement is not None else " (initial tests)"
    print(f"Max retry repair: {config.max_retries_repair}{context_msg}")

    while repair_count <= config.max_retries_repair:
        # Verifica compilazione
        print(
            f"\n=== VERIFYING COMPILATION (Repair {repair_count}/{config.max_retries_repair}) ==="
        )
        if repair_count == 1 and initial_test_results:
             print(f" Reusing initial verification test results.")
             test_results = initial_test_results
             initial_test_results = None # Resetta per i loop successivi
        else:
            test_results = esegui_test_originale(
                test_path,
                project_path,
                target_class_path,
                nome_classe_solo,
                metodo_da_testare,
                config.nome_esperimento,
                timeout=120,
            )

        has_compilation_errors = test_results.get("has_compilation_errors", False)

        # Se non ci sono errori di compilazione, esci dal loop
        if not has_compilation_errors:
            print(f" Tests compile correctly. Repair loop completed.")
            break

        # Estrai informazioni dettagliate sui test
        tests_passed = test_results.get("tests_passed", 0)
        tests_total = test_results.get("tests_total", 0)
        tests_failed = tests_total - tests_passed
        test_info = test_results.get("test_info", {})
        test_non_validi = test_info.get("invalid_tests", [])
        test_info = test_results.get("test_info", {})
        test_validi = test_info.get("valid_tests", [])
        test_non_validi = test_info.get("invalid_tests", [])
        line_coverage = test_results.get("line_coverage", 0.0)
        branch_coverage = test_results.get("branch_coverage", 0.0)

        repair_info = {
            "repair_iteration": repair_count,
            "has_compilation_errors": has_compilation_errors,
            "compilation_errors": None,
            "test_results": {
                "tests_passed": tests_passed,
                "tests_total": tests_total,
                "tests_failed": tests_failed,
                "line_coverage": line_coverage,
                "branch_coverage": branch_coverage,
                "valid_tests": test_validi,
                "invalid_tests": test_non_validi,
            },
        }

        if not has_compilation_errors:
            print(f" Tests compile correctly!")
            repair_history.append(repair_info)
            break

        # Prepara repair prompt (esegui PRIMA della verifica max retry)
        codice_test_attuale = leggi_codice_test_attuale(test_path, False)
        if codice_test_attuale is None:
            print(f"WARNING:  Impossible to read test suite.")
            break

        # Tenta di usare gli errori già estratti da test_executor se disponibili
        errori_comp = test_results.get("extracted_errors")
        
        # Fallback: estrai se non presenti nel dizionario results
        if not errori_comp:
            test_class_filter = f"{nome_classe_solo}_{metodo_da_testare}TestGenerated"
            errori_comp = estrai_errori_compilazione(test_results.get("output", ""), filter_for_class=test_class_filter)

        if (
            not errori_comp
            or errori_comp.strip() == ""
            or errori_comp.strip().lower() == "none"
        ):
            print(
                f" No compilation errors found. Tests compile correctly."
            )
            repair_info["has_compilation_errors"] = False
            repair_history.append(repair_info)
            break

        # Leggi codice classe target per rilevare se è astratta
        target_class_code = None
        try:
            if target_class_path and os.path.exists(target_class_path):
                target_class_code = leggi_file(target_class_path)
        except Exception:
            pass
        
        repair_prompt = costruisci_repair_prompt(codice_test_attuale, errori_comp, target_class_code, nome_classe_solo)

        # Salva prompt repair
        salva_prompt_log(
            repair_prompt,
            "Test_repair",
            nome_classe_solo,
            config.versione_esperimento,
            config.nome_esperimento,
            retry_count=repair_count - 1,
            metodo_da_testare=metodo_da_testare,
            model_name=model_name,
        )

        print(f"Requesting repair from LLM...")
        test_repaired = provider_func(repair_prompt, model_name)
        
        # Capture tokens used for repair
        # after_refinement is passed to distinguish initial repairs from repairs after refinement
        _capture_llm_tokens("repair", repair_count, after_refinement=after_refinement)
        
        # Salva risposta repair
        salva_risposta_llm(
            test_repaired,
            "Test_repair",
            nome_classe_solo,
            config.versione_esperimento,
            repair_count - 1,
            config.nome_esperimento,
            metodo_da_testare=metodo_da_testare,
            model_name=model_name,
        )

        # Estrai e salva codice
        versione_suffix = (
            config.versione_esperimento if config.versione_esperimento else ""
        )
        nome_classe_test_attesa = (
            f"{nome_classe_solo}_{metodo_da_testare}TestGenerated{versione_suffix}"
        )
        codice_test_repaired = estrai_codice_java(
            test_repaired, nome_classe_test_attesa
        )
        codice_test_repaired = rimuovi_suffisso_llmcheck_da_nome_classe(
            codice_test_repaired
        )
        
        # Aggiunge @Timeout a livello di classe per prevenire infinite loop
        from utils.test.test_utils import aggiungi_timeout_classe
        codice_test_repaired = aggiungi_timeout_classe(codice_test_repaired)

        # Salva test suite riparata
        test_dir = get_test_dir(target_class_path, project_path)
        nome_file = f"{nome_classe_test_attesa}.java"
        test_path = salva_file(test_dir, nome_file, codice_test_repaired)

        repair_info["compilation_errors"] = errori_comp
        repair_history.append(repair_info)
        repair_count += 1

        # Verifica se abbiamo esaurito i tentativi di repair DOPO aver eseguito il tentativo
        if repair_count > config.max_retries_repair:
            # Verifica compilazione dopo l'ultimo tentativo
            test_results_finale = esegui_test_originale(
                test_path,
                project_path,
                target_class_path,
                nome_classe_solo,
                metodo_da_testare,
                config.nome_esperimento,
                timeout=120,
            )
            has_compilation_errors_finale = test_results_finale.get("has_compilation_errors", False)
            
            if has_compilation_errors_finale:
                # Raggiunto max retry repair - rimuovi test non compilabili in loop
                tests_total = test_results_finale.get("tests_total", 0)
                print(
                    f"\nWARNING:  Reached max retry repair ({config.max_retries_repair}). Removing non-compilable tests in loop..."
                )
                test_path = rimuovi_test_non_compilabili_loop(
                    test_path,
                    test_results_finale,
                    target_class_path,
                    nome_classe_solo,
                    metodo_da_testare,
                    config.versione_esperimento,
                    project_path,
                    config.nome_esperimento,
                )
                if test_path is None:
                    # Nessun test compila - la suite verrà scartata
                    print(f"WARNING:  No test compilable. The suite will be discarded.")
                    return None, repair_history
                if tests_total > 0:
                    print(f" Removal process completed.")
            break

    return test_path, repair_history


def esegui_refinement_loop(
    test_path: str,
    config: "ExperimentConfig",
    provider_func,
    model_name: str,
    target_class_path: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    project_path: str,
    test_results_originale: Dict,
    esegui_fase2_func,
    metodo_path_iniziale: str,
    metodo_originale_backup: str = None,  # Backup del metodo originale per confronto
    initial_context: str = None, # Contesto iniziale
    injector: SafeClassInjector = None,
    metodo_precedente_codice: str = None,
    similarity_info: Dict = None,  # Info similarità per similarity enhancement mode
    soglia_similarita_dinamica: float = None,  # Soglia calcolata dinamicamente
    sloc_originale: int = 0,  # SLOC del metodo originale
) -> Tuple[Dict, str, List[Dict], Optional[str], Optional[Dict]]:
    """
    Loop di Refinement: migliora la similarità del metodo rigenerato.
    """
    refinement_count = 0
    refinement_history = []
    metodo_path = metodo_path_iniziale
    test_results_rigenerato = None
    
    # Salva i risultati iniziali dei test per poter preservare i test passanti durante i merge
    # test_results_originale viene riassegnato durante il loop, quindi ne conserviamo una copia
    initial_test_results = test_results_originale.copy() if test_results_originale else None
    
    # Fallback per soglia dinamica: calcola se non passata
    if soglia_similarita_dinamica is None:
        if metodo_originale_backup:
            from core.evaluation.evaluation import calcola_complexity_loc, calcola_soglia_dinamica
            complexity_info = calcola_complexity_loc(metodo_originale_backup)
            sloc_originale = complexity_info.get("sloc", 0)
            
            if config.usa_soglia_dinamica:
                soglia_similarita_dinamica = calcola_soglia_dinamica(sloc_originale, base=config.soglia_similarita)
            else:
                soglia_similarita_dinamica = config.soglia_similarita
        else:
            soglia_similarita_dinamica = config.soglia_similarita
            sloc_originale = 0
    
    # Traccia l'ultima test suite unificata compilabile (inizialmente è la suite iniziale)
    ultima_suite_compilabile_path = test_path
    ultima_suite_compilabile_code = None  # Sarà letto quando serve
    
    # Metriche dell'iterazione precedente per confronto (usato quando max_retries_refinement=-1)
    prev_line_coverage = None
    prev_pass_rate = None  # pass_rate = tests_passed / tests_total
    prev_tests_passed = None  # Numero assoluto di test passati (per quality check)
    prev_similarity = None  # Similarità dell'iterazione precedente

    print(f"Max retry refinement: {config.max_retries_refinement}")
    
    # Tetto massimo assoluto per refinement adattivo (-1)
    MAX_ABSOLUTE_REFINEMENT = 5

    # Se max_retries_refinement == -1, continua finché c'è miglioramento (con tetto massimo)
    while (config.max_retries_refinement == -1 and refinement_count < MAX_ABSOLUTE_REFINEMENT) or \
          (config.max_retries_refinement != -1 and refinement_count <= config.max_retries_refinement):
        refinement_count += 1
        force_stop_after_regeneration = False  # Flag per forzare stop dopo rigenerazione finale

        # Prepara refinement prompt
        codice_test_attuale = leggi_codice_test_attuale(test_path, False)
        if codice_test_attuale is None:
            print(f"WARNING:  Impossible to read test suite.")
            break

        refinement_prompt, _, _ = prepara_refinement_prompt(
            codice_test_attuale,
            test_results_originale,
            target_class_path,
            metodo_da_testare,
            0.0,
            project_path,
            nome_classe_solo,  # soglia_coverage = 0.0 (non usata)
            metodo_originale_backup=metodo_originale_backup,  # Usa il backup del metodo originale
            metodo_signature=config.metodo_signature,  # Signature per distinguere overload
            context_description=initial_context,
            similarity_info=similarity_info,  # Per similarity enhancement mode
        )

        # Salva prompt refinement
        salva_prompt_log(
            refinement_prompt,
            "Test_refinement",
            nome_classe_solo,
            config.versione_esperimento,
            config.nome_esperimento,
            retry_count=refinement_count - 1,
            metodo_da_testare=metodo_da_testare,
            model_name=model_name,
        )

        print(f"Requesting refinement from LLM...")
        test_refined = provider_func(refinement_prompt, model_name)
        
        if test_refined is None:
             print(f"WARNING:  Refinement failed: provider returned None (error or max retries). Skipping iteration.")
             refinement_info_error = {
                 "refinement_iteration": refinement_count,
                 "overall_similarity": None,
                 "passes_threshold": False,
                 "error": "Provider returned None (LLM generation failed)"
             }
             refinement_history.append(refinement_info_error)
             continue
        
        # Cattura i token usati per il refinement
        _capture_llm_tokens("refinement", refinement_count)

        salva_risposta_llm(
            test_refined,
            "Test_refinement",
            nome_classe_solo,
            config.versione_esperimento,
            refinement_count - 1,
            config.nome_esperimento,
            metodo_da_testare=metodo_da_testare,
            model_name=model_name,
        )

        # Estrai codice
        versione_suffix = (
            config.versione_esperimento if config.versione_esperimento else ""
        )
        nome_classe_test_attesa = (
            f"{nome_classe_solo}_{metodo_da_testare}TestGenerated{versione_suffix}"
        )
        codice_test_refined = estrai_codice_java(test_refined, nome_classe_test_attesa)
        codice_test_refined = rimuovi_suffisso_llmcheck_da_nome_classe(
            codice_test_refined
        )
        
        # Aggiunge @Timeout a livello di classe per prevenire infinite loop
        from utils.test.test_utils import aggiungi_timeout_classe
        codice_test_refined = aggiungi_timeout_classe(codice_test_refined)

        # DISABILITA test suite originale per evitare conflitti di nome classe
        print(f"\n=== DISABLING ORIGINAL TEST SUITE ===")

        test_path_originale_disabilitato, success_disabilita = disabilita_test_suite(
            test_path
        )
        if not success_disabilita:
            print(
                f"WARNING:  Impossible to disable original test suite. Proceeding anyway..."
            )
            test_path_originale_disabilitato = test_path

        # Salva nuovi test (con stesso nome classe per evitare conflitti)
        test_path_nuovi = salva_file(
            get_test_dir(target_class_path, project_path),
            f"{nome_classe_test_attesa}.java",
            codice_test_refined,
        )

        # Verifica compilazione nuovi test
        print(f"\n=== VERIFYING COMPILATION OF NEW TESTS ===")
        test_results_nuovi = esegui_test_originale(
            test_path_nuovi,
            project_path,
            target_class_path,
            nome_classe_solo,
            metodo_da_testare,
            config.nome_esperimento,
        )

        has_comp_errors_nuovi = test_results_nuovi.get("has_compilation_errors", False)

        errori_comp_nuovi = None
        if has_comp_errors_nuovi:
            errori_comp_nuovi = estrai_errori_compilazione(
                test_results_nuovi.get("output", "")
            )
            # Se non ci sono errori di compilazione estratti, non chiamare il repair loop
            if (
                not errori_comp_nuovi
                or errori_comp_nuovi.strip() == ""
                or errori_comp_nuovi.strip().lower() == "none"
            ):
                print(
                    f" New tests compile correctly (no compilation errors found)."
                )
                has_comp_errors_nuovi = False

        # If they don't compile, repair loop (resets)
        if has_comp_errors_nuovi:
            print(f"WARNING:  New tests don't compile. Starting Repair Loop after refinement {refinement_count}...")
            result_repair = esegui_repair_loop(
                test_path_nuovi,
                config,
                provider_func,
                model_name,
                target_class_path,
                nome_classe_solo,
                metodo_da_testare,
                project_path,
                after_refinement=refinement_count,  # Indica che questo repair avviene dopo refinement N
            )
            
            # If ALL new tests don't compile, preserve the LAST COMPILED SUITE
            if result_repair is None or result_repair[0] is None:
                print(f"\nWARNING:  ALL new tests don't compile after repair.")
                print(f"   Preserving the last compiled unified test suite.")
                
                # Delete the .java file of the new non-compiling suite
                # before re-enabling the old one to avoid conflicts 
                if test_path_nuovi and os.path.exists(test_path_nuovi):
                    try:
                        os.remove(test_path_nuovi)
                        print(f"   Deleted new non-compiling suite: {os.path.basename(test_path_nuovi)}")
                    except Exception as e:
                        print(f"   WARNING: Impossible to delete new suite: {e}")
                
                # Re-enable original test suite (necessary to restore the file)
                print(f"\n=== RE-ENABLING ORIGINAL TEST SUITE ===")
                if success_disabilita:
                    test_path_riabilitato, success_riabilita = riabilita_test_suite(
                        test_path_originale_disabilitato
                    )
                    if success_riabilita:
                        print(f" Test suite originale re-enabled.")
                    else:
                        test_path_riabilitato = test_path_originale_disabilitato.replace(".disabled", ".java")
                else:
                    # The original suite was not disabled, use the original path
                    test_path_riabilitato = test_path
                
                # Use the LAST COMPILED SUITE instead of the initial suite
                if ultima_suite_compilabile_path and ultima_suite_compilabile_path != test_path:
                    # Restore the last compiled suite
                    if ultima_suite_compilabile_code:
                        with open(test_path_riabilitato, 'w', encoding='utf-8') as f:
                            f.write(ultima_suite_compilabile_code)
                        print(f" Restored last compiled unified test suite.")
                    test_path = test_path_riabilitato
                else:
                    test_path = test_path_riabilitato
                    print(f" Initial test suite preserved (no previous unified suite).")
                
                # If all tests were discarded, exit the loop instead of continuing
                # because it makes no sense to do another refinement if all tests were discarded
                print(f"WARNING:  All tests from refinement {refinement_count} were discarded. Exiting refinement loop.")
                
                # === FORCED REGENERATION BEFORE TERMINATING ===
                # Even if the refinement fails, we must regenerate the method at least once
                # using the last compiled test suite
                print(f"\n=== FORCED REGENERATION (before terminating) ===")
                print(f"   Using the last compiled test suite to regenerate the method...")
                
                try:
                    # Esegui test sull'ultima suite compilabile per ottenere i risultati
                    test_results_for_regen = esegui_test_originale(
                        test_path,
                        project_path,
                        target_class_path,
                        nome_classe_solo,
                        metodo_da_testare,
                        config.nome_esperimento,
                    )
                    
                    # Rigenera il metodo
                    metodo_rigenerato_codice_forced, _, _ = esegui_fase2_func(
                        config,
                        provider_func,
                        model_name,
                        test_path,
                        test_results_for_regen,
                        None,
                        retry_count=refinement_count,
                        metodo_originale_backup=metodo_originale_backup,
                        precomputed_context=initial_context,
                    )
                    
                    if metodo_rigenerato_codice_forced:
                        print(f" Method regenerated successfully!")
                        
                        # Inietta il metodo nella classe
                        if injector:
                            injector.inject_and_backup(metodo_rigenerato_codice_forced, metodo_da_testare)
                        
                        # Esegui test sul metodo rigenerato
                        print(f"\n=== TEST EXECUTION ON REGENERATED METHOD ===")
                        test_results_rigenerato = esegui_test_originale(
                            test_path,
                            project_path,
                            target_class_path,
                            nome_classe_solo,
                            metodo_da_testare,
                            config.nome_esperimento,
                        )
                        stampa_risultati_test(test_results_rigenerato, "metodo rigenerato")
                        
                        # Calcola similarità
                        print(f"\n=== SIMILARITY CALCULATION ===")
                        if metodo_originale_backup and metodo_rigenerato_codice_forced:
                            metriche_similarita = confronta_metodi(
                                metodo_originale_backup,
                                metodo_rigenerato_codice_forced,
                                soglia_similarita_dinamica,
                                config.cb_use_stopwords,
                                similarity_weights=config.similarity_weights,
                            )
                            overall_similarity = metriche_similarita.get("overall_similarity", 0.0)
                            passa_soglia = metriche_similarita.get("passes_threshold", False)
                            
                            if config.usa_soglia_dinamica:
                                print(f"Similarity: {overall_similarity:.4f} (Dynamic threshold: {soglia_similarita_dinamica:.2f})")
                            else:
                                print(f"Similarity: {overall_similarity:.4f} (Fixed threshold: {soglia_similarita_dinamica:.2f})")
                            
                            # Save results in refinement_history

                            test_info_orig = test_results_for_regen.get("test_info", {})
                            test_info_regen = test_results_rigenerato.get("test_info", {})
                            
                            refinement_info_forced = {
                                "refinement_iteration": refinement_count,
                                "type": "forced_regeneration",
                                "overall_similarity": overall_similarity,
                                "passes_threshold": passa_soglia,
                                "similarity_metrics": metriche_similarita,
                                "original_method": metodo_originale_backup,
                                "regenerated_method": metodo_rigenerato_codice_forced,
                                "original_test_results": {
                                    "tests_passed": test_results_for_regen.get("tests_passed", 0),
                                    "tests_total": test_results_for_regen.get("tests_total", 0),
                                    "line_coverage": test_results_for_regen.get("line_coverage", 0.0),
                                    "test_info": {
                                        "valid_tests": test_info_orig.get("valid_tests", []),
                                        "failed_assert_tests": test_info_orig.get("failed_assert_tests", []),
                                        "runtime_error_tests": test_info_orig.get("runtime_error_tests", []),
                                    },
                                },
                                "regenerated_test_results": {
                                    "tests_passed": test_results_rigenerato.get("tests_passed", 0),
                                    "tests_total": test_results_rigenerato.get("tests_total", 0),
                                    "line_coverage": test_results_rigenerato.get("line_coverage", 0.0),
                                    "test_info": {
                                        "valid_tests": test_info_regen.get("valid_tests", []),
                                        "failed_assert_tests": test_info_regen.get("failed_assert_tests", []),
                                        "runtime_error_tests": test_info_regen.get("runtime_error_tests", []),
                                    },
                                },
                            }
                            refinement_history.append(refinement_info_forced)
                        
                        # Ripristina originale dopo confronto
                        if injector:
                            injector.restore()
                    else:
                        print(f"WARNING:  Forced regeneration failed (no code).")
                except Exception as e:
                    print(f"WARNING:  Error in forced regeneration: {e}")
                
                break
            
            test_path_nuovi, repair_history_after_refinement = result_repair
            
            # Aggiungi repair history al refinement_history
            if repair_history_after_refinement:
                for repair_info in repair_history_after_refinement:
                    repair_info["type"] = "repair_after_refinement"
                    repair_info["after_refinement_iteration"] = refinement_count
                refinement_history.extend(repair_history_after_refinement)
            
            # Verifica di nuovo dopo repair
            test_results_nuovi = esegui_test_originale(
                test_path_nuovi,
                project_path,
                target_class_path,
                nome_classe_solo,
                metodo_da_testare,
                config.nome_esperimento,
            )
            has_comp_errors_nuovi = test_results_nuovi.get(
                "has_compilation_errors", False
            )

        # Leggi nuovi test PRIMA di riattivare la suite originale (per evitare conflitti)
        print(f"\n=== READING NEW TESTS ===")
        codice_test_nuovi = leggi_codice_test_attuale(test_path_nuovi, False)

        # RIATTIVA test suite originale prima dell'unione
        print(f"\n=== RE-ENABLING ORIGINAL TEST SUITE ===")
        test_path_originale_riabilitato = None
        if success_disabilita:
            # Elimina il file temporaneo dei nuovi test per evitare conflitti con la riattivazione
            if os.path.exists(test_path_nuovi):
                try:
                    os.remove(test_path_nuovi)
                except Exception as e:
                    print(f"WARNING:  Impossible to delete temporary file: {e}")

            # Riabilita test suite originale
            test_path_originale_riabilitato, success_riabilita = riabilita_test_suite(
                test_path_originale_disabilitato
            )
            if success_riabilita:
                print(f" Original test suite re-enabled.")
            else:
                print(
                    f"WARNING:  Impossible to re-enable original test suite. Using original path."
                )
                test_path_originale_riabilitato = (
                    test_path_originale_disabilitato.replace(".disabled", ".java")
                )
        else:
            test_path_originale_riabilitato = test_path

        # Unisci nuovi test con test suite esistente
        print(f"\n=== MERGING NEW TESTS WITH EXISTING TEST SUITE ===")
        # Leggi test suite originale riabilitata
        codice_test_originale = leggi_codice_test_attuale(
            test_path_originale_riabilitato, False
        )
        if codice_test_originale:
            if codice_test_nuovi:
                # Estrai test validi dalla suite originale
                test_validi_nomi = test_results_originale.get("test_info", {}).get(
                    "valid_tests", []
                )
                if test_validi_nomi:
                    # Usa solo i test validi specificati
                    codice_test_validi = estrai_test_validi_completi(
                        codice_test_originale, test_validi_nomi
                    )
                    if codice_test_validi:
                        codice_test_finale = unisci_test_validi_e_rigenerati(
                            codice_test_validi, codice_test_nuovi, 
                            nomi_test_da_preservare=test_validi_nomi
                        )
                    else:
                        # Se non riesce a estrarre test validi, usa tutta la suite originale
                        print(
                            f"WARNING:  Impossible to extract valid tests. Merging the entire original suite with the new tests."
                        )
                        codice_test_finale = unisci_test_validi_e_rigenerati(
                            codice_test_originale, codice_test_nuovi,
                            nomi_test_da_preservare=test_validi_nomi
                        )
                else:
                    # Se non ci sono test validi specificati nei risultati correnti,
                    # prova a usare i test_validi iniziali (dalla prima esecuzione)
                    # per preservare i test che passavano inizialmente
                    print(
                        f"WARNING:  No valid tests specified. Merging the entire original suite with the new tests."
                    )
                    
                    # Prova a estrarre i test validi iniziali (prima del refinement)
                    initial_valid_tests = initial_test_results.get("test_info", {}).get(
                        "valid_tests", []
                    ) if initial_test_results else []
                    
                    if initial_valid_tests:
                        print(f"   Preserving {len(initial_valid_tests)} initial passing tests: {initial_valid_tests}")
                    
                    # Extract package target for manual fallback
                    package_target = None
                    try:
                        content = leggi_file(target_class_path)
                        import re
                        pm = re.search(r'package\s+([\w.]+);', content)
                        if pm: package_target = pm.group(1)
                    except: pass

                    codice_test_finale = unisci_test_validi_e_rigenerati(
                        codice_test_originale, codice_test_nuovi, package_target,
                        nomi_test_da_preservare=initial_valid_tests if initial_valid_tests else None
                    )

                # Salva test suite unificata
                test_dir = get_test_dir(target_class_path, project_path)
                nome_file = f"{nome_classe_test_attesa}.java"
                test_path = salva_file(test_dir, nome_file, codice_test_finale)
                print(f" Unified test suite saved.")
                
                # === VERIFICA COMPILAZIONE TEST SUITE UNIFICATA ===
                print(f"\n=== VERIFICATION OF THE UNIFIED TEST SUITE ===")
                test_results_unificata = esegui_test_originale(
                    test_path, project_path, target_class_path,
                    nome_classe_solo, metodo_da_testare, config.nome_esperimento
                )
                
                has_compilation_errors = test_results_unificata.get("has_compilation_errors", False)
                tests_total_unificata = test_results_unificata.get("tests_total", 0)
                
                # Se la suite unificata non compila, avvia repair loop
                if has_compilation_errors or tests_total_unificata == 0:
                    print(f"WARNING:  Unified test suite does not compile. Starting Repair Loop...")
                    print(f"Max retry repair: {config.max_retries_repair} (unified test suite)")
                    
                    # Chiama esegui_repair_loop con la signature corretta
                    test_path_repaired, repair_history_ignored = esegui_repair_loop(
                        test_path,
                        config,
                        provider_func,
                        model_name,
                        target_class_path,
                        nome_classe_solo,
                        metodo_da_testare,
                        project_path,
                        after_refinement=refinement_count,
                    )
                    
                    # BUG FIX 1: Controlla se il path restituito è valido, non la history
                    # (la history è vuota se compila subito, e [] è False in python)
                    if not test_path_repaired:
                        # Nessun test della suite unificata compila
                        # Preserva l'ULTIMA SUITE COMPILABILE invece della suite iniziale
                        print(f"WARNING:  No test in the unified suite compiles after repair.")
                        if ultima_suite_compilabile_code and ultima_suite_compilabile_path:
                            print(f"   Restoring the last compilable unified suite.")
                            with open(test_path_originale_riabilitato, 'w', encoding='utf-8') as f:
                                f.write(ultima_suite_compilabile_code)
                            test_path = test_path_originale_riabilitato
                        else:
                            print(f"   Preserving the initial test suite (no previous compilable suite).")
                            test_path = test_path_originale_riabilitato
                        # Continua con l'ultima suite compilabile
                        continue
                    
                    test_path = test_path_repaired
                else:
                    print(f" Unified test suite compiles. Tests executed: {tests_total_unificata}")
                    # UPDATE the last compilable unified suite
                    ultima_suite_compilabile_path = test_path
                    ultima_suite_compilabile_code = leggi_codice_test_attuale(test_path, False)
                    print(f"   Updated last compilable unified suite.")

                # Salva test suite in results/logs/test_suite
                from utils.prompt.prompt_logger import salva_test_suite
                
                # Rileggi il codice della test suite (potrebbe essere stato modificato dal repair)
                codice_test_finale = leggi_codice_test_attuale(test_path, False)
                test_output = test_results_originale.get("output", "")
                salva_test_suite(
                    codice_test_finale,
                    nome_classe_solo,
                    config.versione_esperimento,
                    refinement_count - 1,
                    config.nome_esperimento,
                    metodo_da_testare,
                    test_output,
                    model_name=model_name,
                )
            else:
                print(
                    f"WARNING:  Impossible to read new tests. Keeping original test suite."
                )
                test_path = test_path_originale_riabilitato
        else:
            print(
                f"WARNING:  Impossible to read original test suite. Keeping new tests."
            )
            # Salva nuovi test come test suite finale
            if codice_test_nuovi:
                test_dir = get_test_dir(target_class_path, project_path)
                nome_file = f"{nome_classe_test_attesa}.java"
                test_path = salva_file(test_dir, nome_file, codice_test_nuovi)
            else:
                test_path = test_path_originale_riabilitato

        # Esegui test suite completa su metodo originale (SOLO UNA VOLTA)
        print(f"\n=== EXECUTION OF THE COMPLETE TEST SUITE ON THE ORIGINAL METHOD ===")
        test_results_originale = esegui_test_originale(
            test_path,
            project_path,
            target_class_path,
            nome_classe_solo,
            metodo_da_testare,
            config.nome_esperimento,
        )
        
        line_coverage = test_results_originale.get('line_coverage', 0.0)
        if line_coverage < 100.0:
            report_path = find_jacoco_report(project_path)
            if report_path:
                try:
                    codice_classe_completo = leggi_file(target_class_path)
                    metodo_originale_code, riga_inizio_metodo = estrai_metodo_con_riga_inizio(codice_classe_completo, metodo_da_testare, config.metodo_signature)
                    if metodo_originale_code:
                        righe_metodo = metodo_originale_code.split('\n')
                        riga_fine_metodo = riga_inizio_metodo + len(righe_metodo) - 1
                        
                        coverage_class_name = get_coverage_class_name(target_class_path, project_path)
                        righe_non_coperte_updated = extract_uncovered_lines(
                            report_path, coverage_class_name, metodo_da_testare,
                            riga_inizio_metodo=riga_inizio_metodo,
                            riga_fine_metodo=riga_fine_metodo,
                            codice_classe_completo=codice_classe_completo
                        )
                        test_results_originale['uncovered_lines'] = righe_non_coperte_updated if righe_non_coperte_updated else []
                except Exception as e:
                    test_results_originale['uncovered_lines'] = []
        else:
            test_results_originale['uncovered_lines'] = []
        
        stampa_risultati_test(
            test_results_originale, "metodo originale (test suite completa)"
        )

        # Estrai informazioni per salvare anche se ci fermiamo dopo
        line_coverage = test_results_originale.get("line_coverage", 0.0)
        tests_passed = test_results_originale.get("tests_passed", 0)
        tests_total = test_results_originale.get("tests_total", 0)
        pass_rate = (tests_passed / tests_total * 100) if tests_total > 0 else 0.0
        branch_coverage_orig = test_results_originale.get("branch_coverage", 0.0)
        test_info_orig = test_results_originale.get("test_info", {})
        test_validi_orig = test_info_orig.get("valid_tests", [])
        test_non_validi_orig = test_info_orig.get("invalid_tests", [])
        test_falliti_assert_orig = test_info_orig.get("failed_assert_tests", [])
        test_errori_runtime_orig = test_info_orig.get("runtime_error_tests", [])
        
        # BUG FIX 4: Se eravamo in enhancement mode ma i nuovi test causano fallimenti,
        # usciamo dall'enhancement mode per permettere al prompt di focalizzarsi sui fix
        if similarity_info and similarity_info.get("is_enhancement_mode"):
            if pass_rate < 100.0:
                 print(f"   [DEBUG] Exiting Enhancement Mode: pass_rate {pass_rate:.2f}% < 100% (reset similarity_info)")
                 similarity_info = None

        # === OPTIMAL CONDITIONS: 100% coverage + all tests pass ===
        # (The loop will continue to do the regeneration and comparison, then stop)
        condizioni_ottimali_raggiunte = line_coverage >= 100.0 and tests_passed == tests_total and tests_total > 0
        if condizioni_ottimali_raggiunte:
            print(f"\n OPTIMAL CONDITIONS REACHED! (line_coverage: {line_coverage:.2f}%, pass_rate: {pass_rate:.2f}%).")

        # Log initial metrics (only first iteration) or detected improvement
        if config.max_retries_refinement == -1:
            if prev_line_coverage is None:
                print(f"\n Initial metrics: coverage={line_coverage:.2f}%, pass_rate={pass_rate:.2f}%")
            else:
                coverage_diff = line_coverage - prev_line_coverage
                pass_rate_diff = pass_rate - prev_pass_rate
                if coverage_diff > 0 or pass_rate_diff > 0:
                    print(f"\n Updated metrics: coverage={line_coverage:.2f}% (Δ{coverage_diff:+.2f}%), pass_rate={pass_rate:.2f}% (Δ{pass_rate_diff:+.2f}%)")

        # === VERIFICA SOGLIE MINIME PER RIGENERAZIONE ===
        from utils.test.test_utils import calcola_weighted_pass_rate
        weighted_pass_rate, wpr_dettagli = calcola_weighted_pass_rate(test_results_originale)
        line_coverage_norm = line_coverage / 100.0  # Normalizza a 0-1
        
        soglie_rispettate_refinement = (
            weighted_pass_rate >= config.soglia_min_weighted_pass_rate
            and line_coverage_norm >= config.soglia_min_coverage
        )
        
        print(f"\n=== VERIFICATION OF MINIMUM THRESHOLDS FOR REGENERATION (Refinement {refinement_count}) ===")
        print(f"   Weighted Pass Rate: {weighted_pass_rate:.2%} (threshold: {config.soglia_min_weighted_pass_rate:.0%})")
        print(f"   Line Coverage: {line_coverage_norm:.2%} (threshold: {config.soglia_min_coverage:.0%})")
        print(f"   Thresholds respected: {'YES' if soglie_rispettate_refinement else 'NO'}")
        
        # If thresholds are not met, skip regeneration (similarity cannot be calculated)
        if not soglie_rispettate_refinement:
            print(f"\n=== SKIP REGENERATION (thresholds not respected) ===")
            print(f"   Continuing refinement to improve test suite...")
            
            # === CHECK IMPROVEMENT WHEN THRESHOLDS ARE NOT MET ===
            # Use the SAME thresholds as the case with regeneration, without similarity
            if prev_line_coverage is not None and prev_pass_rate is not None:
                # Soglia adattiva per coverage (stessa logica del caso con rigenerazione)
                MIN_PASSRATE_IMPROV = 2.0
                def min_coverage_improvement(prev_cov):
                    if prev_cov < 60:   return 5.0
                    elif prev_cov < 85: return 3.0
                    elif prev_cov < 95: return 1.0
                    else:               return 0.5
                
                min_cov_improv = min_coverage_improvement(prev_line_coverage)
                coverage_improved = (line_coverage - prev_line_coverage) >= min_cov_improv
                passrate_improved = (pass_rate - prev_pass_rate) >= MIN_PASSRATE_IMPROV
                
                # CHECK TEST ASSOLUTI
                if prev_tests_passed is not None:
                    tests_passed_improved = tests_passed > prev_tests_passed
                    tests_passed_stable = tests_passed >= prev_tests_passed
                else:
                    tests_passed_improved = False
                    tests_passed_stable = True

                # QUALITY CHECK: almeno una migliora significativamente, l'altra non peggiora
                # (stessa logica del caso con rigenerazione, ma senza similarity)
                # Usa test assoluti invece di pass_rate %
                quality_ok = (
                    (coverage_improved and tests_passed_stable) or
                    (tests_passed_improved and line_coverage >= prev_line_coverage) or
                    (passrate_improved and line_coverage >= prev_line_coverage)
                )
                
                if not quality_ok:
                    reasons = []
                    if not coverage_improved and not passrate_improved:
                        reasons.append(f"no significant improvement (coverage +{line_coverage-prev_line_coverage:.2f}%, pass_rate +{pass_rate-prev_pass_rate:.2f}%)")
                    elif coverage_improved and pass_rate < prev_pass_rate:
                        reasons.append(f"coverage +{line_coverage-prev_line_coverage:.2f}% but pass_rate decreased ({prev_pass_rate:.2f}%→{pass_rate:.2f}%)")
                    elif passrate_improved and line_coverage < prev_line_coverage:
                        reasons.append(f"pass_rate +{pass_rate-prev_pass_rate:.2f}% but coverage decreased ({prev_line_coverage:.2f}%→{line_coverage:.2f}%)")
                    print(f"\nWARNING: Stop refinement (thresholds not respected) - {', '.join(reasons)}")
                    print(f"   Executing final method regeneration before terminating...")
                    # DO NOT break here - let the code continue to regenerate the method
                    # Instead, force regeneration by setting soglie_rispettate_refinement to True
                    soglie_rispettate_refinement = True  # Force final regeneration
                    force_stop_after_regeneration = True  # Flag to exit after regeneration
            
                    soglie_rispettate_refinement = True  # Forza rigenerazione finale
                    force_stop_after_regeneration = True  # Flag per uscire dopo la rigenerazione
            
            # BUG FIX 3: Aggiorna metriche per il prossimo confronto ANCHE qui
            prev_line_coverage = line_coverage
            prev_pass_rate = pass_rate
            prev_tests_passed = tests_passed  # Traccia test passati assoluti
            
            # Se le soglie sono ancora non rispettate (non forzate), continua il loop
            if not soglie_rispettate_refinement:
                refinement_info_partial = {
                    "refinement_iteration": refinement_count,
                    "overall_similarity": None,
                    "passes_threshold": False,
                    "thresholds_respected": False,
                    "weighted_pass_rate": weighted_pass_rate,
                    "original_test_results": {
                        "tests_passed": tests_passed,
                        "tests_total": tests_total,
                        "line_coverage": line_coverage,
                        "test_info": {
                            "valid_tests": test_validi_orig,
                            "failed_assert_tests": test_falliti_assert_orig,
                            "runtime_error_tests": test_errori_runtime_orig,
                        },
                    },
                }
                refinement_history.append(refinement_info_partial)
                continue  # Skip rigenerazione, vai al prossimo ciclo

        try:
            # Passa refinement_count - 1 come retry_count
            # Passa metodo_precedente_codice per iterative refinement SOLO se configurato
            print(f"\n=== METHOD REGENERATION (Refinement {refinement_count}) ===")
            
            # Usa metodo_precedente_codice solo se il flag è abilitato
            prev_method_to_pass = metodo_precedente_codice if config.includi_metodo_precedente else None
            
            metodo_rigenerato_codice, _, _ = esegui_fase2_func(
                config,
                provider_func,
                model_name,
                test_path,
                test_results_originale,
                None,
                retry_count=refinement_count - 1,
                metodo_originale_backup=metodo_originale_backup,
                precomputed_context=initial_context,
                metodo_precedente_codice=prev_method_to_pass,
            )
            
            if not metodo_rigenerato_codice:
                 raise ValueError("Method generation failed (no code)")

            # SAFE INJECTION for Refinement
            if injector:
                if not injector.inject_and_backup(metodo_rigenerato_codice, metodo_da_testare):
                     raise ValueError("Injection failed during refinement")
            
            metodo_path = target_class_path # Nominal path

        except Exception as e:
            print(f"WARNING:  Error in regeneration: {e}")
            # Salva l'iterazione anche in caso di errore
            refinement_info_partial = {
                "refinement_iteration": refinement_count,
                "overall_similarity": 0.0,
                "passes_threshold": False,
                "error": str(e),
                "original_test_results": {
                    "tests_passed": tests_passed,
                    "tests_total": tests_total,
                    "tests_failed": tests_total - tests_passed,
                    "line_coverage": line_coverage,
                    "branch_coverage": branch_coverage_orig,
                    "valid_tests": test_validi_orig,
                    "invalid_tests": test_non_validi_orig,
                    "failed_assert_tests": test_falliti_assert_orig,
                    "runtime_error_tests": test_errori_runtime_orig,
                },
            }
            refinement_history.append(refinement_info_partial)
            break

        # Esegui test su metodo rigenerato
        # Ora il metodo rigenerato è nella classe originale, usa esegui_test_originale
        print(f"\n=== EXECUTION OF THE TEST SUITE ON THE REGENERATED METHOD ===")

        test_results_rigenerato = esegui_test_originale(
            test_path,
            project_path,
            target_class_path,  # La classe originale contiene ora il metodo rigenerato
            nome_classe_solo,
            metodo_da_testare,
            config.nome_esperimento,
        )
        stampa_risultati_test(test_results_rigenerato, "metodo rigenerato")

        # Confronta similarità
        print(f"\n=== SIMILARITY CALCULATION ===")
        try:
            # USA IL BACKUP per il metodo originale (la classe ora contiene il metodo rigenerato)
            metodo_originale = metodo_originale_backup
            
            # Usa direttamente il codice rigenerato
            metodo_rigenerato = metodo_rigenerato_codice
            

            if metodo_originale and metodo_rigenerato:
                metriche_similarita = confronta_metodi(
                    metodo_originale,
                    metodo_rigenerato,
                    soglia_similarita_dinamica,
                    config.cb_use_stopwords,
                    similarity_weights=config.similarity_weights,
                )
                overall_similarity = metriche_similarita.get("overall_similarity", 0.0)
                passa_soglia = metriche_similarita.get("passes_threshold", False)

                print(
                    f"Similarity: {overall_similarity:.4f} ({'Dynamic' if config.usa_soglia_dinamica else 'Fixed'} threshold: {soglia_similarita_dinamica:.2f})"
                )
                
                # AGGIORNA similarity_info per il prossimo ciclo del loop
                # Così costruisci_refinement_prompt userà i valori corretti
                if line_coverage >= 100.0 and pass_rate >= 100.0 and not passa_soglia:
                    # Siamo in similarity enhancement mode (coverage ottimale ma similarità insufficiente)
                    similarity_info = {
                        "is_enhancement_mode": True,
                        "similarity_current": overall_similarity,
                        "similarity_threshold": soglia_similarita_dinamica,
                    }
                    # BUG FIX 3: Aggiorna metriche baseline quando entriamo in enhancement mode
                    # altrimenti il delta alla prossima iterazione sarà sbagliato
                    prev_line_coverage = line_coverage
                    prev_pass_rate = pass_rate
                    prev_tests_passed = tests_passed  # Traccia test passati assoluti
                else:
                    similarity_info = None  # Non siamo in enhancement mode
                
                test_info_orig = test_results_originale.get("test_info", {})
                test_validi_orig = test_info_orig.get("valid_tests", [])
                test_non_validi_orig = test_info_orig.get("invalid_tests", [])
                branch_coverage_orig = test_results_originale.get(
                    "branch_coverage", 0.0
                )

                # Estrai informazioni dettagliate sui test per metodo rigenerato
                tests_passed_regen = (
                    test_results_rigenerato.get("tests_passed", 0)
                    if test_results_rigenerato
                    else 0
                )
                tests_total_regen = (
                    test_results_rigenerato.get("tests_total", 0)
                    if test_results_rigenerato
                    else 0
                )
                tests_failed_regen = tests_total_regen - tests_passed_regen
                test_info_regen = (
                    test_results_rigenerato.get("test_info", {})
                    if test_results_rigenerato
                    else {}
                )
                test_validi_regen = test_info_regen.get("valid_tests", [])
                test_non_validi_regen = test_info_regen.get("invalid_tests", [])
                test_falliti_assert_regen = test_info_regen.get("failed_assert_tests", [])
                test_errori_runtime_regen = test_info_regen.get("runtime_error_tests", [])
                line_coverage_regen = (
                    test_results_rigenerato.get("line_coverage", 0.0)
                    if test_results_rigenerato
                    else 0.0
                )
                branch_coverage_regen = (
                    test_results_rigenerato.get("branch_coverage", 0.0)
                    if test_results_rigenerato
                    else 0.0
                )

                refinement_info = {
                    "refinement_iteration": refinement_count,
                    "overall_similarity": overall_similarity,
                    "passes_threshold": passa_soglia,
                    "similarity_metrics": metriche_similarita,
                    "original_method": metodo_originale,  # Per confronto HTML
                    "regenerated_method": metodo_rigenerato,  # Per confronto HTML
                    "original_test_results": {
                        "tests_passed": tests_passed,
                        "tests_total": tests_total,
                        "tests_failed": tests_total - tests_passed,
                        "line_coverage": line_coverage,
                        "branch_coverage": branch_coverage_orig,
                        "valid_tests": test_validi_orig,
                        "invalid_tests": test_non_validi_orig,
                        "failed_assert_tests": test_falliti_assert_orig,
                        "runtime_error_tests": test_errori_runtime_orig,
                    },
                    "regenerated_test_results": {
                        "tests_passed": tests_passed_regen,
                        "tests_total": tests_total_regen,
                        "tests_failed": tests_failed_regen,
                        "line_coverage": line_coverage_regen,
                        "branch_coverage": branch_coverage_regen,
                        "valid_tests": test_validi_regen,
                        "invalid_tests": test_non_validi_regen,
                        "failed_assert_tests": test_falliti_assert_regen,
                        "runtime_error_tests": test_errori_runtime_regen,
                    },
                }
                refinement_history.append(refinement_info)

                # Verifica condizioni di stop DOPO aver salvato i dati
                if passa_soglia:
                    print(f"\n Similarity reached! Stop refinement.")
                    break
                
                # Check per rigenerazione forzata (ultima iterazione quando soglie non rispettate)
                if force_stop_after_regeneration:
                    print(f"\n Final regeneration completed (thresholds not respected, no improvement). Stop refinement.")
                    break
                
                # Verifica condizioni ottimali sul metodo originale (flag impostato prima)
                # Nota: se passa_soglia=True, il break sopra è già stato eseguito
                # Quindi qui passa_soglia è sempre False
                if condizioni_ottimali_raggiunte:
                    # Condizioni ottimali ma similarità insufficiente → continua per migliorare similarità
                    print(f"\n Optimal conditions (coverage 100%, all tests pass) but similarity {overall_similarity:.4f} < {config.soglia_similarita} (below threshold)")
                    print(f"   Continuing refinement to improve similarity...")
                
                # Aggiorna codice per la prossima iterazione
                metodo_precedente_codice = metodo_rigenerato_codice

                # Verifica anche condizioni ottimali (100% coverage + tutti i test passano) sul metodo rigenerato
                # Nota: anche qui passa_soglia è sempre False (altrimenti break già eseguito sopra)
                if line_coverage_regen >= 100.0 and tests_passed_regen == tests_total_regen and tests_total_regen > 0:
                    print(f"\n Optimal conditions on the regenerated method but insufficient similarity. Continue...")
                
                # === CHECK IMPROVEMENT (only for max_retries_refinement == -1) ===
                # This check occurs AFTER regenerating the method and calculating similarity
                if config.max_retries_refinement == -1:
                    if prev_line_coverage is not None and prev_pass_rate is not None:
                        # === LOGICA REFINEMENT ===
                        SIM_TOL = 0.01      # Tolleranza 1% relativa per similarity
                        MIN_PASSRATE_IMPROV = 2.0   # Improvement minimo per pass_rate (%)
                        
                        # Soglia adattiva per coverage (rendimenti decrescenti)
                        def min_coverage_improvement(prev_cov):
                            if prev_cov < 60:
                                return 5.0   # Coverage bassa: richiedi +5%
                            elif prev_cov < 85:
                                return 3.0   # Coverage media: richiedi +3%
                            elif prev_cov < 95:
                                return 1.0   # Coverage alta: richiedi +1%
                            else:
                                return 0.5   # Coverage molto alta: richiedi +0.5%
                        
                        min_cov_improv = min_coverage_improvement(prev_line_coverage)
                        
                        # SIMILARITY CHECK: solo se il metodo è stato rigenerato in questa iterazione
                        # Se le soglie non erano rispettate, non abbiamo la similarità
                        if soglie_rispettate_refinement and prev_similarity is not None:
                            # 1. SIMILARITY GATE: tolleranza combinata (relativa OR assoluta)
                            REL_TOL = 0.02   # 2% tolleranza relativa
                            ABS_TOL = 0.01   # 0.01 tolleranza assoluta (1 punto)
                            
                            similarity_ok = (
                                overall_similarity >= prev_similarity * (1 - REL_TOL)
                                or
                                abs(prev_similarity - overall_similarity) <= ABS_TOL
                            )
                            
                            # Miglioramento significativo (non solo rumore) - stesse soglie simmetriche
                            sim_delta = overall_similarity - prev_similarity
                            similarity_improved = (
                                sim_delta >= ABS_TOL  # Almeno +0.01 assoluto
                                or
                                sim_delta >= prev_similarity * REL_TOL  # Almeno +2% relativo
                            )
                        else:
                            # Metodo non rigenerato in questa iterazione → ignora similarità
                            similarity_ok = True  # Non bloccare per similarità
                            similarity_improved = False  # Non conta come miglioramento
                            similarity_threshold = 0
                        
                        # 2. IMPROVEMENT CHECK con soglie minime
                        coverage_improved = (line_coverage - prev_line_coverage) >= min_cov_improv
                        passrate_improved = (pass_rate - prev_pass_rate) >= MIN_PASSRATE_IMPROV
                        
                        # 3. CHECK TEST ASSOLUTI: il numero di test passati non deve diminuire
                        # Questo è più robusto del pass_rate % quando si espande la suite
                        # Es: 1/1 (100%) → 11/14 (78%) = +10 test passati = miglioramento!
                        if prev_tests_passed is not None:
                            tests_passed_improved = tests_passed > prev_tests_passed
                            tests_passed_stable = tests_passed >= prev_tests_passed
                        else:
                            tests_passed_improved = False
                            tests_passed_stable = True  # Prima iterazione, non bloccare
                        
                        # 4. QUALITY CHECK: almeno una migliora significativamente, l'altra non peggiora
                        # Usa test assoluti invece di pass_rate % per evitare falsi positivi
                        # quando si espande da suite banale (1 test) a suite completa (14 test)
                        #
                        # CASO SPECIALE: Se eravamo in condizioni ottimali (100% pass rate) e la similarità
                        # migliora, accettiamo un calo del pass_rate purché i test passati non diminuiscano.
                        # Usa tests_passed_stable invece di pass_rate % per coerenza con la logica normale.
                        was_optimal = (prev_pass_rate >= 100.0)
                        
                        if was_optimal and similarity_improved and tests_passed_stable:
                            # Similarity enhancement mode: accetta calo pass_rate % se test assoluti non calano
                            quality_ok = (line_coverage >= prev_line_coverage)
                        else:
                            # Logica normale - USA TEST ASSOLUTI invece di pass_rate %
                            quality_ok = (
                                # Caso 1: coverage migliora E test passati non calano (assoluto)
                                (coverage_improved and tests_passed_stable) or
                                # Caso 2: test passati migliorano (assoluto) E coverage non cala
                                (tests_passed_improved and line_coverage >= prev_line_coverage) or
                                # Caso 3: pass rate % migliora E coverage non cala (legacy)
                                (passrate_improved and line_coverage >= prev_line_coverage) or
                                # Caso 4: similarity migliora E entrambe stabili
                                (similarity_improved and line_coverage >= prev_line_coverage and tests_passed_stable)
                            )
                        
                        # RISULTATO FINALE: similarity OK (se applicabile) E quality OK
                        has_improvement = similarity_ok and quality_ok
                        
                        print(f"   [DEBUG] Check Improvement:")
                        print(f"   - Coverage: {line_coverage:.2f}% vs {prev_line_coverage:.2f}% (min +{min_cov_improv:.1f}%) → improved={coverage_improved}")
                        print(f"   - Pass Rate: {pass_rate:.2f}% vs {prev_pass_rate:.2f}% (min +{MIN_PASSRATE_IMPROV:.1f}%) → improved={passrate_improved}")
                        prev_tests_str = str(prev_tests_passed) if prev_tests_passed is not None else "N/A"
                        print(f"   - Tests Passed: {tests_passed} vs {prev_tests_str} → stable={tests_passed_stable}, improved={tests_passed_improved}")
                        if soglie_rispettate_refinement:
                            # BUG FIX 2: Safely handle None values for similarity printing
                            curr_sim_str = f"{overall_similarity:.4f}" if overall_similarity is not None else "N/A"
                            prev_sim_str = f"{prev_similarity:.4f}" if prev_similarity is not None else "N/A"
                            print(f"   - Similarity: {curr_sim_str} vs {prev_sim_str} (tol 2% rel OR 0.01 abs) → ok={similarity_ok}, improved={similarity_improved}")
                        else:
                            print(f"   - Similarity: N/A (method not regenerated, thresholds not respected)")
                        print(f"   - Quality OK: {quality_ok} | Final: {has_improvement}")
                        
                        if not has_improvement:
                            reasons = []
                            if not similarity_ok:
                                reasons.append(f"similarity decreased too much ({prev_similarity:.4f}→{overall_similarity:.4f})")
                            if not quality_ok:
                                if not coverage_improved and not passrate_improved and not similarity_improved:
                                    reasons.append(f"no significant improvement (coverage +{line_coverage-prev_line_coverage:.2f}%, pass_rate +{pass_rate-prev_pass_rate:.2f}%)")
                                elif coverage_improved and pass_rate < prev_pass_rate:
                                    reasons.append(f"coverage +{line_coverage-prev_line_coverage:.2f}% but pass_rate decreased ({prev_pass_rate:.2f}%→{pass_rate:.2f}%)")
                                elif passrate_improved and line_coverage < prev_line_coverage:
                                    reasons.append(f"pass_rate +{pass_rate-prev_pass_rate:.2f}% but coverage decreased ({prev_line_coverage:.2f}%→{line_coverage:.2f}%)")
                            print(f"\nWARNING: Stop refinement - {', '.join(reasons)}")
                            break
                        else:
                            improved_metrics = []
                            if coverage_improved:
                                improved_metrics.append(f"coverage: {prev_line_coverage:.2f}%→{line_coverage:.2f}% (+{line_coverage-prev_line_coverage:.2f}%)")
                            if passrate_improved:
                                improved_metrics.append(f"pass_rate: {prev_pass_rate:.2f}%→{pass_rate:.2f}% (+{pass_rate-prev_pass_rate:.2f}%)")
                            if similarity_improved:
                                improved_metrics.append(f"similarity: {prev_similarity:.4f}→{overall_similarity:.4f}")
                            print(f"\n✓ Significant improvement! ({', '.join(improved_metrics)}). Continuing refinement.")
                    
                    # Update previous metrics for next iteration
                    # BUG FIX 3: Ensure prev_pass_rate is updated here too
                    prev_line_coverage = line_coverage
                    prev_pass_rate = pass_rate
                    prev_tests_passed = tests_passed  # Traccia test passati assoluti
                    if soglie_rispettate_refinement and overall_similarity is not None:
                         prev_similarity = overall_similarity  # Aggiorna solo se calcolata validamente
            
            if injector:
                injector.restore()
            else:
                print(f"WARNING:  Unable to extract methods.")
                # Salva iterazione anche se non riesco ad estrarre i metodi
                refinement_info_partial = {
                    "refinement_iteration": refinement_count,
                    "overall_similarity": 0.0,
                    "passes_threshold": False,
                    "error": "Impossibile estrarre metodi per confronto",
                    "original_test_results": {
                        "tests_passed": tests_passed,
                        "tests_total": tests_total,
                        "tests_failed": tests_total - tests_passed,
                        "line_coverage": line_coverage,
                        "branch_coverage": branch_coverage_orig,
                        "valid_tests": test_validi_orig,
                        "invalid_tests": test_non_validi_orig,
                        "failed_assert_tests": test_falliti_assert_orig,
                        "runtime_error_tests": test_errori_runtime_orig,
                    },
                }
                refinement_history.append(refinement_info_partial)
        except Exception as e:
            print(f"WARNING:  Error in similarity calculation: {e}")
            # Save iteration even in case of error
            refinement_info_partial = {
                "refinement_iteration": refinement_count,
                "overall_similarity": 0.0,
                "passes_threshold": False,
                "error": str(e),
                "original_test_results": {
                    "tests_passed": tests_passed,
                    "tests_total": tests_total,
                    "tests_failed": tests_total - tests_passed,
                    "line_coverage": line_coverage,
                    "branch_coverage": branch_coverage_orig,
                    "valid_tests": test_validi_orig,
                    "invalid_tests": test_non_validi_orig,
                    "failed_assert_tests": test_falliti_assert_orig,
                    "runtime_error_tests": test_errori_runtime_orig,
                },
            }
            refinement_history.append(refinement_info_partial)

        if config.max_retries_refinement != -1 and refinement_count >= config.max_retries_refinement:
            print(f"\nWARNING:  Reached max retry refinement.")
            
            # === FORCED REGENERATION IF NOT PERFORMED IN THIS ITERATION ===
            # Check if regeneration was performed (metodo_path would have been updated)
            if test_results_rigenerato is None and metodo_originale_backup:
                print(f"\n=== FORCED REGENERATION (max iterations reached) ===")
                print(f"   No regeneration performed. Performing final regeneration...")
                
                try:
                    metodo_rigenerato_codice_final, _, _ = esegui_fase2_func(
                        config,
                        provider_func,
                        model_name,
                        test_path,
                        test_results_originale,
                        None,
                        retry_count=refinement_count,
                        metodo_originale_backup=metodo_originale_backup,
                        precomputed_context=initial_context,
                    )
                    
                    if metodo_rigenerato_codice_final:
                        print(f" Method regenerated successfully!")
                        
                        if injector:
                            injector.inject_and_backup(metodo_rigenerato_codice_final, metodo_da_testare)
                        
                        print(f"\n=== EXECUTION OF THE TEST SUITE ON THE REGENERATED METHOD ===")
                        test_results_rigenerato = esegui_test_originale(
                            test_path,
                            project_path,
                            target_class_path,
                            nome_classe_solo,
                            metodo_da_testare,
                            config.nome_esperimento,
                        )
                        stampa_risultati_test(test_results_rigenerato, "metodo rigenerato")
                        
                        print(f"\n=== SIMILARITY CALCULATION ===")
                        if metodo_originale_backup and metodo_rigenerato_codice_final:
                            metriche_similarita = confronta_metodi(
                                metodo_originale_backup,
                                metodo_rigenerato_codice_final,
                                soglia_similarita_dinamica,
                                config.cb_use_stopwords,
                                similarity_weights=config.similarity_weights,
                            )
                            overall_similarity = metriche_similarita.get("overall_similarity", 0.0)
                            passa_soglia = metriche_similarita.get("passes_threshold", False)
                            
                            if config.usa_soglia_dinamica:
                                print(f"Similarity: {overall_similarity:.4f} (Dynamic threshold: {soglia_similarita_dinamica:.2f})")
                            else:
                                print(f"Similarity: {overall_similarity:.4f} (Fixed threshold: {soglia_similarita_dinamica:.2f})")
                            
                            test_info_orig = test_results_originale.get("test_info", {})

                            test_info_regen = test_results_rigenerato.get("test_info", {})
                            
                            refinement_info_final = {
                                "refinement_iteration": refinement_count,
                                "type": "forced_regeneration_max",
                                "overall_similarity": overall_similarity,
                                "passes_threshold": passa_soglia,
                                "similarity_metrics": metriche_similarita,
                                "original_method": metodo_originale_backup,
                                "regenerated_method": metodo_rigenerato_codice_final,
                                "original_test_results": {
                                    "tests_passed": test_results_originale.get("tests_passed", 0),
                                    "tests_total": test_results_originale.get("tests_total", 0),
                                    "line_coverage": test_results_originale.get("line_coverage", 0.0),
                                    "test_info": {
                                        "valid_tests": test_info_orig.get("valid_tests", []),
                                        "failed_assert_tests": test_info_orig.get("failed_assert_tests", []),
                                        "runtime_error_tests": test_info_orig.get("runtime_error_tests", []),
                                    },
                                },
                                "regenerated_test_results": {
                                    "tests_passed": test_results_rigenerato.get("tests_passed", 0),
                                    "tests_total": test_results_rigenerato.get("tests_total", 0),
                                    "line_coverage": test_results_rigenerato.get("line_coverage", 0.0),
                                    "test_info": {
                                        "valid_tests": test_info_regen.get("valid_tests", []),
                                        "failed_assert_tests": test_info_regen.get("failed_assert_tests", []),
                                        "runtime_error_tests": test_info_regen.get("runtime_error_tests", []),
                                    },
                                },
                            }
                            refinement_history.append(refinement_info_final)
                        
                        if injector:
                            injector.restore()
                    else:
                        print(f"WARNING:  Forced regeneration failed.")
                except Exception as e:
                    print(f"WARNING:  Error in forced regeneration: {e}")
            
            break

    return (
        test_results_originale,
        test_path,
        refinement_history,
        metodo_path,
        test_results_rigenerato,
    )


def costruisci_repair_prompt(codice_test: str, errori_compilazione: str, target_class_code: str = None, target_class_name: str = None) -> str:
    """Costruisce il prompt per il repair loop"""
    import re
    

    class_name_match = re.search(r'^\s*(?:public\s+)?class\s+(\w+)', codice_test, re.MULTILINE)
    test_class_name = class_name_match.group(1) if class_name_match else "TestClass"
    
    package_match = re.search(r'^package\s+[\w.]+;', codice_test, re.MULTILINE)
    package_line = package_match.group(0) if package_match else ""
    
    # === RILEVAMENTO TIPO PROGETTO DAL CODICE TEST ===
    has_servlet = 'javax.servlet' in codice_test or 'jakarta.servlet' in codice_test or 'MockHttpServlet' in codice_test
    has_spring = 'springframework' in codice_test or 'ReflectionTestUtils' in codice_test
    has_injection = '@Inject' in codice_test or '@Autowired' in codice_test or '@InjectMocks' in codice_test
    has_static_mock = 'mockStatic' in codice_test or 'MockedStatic' in codice_test
    
    # Rileva anche dagli errori di compilazione
    has_servlet_error = 'ServletOutputStream' in errori_compilazione or 'HttpServlet' in errori_compilazione
    has_private_error = 'private access' in errori_compilazione.lower()
    
    # Rileva se la classe target è astratta
    abstract_class_instructions = ""
    if target_class_code:
        try:
            from utils.code.code_analysis import is_abstract_class, generate_abstract_class_instantiation
            if is_abstract_class(target_class_code) and target_class_name:
                instantiation_tmpl = generate_abstract_class_instantiation(target_class_name, target_class_code)
                
                abstract_class_instructions = f"""
IMPORTANT: The target class "{target_class_name}" is ABSTRACT and cannot be instantiated directly.
- DO NOT use Mockito to mock the class itself (unless you need a partial mock).
- INSTANTIATE using this REAL ANONYMOUS SUBCLASS template (copy exactly):
```java
{instantiation_tmpl}
```
- DO NOT create custom concrete subclasses files.
- DO NOT invent classes that do not exist in the codebase.
"""
        except Exception:
            pass
    
    # === COSTRUISCI ISTRUZIONI CONDIZIONALI ===
    base_instructions = """- Fix ALL compilation errors
- Follow suggestions in error messages (e.g., "Use...", "Replace...")
- Fix missing imports for "cannot find symbol" errors
- IMPORTANT: All methods must have JUnit annotations (e.g., @Test, @BeforeEach, @AfterEach, etc.)
- DO NOT create inner classes, helper classes, or custom Driver/Mock implementations inside the test class
- DO NOT mock the target class
- When using enum types from dependencies, use ONLY values explicitly shown in the context. If enum values are not listed, use a mock instead of guessing enum constants."""
    
    conditional_instructions = []
    
    # Per Servlet
    if has_servlet or has_servlet_error:
        conditional_instructions.append("- DO NOT mock javax.servlet classes directly (ServletOutputStream, etc.) - use MockHttpServletRequest/Response from spring-test instead")
    
    # Per campi privati
    if has_injection or has_private_error or has_spring:
        conditional_instructions.append("""- For PRIVATE fields without public setters, use one of these approaches:
  * Spring's ReflectionTestUtils.setField(object, "fieldName", mockValue) - requires import org.springframework.test.util.ReflectionTestUtils
  * OR Mockito's @InjectMocks with @Mock annotations
- DO NOT try to access private fields directly (e.g., object.privateField = value) - this will NOT compile""")
    
    # Per static methods
    if has_static_mock:
        conditional_instructions.append("- For static method calls, ensure mockStatic is properly closed (use try-with-resources)")
    
    all_instructions = base_instructions
    if conditional_instructions:
        all_instructions += "\n" + "\n".join(conditional_instructions)
    
    all_instructions += f"""
- Keep the SAME class name: {test_class_name}
- Generate ONLY the test class, not the original class
- Provide ONLY Java code without explanations"""
    
    return f"""ROLE:
You are an expert Java developer. Task: fix compilation errors.

TEST CLASS INFO:
{f"Package: {package_line}" if package_line else ""}
Class name (MUST be exactly): {test_class_name}

CURRENT TEST SUITE (with errors):
{codice_test}

COMPILATION ERRORS:
{errori_compilazione}

INSTRUCTIONS:
{all_instructions}
{abstract_class_instructions}"""


def rimuovi_test_non_compilabili_loop(
    test_path: str,
    test_results: Dict,
    target_class_path: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    versione: str,
    project_path: str,
    nome_esperimento: str,
    max_iterations: int = 10,
) -> str:
    """
    Rimuove i test non compilabili in loop finché la test suite non compila.
    """
    from utils.test.test_executor import esegui_test_originale
    
    iteration = 0
    current_path = test_path
    current_results = test_results
    
    while iteration < max_iterations:
        iteration += 1
        
        # Verifica se la suite attuale compila
        has_compilation_errors = current_results.get("has_compilation_errors", False)
        tests_total = current_results.get("tests_total", 0)
        
        if not has_compilation_errors and tests_total > 0:
            print(f" Test suite compile after {iteration - 1} removal iterations.")
            return current_path
        
        if iteration == 1:
            print(f"\n=== ITERATIVE REMOVAL OF NON-COMPILABLE TESTS ===")
        
        print(f"\n--- Iteration {iteration}/{max_iterations} ---")
        
        # Rimuovi test non compilabili
        new_path = rimuovi_test_non_compilabili(
            current_path,
            current_results,
            target_class_path,
            nome_classe_solo,
            metodo_da_testare,
            versione,
            project_path,
        )
        
        # Se il path non è cambiato, potrebbero essere stati rimossi tutti i test
        # o la funzione ha creato una suite vuota - verifica se compila
        
        if new_path is None:
            print(f"WARNING:  Test suite discarded (structural error). Returning None.")
            return None
        
        # Recompile to verify
        print(f"   Verifying compilation after removal...")
        new_results = esegui_test_originale(
            new_path,
            project_path,
            target_class_path,
            nome_classe_solo,
            metodo_da_testare,
            nome_esperimento,
            timeout=120,
        )
        
        new_has_errors = new_results.get("has_compilation_errors", False)
        new_tests_total = new_results.get("tests_total", 0)
        
        if not new_has_errors and new_tests_total > 0:
            print(f" Test suite compile! Tests executed: {new_tests_total}")
            return new_path
        elif not new_has_errors and new_tests_total == 0:
            # Suite vuota ma compila (es. solo placeholderTest)
            print(f"WARNING:  Test suite empty but compiles.")
            return new_path
        else:
            # Ancora non compila, continua il loop
            tests_rimasti = len(estrai_nomi_test_da_codice(leggi_codice_test_attuale(new_path, False) or ""))
            print(f"   Still compilation errors. Tests remaining: {tests_rimasti}")
            current_path = new_path
            current_results = new_results
    
    print(f"WARNING:  Reached max iterations ({max_iterations}). Suite may not compile completely.")
    return current_path

def rimuovi_test_non_compilabili(
    test_path: str,
    test_results: Dict,
    target_class_path: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    versione: str,
    project_path: str,
) -> str:
    """
    Rimuove i test non compilabili dalla test suite dopo max retry repair.
    Mantiene TUTTI i test che compilano (passati + falliti), scarta solo quelli con errori di compilazione.
    
    Se tests_total == 0 (suite non compila), usa output Maven + javalang
    per identificare quali test hanno errori e rimuoverli.
    """
    print(f"\n=== REMOVAL OF NON-COMPILABLE TESTS ===")

    # Leggi codice test attuale
    codice_test_attuale = leggi_codice_test_attuale(test_path, False)
    if codice_test_attuale is None:
        print(f"WARNING:  Unable to read test suite.")
        return test_path

    # Estrai nomi test che compilano (se ci sono test eseguiti)
    tests_total = test_results.get("tests_total", 0)
    has_compilation_errors = test_results.get("has_compilation_errors", False)


    if tests_total == 0 and has_compilation_errors:
        print(f"Suite do not compile. Analyzing errors...")
        
        test_output = test_results.get("output", "")
        test_con_errori = estrai_test_con_errori_compilazione(
            test_output, codice_test_attuale, nome_classe_solo
        )
        
        if test_con_errori:
            if "__STRUCTURAL_ERROR__" in test_con_errori:
                print(f"WARNING:  Structural error in test suite (missing imports/fields).")
                print(f"   The test suite will be discarded and the last compilable one will be used.")
                return None  # Segnala di scartare questa suite
            
            print(f"   Test with compilation errors: {test_con_errori}")
            
            # Estrai tutti i nomi dei test dal codice
            tutti_test = estrai_nomi_test_da_codice(codice_test_attuale)
            print(f"   All tests in code: {tutti_test}")
            
            # Calcola test compilabili (tutti - quelli con errori)
            test_compilabili = [t for t in tutti_test if t not in test_con_errori]
            
            if test_compilabili:

                import re
                
                codice_modificato = codice_test_attuale
                test_rimossi = 0
                
                for nome_test in test_con_errori:
                    # Trova e rimuovi il metodo @Test con errori
                    # Pattern per trovare @Test seguito dal metodo
                    # Gestisce anche sintassi JUnit 4: @Test(expected=...)
                    patterns = [
                        # Pattern 1: @Test(expected=...) con annotazioni opzionali e throws (JUnit 4 syntax)
                        rf'(\s*)@Test\s*\([^)]*\)\s*(?:@[^\n]*\n\s*)*(?:public\s+)?void\s+{re.escape(nome_test)}\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{{',
                        # Pattern 2: @Test con annotazioni opzionali e throws (JUnit 5 normal)
                        rf'(\s*)@Test\s*(?:@[^\n]*\n\s*)*(?:public\s+)?void\s+{re.escape(nome_test)}\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{{',
                        # Pattern 3: @Test semplice senza throws
                        rf'(\s*)@Test\s*\n\s*(?:public\s+)?void\s+{re.escape(nome_test)}\s*\([^)]*\)\s*\{{',
                        # Pattern 4: @Test inline
                        rf'(\s*)@Test\s+(?:public\s+)?void\s+{re.escape(nome_test)}\s*\([^)]*\)\s*\{{',
                    ]
                    
                    for pattern in patterns:
                        match = re.search(pattern, codice_modificato, re.MULTILINE | re.DOTALL)
                        if match:
                            # Trova la fine del metodo contando le parentesi graffe
                            start_pos = match.start()
                            brace_count = 0
                            in_method = False
                            end_pos = len(codice_modificato)
                            
                            # Salta stringhe per non contare { } dentro stringhe
                            i = match.end() - 1  # Inizia dalla { trovata nel pattern
                            in_string = False
                            string_char = None
                            
                            while i < len(codice_modificato):
                                char = codice_modificato[i]
                                
                                # Gestisci stringhe
                                if not in_string:
                                    if char in ('"', "'"):
                                        in_string = True
                                        string_char = char
                                else:
                                    if char == '\\' and i + 1 < len(codice_modificato):
                                        i += 2
                                        continue
                                    if char == string_char:
                                        in_string = False
                                        string_char = None
                                    i += 1
                                    continue
                                
                                # Conta parentesi graffe
                                if char == '{':
                                    brace_count += 1
                                    in_method = True
                                elif char == '}':
                                    brace_count -= 1
                                    if in_method and brace_count == 0:
                                        end_pos = i + 1
                                        break
                                
                                i += 1
                            
                            # Rimuovi il metodo trovato
                            codice_modificato = codice_modificato[:start_pos] + codice_modificato[end_pos:]
                            test_rimossi += 1
                            break
                
                if test_rimossi > 0:
                    # Salva il codice modificato
                    test_dir = get_test_dir(target_class_path, project_path)
                    versione_suffix = versione if versione else ""
                    nome_file = f"{nome_classe_solo}_{metodo_da_testare}TestGenerated{versione_suffix}.java"
                    test_path = salva_file(test_dir, nome_file, codice_modificato)
                    print(f" Removed {test_rimossi} tests with errors. Kept {len(test_compilabili)} tests + imports/fields/helpers.")
                    return test_path
                else:
                    print(f"   WARNING:  No tests removed. Pattern did not find the tests.")
                    return None
            else:
                print(f"WARNING:  All tests have compilation errors.")
                print(f"   The test suite will be discarded and the last compilable one will be used.")
                return None  # Segnala di scartare questa suite
        else:
            print(f"WARNING:  Unable to identify tests with errors from Maven output.")
            print(f"   The test suite will be discarded and the last compilable one will be used.")
            return None  # Segnala di scartare questa suite

    elif tests_total > 0:
        # Logica originale: ci sono test che compilano ed eseguono
        test_info = test_results.get("test_info", {})
        
        # IMPORTANTE: Mantiene TUTTI i test eseguiti (passati + falliti)
        # Entrambi compilano, la differenza è solo nel risultato dell'esecuzione
        test_validi = test_info.get("valid_tests", [])  # Test passati
        test_non_validi = test_info.get("invalid_tests", [])  # Test falliti ma che COMPILANO
        
        # Combina tutti i test che compilano
        tutti_test_compilabili = list(set(test_validi + test_non_validi))
        
        if tutti_test_compilabili:
            # Mantieni tutti i test che compilano (passati + falliti)
            codice_test_compilabili = ""
            for nome_test in tutti_test_compilabili:
                codice_test = estrai_codice_test_per_nome(
                    codice_test_attuale, nome_test
                )
                if codice_test:
                    codice_test_compilabili += codice_test + "\n\n"

            if codice_test_compilabili:
                # Mantieni package e imports
                import re

                package_match = re.search(
                    r"^package\s+[\w.]+;", codice_test_attuale, re.MULTILINE
                )
                imports_match = re.findall(
                    r"^import\s+[\w.*]+;", codice_test_attuale, re.MULTILINE
                )

                codice_finale = ""
                if package_match:
                    codice_finale += package_match.group(0) + "\n\n"
                if imports_match:
                    codice_finale += "\n".join(imports_match) + "\n\n"

                # Estrai dichiarazione classe - pattern migliorato
                class_match = re.search(
                    r"(?:public\s+)?class\s+(\w+)", codice_test_attuale, re.MULTILINE
                )
                if not class_match:
                    class_match = re.search(r"class\s+(\w+)", codice_test_attuale)
                if class_match:
                    nome_classe = class_match.group(1)
                    codice_finale += f"public class {nome_classe} {{\n\n"
                    codice_finale += codice_test_compilabili
                    codice_finale += "}\n"

                    # Salva
                    test_dir = get_test_dir(target_class_path, project_path)
                    versione_suffix = versione if versione else ""
                    nome_file = f"{nome_classe_solo}_{metodo_da_testare}TestGenerated{versione_suffix}.java"
                    test_path = salva_file(test_dir, nome_file, codice_finale)
                    print(f" Kept {len(tutti_test_compilabili)} tests that compile ({len(test_validi)} passed, {len(test_non_validi)} failed).")
                    return test_path

    # NESSUN TEST COMPILABILE: restituisci None per scartare la suite e usare l'ultima compilabile
    print(f"WARNING:  No compilable tests found or extracted.")
    print(f"   The test suite will be discarded and the last compilable one will be used.")
    return None



