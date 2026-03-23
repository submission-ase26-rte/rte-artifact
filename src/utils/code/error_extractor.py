import re
from typing import Optional, List, Set
from utils.text.output_utils import clean_error_message, remove_ansi_colors

def estrai_errori_compilazione(test_output: str, filter_for_class: str = None) -> Optional[str]:
    """
    Estrae errori sintetici associati a specifici test falliti.
    - Ignora gli stack trace (righe 'at ...').
    - Elimina i duplicati (stesso test che appare più volte con lo stesso errore).
    """
    if not test_output:
        return None

    # Pulizia ANSI prima di processare
    test_output = remove_ansi_colors(test_output)
    lines = test_output.split('\n')
    extracted_errors = []
    
    # Set per tracciare univocità: contiene tuple (nome_test, hash_messaggio_errore)
    seen_entries: Set[tuple] = set()
    
    # Regex per individuare la riga che annuncia il fallimento di un test specifico
    test_failure_pattern = re.compile(r'\[ERROR\]\s+(.*?)\s+Time elapsed:.*<<<\s+(ERROR|FAILURE)!')
    
    # Regex per stop
    stop_patterns = [
        re.compile(r'^\[INFO\]'),
        re.compile(r'^\[ERROR\] Tests run:'), 
        re.compile(r'^\[WARNING\]'),
    ]

    current_test_name = None
    current_error_buffer = []
    capture_mode = False

    def finalize_error(test_name, buffer):
        """Helper interno per salvare l'errore se non è duplicato"""
        if not test_name or not buffer:
            return
            
        clean_msg = clean_error_message(buffer)
        
        # Crea una chiave univoca per questo errore
        # Usiamo il messaggio pulito per il confronto
        entry_key = (test_name, clean_msg)
        
        if entry_key not in seen_entries:
            seen_entries.add(entry_key)
            extracted_errors.append(f"TEST: {test_name}\nERROR: {clean_msg}")

    for line in lines:
        clean_line = line.strip()
        
        # 1. Cerca l'inizio di un fallimento test
        match = test_failure_pattern.search(line)
        if match:
            # Salva l'errore precedente se esiste
            if capture_mode:
                finalize_error(current_test_name, current_error_buffer)
            
            # Inizia nuovo blocco
            full_test_path = match.group(1)
            current_test_name = full_test_path.split('.')[-1] if '.' in full_test_path else full_test_path
            current_error_buffer = []
            capture_mode = True
            continue

        # 2. Analizza contenuto errore
        if capture_mode:
            # a. Ignora stack trace
            if clean_line.startswith('at ') or clean_line.startswith('\tat '):
                continue 
            
            # b. Stop patterns o nuovo errore
            if any(p.match(line) for p in stop_patterns) or test_failure_pattern.search(line):
                finalize_error(current_test_name, current_error_buffer)
                
                capture_mode = False
                current_test_name = None
                current_error_buffer = []
                
                pass 
            
            # c. Cattura contenuto valido
            else:
                if not clean_line:
                    continue
                current_error_buffer.append(clean_line)

    # Gestione ultimo errore rimasto nel buffer
    if capture_mode:
        finalize_error(current_test_name, current_error_buffer)

    # Fallback per errori generici di build o errori di compilazione
    if not extracted_errors and ("BUILD FAILURE" in test_output or "cannot find symbol" in test_output.lower() or "COMPILATION ERROR" in test_output):
        return extract_generic_build_failure(lines, filter_for_class)

    if not extracted_errors:
        return None

    return "\n--------------------------------------------------\n".join(extracted_errors)

