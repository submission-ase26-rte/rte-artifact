from typing import Dict, Optional, List
from utils.prompt.prompt_helpers import costruisci_refinement_prompt
from utils.test.test_utils import estrai_metodo_con_riga_inizio
from utils.code.error_extractor import estrai_errori_compilazione
from utils.test.test_runner_with_coverage import find_jacoco_report, extract_uncovered_lines
from utils.test.test_utils import get_coverage_class_name
from core.evaluation.evaluation import estrai_metodo_singolo


def prepara_refinement_prompt(
    codice_test_attuale: str,
    test_results_originale: Dict,
    target_class_path: str,
    metodo_da_testare: str,
    soglia_coverage: float,
    project_path: str,
    nome_classe_solo: str,
    metodo_originale_backup: str = None,  # Backup del metodo originale (la classe ora contiene il rigenerato)
    metodo_signature: str = None,  # Signature completa per distinguere overload
    context_description: str = None, # Contesto precalcolato da fase 1
    similarity_info: Dict = None  # Info similarità per similarity enhancement mode
) -> tuple[str, Optional[str], Optional[List[int]]]:
    """
    Prepara il prompt di refinement con tutti i dati necessari.
    """
    # Estrai errori
    errori_compilazione = None
    has_compilation_errors = test_results_originale.get('has_compilation_errors', False)
    tests_failed = test_results_originale.get('tests_total', 0) > 0 and test_results_originale.get('tests_passed', 0) < test_results_originale.get('tests_total', 0)
    
    if has_compilation_errors or tests_failed:
        # Tenta di usare gli errori già estratti da test_executor se disponibili
        errori_compilazione = test_results_originale.get("extracted_errors")
        
        # Fallback
        if not errori_compilazione:
            test_output = test_results_originale.get('output', '')
            errori_compilazione = estrai_errori_compilazione(test_output)
    
    # Usa il backup del metodo originale se disponibile
    # (la classe originale ora contiene il metodo rigenerato)
    riga_inizio_metodo = 1
    riga_fine_metodo = None
    codice_classe_completo = None
    
    # Leggi la classe per il contesto (anche se contiene il metodo rigenerato)
    try:
        with open(target_class_path, 'r', encoding='utf-8') as f:
            codice_classe_completo = f.read()
    except Exception as e:
        print(f"   WARNING:  Error reading class: {e}")
    
    # Se abbiamo il backup del metodo originale, usalo (include già JavaDoc)
    if metodo_originale_backup:
        metodo_originale = metodo_originale_backup
        if metodo_originale:
            # Calcola riga_inizio_metodo cercando il metodo nella classe originale
            # per ottenere la riga corretta (inclusa JavaDoc se presente)
            try:
               
                _, riga_inizio_metodo_calc = estrai_metodo_con_riga_inizio(codice_classe_completo, metodo_da_testare, signature=metodo_signature)
                if riga_inizio_metodo_calc and riga_inizio_metodo_calc > 0:
                    riga_inizio_metodo = riga_inizio_metodo_calc
                    
              
                metodo_lines = metodo_originale.split('\n')
                righe_prima_signature = 0
                for line in metodo_lines:
                    stripped = line.strip()
                    # JavaDoc e annotazioni precedono la signature
                    if stripped.startswith('/**') or stripped.startswith('*') or stripped.endswith('*/'):
                        righe_prima_signature += 1
                    elif stripped.startswith('@'):
                        righe_prima_signature += 1
                    elif stripped == '':
                        righe_prima_signature += 1
                    else:
                        # Prima riga non-JavaDoc/annotazione = signature del metodo
                        break
                
               
                if righe_prima_signature > 0:
                    riga_inizio_metodo = riga_inizio_metodo - righe_prima_signature
            except Exception:
                pass
            righe_metodo = metodo_originale.split('\n')
            riga_fine_metodo = riga_inizio_metodo + len(righe_metodo) - 1
    else:
        # Fallback: estrai dalla classe con JavaDoc (ma sarà il metodo rigenerato, non l'originale!)
        metodo_originale = None
        try:
            # Usa estrai_metodo_singolo con include_javadoc=True per includere JavaDoc
            metodo_originale = estrai_metodo_singolo(codice_classe_completo, metodo_da_testare, include_javadoc=True, signature=metodo_signature)
            if metodo_originale:
                # Prova a ottenere la riga di inizio
                try:
                    _, riga_inizio_metodo = estrai_metodo_con_riga_inizio(codice_classe_completo, metodo_da_testare, signature=metodo_signature)
                except Exception:
                    pass
                righe_metodo = metodo_originale.split('\n')
                riga_fine_metodo = riga_inizio_metodo + len(righe_metodo) - 1
        except Exception as e:
            print(f"   WARNING:  Error extracting method: {e}")
            try:
                metodo_originale = estrai_metodo_singolo(codice_classe_completo, metodo_da_testare, include_javadoc=True, signature=metodo_signature)
                if metodo_originale:
                    riga_fine_metodo = riga_inizio_metodo + len(metodo_originale.split('\n')) - 1
            except Exception:
                pass
    
    # Estrai righe non coperte
   
    righe_non_coperte = test_results_originale.get('uncovered_lines', None)
    
    if righe_non_coperte is None:
        righe_non_coperte = None
        line_coverage = test_results_originale.get('line_coverage', 0.0)
        
        if line_coverage < 100.0:
            report_path = find_jacoco_report(project_path)
            if report_path:
                coverage_class_name = get_coverage_class_name(target_class_path, project_path)
                righe_non_coperte = extract_uncovered_lines(
                    report_path, coverage_class_name, metodo_da_testare,
                    riga_inizio_metodo=riga_inizio_metodo if riga_inizio_metodo else None,
                    riga_fine_metodo=riga_fine_metodo if riga_fine_metodo else None,
                    codice_classe_completo=codice_classe_completo
                )

    # Estrai eccezioni dichiarate dal metodo target e costruisci contesto
    exception_context = None
    try:
        from core.analysis.dependency_analyzer import JavaDependencyAnalyzer
        import os
        
        if target_class_path and os.path.exists(target_class_path) and project_path:
            analyzer = JavaDependencyAnalyzer(project_path)
            eccezioni = analyzer.estrai_eccezioni_metodo(codice_classe_completo, metodo_da_testare, metodo_signature)
            
            if eccezioni:
                exception_parts = []
                java_files = analyzer.find_java_files()
                
                for exception_name in eccezioni:
                    exception_info = analyzer.estrai_contesto_eccezione(exception_name, java_files)
                    
                    if exception_info and exception_info.get('costruttori'):
                        exception_parts.append(f"\n// === Exception Class: {exception_name} ===")
                        exception_parts.append(f"// NOTE: The target method throws this exception.")
                        exception_parts.append("// Constructors:")
                        for costruttore in exception_info['costruttori']:
                            exception_parts.append(costruttore)
                        # Aggiungi esempio di come gestire l'eccezione nei test
                        exception_parts.append(f"\n")
                        exception_parts.append(f"EXCEPTION HANDLING IN TESTS - Use ONE of these patterns:")
                        exception_parts.append(f"Pattern 1 (for tests that EXPECT the exception to be thrown):")
                        exception_parts.append(f"```java")
                        exception_parts.append(f"@Test")
                        exception_parts.append(f"void testMethodThrows{exception_name}() {{")
                        exception_parts.append(f"assertThrows({exception_name}.class, () -> targetMethod(...));")
                        exception_parts.append(f"}}")
                        exception_parts.append(f"```")
                        exception_parts.append(f"\n")
                        exception_parts.append(f"Pattern 2 (for tests where exception should NOT occur):")
                        exception_parts.append(f"```java")
                        exception_parts.append(f"@Test")
                        exception_parts.append(f"void testMethodSucceeds() throws {exception_name} {{")
                        exception_parts.append(f"// Call method directly - exception propagates if thrown")
                        exception_parts.append(f"result = targetMethod(...);")
                        exception_parts.append(f"assertNotNull(result);")
                        exception_parts.append(f"}}")
                        exception_parts.append(f"```")

                
                if exception_parts:
                    exception_context = "\n".join(exception_parts)
    except Exception as e:
        print(f"   WARNING:  Error extracting exceptions for refinement: {e}")

    # Costruisci refinement prompt
    refinement_prompt = costruisci_refinement_prompt(
        codice_test_attuale=codice_test_attuale,
        errori_compilazione=errori_compilazione,
        righe_non_coperte=righe_non_coperte,
        line_coverage=test_results_originale.get('line_coverage', 0.0),
        soglia_coverage=soglia_coverage,
        test_results_originale=test_results_originale,
        metodo_originale=metodo_originale,
        riga_inizio_metodo=riga_inizio_metodo,
        codice_classe_completo=codice_classe_completo,
        target_class_name=nome_classe_solo,  # Per istruzioni classi astratte
        context_description=context_description,
        exception_context=exception_context,
        similarity_info=similarity_info  # Per similarity enhancement mode
    )
    
    return refinement_prompt, errori_compilazione, righe_non_coperte

