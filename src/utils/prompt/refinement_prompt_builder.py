import re
from typing import Dict, List, Optional, Tuple
from utils.test.test_utils import (
    estrai_nomi_test_da_codice, estrai_codice_test_per_nome,
    estrai_campi_classe_test, estrai_signature_test, estrai_tutti_metodi_supporto
)


def _normalizza_nome_test(name: str) -> str:
    """Normalizza il nome del test per il matching"""
    return name.split('.')[-1] if '.' in name else name


def _estrai_struttura_classe(codice_test: str) -> Dict[str, str]:
    """Estrae package, imports, annotazioni e dichiarazione classe"""
    package_match = re.search(r'^package\s+[\w.]+;', codice_test, re.MULTILINE)
    imports_match = re.findall(r'^import\s+(?:static\s+)?[\w.*]+;', codice_test, re.MULTILINE)
    class_decl_match = re.search(r'^(public\s+)?class\s+(\w+)', codice_test, re.MULTILINE)
    
    class_annotations = []
    annotation_pattern = r'((?:@\w+(?:\([^)]*\))?\s*\n\s*)+)(?:public\s+)?class\s+\w+'
    annotation_match = re.search(annotation_pattern, codice_test, re.MULTILINE)
    if annotation_match:
        annotations_text = annotation_match.group(1).strip()
        for ann_line in annotations_text.split('\n'):
            ann_clean = ann_line.strip()
            if ann_clean and ann_clean.startswith('@'):
                class_annotations.append(ann_clean)
    
    return {
        'package': package_match.group(0) if package_match else None,
        'imports': imports_match,
        'class_decl': class_decl_match.group(0) if class_decl_match else None,
        'annotations': class_annotations
    }


def _costruisci_classe_test_completa(
    struttura: Dict[str, str],
    campi_classe: str,
    metodi_supporto: List[str],
    test_codice: List[str]
) -> str:
    """Costruisce una classe di test completa con struttura"""
    codice = ""
    
    if struttura['package']:
        codice += struttura['package'] + "\n\n"
    
    if struttura['imports']:
        imports_normali = [imp for imp in struttura['imports'] if not imp.startswith('import static')]
        imports_statici = [imp for imp in struttura['imports'] if imp.startswith('import static')]
        imports_normali.sort()
        imports_statici.sort()
        if imports_normali:
            codice += "\n".join(imports_normali) + "\n"
        if imports_normali and imports_statici:
            codice += "\n"
        if imports_statici:
            codice += "\n".join(imports_statici) + "\n"
        codice += "\n"
    
    if struttura['annotations']:
        codice += "\n".join(struttura['annotations']) + "\n"
    
    if struttura['class_decl']:
        codice += struttura['class_decl'] + " {\n"
    
    if campi_classe.strip():
        for line in campi_classe.split('\n'):
            if line.strip():
                codice += ("    " + line if not line.startswith(' ') else line) + "\n"
    
    for metodo in metodi_supporto:
        for line in metodo.split('\n'):
            if line.strip():
                codice += ("    " + line if not line.startswith(' ') else line) + "\n"
        codice += "\n"
    
    for codice_test in test_codice:
        codice += "\n    " + "\n    ".join(codice_test.split('\n')) + "\n"
    
    codice += "\n}"
    return codice