def extract_generic_build_failure(lines: list, filter_for_class: str = None) -> str:
    """
    Estrae errori di compilazione generici con dettagli sul simbolo mancante.
    """
    errors = []
    i = 0
    seen_errors = set()
    seen_identities = set()
    
    # Prepara il nome della classe per il filtro
    filter_class_name = None
    filter_class_name_exact = None
    if filter_for_class:
        # Estrai il nome semplice della classe (senza package)
        filter_class_name_exact = filter_for_class.split('.')[-1]  # Nome esatto con _LLMCheck se presente
        # Per il filtro, usa anche il nome senza suffissi temporanei
        filter_class_name = filter_class_name_exact
        # Rimuovi suffissi temporanei come _LLMCheck se presenti
        if '_LLMCheck' in filter_class_name:
            filter_class_name = filter_class_name.replace('_LLMCheck', '')
        
    while i < len(lines):
        line = lines[i]
        
        # 1. Cerca errori di compilazione con formato standard Maven/Javac
        # Pattern: [ERROR] path/to/File.java:[line,col] message
        # Cattura qualsiasi errore, non solo "cannot find symbol"
        error_match = re.search(r'\[ERROR\]\s+([^:]+\.java):\[(\d+),(\d+)\]\s+(.*)', line, re.IGNORECASE)
        
        # Pattern alternativo senza [ERROR] standard (a volte capita)
        if not error_match and ".java:[" in line and "]" in line and not line.strip().startswith("[WARNING]"):
             error_match = re.search(r'\s*([^:]+\.java):\[(\d+),(\d+)\]\s+(.*)', line, re.IGNORECASE)

        if error_match:
            # Estrai dettagli
            error_file = error_match.group(1)
            error_line = error_match.group(2)
            error_col = error_match.group(3)
            error_message = error_match.group(4).strip()
            
            # Crea identità univoca per l'errore (file, riga, colonna, messaggio base)
            error_id = (error_file, error_line, error_col, error_message)
            if error_id in seen_identities:
                i += 1
                continue
            
            seen_identities.add(error_id)
            
            # Se è specificato un filtro per classe, verifica
            if filter_class_name_exact:
                error_file_base = error_file.replace('.java', '').split('/')[-1].split('\\')[-1]
                
                # Logica filtro classe (invariata)
                if '_LLMCheck' in filter_class_name_exact:
                    if filter_class_name_exact != error_file_base:
                        i += 1
                        continue
                else:
                    if filter_class_name_exact != error_file_base and filter_class_name != error_file_base:
                        i += 1
                        continue
            
            # Costruisci l'errore di base
            full_error = f"{error_file}:[{error_line}] {error_message}"
            
            # 2. Cattura dettagli su righe successive (es. symbol location, found/required types)
            details = []
            j = 1
            while i + j < len(lines):
                next_line = lines[i + j].strip()
                # Stop conditions: 
                # - Nuova riga di log [INFO], [ERROR], [WARNING] (a meno che non sia un dettaglio indentato con prefisso errore)
                # - Linea vuota
                if not next_line:
                    break
                    
                is_standard_log = next_line.startswith("[INFO]") or next_line.startswith("[ERROR]") or next_line.startswith("[WARNING]")
                
                # Gestione dettagli che potrebbero avere prefisso [ERROR] (nel riepilogo finale Maven)
                clean_next = next_line
                if next_line.startswith("[ERROR]"):
                    clean_next = next_line.replace("[ERROR]", "").strip()
                
                # Cattura linee che sembrano dettagli
                if (clean_next.startswith("symbol:") or 
                    clean_next.startswith("location:") or 
                    clean_next.startswith("found:") or 
                    clean_next.startswith("required:") or
                    (not is_standard_log and lines[i + j].startswith("    ")) # Indentazione significativa per linee non-log
                   ):
                    details.append(clean_next)
                    j += 1
                elif is_standard_log and not (clean_next.startswith("symbol:") or clean_next.startswith("location:")):
                    # È un vero nuovo log entry, stop
                    break
                else:
                    # Non sembra un dettaglio recognized
                    break
            
            if details:
                full_error += "\n  " + "\n  ".join(details)
                i += j - 1 # Avanza indice
            
            if full_error not in seen_errors:
                seen_errors.add(full_error)
                errors.append(full_error)
                
            i += 1
            continue

        # Gestione errori "cannot find symbol" generici senza formato standard (fallback legacy)
        if "[ERROR]" in line and "cannot find symbol" in line.lower() and not error_match:
             # ... vecchia logica se necessario, ma il pattern sopra dovrebbe catturarlo ...
             pass
        
        i += 1
    
    # Se non abbiamo trovato errori con "cannot find symbol", cerca altri errori di compilazione
    if not errors:
        capture = False
        for line in lines:
            if "[ERROR] COMPILATION ERROR" in line:
                capture = True
                continue
            if "[INFO] BUILD FAILURE" in line:
                capture = False
                break
            
            if capture and "[ERROR]" in line:
                # Se è specificato un filtro per classe, verifica che l'errore sia per quella classe
                if filter_class_name_exact:
                    # Estrai il nome del file dalla riga
                    file_match = re.search(r'([^/]+\.java):\[', line)
                    if file_match:
                        error_file = file_match.group(1)
                        error_file_base = error_file.replace('.java', '')
                        
                    
                        if '_LLMCheck' in filter_class_name_exact:
                            # Se stiamo verificando una classe temporanea, accetta SOLO errori per quella classe esatta
                            if filter_class_name_exact != error_file_base:
                                # Questo errore non è per la classe temporanea che stiamo verificando, salta
                                continue
                        else:
                            # Se non è una classe temporanea, accetta errori per la classe esatta o senza suffisso
                            if filter_class_name_exact != error_file_base and filter_class_name != error_file_base:
                                # Questo errore non è per la classe che stiamo verificando, salta
                                continue
                
                clean = line.replace("[ERROR]", "").strip()
                clean = re.sub(r'^.*?/([^/]+\.java:\[\d+,\d+\])', r'\1', clean)
                if clean and clean not in seen_errors:
                    seen_errors.add(clean)
                    errors.append(clean)
            
    if errors:
        return "\n".join(errors)
    
    return None


