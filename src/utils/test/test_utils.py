import os
import re
from typing import List, Dict, Tuple, Optional


def calcola_weighted_pass_rate(test_results: Dict) -> Tuple[float, Dict]:
    """
    Calcola il weighted pass rate dei test.
    
    Pesi:
    - Test passato: 1.0
    - Test con ERROR (errore tecnico/runtime): 0.5
    - Test con FAILURE (asserzione fallita): 0.0
    
    Returns:
        Tuple[float, Dict]: (weighted_pass_rate, dettagli)
            weighted_pass_rate: valore tra 0.0 e 1.0
            dettagli: dizionario con breakdown dei test
    """
    test_info = test_results.get("test_info", {})
    
    test_validi = test_info.get("valid_tests", [])  # Passati
    test_falliti_assert = test_info.get("failed_assert_tests", [])  # FAILURE (logico)
    test_errori_runtime = test_info.get("runtime_error_tests", [])  # ERROR (tecnico)
    
    # Calcola pesi
    peso_passati = len(test_validi) * 1.0
    peso_errori = len(test_errori_runtime) * 0.5  # Errori tecnici hanno peso parziale
    peso_falliti = len(test_falliti_assert) * 0.0  # Fallimenti logici hanno peso 0
    
    totale_test = len(test_validi) + len(test_falliti_assert) + len(test_errori_runtime)
    
    if totale_test == 0:
        return 0.0, {
            "test_passati": 0,
            "runtime_error_tests": 0,
            "failed_assert_tests": 0,
            "totale_test": 0,
            "peso_totale": 0.0
        }
    
    peso_totale = peso_passati + peso_errori + peso_falliti
    weighted_pass_rate = peso_totale / totale_test
    
    dettagli = {
        "test_passati": len(test_validi),
        "runtime_error_tests": len(test_errori_runtime),
        "failed_assert_tests": len(test_falliti_assert),
        "totale_test": totale_test,
        "peso_totale": peso_totale,
        "weighted_pass_rate": weighted_pass_rate
    }
    
    return weighted_pass_rate, dettagli


def aggiungi_timeout_classe(codice_java: str) -> str:
    """
    Aggiunge @Timeout a livello di classe se non presente.
    Previene test che entrano in loop infinito.
    
    Aggiunge:
    - import org.junit.jupiter.api.Timeout;
    - @Timeout(value = 2, threadMode = Timeout.ThreadMode.SEPARATE_THREAD) prima della classe
    """
    if not codice_java or not codice_java.strip():
        return codice_java
    
    timeout_annotation = '@Timeout(value = 2, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)'
    timeout_import = 'import org.junit.jupiter.api.Timeout;'
    
    # Verifica se @Timeout è già presente a livello di classe (non di metodo)
    # Pattern: cerca @Timeout seguito da class (con possibili altre annotazioni in mezzo)
    class_timeout_pattern = r'@Timeout\s*\([^)]*\)\s*(?:@\w+\s*(?:\([^)]*\))?\s*)*(?:public\s+)?class\s+'
    if re.search(class_timeout_pattern, codice_java, re.DOTALL):
        # @Timeout già presente a livello di classe
        return codice_java
    
    # 1. Aggiungi import se non presente
    if timeout_import not in codice_java:
        # Trova l'ultimo import per inserire dopo
        import_matches = list(re.finditer(r'^import\s+[^;]+;', codice_java, re.MULTILINE))
        if import_matches:
            last_import_end = import_matches[-1].end()
            codice_java = codice_java[:last_import_end] + '\n' + timeout_import + codice_java[last_import_end:]
        else:
            # Nessun import, inserisci dopo package
            package_match = re.search(r'^package\s+[^;]+;', codice_java, re.MULTILINE)
            if package_match:
                insert_pos = package_match.end()
                codice_java = codice_java[:insert_pos] + '\n\n' + timeout_import + codice_java[insert_pos:]
    
    # 2. Aggiungi @Timeout prima della dichiarazione della classe
    # Trova: [annotazioni esistenti] [public] class NomeClasse
    class_pattern = r'((?:@\w+\s*(?:\([^)]*\))?\s*\n\s*)*)((public\s+)?class\s+\w+)'
    match = re.search(class_pattern, codice_java, re.MULTILINE)
    
    if match:
        existing_annotations = match.group(1)
        class_decl = match.group(2)
        
        # Verifica che @Timeout non sia già nelle annotazioni esistenti
        if '@Timeout' not in existing_annotations:
            # Inserisci @Timeout prima delle altre annotazioni o della classe
            new_annotations = timeout_annotation + '\n' + existing_annotations
            codice_java = codice_java[:match.start()] + new_annotations + class_decl + codice_java[match.end():]
    
    return codice_java


def match_test_names(test_name_from_code: str, failed_names_from_output: List[str]) -> bool:
    """
    Verifica se un nome di test dal codice corrisponde a uno dei nomi falliti dall'output.
    Fa matching flessibile (case-insensitive, con/senza prefisso "test").
    """
    if not test_name_from_code or not failed_names_from_output:
        return False
    
    test_name_lower = test_name_from_code.lower().strip()
    # Rimuovi prefisso "test" se presente per matching più flessibile
    test_name_clean = test_name_lower[4:] if test_name_lower.startswith('test') else test_name_lower
    
    for failed_name in failed_names_from_output:
        if not failed_name:
            continue
        failed_lower = str(failed_name).lower().strip()
        failed_clean = failed_lower[4:] if failed_lower.startswith('test') else failed_lower
        
        # Match esatto o match senza prefisso "test"
        if (test_name_lower == failed_lower or 
            test_name_clean == failed_clean or
            test_name_clean == failed_lower or
            test_name_lower == failed_clean):
            return True
    
    return False


def estrai_nomi_test_da_codice(codice_test: str) -> List[str]:
    """Estrae i nomi dei metodi di test dal codice, filtrando nomi non validi.
    Gestisce anche sintassi JUnit 4: @Test(expected=...)"""
    nomi_test = []
    
    # Pattern principale: cerca metodi con annotazione @Test (con o senza parametri)
    # Gestisce: @Test, @Test(expected=...), @Test(timeout=...), etc.
    pattern_test = r'@Test\s*(?:\([^)]*\))?\s*(?:@[^\n]*\n)*\s*(?:@DisplayName\([^)]+\)\s*)?(?:public\s+)?(?:void\s+)?(\w+)\s*\('
    matches = re.findall(pattern_test, codice_test, re.MULTILINE)
    nomi_test.extend(matches)
    
    # Pattern alternativo: cerca metodi che iniziano con test/Test (DEVONO avere void o return type)
    # Aggiornato per NON matchare costruttori (es. TestOption(...)) che non hanno return type
    pattern_naming = r'(?:public\s+|private\s+|protected\s+)?(void|[\w<>]+\s+)(test\w+|Test\w+)\s*\('
    matches_naming = re.findall(pattern_naming, codice_test, re.MULTILINE)
    for match in matches_naming:
        if isinstance(match, tuple):
            # match[1] è il nome del test (match[0] è il return type)
            nome = match[1]
        else:
            nome = match
        if nome not in nomi_test:
            nomi_test.append(nome)
    
    # Pattern per metodi che iniziano con Test seguito da maiuscola (NON underscore)
    # Anche qui richiediamo void o return type
    pattern_test_upper = r'(?:public\s+|private\s+|protected\s+)?(void|[\w<>]+\s+)(Test[A-Z][a-zA-Z0-9]*)\s*\('
    matches_upper = re.findall(pattern_test_upper, codice_test, re.MULTILINE)
    for match in matches_upper:
        if isinstance(match, tuple):
            nome = match[1]
        else:
            nome = match
        if nome not in nomi_test:
            nomi_test.append(nome)
    
    # Filtra nomi non validi (non sono nomi di metodi di test reali)
    nomi_non_validi = {'Test', 'Tests', 'test', 'tests', 'TEST', 'TESTS'}
    
    seen = set()
    result = []
    for nome in nomi_test:
        # Filtra nomi non validi e nomi troppo corti (meno di 4 caratteri dopo "test")
        if nome not in seen and nome not in nomi_non_validi:
            # Verifica che non sia solo "Test" o "Tests" (case-insensitive)
            nome_lower = nome.lower()
            if nome_lower not in {'test', 'tests'} and len(nome) > 4:
                seen.add(nome)
                result.append(nome)
    
    return result


