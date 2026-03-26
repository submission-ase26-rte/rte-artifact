
import os
import re
from typing import Dict, List, Set, Tuple, Optional
import javalang
from utils.tracking.logger import setup_logger
from utils.io.file_utils import leggi_file



logger = setup_logger()

class JavaDependencyAnalyzer:
    """
    Analizzatore per estrarre dipendenze Java da una classe target e risolvere
    automaticamente le classi correlate in un progetto.
    """
    
    def __init__(self, project_root: str):
        self.project_root = project_root
        self.java_files_cache = {}
        self.dependency_graph = {}
        self._project_base_package_cache = None
        
    def find_java_files(self, directory: str = None) -> List[str]:
        """Trova tutti i file Java in un progetto."""
        if directory is None:
            directory = self.project_root
            
        java_files = []
        for root, dirs, files in os.walk(directory):
            # Esclude cartelle di test e build
            dirs[:] = [d for d in dirs if d not in ['test', 'target', 'build', '.git']]
            for file in files:
                if file.endswith('.java'):
                    java_files.append(os.path.join(root, file))
        return java_files
    
    def extract_imports(self, java_code: str) -> List[str]:
        """Estrae gli import da codice Java."""
        imports = []
        lines = java_code.split('\n')
        
        for line in lines:
            line = line.strip()
            if line.startswith('import ') and not line.startswith('import static'):
                # Rimuove 'import ' e ';'
                import_statement = line[7:].rstrip(';')
                imports.append(import_statement)
            elif line.startswith('package '):
                # Salta le righe package
                continue
            elif line.startswith('public class') or line.startswith('class') or line.startswith('public interface'):
                # Ferma quando inizia la definizione della classe
                break
                
        return imports
    
    def extract_class_name(self, java_code: str) -> Optional[str]:
        """Estrae il nome della classe dal codice Java."""
        if not java_code:
            return None
            
        lines = java_code.split('\n')
        for line in lines:
            line = line.strip()
            # Cerca definizioni di classe, interfaccia o enum
            if 'class ' in line or 'interface ' in line or 'enum ' in line:
                # Regex più robusta che gestisce public, abstract, final, static
                class_pattern = r'(?:public|protected|private|abstract|static|final|\s)*\s+(?:class|interface|enum)\s+(\w+)'
                match = re.search(class_pattern, line)
                if match:
                    # Verifica che non sia dentro un commento o stringa (molto basic)
                    if not line.startswith('//') and not line.startswith('*'):
                        return match.group(1)
        return None
    
    def resolve_import_to_file(self, import_statement: str, project_files: List[str]) -> Optional[str]:
        """Risolve un import statement al file Java corrispondente."""
        # Converte import com.example.Class in path com/example/Class.java
        import_path = import_statement.replace('.', '/') + '.java'
        
        # Cerca corrispondenza esatta
        for file_path in project_files:
            if file_path.endswith(import_path):
                return file_path
        
        # Cerca con il nome della classe alla fine
        class_name = import_statement.split('.')[-1]
        for file_path in project_files:
            if file_path.endswith(f'/{class_name}.java'):
                return file_path
        
        # Cerca con pattern più flessibile (per gestire package diversi)
        for file_path in project_files:
            if f'/{class_name}.java' in file_path:
                return file_path
                
        # Cerca per nome classe senza path
        for file_path in project_files:
            filename = os.path.basename(file_path)
            if filename == f'{class_name}.java':
                return file_path
                
        return None
    
    def analyze_class_dependencies(self, target_class_path: str) -> Dict[str, str]:
        """
        Analizza le dipendenze di una classe target e restituisce un dizionario
        {nome_classe: codice_classe} per tutte le classi dipendenti.
        Include anche classi dello stesso package usate senza import esplicito.
        """
        logger.info(f"Analyzing dependencies for: {target_class_path}")
        
        # Carica il codice della classe target
        target_code = leggi_file(target_class_path)
        if not target_code:
            logger.error(f"Unable to read {target_class_path}")
            return {}
            
        # Estrae il classe target
        target_class_name = self.extract_class_name(target_code)
        if not target_class_name:
            logger.error(f"Unable to extract class name from {target_class_path}")
            return {}
        
        # Estrae il package della classe target
        target_package = self._extract_package(target_code)
        logger.info(f"Target class package: {target_package}")
            
        # Trova tutti i file Java nel progetto
        java_files = self.find_java_files()
        logger.info(f"Found {len(java_files)} Java files in the project")
        
        # Estrae gli import dalla classe target
        imports = self.extract_imports(target_code)
        logger.info(f"Found {len(imports)} imports")
        
        # Risolve le dipendenze in modo ricorsivo
        dependencies = {target_class_name: target_code}
        resolved_imports = set()
        
        # Prima passata: risolve import diretti
        for import_stmt in imports:
            if self._is_external_import(import_stmt):
                continue
                
            resolved_file = self.resolve_import_to_file(import_stmt, java_files)
            if resolved_file:
                logger.info(f"Resolved: {import_stmt} -> {resolved_file}")
                resolved_imports.add(resolved_file)
            else:
                logger.warning(f"Not resolved: {import_stmt}")
        
        # Carica le classi dipendenti dirette (da import)
        for file_path in resolved_imports:
            dep_code = leggi_file(file_path)
            if dep_code:
                dep_class_name = self.extract_class_name(dep_code)
                if dep_class_name:
                    dependencies[dep_class_name] = dep_code
                    logger.info(f"Loaded dependency (import): {dep_class_name}")
        
        # Trova classi dello stesso package usate senza import esplicito
        if target_package:
            same_package_classes = self._find_same_package_classes(target_class_path, target_package, java_files)
            same_package_deps = self._find_same_package_dependencies(target_code, target_class_name, same_package_classes)
            
            for class_name, code in same_package_deps.items():
                if class_name not in dependencies:
                    dependencies[class_name] = code
                    logger.info(f"Loaded dependency (same package, no import): {class_name}")
        
        # Terza passata: analizza dipendenze delle dipendenze (ricorsivo)
        new_dependencies = self._analyze_recursive_dependencies(dependencies, java_files)
        dependencies.update(new_dependencies)
        
        logger.info(f"Total classes loaded: {len(dependencies)}")
        return dependencies
    
    def _analyze_recursive_dependencies(self, current_dependencies: Dict[str, str], java_files: List[str]) -> Dict[str, str]:
        """Analizza le dipendenze delle dipendenze in modo ricorsivo."""
        new_dependencies = {}
        
        for class_name, class_code in current_dependencies.items():
            # Estrae import da questa classe
            imports = self.extract_imports(class_code)
            
            for import_stmt in imports:
                if self._is_external_import(import_stmt):
                    continue
                    
                # Controlla se questa dipendenza è già stata risolta
                dep_class_name = import_stmt.split('.')[-1]
                if dep_class_name in current_dependencies or dep_class_name in new_dependencies:
                    continue
                
                # Risolve la dipendenza
                resolved_file = self.resolve_import_to_file(import_stmt, java_files)
                if resolved_file:
                    dep_code = leggi_file(resolved_file)
                    if dep_code:
                        resolved_class_name = self.extract_class_name(dep_code)
                        if resolved_class_name and resolved_class_name not in current_dependencies:
                            new_dependencies[resolved_class_name] = dep_code
                            logger.info(f"Loaded recursive dependency: {resolved_class_name}")
        
        return new_dependencies
    
    def _get_project_base_package(self) -> Optional[str]:
        """Rileva automaticamente il package base del progetto analizzando i file Java."""
        # Usa la cache se disponibile
        if self._project_base_package_cache is not None:
            return self._project_base_package_cache
        
        java_files = self.find_java_files()
        if not java_files:
            self._project_base_package_cache = None
            return None
        
        packages = set()
        
        # Analizza un campione di file per efficienza
        sample_size = min(50, len(java_files))
        for file_path in java_files[:sample_size]:
            code = leggi_file(file_path)
            if code:
                package = self._extract_package(code)
                if package:
                    # Estrae il package base (primi due livelli, es. com.example)
                    parts = package.split('.')
                    if len(parts) >= 2:
                        base_package = '.'.join(parts[:2])
                        packages.add(base_package)
        
        if packages:
            # Conta le occorrenze su tutti i file
            package_counts = {}
            for file_path in java_files:
                code = leggi_file(file_path)
                if code:
                    package = self._extract_package(code)
                    if package:
                        parts = package.split('.')
                        if len(parts) >= 2:
                            base_package = '.'.join(parts[:2])
                            package_counts[base_package] = package_counts.get(base_package, 0) + 1
            
            if package_counts:
                # Restituisce il package più comune
                result = max(package_counts.items(), key=lambda x: x[1])[0]
                self._project_base_package_cache = result
                return result
        
        self._project_base_package_cache = None
        return None
    
    def _is_external_import(self, import_stmt: str) -> bool:
        """Verifica se un import è esterno (librerie, framework, etc.)."""
        external_prefixes = [
            'java.', 'javax.', 'org.springframework', 'org.hibernate',
            'com.fasterxml', 'org.apache', 'org.slf4j', 'org.junit',
            'junit.', 'org.mockito', 'org.hamcrest', 'org.springframework',
            'org.springframework.security', 'org.springframework.web',
            'org.springframework.boot', 'org.springframework.data'
        ]
        
        # Rileva automaticamente il package base del progetto
        project_base_package = self._get_project_base_package()
        if project_base_package and import_stmt.startswith(project_base_package):
            return False
            
        return any(import_stmt.startswith(prefix) for prefix in external_prefixes)

    
    def _extract_package(self, java_code: str) -> Optional[str]:
        """Estrae il package dal codice Java."""
        lines = java_code.split('\n')
        for line in lines:
            line = line.strip()
            if line.startswith('package '):
                return line[8:].rstrip(';')
        return None
    
    def _find_same_package_classes(self, target_class_path: str, target_package: str, java_files: List[str]) -> Dict[str, str]:
        """
        Trova tutte le classi nello stesso package della classe target.
        Restituisce un dizionario {nome_classe: percorso_file} per le classi dello stesso package.
        """
        same_package_classes = {}
        
        if not target_package:
            return same_package_classes
        
        target_file_normalized = os.path.normpath(target_class_path).lower()
        
        for file_path in java_files:
            # Salta la classe target stessa
            if os.path.normpath(file_path).lower() == target_file_normalized:
                continue
            
            # Ottimizzazione: leggi solo prime righe per package
            try:
                with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                    head = [next(f) for _ in range(20)]
                code_head = "".join(head)
                file_package = self._extract_package(code_head)
                
                # Se non trovato nelle prime righe, leggi tutto (caso raro commenti lunghi)
                if not file_package:
                    code = leggi_file(file_path)
                    file_package = self._extract_package(code)
                else:
                    code = None # Leggeremo dopo se serve
                    
                if file_package == target_package:
                    if code is None:
                        code = leggi_file(file_path)
                    class_name = self.extract_class_name(code)
                    if class_name:
                        same_package_classes[class_name] = file_path
                        logger.debug(f"Found same package class: {class_name}")
            except StopIteration:
                pass # File vuoto
            except Exception as e:
                logger.debug(f"Error reading file {file_path}: {e}")
        
        logger.info(f"Found {len(same_package_classes)} classes in the same package '{target_package}'")
        return same_package_classes
    
    def _extract_types_used_in_code(self, java_code: str) -> Set[str]:
        """
        Extracts all types/classes used in Java code (declarations, parameters, casts, etc.).
        Excludes primitive types and common JDK types.
        """
        types_used = set()
        
        # Types to exclude (primitives + common JDK)
        excluded_types = {
            # Primitivi
            'void', 'int', 'long', 'short', 'byte', 'char', 'boolean', 'float', 'double',
            # Wrapper
            'Integer', 'Long', 'Short', 'Byte', 'Character', 'Boolean', 'Float', 'Double',
            # Comune JDK
            'String', 'Object', 'Class', 'System', 'Exception', 'RuntimeException',
            'Throwable', 'Error', 'Thread', 'Runnable',
            # Collections
            'List', 'ArrayList', 'LinkedList', 'Map', 'HashMap', 'TreeMap', 'LinkedHashMap',
            'Set', 'HashSet', 'TreeSet', 'LinkedHashSet', 'Collection', 'Collections',
            'Iterator', 'Iterable', 'Enumeration', 'Queue', 'Deque', 'ArrayDeque',
            'Optional', 'Stream', 'Collectors',
            # Altri comuni
            'Arrays', 'Math', 'StringBuilder', 'StringBuffer', 'Comparator', 'Comparable',
            'Date', 'Calendar', 'LocalDate', 'LocalDateTime', 'Instant', 'Duration',
            'Pattern', 'Matcher', 'UUID', 'Random', 'File', 'Path', 'Files',
            'InputStream', 'OutputStream', 'Reader', 'Writer', 'BufferedReader', 'BufferedWriter',
            'IOException', 'FileNotFoundException', 'NullPointerException', 'IllegalArgumentException',
            'IllegalStateException', 'UnsupportedOperationException', 'IndexOutOfBoundsException',
            # Annotation comuni
            'Override', 'Deprecated', 'SuppressWarnings', 'FunctionalInterface',
            # Generics placeholder
            'T', 'E', 'K', 'V', 'R', 'U'
        }
        
        try:
            tree = javalang.parse.parse(java_code)
            
            for path, node in tree:
                # Estrae tipi da dichiarazioni di variabili locali
                if isinstance(node, javalang.tree.LocalVariableDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Estrae tipi da parametri di metodi
                if isinstance(node, javalang.tree.FormalParameter):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Estrae tipi da dichiarazioni di campi
                if isinstance(node, javalang.tree.FieldDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Estrae tipi da return type di metodi
                if isinstance(node, javalang.tree.MethodDeclaration):
                    if hasattr(node, 'return_type') and node.return_type and hasattr(node.return_type, 'name'):
                        type_name = node.return_type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Estrae tipi da creazione oggetti (new Classe())
                if isinstance(node, javalang.tree.ClassCreator):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Estrae tipi da cast
                if isinstance(node, javalang.tree.Cast):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Estrae tipi da instanceof
                if isinstance(node, javalang.tree.BinaryOperation):
                    if hasattr(node, 'operator') and node.operator == 'instanceof':
                        if hasattr(node, 'operandr') and hasattr(node.operandr, 'name'):
                            type_name = node.operandr.name.split('<')[0].split('[')[0]
                            if type_name not in excluded_types:
                                types_used.add(type_name)
                
                # Estrae tipi da throws
                if isinstance(node, javalang.tree.MethodDeclaration):
                    if hasattr(node, 'throws') and node.throws:
                        for throw_type in node.throws:
                            if hasattr(throw_type, 'name'):
                                type_name = throw_type.name if isinstance(throw_type.name, str) else throw_type.name
                                if type_name not in excluded_types:
                                    types_used.add(type_name)
                            elif isinstance(throw_type, str):
                                if throw_type not in excluded_types:
                                    types_used.add(throw_type)
                
                # Estrae tipi da extends/implements
                if isinstance(node, javalang.tree.ClassDeclaration):
                    if hasattr(node, 'extends') and node.extends:
                        if hasattr(node.extends, 'name'):
                            type_name = node.extends.name.split('<')[0]
                            if type_name not in excluded_types:
                                types_used.add(type_name)
                    if hasattr(node, 'implements') and node.implements:
                        for impl in node.implements:
                            if hasattr(impl, 'name'):
                                type_name = impl.name.split('<')[0]
                                if type_name not in excluded_types:
                                    types_used.add(type_name)
                
                if isinstance(node, javalang.tree.EnhancedForControl):
                    if hasattr(node, 'var') and hasattr(node.var, 'type') and hasattr(node.var.type, 'name'):
                        type_name = node.var.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                            logger.debug(f"Type from for-each: {type_name}")
                
                if isinstance(node, javalang.tree.MethodInvocation):
                    if hasattr(node, 'qualifier') and node.qualifier:
                        # If the qualifier starts with an uppercase letter, it could be a class (static call)
                        if isinstance(node.qualifier, str) and node.qualifier[0].isupper():
                            if node.qualifier not in excluded_types:
                                types_used.add(node.qualifier)
                                logger.debug(f"Type from static call: {node.qualifier}")
                                        
        except Exception as e:
            logger.debug(f"Error parsing AST for type extraction: {e}")
            # Fallback: use regex to find types
            type_pattern = r'\b([A-Z][a-zA-Z0-9_]*)\s+\w+\s*[=;,)]'
            matches = re.findall(type_pattern, java_code)
            for match in matches:
                if match not in excluded_types:
                    types_used.add(match)
        
        foreach_pattern = r'for\s*\(\s*(?:final\s+)?([A-Z][a-zA-Z0-9_]*)\s+\w+\s*:'
        foreach_matches = re.findall(foreach_pattern, java_code)
        for match in foreach_matches:
            if match not in excluded_types:
                types_used.add(match)
                logger.debug(f"Type from for-each (regex): {match}")
        
        static_call_pattern = r'\b([A-Z][a-zA-Z0-9_]*)\.\w+\s*\('
        static_matches = re.findall(static_call_pattern, java_code)
        for match in static_matches:
            if match not in excluded_types:
                types_used.add(match)
                logger.debug(f"Type from static call (regex): {match}")
        
        return types_used
    
    def _find_same_package_dependencies(self, target_code: str, target_class_name: str, 
                                         same_package_classes: Dict[str, str]) -> Dict[str, str]:
        """
        Identifica quali classi dello stesso package sono effettivamente usate nel codice target.
        Restituisce {nome_classe: codice_classe} per le dipendenze dello stesso package.
        """
        dependencies = {}
        
        if not same_package_classes:
            return dependencies
        
        # Estrae tutti i tipi usati nel codice
        types_used = self._extract_types_used_in_code(target_code)
        logger.debug(f"Types used in code: {types_used}")
        
        # Controlla quali tipi corrispondono a classi dello stesso package
        for class_name, file_path in same_package_classes.items():
            if class_name == target_class_name:
                continue
                
            if class_name in types_used:
                code = leggi_file(file_path)
                if code:
                    dependencies[class_name] = code
                    logger.info(f"Found same package dependency (no import): {class_name}")
        
        return dependencies
    
    def create_test_context(self, target_class_path: str, max_dependencies: int = 10) -> Tuple[str, Dict[str, str]]:
        """
        Crea un contesto completo per la generation di test, includendo
        la classe target e le sue dipendenze principali.
        """
        dependencies = self.analyze_class_dependencies(target_class_path)
        
        # Limita il numero di dipendenze per evitare prompt troppo lunghi
        if len(dependencies) > max_dependencies:
            logger.warning(f"Too many dependencies ({len(dependencies)}), limiting to {max_dependencies}")
            # Mantiene la classe target e le prime dipendenze
            limited_deps = dict(list(dependencies.items())[:max_dependencies])
        else:
            limited_deps = dependencies
        
        # Crea il contesto per il prompt
        context_parts = []
        for class_name, code in limited_deps.items():
            context_parts.append(f"// === Class: {class_name} ===\n{code}\n")
        
        context = "\n".join(context_parts)
        
        logger.info(f"Context created with {len(limited_deps)} classes")
        return context, limited_deps
    
    def extract_methods_used_from_original_method(
        self, 
        target_class_path: str, 
        nome_metodo: str,
        dependencies: Dict[str, str]
    ) -> Dict[str, str]:
        """
        Estrae solo i metodi effettivamente usati dal metodo originale dalle classi dipendenti.
        Invece di passare l'intera classe, passa solo i metodi chiamati.
        """
        logger.info(f"Extracting methods used from method {nome_metodo}")
        
        # Estrae il metodo originale
        target_code = leggi_file(target_class_path)
        if not target_code:
            logger.error(f"Unable to read {target_class_path}")
            return {}
        
        metodo_originale = self._estrai_metodo_da_classe(target_code, nome_metodo)
        if not metodo_originale:
            logger.warning(f"Method {nome_metodo} not found in {target_class_path}")
            return {}
        
        logger.debug(f"Original method extracted (first 200 characters): {metodo_originale[:200]}...")
        logger.info(f"Available dependencies: {list(dependencies.keys())}")
        
        # Estrae anche i campi della classe per identificare le dipendenze
        campi_classe = self._estrai_campi_classe(target_code)
        logger.debug(f"Class fields: {campi_classe}")
        
        # Analizza il metodo per trovare le chiamate a metodi (inclusi campi e parametri)
        metodi_chiamati_per_classe = self._analizza_chiamate_metodi(metodo_originale, dependencies, campi_classe)
        logger.info(f"Methods called per class: {metodi_chiamati_per_classe}")
        
        # Estrae solo i metodi usati da ogni classe, insieme a fields e costruttori
        # Restituisce un dizionario con struttura: {class_name: {'nome_classe': str, 'fields': List[str], 'costruttori': List[str], 'metodi': List[str]}}
        metodi_estratti = {}
        for class_name, metodi_chiamati in metodi_chiamati_per_classe.items():
            if class_name in dependencies:
                codice_classe = dependencies[class_name]
                metodi_codice = []
                
                for metodo_nome in metodi_chiamati:
                    metodo_codice = self._estrai_metodo_da_classe(codice_classe, metodo_nome)
                    if metodo_codice:
                        metodi_codice.append(metodo_codice)
                
                if metodi_codice:
                    # Estrae nome classe
                    nome_classe_estratto = self.extract_class_name(codice_classe) or class_name
                    
                    # Estrae fields e costruttori della classe esterna (parsing AST una sola volta)
                    campi_codice = []
                    costruttori_codice = []
                    try:
                        tree = javalang.parse.parse(codice_classe)
                        lines = codice_classe.split('\n')
                        for path, node in tree:
                            # Estrae fields
                            if isinstance(node, javalang.tree.FieldDeclaration):
                                if hasattr(node, 'position') and node.position:
                                    start_line = node.position.line - 1
                                    end_line = start_line
                                    # Trova la fine della dichiarazione (fino al ;)
                                    for i in range(start_line, len(lines)):
                                        if ';' in lines[i]:
                                            end_line = i
                                            break
                                    campo_codice = '\n'.join(lines[start_line:end_line + 1])
                                    campi_codice.append(campo_codice)
                            
                            # Estrae costruttori (completi con implementazione)
                            elif isinstance(node, javalang.tree.ConstructorDeclaration):
                                if hasattr(node, 'position') and node.position:
                                    start_line = node.position.line - 1
                                    start_char = sum(len(lines[i]) + 1 for i in range(start_line))
                                    brace_count = 0
                                    in_constructor = False
                                    end_char = len(codice_classe)
                                    for i in range(start_char, len(codice_classe)):
                                        if codice_classe[i] == '{':
                                            brace_count += 1
                                            in_constructor = True
                                        elif codice_classe[i] == '}':
                                            brace_count -= 1
                                            if in_constructor and brace_count == 0:
                                                end_char = i + 1
                                                break
                                    costruttore_completo = codice_classe[start_char:end_char]
                                    costruttori_codice.append(costruttore_completo)
                    except Exception as e:
                        logger.warning(f"Error extracting fields/constructors class {class_name}: {e}")
                        # Fallback per costruttori: usa solo le signature
                        costruttori_signature = self._estrai_costruttori(codice_classe)
                        for costruttore_signature in costruttori_signature:
                            # Rimuove il ; finale e aggiunge corpo vuoto
                            signature_clean = costruttore_signature.rstrip(';').strip()
                            costruttori_codice.append(f"{signature_clean} {{}}")
                    
                    # Salva in struttura separata per formattazione migliore nel prompt
                    metodi_estratti[class_name] = {
                        'nome_classe': nome_classe_estratto,
                        'fields': campi_codice,
                        'costruttori': costruttori_codice,
                        'metodi': metodi_codice
                    }
                    logger.info(f"Extracted from {class_name}: {len(campi_codice)} fields, {len(costruttori_codice)} constructors, {len(metodi_codice)} methods ({', '.join(metodi_chiamati)})")
        
        return metodi_estratti
    
    def _estrai_metodo_da_classe(self, codice_classe: str, nome_metodo: str) -> str:
        """Extracts a specific method from a class."""
        try:
            tree = javalang.parse.parse(codice_classe)
            lines = codice_classe.split('\n')
            for path, node in tree:
                if isinstance(node, javalang.tree.MethodDeclaration) and node.name == nome_metodo:
                    if hasattr(node, 'position') and node.position:
                        start_line = node.position.line - 1
                    else:
                        continue
                    start_char = sum(len(lines[i]) + 1 for i in range(start_line))
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
                    return codice_classe[start_char:end_char]
        except Exception as e:
            logger.warning(f"Error extracting method {nome_metodo}: {e}")
        return ""
    
    def _estrai_signature_metodo(self, codice_classe: str, nome_metodo: str) -> str:
        """Extracts only the signature (without body) of a specific method."""
        try:
            tree = javalang.parse.parse(codice_classe)
            for path, node in tree:
                if isinstance(node, javalang.tree.MethodDeclaration) and node.name == nome_metodo:
                    # Costruisce la signature dai componenti del nodo
                    modifiers = " ".join(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else ""
                    return_type = node.return_type.name if hasattr(node, 'return_type') and node.return_type else "void"
                    params_list = []
                    if hasattr(node, 'parameters'):
                        for p in node.parameters:
                            if hasattr(p, 'type') and hasattr(p, 'name'):
                                # Gestisci modifiers (es. final)
                                p_modifiers = ""
                                if hasattr(p, 'modifiers') and 'final' in p.modifiers:
                                     p_modifiers = "final "
                                
                                # Gestisci tipo (base logic, generics complessi omessi per ora ma array gestiti)
                                p_type = p.type.name
                                if hasattr(p.type, 'dimensions') and p.type.dimensions:
                                    p_type += '[]' * len(p.type.dimensions)
                                if hasattr(p, 'varargs') and p.varargs:
                                    p_type += '...'

                                params_list.append(f"{p_modifiers}{p_type} {p.name}")
                            else:
                                params_list.append(str(p))
                    params = ", ".join(params_list)
                    signature = f"{modifiers} {return_type} {nome_metodo}({params})".strip()
                    return signature + ";"
        except Exception as e:
            logger.warning(f"Error extracting method signature {nome_metodo}: {e}")
            # Fallback: cerca la signature nel codice
            try:
                pattern = rf'(public|private|protected)?\s*(static)?\s*(\w+)\s+({re.escape(nome_metodo)})\s*\([^)]*\)'
                match = re.search(pattern, codice_classe, re.MULTILINE)
                if match:
                    return match.group(0).strip() + ";"
            except:
                pass
        return ""
    
    def _estrai_getter_pubblici(self, codice_classe: str) -> List[str]:
        """
        Estrae le signature di tutti i metodi getter pubblici di una classe.
        Include: get*, is*, has*, size(), isEmpty(), length(), iterator(), toArray(), etc.
        
        Utile per fornire all'LLM i metodi disponibili per le asserzioni nei test.
        """
        getter_signatures = []
        getter_patterns = ['get', 'is', 'has', 'can', 'should', 'will', 'was', 'to', 'as']
        utility_methods = ['size', 'isEmpty', 'length', 'iterator', 'toArray', 'stream', 
                          'values', 'keys', 'keySet', 'entrySet', 'contains', 'containsKey', 
                          'containsValue', 'indexOf', 'lastIndexOf', 'first', 'last']
        
        try:
            tree = javalang.parse.parse(codice_classe)
            for path, node in tree:
                if isinstance(node, javalang.tree.MethodDeclaration):
                    # Verifica che sia pubblico
                    modifiers = set(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else set()
                    if 'public' not in modifiers:
                        continue
                    
                    method_name = node.name
                    is_getter = False
                    
                    # Check if it's a getter pattern (getX, isX, etc.)
                    for pattern in getter_patterns:
                        if method_name.startswith(pattern) and len(method_name) > len(pattern):
                            # Il carattere dopo il pattern deve essere maiuscolo (getOptions, non getoptions)
                            if method_name[len(pattern)].isupper():
                                is_getter = True
                                break
                    
                    # Check if it's a utility method
                    if method_name in utility_methods:
                        is_getter = True
                    
                    if is_getter:
                        # Costruisci la signature
                        return_type = "void"
                        if hasattr(node, 'return_type') and node.return_type:
                            return_type = node.return_type.name
                            # Handle generics
                            if hasattr(node.return_type, 'arguments') and node.return_type.arguments:
                                args = []
                                for arg in node.return_type.arguments:
                                    if hasattr(arg, 'type') and hasattr(arg.type, 'name'):
                                        args.append(arg.type.name)
                                if args:
                                    return_type += f"<{', '.join(args)}>"
                        
                        # Parametri
                        params_list = []
                        if hasattr(node, 'parameters') and node.parameters:
                            for p in node.parameters:
                                if hasattr(p, 'type') and hasattr(p, 'name'):
                                    p_type = p.type.name
                                    if hasattr(p.type, 'dimensions') and p.type.dimensions:
                                        p_type += '[]' * len(p.type.dimensions)
                                    params_list.append(f"{p_type} {p.name}")
                        params = ", ".join(params_list)
                        
                        # Static modifier
                        static_mod = "static " if 'static' in modifiers else ""
                        
                        signature = f"public {static_mod}{return_type} {method_name}({params});"
                        getter_signatures.append(signature)
                        
        except Exception as e:
            logger.warning(f"Error extracting public getters: {e}")
        
        return getter_signatures
    
    def _estrai_campi_classe(self, codice_classe: str) -> Dict[str, str]:
        """Extracts the fields of the class {field_name: type}."""
        campi = {}
        try:
            tree = javalang.parse.parse(codice_classe)
            for path, node in tree:
                if isinstance(node, javalang.tree.FieldDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name') and hasattr(node, 'declarators'):
                        tipo = node.type.name.split('<')[0].split('[')[0]
                        for declarator in node.declarators:
                            if hasattr(declarator, 'name'):
                                campi[declarator.name] = tipo
        except Exception as e:
            logger.warning(f"Error extracting class fields: {e}")
        return campi
    
    def _estrai_tipo_classe(self, codice_classe: str) -> str:
        """
        Detects the class type: 'interface', 'abstract', or 'class'.
        Utile per avvisare l'LLM di non istanziare interfacce/classi astratte.
        """
        try:
            tree = javalang.parse.parse(codice_classe)
            for path, node in tree:
                if isinstance(node, javalang.tree.InterfaceDeclaration):
                    return "interface"
                elif isinstance(node, javalang.tree.ClassDeclaration):
                    modifiers = set(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else set()
                    if 'abstract' in modifiers:
                        return "abstract"
                    return "class"
                elif isinstance(node, javalang.tree.EnumDeclaration):
                    return "enum"
        except Exception as e:
            logger.warning(f"Error detecting class type: {e}")
            # Fallback con regex
            if re.search(r'\binterface\s+\w+', codice_classe):
                return "interface"
            if re.search(r'\babstract\s+class\s+\w+', codice_classe):
                return "abstract"
            if re.search(r'\benum\s+\w+', codice_classe):
                return "enum"
        return "class"
    
    def _estrai_campi_classe_con_visibilita(self, codice_classe: str) -> Dict[str, Dict[str, str]]:
        """
        Estrae i campi della classe con informazioni complete.
        Returns: {nome_campo: {'tipo': str, 'visibilita': str, 'static': bool, 'final': bool}}
        """
        campi = {}
        try:
            tree = javalang.parse.parse(codice_classe)
            for path, node in tree:
                if isinstance(node, javalang.tree.FieldDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name') and hasattr(node, 'declarators'):
                        tipo = node.type.name
                        # Handle array types
                        if hasattr(node.type, 'dimensions') and node.type.dimensions:
                            tipo += '[]' * len(node.type.dimensions)
                        
                        # Extract modifiers
                        modifiers = set(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else set()
                        
                        # Determine visibility
                        if 'public' in modifiers:
                            visibilita = 'public'
                        elif 'protected' in modifiers:
                            visibilita = 'protected'
                        elif 'private' in modifiers:
                            visibilita = 'private'
                        else:
                            visibilita = 'package-private'  # Default in Java
                        
                        is_static = 'static' in modifiers
                        is_final = 'final' in modifiers
                        
                        for declarator in node.declarators:
                            if hasattr(declarator, 'name'):
                                campi[declarator.name] = {
                                    'tipo': tipo,
                                    'visibilita': visibilita,
                                    'static': is_static,
                                    'final': is_final
                                }
        except Exception as e:
            logger.warning(f"Error extracting class fields with visibility: {e}")
        return campi
    
    def _estrai_import_mapping(self, codice_classe: str) -> Dict[str, str]:
        """
        Extracts a mapping {simple_class_name: fully_qualified_name} for all imports.
        Utile per fornire all'LLM i percorsi corretti delle classi.
        
        Risolve anche i wildcard imports (es. import com.example.*) cercando
        tutti i file Java nella directory corrispondente del progetto.
        """
        import_mapping = {}
        wildcard_packages = []  # Lista di package con wildcard da risolvere
        
        try:
            lines = codice_classe.split('\n')
            for line in lines:
                line = line.strip()
                if line.startswith('import ') and not line.startswith('import static'):
                    # Remove 'import ' and ';'
                    fqn = line[7:].rstrip(';').strip()
                    if '.' in fqn:
                        simple_name = fqn.split('.')[-1]
                        if simple_name == '*':
                            # Wildcard import - salva il package per risoluzione successiva
                            package_path = fqn[:-2]  # Rimuove ".*"
                            wildcard_packages.append(package_path)
                        else:
                            import_mapping[simple_name] = fqn
                elif line.startswith('public class') or line.startswith('class') or \
                     line.startswith('public interface') or line.startswith('public abstract'):
                    # Stop when class definition starts
                    break
            
            # Risolvi wildcard imports cercando nel progetto
            if wildcard_packages and self.project_root:
                for package in wildcard_packages:
                    resolved_classes = self._resolve_wildcard_import(package)
                    for simple_name, fqn in resolved_classes.items():
                        # Non sovrascrivere import espliciti
                        if simple_name not in import_mapping:
                            import_mapping[simple_name] = fqn
                            
        except Exception as e:
            logger.warning(f"Error extracting import mapping: {e}")
        return import_mapping
    
    def _resolve_wildcard_import(self, package: str) -> Dict[str, str]:
        """
        Risolve un wildcard import cercando tutte le classi Java nel package specificato.
        """
        resolved = {}
        try:
            # Converti package in path relativo (com.example -> com/example)
            package_path = package.replace('.', os.sep)
            
            # Cerca la directory del package in vari possibili percorsi src
            possible_src_dirs = ['src/main/java', 'src', 'java', '']
            
            for src_dir in possible_src_dirs:
                if src_dir:
                    full_package_dir = os.path.join(self.project_root, src_dir, package_path)
                else:
                    full_package_dir = os.path.join(self.project_root, package_path)
                
                if os.path.isdir(full_package_dir):
                    # Trovato! Lista tutti i file .java nella directory
                    for filename in os.listdir(full_package_dir):
                        if filename.endswith('.java'):
                            # Estrai nome classe (rimuovi .java)
                            class_name = filename[:-5]
                            # Costruisci FQN
                            fqn = f"{package}.{class_name}"
                            resolved[class_name] = fqn
                    
                    if resolved:
                        logger.debug(f"Resolved wildcard import {package}.* -> {len(resolved)} classes")
                    break  # Found, exit loop
                    
        except Exception as e:
            logger.warning(f"Error resolving wildcard import {package}.*: {e}")
        
        return resolved

    def _estrai_parametri_metodo(self, codice_metodo: str) -> Dict[str, str]:
        """Estrae i parametri del metodo {nome_parametro: tipo}."""
        parametri = {}
        try:
            codice_wrapped = f"class Temp {{\n{codice_metodo}\n}}"
            tree = javalang.parse.parse(codice_wrapped)
            for path, node in tree:
                if isinstance(node, javalang.tree.MethodDeclaration):
                    if hasattr(node, 'parameters'):
                        for param in node.parameters:
                            if hasattr(param, 'type') and hasattr(param.type, 'name') and hasattr(param, 'name'):
                                tipo = param.type.name.split('<')[0].split('[')[0]
                                parametri[param.name] = tipo
                    break
        except Exception as e:
            logger.warning(f"Error extracting method parameters: {e}")
        return parametri
    
    def _analizza_chiamate_metodi(self, codice_metodo: str, dependencies: Dict[str, str], campi_classe: Dict[str, str] = None, nome_classe_target: str = None) -> Dict[str, Set[str]]:
        """
        Analizza il codice del metodo per trovare le chiamate a metodi su oggetti di classi dipendenti.
        Restituisce {nome_classe: {nomi_metodi_chiamati}}
        Include anche metodi interni chiamati direttamente (metodo() o this.metodo()).
        """
        metodi_per_classe = {}
        
        if not codice_metodo:
            return metodi_per_classe
        
        if campi_classe is None:
            campi_classe = {}
        
        # Estrae parametri del metodo
        parametri_metodo = self._estrai_parametri_metodo(codice_metodo)
        
        # Crea mappa case-insensitive dei nomi delle classi nelle dipendenze
        class_names_lower = {name.lower(): name for name in dependencies.keys()}
        
        # Identifica metodi interni chiamati direttamente (metodo() o this.metodo())
        if nome_classe_target:
            # Pattern per chiamate dirette: metodo() o this.metodo()
            # Esclude parole chiave Java e metodi comuni
            java_keywords = {'if', 'for', 'while', 'switch', 'catch', 'try', 'new', 'return', 'throw', 'assert', 'synchronized'}
            common_methods = {'toString', 'equals', 'hashCode', 'getClass', 'notify', 'notifyAll', 'wait', 'print', 'println'}
            
            # Pattern per identificare chiamate dirette a metodi (non su oggetti)
            # Cerca: metodo( oppure this.metodo(
            # Usa negative lookbehind per escludere chiamate con punto prima (es. obj.metodo)
            pattern_direct = r'(?<!\.)\b(?:this\.)?(\w+)\s*\('
            matches_direct = re.finditer(pattern_direct, codice_metodo)
            
            for match in matches_direct:
                method_name = match.group(1)
                
                # Salta parole chiave Java e metodi comuni
                if method_name in java_keywords or method_name in common_methods:
                    continue
                
                start_pos = match.start()
                
                # Verifica che non sia una dichiarazione di variabile o altro
                # Cerca pattern tipo: Tipo metodo( che indicherebbe una dichiarazione
                before_match = codice_metodo[max(0, start_pos - 50):start_pos]
                if re.search(r'\b(public|private|protected|static|final|abstract|synchronized|native|strictfp)\s+(\w+\s+)*' + re.escape(method_name) + r'\s*\(', before_match):
                    continue  # Probabilmente una dichiarazione di metodo
                
                # Aggiunge come metodo interno della classe target
                metodi_per_classe.setdefault(nome_classe_target, set()).add(method_name)
                logger.debug(f"Found direct internal method call: {method_name}() -> {nome_classe_target}")
        
        # Pattern regex per trovare chiamate a metodi: variabile.metodo() o this.variabile.metodo()
        pattern = r'(?:this\.)?(\w+)\.(\w+)\s*\('
        matches = re.finditer(pattern, codice_metodo)

        # Pattern per method references: this::method o ClassName::method o var::method
        method_ref_pattern = r'(?:this|(\w+))::(\w+)'
        method_ref_matches = re.finditer(method_ref_pattern, codice_metodo)
        
        for match in method_ref_matches:
            qualifier = match.group(1) # None se 'this', altrimenti nome classe/var
            method_name = match.group(2)
            
            if not qualifier: # Caso this::method
                metodi_per_classe.setdefault(nome_classe_target, set()).add(method_name)
                logger.debug(f"Found internal method reference: this::{method_name} -> {nome_classe_target}")
            else:
                # Caso ClassName::method o var::method
                # 1. Controlla se è una classe (metodo statico o costruttore array)
                if qualifier.lower() in class_names_lower:
                     class_name = class_names_lower[qualifier.lower()]
                     metodi_per_classe.setdefault(class_name, set()).add(method_name)
                     logger.debug(f"Found static method reference: {qualifier}::{method_name} -> {class_name}")
                # 2. Controlla se è una variabile (campo, parametro, locale)
                elif qualifier in campi_classe:
                    tipo = campi_classe[qualifier]
                    if tipo.lower() in class_names_lower:
                        class_name = class_names_lower[tipo.lower()]
                        metodi_per_classe.setdefault(class_name, set()).add(method_name)
                        logger.debug(f"Found method reference on field: {qualifier}::{method_name} -> {class_name}")
                elif qualifier in parametri_metodo:
                     tipo = parametri_metodo[qualifier]
                     if tipo.lower() in class_names_lower:
                        class_name = class_names_lower[tipo.lower()]
                        metodi_per_classe.setdefault(class_name, set()).add(method_name)
                        logger.debug(f"Found method reference on parameter: {qualifier}::{method_name} -> {class_name}")

        
        foreach_vars = {}
        foreach_pattern = r'for\s*\(\s*(?:final\s+)?([A-Z][a-zA-Z0-9_]*)\s+(\w+)\s*:'
        foreach_matches = re.finditer(foreach_pattern, codice_metodo)
        for fm in foreach_matches:
            var_type = fm.group(1)
            var_name = fm.group(2)
            foreach_vars[var_name] = var_type
            logger.debug(f"Found for-each variable: {var_name} of type {var_type}")
        
        for match in matches:
            var_name = match.group(1)
            method_name = match.group(2)
            
            # Salta metodi comuni che non sono chiamate a dipendenze
            if method_name in ('toString', 'equals', 'hashCode', 'getClass', 'notify', 'notifyAll', 'wait'):
                continue
            
            class_name = None
            
            # Controlla se è una chiamata statica (var_name inizia con maiuscola e corrisponde a una classe)
            if var_name[0].isupper() and var_name.lower() in class_names_lower:
                class_name = class_names_lower[var_name.lower()]
                logger.debug(f"Found static call: {var_name}.{method_name}() -> {class_name}")
            
            # Controlla se è un campo della classe
            elif var_name in campi_classe:
                tipo_campo = campi_classe[var_name]
                if tipo_campo.lower() in class_names_lower:
                    class_name = class_names_lower[tipo_campo.lower()]
                    logger.debug(f"Found call (class field): {var_name}.{method_name}() -> {class_name}")
            
            #  Controlla se è un parametro del metodo
            elif var_name in parametri_metodo:
                tipo_param = parametri_metodo[var_name]
                if tipo_param.lower() in class_names_lower:
                    class_name = class_names_lower[tipo_param.lower()]
                    logger.debug(f"Found call (method parameter): {var_name}.{method_name}() -> {class_name}")
            
            # Controlla se è una variabile for-each
            elif var_name in foreach_vars:
                tipo_foreach = foreach_vars[var_name]
                if tipo_foreach.lower() in class_names_lower:
                    class_name = class_names_lower[tipo_foreach.lower()]
                    logger.debug(f"Found call (for-each variable): {var_name}.{method_name}() -> {class_name}")
            
            # 3. Cerca dichiarazione variabile locale nel metodo
            else:
                var_pattern = rf'\b(\w+)\s+{re.escape(var_name)}\s*[=;]'
                var_match = re.search(var_pattern, codice_metodo)
                if var_match:
                    potential_type = var_match.group(1)
                    if potential_type.lower() in class_names_lower:
                        class_name = class_names_lower[potential_type.lower()]
                        logger.debug(f"Found call (local variable): {var_name}.{method_name}() -> {class_name}")
                else:
                    # 4. Se il nome della variabile corrisponde (case-insensitive) a una classe nelle dipendenze
                    if var_name.lower() in class_names_lower:
                        class_name = class_names_lower[var_name.lower()]
                        logger.debug(f"Found call (by variable name): {var_name}.{method_name}() -> {class_name}")
            
            if class_name:
                metodi_per_classe.setdefault(class_name, set()).add(method_name)
        
        # Analisi AST per maggiore precisione
        codice_wrapped = f"class Temp {{\n{codice_metodo}\n}}"
        
        try:
            tree = javalang.parse.parse(codice_wrapped)
            variabili_tipi = {}
            
            for path, node in tree:
                # Estrae variabili locali
                if isinstance(node, javalang.tree.LocalVariableDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name') and hasattr(node, 'declarators'):
                        tipo = node.type.name.split('<')[0].split('[')[0]
                        for declarator in node.declarators:
                            if hasattr(declarator, 'name'):
                                variabili_tipi[declarator.name] = tipo
                
                # Estrae variabili da for-each loop
                if isinstance(node, javalang.tree.EnhancedForControl):
                    if hasattr(node, 'var') and hasattr(node.var, 'type') and hasattr(node.var.type, 'name'):
                        tipo = node.var.type.name.split('<')[0].split('[')[0]
                        if hasattr(node.var, 'name'):
                            variabili_tipi[node.var.name] = tipo
                            logger.debug(f"AST: for-each variable {node.var.name} of type {tipo}")
                
                if isinstance(node, javalang.tree.MethodInvocation):
                    if hasattr(node, 'qualifier') and node.qualifier:
                        if isinstance(node.qualifier, str):
                            qualifier = node.qualifier
                            # Cerca nelle variabili locali (incluse for-each)
                            if qualifier in variabili_tipi:
                                class_name = variabili_tipi[qualifier]
                                if class_name.lower() in class_names_lower:
                                    class_name = class_names_lower[class_name.lower()]
                                    if hasattr(node, 'member') and node.member:
                                        metodi_per_classe.setdefault(class_name, set()).add(node.member)
                                        logger.debug(f"AST: {qualifier}.{node.member}() -> {class_name}")
                            # Controlla se è una chiamata statica (qualifier inizia con maiuscola)
                            elif qualifier[0].isupper() and qualifier.lower() in class_names_lower:
                                class_name = class_names_lower[qualifier.lower()]
                                if hasattr(node, 'member') and node.member:
                                    metodi_per_classe.setdefault(class_name, set()).add(node.member)
                                    logger.debug(f"AST static call: {qualifier}.{node.member}() -> {class_name}")
                            # Cerca direttamente nelle dipendenze (case-insensitive)
                            elif qualifier.lower() in class_names_lower:
                                class_name = class_names_lower[qualifier.lower()]
                                if hasattr(node, 'member') and node.member:
                                    metodi_per_classe.setdefault(class_name, set()).add(node.member)
                                    logger.debug(f"AST: {qualifier}.{node.member}() -> {class_name}")
        
        except Exception as e:
            logger.warning(f"Error analyzing AST method calls: {e}")
        
        # Extracts types from CAST to ensure their context (constructors/fields) is included
        # Pattern: (Type)
        cast_pattern = r'\(\s*([A-Z]\w+)\s*\)'
        cast_matches = re.finditer(cast_pattern, codice_metodo)
        for match in cast_matches:
            type_name = match.group(1)
            # Verifica se è una dipendenza nota (esatto case)
            if type_name in class_names_lower.values(): 
                if type_name not in metodi_per_classe:
                    metodi_per_classe[type_name] = set()
                    logger.debug(f"Found cast to {type_name}: forcing context inclusion")
            elif type_name.lower() in class_names_lower: # Case-insensitive check
                 real_name = class_names_lower[type_name.lower()]
                 if real_name not in metodi_per_classe:
                    metodi_per_classe[real_name] = set()
                    logger.debug(f"Found cast to {real_name} (from {type_name}): forcing context inclusion")

        # Estrae tipi da GENERICS per garantire che il loro contesto sia incluso (es. List<Type>)
        # Pattern: <Type> o <Type, Type2>
        generics_pattern = r'<([^>]+)>'
        generics_matches = re.finditer(generics_pattern, codice_metodo)
        for match in generics_matches:
            content = match.group(1)
            # Gestisce tipi multipli (Map<K,V>)
            types = [t.strip().split('[')[0] for t in content.split(',')] # Rimuove anche array dimensions
            for type_name in types:
                type_name = type_name.strip()
                if not type_name or type_name == '?': continue 
                
                # Gestisce Wildcards (List<? extends Type>)
                if 'extends' in type_name:
                    type_name = type_name.split('extends')[1].strip()
                elif 'super' in type_name:
                    type_name = type_name.split('super')[1].strip()
                
                type_name = type_name.split('<')[0].strip() # Gestisce nested generics (Type<Sub>)
                
                if type_name in class_names_lower.values():
                    if type_name not in metodi_per_classe:
                        metodi_per_classe[type_name] = set()
                        logger.debug(f"Found generic type {type_name}: forcing context inclusion")
                elif type_name.lower() in class_names_lower:
                    real_name = class_names_lower[type_name.lower()]
                    if real_name not in metodi_per_classe:
                        metodi_per_classe[real_name] = set()
                        logger.debug(f"Found generic type {real_name} (from {type_name}): forcing context inclusion")

        # Estrae tipi da STATIC CONSTANTS (Class.CONSTANT)
        # Pattern: Class.PROP o Class.CONST_NAME (solo maiuscole per costanti o PascalCase per proprietà statiche)
        static_const_pattern = r'\b([A-Z]\w+)\.([A-Z][A-Z0-9_]*|[A-Z]\w+)\b'
        static_matches = re.finditer(static_const_pattern, codice_metodo)
        for match in static_matches:
            class_name = match.group(1)
            member_name = match.group(2)
            
            # Esclude chiamate metodo già gestite (es. Class.method()) - qui cerchiamo campi
            # Se è seguito da ( è un metodo, l'abbiamo già gestito
            # Cerca nel codice originale se dopo il match c'è una (
            start, end = match.span()
            if end < len(codice_metodo) and codice_metodo[end:].lstrip().startswith('('):
                continue
                
            if class_name in class_names_lower.values():
                if class_name not in metodi_per_classe:
                    metodi_per_classe[class_name] = set()
                    logger.debug(f"Found static field {class_name}.{member_name}: forcing context inclusion")
            elif class_name.lower() in class_names_lower:
                real_name = class_names_lower[class_name.lower()]
                if real_name not in metodi_per_classe:
                    metodi_per_classe[real_name] = set()
                    logger.debug(f"Found static field {real_name}.{member_name} (from {class_name}): forcing context inclusion")

        return metodi_per_classe
    
    def estrai_eccezioni_metodo(self, codice_classe: str, nome_metodo: str, metodo_signature: str = None) -> List[str]:
        """
        Estrae le eccezioni dichiarate nella clausola throws di un metodo.
        """
        eccezioni = []
        try:
            tree = javalang.parse.parse(codice_classe)
            
            # Funzione helper per matching signature
            def _match_signature(node, signature):
                if not signature:
                    return True
                    
                # Estrae i tipi dei parametri dalla signature cercata
                import re
                match = re.search(r'\(([^)]*)\)', signature)
                if not match:
                    return True
                    
                params_str = match.group(1).strip()
                if not params_str:
                    expected_types = []
                else:
                    # Estrae il tipo dai parametri, gestendo 'final' keyword
                    expected_types = []
                    for p in params_str.split(','):
                        parts = p.strip().split()
                        # Rimuove 'final' se presente
                        type_parts = [part for part in parts if part != 'final']
                        if type_parts:
                            expected_types.append(type_parts[0])  # Il primo rimanente è il tipo
                
                # Confronta con i parametri del nodo
                if not hasattr(node, 'parameters') or not node.parameters:
                    return len(expected_types) == 0
                    
                if len(node.parameters) != len(expected_types):
                    return False
                    
                for i, param in enumerate(node.parameters):
                    if hasattr(param, 'type') and hasattr(param.type, 'name'):
                        param_type = param.type.name
                        # Gestisce array
                        if hasattr(param.type, 'dimensions') and param.type.dimensions:
                            param_type += '[]' * len(param.type.dimensions)
                        
                        # Confronto semplificato (solo nome base del tipo)
                        if param_type.split('<')[0].split('[')[0] != expected_types[i].split('<')[0].split('[')[0]:
                            return False
                            
                return True
            
            for path, node in tree:
                if isinstance(node, javalang.tree.MethodDeclaration) and node.name == nome_metodo:
                    # Verifica signature se fornita
                    if metodo_signature and not _match_signature(node, metodo_signature):
                        continue
                    
                    # Estrae le eccezioni dalla clausola throws
                    if hasattr(node, 'throws') and node.throws:
                        for throw_type in node.throws:
                            if hasattr(throw_type, 'name'):
                                eccezioni.append(throw_type.name)
                            elif isinstance(throw_type, str):
                                eccezioni.append(throw_type)
                    
                    # Se troviamo il metodo giusto, interrompi
                    if not metodo_signature or _match_signature(node, metodo_signature):
                        break
                        
        except Exception as e:
            logger.warning(f"Error extracting method exceptions {nome_metodo}: {e}")
            # Fallback con regex
            try:
                import re
                pattern = rf'{re.escape(nome_metodo)}\s*\([^)]*\)\s*throws\s+([\w\s,]+)\s*\{{'
                match = re.search(pattern, codice_classe)
                if match:
                    throws_str = match.group(1)
                    eccezioni = [e.strip() for e in throws_str.split(',') if e.strip()]
            except:
                pass
        
        return eccezioni
    
    def estrai_contesto_eccezione(self, exception_name: str, java_files: List[str] = None) -> Optional[Dict]:
        """
        Trova la classe dell'eccezione nel progetto e ne estrae i costruttori.
        """
        if java_files is None:
            java_files = self.find_java_files()
        
        # Eccezioni standard Java da ignorare
        standard_exceptions = {
            'Exception', 'RuntimeException', 'Throwable', 'Error',
            'IOException', 'FileNotFoundException', 'NullPointerException',
            'IllegalArgumentException', 'IllegalStateException', 
            'UnsupportedOperationException', 'IndexOutOfBoundsException',
            'ClassNotFoundException', 'NoSuchMethodException', 'SecurityException',
            'InterruptedException', 'CloneNotSupportedException'
        }
        
        if exception_name in standard_exceptions:
            return None
        
        # Cerca il file della classe eccezione
        exception_file = None
        for file_path in java_files:
            if file_path.endswith(f'{exception_name}.java'):
                exception_file = file_path
                break
        
        if not exception_file:
            logger.debug(f"Exception file {exception_name} not found in project")
            return None
        
        # Leggi e analizza il file
        codice_eccezione = leggi_file(exception_file)
        if not codice_eccezione:
            return None
        
        # Estrae package
        package = self._extract_package(codice_eccezione)
        
        # Estrae costruttori
        costruttori = self._estrai_costruttori(codice_eccezione)
        
        return {
            'nome_classe': exception_name,
            'costruttori': costruttori,
            'package': package,
            'file_path': exception_file
        }

    
    def _estrai_imports_completi(self, codice_classe: str) -> str:
        """Estrae tutti gli import dal codice."""
        imports = []
        lines = codice_classe.split('\n')
        for line in lines:
            line_stripped = line.strip()
            if line_stripped.startswith('import '):
                imports.append(line)
            elif line_stripped.startswith('package '):
                continue
            elif line_stripped.startswith('public class ') or line_stripped.startswith('class '):
                break
        return '\n'.join(imports) if imports else ""
    
    def _estrai_costruttori(self, codice_classe: str) -> List[str]:
        """Estrae tutti i costruttori della classe (solo signature)."""
        costruttori = []
        try:
            tree = javalang.parse.parse(codice_classe)
            lines = codice_classe.split('\n')
            for path, node in tree:
                if isinstance(node, javalang.tree.ConstructorDeclaration):
                    # Costruisce la signature del costruttore
                    modifiers = " ".join(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else ""
                    params = ", ".join([
                        f"{p.type.name} {p.name}" if hasattr(p, 'type') and hasattr(p, 'name')
                        else str(p)
                        for p in (node.parameters if hasattr(node, 'parameters') else [])
                    ])
                    class_name = node.name if hasattr(node, 'name') else ""
                    signature = f"{modifiers} {class_name}({params})".strip()
                    costruttori.append(signature + ";")
        except Exception as e:
            logger.warning(f"Error extracting constructors: {e}")
            # Fallback: cerca costruttori con regex
            try:
                pattern = r'(public|private|protected)?\s*(\w+)\s*\([^)]*\)\s*\{'
                matches = re.finditer(pattern, codice_classe)
                class_name = self.extract_class_name(codice_classe)
                if class_name:
                    for match in matches:
                        if match.group(2) == class_name:  # Il costruttore ha lo stesso nome della classe
                            signature = match.group(0).split('{')[0].strip() + ";"
                            if signature not in costruttori:
                                costruttori.append(signature)
            except:
                pass
        return costruttori

    def _estrai_enum_values(self, codice_classe: str) -> Dict[str, List[str]]:
        """
        Estrae tutti gli enum definiti nella classe e i loro valori.
        """
        enum_values = {}
        try:
            tree = javalang.parse.parse(codice_classe)
            for path, node in tree:
                if isinstance(node, javalang.tree.EnumDeclaration):
                    enum_name = node.name
                    values = []
                    if hasattr(node, 'body') and node.body and hasattr(node.body, 'constants'):
                        for constant in node.body.constants:
                            if hasattr(constant, 'name'):
                                values.append(constant.name)
                    if values:
                        enum_values[enum_name] = values
        except Exception as e:
            logger.warning(f"Error extracting enum (javalang): {e}")
            # Fallback: cerca enum con regex
            try:
                # Pattern per enum: public enum Type { VALUE1, VALUE2, ... }
                # o enum interno: enum Type { VALUE1(true), VALUE2(false) }
                pattern = r'(?:public\s+)?enum\s+(\w+)\s*\{([^}]+)\}'
                for match in re.finditer(pattern, codice_classe, re.DOTALL):
                    enum_name = match.group(1)
                    body = match.group(2)
                    # Estrai i valori (prima del primo metodo o costruttore)
                    # I valori possono essere: VALUE, VALUE(args), VALUE { body }
                    values = []
                    # Split su ';' per separare valori da metodi
                    parts = body.split(';')
                    if parts:
                        values_section = parts[0]
                        # Estrai i nomi dei valori
                        value_pattern = r'([A-Z][A-Z0-9_]*)\s*(?:\([^)]*\))?'
                        for val_match in re.finditer(value_pattern, values_section):
                            val_name = val_match.group(1)
                            if val_name not in values:
                                values.append(val_name)
                    if values:
                        enum_values[enum_name] = values
            except Exception as e2:
                logger.warning(f"Error extracting enum (regex fallback): {e2}")
        return enum_values
    