def estrai_test_con_errori_compilazione(
    test_output: str,
    codice_test: str,
    test_class_name: str = None
) -> List[str]:
    """
    Identifica quali test hanno errori di compilazione analizzando:
    1. L'output Maven per estrarre i numeri di riga con errori
    2. Il codice test con REGEX (non javalang!) per mappare righe -> nomi metodi @Test
    
    NOTA: Non usiamo javalang perché il codice non compila!
    """
    if not test_output or not codice_test:
        return []
    
    # Pulizia ANSI
    test_output = remove_ansi_colors(test_output)
    
    # 1. Estrai i numeri di riga con errori di compilazione dall'output Maven
    righe_con_errori = set()
    
    # Prepara filtro per nome classe
    filter_class_name = None
    if test_class_name:
        filter_class_name = test_class_name.split('.')[-1]
        if '_LLMCheck' in filter_class_name:
            filter_class_name = filter_class_name.replace('_LLMCheck', '')
    
    # Pattern multipli per catturare diversi formati di errore Maven
    error_patterns = [
        # Pattern 1: [ERROR] path/file.java:[line,col] message
        r'\[ERROR\]\s+([^:]+\.java):\[(\d+),\d+\]',
        # Pattern 2: [ERROR] path/file.java:line: message
        r'\[ERROR\]\s+([^:]+\.java):(\d+):',
        # Pattern 3: file.java:[line,col] message (senza [ERROR])
        r'([^\s:]+\.java):\[(\d+),\d+\]',
        # Pattern 4: file.java:line: message (senza [ERROR])
        r'([^\s:]+\.java):(\d+):',
    ]
    
    for line in test_output.split('\n'):
        for pattern in error_patterns:
            error_match = re.search(pattern, line)
            if error_match:
                error_file = error_match.group(1)
                error_line = int(error_match.group(2))
                
                # Se c'è un filtro, verifica che l'errore sia per la classe corretta
                if filter_class_name:
                    error_file_base = error_file.replace('.java', '').split('/')[-1].split('\\')[-1]
                    # Accetta errori per la classe esatta o varianti con suffissi
                    if filter_class_name not in error_file_base:
                        continue
                
                righe_con_errori.add(error_line)
                break  # Trovato match, passa alla prossima riga
    
    
    if not righe_con_errori:
        # Fallback: se non troviamo righe specifiche, prova a estrarre qualsiasi errore
        # Cerca pattern generici di errore Java
        generic_error_patterns = [
            r'cannot find symbol',
            r'error:',
            r'incompatible types',
            r'method .+ cannot be applied',
            r'constructor .+ cannot be applied',
        ]
        has_errors = any(re.search(p, test_output, re.IGNORECASE) for p in generic_error_patterns)
        if has_errors:
            # Se ci sono errori ma non riusciamo a identificare le righe,
            # marca TUTTI i test come con errori (approccio conservativo)
            all_tests = _estrai_nomi_test_con_regex(codice_test)
            return all_tests
        return []
    
    # 2. Usa SOLO regex per mappare righe -> nomi metodi @Test
    # (Non usiamo javalang perché il codice non compila!)
    test_con_errori = []
    
    # Trova tutti i metodi @Test e le loro posizioni usando regex
    test_methods = _estrai_metodi_test_con_posizioni(codice_test)
    
    # Controlla se qualche riga con errore è dentro un metodo test
    for method in test_methods:
        for error_line in righe_con_errori:
            if method['start_line'] <= error_line <= method['end_line']:
                if method['name'] not in test_con_errori:
                    test_con_errori.append(method['name'])
                break
    
    # Se abbiamo righe di errore ma nessun test identificato,
    # potrebbe essere un errore fuori dai metodi (es. import, campo classe)
    if righe_con_errori and not test_con_errori:
        # Ritorna marcatore speciale per indicare che la suite ha errori strutturali
        # e deve essere scartata invece di interrompere l'esperimento
        return ["__STRUCTURAL_ERROR__"]
    
    return test_con_errori


