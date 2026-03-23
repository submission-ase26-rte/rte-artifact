from utils.text.text_utils import estrai_codice_java
from utils.io.file_utils import salva_file
from utils.prompt.prompt_logger import salva_prompt_log
from utils.prompt.prompt_helpers import (
    costruisci_metodo_precedente_info,
    costruisci_struttura_classe_info,
    costruisci_test_info_section,
    costruisci_sezione_istruzioni_specifiche
)
from utils.tracking.token_tracker import save_test_generation_tokens, save_method_generation_tokens
import os
import re


# Importa la funzione unificata da token_tracker
from utils.tracking.token_tracker import capture_llm_tokens

def _capture_llm_tokens(phase: str, provider_name: str = None, refinement_iteration: int = None):
    """
    Cattura i token usati dall'ultima chiamata LLM (Ollama Cloud, Ollama Colab o Groq).
    Wrapper per retrocompatibilità - ora usa la funzione unificata in token_tracker.
    """
    capture_llm_tokens(operation_type=phase, refinement_iteration=refinement_iteration)


def genera_test_per_metodo(codice_classe, nome_metodo, provider_func, model_name, prompt_test_metodo, output_dir, nome_classe, versione, nome_esperimento=None):
    sezione_istruzioni_specifiche = costruisci_sezione_istruzioni_specifiche(prompt_test_metodo)
    
    # Estrai il package dalla classe target
    package_match = re.search(r'^package\s+([\w.]+);', codice_classe, re.MULTILINE)
    package_target = package_match.group(1) if package_match else None
    
    # Costruisci la sezione package
    package_section = ""
    if package_target:
        package_section = f"""TEST CLASS PACKAGE:
The test class MUST use the following package (same as the target class):
package {package_target};

"""
    
    # Estrai il metodo target con JavaDoc per evidenziarlo nel prompt
    metodo_target_con_javadoc = ""
    try:
        # Usa estrai_metodo_completo che include JavaDoc e annotazioni
        from utils.code.code_analysis import estrai_metodo_completo
        metodo_target_con_javadoc, _, _ = estrai_metodo_completo(codice_classe, nome_metodo)
        if not metodo_target_con_javadoc:
            # Fallback: usa estrai_metodo_singolo
            from core.evaluation.evaluation import estrai_metodo_singolo
            metodo_target_con_javadoc = estrai_metodo_singolo(codice_classe, nome_metodo, include_javadoc=True)
        
        if metodo_target_con_javadoc:
            metodo_target_section = f"""
TARGET METHOD TO TEST:
The following is the specific method you need to test (including its JavaDoc comments if present):

{metodo_target_con_javadoc}

"""
        else:
            metodo_target_section = ""
    except Exception as e:
        print(f"   WARNING:  Error extracting method with JavaDoc: {e}")
        metodo_target_section = ""
    
    prompt = f"""ROLE:
You are an expert Java developer specialized in writing complete and robust JUnit 5 tests. Your task is to generate high-quality tests that cover all edge cases and possible scenarios for the specified method.

{package_section}COMPLETE CLASS:
{codice_classe}
{metodo_target_section}

TECHNICAL INSTRUCTIONS:
- Generate COMPLETE JUnit 5 tests for the method {nome_metodo} of the class {nome_classe}
- The test must be robust and cover all edge cases and possible scenarios
- Test class name: {nome_classe}_{nome_metodo}TestGenerated{versione if versione else ""}
- CRITICAL: Generate ONLY the test class. DO NOT generate the original class ({nome_classe}) or any other classes.
- The original class code is provided above for reference only. You must test the existing implementation, not recreate it.
- CRITICAL: You MUST include the package declaration at the beginning of the test class (as specified above).
- CRITICAL: All methods MUST have JUnit annotations (@Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, etc.). DO NOT create methods without annotations.
- DO NOT create inner classes, helper classes, or custom Driver/Mock implementations inside the test class
- Use only standard Mockito mocking - do not create custom mock implementations
- Provide only Java code without explanations
- Make sure to test all paths of the method {nome_metodo}
- REMEMBER to include the correct import for Mockito: org.mockito.junit.jupiter.MockitoExtension.
- Include all necessary imports (e.g., @BeforeEach, @MockitoExtension, @InjectMocks, @Mock, etc.)

{sezione_istruzioni_specifiche}"""
    
    salva_prompt_log(prompt, "Test_generation", nome_classe, versione, nome_esperimento, metodo_da_testare=nome_metodo, model_name=model_name)
    
    output = provider_func(prompt, model_name)
    
    # Cattura i token usati per la generation test (Ollama Cloud o Groq)
    _capture_llm_tokens("test_generation")
    
    # Salva la risposta dell'LLM per la prima generation
    from utils.prompt.prompt_logger import salva_risposta_llm
    risposta_path = salva_risposta_llm(output, "Test_generation", nome_classe, versione, retry_count=0, nome_esperimento=nome_esperimento, metodo_da_testare=nome_metodo, model_name=model_name)
    
    versione_suffix = versione if versione else ""
    nome_classe_test_attesa = f"{nome_classe}_{nome_metodo}TestGenerated{versione_suffix}"
    codice_test = estrai_codice_java(output, nome_classe_test_attesa)
    
    # IMPORTANTE: Aggiungi @Timeout a livello di classe per prevenire infinite loop
    from utils.test.test_utils import aggiungi_timeout_classe
    codice_test = aggiungi_timeout_classe(codice_test)
    
    percorso_file = salva_file(output_dir, f"{nome_classe}_{nome_metodo}TestGenerated{versione_suffix}.java", codice_test)
    return percorso_file, prompt

