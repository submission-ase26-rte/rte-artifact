import re

def pulisci_risposta_llm(testo: str) -> str:
    """
    Rimuove tutti i marker di codice (```java, ```, etc.) dalla risposta LLM.
    """
    if not testo:
        return testo
    
    testo_originale = testo
    codice = testo.strip()
    
    # Prova prima a estrarre blocchi ```java (più specifico)
    matches_java = re.findall(r'```java\s*\n?(.*?)```', testo, flags=re.DOTALL)
    if matches_java:
        codice = "\n".join(m.strip() for m in matches_java).strip()
    else:
        # Prova pattern generico per qualsiasi blocco di codice markdown (```lang o ```)
        matches_generic = re.findall(r'```\w*\s*\n?(.*?)```', testo, flags=re.DOTALL)
        if matches_generic:
            codice = "\n".join(m.strip() for m in matches_generic).strip()
        else:
            # Se non trova blocchi markdown, il testo potrebbe essere già pulito
            # Verifica se ci sono marker residui all'inizio/fine
            codice = testo.strip()
            # Rimuovi eventuali marker residui all'inizio
            codice = re.sub(r'^```\w*\s*\n?', '', codice, flags=re.MULTILINE)
            # Rimuovi eventuali marker residui alla fine
            codice = re.sub(r'\n?```\s*$', '', codice, flags=re.MULTILINE)
            codice = codice.strip()
    
    # Se il codice estratto è vuoto o troppo corto rispetto all'originale, usa l'originale
    if not codice or (len(codice) < 50 and len(testo_originale) > 100):
        codice = testo_originale.strip()
        # Rimuovi solo i marker all'inizio e alla fine
        codice = re.sub(r'^```\w*\s*\n?', '', codice, flags=re.MULTILINE)
        codice = re.sub(r'\n?```\s*$', '', codice, flags=re.MULTILINE)
        codice = codice.strip()
    
    return codice


def estrai_codice_java(testo: str, nome_classe_attesa: str = None) -> str:
    """
    Estrae il blocco di codice Java da una risposta del modello.
    Se nome_classe_attesa è fornito, estrae solo la classe che corrisponde a quel nome.
    """
    # Prima pulisci la risposta LLM rimuovendo tutti i marker di codice
    testo_pulito = pulisci_risposta_llm(testo)
    
    # Estrae il blocco di codice Java da una risposta del modello.
    # Se non trova blocchi ```java ... ```, restituisce il testo intero.
    matches = re.findall(r"```java(.*?)```", testo_pulito, re.DOTALL)
    codice = "\n".join(m.strip() for m in matches).strip()
    
    # Se non ci sono blocchi ```java```, prova a estrarre il codice direttamente dal testo pulito
    if not codice:
        codice = testo_pulito.strip()
    
    # Se è specificato un nome classe attesa, estrae solo quella classe
    if nome_classe_attesa and codice:
        codice = estrai_solo_classe(codice, nome_classe_attesa)
    
    return codice