def estrai_campi_classe_test(codice_test: str) -> str:
    """
    Estrae i campi della classe di test (con annotazioni @Mock, @InjectMocks, @Captor, ecc.)
    che sono dichiarati tra la dichiarazione della classe e i metodi di test.
    Gestisce anche fields su più righe (annotazione su una riga, dichiarazione su un'altra).
    """
    # Trova la posizione della dichiarazione della classe (gestisce anche annotazioni di classe prima)
    # Cerca la parentesi graffa di apertura della classe
    class_match = re.search(r'class\s+\w+\s*\{', codice_test, re.MULTILINE)
    if not class_match:
        return ""
    
    # Trova la posizione della parentesi graffa di apertura
    class_start = codice_test.find('{', class_match.start())
    if class_start == -1:
        return ""
    class_start += 1  # Inizia dopo la parentesi graffa
    
    # Trova la posizione del primo metodo di test o setup
    # Cerca @Test, @BeforeEach, @AfterEach, o qualsiasi metodo
    first_method_match = re.search(
        r'(@Test|@BeforeEach|@AfterEach|@BeforeAll|@AfterAll)\s*\n\s*(?:public\s+|private\s+|protected\s+)?void\s+\w+\s*\(',
        codice_test[class_start:],
        re.MULTILINE
    )
    
    if not first_method_match:
        # Se non troviamo un metodo con annotazione, cerca qualsiasi metodo
        first_method_match = re.search(
            r'(?:public\s+|private\s+|protected\s+)?void\s+\w+\s*\(',
            codice_test[class_start:],
            re.MULTILINE
        )
        
        # Verifica che non sia un blocco catch o altra struttura di controllo
        if first_method_match:
            match_start = class_start + first_method_match.start()
            # Cerca indietro per vedere se c'è un catch, if, else, ecc.
            context_before = codice_test[max(0, match_start - 50):match_start]
            if any(keyword in context_before for keyword in ['catch', 'if', 'else', 'for', 'while', 'switch', 'do']):
                first_method_match = None
    
    if not first_method_match:
        # Se non ci sono metodi, estrai tutto fino alla chiusura della classe
        # Conta le parentesi graffe per trovare la chiusura corretta
        brace_count = 1
        end_pos = class_start
        for i in range(class_start, len(codice_test)):
            if codice_test[i] == '{':
                brace_count += 1
            elif codice_test[i] == '}':
                brace_count -= 1
                if brace_count == 0:
                    end_pos = i
                    break
        if end_pos == class_start:
            return ""
        fields_section = codice_test[class_start:end_pos].strip()
    else:
        # Estrai tutto tra la dichiarazione della classe e il primo metodo
        fields_section = codice_test[class_start:class_start + first_method_match.start()].strip()
    
    # Rimuovi righe vuote iniziali e finali, ma mantieni l'indentazione
    lines = fields_section.split('\n')
    # Filtra righe vuote e commenti standalone, ma mantieni i campi
    filtered_lines = []
    i = 0
    in_control_structure = False
    brace_count = 0
    
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        
        if not stripped:
            i += 1
            continue
        

        control_flow_starters = ['try', 'catch', 'if', 'else', 'for', 'while', 'switch', 'do', 'return', 'throw']
        if any(stripped.startswith(keyword + ' ') or stripped.startswith(keyword + '(') for keyword in control_flow_starters):
            in_control_structure = True
            brace_count = stripped.count('{') - stripped.count('}')
            i += 1
            continue
        
        if '(' in stripped and ')' in stripped:
           
            has_field_keyword = any(keyword in stripped for keyword in ['private', 'protected', 'public', 'static', 'final'])
            has_field_annotation = stripped.startswith('@') and any(ann in stripped for ann in ['@Mock', '@InjectMocks', '@Captor', '@Spy', '@Autowired'])
            
            if not has_field_keyword and not has_field_annotation:
                
                i += 1
                continue
        
        if any(op in stripped for op in [' > ', ' < ', ' >= ', ' <= ', ' == ', ' != ']):
            has_field_keyword = any(keyword in stripped for keyword in ['private', 'protected', 'public', 'static', 'final'])
            if not has_field_keyword:
                i += 1
                continue
        
        if in_control_structure:
            brace_count += stripped.count('{') - stripped.count('}')
            if brace_count <= 0 and '}' in stripped:
                in_control_structure = False
                brace_count = 0
            i += 1
            continue
        
        if stripped.startswith('@') and any(ann in stripped for ann in ['@Mock', '@InjectMocks', '@Captor', '@Spy', '@Autowired']):
            field_block = [line]
            if i + 1 < len(lines):
                next_line = lines[i + 1]
                next_stripped = next_line.strip()
                if any(keyword in next_stripped for keyword in ['private', 'protected', 'public']) and ';' in next_stripped:
                    field_block.append(next_line)
                    i += 1  
            filtered_lines.extend(field_block)
        elif stripped and any(keyword in stripped for keyword in ['private', 'protected', 'public', 'static', 'final']):
            if re.search(r'\w+\s+\w+\s*[;=]', stripped) or re.search(r'\w+<[^>]+>\s+\w+\s*[;=]', stripped):
                filtered_lines.append(line)
                if '=' in stripped and ';' not in stripped:
                    j = i + 1
                    while j < len(lines) and ';' not in lines[j]:
                        filtered_lines.append(lines[j])
                        j += 1
                    if j < len(lines):
                        filtered_lines.append(lines[j])
                        i = j  
        elif stripped and ('//' in stripped and not stripped.startswith('//')) or '/*' in stripped:
            if i > 0 and any(keyword in lines[i-1].strip() for keyword in ['private', 'protected', 'public', '@Mock', '@InjectMocks']):
                filtered_lines.append(line)
            elif i + 1 < len(lines) and any(keyword in lines[i+1].strip() for keyword in ['private', 'protected', 'public', '@Mock', '@InjectMocks']):
                filtered_lines.append(line)
        
        i += 1
    
    if not filtered_lines:
        return ""
    
    return '\n'.join(filtered_lines)


def _estrai_package(codice_test_validi: str, codice_test_rigenerati: str) -> Optional[str]:
    """Estrae il package declaration dal codice (priorità ai test validi)."""
    match = re.search(r'^package\s+[\w.]+;', codice_test_validi, re.MULTILINE)
    if not match:
        match = re.search(r'^package\s+[\w.]+;', codice_test_rigenerati, re.MULTILINE)
    return match.group(0) if match else None


def _estrai_imports_uniti(codice_test_validi: str, codice_test_rigenerati: str) -> Tuple[List[str], List[str]]:
    """Estrae e unisce imports da entrambi i codici, separando normali e statici."""
    # Estrai tutti gli imports
    imports_validi = re.findall(r'^import\s+(?:static\s+)?[\w.*]+;', codice_test_validi, re.MULTILINE)
    imports_rigenerati = re.findall(r'^import\s+(?:static\s+)?[\w.*]+;', codice_test_rigenerati, re.MULTILINE)
    
    # Normalizza e deduplica
    imports_dict = {}
    for imp in imports_validi + imports_rigenerati:
        imp_norm = re.sub(r'\s+', ' ', imp.strip())
        imports_dict[imp_norm] = imp.strip()
    
    all_imports = list(imports_dict.values())
    
    # Separa normali e statici
    imports_normali = sorted([i for i in all_imports if not i.startswith('import static')], key=str.lower)
    imports_statici = sorted([i for i in all_imports if i.startswith('import static')], key=str.lower)
    
    # @Timeout presente (per class-level timeout)
    timeout_import = 'import org.junit.jupiter.api.Timeout;'
    if timeout_import not in imports_normali:
        imports_normali.append(timeout_import)
        imports_normali = sorted(imports_normali, key=str.lower)
    
    return imports_normali, imports_statici


def _estrai_annotazioni_classe(codice_test_validi: str, codice_test_rigenerati: str) -> List[str]:
    """Estrae annotazioni di classe (es. @ExtendWith) dai codici.
    Aggiunge automaticamente @Timeout a livello di classe per prevenire infinite loop."""
    pattern = r'((?:@\w+(?:\([^)]*\))?\s*\n\s*)+)(?:public\s+)?class\s+\w+'
    
    annotations = []
    for codice in [codice_test_validi, codice_test_rigenerati]:
        match = re.search(pattern, codice, re.MULTILINE)
        if match:
            for line in match.group(1).strip().split('\n'):
                ann = line.strip()
                if ann.startswith('@') and ann not in annotations:
                    annotations.append(ann)
    
    # IMPORTANTE: Aggiungi @Timeout a livello di classe se non presente (previene infinite loop)
    has_timeout = any('@Timeout' in ann for ann in annotations)
    if not has_timeout:
        # @Timeout(value = 2, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
        annotations.insert(0, '@Timeout(value = 2, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)')
    
    return annotations