def _estrai_nomi_test_con_regex(codice: str) -> List[str]:
    """Estrae i nomi di tutti i metodi @Test usando regex
    Gestisce anche sintassi JUnit 4: @Test(expected=...)"""
    # Pattern che gestisce @Test con o senza parametri (es. @Test(expected=...))
    pattern = r'@Test\s*(?:\([^)]*\))?\s*(?:@[^\n]*\n)*\s*(?:public\s+)?(?:void\s+)?(\w+)\s*\('
    return [m.group(1) for m in re.finditer(pattern, codice, re.MULTILINE)]


def _estrai_metodi_test_con_posizioni(codice: str) -> List[dict]:
    """
    Estrae i metodi @Test con le loro posizioni di riga (inizio e fine).
    Usa SOLO regex, non javalang.
    Gestisce anche sintassi JUnit 4: @Test(expected=...)
    """
    methods = []
    lines = codice.split('\n')
    
    # Pattern per trovare @Test seguito da dichiarazione metodo
    # Gestisce: @Test void name(), @Test public void name(), @Test\npublic void name() throws X
    # E ANCHE: @Test(expected=...) void name() - sintassi JUnit 4
    pattern = r'@Test\s*(?:\([^)]*\))?\s*(?:@[^\n]*\n\s*)*(?:public\s+)?(?:void\s+)?(\w+)\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{'
    
    for match in re.finditer(pattern, codice, re.MULTILINE | re.DOTALL):
        method_name = match.group(1)
        
        # Calcola riga di inizio (1-indexed)
        start_pos = match.start()
        start_line = codice[:start_pos].count('\n') + 1
        
        # Trova la fine del metodo contando le parentesi graffe
        method_text = codice[match.start():]
        brace_count = 0
        in_method = False
        end_pos = 0
        
        # Gestisce stringhe per evitare di contare { } dentro stringhe
        in_string = False
        string_char = None
        
        for i, char in enumerate(method_text):
            # Gestisci stringhe
            if not in_string:
                if char in ('"', "'"):
                    in_string = True
                    string_char = char
            else:
                if char == '\\' and i + 1 < len(method_text):
                    continue  # Skip escaped character
                if char == string_char:
                    in_string = False
                    string_char = None
                continue
            
            # Conta parentesi graffe
            if char == '{':
                brace_count += 1
                in_method = True
            elif char == '}':
                brace_count -= 1
                if in_method and brace_count == 0:
                    end_pos = match.start() + i
                    break
        
        # Calcola riga di fine (1-indexed)
        # Se end_pos è 0 significa che non abbiamo trovato la fine del metodo
        if end_pos > 0:
            end_line = codice[:end_pos + 1].count('\n') + 1
        else:
            # Fallback: usa la fine del file o stima basata sulla posizione
            # Cerca la prossima @Test o la fine del file
            next_test = re.search(r'@Test', codice[match.end():])
            if next_test:
                end_line = codice[:match.end() + next_test.start()].count('\n')
            else:
                end_line = len(lines)  # Ultima riga del file
        
        # Verifica che end_line >= start_line (sanity check)
        if end_line < start_line:
            end_line = start_line + 10  # Stima conservativa
        
        methods.append({
            'name': method_name,
            'start_line': start_line,
            'end_line': end_line
        })
    
    return methods