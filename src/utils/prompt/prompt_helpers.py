import os
import re
from typing import Optional, Dict, Tuple, List
from utils.prompt.refinement_prompt_builder import (
    costruisci_sezione_test_falliti,
    costruisci_sezione_test_validi,
    costruisci_sezione_coverage,
    estrai_test_info_da_risultati
)
from utils.code.code_analysis import aggiungi_commenti_righe_non_coperte
from utils.test.test_utils import estrai_metodo_con_riga_inizio, estrai_tutti_metodi_supporto


def costruisci_metodo_precedente_info(metodo_precedente_path: str, nome_metodo: str, offusca_nome: bool = False, metodo_precedente_codice: str = None) -> str:
    """Costruisce la sezione del prompt per il metodo precedente"""
    from core.evaluation.evaluation import estrai_metodo_singolo

    classe_precedente_completa = ""
    
    # Priority to direct code if provided
    if metodo_precedente_codice:
        # If code is provided, it might be just the method or the full class
        # Try to extract method, if fail assume it is the method
        temp = estrai_metodo_singolo(metodo_precedente_codice, nome_metodo)
        if temp:
             metodo_precedente_estratto = temp
        else:
             # Assume it is already the method code
             metodo_precedente_estratto = metodo_precedente_codice
            
    elif metodo_precedente_path and os.path.exists(metodo_precedente_path):
        with open(metodo_precedente_path, 'r', encoding='utf-8') as f:
            classe_precedente_completa = f.read()
        metodo_precedente_estratto = estrai_metodo_singolo(classe_precedente_completa, nome_metodo)
    else:
        return ""
    
    if not metodo_precedente_estratto:
        return ""
    
    if offusca_nome:
        pattern1 = rf'(\b)({re.escape(nome_metodo)})(\s*\()'
        metodo_precedente_estratto = re.sub(pattern1, r'\1x\3', metodo_precedente_estratto)
        pattern2 = rf'([\.\s])({re.escape(nome_metodo)})(\s*\()'
        metodo_precedente_estratto = re.sub(pattern2, r'\1x\3', metodo_precedente_estratto)
        pattern3 = rf'(^|\s+)({re.escape(nome_metodo)})(\s*\()'
        metodo_precedente_estratto = re.sub(pattern3, r'\1x\3', metodo_precedente_estratto, flags=re.MULTILINE)
    
    return f"""

PREVIOUSLY GENERATED METHOD:
{metodo_precedente_estratto}

ADDITIONAL INSTRUCTIONS:
- Compare the previous method with the one you are about to generate
- Improve quality and correctness compared to the previous version
- Maintain compatibility with all provided tests
"""


def costruisci_struttura_classe_info(codice_classe_originale: Optional[str] = None, target_class_path: Optional[str] = None) -> str:
    """Costruisce la sezione del prompt per la struttura della classe"""
    if not codice_classe_originale and not target_class_path:
        return ""
    
    if target_class_path and os.path.exists(target_class_path) and not codice_classe_originale:
        with open(target_class_path, 'r', encoding='utf-8') as f:
            codice_classe_originale = f.read()
    
    if not codice_classe_originale:
        return ""
    
    package_declaration = ""
    class_annotations = []
    
    package_match = re.search(r'^package\s+([\w.]+);', codice_classe_originale, re.MULTILINE)
    if package_match:
        package_declaration = f"package {package_match.group(1)};"
    
    annotation_matches = re.findall(r'@\w+(?:\([^)]*\))?', codice_classe_originale)
    class_annotations = [
        ann for ann in annotation_matches 
        if '@Repository' in ann or '@Service' in ann or '@Component' in ann
    ]
    
    return f"""
- Package: {package_declaration.replace('package ', '').replace(';', '') if package_declaration else 'to be determined'}
- Class annotations: {', '.join(class_annotations) if class_annotations else 'none'}
"""


