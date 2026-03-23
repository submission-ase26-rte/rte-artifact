import re
from typing import List, Optional, Tuple


def is_abstract_class(codice_java: str) -> bool:
    """
    Verifica se una classe Java è astratta.
    """
    if not codice_java:
        return False
    
    try:
        import javalang
        tree = javalang.parse.parse(codice_java)
        for path, node in tree:
            if isinstance(node, javalang.tree.ClassDeclaration):
                if hasattr(node, 'modifiers') and node.modifiers:
                    if 'abstract' in node.modifiers:
                        return True
        return False
    except Exception:
        # Fallback: usa regex per trovare "abstract class"
        pattern = r'\babstract\s+class\s+\w+'
        return bool(re.search(pattern, codice_java, re.MULTILINE))


def is_only_braces_or_empty(line: str) -> bool:
    """Verifica se una riga contiene solo parentesi graffe, spazi o è vuota"""
    stripped = line.strip()
    if not stripped:
        return True
    return bool(re.match(r'^[\s{}]+$', stripped))


def is_only_else_or_control_structure(line: str) -> bool:
    """Verifica se una riga contiene solo parole chiave di controllo senza codice eseguibile"""
    stripped = line.strip()
    if not stripped:
        return False
    
    # Rimuovi stringhe e commenti per verificare solo il codice
    line_no_strings = re.sub(r'"[^"]*"', '""', stripped)
    line_no_strings = re.sub(r"'[^']*'", "''", line_no_strings)
    
    if '//' in line_no_strings:
        line_no_strings = line_no_strings.split('//')[0].strip()
    if '/*' in line_no_strings:
        line_no_strings = line_no_strings.split('/*')[0].strip()
    
    control_keywords = [
        r'^else\s*$',
        r'^else\s*\{',
        r'^else\s+if\s*\(',
        r'^catch\s*\(',
        r'^finally\s*\{',
        r'^catch\s*\{',
        r'^\}\s*else\s*\{',
        r'^\}\s*else\s+if\s*\(',
        r'^\}\s*else\s*$',
        r'^[\s}]*else\s*[\s{}]*$',
    ]
    
    return any(re.match(pattern, line_no_strings, re.IGNORECASE) for pattern in control_keywords)


def is_only_comment(line: str) -> bool:
    """Verifica se una riga contiene solo commenti"""
    stripped = line.strip()
    if not stripped:
        return False
    
    if stripped.startswith('//') or stripped.startswith('/*'):
        return True
    
    # Verifica commenti inline
    line_no_strings = re.sub(r'"[^"]*"', '""', stripped)
    line_no_strings = re.sub(r"'[^']*'", "''", line_no_strings)
    
    if '//' in line_no_strings:
        before_comment = line_no_strings.split('//')[0].strip()
        if not before_comment or before_comment in ['{', '}', ';']:
            return True
    
    if '/*' in line_no_strings:
        before_comment = line_no_strings.split('/*')[0].strip()
        if not before_comment or before_comment in ['{', '}', ';']:
            return True
    
    return False

def valida_sintassi_java(codice_frammento: str) -> bool:
    """
    Verifica se un frammento di codice (es. un metodo) è sintatticamente valido.
    Lo avvolge in una classe dummy per il parsing.
    """
    if not codice_frammento or not codice_frammento.strip():
        return False
        
    try:
        import javalang
        # Wrap in a dummy class
        dummy_class = f"public class Dummy {{ {codice_frammento} }}"
        javalang.parse.parse(dummy_class)
        return True
    except Exception as e:
        # print(f"Syntax Validation Failed: {e}")
        return False