def _unisci_campi_classe(codice_test_validi: str, codice_test_rigenerati: str) -> str:
    """Unisce i campi della classe da entrambi i codici evitando duplicati.
    NOTA: Rimuove prima le inner classes per evitare di estrarre i loro campi."""
    # Rimuovi inner classes prima di estrarre i campi
    codice_validi_pulito = _rimuovi_inner_classes(codice_test_validi)
    codice_rigenerati_pulito = _rimuovi_inner_classes(codice_test_rigenerati)
    
    campi_validi = estrai_campi_classe_test(codice_validi_pulito)
    campi_rigenerati = estrai_campi_classe_test(codice_rigenerati_pulito)
    
    campi_uniti = []
    campi_visti = set()
    
    def estrai_nome_campo(riga: str) -> Optional[str]:
        # Gestisce: tipo nome; tipo nome = valore; @Annotazione tipo nome;
        match = re.search(r'(\w+)\s*[;=]', riga.strip())
        return match.group(1) if match else None
    
    def estrai_blocco_campo_completo(lines: list, start_idx: int) -> tuple:
        """
        Estrae un blocco completo di campo che può includere:
        - Una o più annotazioni su righe separate (@Mock, @TempDir, @RegisterExtension, ecc.)
        - Modificatori (private, public, protected, static, final)
        - Tipo (anche generics come List<String>, Map<K,V>)
        - Nome campo
        - Inizializzazione inline (opzionale)
        - Punto e virgola finale
        """
        field_block = []
        i = start_idx
        
        # Fase 1: Raccogli tutte le annotazioni consecutive
        while i < len(lines):
            line = lines[i].strip()
            if line.startswith('@'):
                field_block.append(lines[i])
                i += 1
            else:
                break
        
        # Fase 2: Raccogli modificatori, tipo e nome campo fino al punto e virgola
        if i < len(lines):
            # Raccogli righe fino a trovare il punto e virgola
            found_semicolon = False
            while i < len(lines):
                line = lines[i]
                field_block.append(line)
                
                # Controlla se questa riga contiene il punto e virgola finale
                if ';' in line:
                    found_semicolon = True
                    i += 1
                    break
                
                # Se la riga successiva non sembra essere parte del campo, fermati
                if i + 1 < len(lines):
                    next_line = lines[i + 1].strip()
                    # Se la prossima riga inizia con @ o è un modificatore, potrebbe essere un nuovo campo
                    if (next_line.startswith('@') or 
                        next_line.startswith('private') or 
                        next_line.startswith('protected') or 
                        next_line.startswith('public')):
                        # Ma controlla se la riga corrente finisce con punto e virgola
                        if ';' not in line:
                            # Potrebbe essere un campo su più righe, continua
                            i += 1
                            continue
                        else:
                            break
                
                i += 1
        
        return field_block, i
    
    def processa_fields(campi_str: str):
        if not campi_str.strip():
            return
        lines = campi_str.split('\n')
        i = 0
        while i < len(lines):
            line = lines[i].strip()
            
            # Salta righe vuote
            if not line:
                i += 1
                continue
            
            is_field = False
            
            # Caso 1: Campo con annotazione (qualsiasi annotazione, non solo quelle specifiche)
            if line.startswith('@'):
                is_field = True
            # Caso 2: Campo con modificatore di accesso
            elif any(line.startswith(kw) for kw in ['private', 'protected', 'public']):
                is_field = True
            # Caso 3: Campo static/final senza modificatore esplicito (package-private)
            elif any(line.startswith(kw) for kw in ['static', 'final']):
                # Verifica che non sia l'inizio di un metodo o altro
                if re.search(r'\w+\s+\w+\s*[;=]', line) or ';' in line:
                    is_field = True
            
            if is_field:
                field_block, next_idx = estrai_blocco_campo_completo(lines, i)
                
                # Estrai il nome del campo dal blocco
                nome = None
                for l in field_block:
                    nome = estrai_nome_campo(l)
                    if nome:
                        break
                
                # Se abbiamo trovato un nome e non l'abbiamo già visto, aggiungilo
                if nome and nome not in campi_visti:
                    campi_visti.add(nome)
                    campi_uniti.extend(field_block)
                elif not nome:
                    # Se non abbiamo trovato un nome ma sembra un campo valido, aggiungilo comunque
                    # (potrebbe essere un campo con sintassi particolare)
                    campi_uniti.extend(field_block)
                
                i = next_idx
            else:
                i += 1
    
    processa_fields(campi_validi)
    processa_fields(campi_rigenerati)
    
    return '\n'.join(campi_uniti) if campi_uniti else ""


def _estrai_inner_classes(codice: str) -> List[Tuple[str, str]]:
    """Estrae tutte le inner classes dal codice. Ritorna lista di (nome_classe, codice_completo)."""
    inner_classes = []
    
    # Pattern per trovare inner classes (private/public/protected static? class Nome ...)
    # Deve essere dentro la classe principale, non la classe principale stessa
    pattern = r'(?:private|public|protected)\s+(?:static\s+)?class\s+(\w+)(?:\s+(?:extends|implements)\s+[\w<>,\s]+)?\s*\{'
    
    # Trova la classe principale per escluderla
    main_class_match = re.search(r'^(?:public\s+)?class\s+(\w+)', codice, re.MULTILINE)
    main_class_name = main_class_match.group(1) if main_class_match else ""
    
    for match in re.finditer(pattern, codice, re.MULTILINE):
        class_name = match.group(1)
        
        # Salta la classe principale
        if class_name == main_class_name:
            continue
        
        # Trova la posizione di inizio della dichiarazione completa
        start_pos = match.start()
        
        # Cerca annotazioni prima della classe (es. @SuppressWarnings)
        search_start = max(0, start_pos - 200)
        context_before = codice[search_start:start_pos]
        
        # Cerca l'ultima annotazione/javadoc prima della classe
        annotation_pattern = r'(/\*\*[\s\S]*?\*/\s*|(?:@\w+(?:\([^)]*\))?\s*\n\s*)+)'
        annotation_match = re.search(annotation_pattern + r'$', context_before)
        if annotation_match:
            start_pos = search_start + annotation_match.start()
        
        # Estrai la classe completa contando le parentesi graffe
        brace_count = 0
        in_class = False
        end_pos = len(codice)
        
        i = match.end() - 1  # Inizia dalla {
        while i < len(codice):
            char = codice[i]
            if char == '{':
                brace_count += 1
                in_class = True
            elif char == '}':
                brace_count -= 1
                if in_class and brace_count == 0:
                    end_pos = i + 1
                    break
            i += 1
        
        inner_class_code = codice[start_pos:end_pos].strip()
        inner_classes.append((class_name, inner_class_code))
    
    return inner_classes


def _unisci_inner_classes(codice_test_validi: str, codice_test_rigenerati: str) -> List[str]:
    """Unisce le inner classes da entrambi i codici evitando duplicati."""
    inner_validi = _estrai_inner_classes(codice_test_validi)
    inner_rigenerati = _estrai_inner_classes(codice_test_rigenerati)
    
    inner_uniti = []
    nomi_visti = set()
    
    # Priorità ai test rigenerati (sono più recenti)
    for nome, codice in inner_rigenerati + inner_validi:
        if nome not in nomi_visti:
            inner_uniti.append(codice)
            nomi_visti.add(nome)
    
    return inner_uniti


def _rimuovi_inner_classes(codice: str) -> str:
    """Rimuove le inner classes dal codice, lasciando solo il contenuto della classe principale.
    Utile per estrarre campi/metodi solo dalla classe principale, evitando di estrarre contenuti delle inner classes."""
    inner_classes = _estrai_inner_classes(codice)
    
    # Rimuovi ogni inner class dal codice
    result = codice
    for _, inner_class_code in inner_classes:
        result = result.replace(inner_class_code, '')
    
    return result


def _unisci_metodi_helper(codice_test_validi: str, codice_test_rigenerati: str, 
                          nomi_test_validi: List[str], nomi_test_rigenerati: List[str]) -> List[str]:
    """Unisce i metodi helper da entrambi i codici evitando duplicati.
    
    Se un metodo appare in entrambe le sorgenti, preferisce la versione con JavaDoc.
    """
    
    # Rimuovi inner classes prima di estrarre i metodi helper
    codice_validi_pulito = _rimuovi_inner_classes(codice_test_validi)
    codice_rigenerati_pulito = _rimuovi_inner_classes(codice_test_rigenerati)
    
    helper_validi = estrai_tutti_metodi_supporto(codice_validi_pulito, nomi_test_validi)
    helper_rigenerati = estrai_tutti_metodi_supporto(codice_rigenerati_pulito, nomi_test_rigenerati)
    
    # Dizionario: nome_metodo -> (codice_metodo, has_javadoc)
    metodi_per_nome = {}
    
    def _ha_javadoc(codice: str) -> bool:
        """Verifica se il metodo ha JavaDoc (/** ... */)"""
        return '/**' in codice and '*/' in codice
    
    def _estrai_nome_metodo(metodo: str) -> str:
        """Estrae il nome del metodo dal codice"""
        match = re.search(
            r'(?:@\w+(?:\([^)]*\))?\s+)*'  # Annotazioni opzionali con parametri
            r'\s*(?:public|private|protected)?\s*'  # Modificatore di accesso opzionale
            r'(?:static\s+)?'  # static opzionale
            r'(?:final\s+)?'  # final opzionale
            r'(?:<[^>]+>\s+)?'  # Type parameters es. <T> opzionali
            r'(?:[\w.<>?\[\],\s]+\s+)?'  # Return type (gestisce Class<?>, List<String>, etc.)
            r'(\w+)\s*\(',  # Nome metodo seguito da (
            metodo
        )
        return match.group(1) if match else None
    
    # Processa tutti i metodi helper (prima rigenerati, poi validi)
    for metodo in helper_rigenerati + helper_validi:
        nome = _estrai_nome_metodo(metodo)
        if not nome:
            continue
            
        has_javadoc = _ha_javadoc(metodo)
        
        if nome not in metodi_per_nome:
            # Prima occorrenza: aggiungi
            metodi_per_nome[nome] = (metodo, has_javadoc)
        else:
            # Duplicato trovato: preferisci versione con JavaDoc
            existing_code, existing_has_javadoc = metodi_per_nome[nome]
            
            if has_javadoc and not existing_has_javadoc:
                # Nuova versione ha JavaDoc, vecchia no -> sostituisci
                metodi_per_nome[nome] = (metodo, has_javadoc)
            # else: mantieni versione esistente (o entrambe hanno/non hanno JavaDoc)
    
    return [code for code, _ in metodi_per_nome.values()]


