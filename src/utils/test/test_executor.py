import os
import shutil
import time
import re
from datetime import datetime
from typing import Dict, Optional, Tuple
import xml.etree.ElementTree as ET
from utils.test.test_runner_with_coverage import (
    find_pom_file, backup_pom, restore_pom, add_jacoco_to_pom, add_surefire_to_pom,
    run_tests_with_coverage, extract_method_name_from_test,
    parse_jacoco_report_for_method, find_jacoco_report
)
from utils.test.test_utils import get_coverage_class_name, popola_test_info
from utils.text.output_utils import remove_ansi_colors, extract_failed_test_name
from utils.code.error_extractor import estrai_errori_compilazione
from config import LOGS_DIR


def _salva_maven_output(test_output: str, test_class: str, nome_esperimento: str = None, fase: str = "test"):
    """Salva l'output di Maven in un file di log nella directory results/logs/maven."""
    try:
        maven_log_dir = os.path.join(LOGS_DIR, "maven")
        
        if nome_esperimento:
            maven_log_dir = os.path.join(maven_log_dir, nome_esperimento)
        
        os.makedirs(maven_log_dir, exist_ok=True)
        
        # Genera nome file con timestamp
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        simple_class_name = test_class.split('.')[-1] if '.' in test_class else test_class
        log_filename = f"maven_{fase}_{simple_class_name}_{timestamp}.log"
        log_path = os.path.join(maven_log_dir, log_filename)
        
        with open(log_path, 'w', encoding='utf-8', errors='replace') as f:
            f.write(f"=== MAVEN OUTPUT LOG ===\n")
            f.write(f"Test Class: {test_class}\n")
            f.write(f"Experiment: {nome_esperimento or 'N/A'}\n")
            f.write(f"Phase: {fase}\n")
            f.write(f"Timestamp: {datetime.now().isoformat()}\n")
            f.write(f"=" * 60 + "\n\n")
            f.write(test_output)
        
        return log_path
    except Exception as e:
        print(f"   [Error saving Maven log: {e}]")
        return None


def _has_compilation_errors(test_output: str, test_class_name: str = None) -> bool:
    """
    Verifica se ci sono errori di compilazione nell'output.
    """
    if not test_output:
        return False
    
    # Rimuovi colori per check affidabile
    test_output_lower = remove_ansi_colors(test_output).lower()
    
    # Se è specificato il nome della classe, verifica che gli errori siano relativi a quella classe
    # Questo evita falsi positivi quando ci sono errori in altre classi
    if test_class_name:
        simple_class_name = test_class_name.split('.')[-1]
        # Rimuovi suffissi temporanei come _LLMCheck se presenti
        if '_LLMCheck' in simple_class_name:
            simple_class_name = simple_class_name.replace('_LLMCheck', '')
        
        # Verifica se ci sono errori specifici per questa classe
        class_error_pattern = rf'\[error\].*{re.escape(simple_class_name)}.*\.java.*cannot\s+find\s+symbol'
        if re.search(class_error_pattern, test_output_lower):
            return True
        
        # Se non ci sono errori specifici per questa classe, verifica se i test sono stati eseguiti
        # Se i test sono stati eseguiti, la classe compila correttamente (anche se ci sono errori in altre classi)
        if 'tests run:' in test_output_lower:
            # Estrai il numero di test eseguiti
            tests_run_match = re.search(r'tests\s+run:\s*(\d+)', test_output_lower)
            if tests_run_match:
                tests_run_count = int(tests_run_match.group(1))
                if tests_run_count > 0:
                    # Se ci sono test eseguiti, la classe compila correttamente
                    return False
    
    compilation_error_patterns = [
        r'compilation\s+error',
        r'cannot\s+find\s+symbol',
        r'package\s+\w+\s+does\s+not\s+exist',
        r'cannot\s+resolve\s+symbol',
        r'symbol\s+not\s+found',
        r'error:\s+cannot\s+find\s+symbol',
        r'error:\s+package\s+\w+\s+does\s+not\s+exist',
        r'error:\s+cannot\s+resolve',
        r'\[error\].*compilation',
        r'build\s+failure',
        r'syntax\s+error',
        r'expected\s+.*but\s+found',
    ]
    
    for pattern in compilation_error_patterns:
        if re.search(pattern, test_output_lower):
            # Eccezione: BUILD FAILURE può capitare anche solo per test falliti.
            # Se è BUILD FAILURE, controlliamo se ci sono indicatori specifici di errore Java
            if 'build failure' in pattern:
                # Se ci sono test eseguiti, probabilmente non è un errore di compilazione
                if 'tests run:' in test_output_lower:
                    # Verifica se ci sono errori di compilazione specifici
                    java_compilation_errors = [
                        r'cannot\s+find\s+symbol',
                        r'package\s+\w+\s+does\s+not\s+exist',
                        r'cannot\s+resolve\s+symbol',
                        r'error:\s+cannot\s+find',
                        r'compilation\s+error',
                        r'syntax\s+error',
                        r'expected\s+.*but\s+found',
                    ]
                    has_java_error = any(re.search(p, test_output_lower) for p in java_compilation_errors)
                    if not has_java_error:
                        continue  # Probabilmente solo test falliti, non errore di compilazione
        return True
    
    return False


