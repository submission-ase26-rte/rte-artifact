import re
import os
from typing import List, Optional
from utils.test.test_utils import estrai_codice_test_per_nome


def estrai_test_validi_completi(codice_test_attuale: str, test_validi_nomi: List[str]) -> Optional[str]:
    """
    Estrae solo i test validi dal codice attuale con implementazioni complete.
    """
    if not test_validi_nomi or not codice_test_attuale:
        return None
    
    codice_test_validi_parts = []
    
    # Estrae package, imports e struttura
    package_match = re.search(r'^package\s+[\w.]+;', codice_test_attuale, re.MULTILINE)
    imports_match = re.findall(r'^import\s+(?:static\s+)?[\w.*]+;', codice_test_attuale, re.MULTILINE)
    class_decl_match = re.search(r'^(public\s+)?class\s+(\w+)', codice_test_attuale, re.MULTILINE)
    
    if package_match:
        codice_test_validi_parts.append(package_match.group(0))
    if imports_match:
        codice_test_validi_parts.append("\n".join(imports_match))
    if class_decl_match:
        codice_test_validi_parts.append(f"{class_decl_match.group(1) or ''}class {class_decl_match.group(2)} {{")
    
    # Estrae TUTTI i metodi helper (setup, teardown, helper privati, etc.)
    from utils.test.test_utils import estrai_tutti_metodi_supporto
    metodi_helper = estrai_tutti_metodi_supporto(codice_test_attuale, test_validi_nomi)
    for metodo_helper in metodi_helper:
        codice_test_validi_parts.append(metodo_helper)
    
    # Aggiunge solo i test validi con implementazioni complete
    for nome_test in test_validi_nomi:
        codice_test = estrai_codice_test_per_nome(codice_test_attuale, nome_test)
        if codice_test:
            codice_test_validi_parts.append(codice_test)
    
    codice_test_validi_parts.append("}")
    return "\n\n".join(codice_test_validi_parts)


def leggi_codice_test_attuale(test_path: str, test_suite_disabilitata: bool) -> Optional[str]:
    """
    Legge il codice della test suite, gestendo il caso in cui sia disabilitata (.disabled).
    """
    try:
        if test_suite_disabilitata and test_path.endswith('.disabled'):
            with open(test_path, 'r', encoding='utf-8') as f:
                return f.read()
        elif test_suite_disabilitata:
            test_path_disabled = test_path.replace('.java', '.disabled')
            if os.path.exists(test_path_disabled):
                with open(test_path_disabled, 'r', encoding='utf-8') as f:
                    return f.read()
            else:
                with open(test_path, 'r', encoding='utf-8') as f:
                    return f.read()
        else:
            with open(test_path, 'r', encoding='utf-8') as f:
                return f.read()
    except Exception:
        return None


def disabilita_test_suite(test_path: str) -> tuple[str, bool]:
    """
    Disabilita la test suite cambiando l'estensione da .java a .disabled. nel caso non compili.
    Utile per eseguire test suite temporanea 
    """
    test_path_disabled = test_path.replace('.java', '.disabled')
    try:
        if os.path.exists(test_path):
            os.rename(test_path, test_path_disabled)
            return test_path_disabled, True
    except Exception as e:
        print(f"WARNING:  Error disabling initial test suite: {e}")
    return test_path, False


def riabilita_test_suite(test_path: str) -> tuple[str, bool]:
    """
    Riabilita la test suite cambiando l'estensione da .disabled a .java.
    """
    test_path_disabled = test_path if test_path.endswith('.disabled') else test_path.replace('.java', '.disabled')
    test_path_java = test_path_disabled.replace('.disabled', '.java')
    
    try:
        if os.path.exists(test_path_disabled):
            os.rename(test_path_disabled, test_path_java)
            return test_path_java, True
    except Exception as e:
        print(f"WARNING:  Error enabling initial test suite: {e}")
    return test_path, False

