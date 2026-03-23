import re
from typing import Optional, List


def remove_ansi_colors(text: str) -> str:
    """
    Rimuove i codici di escape ANSI (colori) dall'output di Maven.
    Essenziale perché le regex falliscono se trovano caratteri di controllo invisibili.
    """
    ansi_escape = re.compile(r'\x1B(?:[@-Z\\-_]|\[[0-?]*[ -/]*[@-~])')
    return ansi_escape.sub('', text)


def clean_error_message(lines: List[str]) -> str:
    """Pulisce e unisce le righe di errore per il report."""
    cleaned = []
    for line in lines:
        # Rimuove [ERROR] e spazi
        line = re.sub(r'^\[ERROR\]\s*', '', line).strip()
        if line:
            cleaned.append(line)
    return "\n".join(cleaned)


def extract_failed_test_name(lines: List[str], error_line_index: int) -> Optional[str]:
    """
    Estrae il nome del test fallito dalla riga di errore Maven.
    
    Formati supportati:
    - [ERROR] testName  Time elapsed: X s  <<< FAILURE!/ERROR!
    - [ERROR] package.Class.testName -- Time elapsed: X s  <<< FAILURE!/ERROR!
    - testName  Time elapsed: X s  <<< FAILURE!/ERROR!
    """
    if error_line_index >= len(lines):
        return None
    
    line = lines[error_line_index]
    
    # Pattern principale: estrae il nome del test dalla riga con <<< FAILURE! o <<< ERROR!
    
    # Pattern 1: Formato con package completo - [ERROR] org.example.TestClass.testMethod -- Time elapsed...
    # Questo è il formato più comune in Maven recenti
    match = re.search(r'\[ERROR\]\s+([\w.$]+)\s+--?\s+Time elapsed.*<<<\s+(FAILURE|ERROR)!', line)
    if match:
        full_name = match.group(1)
        # Prendi solo l'ultima parte dopo il punto (il nome del metodo)
        return full_name.split('.')[-1]
    
    # Pattern 2: Formato semplice - [ERROR] testName  Time elapsed...
    match = re.search(r'\[ERROR\]\s+([A-Za-z_$][\w$]*)\s+Time elapsed.*<<<\s+(FAILURE|ERROR)!', line)
    if match:
        return match.group(1)
    
    # Pattern 3: Formato senza [ERROR]: testName  Time elapsed...
    match = re.search(r'^([A-Za-z_$][\w$]*)\s+Time elapsed.*<<<\s+(FAILURE|ERROR)!', line.strip())
    if match:
        return match.group(1)
    
    # Pattern 4: testName(package): testName(com.example.Test)  Time elapsed...
    match = re.search(r'^([A-Za-z_$][\w$]*)\([^)]+\)\s+Time elapsed.*<<<\s+(FAILURE|ERROR)!', line.strip())
    if match:
        return match.group(1)
    
    # Pattern 5: package.Class.testName con spazi - com.example.Test.testName  Time elapsed...
    match = re.search(r'([\w$]+(?:\.[\w$]+)+)\s+--?\s+Time elapsed.*<<<\s+(FAILURE|ERROR)!', line)
    if match:
        full_name = match.group(1)
        return full_name.split('.')[-1]
    
    # Pattern 6: Fallback - qualsiasi cosa prima di "Time elapsed"
    match = re.search(r'([\w.$]+)\s+Time elapsed.*<<<\s+(FAILURE|ERROR)!', line)
    if match:
        full_name = match.group(1)
        return full_name.split('.')[-1]
    
    return None