def _indenta_codice(codice: str, min_indent: int = 4) -> List[str]:
    """Normalizza l'indentazione del codice."""
    lines = []
    for line in codice.split('\n'):
        if line.strip():
            if not line.startswith(' '):
                lines.append(' ' * min_indent + line)
            else:
                stripped = line.lstrip()
                current_indent = len(line) - len(stripped)
                if current_indent < min_indent:
                    lines.append(' ' * min_indent + stripped)
                else:
                    lines.append(line)
    return lines


def _ha_annotazione_test(codice: str) -> bool:
    """Verifica se il codice contiene un'annotazione JUnit."""
    return any(ann in codice for ann in ['@Test', '@BeforeEach', '@AfterEach', '@BeforeAll', '@AfterAll'])


def _rimuovi_codice_vagante(codice: str) -> str:
    """
    Rimuove blocchi di codice vagante (catch, try, for, if, etc.) che non sono 
    preceduti da annotazioni JUnit valide o che non appartengono a metodi completi.
    """
    lines = codice.split('\n')
    cleaned_lines = []
    i = 0
    brace_depth = 0
    in_class = False
    in_method = False
    skip_until_brace_close = False
    skip_brace_depth = 0
    method_brace_depth = 0
    last_annotation_line = -1
    
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        
        # Conta parentesi graffe
        open_braces = stripped.count('{')
        close_braces = stripped.count('}')
        
        # Se stiamo saltando un blocco vagante, continua fino alla chiusura
        if skip_until_brace_close:
            skip_brace_depth += open_braces - close_braces
            if skip_brace_depth <= 0:
                skip_until_brace_close = False
                skip_brace_depth = 0
            i += 1
            continue
        
        # Traccia se siamo nella classe
        if 'class ' in stripped and '{' in stripped:
            in_class = True
            cleaned_lines.append(line)
            brace_depth += open_braces - close_braces
            i += 1
            continue
        
        # Se siamo dentro un metodo, traccia la profondità del metodo
        if in_method:
            method_brace_depth += open_braces - close_braces
            if method_brace_depth <= 0:
                in_method = False
                method_brace_depth = 0
            cleaned_lines.append(line)
            brace_depth += open_braces - close_braces
            i += 1
            continue
        
        # Verifica se è un'annotazione JUnit
        if stripped.startswith('@') and any(ann in stripped for ann in 
            ['@Test', '@BeforeEach', '@AfterEach', '@BeforeAll', '@AfterAll', '@DisplayName']):
            last_annotation_line = i
            cleaned_lines.append(line)
            brace_depth += open_braces - close_braces
            i += 1
            continue
        
        # Verifica se è l'inizio di un metodo dopo un'annotazione
        if last_annotation_line >= 0 and i == last_annotation_line + 1:
            # Verifica se è una dichiarazione di metodo
            is_method_decl = re.match(
                r'^\s*(?:public\s+|private\s+|protected\s+)?(?:static\s+)?(?:final\s+)?(?:\w+(?:<[^>]+>)?\s+)?\w+\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{?',
                line
            )
            if is_method_decl:
                in_method = True
                method_brace_depth = open_braces - close_braces
                cleaned_lines.append(line)
                brace_depth += open_braces - close_braces
                i += 1
                continue
        
        # Verifica se è un metodo helper (senza annotazione @Test ma con modificatori)
        # Pattern migliorato per riconoscere anche metodi senza modificatori espliciti (package-private)
        is_helper_method = re.match(
            r'^\s*(?:(?:public|private|protected)\s+)?(?:static\s+)?(?:final\s+)?(?:\w+(?:<[^>]+>)?\s+)?\w+\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{?',
            line
        )
        if is_helper_method and not stripped.startswith('@'):
            # Verifica che non sia un test (non deve iniziare con "test" senza argomenti o avere pattern sospetto)
            method_name_match = re.search(r'(\w+)\s*\(', line)
            if method_name_match:
                method_name = method_name_match.group(1)
                # Se inizia con "test" e non ha argomenti, potrebbe essere un test senza @Test - escludilo
                if method_name.lower().startswith('test') and '()' in line:
                    # Potrebbe essere un test, ma controlla meglio
                    pass  # Continua con il normale flusso
                else:
                    # È un metodo helper valido
                    in_method = True
                    method_brace_depth = open_braces - close_braces
                    cleaned_lines.append(line)
                    brace_depth += open_braces - close_braces
                    i += 1
                    continue
        
        # Aggiorna profondità
        brace_depth += open_braces - close_braces
        
        if in_class and not in_method:
            is_stray = False
            
            # Catch senza try (frammento) - SEMPRE vagante a livello classe
            if stripped.startswith('catch ') or stripped.startswith('catch('):
                is_stray = True
            # Try isolato (senza metodo) - SEMPRE vagante a livello classe
            elif stripped.startswith('try ') or stripped.startswith('try{') or stripped == 'try':
                is_stray = True
            # For/while/if isolati - SEMPRE vaganti a livello classe
            elif stripped.startswith('for ') or stripped.startswith('for('):
                is_stray = True
            elif stripped.startswith('while ') or stripped.startswith('while('):
                is_stray = True
            elif stripped.startswith('if ') or stripped.startswith('if('):
                is_stray = True
            # super() calls - SEMPRE vaganti a livello classe (dovrebbero essere in costruttori)
            elif stripped.startswith('super(') or stripped.startswith('super.'):
                is_stray = True
            # return/throw isolati - SEMPRE vaganti a livello classe
            elif stripped.startswith('return ') or stripped.startswith('return;') or stripped.startswith('throw '):
                is_stray = True
            # Costruttori orfani: NomeClasse(...) { - senza tipo ritorno, NON preceduti da class
            elif re.match(r'^[A-Z]\w*\s*\([^)]*\)\s*\{?$', stripped):
                # Verifica che la riga precedente non sia una dichiarazione di classe
                prev_line = lines[i-1].strip() if i > 0 else ''
                if 'class ' not in prev_line and not prev_line.startswith('@'):
                    is_stray = True
            # Metodi @Override a livello classe senza annotazione JUnit - orfani
            elif stripped.startswith('@Override'):
                is_stray = True
            # Chiamate di metodo isolate (senza annotazione precedente)
            elif ('(' in stripped and ')' in stripped and ';' in stripped and
                  not any(mod in stripped for mod in ['private ', 'protected ', 'public ']) and
                  not stripped.startswith('@')):
                # Verifica che non sia una dichiarazione di campo
                if not re.match(r'^\w+\s+\w+\s*;', stripped):
                    # Controlla se la riga precedente ha un'annotazione o è parte di un metodo
                    prev_line = lines[i-1].strip() if i > 0 else ''
                    if not prev_line.startswith('@') and not prev_line.endswith('{') and not prev_line.endswith('}'):
                        is_stray = True
            # Statement isolati come fail(...), assert(...), etc. senza contesto
            elif (stripped.startswith('fail(') or stripped.startswith('assert') or 
                  stripped.startswith('assertEquals') or stripped.startswith('assertTrue') or
                  stripped.startswith('assertFalse') or stripped.startswith('assertNull') or
                  stripped.startswith('assertNotNull')):
                is_stray = True
            
            if is_stray:
                # Salta questo blocco
                if '{' in stripped:
                    skip_until_brace_close = True
                    skip_brace_depth = open_braces - close_braces
                i += 1
                continue
        
        # Include tutto il resto (package, import, dichiarazioni valide, etc.)
        cleaned_lines.append(line)
        i += 1
    
    return '\n'.join(cleaned_lines)