def costruisci_sezione_test_falliti(
    codice_test_attuale: str,
    test_falliti: List[str],
    test_errori_runtime: List[str],
    test_validi: List[str],
    error_messages: Optional[str] = None
) -> str:
    """Costruisce la sezione con i test falliti e la struttura completa della classe"""
    test_non_validi = test_falliti + test_errori_runtime
    
    test_falliti_codice = []
    for test_name in test_non_validi:
        codice_test = estrai_codice_test_per_nome(codice_test_attuale, test_name)
        if codice_test:
            # Filter out vague code markers inside the test code itself
            if "TEST CODE TO FIX" in codice_test:
                codice_test = codice_test.replace("TEST CODE TO FIX:", "").replace("TEST CODE TO FIX", "")

            # Pulisci il codice del test da frammenti vaganti
            codice_test_pulito = _pulisci_frammenti_codice(codice_test)
            if codice_test_pulito.strip():
                test_falliti_codice.append(codice_test_pulito)
    
    struttura = _estrai_struttura_classe(codice_test_attuale)
    campi_classe = estrai_campi_classe_test(codice_test_attuale)
    
    # Pulisci i campi dalla duplicazione e codice vagante
    campi_classe = _pulisci_frammenti_codice(campi_classe)
    
    # Estrai metodi di supporto senza duplicazioni
    metodi_supporto = estrai_tutti_metodi_supporto(codice_test_attuale, test_validi)
    
    # Rimuovi duplicati e pulisci i metodi di supporto
    metodi_supporto_puliti = []
    metodi_visti = set()
    for metodo in metodi_supporto:
        metodo_pulito = _pulisci_frammenti_codice(metodo)
        # Estrai la signature per identificare duplicati
        signature = _estrai_signature_metodo(metodo_pulito)
        if signature and signature not in metodi_visti and metodo_pulito.strip():
            metodi_supporto_puliti.append(metodo_pulito)
            metodi_visti.add(signature)
    
    codice_test_falliti = _costruisci_classe_test_completa(
        struttura, campi_classe, metodi_supporto_puliti, test_falliti_codice
    )
    
    # Costruisci la sezione con i motivi dei fallimenti se disponibili
    sezione = """FAILED TESTS (MUST BE FIXED):
The following tests have FAILED or encountered RUNTIME ERRORS. You MUST fix these tests by correcting their implementation:

"""
    
    # Aggiungi i motivi dei fallimenti se disponibili
    if error_messages and error_messages.strip():
        sezione += """FAILURE REASONS:
The following are the error messages from the test execution. Use these to understand WHY the tests failed:

"""
        sezione += error_messages.strip() + "\n\n"
        sezione += """TEST CODE TO FIX:
"""
    
    sezione += codice_test_falliti + "\n"
    return sezione


def _estrai_signature_metodo(codice_metodo: str) -> Optional[str]:
    """Estrae la signature di un metodo per identificare duplicati"""
    # Pattern per trovare la signature: (modificatori)? (tipo_ritorno) nomeMetodo(parametri)
    pattern = r'(?:public\s+|private\s+|protected\s+)?(?:static\s+)?(?:final\s+)?(?:\w+(?:<[^>]+>)?\s+)?(\w+)\s*\([^)]*\)'
    match = re.search(pattern, codice_metodo)
    if match:
        return match.group(1)  # Nome del metodo
    return None