def aggiungi_commenti_righe_non_coperte(
    codice_metodo: str,
    righe_non_coperte: List[int],
    riga_inizio_metodo: int = 1,
    codice_classe_completo: Optional[str] = None
) -> str:
    """
    Aggiunge commenti a fianco delle righe non coperte nel codice del metodo.
    """
    if not righe_non_coperte:
        return codice_metodo
    
    lines = codice_metodo.split('\n')
    righe_non_coperte_set = set(righe_non_coperte)
    class_lines = codice_classe_completo.split('\n') if codice_classe_completo else None
    
    metodo_lines = []
    for i, line in enumerate(lines):
        # Calcola la riga nel file originale: riga_inizio_metodo è la prima riga del metodo estratto (con JavaDoc)
        riga_file_originale = riga_inizio_metodo + i
        
        if riga_file_originale in righe_non_coperte_set:
            # Usa la riga dalla classe originale se disponibile, altrimenti usa la riga del metodo estratto
            if class_lines and 1 <= riga_file_originale <= len(class_lines):
                line_to_check = class_lines[riga_file_originale - 1]
            else:
                line_to_check = line
            
            # Non aggiungere commenti su righe che sono solo commenti, parentesi graffe, o strutture di controllo
            if is_only_comment(line_to_check) or is_only_braces_or_empty(line_to_check) or is_only_else_or_control_structure(line_to_check):
                metodo_lines.append(line)
            else:
                stripped = line.rstrip()
                if stripped:
                    # Aggiungi il commento solo se la riga non finisce già con un commento
                    if '//' not in stripped or stripped.rstrip().endswith('//'):
                        metodo_lines.append(f"{stripped} // NOT COVERED BY TESTS")
                    else:
                        # Se c'è già un commento, aggiungi prima di quello esistente
                        parts = stripped.rsplit('//', 1)
                        if len(parts) == 2:
                            metodo_lines.append(f"{parts[0].rstrip()} // NOT COVERED BY TESTS // {parts[1].strip()}")
                        else:
                            metodo_lines.append(f"{stripped} // NOT COVERED BY TESTS")
                else:
                    metodo_lines.append(line)
        else:
            metodo_lines.append(line)
    
    return '\n'.join(metodo_lines)


def estrai_tipi_parametri_da_signature(sig: str) -> List[str]:
    """
    Estrae i tipi dei parametri dalla signature (es: 'hasOption(String opt)' -> ['String']).
    Funzione unificata per evitare duplicazione.
    """
    if not sig:
        return []
    match = re.search(r'\(([^)]*)\)', sig)
    if not match:
        return []
    params_str = match.group(1).strip()
    if not params_str:
        return []
    params = [p.strip() for p in params_str.split(',')]
    # Estrai solo il tipo, ignora il nome del parametro
    types = []
    for p in params:
        parts = p.split()
        if parts:
            # Il tipo può essere composto (es: "byte[]", "String...", "Map<String, Object>")
            # Prendi tutto tranne l'ultima parola (che è il nome del parametro)
            if len(parts) > 1:
                tipo = ' '.join(parts[:-1])
            else:
                # Solo il tipo senza nome parametro (es: "byte[]")
                tipo = parts[0]
            types.append(tipo)
    return types


def normalizza_tipo_java(tipo_str: str) -> str:
    """
    Normalizza un tipo Java per il confronto.
    Funzione unificata per evitare duplicazione.
    """
    tipo = tipo_str.strip()
    # Rimuovi generics (es: List<String> -> List)
    if '<' in tipo:
        tipo = tipo.split('<')[0]
    # Rimuovi 'final' keyword (es: 'final Options' -> 'Options')
    if tipo.startswith('final '):
        tipo = tipo[6:].strip()
    # Rimuovi package (es: java.lang.String -> String)
    if '.' in tipo and '[' not in tipo:
        tipo = tipo.split('.')[-1]
    # Gestisci varargs (String... -> String[])
    tipo = tipo.replace('...', '[]')
    return tipo