def unisci_test_validi_e_rigenerati(codice_test_validi: str, codice_test_rigenerati: str, package_target: str = None, nomi_test_da_preservare: list = None) -> str:
    """
    Unisce i test validi esistenti con i test rigenerati.
    
    Logica di unione:
    1. Se un test è in 'nomi_test_da_preservare' (test che passavano), MANTIENI la versione valida originale.
    2. Altrimenti, se esiste una versione rigenerata, USA quella rigenerata (per fixare test falliti o aggiungerne nuovi).
    3. Infine, mantieni gli altri test validi non toccati.
    
    Mantiene package, imports, campi della classe, setup e struttura classe dal codice originale.
    """
    # 1. Estrai componenti base
    package = _estrai_package(codice_test_validi, codice_test_rigenerati)
    
    # Se il package non viene trovato, usa quello target fornito
    if not package and package_target:
        package = f"package {package_target};"
    imports_normali, imports_statici = _estrai_imports_uniti(codice_test_validi, codice_test_rigenerati)
    annotazioni_classe = _estrai_annotazioni_classe(codice_test_validi, codice_test_rigenerati)
    campi_classe = _unisci_campi_classe(codice_test_validi, codice_test_rigenerati)
    
    # 2. Estrai nomi test
    nomi_test_validi = estrai_nomi_test_da_codice(codice_test_validi)
    nomi_test_rigenerati = estrai_nomi_test_da_codice(codice_test_rigenerati)
    
    # Normalizza i nomi da preservare
    nomi_da_preservare_norm = set()
    if nomi_test_da_preservare:
        nomi_da_preservare_norm = {n.lower().strip() for n in nomi_test_da_preservare}
    
    # 3. Estrai nome classe
    class_match = re.search(r'^(public\s+)?class\s+(\w+)', codice_test_validi, re.MULTILINE)
    nome_classe = class_match.group(2) if class_match else "TestClass"
    class_public = class_match.group(1) or "" if class_match else ""
    
    # 4. Estrai inner classes
    inner_classes = _unisci_inner_classes(codice_test_validi, codice_test_rigenerati)
    
    # 5. Costruisci codice finale
    codice_finale = ""
    
    if package:
        codice_finale += package + "\n\n"
    else:
        print("WARNING:  Package not found")
    
    if imports_normali:
        codice_finale += "\n".join(imports_normali)
        if imports_statici:
            codice_finale += "\n"
    if imports_statici:
        codice_finale += "\n".join(imports_statici)
    if imports_normali or imports_statici:
        codice_finale += "\n\n"
    
    if annotazioni_classe:
        codice_finale += "\n".join(annotazioni_classe) + "\n"
    
    codice_finale += f"{class_public}class {nome_classe} {{\n"
    
    # 6. Aggiungi campi
    if campi_classe.strip():
        campi_indented = _indenta_codice(campi_classe)
        codice_finale += "\n".join(campi_indented) + "\n"
    
    # 7. Aggiungi inner classes
    if inner_classes:
        for inner_class in inner_classes:
            inner_indented = _indenta_codice(inner_class)
            codice_finale += "\n" + "\n".join(inner_indented) + "\n"
    
    # 8. Aggiungi metodi helper (escludendo quelli delle inner classes)
    metodi_helper = _unisci_metodi_helper(codice_test_validi, codice_test_rigenerati, nomi_test_validi, nomi_test_rigenerati)
    for metodo in metodi_helper:
        metodo_indented = _indenta_codice(metodo)
        codice_finale += "\n" + "\n".join(metodo_indented) + "\n"
    
    # 9. Aggiungi test con priorità corretta
    # Set per tenere traccia dei test aggiunti (key = lowercase trimmed name)
    nomi_aggiunti = set()
    
    # A. PRIMA aggiungi i test DA PRESERVARE (quelli che passavano) dalla suite VALIDA
    for nome_test in nomi_test_validi:
        nome_norm = nome_test.lower().strip()
        if nome_norm in nomi_da_preservare_norm:
            codice_test = estrai_codice_test_per_nome(codice_test_validi, nome_test)
            if codice_test:
                test_indented = _indenta_codice(codice_test)
                codice_finale += "\n" + "\n".join(test_indented) + "\n"
                nomi_aggiunti.add(nome_norm)
            else:
                print(f"WARNING:  Could not extract preserved test '{nome_test}'")

    # B. POI aggiungi i test RIGENERATI (Fixes & Nuovi), se non già preservati
    for nome_test in nomi_test_rigenerati:
        nome_norm = nome_test.lower().strip()
        
        # Se già aggiunto (perché preservato), SKIP
        if nome_norm in nomi_aggiunti:
             # print(f"   [DEBUG] Ignoro '{nome_test}' rigenerato perché protetto (passava già)")
             continue

        codice_test = estrai_codice_test_per_nome(codice_test_rigenerati, nome_test)
        
        if codice_test and _ha_annotazione_test(codice_test):
            test_indented = _indenta_codice(codice_test)
            codice_finale += "\n" + "\n".join(test_indented) + "\n"
            nomi_aggiunti.add(nome_norm)
        elif codice_test:
            print(f"WARNING:  Skipping regenerated method '{nome_test}' - no test annotation found (regex issue?)")
            
    # C. INFINE aggiungi i restanti test VALIDI (quelli che fallivano e non sono stati toccati/rigenerati)
    for nome_test in nomi_test_validi:
        nome_norm = nome_test.lower().strip()
        
        # Se già aggiunto, SKIP
        if nome_norm in nomi_aggiunti:
            continue
            
        codice_test = estrai_codice_test_per_nome(codice_test_validi, nome_test)
        if codice_test:
            test_indented = _indenta_codice(codice_test)
            codice_finale += "\n" + "\n".join(test_indented) + "\n"
            nomi_aggiunti.add(nome_norm)
        else:
            print(f"WARNING:  Skipping valid method '{nome_test}' - extraction failed")
    
    codice_finale += "\n}"
    
    # Pulizia finale: rimuovi eventuali blocchi di codice vagante
    # Estrai i metodi helper dal codice finale prima della pulizia
    metodi_helper_finali = []
    for metodo in metodi_helper:
        # Verifica che il metodo sia completo (contiene almeno una graffa di chiusura)
        if metodo.count('{') > 0 and metodo.count('}') >= metodo.count('{'):
            metodi_helper_finali.append(metodo)
    
    codice_finale = _rimuovi_codice_vagante(codice_finale)
    
    # Verifica che i metodi helper siano ancora presenti e completi dopo la pulizia
    # Se alcuni sono stati rimossi o troncati, riaggiungili
    for metodo_helper in metodi_helper_finali:
        # Estrai il nome del metodo helper
        nome_match = re.search(r'(?:public|private|protected)?\s*(?:static\s+)?(?:final\s+)?(?:\w+(?:[\w<>\[\],\s.]*)?\s+)?(\w+)\s*\(', metodo_helper)
        if nome_match:
            nome_helper = nome_match.group(1)
            # Verifica se il metodo helper è presente e completo nel codice finale
            if nome_helper not in codice_finale or metodo_helper not in codice_finale:
                # Il metodo è stato rimosso o troncato, riaggiungilo prima della chiusura della classe
                # Trova l'ultima graffa di chiusura della classe
                last_brace = codice_finale.rfind('}')
                if last_brace != -1:
                    # Inserisci il metodo helper prima della chiusura
                    metodo_indented = _indenta_codice(metodo_helper)
                    codice_finale = codice_finale[:last_brace] + "\n" + "\n".join(metodo_indented) + "\n" + codice_finale[last_brace:]
    
    return codice_finale


def estrai_codice_test_per_nome(codice_test: str, nome_test: str) -> str:
    """
    Estrae il codice completo di un metodo, inclusa qualsiasi annotazione che lo precede.
    Gestisce metodi con @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, e metodi senza annotazioni.
    Migliorato per gestire commenti, whitespace, throws complessi e modificatori.
    """
    
    # Pattern per spazi e commenti che possono apparire tra le annotazioni e il metodo
    skippable = r'(?:\s+|//[^\n]*\n|/\*[\s\S]*?\*/)*'
    
    # Componenti riutilizzabili per regex
    esc_name = re.escape(nome_test)
    # Throws più robusto: include punti, generics <>, array []
    throws_pattern = r'(?:\s+throws\s+[\w\s,.<>?\[\]]+)?'
    # Modificatori Java
    modifiers_pattern = r'(?:(?:public|protected|private|static|final|synchronized|native|strictfp|default)\s+)*'
    # Pattern per parametri generici prima del return type (es. <T> o <T, U>)
    type_params_pattern = r'(?:<[^>]+>\s+)?'
    # Return type generico (void o altro tipo)
    return_type_pattern = r'(?:void|[\w.<>?\[\]]+)'
    
    # Pattern 1: Test con @Test seguito da void (gestione throws aggiornata)
    pattern1 = rf'@Test{skippable}void\s+{esc_name}\s*\([^)]*\){throws_pattern}\s*\{{'
    match = re.search(pattern1, codice_test, re.MULTILINE | re.DOTALL)
    
    start_pos = -1
    
    if match:
        start_pos = match.start()
    else:
        # Pattern 2: Test con @Test, altre annotazioni, modificatori e throws
        # Gestisce: @Test, @DisplayName, public/protected/static/final, throws ...
        pattern2 = rf'@Test{skippable}(?:@[^\n]+\n?{skippable})*{modifiers_pattern}void\s+{esc_name}\s*\([^)]*\){throws_pattern}\s*\{{'
        match = re.search(pattern2, codice_test, re.MULTILINE | re.DOTALL)
        if match:
            start_pos = match.start()
        else:
            # Pattern 3: Metodi con annotazioni di supporto (@BeforeEach, @AfterEach, ecc.)
            support_annotations = ['@BeforeEach', '@AfterEach', '@BeforeAll', '@AfterAll', '@ParameterizedTest', '@RepeatedTest']
            match = None
            for annotation in support_annotations:
                # Nota: permette qualsiasi return type per metodi di supporto (es. factory methods)
                # Aggiunto type_params_pattern per supportare generics
                pattern_support = rf'{re.escape(annotation)}{skippable}(?:@[^\n]+\n?{skippable})*{modifiers_pattern}{type_params_pattern}{return_type_pattern}\s+{esc_name}\s*\([^)]*\){throws_pattern}\s*\{{'
                match = re.search(pattern_support, codice_test, re.MULTILINE | re.DOTALL)
                if match:
                    start_pos = match.start()
                    break
            
            if not match:
                # Pattern 4 (Fallback): Cerca solo la firma del metodo, poi cerca indietro le annotazioni
                # Match signature: modifiers type_params return_type name(args) throws ... {
                from_pattern = rf'{modifiers_pattern}{type_params_pattern}{return_type_pattern}\s+{esc_name}\s*\([^)]*\){throws_pattern}\s*\{{'
                match = re.search(from_pattern, codice_test, re.MULTILINE | re.DOTALL)
                if match:
                    start_pos = match.start()
                else:
                    # Ultimo tentativo: solo nome e args (molto lasco)
                    pattern_last = rf'{esc_name}\s*\([^)]*\){throws_pattern}\s*\{{'
                    match = re.search(pattern_last, codice_test, re.MULTILINE | re.DOTALL)
                    if match:
                        start_pos = match.start()
                    else:
                        return ""
    
    # Se abbiamo trovato un match, verifica se il pattern include già l'annotazione
    if start_pos != -1:
        match_text = codice_test[start_pos:start_pos + min(100, len(codice_test) - start_pos)]
        
        # Se il match non inizia con '@', cerca le annotazioni prima
        if not match_text.strip().startswith('@'):
            # Cerca indietro per trovare annotazioni (aumentato a 5000 caratteri per javadoc/commenti lunghi)
            search_start = max(0, start_pos - 5000)
            context_before = codice_test[search_start:start_pos]
            
            # Pattern per trovare l'ultima annotazione rilevante prima del metodo
            # Cerca un'annotazione (@...) seguita da (spazi/commenti/altre annotazioni) fino alla fine del contesto
            annotation_pattern = rf'(@\w+(?:\([^)]*\))?){skippable}(?:@\w+(?:\([^)]*\))?{skippable})*$'
            
            annotation_match = re.search(annotation_pattern, context_before, re.MULTILINE)
            
            if annotation_match:
                # Abbiamo trovato annotazioni che finiscono esattamente (o quasi) dove inizia il metodo
                start_pos = search_start + annotation_match.start()
        
        # Estrai il metodo completo
        brace_count = 0
        in_method = False
        in_string = False
        in_char = False
        in_single_comment = False
        in_multi_comment = False
        string_char = None

        
        i = start_pos
        while i < len(codice_test):
            char = codice_test[i]
            next_char = codice_test[i+1] if i+1 < len(codice_test) else ''
            
            # Gestisci commenti
            if not in_string and not in_char:
                if not in_multi_comment and not in_single_comment:
                    if char == '/' and next_char == '/':
                        in_single_comment = True
                        i += 1
                    elif char == '/' and next_char == '*':
                        in_multi_comment = True
                        i += 1
                elif in_single_comment:
                    if char == '\n':
                        in_single_comment = False
                elif in_multi_comment:
                    if char == '*' and next_char == '/':
                        in_multi_comment = False
                        i += 1
                
                if in_single_comment or in_multi_comment:
                    i += 1
                    continue
            
            # Gestisci stringhe
            if not in_single_comment and not in_multi_comment:
                if not in_string and not in_char:
                    if char == '"':
                        in_string = True
                        string_char = '"'
                    elif char == "'":
                        in_char = True
                        string_char = "'"
                elif in_string:
                    if char == '\\':
                        i += 1 
                    elif char == string_char:
                        in_string = False
                        string_char = None
                elif in_char:
                    if char == '\\':
                        i += 1
                    elif char == string_char:
                        in_char = False
                        string_char = None
                
                if in_string or in_char:
                    i += 1
                    continue
            
            # Conta parentesi
            if not in_string and not in_char and not in_single_comment and not in_multi_comment:
                if char == '{':
                    brace_count += 1
                    in_method = True
                elif char == '}':
                    brace_count -= 1
                    if in_method and brace_count == 0:
                        extracted = codice_test[start_pos:i+1]
                        return extracted
            
            i += 1
        
        return ""
    
    return ""