def costruisci_test_info_section(
    test_results_originale: Optional[Dict],
    codice_test: str,
    is_metodo_precedente: bool = False
) -> Tuple[str, list, list]:
    """Costruisce la sezione del prompt per le informazioni sui test"""
    if not test_results_originale:
        return "", [], []
    
    from utils.test.test_utils import estrai_nomi_test_da_codice
    
    line_coverage = test_results_originale.get('line_coverage', 0.0)
    branch_coverage = test_results_originale.get('branch_coverage', 0.0)
    tests_passed = test_results_originale.get('tests_passed', 0)
    tests_total = test_results_originale.get('tests_total', 0)
    
    test_info = test_results_originale.get('test_info', {})
    test_validi = test_info.get('valid_tests', [])
    test_non_validi = test_info.get('invalid_tests', [])
    test_falliti_assert = test_info.get('failed_assert_tests', [])
    test_errori_runtime = test_info.get('runtime_error_tests', [])
    
    # Se test_info non è completo, estrai i dati direttamente dall'output Maven
    if not test_info or (not test_validi and not test_non_validi):
        from utils.test.test_utils import estrai_nomi_test_da_codice, match_test_names
        
        nomi_test_totali = estrai_nomi_test_da_codice(codice_test)
        tests_passed = test_results_originale.get('tests_passed', 0)
        tests_total = test_results_originale.get('tests_total', 0)
        
        # Estrai i test falliti dall'output Maven
        failed_test_names_from_output = test_results_originale.get('_internal_failed_test_names_only', [])
        error_test_names_from_output = test_results_originale.get('_internal_error_test_names', [])
        
        # Classifica i test basandosi SOLO sull'output Maven
        test_falliti_assert = []
        test_errori_runtime = []
        
        for test_name in nomi_test_totali:
            if match_test_names(test_name, failed_test_names_from_output):
                test_falliti_assert.append(test_name)
            elif match_test_names(test_name, error_test_names_from_output):
                test_errori_runtime.append(test_name)
        
        test_non_validi = test_falliti_assert + test_errori_runtime
        test_validi = [t for t in nomi_test_totali if t not in test_non_validi]
        
        if len(test_validi) != tests_passed:
            print(f"WARNING:  WARNING in costruisci_test_info_section: Valid tests {len(test_validi)} != tests_passed {tests_passed}")
            print(f"   Failed from output: {failed_test_names_from_output}")
            print(f"   Errors from output: {error_test_names_from_output}")
            print(f"   Tests from code: {nomi_test_totali}")
    
    # Filtra nomi non validi dai test (come "Tests", "Test", ecc.)
    nomi_non_validi = {'Test', 'Tests', 'test', 'tests', 'TEST', 'TESTS'}
    test_validi_filtrati = [t for t in test_validi if t not in nomi_non_validi]
    test_falliti_filtrati = [t for t in test_falliti_assert if t not in nomi_non_validi]
    test_errori_runtime_filtrati = [t for t in test_errori_runtime if t not in nomi_non_validi]
    
    test_passati = test_validi_filtrati
    test_falliti = test_falliti_filtrati
    test_errori_runtime_final = test_errori_runtime_filtrati
    
    method_reference = "PREVIOUSLY REGENERATED METHOD" if is_metodo_precedente else "ORIGINAL METHOD"
    
    test_info_section = f"""
COVERAGE ON {method_reference}:
- Line: {line_coverage:.2f}% | Branch: {branch_coverage:.2f}%
- Tests: {tests_passed}/{tests_total} passed
"""
    if is_metodo_precedente:
        test_info_section += "(Coverage refers to previously regenerated method, not original)\n"
    
    if test_passati or test_falliti or test_errori_runtime_final:
        test_info_section += f"""
TEST STATUS:
- PASSED ({len(test_passati)}): {', '.join(test_passati) if test_passati else 'none'}
- FAILED ({len(test_falliti)}): {', '.join(test_falliti) if test_falliti else 'none'}
- ERRORS ({len(test_errori_runtime_final)}): {', '.join(test_errori_runtime_final) if test_errori_runtime_final else 'none'}

The regenerated method must pass all PASSED tests.
"""
    
    return test_info_section, test_validi, test_non_validi