def _pulisci_frammenti_codice(codice: str) -> str:
    """
    Rimuove frammenti di codice vagante che non appartengono a metodi completi.
    Questi sono tipicamente statement isolati, chiamate a metodi senza contesto, ecc.
    """
    if not codice or not codice.strip():
        return ""
    
    lines = codice.split('\n')
    cleaned_lines = []
    brace_depth = 0
    in_method = False
    skip_until_method = False
    
    for i, line in enumerate(lines):
        stripped = line.strip()
        
        # Conta parentesi graffe (ignorando quelle in stringhe)
        open_braces = stripped.count('{') - stripped.count('\\{')
        close_braces = stripped.count('}') - stripped.count('\\}')
        
        # Se siamo dentro un metodo, includi la riga
        if in_method:
            cleaned_lines.append(line)
            brace_depth += open_braces - close_braces
            if brace_depth <= 0:
                in_method = False
                brace_depth = 0
            continue
        
        # Verifica se è l'inizio di un metodo annotato o una dichiarazione di metodo
        if stripped.startswith('@') and any(ann in stripped for ann in 
            ['@Test', '@BeforeEach', '@AfterEach', '@BeforeAll', '@AfterAll']):
            cleaned_lines.append(line)
            skip_until_method = True
            continue
        
        # Se stiamo aspettando l'inizio di un metodo dopo un'annotazione
        if skip_until_method:
            cleaned_lines.append(line)
            if '{' in stripped:
                in_method = True
                brace_depth = open_braces - close_braces
                skip_until_method = False
            continue
        
        # Verifica se è l'inizio di un metodo helper (senza annotazione @Test)
        is_method_decl = re.match(
            r'^\s*(?:public\s+|private\s+|protected\s+)?(?:static\s+)?(?:final\s+)?(?:synchronized\s+)?(?:\w+(?:<[^>]+>)?\s+)?(\w+)\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{?',
            line
        )
        if is_method_decl and '(' in stripped and not stripped.startswith('//'):
            cleaned_lines.append(line)
            if '{' in stripped:
                in_method = True
                brace_depth = open_braces - close_braces
            else:
                skip_until_method = True
            continue
        
        # Includi dichiarazioni di campi
        if any(mod in stripped for mod in ['private ', 'protected ', 'public ']) and ';' in stripped and '(' not in stripped:
            cleaned_lines.append(line)
            continue
        
        # Includi annotazioni di campi
        if stripped.startswith('@') and any(ann in stripped for ann in 
            ['@Mock', '@InjectMocks', '@Captor', '@Spy', '@Autowired', '@Value']):
            cleaned_lines.append(line)
            continue
        
        # Includi package e import
        if stripped.startswith('package ') or stripped.startswith('import '):
            cleaned_lines.append(line)
            continue
        
        # Includi dichiarazione di classe
        if 'class ' in stripped and not stripped.startswith('//'):
            cleaned_lines.append(line)
            continue
        
        # Includi parentesi graffe di chiusura della classe
        if stripped == '}' and brace_depth == 0:
            cleaned_lines.append(line)
            continue
        
        # Includi linee vuote
        if not stripped:
            cleaned_lines.append(line)
            continue
        
        # SKIP: tutto il resto (statement isolati, chiamate a metodi senza contesto, ecc.)
    
    return '\n'.join(cleaned_lines)


def _pulisci_struttura_classe(struttura_classe: str) -> str:
    """
    Rimuove codice vagante dalla struttura della classe.
    Mantiene: package, imports, dichiarazione classe, campi, metodi @BeforeEach/@AfterEach COMPLETI.
    """
    lines = struttura_classe.split('\n')
    cleaned_lines = []
    brace_depth = 0
    in_annotated_method = False
    method_brace_depth = 0
    
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        
        # Aggiorna la profondità delle parentesi graffe
        open_braces = stripped.count('{')
        close_braces = stripped.count('}')
        
        # Se siamo dentro un metodo annotato, includi tutto fino alla chiusura
        if in_annotated_method:
            cleaned_lines.append(line)
            method_brace_depth += open_braces - close_braces
            if method_brace_depth <= 0:
                in_annotated_method = False
                method_brace_depth = 0
            i += 1
            continue
        
        # Aggiorna profondità generale
        brace_depth += open_braces - close_braces
        
        # Skip linee vuote
        if not stripped:
            cleaned_lines.append(line)
            i += 1
            continue
        
        # Sempre includere: package, import, class declaration
        if stripped.startswith('package ') or stripped.startswith('import '):
            cleaned_lines.append(line)
            i += 1
            continue
        if 'class ' in stripped and '{' in stripped:
            cleaned_lines.append(line)
            i += 1
            continue
        
        # Includere annotazioni di campi
        if stripped.startswith('@') and any(ann in stripped for ann in 
            ['@Mock', '@InjectMocks', '@Captor', '@Spy', '@Autowired', '@Value']):
            cleaned_lines.append(line)
            i += 1
            continue
        
        # Includere dichiarazioni di campi
        if any(mod in stripped for mod in ['private ', 'protected ', 'public ']) and ';' in stripped:
            if '(' not in stripped:  # Non è un metodo
                cleaned_lines.append(line)
                i += 1
                continue
        
        # Includere metodi @BeforeEach, @AfterEach, @BeforeAll, @AfterAll COMPLETI
        if stripped.startswith('@BeforeEach') or stripped.startswith('@AfterEach') or \
           stripped.startswith('@BeforeAll') or stripped.startswith('@AfterAll'):
            cleaned_lines.append(line)
            # Ora dobbiamo includere tutto il metodo
            in_annotated_method = True
            method_brace_depth = open_braces - close_braces
            i += 1
            continue
        
        # ESCLUDERE: codice vagante al livello classe (for, if, catch, assert, etc.)
        if brace_depth == 1:  # Siamo al livello classe
            if any(stripped.startswith(kw) for kw in 
                ['for ', 'for(', 'if ', 'if(', 'while ', 'while(', 'try ', 'try{', 
                 'catch ', 'catch(', 'return ', 'throw ', 'assert', 'assertEquals',
                 'assertTrue', 'assertFalse', 'assertNull', 'assertNotNull', 'fail(']):
                i += 1
                continue
            
            # Escludere chiamate di metodo senza annotazione
            if '(' in stripped and ')' in stripped and ';' in stripped:
                if not any(mod in stripped for mod in ['private ', 'protected ', 'public ']):
                    i += 1
                    continue
        
        # Includere la chiusura della classe
        if stripped == '}' and brace_depth == 0:
            cleaned_lines.append(line)
            i += 1
            continue
        
        # Includere parentesi graffe di chiusura valide
        if stripped == '}' and brace_depth >= 0:
            cleaned_lines.append(line)
            i += 1
            continue
        
        # Default: includi dichiarazioni valide
        if any(mod in stripped for mod in ['private ', 'protected ', 'public ', 'static ', 'final ']):
            cleaned_lines.append(line)
        
        i += 1
    
    return '\n'.join(cleaned_lines)