def _parse_test_results(test_output: str) -> Tuple[int, int, list, list, list]:
    """
    Parsa l'output di Maven per estrarre statistiche e nomi dei test falliti.
    Gestisce output colorati (ANSI) e formati multipli.
    """
    # 1. Pulisci output
    clean_output = remove_ansi_colors(test_output)
    lines = clean_output.split('\n')
    
    tests_passed = 0
    tests_total = 0
    tests_failures = 0
    tests_errors = 0
    tests_skipped = 0
    failed_test_names = []
    
    # Regex flessibili
    test_summary_patterns = [
        r'Tests run:\s*(\d+),\s*Failures:\s*(\d+),\s*Errors:\s*(\d+),\s*Skipped:\s*(\d+)',
        r'Tests run:\s*(\d+),\s*Failures:\s*(\d+),\s*Errors:\s*(\d+)',
    ]
    
    # 2. Trova riepilogo (usa l'ultimo trovato valido)
    # Cerca anche pattern alternativi come "[ERROR] Tests run:" che possono apparire quando ci sono test falliti
    for line in lines:
        line_stripped = line.strip()
        # Cerca pattern standard e pattern con [ERROR] prefix
        if "Tests run:" in line_stripped or "[ERROR] Tests run:" in line_stripped:
            # Rimuovi [ERROR] prefix se presente per il matching
            line_for_matching = line_stripped.replace("[ERROR]", "").strip()
            for pattern in test_summary_patterns:
                match = re.search(pattern, line_for_matching)
                if match:
                    tests_total = int(match.group(1))
                    tests_failures = int(match.group(2))
                    tests_errors = int(match.group(3))
                    if len(match.groups()) > 3:
                        tests_skipped = int(match.group(4))
                    else:
                        tests_skipped = 0
                    break  # Usa il primo match valido

    tests_passed = tests_total - tests_failures - tests_errors - tests_skipped

    # 3. Trova nomi test falliti e con errori separatamente
    failed_test_names = []  # Test con assert falliti (FAILURE)
    error_test_names = []   # Test con errori runtime (ERROR)
    
    for i, line in enumerate(lines):
        if "<<< FAILURE!" in line:
            test_name = extract_failed_test_name(lines, i)
            # Aggiungi solo se trovato e non già presente
            if test_name and test_name not in failed_test_names:
                failed_test_names.append(test_name)
        elif "<<< ERROR!" in line:
            test_name = extract_failed_test_name(lines, i)
            # Aggiungi solo se trovato e non già presente
            if test_name and test_name not in error_test_names:
                error_test_names.append(test_name)
    
    # Lista combinata per retrocompatibilità
    all_failed_test_names = failed_test_names + error_test_names
                
    return tests_passed, tests_total, all_failed_test_names, failed_test_names, error_test_names