def estrai_signature_test(codice_test: str, nome_test: str) -> str:
    """Estrae solo la signature (firma) di un metodo test dal codice"""
    pattern = rf'(@Test\s*(?:@[^\n]*\n)*\s*)?(?:@DisplayName\([^)]+\)\s*)?(?:@[^\n]+\s*)*\s*(?:public\s+)?(?:void\s+)?{re.escape(nome_test)}\s*\([^)]*\)'
    match = re.search(pattern, codice_test, re.MULTILINE | re.DOTALL)
    if match:
        return match.group(0).strip()
    
    pattern_simple = rf'(?:public\s+)?(?:void\s+)?{re.escape(nome_test)}\s*\([^)]*\)'
    match_simple = re.search(pattern_simple, codice_test, re.MULTILINE)
    if match_simple:
        return f"@Test {match_simple.group(0).strip()}"
    return f"@Test void {nome_test}()"


def estrai_tutti_metodi_supporto(codice_test: str, nomi_test_validi: List[str] = None) -> List[str]:
    """
    Estrae tutti i metodi di supporto dalla classe di test, evitando duplicati e metodi inner (es. classi anonime).
    Include metodi @BeforeEach, @AfterEach e helper privati/protected.
    """
    if nomi_test_validi is None:
        nomi_test_validi = []
    
    metodi_supporto = []
    metodi_visti = set()
    ranges_occupati = [] 
    
    # 1. Identifica TUTTI i metodi potenziali con scansione sequenziale

    method_pattern = r'(?:public|private|protected)?\s*(?:static\s+)?(?:final\s+)?(?:synchronized\s+)?(?:<[^>]+>\s+)?(?:\w+(?:[\w<>\[\],\s.]*)?\s+)?(\w+)\s*\([^)]*\)\s*(?:throws\s+[\w\s,]+)?\s*\{'
    
    matches = list(re.finditer(method_pattern, codice_test, re.MULTILINE))
    
    # Nomi da escludere (Test attuali e Test validi noti)
    nomi_test_totali = set(estrai_nomi_test_da_codice(codice_test))
    test_names_to_exclude = nomi_test_totali.union(set(nomi_test_validi))
    
    for match in matches:
        start_decl = match.start()
        method_name = match.group(1)
        
        # Check sovrapposizione: se inizia dentro un range già processato, è un metodo inner -> SKIP
        is_inner = False
        for r_start, r_end in ranges_occupati:
            if r_start <= start_decl < r_end:
                is_inner = True
                break
        if is_inner:
            continue
            
        # Trova fine blocco (bilanciamento graffe)
        # La regex finisce con '{', match.end() è subito dopo
        start_brace = match.end() - 1
        # Extra check sicurezza
        if start_brace >= len(codice_test) or codice_test[start_brace] != '{':
            found = codice_test.find('{', max(0, match.end()-10), min(len(codice_test), match.end()+10))
            if found != -1: start_brace = found
            else: continue
            
        brace_count = 1
        i = start_brace + 1
        in_string = False
        in_char = False
        string_char = None
        end_pos = -1
        
        # Parser migliorato per trovare chiusura metodo (gestisce commenti)
        in_single_comment = False
        in_multi_comment = False
        while i < len(codice_test):
            char = codice_test[i]
            next_char = codice_test[i+1] if i+1 < len(codice_test) else ''
            
            # Gestione commenti (importante per non confondere graffe nei commenti)
            if not in_string and not in_char:
                if not in_multi_comment and not in_single_comment:
                    if char == '/' and next_char == '/':
                        in_single_comment = True
                        i += 1
                    elif char == '/' and next_char == '*':
                        in_multi_comment = True
                        i += 1
                elif in_single_comment:
                    if char == '\n':
                        in_single_comment = False
                elif in_multi_comment:
                    if char == '*' and next_char == '/':
                        in_multi_comment = False
                        i += 1
                
                if in_single_comment or in_multi_comment:
                    i += 1
                    continue
            
            # Gestione stringhe e char literal per non contare graffe al loro interno
            if not in_single_comment and not in_multi_comment:
                if char == '"' and not in_char:
                    if not in_string: 
                        in_string = True; string_char = '"'
                    elif string_char == '"' and (i == 0 or codice_test[i-1] != '\\'): 
                        in_string = False
                elif char == "'" and not in_string:
                    if not in_char: 
                        in_char = True; string_char = "'"
                    elif string_char == "'" and (i == 0 or codice_test[i-1] != '\\'): 
                        in_char = False
            
            if not in_string and not in_char and not in_single_comment and not in_multi_comment:
                if char == '{': brace_count += 1
                elif char == '}': 
                    brace_count -= 1
                    if brace_count == 0:
                        end_pos = i + 1
                        break
            i += 1
            
        if end_pos == -1: continue # Metodo non chiuso correttamente o troncato
        
        # Trova inizio completo (inclusi annotazioni e javadoc)
        full_start = start_decl
        # Cerca indietro da start_decl
        while full_start > 0:
            prev_newline = codice_test.rfind('\n', 0, full_start)
            check_start = prev_newline + 1 if prev_newline != -1 else 0
            
            line_content = codice_test[check_start:full_start].strip()
            # Se la riga sopra è annotazione, commento o vuota, includila
            if (line_content.startswith('@') or 
                line_content.startswith('//') or 
                line_content.startswith('/*') or 
                line_content.startswith('*') or 
                not line_content):
                full_start = prev_newline # Salta alla riga precedente (newline incluso nel chunk dopo)
                if full_start <= 0: break
            else:
                break
        
        # Se siamo su newline, avanza di 1 per pulizia
        if full_start < len(codice_test) and codice_test[full_start] == '\n':
            full_start += 1

        # Registra range occupato
        ranges_occupati.append((full_start, end_pos))
        
        # --- FILTRI PER SELEZIONE HELPER ---
        
        # 1. Escludi se è un Test Case noto
        if method_name in test_names_to_exclude:
            continue

        code = codice_test[full_start:end_pos]
        
        # Verifica che il metodo sia completo (bilanciamento graffe corretto)
        open_braces_in_code = code.count('{')
        close_braces_in_code = code.count('}')
        if open_braces_in_code != close_braces_in_code:
            # Metodo non bilanciato - probabilmente troncato, salta
            continue
        
        # Verifica che il metodo abbia un corpo valido (almeno una graffa di apertura e chiusura)
        if open_braces_in_code == 0:
            # Metodo senza corpo - salta
            continue
        
        # 2. Escludi se contiene @Test (caso in cui estrai_nomi non l'abbia rilevato)
        if '@Test' in code:
             # Verifica regex per evitare falsi positivi nei commenti
             if re.search(r'@Test\s', code) or re.search(r'@Test$', code, re.MULTILINE):
                 continue

        # 3. Euristica Anti-Spazzatura (Tests rotti mascherati da helper)
        match_decl = match.group(0)
        # Se inizia con "test" e non ha argomenti, è sospetto (manca @Test)
        if method_name.lower().startswith("test") and "()" in match_decl:
             continue
             
        # Filtra codice vago o troncato
        if any(bad in code for bad in ["TEST CODE TO FIX", "TRUNCATED", "code omitted"]):
             if "TEST CODE TO FIX" in code: continue
             if "TRUNCATED" in code: continue
             if "code omitted" in code: continue
        # Filtra "..." se non è in stringa (approssimato)
        if "..." in code and not '"..."' in code and not "'...'" in code:
             continue
        
        # Verifica che il metodo non sia troncato (deve avere almeno un return o una chiusura valida)
        # Se il metodo ha un return type non-void, deve avere almeno un return statement
        return_type_match = re.search(r'(?:public|private|protected)?\s*(?:static\s+)?(?:final\s+)?(\w+(?:<[^>]+>)?)\s+' + re.escape(method_name), code)
        if return_type_match:
            return_type = return_type_match.group(1)
            if return_type != 'void' and 'return' not in code:
                # Metodo con return type ma senza return - probabilmente troncato
                # MA potrebbe essere un metodo che lancia eccezioni, quindi non escludiamo automaticamente
                # Verifica se termina con } senza return - potrebbe essere OK se ha throws
                if not re.search(r'throws\s+\w+', code):
                    # Non ha throws e non ha return - probabilmente troncato
                    continue

        # 4. Aggiungi se unico
        if method_name not in metodi_visti:
            metodi_supporto.append(code)
            metodi_visti.add(method_name)
    
    return metodi_supporto