def rigenera_metodo_dai_test(codice_test, nome_metodo, provider_func, model_name, prompt_rigenera_metodo, output_dir, nome_classe, versione, codice_classe_originale=None, metodo_precedente_path=None, test_results_originale=None, nome_esperimento=None, offusca_nome_metodo=False, retry_count=None, target_class_path=None, metodo_signature=None, metodo_precedente_codice=None):
    """
    Rigenera un metodo a partire dai test forniti.
    
    """
    # Costruisce le sezioni del prompt usando helper functions
    metodo_precedente_info = costruisci_metodo_precedente_info(metodo_precedente_path, nome_metodo, offusca_nome=offusca_nome_metodo, metodo_precedente_codice=metodo_precedente_codice)
    struttura_classe_info = costruisci_struttura_classe_info(codice_classe_originale)
    # Se c'è un metodo precedente, i test_results si riferiscono a quel metodo, non all'originale
    is_metodo_precedente = metodo_precedente_path is not None and os.path.exists(metodo_precedente_path)
    test_info_section, _, _ = costruisci_test_info_section(test_results_originale, codice_test, is_metodo_precedente=is_metodo_precedente)
    
    test_section = f"PROVIDED TESTS:\n{codice_test}\n" if codice_test and codice_test.strip() else ""
    sezione_istruzioni_specifiche = costruisci_sezione_istruzioni_specifiche(prompt_rigenera_metodo)
    

    
    signature_esatta = f"public <return_type> {nome_metodo}(<parameters>)" # Fallback default
    try:
        if codice_classe_originale:
            from core.evaluation.evaluation import estrai_metodo_singolo
            # Estrai codice metodo originale (senza javadoc per pulizia)
            metodo_orig = estrai_metodo_singolo(codice_classe_originale, nome_metodo, signature=metodo_signature)
            if metodo_orig:
                match = re.search(r'^(.*?)({|throws)', metodo_orig, re.DOTALL | re.MULTILINE)
                if match:
                    # Se troviamo 'throws', dobbiamo prendere anche quello
                    signature_part1 = match.group(1).strip()
                    if match.group(2) == 'throws':
                        # Cerca il resto fino alla graffa
                        match_throws = re.search(r'(throws.*?){', metodo_orig, re.DOTALL | re.MULTILINE)
                        if match_throws:
                            signature_esatta = signature_part1 + " " + match_throws.group(1).strip()
                        else:
                             signature_esatta = signature_part1 # Fallback
                    else:
                        signature_esatta = signature_part1
                
                # Rimuovi eventuali annotazioni (righe che iniziano con @) dalla signature per pulizia
                lines = signature_esatta.split('\n')
                lines = [l.strip() for l in lines if not l.strip().startswith('@')]
                signature_esatta = ' '.join(lines)
                
    except Exception as e:
        print(f"WARNING: Error extracting exact signature: {e}")

    prompt = f"""ROLE:
You are an expert Java developer specialized in regenerating methods from JUnit tests. Your task is to recreate the original method by analyzing the provided tests.

{test_section}{test_info_section}
{metodo_precedente_info}

TASK:
Regenerate ONLY the method "{nome_metodo}" by analyzing the provided tests.

CRITICAL OUTPUT FORMAT:
- Generate ONLY the method implementation
- DO NOT generate the full class, package, imports or class declaration
- Output ONLY the method with its annotations (if any), signature and body

EXPECTED OUTPUT FORMAT:
```java
{signature_esatta} {{
    // your implementation here
}}
```

{struttura_classe_info}

REQUIREMENTS:
- The regenerated method MUST pass ONLY the VALID tests (tests that do not fail)
- Maintain the exact same method signature as the original (as shown in EXPECTED OUTPUT FORMAT)
- Goal: improve coverage if possible compared to the values indicated above

CONSTRAINTS:
- Output ONLY the method code (with annotations if needed)
- DO NOT include package declaration
- DO NOT include import statements  
- DO NOT include class declaration
- DO NOT include any fields or constructors
- DO NOT generate helper methods - output ONLY the single method "{nome_metodo}"
- Provide ONLY Java code without explanations

{sezione_istruzioni_specifiche}"""
    
    salva_prompt_log(prompt, "Method_generation", nome_classe, versione, nome_esperimento, retry_count=retry_count, metodo_da_testare=nome_metodo, model_name=model_name)
    
    risposta_llm = provider_func(prompt, model_name)
    
    # Cattura i token usati per la generation metodo (Ollama Cloud o Groq)
    # retry_count indica l'iterazione di refinement (None = generation iniziale)
    _capture_llm_tokens("method_generation", refinement_iteration=retry_count)
    
    # Salva la risposta dell'LLM
    from utils.prompt.prompt_logger import salva_risposta_llm
    salva_risposta_llm(risposta_llm, "Method_generation", nome_classe, versione, retry_count=retry_count, nome_esperimento=nome_esperimento, metodo_da_testare=nome_metodo, model_name=model_name)
    
    # Estrai SOLO il metodo dalla risposta
    from utils.code.code_analysis import estrai_solo_metodo_da_risposta, backup_metodo_originale, sostituisci_metodo_in_file
    nuovo_metodo = estrai_solo_metodo_da_risposta(risposta_llm, nome_metodo)
    
    if not nuovo_metodo or not nuovo_metodo.strip():
        print(f"WARNING: Impossible to extract the method from the LLM response")
        # Fallback: prova a estrarre comunque il codice Java
        nuovo_metodo = estrai_codice_java(risposta_llm)
    
    
    # Valida sintassi
    from utils.code.code_analysis import valida_sintassi_java
    if not valida_sintassi_java(nuovo_metodo):
        print(f" Error: The regenerated method has an invalid syntax. Ignored.")
        # Se la sintassi è invalida, consideriamo fallimento della generation per questo tentativo
        # Solleviamo eccezione per far scattare meccanismo di retry o fallback
        raise ValueError("Generated method has invalid syntax")

    if target_class_path and os.path.exists(target_class_path):
        # 1. Backup del metodo originale (per completezza dei dati restituiti)
        metodo_originale_backup = backup_metodo_originale(target_class_path, nome_metodo, metodo_signature)
        
        # 2. Ritorna il codice del nuovo metodo e il backup, SENZA modificare il file qui
        return nuovo_metodo, prompt, metodo_originale_backup
    else:
        # Fallback (non dovrebbe accadere spesso in fase 2 con file esistente)
        print(f"WARNING: Original class not found, method not usable.")
        return None, prompt, None