def _parse_java_params_string(params_str: str) -> List[str]:
    """
    Parsa una stringa di parametri Java (es: "final int a, List<String> b")
    e restituisce una lista di tipi normalizzati (es: ["int", "List"]).
    Gestisce generics annidati e rimuove 'final'.
    """
    if not params_str:
        return []
    
    types = []
    depth = 0
    current_param = ""
    
    def process_param(p_str):
        p_str = p_str.strip()
        if not p_str:
            return None
        parts = p_str.split()
       
        
        if len(parts) > 1:
            tipo = ' '.join(parts[:-1])
        else:
            tipo = parts[0] if parts else ""
        
        tipo = tipo.replace('...', '[]')

        if '.' in tipo and '[' not in tipo and '<' not in tipo:
             tipo = tipo.split('.')[-1]
        return tipo

    for char in params_str:
        if char == '<':
            depth += 1
        elif char == '>':
            depth -= 1
        elif char == ',' and depth == 0:
            t = process_param(current_param)
            if t: types.append(t)
            current_param = ""
            continue
        current_param += char
    
    t = process_param(current_param)
    if t: types.append(t)
    
    return types

def estrai_metodo_completo(codice_classe: str, nome_metodo: str, signature: str = None) -> Tuple[str, int, int]:
    """
    Estrae un metodo completo dalla classe, includendo eventuali annotazioni.
    """
    lines = codice_classe.split('\n')
    
    # Estrai tipi parametri dalla signature se fornita
    signature_param_types = []
    if signature:
        import re as regex_module
        param_match = regex_module.search(r'\(([^)]*)\)', signature)
        if param_match:
            params_str = param_match.group(1).strip()
            # Usa il parser robusto anche per la signature di input
            signature_param_types = _parse_java_params_string(params_str)
            # Ulteriore normalizzazione per matchare la logica interna (rimozione package aggressiva)
            normalized = []
            for t in signature_param_types:
                 if '.' in t and '[' not in t and '<' not in t:
                      t = t.split('.')[-1]
                 normalized.append(t)
            signature_param_types = normalized
    
    # Trova la riga che contiene la signature del metodo
    metodo_pattern = rf'(public|private|protected)?\s*(static)?\s*[\w<>\[\],\s\.]+\s+{re.escape(nome_metodo)}\s*\('
    
    candidate_lines = []
    for i, line in enumerate(lines):
        if re.search(metodo_pattern, line):
            candidate_lines.append(i)
    
    if not candidate_lines:
        return "", -1, -1
    
    # Se abbiamo signature_param_types, cerca il metodo con i parametri giusti
    start_line = -1
    if signature_param_types is not None and len(candidate_lines) >= 1: # Check anche se len=1 per sicurezza
        for line_idx in candidate_lines:
            # Estrai la linea di signature (potrebbe estendersi su più righe)
            sig_line = lines[line_idx]
            # Trova la parentesi chiusa
            paren_count = 0
            end_sig_idx = line_idx
            # Increase scan range to 50 lines to handle long signatures with many params
            for j in range(line_idx, min(line_idx + 50, len(lines))):
                for char in lines[j]:
                    if char == '(':
                        paren_count += 1
                    elif char == ')':
                        paren_count -= 1
                        if paren_count == 0:
                            end_sig_idx = j
                            break
                if paren_count == 0:
                    break
            
            # Costruisci la signature completa
            full_sig = ' '.join(lines[line_idx:end_sig_idx+1])
            
            # Estrai i tipi dei parametri dalla signature nel codice
            param_match = re.search(r'\(([^)]*)\)', full_sig)
            if param_match:
                code_params_str = param_match.group(1).strip()
                # Usa lo stesso parser robusto
                extracted_types = _parse_java_params_string(code_params_str)
                
                # Confronta
                if len(extracted_types) == len(signature_param_types):
                    match = True
                    for et, st in zip(extracted_types, signature_param_types):
                        # Normalizza 'final' per il confronto (robustezza overload)
                        et_clean = et.replace('final ', '').strip()
                        st_clean = st.replace('final ', '').strip()
                        if et_clean != st_clean:
                            match = False
                            break
                    if match:
                        start_line = line_idx
                        break
    elif not signature:
         # Fallback classico se signature non fornita
         start_line = candidate_lines[0]

    if start_line == -1:
        # Se signature fornita e nessun match -> Nessun metodo trovato (evita overwrite sbagliati)
        if signature:
            # print(f"Warning: Signature match failed for {signature}")
            return "", -1, -1
        start_line = candidate_lines[0]
    
    # Cerca JavaDoc e annotazioni prima del metodo (risali le righe)
    annotation_start = start_line
    
    # Risali riga per riga e include tutto ciò che è JavaDoc, annotazione o riga vuota
    for i in range(start_line - 1, -1, -1):
        stripped = lines[i].strip()
        
        # Include JavaDoc (inizio, contenuto, fine)
        if stripped.startswith('/**') or stripped.startswith('*') or '*/' in stripped:
            annotation_start = i
            continue
        
        # Include annotazioni (@Override, @Deprecated, etc.)
        if stripped.startswith('@'):
            annotation_start = i
            continue
        
        # Include righe vuote (potrebbero essere tra JavaDoc e annotazioni o tra annotazioni e metodo)
        if stripped == '':
            annotation_start = i
            continue
        
        # Include commenti singoli (//)
        if stripped.startswith('//'):
            annotation_start = i
            continue
        
        # Se troviamo una riga non vuota che non è JavaDoc/annotazione/commento, fermiamoci
        if stripped:
            break
    
    # Rimuovi le righe vuote all'inizio (ma mantieni JavaDoc e annotazioni)
    while annotation_start < start_line and lines[annotation_start].strip() == '':
        annotation_start += 1
    
    # Trova la fine del metodo contando le parentesi graffe
    brace_count = 0
    in_method = False
    end_line = start_line
    
    for i in range(start_line, len(lines)):
        line = lines[i]
        for char in line:
            if char == '{':
                brace_count += 1
                in_method = True
            elif char == '}':
                brace_count -= 1
                if in_method and brace_count == 0:
                    end_line = i
                    break
        if in_method and brace_count == 0:
            break
    
    # Estrai il metodo completo
    metodo_lines = lines[annotation_start:end_line + 1]
    metodo_codice = '\n'.join(metodo_lines)
    
    # Calcola gli indici nel testo originale
    indice_inizio = sum(len(lines[i]) + 1 for i in range(annotation_start))
    indice_fine = sum(len(lines[i]) + 1 for i in range(end_line + 1))
    
    return metodo_codice, annotation_start, end_line