def costruisci_sezione_test_validi(
    codice_test_attuale: str,
    test_validi: List[str],
    has_failed_tests: bool,
    is_similarity_enhancement: bool = False
) -> str:
    """Costruisce la sezione con i test validi (full code se similarity enhancement logic, altrimenti solo signature)"""
    if not test_validi:
        return ""
    
    if is_similarity_enhancement:
        # SIMILARITY ENHANCEMENT MODE: Show FULL IMPLEMENTATION of valid tests
        # This helps the LLM understand what is already tested to avoid redundancy
        
        sezione = """EXISTING TEST SUITE (100% COVERAGE, ALL PASSING):
The following is the COMPLETE current test suite. It achieves 100% coverage and all tests pass.
It is provided fully implemented so you can see EXACTLY what logic is already covered.

CRITICAL INSTRUCTIONS:
1. READ these tests carefully to understand the current coverage.
2. DO NOT modify, reimplement, or remove any of these tests.
3. Your task is to ADD NEW TESTS that cover specific logic/paths/values NOT already covered by these tests.
4. Avoid adding tests that are redundant with the ones below.

"""
        # Extract full code for all valid tests
        test_validi_codice = []
        for test_name in test_validi:
            codice_test = estrai_codice_test_per_nome(codice_test_attuale, test_name)
            if codice_test:
                codice_test_pulito = _pulisci_frammenti_codice(codice_test)
                if codice_test_pulito.strip():
                    test_validi_codice.append(codice_test_pulito)
        
        # Build the full class
        struttura = _estrai_struttura_classe(codice_test_attuale)
        campi_classe = estrai_campi_classe_test(codice_test_attuale)
        campi_classe = _pulisci_frammenti_codice(campi_classe)
        
        metodi_supporto = estrai_tutti_metodi_supporto(codice_test_attuale, test_validi)
        
        # Deduplicate and clean helper methods
        metodi_supporto_puliti = []
        metodi_visti = set()
        for metodo in metodi_supporto:
            metodo_pulito = _pulisci_frammenti_codice(metodo)
            signature = _estrai_signature_metodo(metodo_pulito)
            if signature and signature not in metodi_visti and metodo_pulito.strip():
                metodi_supporto_puliti.append(metodo_pulito)
                metodi_visti.add(signature)

        codice_completo = _costruisci_classe_test_completa(
            struttura, campi_classe, metodi_supporto_puliti, test_validi_codice
        )
        
        sezione += codice_completo + "\n"
        return sezione

    # NORMAL MODE: Just signatures
    sezione = """VALID TESTS (REFERENCE ONLY):
The following tests are PASSING. Their signatures are shown for reference.
The system will automatically merge these valid tests with your new tests.

DO NOT include these test signatures in your output - generate ONLY:
- Fixed versions of failed tests
- New tests for uncovered lines

IMPORTANT: If you see any stray code fragments (like loops, assertions, or method calls 
NOT preceded by JUnit annotations like @Test, @BeforeEach, @AfterEach), IGNORE them completely.
Only valid Java class structure and annotated methods should be considered.

"""
    
    # Mostra struttura classe solo se non ci sono test falliti
    if not has_failed_tests:
        struttura = _estrai_struttura_classe(codice_test_attuale)
        campi_classe = estrai_campi_classe_test(codice_test_attuale)
        metodi_supporto = estrai_tutti_metodi_supporto(codice_test_attuale, test_validi)
        
        struttura_classe = _costruisci_classe_test_completa(
            struttura, campi_classe, metodi_supporto, []
        )
        
        # Pulisci la struttura da codice vagante
        struttura_classe = _pulisci_struttura_classe(struttura_classe)
        
        sezione += "Test class structure (reference):\n"
        sezione += struttura_classe + "\n"
    
    # Estrai e mostra solo le signature
    signatures_validi = []
    for test_name in test_validi:
        signature = estrai_signature_test(codice_test_attuale, test_name)
        if signature:
            signatures_validi.append(f"    {signature} {{}}")
    
    if signatures_validi:
        sezione += "\nValid test signatures:\n"
        sezione += "\n".join(signatures_validi) + "\n"
    
    if not has_failed_tests:
        sezione += "}\n"
    
    return sezione