def estrai_solo_metodo(codice: str) -> str:
    """
    Estrae solo il metodo da una risposta LLM che potrebbe contenere:
    - import statements
    - package declarations
    - commenti
    - annotazioni
    
    Restituisce solo il corpo del metodo (dalla signature alla } finale).
    Utile per la fase di rigenerazione metodo dove serve SOLO il metodo.
    """
    if not codice:
        return codice
    
    # Prima pulisci il codice
    codice = codice.strip()
    
    # Rimuovi package declaration
    codice = re.sub(r'^package\s+[\w.]+;\s*\n?', '', codice, flags=re.MULTILINE)
    
    # Rimuovi tutti gli import (singoli e multipli)
    codice = re.sub(r'^import\s+(?:static\s+)?[\w.*]+;\s*\n?', '', codice, flags=re.MULTILINE)
    
    # Rimuovi righe vuote multiple all'inizio
    codice = re.sub(r'^\s*\n+', '', codice)
    
    # Cerca l'inizio del metodo - pattern semplificato
    method_pattern = r'((?:@\w+(?:\([^)]*\))?\s*)*(?:public|private|protected)?\s*(?:static\s+)?(?:final\s+)?[\w<>\[\],\s]+\s+\w+\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{)'
    
    method_match = re.search(method_pattern, codice, re.MULTILINE)
    
    if method_match:
        method_start = method_match.start()
        
        # Trova la fine del metodo (parentesi graffa chiusa corrispondente)
        brace_count = 0
        in_method = False
        method_end = len(codice)
        
        for i in range(method_match.end() - 1, len(codice)):
            char = codice[i]
            if char == '{':
                brace_count += 1
                in_method = True
            elif char == '}':
                brace_count -= 1
                if in_method and brace_count == 0:
                    method_end = i + 1
                    break
        
        metodo_estratto = codice[method_start:method_end].strip()
        return metodo_estratto
    
    return codice.strip()


def estrai_solo_classe(codice_java: str, nome_classe_attesa: str) -> str:
    """
    Estrae solo la classe specificata dal codice Java.
    Utile quando l'LLM genera più classi (es. test suite + classe originale).
    IMPORTANTE: Preserva i commenti JavaDoc che precedono la classe.
    """
    if not codice_java or not nome_classe_attesa:
        return codice_java
    
    # Trova tutte le classi nel codice
    class_pattern = r'((?:public\s+|private\s+|protected\s+|abstract\s+|final\s+|static\s+)*class\s+)(\w+)(\s*(?:extends|implements|\{))'
    class_matches = list(re.finditer(class_pattern, codice_java, re.MULTILINE))
    
    if not class_matches:
        return codice_java
    
    # Se c'è solo una classe e corrisponde a quella attesa, restituisci tutto il codice
    if len(class_matches) == 1 and class_matches[0].group(2) == nome_classe_attesa:
        return codice_java
    
    # Trova la classe attesa
    target_class_match = None
    target_class_index = -1
    for idx, match in enumerate(class_matches):
        if match.group(2) == nome_classe_attesa:
            target_class_match = match
            target_class_index = idx
            break
    
    if not target_class_match:
        # Se non trova la classe attesa, restituisci il codice originale
        # Potrebbe essere che il nome non corrisponda esattamente
        return codice_java
    
    # Trova l'inizio della classe target (inclusi eventuali commenti JavaDoc)
    start_pos = target_class_match.start()
    
    # Cerca il commento JavaDoc che precede la classe (se presente)
    # Cerca indietro da start_pos per trovare /** ... */
    javadoc_start = start_pos
    text_before_class = codice_java[:start_pos]
    
    # Trova l'ultimo commento JavaDoc prima della classe
    javadoc_pattern = r'/\*\*[\s\S]*?\*/'
    javadoc_matches = list(re.finditer(javadoc_pattern, text_before_class))
    if javadoc_matches:
        last_javadoc = javadoc_matches[-1]
        # Verifica che tra il commento e la classe ci siano solo spazi/newline/annotazioni
        text_between = text_before_class[last_javadoc.end():].strip()
        # Se c'è solo spazio bianco o annotazioni (@...) tra il commento e la classe, includi il commento
        if not text_between or re.match(r'^(\s*@\w+(\([^)]*\))?\s*)*$', text_between):
            javadoc_start = last_javadoc.start()
    
    # Trova l'inizio effettivo (dopo la classe precedente o dopo gli imports)
    effective_start = 0
    if target_class_index > 0:
        # C'è una classe precedente, trova dove finisce
        prev_match = class_matches[target_class_index - 1]
        prev_class_start = prev_match.start()
        brace_count = 0
        in_class = False
        for i in range(prev_class_start, javadoc_start):
            if codice_java[i] == '{':
                brace_count += 1
                in_class = True
            elif codice_java[i] == '}':
                brace_count -= 1
                if in_class and brace_count == 0:
                    effective_start = i + 1
                    break
        # Salta spazi bianchi dopo la fine della classe precedente
        while effective_start < javadoc_start and codice_java[effective_start] in ' \t\n\r':
            effective_start += 1
    
    # Trova la fine della classe target (ultima parentesi graffa chiusa)
    brace_count = 0
    in_class = False
    end_pos = len(codice_java)
    
    for i in range(start_pos, len(codice_java)):
        if codice_java[i] == '{':
            brace_count += 1
            in_class = True
        elif codice_java[i] == '}':
            brace_count -= 1
            if in_class and brace_count == 0:
                end_pos = i + 1
                break
    
    # Estrai la classe con il suo commento JavaDoc (se presente)
    if effective_start == 0:
        # È la prima classe, includi tutto dall'inizio
        codice_estratto = codice_java[:end_pos].strip()
    else:
        # C'è una classe precedente, estrai solo dalla fine della classe precedente
        # ma includi il commento JavaDoc
        codice_estratto = codice_java[min(effective_start, javadoc_start):end_pos].strip()
        
        # Se abbiamo estratto solo la classe senza package/imports, prova a includerli
        if not codice_estratto.strip().startswith('package'):
            # Trova package e imports dal codice originale (prima di tutte le classi)
            first_class_start = class_matches[0].start() if class_matches else 0
            header = codice_java[:first_class_start]
            
            if header:
                # Estrai package
                package_match = re.search(r'package\s+[\w.]+;', header)
                # Estrai imports
                imports = re.findall(r'import\s+(?:static\s+)?[\w.*]+;', header)
                
                if package_match or imports:
                    parts = []
                    if package_match:
                        parts.append(package_match.group(0))
                    if imports:
                        # Separa import normali e statici per un ordinamento migliore
                        imports_normali = [imp for imp in imports if not imp.startswith('import static')]
                        imports_statici = [imp for imp in imports if imp.startswith('import static')]
                        imports_normali.sort()
                        imports_statici.sort()
                        parts.extend(imports_normali)
                        if imports_normali and imports_statici:
                            parts.append('')  # Riga vuota tra import normali e statici
                        parts.extend(imports_statici)
                    parts.append('')  # Riga vuota
                    parts.append(codice_estratto)
                    codice_estratto = '\n'.join(parts)
    
    return codice_estratto