def _estrai_signature_da_nuovo_metodo(nuovo_metodo: str, nome_metodo: str) -> Optional[str]:
    """Estrae la signature dal codice del nuovo metodo per individuare l'overload corretto."""
    try:
        # Usa regex per estrarre la parte dei parametri
        # Cerca: nome_metodo \s* ( ... )
        import re as regex_module
        pattern = rf'\b{regex_module.escape(nome_metodo)}\s*\(([^)]*)\)'
        match = regex_module.search(pattern, nuovo_metodo)
        if match:
             params_str = match.group(1)
             # Restituisci signature ricostruita in modo che estrai_metodo_completo la possa parsare
             return f"{nome_metodo}({params_str})"
    except Exception as e:
        print(f"Warning: Error extracting signature from new method (regex): {e}")
    
    return None

def sostituisci_metodo_in_classe(codice_classe: str, nome_metodo: str, nuovo_metodo: str) -> str:
    """
    Sostituisce un metodo nella classe con un nuovo metodo.
    """
    # Prova a dedurre la signature dal nuovo metodo per trovare l'overload corrispondente
    signature = _estrai_signature_da_nuovo_metodo(nuovo_metodo, nome_metodo)
    
    metodo_originale, start_line, end_line = estrai_metodo_completo(codice_classe, nome_metodo, signature=signature)
    
    if start_line == -1:
        print(f"WARNING: Method {nome_metodo} not found in class (signature: {signature})")
        # Se fallisce con signature, riprova senza (fallback rischioso ma meglio di nulla se signature dedotta male)
        if signature:
             print("Fallback attempt without signature...")
             metodo_originale, start_line, end_line = estrai_metodo_completo(codice_classe, nome_metodo, signature=None)
             if start_line == -1:
                  return codice_classe
        else:
             return codice_classe
    
    lines = codice_classe.split('\n')
    
    # Ricostruisci la classe sostituendo il metodo
    # Il nuovo_metodo viene inserito così com'è (mantiene la sua indentazione originale)
    nuova_classe_lines = lines[:start_line] + [nuovo_metodo.rstrip()] + lines[end_line + 1:]
    
    return '\n'.join(nuova_classe_lines)