def costruisci_sezione_compilation_errors(
    codice_test_attuale: str,
    errori_compilazione: str
) -> str:
    """Costruisce la sezione per gli errori di compilazione"""
    return f"""CURRENT TEST SUITE (with compilation errors):
{codice_test_attuale}

COMPILATION ERRORS:
{errori_compilazione}

FIX INSTRUCTIONS:
- Fix ALL compilation errors listed above
- Follow any suggestions in the error messages (e.g., "Use...", "Replace...with...")
- For "cannot find symbol" errors:
  * Add missing import statements
  * Use correct static imports for JUnit assertions (import static org.junit.jupiter.api.Assertions.*)
"""


def costruisci_sezione_coverage(
    line_coverage: float,
    soglia_coverage: float,
    has_failed_tests: bool
) -> str:
    """Costruisce la sezione per i problemi di coverage"""
    if not has_failed_tests:
        return f"""COVERAGE ANALYSIS:
 STATUS: All tests are currently PASSING and there are NO compilation errors.
  ISSUE: The test suite does not meet the minimum coverage threshold.

COVERAGE METRICS:
- Current line coverage: {line_coverage:.2f}%
- Target line coverage: 100%

YOUR TASK:
You MUST add NEW test cases to improve coverage from {line_coverage:.2f}% to 100%.

The uncovered lines of the original method are marked with "// NOT COVERED BY TESTS" in the method above. 
Analyze the target method to understand what scenarios need to be tested to cover these exact lines.

IMPORTANT: Maintain all existing passing tests - do NOT modify or remove them. Only ADD new test cases.
"""
    else:
        return f"""COVERAGE ISSUES:
Current line coverage: {line_coverage:.2f}%
Target line coverage: 100%

CRITICAL: After fixing the failed tests above, you MUST also add NEW test cases to improve coverage. The uncovered lines of the original method are marked with "// NOT COVERED BY TESTS" in the method above. Analyze the target method to understand what scenarios need to be tested to cover these exact lines.
"""