def estrai_metodo_con_riga_inizio(codice_classe: str, nome_metodo: str, signature: str = None) -> tuple[str, int]:
    """Estrae il metodo e la riga di inizio nel file originale
    
    Args:
        signature: Signature completa del metodo (es: "parse(Options, String[], Properties, boolean)") per distinguere overload
    """
    try:
        import javalang
        import re
        
        # Importa funzioni unificate da code_analysis
        from utils.code.code_analysis import estrai_tipi_parametri_da_signature as _estrai_tipi_parametri_unified, normalizza_tipo_java as _normalizza_tipo_unified
        
        def _estrai_tipi_parametri_da_signature(sig: str) -> list:
            """Wrapper per retrocompatibilità - usa la funzione unificata"""
            return _estrai_tipi_parametri_unified(sig)
        
        def _normalizza_tipo(tipo_str: str) -> str:
            """Wrapper per retrocompatibilità - usa la funzione unificata"""
            return _normalizza_tipo_unified(tipo_str)
        
        def _matcha_parametri(node_params, signature_types):
            """Verifica se i parametri del nodo corrispondono ai tipi nella signature"""
            if not signature_types and not node_params:
                return True
            if not node_params:
                return len(signature_types) == 0
            if len(node_params) != len(signature_types):
                return False
            for i, param in enumerate(node_params):
                param_type = param.type
                tipo_completo = ""
                if hasattr(param_type, 'name'):
                    tipo_completo = param_type.name
                else:
                    tipo_completo = str(param_type)
                if hasattr(param_type, 'dimensions') and param_type.dimensions:
                    tipo_completo += '[]' * len(param_type.dimensions)
                tipo_node = _normalizza_tipo(tipo_completo)
                tipo_expected = _normalizza_tipo(signature_types[i])
                if tipo_node != tipo_expected:
                    return False
            return True
        
        signature_types = _estrai_tipi_parametri_da_signature(signature) if signature else None
        
        tree = javalang.parse.parse(codice_classe)
        lines = codice_classe.split('\n')
        for path, node in tree:
            if isinstance(node, javalang.tree.MethodDeclaration) and node.name == nome_metodo:
                # Se signature fornita, verifica che i parametri corrispondano
                if signature_types is not None:
                    node_params = node.parameters if node.parameters else []
                    if not _matcha_parametri(node_params, signature_types):
                        continue  # Non corrisponde, cerca il prossimo
                
                if hasattr(node, 'position') and node.position:
                    start_line = node.position.line
                else:
                    continue
                start_char = sum(len(lines[i]) + 1 for i in range(start_line - 1))
                brace_count = 0
                in_method = False
                end_char = len(codice_classe)
                for i in range(start_char, len(codice_classe)):
                    if codice_classe[i] == '{':
                        brace_count += 1
                        in_method = True
                    elif codice_classe[i] == '}':
                        brace_count -= 1
                        if in_method and brace_count == 0:
                            end_char = i + 1
                            break
                return codice_classe[start_char:end_char], start_line
    except Exception:
        pass
    
    from core.evaluation.evaluation import estrai_metodo_singolo
    metodo = estrai_metodo_singolo(codice_classe, nome_metodo, signature=signature)
    return metodo, 1


def get_coverage_class_name(class_path: str, project_path: str) -> str:
    """Calcola il nome della classe per la coverage da un path"""
    if "src/main/java" not in class_path:
        return os.path.basename(class_path).replace('.java', '')
    
    rel_path = os.path.relpath(class_path, project_path)
    rel_path_normalized = rel_path.replace("\\", "/")
    if "src/main/java" in rel_path_normalized:
        package_path = rel_path_normalized.split("src/main/java/")[1].replace(".java", "")
        return package_path.replace(os.sep, "/").replace("\\", "/")
    return os.path.basename(class_path).replace('.java', '')