def backup_metodo_originale(percorso_classe: str, nome_metodo: str, metodo_signature: str = None) -> str:
    """
    Fa il backup del metodo originale dalla classe, includendo sempre la JavaDoc se presente.
    """
    with open(percorso_classe, 'r', encoding='utf-8') as f:
        codice_classe = f.read()
    
    # Usa estrai_metodo_singolo con include_javadoc=True per includere sempre la JavaDoc
    from core.evaluation.evaluation import estrai_metodo_singolo
    metodo_originale = estrai_metodo_singolo(codice_classe, nome_metodo, include_javadoc=True, signature=metodo_signature)
    
    if not metodo_originale:
        # Fallback a estrai_metodo_completo se estrai_metodo_singolo fallisce
        metodo_originale, _, _ = estrai_metodo_completo(codice_classe, nome_metodo)
        if not metodo_originale:
            print(f"WARNING: Method {nome_metodo} not found for backup")
            return ""
    
    return metodo_originale


def sostituisci_metodo_in_file(percorso_classe: str, nome_metodo: str, nuovo_metodo: str) -> bool:
    """
    Sostituisce un metodo direttamente nel file della classe.
    """
    try:
        with open(percorso_classe, 'r', encoding='utf-8') as f:
            codice_classe = f.read()
        
        codice_modificato = sostituisci_metodo_in_classe(codice_classe, nome_metodo, nuovo_metodo)
        
        with open(percorso_classe, 'w', encoding='utf-8') as f:
            f.write(codice_modificato)
        
        return True
    except Exception as e:
        print(f" Error replacing method in file: {e}")
        return False


def ripristina_metodo_originale(percorso_classe: str, nome_metodo: str, metodo_originale: str) -> bool:
    """
    Ripristina il metodo originale nella classe.
    """
    if not metodo_originale:
        print(f"WARNING: No original method to restore for {nome_metodo}")
        return False
    
    return sostituisci_metodo_in_file(percorso_classe, nome_metodo, metodo_originale)