def costruisci_sezione_istruzioni_specifiche(prompt_yaml: Optional[str]) -> str:
    """Costruisce la sezione istruzioni specifiche dal prompt YAML"""
    if not prompt_yaml or not prompt_yaml.strip():
        return ""
    return f"DOMAIN-SPECIFIC INSTRUCTIONS:\n{prompt_yaml.strip()}\n"


def costruisci_refinement_prompt(
    codice_test_attuale: str,
    errori_compilazione: Optional[str] = None,
    righe_non_coperte: Optional[List[int]] = None,
    line_coverage: float = 0.0,
    soglia_coverage: float = 0.0,
    test_results_originale: Optional[Dict] = None,
    metodo_originale: Optional[str] = None,
    riga_inizio_metodo: int = 1,
    codice_classe_completo: Optional[str] = None,
    target_class_name: str = None,  # Nome della classe target per istruzioni classi astratte
    context_description: Optional[str] = None,  # Contesto completo del progetto (dipendenze, ecc.)
    exception_context: Optional[str] = None,  # Contesto delle eccezioni (costruttori)
    similarity_info: Optional[Dict] = None  # Info similarità per similarity enhancement mode
) -> str:
    """Costruisce un prompt di refinement per rigenerare i test con correzioni"""
    refinement_sections = []
    
    # Estrai informazioni sui test
    test_passati, test_falliti, test_errori_runtime = [], [], []
    if test_results_originale:
        test_passati, test_falliti, test_errori_runtime = estrai_test_info_da_risultati(
            test_results_originale, codice_test_attuale
        )
    
    test_validi = test_passati
    has_compilation_errors = errori_compilazione is not None and len(errori_compilazione.strip()) > 0
    has_failed_tests = len(test_falliti) > 0 or len(test_errori_runtime) > 0
    has_coverage_issues = (righe_non_coperte is not None and len(righe_non_coperte) > 0) or (soglia_coverage > 0.0 and line_coverage < soglia_coverage)
    
    # Rileva similarity enhancement mode
    is_similarity_enhancement = (
        similarity_info is not None 
        and similarity_info.get("is_enhancement_mode", False)
    )
    
    # Estrai package
    package_test_match = re.search(r'^package\s+[\w.]+;', codice_test_attuale, re.MULTILINE)
    package_test = package_test_match.group(0) if package_test_match else None
    
    # Estrai metodi helper esistenti per includerli nel contesto
    metodi_helper_esistenti = []
    if codice_test_attuale:
        # Passiamo i test validi per evitare di estrarre test come helper
        nomi_test_noti = test_validi + test_falliti + test_errori_runtime
        metodi_helper_esistenti = estrai_tutti_metodi_supporto(codice_test_attuale, nomi_test_validi=nomi_test_noti)

    if not package_test and codice_classe_completo:
        package_target_match = re.search(r'^package\s+([\w.]+);', codice_classe_completo, re.MULTILINE)
        if package_target_match:
            package_test = f"package {package_target_match.group(1)};"
    
    # Build task description based on mode
    if is_similarity_enhancement:
        # Similarity enhancement mode: coverage 100%, all tests pass, but similarity low
        similarity_current = similarity_info.get("similarity_current", 0.0)
        similarity_threshold = similarity_info.get("similarity_threshold", 0.7)
        
        role_message = f"""ROLE:
You are an expert Java developer. The current test suite achieves 100% code coverage 
and all tests pass, but the tests are TOO GENERIC.

CURRENT SITUATION:
- Line Coverage: 100%
- Tests: {len(test_validi)}/{len(test_validi)} passed (all passing)
- Similarity Score: {similarity_current:.4f} (threshold: {similarity_threshold})

The low similarity score indicates that a DIFFERENT implementation could also pass 
all these tests. Your task is to ADD MORE SPECIFIC tests that capture the unique 
behavior of the original method.
"""
    else:
        # Normal refinement mode
        task_description = []
        if has_failed_tests:
            task_description.append("fix failed tests")
        if has_coverage_issues:
            task_description.append("improve coverage")
        
        task_str = " and ".join(task_description) if task_description else "improve coverage"
        role_message = f"""ROLE:
You are an expert Java developer. Task: {task_str}.
"""
    
    refinement_sections.append(role_message)
    
    # Estrai nome classe test dal codice esistente
    class_name_match = re.search(r'^\s*(?:public\s+)?class\s+(\w+)', codice_test_attuale, re.MULTILINE)
    test_class_name = class_name_match.group(1) if class_name_match else None
    
    # Package e nome classe
    if package_test or test_class_name:
        class_info = "TEST CLASS INFO:\n"
        if package_test:
            class_info += f"Package: {package_test}\n"
        if test_class_name:
            class_info += f"Class name (MUST be exactly): {test_class_name}\n"
        class_info += "\n"
        refinement_sections.append(class_info)
    
    # Sezione test falliti/validi (NON errori di compilazione!)
    if has_failed_tests:
        # Estrai i messaggi di errore dall'output Maven
        error_messages = test_results_originale.get('extracted_errors') if test_results_originale else None
        
        if not error_messages and test_results_originale:
            from utils.code.error_extractor import estrai_errori_compilazione
            test_output = test_results_originale.get('output', '')
            if test_output:
                # Usa la stessa funzione che estrae errori per test falliti
                error_messages = estrai_errori_compilazione(test_output)
        
        refinement_sections.append(costruisci_sezione_test_falliti(
            codice_test_attuale, test_falliti, test_errori_runtime, test_validi,
            error_messages=error_messages
        ))
        refinement_sections.append("\n")
    
    if test_validi:
        refinement_sections.append(costruisci_sezione_test_validi(
            codice_test_attuale, test_validi, has_failed_tests, is_similarity_enhancement
        ))
        refinement_sections.append("\n")
    
    if context_description or exception_context:
        context_parts = []
        if context_description:
            context_parts.append(context_description)
        if exception_context:
            context_parts.append(exception_context)
    if context_description or exception_context:
        context_parts = []
        if context_description:
            context_parts.append(context_description)
        if exception_context:
            context_parts.append(exception_context)
        refinement_sections.append(f"PROJECT CONTEXT:\n{chr(10).join(context_parts)}\n")

    if metodi_helper_esistenti:
        refinement_sections.append("EXISTING HELPER METHODS (Available for use, DO NOT MODIFY):")
        refinement_sections.append("The following helper methods are already present in the test class. You CAN use them, but DO NOT modify or reimplement them unless necessary for a fix.")
        refinement_sections.append("```java")
        for helper in metodi_helper_esistenti:
            refinement_sections.append(helper)
        refinement_sections.append("```")
        refinement_sections.append("\n")

    # Metodo originale con commenti
    if metodo_originale:
        if righe_non_coperte and len(righe_non_coperte) > 0:
            metodo_con_commenti = aggiungi_commenti_righe_non_coperte(
                metodo_originale, righe_non_coperte, riga_inizio_metodo, codice_classe_completo
            )
            refinement_sections.append("""ORIGINAL METHOD TO TEST:
    The following is the complete original method that needs to be tested. Lines marked with "// NOT COVERED BY TESTS" are NOT covered by the current tests and MUST be covered by new test cases:

    """)
            refinement_sections.append(metodo_con_commenti)
        else:
            # Se coverage è 100%, specificarlo nel messaggio
            if line_coverage >= 100.0:
                refinement_sections.append("""ORIGINAL METHOD TO TEST:
The following is the complete original method that needs to be tested. NOTE: The current test suite achieves 100% line coverage on this method:

""")
            else:
                refinement_sections.append("""ORIGINAL METHOD TO TEST:
The following is the complete original method that needs to be tested:

""")
            refinement_sections.append(metodo_originale)
        refinement_sections.append("\n")
    
    # Sezione coverage (sempre mostrata se ci sono problemi di coverage)
    if has_coverage_issues:
        refinement_sections.append(costruisci_sezione_coverage(line_coverage, soglia_coverage, has_failed_tests))
        refinement_sections.append("\n")
    elif line_coverage >= 100.0 and not is_similarity_enhancement:
        # In similarity enhancement mode, don't show this message because
        # the goal is to ADD tests for similarity, not fix failing tests
        refinement_sections.append(f"""COVERAGE: 100% line coverage achieved.
Focus on fixing failed tests, not adding new coverage tests.
""")
        refinement_sections.append("\n")
    
    # Istruzioni tecniche - consolidate (NON includere errori di compilazione)
    instructions = []
    
    # Istruzioni specifiche basate sulla modalità
    if is_similarity_enhancement:
        # Similarity enhancement mode - focus on specificity
        instructions.extend([
            "STRENGTHEN existing tests that do not uniquely characterize the original method behavior",
            "ADD new tests to constrain behaviors that could be satisfied by alternative implementations",
            "Focus on SPECIFIC RETURN VALUES - test exact values, not just types",
            "Focus on BOUNDARY CONDITIONS - edge cases unique to this implementation",
            "Focus on SPECIFIC EXCEPTION MESSAGES if applicable",
            "Focus on INTERNAL LOGIC PATHS specific to this implementation",
            "The goal: new tests should FAIL if someone wrote a different implementation"
        ])
    else:
        # Normal refinement mode
        if has_failed_tests:
            instructions.append("Fix failed tests")
            instructions.append("CRITICAL FOR FIXING ASSERTIONS: When an error shows 'expected: <X> but was: <Y>', the ACTUAL VALUE is Y (what the method returns). You MUST change your assertion to expect Y, not X. For example: if error is 'expected: <null> but was: <java.lang.String>' you must change assertNull(...) to assertEquals(String.class, ...)")
        if has_coverage_issues:
            instructions.append("Add tests for lines marked '// NOT COVERED BY TESTS'")
        if test_validi:
            instructions.append("Valid tests are for REFERENCE ONLY - do not include them in output")
    
    # Rileva se la classe target è astratta e aggiungi istruzioni
    abstract_class_instructions = ""
    if codice_classe_completo:
        try:
            from utils.code.code_analysis import is_abstract_class, generate_abstract_class_instantiation
            if is_abstract_class(codice_classe_completo) and target_class_name:
                instantiation_tmpl = generate_abstract_class_instantiation(target_class_name, codice_classe_completo)
                
                abstract_class_instructions = f"""
- IMPORTANT: The target class "{target_class_name}" is ABSTRACT and cannot be instantiated directly.
- DO NOT use Mockito to mock the class itself (unless you need a partial mock).
- INSTANTIATE using this REAL ANONYMOUS SUBCLASS template (copy exactly):
```java
{instantiation_tmpl}
```
- DO NOT create custom concrete subclasses files.
- DO NOT invent classes that do not exist in the codebase.
"""
        except Exception as e:
            # print(f"Warning: error generating abstract instructions: {e}")
            pass
    
    instructions.extend([
        "Generate ONLY the test class without explanations or comments outside the code",
        "All methods MUST have JUnit annotations (@Test, @BeforeEach, @AfterEach, etc.)",
        "DO NOT use @Nested annotations",
        "DO NOT create inner classes or custom implementations.",
        "USE EXISTING HELPER METHODS if available (see above). Create NEW helpers methods only if ensuring unique names.",
        "CRITICAL: DO NOT mock the target class itself - ALWAYS instantiate it directly using 'new TargetClass(...)'. Mocking the target class defeats the purpose of testing. You MAY mock DEPENDENCIES but NEVER the class under test",
        "Do not infer undocumented behavior.",
        "Do not rely on implementation details not observable from the method signature.",
        "Do not assert exact outputs if they depend on non-deterministic factors.",
        "For expected exceptions, use JUnit 5 assertThrows() - DO NOT use JUnit 4 syntax @Test(expected=...) which is NOT valid in JUnit 5",
        "When using enum types from dependencies, use ONLY values explicitly shown in the context. If enum values are not listed, use a mock instead of guessing enum constants.",
        "ENSURE the file starts with the correct package declaration",
        "Keep the SAME test class name as shown in TEST CLASS INFO",
        "IGNORE any stray code fragments (loops, assertions, statements) NOT preceded by JUnit annotations - they are extraction artifacts"
    ])
    
    refinement_sections.append("INSTRUCTIONS:\n" + "\n".join(f"- {i}" for i in instructions) + abstract_class_instructions)
    
    return "\n".join(refinement_sections)