def estrai_nome_classe_test(test_file_path: str) -> Optional[str]:
    if not os.path.exists(test_file_path):
        return None
    try:
        with open(test_file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        # 1. Cerca 'public class Name' (più specifico e sicuro)
        match = re.search(r'public\s+class\s+(\w+)', content)
        if match: return match.group(1)
        
        # 2. Cerca 'class Name' ma con controlli più stretti per evitare commenti
        match_strict = re.search(r'class\s+(\w+)\s*(?:extends\s+[\w\.]+\s*)?(?:implements\s+[\w\.,\s]+\s*)?\{', content)
        if match_strict: return match_strict.group(1)
        
        # 3. Fallback: cerca 'class Name' generico ma prova a escludere keyword comuni se fosse un commento
        match = re.search(r'class\s+(\w+)', content)
        if match:
            candidate = match.group(1)
            # Ignora se la "class_name" estratta è una stopword comune nei commenti
            if candidate.lower() not in ['for', 'of', 'to', 'in', 'and', 'with', 'the', 'a']:
                return candidate
    except Exception:
        pass
    basename = os.path.basename(test_file_path).replace('.java', '')
    return basename


def esegui_test_e_ottieni_risultati(project_path: str, test_class: str, target_class_name: str = None, 
                                    target_method_name: str = None, save_report_backup: bool = False, 
                                    skip_clean: bool = False, nome_esperimento: str = None,
                                    check_compilation_for_class: str = None, timeout: int = 60) -> Dict:
    pom_path = find_pom_file(project_path)
    if not pom_path:
        return {'success': False, 'error': 'pom.xml non trovato', 'tests_passed': 0, 'tests_total': 0, 'line_coverage': 0.0, 'branch_coverage': 0.0}
    
    # Normalizzazione nome classe test per Maven
    if test_class.endswith(".java"): test_class = test_class[:-5]
    if "/" in test_class or "\\" in test_class:
        test_class = test_class.replace("/", ".").replace("\\", ".")
        if "src.test.java." in test_class:
            test_class = test_class.split("src.test.java.")[1]

    backup_path = None
    pom_modified = False
    formatted_errors = None
    try:
        backup_path = backup_pom(pom_path)
        pom_modified_jacoco = add_jacoco_to_pom(pom_path)
        pom_modified_surefire = add_surefire_to_pom(pom_path)
        pom_modified = pom_modified_jacoco or pom_modified_surefire
        
        # Esecuzione Maven (include compilazione, test e -e per errori dettagliati)
        return_code, test_output = run_tests_with_coverage(project_path, test_class, skip_clean=skip_clean, timeout=timeout)
        
        # Salva l'output di Maven in un file di log
        _salva_maven_output(test_output, test_class, nome_esperimento, fase="test")
        
        # Parsing risultati (usa la versione robusta con pulizia ANSI)
        tests_passed, tests_total, all_failed_test_names, failed_test_names, error_test_names = _parse_test_results(test_output)
        
        # Debug: se tests_total è 0 ma ci sono test falliti rilevati, potrebbe essere un problema di parsing
        if tests_total == 0 and len(all_failed_test_names) > 0:
            # Prova a ricalcolare tests_total basandosi sui test falliti rilevati
            # Se ci sono test falliti, significa che almeno quei test sono stati eseguiti
            tests_total = len(all_failed_test_names)
            tests_passed = 0  # Se tutti i test rilevati sono falliti, assumiamo 0 passati
        
        # Gestione Coverage
        coverage_data = None
        # Estrai coverage se ci sono test eseguiti O se ci sono test falliti rilevati (potrebbero essere stati eseguiti ma non parsati correttamente)
        has_detected_failures = len(all_failed_test_names) > 0
        if tests_total > 0 or has_detected_failures:
            report_path = None
            for attempt in range(5):
                time.sleep(1 + attempt)
                report_path = find_jacoco_report(project_path)
                if report_path: break
            
            if report_path:
                if save_report_backup:
                    try:
                        shutil.copy2(report_path, report_path + ".backup_original")
                    except Exception: pass

                # Logica estrazione coverage (invariata)
                method_name = target_method_name
                if not method_name:
                    _, method_name = extract_method_name_from_test(test_class) # Assumendo che questa funzione esista e funzioni
                
                if method_name and target_class_name:
                    # Prima prova standard
                    coverage_data = parse_jacoco_report_for_method(report_path, target_class_name, method_name)
                    
                    # Fallback: nome semplice
                    if not coverage_data.get('found', False) and '/' in target_class_name:
                        coverage_data = parse_jacoco_report_for_method(report_path, target_class_name.split('/')[-1], method_name)
                    
                    # Fallback: classi rigenerate (logica custom mantenuta)
                    if not coverage_data.get('found', False) and 'generated' in target_class_name.lower():
                        # Per brevità qui uso parse_jacoco_report_for_method che dovrebbe gestire la maggior parte dei casi
                        pass
                    
                    # Se ancora non trovato e ci sono test passati, potrebbe essere un problema di matching
                    if not coverage_data.get('found', False) and tests_passed > 0:
                        # Aggiungi un warning per debug
                        print(f"WARNING: Method '{method_name}' in class '{target_class_name}' not found in JaCoCo report, but {tests_passed} tests passed.")
                        print(f"   This might indicate that the tests are not actually testing the target method, or there is a matching issue in the report.")
                        # Prova a cercare il metodo senza il nome della classe (cerca in tutte le classi)
                        import xml.etree.ElementTree as ET
                        try:
                            tree = ET.parse(report_path)
                            root = tree.getroot()
                            # Cerca il metodo in tutte le classi
                            for package in root.findall('.//package'):
                                for class_elem in package.findall('class'):
                                    for method_elem in class_elem.findall('method'):
                                        method_elem_name = method_elem.get('name', '')
                                        if method_name.lower() in method_elem_name.lower() or method_elem_name.lower() in method_name.lower():
                                            # Trovato un metodo con nome simile - usa questo come fallback
                                            method_counters = {}
                                            for counter in method_elem.findall('counter'):
                                                counter_type = counter.get('type')
                                                if counter_type:
                                                    missed = int(counter.get('missed', 0))
                                                    covered = int(counter.get('covered', 0))
                                                    total = missed + covered
                                                    percentage = (covered / total) * 100 if total > 0 else 0.0
                                                    method_counters[counter_type] = {
                                                        'missed': missed,
                                                        'covered': covered,
                                                        'total': total,
                                                        'percentage': percentage
                                                    }
                                            line_coverage = method_counters.get('LINE', {}).get('percentage', 0.0)
                                            branch_coverage = method_counters.get('BRANCH', {}).get('percentage', 0.0)
                                            if line_coverage > 0 or branch_coverage > 0:
                                                coverage_data = {
                                                    'line_coverage': line_coverage,
                                                    'branch_coverage': branch_coverage,
                                                    'found': True,
                                                    'method_counters': method_counters,
                                                    'class_name': class_elem.get('name', ''),
                                                    'method_name': method_elem_name
                                                }
                                                break
                                    if coverage_data.get('found', False):
                                        break
                                if coverage_data.get('found', False):
                                    break
                        except Exception:
                            pass  # Se fallisce, mantieni coverage_data originale (0%)

        # Analisi Errori
        has_compilation_errors = False
        if tests_total > 0:
            # Se i test sono stati eseguiti, la classe compila correttamente (Maven ha già compilato ed eseguito)
            has_compilation_errors = False
        else:
            # Se nessun test è stato eseguito, verifica se ci sono errori di compilazione nell'output di Maven
            has_compilation_errors = _has_compilation_errors(test_output, check_compilation_for_class)
        
        tests_failed = tests_total > 0 and tests_passed < tests_total
        
        # Controllo Edge Case: "0 tests run" ma Maven dice successo
        clean_out = remove_ansi_colors(test_output)
        no_tests_executed = "No tests were executed" in clean_out or "Tests are skipped" in clean_out
        
        if return_code == 0 and tests_total == 0 and not no_tests_executed:
            # Probabile mismatch nome classe
            if f"Class not found" in clean_out or "Cannot find class" in clean_out:
                has_compilation_errors = True
                test_output += "\n\n[SYSTEM] Warning: Maven finished successfully but 0 tests ran. Verify test class name."

        # Estrazione errori dettagliata (se necessario)
        if has_compilation_errors or tests_failed:
            formatted_errors = estrai_errori_compilazione(test_output)
            if formatted_errors:
                test_output += "\n\n=== EXTRACTED ERRORS FOR LLM ===\n" + formatted_errors

       
        success = (return_code == 0 and tests_total > 0 and tests_passed > 0 and not has_compilation_errors) or (return_code == 0 and tests_total == 0 and not has_compilation_errors)

        return {
            'success': success,
            'tests_passed': tests_passed,
            'tests_total': tests_total,
            '_internal_failed_test_names_only': failed_test_names,
            '_internal_error_test_names': error_test_names,
            'line_coverage': coverage_data.get('line_coverage', 0.0) if coverage_data else 0.0,
            'branch_coverage': coverage_data.get('branch_coverage', 0.0) if coverage_data else 0.0,
            'output': test_output,
            'has_compilation_errors': has_compilation_errors,
            'extracted_errors': formatted_errors,
            '_internal_failed_test_names': all_failed_test_names,  # Usato solo da popola_test_info
            '_internal_failed_test_names_only': failed_test_names,  # Usato solo da popola_test_info
        }
    finally:
        if backup_path:
            if pom_modified:
                restore_pom(pom_path, backup_path)
            else:
                try:
                    if os.path.exists(backup_path):
                        os.remove(backup_path)
                except Exception: pass


def esegui_test_originale(test_path: str, project_path: str, target_class_path: str,
                          nome_classe_solo: str, metodo_da_testare: str, nome_esperimento: str = None, timeout: int = 60) -> dict:
    test_class_name = estrai_nome_classe_test(test_path) or os.path.basename(test_path).replace('.java', '')
    target_class_name = get_coverage_class_name(target_class_path, project_path)
    
    # Estrai il nome semplice della classe di test per il check di compilazione
    simple_test_class_name = test_class_name.split('.')[-1]
    
    test_results = esegui_test_e_ottieni_risultati(
        project_path, test_class_name, target_class_name, metodo_da_testare,
        save_report_backup=True, skip_clean=False, nome_esperimento=nome_esperimento,
        check_compilation_for_class=simple_test_class_name, timeout=timeout
    )
    test_results['test_info'] = popola_test_info(test_results, test_path)
    return test_results