def estrai_solo_metodo_da_risposta(risposta_llm: str, nome_metodo: str) -> str:
    """
    Estrae SOLO il metodo dalla risposta dell'LLM.
    L'LLM potrebbe restituire:
    1. Solo il metodo
    2. Il metodo dentro un blocco di codice markdown
    3. Il metodo con commenti/spiegazioni
    4. Il metodo con metodi helper aggiuntivi (che devono essere IGNORATI)
    """
    # Rimuovi blocchi markdown se presenti
    codice = risposta_llm
    
    # Estrai da blocco ```java ... ```
    pattern_java_block = r'```(?:java)?\s*\n(.*?)```'
    matches = re.findall(pattern_java_block, codice, re.DOTALL)
    if matches:
        # Prendi il primo blocco che contiene il metodo
        for match in matches:
            if nome_metodo in match:
                codice = match
                break
        else:
            codice = matches[0]
    
    # Se il codice contiene una classe completa, estrai solo il metodo
    if 'class ' in codice and '{' in codice:
        # Prova ad estrarre il metodo dalla classe
        metodo, _, _ = estrai_metodo_completo(codice, nome_metodo)
        if metodo:
            return metodo.strip()
    
    # Cerca il pattern del metodo direttamente
    # Pattern: [annotazioni] [modificatori] tipo nomeMetodo(params) { ... }
    pattern_metodo = rf'(@\w+(?:\([^)]*\))?\s*)*\s*(public|private|protected)?\s*(static)?\s*\w+(?:<[^>]+>)?\s+{re.escape(nome_metodo)}\s*\([^)]*\)\s*(?:throws\s+[\w,\s]+)?\s*\{{'
    
    match = re.search(pattern_metodo, codice, re.DOTALL)
    if match:
        start = match.start()
        # Trova la fine del metodo contando le parentesi
        brace_count = 0
        in_method = False
        end = len(codice)
        
        for i in range(match.end() - 1, len(codice)):
            if codice[i] == '{':
                brace_count += 1
                in_method = True
            elif codice[i] == '}':
                brace_count -= 1
                if in_method and brace_count == 0:
                    end = i + 1
                    break
        
        return codice[start:end].strip()
    
    # === FALLBACK MIGLIORATO ===
    # Se non troviamo il metodo con il pattern esatto, cerca qualsiasi metodo nel codice
    # e prendi SOLO quello con il nome target, ignorando tutti gli altri metodi helper
    
    # Pattern generico per trovare TUTTI i metodi nel codice
    pattern_any_method = r'(@\w+(?:\([^)]*\))?\s*)*\s*(public|private|protected)?\s*(static)?\s*[\w<>\[\],\s\.]+\s+(\w+)\s*\([^{]*\)\s*(?:throws\s+[\w,\s]+)?\s*\{'
    
    all_methods = list(re.finditer(pattern_any_method, codice, re.DOTALL))
    
    for match in all_methods:
        method_name = match.group(4)  # Il nome del metodo è nel gruppo 4
        if method_name == nome_metodo:
            start = match.start()
            # Trova la fine del metodo contando le parentesi
            brace_count = 0
            in_method = False
            end = len(codice)
            
            for i in range(match.end() - 1, len(codice)):
                if codice[i] == '{':
                    brace_count += 1
                    in_method = True
                elif codice[i] == '}':
                    brace_count -= 1
                    if in_method and brace_count == 0:
                        end = i + 1
                        break
            
            return codice[start:end].strip()
    
    # Se non troviamo il metodo, NON restituire tutto il codice (potrebbe contenere helper)
    # Invece restituisci stringa vuota per indicare fallimento
    print(f"WARNING: Method '{nome_metodo}' not found in LLM response")
    return ""


def get_abstract_methods(codice_classe: str) -> List[dict]:
    """
    Analizza il codice della classe e restituisce una lista di dizionari per ogni metodo astratto.
    Ogni dict contiene: 'name', 'return_type', 'parameters' (lista di stringhe "Type name"), 'exceptions'.
    """
    abstract_methods = []
    try:
        import javalang
        tree = javalang.parse.parse(codice_classe)
        for path, node in tree:
            if isinstance(node, javalang.tree.MethodDeclaration):
                if 'abstract' in node.modifiers:
                    # Estrai info
                    return_type = node.return_type.name if node.return_type else "void"
                    # Gestione array/generics nel return type (semplificata)
                    if node.return_type and hasattr(node.return_type, 'dimensions') and node.return_type.dimensions:
                        return_type += '[]' * len(node.return_type.dimensions)
                    # Gestione generics (es List<String>) - javalang li ha in arguments
                    if node.return_type and hasattr(node.return_type, 'arguments') and node.return_type.arguments:
                        # Ricostruzione base dei generics (non perfetta ma sufficiente per i tipi comuni)
                        pass # Manteniamo il nome base per semplicità, es "List"

                    params = []
                    if node.parameters:
                        for p in node.parameters:
                            p_type = p.type.name
                            if hasattr(p.type, 'dimensions') and p.type.dimensions:
                                p_type += '[]' * len(p.type.dimensions)
                            if hasattr(p, 'varargs') and p.varargs:
                                p_type += '...'
                            params.append(f"{p_type} {p.name}")
                    
                    exceptions = node.throws if node.throws else []
                    
                    abstract_methods.append({
                        'name': node.name,
                        'return_type': return_type,
                        'parameters': params,
                        'exceptions': exceptions,
                        'signature': f"{node.name}({', '.join(params)})"
                    })
    except Exception as e:
        print(f"Warning: Error analyzing abstract methods: {e}")
    
    return abstract_methods