def popola_test_info(test_results: dict, test_path: str) -> dict:
    """Popola test_info nei risultati dei test, assicurando che il numero di test_validi corrisponda esattamente a tests_passed"""
    try:
        with open(test_path, 'r', encoding='utf-8') as f:
            codice_test = f.read()
        
        # Estrai tutti i nomi dei test falliti (sia assert che runtime errors)
        # Usa i campi interni temporanei se disponibili, altrimenti fallback
        failed_test_names_only = test_results.get('_internal_failed_test_names_only', [])
        error_test_names = test_results.get('_internal_error_test_names', [])
        failed_test_names_legacy = test_results.get('_internal_failed_test_names', [])
        
        # Combina tutti i nomi dei test falliti
        all_failed_names = list(set(failed_test_names_only + error_test_names))
        if not all_failed_names and failed_test_names_legacy:
            all_failed_names = failed_test_names_legacy
        
        tests_passed = test_results.get('tests_passed', 0)
        tests_total = test_results.get('tests_total', 0)
        nomi_test_totali = estrai_nomi_test_da_codice(codice_test)
        
        # Filtra nomi non validi dai test (come "Tests", "Test", ecc.)
        nomi_non_validi = {'Test', 'Tests', 'test', 'tests', 'TEST', 'TESTS'}
        nomi_test_totali_filtrati = [t for t in nomi_test_totali if t not in nomi_non_validi]
        
        if tests_total == 0:
            # Nessun test eseguito
            return {'valid_tests': [], 'invalid_tests': [], 'failed_assert_tests': [], 'runtime_error_tests': []}
        
        # Se tutti i test sono passati
        if tests_passed == tests_total:
            return {'valid_tests': nomi_test_totali_filtrati, 'invalid_tests': [], 'failed_assert_tests': [], 'runtime_error_tests': []}
        
        # Distingui tra FAILURE (assert falliti) e ERROR (errori runtime)
        test_falliti_assert = []
        test_errori_runtime = []
        
        for test_name in nomi_test_totali_filtrati:
            # Verifica se questo test corrisponde a un test con FAILURE nell'output Maven
            if match_test_names(test_name, failed_test_names_only):
                test_falliti_assert.append(test_name)
            # Verifica se questo test corrisponde a un test con ERROR nell'output Maven
            elif match_test_names(test_name, error_test_names):
                test_errori_runtime.append(test_name)
        
        # I test validi sono quelli che NON sono in test_falliti_assert né in test_errori_runtime
        test_non_validi = test_falliti_assert + test_errori_runtime
        test_validi = [t for t in nomi_test_totali_filtrati if t not in test_non_validi]
        
        expected_failed = tests_total - tests_passed
        
        # Miglioramento: Se il matching non ha funzionato correttamente, prova un matching più aggressivo
        if len(test_validi) != tests_passed or len(test_non_validi) != expected_failed:
            # Prova matching più flessibile: cerca anche match parziali e case-insensitive più aggressivo
            all_failed_from_output = failed_test_names_only + error_test_names
            
            # Se abbiamo meno test non validi di quelli attesi, prova matching più flessibile
            if len(test_non_validi) < expected_failed and all_failed_from_output:
                # Prova matching più flessibile per ogni test nel codice
                for test_name in nomi_test_totali_filtrati:
                    if test_name in test_validi:  # Solo se non è già classificato come non valido
                        # Matching più flessibile: cerca match parziali
                        test_name_lower = test_name.lower()
                        for failed_name in all_failed_from_output:
                            failed_lower = str(failed_name).lower()
                            # Match se il nome del test contiene il nome fallito o viceversa
                            if (test_name_lower in failed_lower or failed_lower in test_name_lower or
                                test_name_lower.replace('_', '') == failed_lower.replace('_', '') or
                                test_name_lower.replace('test', '') == failed_lower.replace('test', '')):
                                # Rimuovi da validi e aggiungi a non validi
                                if test_name in test_validi:
                                    test_validi.remove(test_name)
                                if test_name not in test_non_validi:
                                    test_non_validi.append(test_name)
                                # Classifica come FAILURE o ERROR
                                if match_test_names(test_name, failed_test_names_only):
                                    if test_name not in test_falliti_assert:
                                        test_falliti_assert.append(test_name)
                                elif match_test_names(test_name, error_test_names):
                                    if test_name not in test_errori_runtime:
                                        test_errori_runtime.append(test_name)
                                else:
                                    # Se non riusciamo a classificare, prova a vedere se è in failed o error
                                    if failed_name in failed_test_names_only:
                                        if test_name not in test_falliti_assert:
                                            test_falliti_assert.append(test_name)
                                    elif failed_name in error_test_names:
                                        if test_name not in test_errori_runtime:
                                            test_errori_runtime.append(test_name)
                                break
            
            # Se ancora non corrisponde, usa l'ordine nel codice come fallback
            if len(test_validi) != tests_passed or len(test_non_validi) != expected_failed:
                print(f"WARNING:  Discrepancy in test classification!")
                print(f"   Total tests in code: {len(nomi_test_totali_filtrati)}")
                print(f"   Total tests executed: {tests_total}")
                print(f"   Tests passed: {tests_passed}")
                print(f"   Expected failed tests: {expected_failed}")
                print(f"   Valid tests found: {len(test_validi)} - {test_validi}")
                print(f"   Failed tests (FAILURE) found: {len(test_falliti_assert)} - {test_falliti_assert}")
                print(f"   Error tests (ERROR) found: {len(test_errori_runtime)} - {test_errori_runtime}")
                print(f"   Failed tests from Maven output (FAILURE): {failed_test_names_only}")
                print(f"   Error tests from Maven output (ERROR): {error_test_names}")
                
                # Fallback: usa l'ordine nel codice, ma solo se il numero di test nel codice corrisponde
                if len(nomi_test_totali_filtrati) == tests_total:
                    test_validi = nomi_test_totali_filtrati[:tests_passed]
                    test_non_validi = nomi_test_totali_filtrati[tests_passed:]
                    # Classifica i test falliti usando le liste dall'output Maven
                    test_falliti_assert = [t for t in test_non_validi if match_test_names(t, failed_test_names_only)]
                    test_errori_runtime = [t for t in test_non_validi if match_test_names(t, error_test_names)]
                    # Se non riusciamo a classificarli, distribuisci tra FAILURE e ERROR in base ai numeri attesi
                    remaining = [t for t in test_non_validi if t not in test_falliti_assert and t not in test_errori_runtime]
                    if remaining:
                        # Distribuisci i rimanenti: prima ERROR (se attesi), poi FAILURE
                        expected_errors = len(error_test_names)
                        if len(test_errori_runtime) < expected_errors and error_test_names:
                            for t in remaining[:expected_errors - len(test_errori_runtime)]:
                                test_errori_runtime.append(t)
                            remaining = [t for t in remaining if t not in test_errori_runtime]
                        # I rimanenti vanno in FAILURE
                        test_falliti_assert.extend(remaining)
                else:
                    # Se anche il numero di test nel codice non corrisponde, mantieni la classificazione attuale
                    # ma aggiusta i numeri per farli corrispondere
                    if len(test_validi) > tests_passed:
                        # Troppi validi: rimuovi gli ultimi
                        test_non_validi.extend(test_validi[tests_passed:])
                        test_validi = test_validi[:tests_passed]
                    elif len(test_validi) < tests_passed:
                        # Troppi pochi validi: aggiungi dai non validi
                        needed = tests_passed - len(test_validi)
                        if needed <= len(test_non_validi):
                            test_validi.extend(test_non_validi[:needed])
                            test_non_validi = test_non_validi[needed:]
        
        return {
            'valid_tests': test_validi, 
            'invalid_tests': test_non_validi,
            'failed_assert_tests': test_falliti_assert,
            'runtime_error_tests': test_errori_runtime
        }
    except Exception as e:
        print(f"WARNING: {e}")
        return {'valid_tests': [], 'invalid_tests': [], 'failed_assert_tests': [], 'runtime_error_tests': []}


def stampa_risultati_test(test_results: dict, metodo: str = "method"):
    """Stampa i risultati dei test"""
    tests_total = test_results.get('tests_total', 0)
    tests_passed = test_results.get('tests_passed', 0)
    tests_failed = tests_total - tests_passed
    line_coverage = test_results.get('line_coverage', 0.0)
    branch_coverage = test_results.get('branch_coverage', 0.0)
    
    print(f"\nTest results for {metodo}:")
    print(f"   Total tests executed: {tests_total}")
    print(f"   Tests passed: {tests_passed}")
    print(f"   Tests failed: {tests_failed}")
    if tests_total > 0:
        print(f"   Line Coverage: {line_coverage:.2f}%")
        print(f"   Branch Coverage: {branch_coverage:.2f}%")


def verifica_errori_sintassi(test_results: dict) -> bool:
    """Verifica se ci sono errori di sintassi/compilazione"""
    has_compilation_errors = test_results.get('has_compilation_errors', False)
    tests_total = test_results.get('tests_total', 0)
    tests_passed = test_results.get('tests_passed', 0)
    output = test_results.get('output', '').lower()
    
    # Se ci sono errori di compilazione espliciti rilevati, ferma sempre
    if has_compilation_errors:
        return True
    
    # Se nessun test è stato eseguito (0/0), verifica se è un errore di compilazione
    # Ma solo se ci sono indicatori espliciti di errore, non per default
    if tests_total == 0 and tests_passed == 0:
        # Se c'è BUILD FAILURE senza "Tests run:", verifica indicatori di errore Java
        if 'build failure' in output and 'tests run:' not in output:
            # Verifica se ci sono indicatori espliciti di errore Java
            java_error_indicators = [
                'error:', 'cannot find', 'does not exist', 'symbol not found',
                'compilation error', 'cannot resolve symbol', 'package.*does not exist'
            ]
            # Usa regex per pattern più specifici
            for indicator in java_error_indicators:
                if re.search(indicator, output):
                    return True
        
        # Se non ci sono test trovati e ci sono errori espliciti, ferma
        if 'no tests found' in output or 'no tests to run' in output:
            # Solo se accompagnato da errori di compilazione
            if has_compilation_errors or any(err in output for err in ['error:', 'cannot find', 'compilation']):
                return True
    
    return False


def verifica_nessun_test_passato(test_results: dict) -> bool:
    """Verifica se nessun test è passato (tutti falliti)"""
    tests_total = test_results.get('tests_total', 0)
    tests_passed = test_results.get('tests_passed', 0)
    
    # Se ci sono test eseguiti ma nessuno è passato, ferma l'esperimento
    if tests_total > 0 and tests_passed == 0:
        return True
    
    return False


def prepara_test_results_info(result: dict) -> dict:
    """Prepara test_results_info per il salvataggio delle metriche"""
    test_results_info = {}
    
    if result.get('original_test_results'):
        orig = result['original_test_results']
        orig_test_info = orig.get('test_info', {'valid_tests': [], 'invalid_tests': []})
        test_results_info['original'] = {
            'success': orig['success'],
            'tests_passed': orig['tests_passed'],
            'tests_total': orig['tests_total'],
            'tests_failed': orig.get('tests_total', 0) - orig.get('tests_passed', 0),
            'line_coverage': orig['line_coverage'],
            'branch_coverage': orig['branch_coverage'],
            'has_compilation_errors': orig.get('has_compilation_errors', False),
            # error_test_names rimosso - usare solo test_info.test_errori_runtime
            # test_info ora include anche test_falliti_assert e test_errori_runtime
            'test_info': {
                'valid_tests': orig_test_info.get('valid_tests', []),
                'invalid_tests': orig_test_info.get('invalid_tests', []),
                'failed_assert_tests': orig_test_info.get('failed_assert_tests', []),
                'runtime_error_tests': orig_test_info.get('runtime_error_tests', [])
            }
        }
    
    if result.get('regenerated_test_results'):
        regen = result['regenerated_test_results']
        regen_test_info = regen.get('test_info', {'valid_tests': [], 'invalid_tests': []})
        test_results_info['regenerated'] = {
            'success': regen['success'],
            'tests_passed': regen['tests_passed'],
            'tests_total': regen['tests_total'],
            'tests_failed': regen.get('tests_total', 0) - regen.get('tests_passed', 0),
            'line_coverage': regen['line_coverage'],
            'branch_coverage': regen['branch_coverage'],
            'has_compilation_errors': regen.get('has_compilation_errors', False),
            # error_test_names rimosso - usare solo test_info.test_errori_runtime
            # test_info ora include anche test_falliti_assert e test_errori_runtime
            'test_info': {
                'valid_tests': regen_test_info.get('valid_tests', []),
                'invalid_tests': regen_test_info.get('invalid_tests', []),
                'failed_assert_tests': regen_test_info.get('failed_assert_tests', []),
                'runtime_error_tests': regen_test_info.get('runtime_error_tests', [])
            }
        }
    
    return test_results_info