def sanitize_model_name(model_name: str) -> str:
    """
    Converte il nome del modello in un nome file valido.
    Funzione unificata per evitare duplicazione.
    """
    sanitized = model_name.replace(':', '_').replace('/', '_').replace('\\', '_')
    sanitized = sanitized.replace(' ', '_').replace('.', '_')
    return sanitized


def rimuovi_suffisso_llmcheck_da_nome_classe(codice_java: str) -> str:
    """
    Rimuove il suffisso _LLMCheck dal nome della classe nel codice Java.
    """
    if not codice_java:
        return codice_java
    
    # Pattern per trovare la dichiarazione della classe
    pattern = r'((?:public\s+|private\s+|protected\s+|abstract\s+|final\s+|static\s+)*class\s+)(\w+)(\s*(?:extends|implements|\{))'
    
    def rimuovi_suffisso(match):
        modificatori = match.group(1)
        nome_classe = match.group(2)
        resto = match.group(3)
        
        # Se il nome finisce con _LLMCheck, rimuovilo
        if nome_classe.endswith('_LLMCheck'):
            nuovo_nome = nome_classe[:-9]  # Rimuovi '_LLMCheck' (9 caratteri)
            print(f"WARNING:  Removed suffix _LLMCheck from class name: '{nome_classe}' -> '{nuovo_nome}'")
            return f"{modificatori}{nuovo_nome}{resto}"
        
        return match.group(0)
    
    codice_corretto = re.sub(pattern, rimuovi_suffisso, codice_java, flags=re.MULTILINE)
    
    return codice_corretto