def generate_abstract_class_instantiation(nome_classe: str, codice_classe: str) -> str:
    """
    Genera il codice Java per istanziare una classe astratta tramite classe anonima,
    implementando tutti i metodi astratti con logica dummy intelligente.
    """
    methods = get_abstract_methods(codice_classe)
    
    sb = [f"new {nome_classe}() {{"]
    
    for m in methods:
        sb.append(f"    @Override")
        # Ricostruisci declaration
        throws_part = ""
        if m['exceptions']:
            throws_part = " throws " + ", ".join(m['exceptions'])
        
        signature = f"    public {m['return_type']} {m['name']}({', '.join(m['parameters'])}){throws_part} {{"
        sb.append(signature)
        
        # Logica di ritorno dummy
        ret = m['return_type']
        
        # 1. Void
        if ret == 'void':
            pass # Empty body
            
        else:
            returned = False
            # Cerca un parametro con lo stesso tipo di ritorno
            for p in m['parameters']:
                # p stringa è "Type name"
                p_parts = p.split()
                p_type = p_parts[0] # Approssimativo
                p_name = p_parts[-1]
                
                # Normalizza tipi per confronto (rimuovi package)
                norm_ret = ret.split('.')[-1]
                norm_p = p_type.split('.')[-1]
                
                if norm_ret == norm_p:
                    sb.append(f"        return {p_name};")
                    returned = True
                    break
            
            if not returned:
                # 3. Dummy values based on type
                if ret in ['boolean', 'Boolean']:
                    sb.append("        return false;")
                elif ret in ['int', 'Integer', 'byte', 'Byte', 'short', 'Short']:
                    sb.append("        return 0;")
                elif ret in ['long', 'Long']:
                    sb.append("        return 0L;")
                elif ret in ['double', 'Double']:
                    sb.append("        return 0.0;")
                elif ret in ['float', 'Float']:
                    sb.append("        return 0.0f;")
                elif ret in ['char', 'Character']:
                    sb.append("        return '\\u0000';")
                elif ret == 'String':
                    sb.append('        return "";')
                elif 'List' in ret:
                    sb.append("        return java.util.Collections.emptyList();")
                elif 'Set' in ret:
                    sb.append("        return java.util.Collections.emptySet();")
                elif 'Map' in ret:
                    sb.append("        return java.util.Collections.emptyMap();")
                elif 'Iterator' in ret:
                    sb.append("        return java.util.Collections.emptyIterator();")
                elif 'Collection' in ret or 'Iterable' in ret:
                    sb.append("        return java.util.Collections.emptyList();")
                elif ret.endswith('[]'):
                    # Array vuoto: new Type[0]
                    base_type = ret[:-2]
                    sb.append(f"        return new {base_type}[0];")
                else:
                    # Oggetti generici: null (come ultima risorsa, anche se l'utente ha detto no null per coll/arrays, qui è generic obj)
                    sb.append("        return null;")
        
        sb.append("    }")
        sb.append("")
        
    sb.append("}")
    return "\n".join(sb)

