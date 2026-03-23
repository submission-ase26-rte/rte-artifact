import os
import subprocess
import xml.etree.ElementTree as ET
import shutil
import re
from typing import Dict, Optional, Tuple


def find_pom_file(project_path: str) -> Optional[str]:
    pom_path = os.path.join(project_path, "pom.xml")
    return pom_path if os.path.exists(pom_path) else None


def backup_pom(pom_path: str) -> str:
    backup_path = pom_path + ".backup"
    shutil.copy2(pom_path, backup_path)
    return backup_path


def restore_pom(pom_path: str, backup_path: str):
    if os.path.exists(backup_path):
        shutil.copy2(backup_path, pom_path)
        os.remove(backup_path)


def add_jacoco_to_pom(pom_path: str) -> bool:
    """
    Aggiunge il plugin JaCoCo al pom.xml in modo robusto.
    Gestisce build, plugins e pluginManagement.
    Ritorna True se il file è stato modificato, False altrimenti.
    """
    try:
        with open(pom_path, 'r', encoding='utf-8') as f:
            content = f.read()
            
        if 'jacoco-maven-plugin' in content:
            return False

        jacoco_plugin = '''<plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.11</version>
                <configuration>
                  <dataFile>${project.build.directory}/jacoco.exec</dataFile>
                </configuration>
                <executions>
                  <execution>
                    <id>prepare-agent</id>
                    <goals>
                      <goal>prepare-agent</goal>
                    </goals>
                  </execution>
                  <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                      <goal>report</goal>
                    </goals>
                  </execution>
                </executions>
              </plugin>'''

        # Trova se esiste <build>
        build_match = re.search(r'<build>.*?</build>', content, re.DOTALL)
        
        if build_match:
            # Analisi posizionale per trovare <plugins> attivo (fuori da pluginManagement)
            plugins_intervals = [(m.start(), m.end()) for m in re.finditer(r'<plugins>.*?</plugins>', content, re.DOTALL)]
            mgmt_intervals = [(m.start(), m.end()) for m in re.finditer(r'<pluginManagement>.*?</pluginManagement>', content, re.DOTALL)]
            
            target_plugins_interval = None
            
            for p_start, p_end in plugins_intervals:
                # Controlla se questo intervallo è dentro un mgmt interval
                is_inside_mgmt = False
                for m_start, m_end in mgmt_intervals:
                    if m_start <= p_start and p_end <= m_end:
                        is_inside_mgmt = True
                        break
                
                if not is_inside_mgmt:
                    # Trovato <plugins> attivo!
                    target_plugins_interval = (p_start, p_end)
                    break
            
            if target_plugins_interval:
                # Inserisci dentro questo <plugins>
                p_start, p_end = target_plugins_interval
                original_plugins_block = content[p_start:p_end]
                # Inserisci prima di </plugins>
                new_plugins_block = original_plugins_block.replace('</plugins>', f'{jacoco_plugin}\n</plugins>', 1)
                content = content[:p_start] + new_plugins_block + content[p_end:]
            else:
                # <build> esiste ma non c'è <plugins> attivo. Aggiungilo all'inizio di <build>
                content = content.replace('<build>', f'<build>\n<plugins>\n{jacoco_plugin}\n</plugins>', 1)
                
        else:
            # <build> non esiste. Inserisci prima di </project>
            if '</project>' in content:
                content = content.replace('</project>', f'\n<build>\n<plugins>\n{jacoco_plugin}\n</plugins>\n</build>\n</project>', 1)
            else:
                print(f"WARNING:  Unable to add JaCoCo: </project> not found in {pom_path}")
                return False

        with open(pom_path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
            
        return False
    
    except Exception as e:
        print(f"WARNING:  Error adding JaCoCo to POM: {e}")
        return False


def add_surefire_to_pom(pom_path: str) -> bool:
    """
    Aggiunge o configura il maven-surefire-plugin nel pom.xml.
    Imposta <argLine>@{argLine} -Xmx512m</argLine> per la memoria.
    """
    try:
        with open(pom_path, 'r', encoding='utf-8') as f:
            content = f.read()

        surefire_plugin_template = '''<plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
          <argLine>@{argLine} -Xmx512m</argLine>
        </configuration>
      </plugin>'''

        # Verifica se il plugin esiste già
        if 'maven-surefire-plugin' in content:
           
            if '-Xmx512m' in content and 'maven-surefire-plugin' in content:
                return False # Assume già configurato
            
            return False

        # Se non esiste, lo aggiungiamo usando la stessa logica di JaCoCo
        # Trova se esiste <build>
        build_match = re.search(r'<build>.*?</build>', content, re.DOTALL)
        
        if build_match:
            # Analisi posizionale per trovare <plugins> attivo (fuori da pluginManagement)
            plugins_intervals = [(m.start(), m.end()) for m in re.finditer(r'<plugins>.*?</plugins>', content, re.DOTALL)]
            mgmt_intervals = [(m.start(), m.end()) for m in re.finditer(r'<pluginManagement>.*?</pluginManagement>', content, re.DOTALL)]
            
            target_plugins_interval = None
            
            for p_start, p_end in plugins_intervals:
                # Controlla se questo intervallo è dentro un mgmt interval
                is_inside_mgmt = False
                for m_start, m_end in mgmt_intervals:
                    if m_start <= p_start and p_end <= m_end:
                        is_inside_mgmt = True
                        break
                
                if not is_inside_mgmt:
                    # Trovato <plugins> attivo!
                    target_plugins_interval = (p_start, p_end)
                    break
            
            if target_plugins_interval:
                # Inserisci dentro questo <plugins>
                p_start, p_end = target_plugins_interval
                original_plugins_block = content[p_start:p_end]
                # Inserisci prima di </plugins>
                new_plugins_block = original_plugins_block.replace('</plugins>', f'{surefire_plugin_template}\n</plugins>', 1)
                content = content[:p_start] + new_plugins_block + content[p_end:]
            else:
                # <build> esiste ma non c'è <plugins> attivo. Aggiungilo all'inizio di <build>
                content = content.replace('<build>', f'<build>\n<plugins>\n{surefire_plugin_template}\n</plugins>', 1)
                
        else:
            # <build> non esiste. Inserisci prima di </project>
            if '</project>' in content:
                content = content.replace('</project>', f'\n<build>\n<plugins>\n{surefire_plugin_template}\n</plugins>\n</build>\n</project>', 1)
            else:
                print(f"WARNING:  Unable to add Surefire: </project> not found in {pom_path}")
                return False

        with open(pom_path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True

    except Exception as e:
        print(f"WARNING:  Error adding Surefire to POM: {e}")
        return False


def run_tests_with_coverage(project_path: str, test_class: str = None, skip_clean: bool = False, timeout: int = None) -> Tuple[int, str]:
    """
    Esegue i test con coverage in un unico passaggio.
    Maven compila ed esegue i test automaticamente con 'mvn test' (non serve compilazione separata).
    """
    abs_project_path = os.path.abspath(project_path)
    
    # Flags comuni per saltare plugin opzionali (RAT, checkstyle, spotbugs, japicmp, spring-javaformat)
    skip_plugins = [
        "-Drat.skip=true",
        "-Dcheckstyle.skip=true", 
        "-Dspotbugs.skip=true",
        "-Djapicmp.skip=true",
        "-Denforcer.skip=true",
        "-Dspring-javaformat.skip=true"
    ]
    
    if skip_clean:
        cmd_parts_test = ["mvn", "test", "-e", "-Dmaven.test.failure.ignore=true"] + skip_plugins
    else:
        cmd_parts_test = ["mvn", "clean", "test", "-e", "-Dmaven.test.failure.ignore=true"] + skip_plugins
    if test_class:
        cmd_parts_test.append(f"-Dtest={test_class}")
    
    encoding = 'cp1252' if os.name == 'nt' else 'utf-8'
    
    try:
        if os.name == 'nt':
            cmd_str_for_shell = " ".join(cmd_parts_test)
            full_cmd = f'cd /d "{abs_project_path}" && {cmd_str_for_shell}'
            result = subprocess.run(
                full_cmd,
                shell=True,
                capture_output=True,
                text=True,
                encoding=encoding,
                errors='replace',
                timeout=timeout
            )
        else:
            result = subprocess.run(cmd_parts_test, capture_output=True, text=True, encoding=encoding, errors='replace', shell=False, cwd=abs_project_path, timeout=timeout)
    except subprocess.TimeoutExpired:
        print(f"WARNING:  TIMEOUT EXPIRED executing tests ({timeout}s).")
        return 124, "TIMEOUT EXPIRED"
    
    test_returncode = result.returncode
    test_output = (result.stdout if result.stdout else "") + (result.stderr if result.stderr else "")
    
    cmd_parts_report = ["mvn", "jacoco:report"]
    
    if os.name == 'nt':
        cmd_str_for_shell = " ".join(cmd_parts_report)
        full_cmd_report = f'cd /d "{abs_project_path}" && {cmd_str_for_shell}'
        result_report = subprocess.run(
            full_cmd_report,
            shell=True,
            capture_output=True,
            text=True,
            encoding=encoding,
            errors='replace'
        )
    else:
        result_report = subprocess.run(cmd_parts_report, capture_output=True, text=True, encoding=encoding, errors='replace', shell=False, cwd=abs_project_path)
    
    report_output = (result_report.stdout if result_report.stdout else "") + (result_report.stderr if result_report.stderr else "")
    test_output += "\n\n=== JaCoCo Report Generation ===\n" + report_output
    
    return test_returncode, test_output


def extract_method_name_from_test(test_class_name: str) -> tuple:
    test_name = test_class_name.replace("TestGenerated", "").replace("Test", "")
    test_name = re.sub(r'(\d+)$', '', test_name)
    
    if '_' in test_name:
        parts = test_name.split('_', 1)
        if len(parts) == 2:
            return (parts[0], parts[1])
    return (None, None)


def parse_jacoco_report_for_method(report_path: str, class_name: str, method_name: str) -> Dict:
    if not os.path.exists(report_path):
        return {'line_coverage': 0.0, 'branch_coverage': 0.0, 'found': False}
    
    try:
        tree = ET.parse(report_path)
        root = tree.getroot()
    except ET.ParseError:
        return {'line_coverage': 0.0, 'branch_coverage': 0.0, 'found': False}
    
    method_counters = {}
    method_found = False
    class_name_lower = class_name.lower()
    method_name_lower = method_name.lower()
    
    best_counters = {}
    best_coverage_count = -1
    best_line_coverage = 0.0
    
    for package in root.findall('.//package'):
        for class_elem in package.findall('class'):
            full_class_name = class_elem.get('name', '')
            simple_class_name = full_class_name.split('/')[-1] if '/' in full_class_name else full_class_name
            
            # Normalizza i nomi per il matching
            class_name_normalized = class_name_lower.replace('/', '').replace('_', '').replace('-', '')
            simple_class_name_normalized = simple_class_name.lower().replace('_', '').replace('-', '').replace('/', '')
            full_class_name_normalized = full_class_name.lower().replace('_', '').replace('-', '').replace('/', '')
            
            # Rimuovi numeri per matching più flessibile
            class_name_normalized_no_digits = ''.join(c for c in class_name_normalized if not c.isdigit())
            simple_class_name_normalized_no_digits = ''.join(c for c in simple_class_name_normalized if not c.isdigit())
            full_class_name_normalized_no_digits = ''.join(c for c in full_class_name_normalized if not c.isdigit())
            
            is_regenerated_class = 'generated' in simple_class_name.lower() or 'generated' in full_class_name.lower()
            is_original_class_search = 'generated' not in class_name_lower
            
            # Se stiamo cercando una classe rigenerata, salta le classi originali
            if not is_original_class_search and not is_regenerated_class:
                continue
            
            # Se stiamo cercando una classe originale, salta le classi rigenerate
            if is_original_class_search and is_regenerated_class:
                continue
            
            # Matching esatto
            class_matches = (
                simple_class_name.lower() == class_name_lower or
                full_class_name.lower() == class_name_lower or
                full_class_name.lower().endswith('/' + class_name_lower) or
                full_class_name.lower().endswith(class_name_lower)
            )
            
            # Matching normalizzato (senza caratteri speciali) - solo se entrambe sono rigenerate o entrambe originali
            if not class_matches:
                # Per classi rigenerate, evita match parziali con classi originali
                if not is_original_class_search and is_regenerated_class:
                   
                    # Prima prova match esatto con numeri
                    class_name_clean = class_name_lower.replace('_', '').replace('-', '').replace('/', '')
                    simple_class_clean = simple_class_name.lower().replace('_', '').replace('-', '').replace('/', '')
                    full_class_clean = full_class_name.lower().replace('_', '').replace('-', '').replace('/', '')
                    
                    # Match esatto (con numeri) 
                    class_matches = (
                        class_name_clean == simple_class_clean or
                        class_name_clean == full_class_clean or
                        class_name_lower == simple_class_name.lower() or
                        class_name_lower == full_class_name.lower()
                    )
                    
                    # Se non c'è match esatto, prova solo se il nome base (senza numero finale) corrisponde
                    # ma solo se entrambe hanno lo stesso numero di caratteri prima del numero
                    if not class_matches:
                        # Estrai il numero finale da entrambi
                        import re as re_module
                        search_num_match = re_module.search(r'(\d+)$', class_name)
                        report_num_match = re_module.search(r'(\d+)$', simple_class_name)
                        
                        if search_num_match and report_num_match:
    
                            pass  # Non fare match se i numeri sono diversi
                        else:
                            # Se uno non ha numero, prova match normalizzato
                            class_matches = (
                                class_name_normalized_no_digits == simple_class_name_normalized_no_digits or
                                class_name_normalized_no_digits == full_class_name_normalized_no_digits
                            )
                else:
                    # Matching normale per classi originali
                    class_matches = (
                        class_name_normalized_no_digits == simple_class_name_normalized_no_digits or
                        class_name_normalized_no_digits == full_class_name_normalized_no_digits or
                        class_name_normalized_no_digits in simple_class_name_normalized_no_digits or
                        class_name_normalized_no_digits in full_class_name_normalized_no_digits or
                        simple_class_name_normalized_no_digits in class_name_normalized_no_digits or
                        full_class_name_normalized_no_digits in class_name_normalized_no_digits
                    )
            
            
            if class_matches:
                for method_elem in class_elem.findall('method'):
                    method_elem_name = method_elem.get('name', '')
                    
                    method_matches = (
                        method_elem_name.lower() == method_name_lower or
                        method_elem_name.lower().startswith(method_name_lower) or
                        method_name_lower in method_elem_name.lower()
                    )
                    
                    if method_matches:
                        method_found = True
                        
                        current_method_counters = {}
                        current_covered_lines = 0
                        current_line_percentage = 0.0
                        
                        for counter in method_elem.findall('counter'):
                            counter_type = counter.get('type')
                            if counter_type:
                                missed = int(counter.get('missed', 0))
                                covered = int(counter.get('covered', 0))
                                total = missed + covered
                                percentage = (covered / total) * 100 if total > 0 else 0.0
                                
                                current_method_counters[counter_type] = {
                                    'missed': missed,
                                    'covered': covered,
                                    'total': total,
                                    'percentage': percentage
                                }
                                
                                if counter_type == 'LINE':
                                    current_covered_lines = covered
                                    current_line_percentage = percentage

                        # Logica di selezione migliore overload:
                
                        if current_covered_lines > best_coverage_count:
                            best_coverage_count = current_covered_lines
                            best_counters = current_method_counters
                            best_line_coverage = current_line_percentage
                        elif current_covered_lines == best_coverage_count:
                         
                            if not best_counters: # Primo match
                                best_counters = current_method_counters
                                best_line_coverage = current_line_percentage
                
       
    if method_found and best_counters:
        method_counters = best_counters
    
    line_coverage = method_counters.get('LINE', {}).get('percentage', 0.0)
    branch_coverage = method_counters.get('BRANCH', {}).get('percentage', 0.0)
    
    return {
        'line_coverage': line_coverage,
        'branch_coverage': branch_coverage,
        'found': method_found,
        'method_counters': method_counters,
        'class_name': class_name,
        'method_name': method_name
    }


def find_jacoco_report(project_path: str) -> Optional[str]:
    jacoco_base = os.path.join(project_path, "target", "site", "jacoco")
    report_paths = [os.path.join(jacoco_base, "jacoco.xml"), os.path.join(jacoco_base, "index.xml")]
    
    if os.path.exists(jacoco_base):
        for root, dirs, files in os.walk(jacoco_base):
            for file in files:
                if file in ("jacoco.xml", "index.xml"):
                    report_paths.append(os.path.join(root, file))
    
    for path in report_paths:
        if os.path.exists(path):
            return path
    return None


def extract_uncovered_lines(report_path: str, class_name: str, method_name: str, 
                            riga_inizio_metodo: Optional[int] = None, 
                            riga_fine_metodo: Optional[int] = None,
                            codice_classe_completo: Optional[str] = None) -> list[int]:
    """
    Estrae le righe non coperte del metodo specifico dal report JaCoCo XML.
    Corregge il problema della struttura XML e filtra per range di righe del metodo.
    """
    uncovered_lines = []
    
    if not os.path.exists(report_path):
        return uncovered_lines
    
    try:
        tree = ET.parse(report_path)
        root = tree.getroot()
    except ET.ParseError as e:
        return uncovered_lines
    
    class_name_lower = class_name.lower()
    method_name_lower = method_name.lower()
    
    # 1. Trova il package e la classe
    packages_found = 0
    for package in root.findall('.//package'):
        packages_found += 1
        target_class_elem = None
        source_filename = None
        
        # Cerca la classe corretta nel package
        for class_elem in package.findall('class'):
            full_class_name = class_elem.get('name', '')
            simple_class_name = full_class_name.split('/')[-1] if '/' in full_class_name else full_class_name
            
            # --- LOGICA DI MATCHING ---
        
            is_generated_class = (
                'generated' in simple_class_name.lower() or
                'Generated' in simple_class_name or
                '_' in simple_class_name and simple_class_name != class_name  
            )
            

            is_searching_for_original = 'generated' not in class_name_lower
            if is_searching_for_original and is_generated_class:
                continue
            
            is_match = (
                simple_class_name == class_name or  
                full_class_name == class_name or  
                full_class_name.endswith('/' + class_name)  
            )
            
            if is_match:
                target_class_elem = class_elem
                source_filename = class_elem.get('sourcefilename')
                break
        
        # Se abbiamo trovato la classe, ora cerchiamo il metodo per capire il range di righe
        if target_class_elem is not None and source_filename:
            # Usa il range dal file sorgente se fornito, altrimenti calcolalo dal report JaCoCo
            if riga_inizio_metodo is not None and riga_fine_metodo is not None:
                method_start_line = riga_inizio_metodo
                method_end_line = riga_fine_metodo
            else:
                method_start_line = -1
                method_end_line = float('inf')
                
                # Raccogli tutti i metodi per ordinarli e trovare i range
                methods = []
                for m in target_class_elem.findall('method'):
                    m_name = m.get('name', '')
                    try:
                        m_line = int(m.get('line', -1))
                    except (ValueError, TypeError):
                        m_line = -1
                    
                    if m_line > 0:
                        methods.append({'name': m_name, 'line': m_line, 'elem': m})
                
                # Ordina per riga
                methods.sort(key=lambda x: x['line'])
                
                # Trova il metodo target e determina il range
                for i, m_data in enumerate(methods):
                    m_elem_name = m_data['name']
                    
                    # Matching Metodo
                    if (method_name_lower == m_elem_name.lower() or 
                        m_elem_name.lower().startswith(method_name_lower)):
                        
                        method_start_line = m_data['line']
                        
                        # La fine è l'inizio del prossimo metodo (se esiste)
                        if i < len(methods) - 1:
                            method_end_line = methods[i+1]['line']
                        break
                
                # Usando range dal report JaCoCo se non disponibile dal file sorgente
            
            if method_start_line != -1:
                
                # 2. Trova il sourcefile nel PACKAGE
                target_sourcefile = None
                for sf in package.findall('sourcefile'):
                    if sf.get('name') == source_filename:
                        target_sourcefile = sf
                        break
                
                # 3. Estrai le righe non coperte NEL RANGE del metodo
                if target_sourcefile is not None:
                    all_uncovered_in_sourcefile = []
                    for line in target_sourcefile.findall('line'):
                        try:
                            nr = int(line.get('nr', 0))
                            mi = int(line.get('mi', 0)) # Missed Instructions
                            mb = int(line.get('mb', 0)) # Missed Branches
                        except (ValueError, TypeError):
                            continue
                        
                        # Se c'è qualcosa di non coperto (anche fuori dal metodo, per debug)
                        if mi > 0 or mb > 0:
                            all_uncovered_in_sourcefile.append(nr)
                        
                        # Controlla se la riga appartiene al metodo
                        # Usa <= per includere anche l'ultima riga del metodo
                        if method_start_line <= nr <= method_end_line:
                            # Se c'è qualcosa di non coperto
                            if mi > 0 or mb > 0:
                                # Verifica se la riga è solo un commento o solo parentesi graffe (escludila se lo è)
                                if codice_classe_completo:
                                    class_lines = codice_classe_completo.split('\n')
                                    if nr <= len(class_lines):
                                        line_content = class_lines[nr - 1]  # nr è 1-based
                                        from utils.code.code_analysis import is_only_comment, is_only_braces_or_empty, is_only_else_or_control_structure
                                        # Escludi se è solo commento, solo parentesi graffe/spazi, o solo else/control structure
                                        if (not is_only_comment(line_content) and 
                                            not is_only_braces_or_empty(line_content) and 
                                            not is_only_else_or_control_structure(line_content)):
                                            uncovered_lines.append(nr)
                                    else:
                                        uncovered_lines.append(nr)
                                else:
                                    # Se non abbiamo il codice completo, includiamo tutte le righe
                                    uncovered_lines.append(nr)
                    
                    if uncovered_lines:
                        return sorted(list(set(uncovered_lines)))


    # Fallback ricorsivo
    if not uncovered_lines and '/' in class_name:
        return extract_uncovered_lines(report_path, class_name.split('/')[-1], method_name)

    return sorted(list(set(uncovered_lines)))