def estrai_test_info_da_risultati(
    test_results_originale: Dict,
    codice_test_attuale: str
) -> Tuple[List[str], List[str], List[str]]:
    """
    Estrae informazioni sui test dai risultati usando test_info.
    """
    from utils.test.test_utils import estrai_nomi_test_da_codice, match_test_names
    
    # Ottieni conteggi base
    tests_passed = test_results_originale.get('tests_passed', 0)
    tests_total = test_results_originale.get('tests_total', 0)
    tests_failed = test_results_originale.get('tests_failed', 0)
    
    # Usa direttamente test_info che contiene i nomi corretti estratti dall'output Maven
    test_info = test_results_originale.get('test_info', {})
    test_validi = test_info.get('valid_tests', [])
    test_non_validi = test_info.get('invalid_tests', [])
    test_falliti_assert = test_info.get('failed_assert_tests', [])
    test_errori_runtime = test_info.get('runtime_error_tests', [])
    

    if tests_total > 0:
        # Se test_falliti_assert e test_errori_runtime sono vuoti ma ci sono test falliti
        if tests_failed > 0 and len(test_falliti_assert) == 0 and len(test_errori_runtime) == 0:
            # test_info non ha i dettagli sui test falliti, usa test_non_validi
            if test_non_validi:
                # Considera tutti i test non validi come falliti assert (default)
                return test_validi, test_non_validi, []
            else:
                # Fallback: tutti i test nel codice sono falliti
                nomi_test_totali = estrai_nomi_test_da_codice(codice_test_attuale)
                if tests_passed == 0:
                    # Tutti falliti
                    return [], nomi_test_totali, []
                else:
                    # Alcuni passati, alcuni falliti - non sappiamo quali
                    return test_validi, [t for t in nomi_test_totali if t not in test_validi], []
        
        # Se abbiamo dati dettagliati, usali
        if test_falliti_assert or test_errori_runtime:
            return test_validi, test_falliti_assert, test_errori_runtime
        
        # Se tutti passati secondo Maven e test_info
        if tests_passed == tests_total and tests_failed == 0:
            return test_validi if test_validi else estrai_nomi_test_da_codice(codice_test_attuale), [], []
    
    # Fallback: estrai i nomi dal codice e classifica in base ai conteggi Maven
    nomi_test_totali = estrai_nomi_test_da_codice(codice_test_attuale)
    
    if tests_total == 0:
        return [], [], []
    elif tests_passed == 0:
        # Tutti falliti - distingui tra assert e runtime
        failed_test_names = test_results_originale.get('_internal_failed_test_names_only', [])
        error_test_names = test_results_originale.get('_internal_error_test_names', [])
        test_falliti = []
        test_errori = []
        for test_name in nomi_test_totali:
            if match_test_names(test_name, error_test_names):
                test_errori.append(test_name)
            elif match_test_names(test_name, failed_test_names):
                test_falliti.append(test_name)
            else:
                # Fallback: se non corrisponde a nessuno, mettilo nei falliti
                test_falliti.append(test_name)
        return [], test_falliti, test_errori
    elif tests_passed == tests_total:
        return nomi_test_totali, [], []
    
    # Estrai i test falliti dall'output Maven
    failed_test_names_from_output = test_results_originale.get('_internal_failed_test_names_only', [])
    error_test_names_from_output = test_results_originale.get('_internal_error_test_names', [])
    
    # Classifica i test basandosi SOLO sull'output Maven
    test_falliti = []
    test_errori = []
    
    for test_name in nomi_test_totali:
        if match_test_names(test_name, failed_test_names_from_output):
            test_falliti.append(test_name)
        elif match_test_names(test_name, error_test_names_from_output):
            test_errori.append(test_name)
    
    test_non_validi_calcolati = test_falliti + test_errori
    test_validi_calcolati = [t for t in nomi_test_totali if t not in test_non_validi_calcolati]
    
    return test_validi_calcolati, test_falliti, test_errori

