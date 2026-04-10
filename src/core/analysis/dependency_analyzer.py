
import os
import re
from typing import Dict, List, Set, Tuple, Optional
import javalang
from utils.tracking.logger import setup_logger
from utils.io.file_utils import leggi_file



logger = setup_logger()

class JavaDependencyAnalyzer:
    """
    Analyzer to extract Java dependencies from a target class and automatically resolve
    related classes in a project.
    """
    
    def __init__(self, project_root: str):
        self.project_root = project_root
        self.java_files_cache = {}
        self.dependency_graph = {}
        self._project_base_package_cache = None
        
    def find_java_files(self, directory: str = None) -> List[str]:
        """Find all Java files in a project."""
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
        """Extracts imports from Java code."""
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
        """Extracts the class name from Java code."""
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
        """Resolves an import statement to the corresponding Java file."""
        # Convert import com.example.Class to path com/example/Class.java
        import_path = import_statement.replace('.', '/') + '.java'
        
        # Searches for an exact match
        for file_path in project_files:
            if file_path.endswith(import_path):
                return file_path
        
        # Searches for the class name at the end
        class_name = import_statement.split('.')[-1]
        for file_path in project_files:
            if file_path.endswith(f'/{class_name}.java'):
                return file_path
        
        # Searches with a more flexible pattern (to handle different packages)
        for file_path in project_files:
            if f'/{class_name}.java' in file_path:
                return file_path
                
        # Searches for the class name without path
        for file_path in project_files:
            filename = os.path.basename(file_path)
            if filename == f'{class_name}.java':
                return file_path
                
        return None
    
    def analyze_class_dependencies(self, target_class_path: str) -> Dict[str, str]:
        """
        Analyzes the dependencies of a target class and returns a dictionary
        {class_name: class_code} for all dependent classes.
        Includes classes from the same package used without explicit import.
        """
        logger.info(f"Analyzing dependencies for: {target_class_path}")
        
        # Loads the target class code
        target_code = leggi_file(target_class_path)
        if not target_code:
            logger.error(f"Unable to read {target_class_path}")
            return {}
            
        # Extracts the target class
        target_class_name = self.extract_class_name(target_code)
        if not target_class_name:
            logger.error(f"Unable to extract class name from {target_class_path}")
            return {}
        
        # Extracts the target class package
        target_package = self._extract_package(target_code)
        logger.info(f"Target class package: {target_package}")
            
        # Finds all Java files in the project
        java_files = self.find_java_files()
        logger.info(f"Found {len(java_files)} Java files in the project")
        
        # Extracts imports from the target class
        imports = self.extract_imports(target_code)
        logger.info(f"Found {len(imports)} imports")
        
        # Resolves dependencies recursively
        dependencies = {target_class_name: target_code}
        resolved_imports = set()
        
        # First pass: resolves direct imports
        for import_stmt in imports:
            if self._is_external_import(import_stmt):
                continue
                
            resolved_file = self.resolve_import_to_file(import_stmt, java_files)
            if resolved_file:
                logger.info(f"Resolved: {import_stmt} -> {resolved_file}")
                resolved_imports.add(resolved_file)
            else:
                logger.warning(f"Not resolved: {import_stmt}")
        
        # Loads direct dependent classes (from import)
        for file_path in resolved_imports:
            dep_code = leggi_file(file_path)
            if dep_code:
                dep_class_name = self.extract_class_name(dep_code)
                if dep_class_name:
                    dependencies[dep_class_name] = dep_code
                    logger.info(f"Loaded dependency (import): {dep_class_name}")
        
        # Finds classes in the same package used without explicit import
        if target_package:
            same_package_classes = self._find_same_package_classes(target_class_path, target_package, java_files)
            same_package_deps = self._find_same_package_dependencies(target_code, target_class_name, same_package_classes)
            
            for class_name, code in same_package_deps.items():
                if class_name not in dependencies:
                    dependencies[class_name] = code
                    logger.info(f"Loaded dependency (same package, no import): {class_name}")
        
        # Third pass: analyzes dependencies of dependencies (recursive)
        new_dependencies = self._analyze_recursive_dependencies(dependencies, java_files)
        dependencies.update(new_dependencies)
        
        logger.info(f"Total classes loaded: {len(dependencies)}")
        return dependencies
    
    def _analyze_recursive_dependencies(self, current_dependencies: Dict[str, str], java_files: List[str]) -> Dict[str, str]:
        """Analyzes the dependencies of dependencies recursively."""
        new_dependencies = {}
        
        for class_name, class_code in current_dependencies.items():
            # Extracts imports from this class
            imports = self.extract_imports(class_code)
            
            for import_stmt in imports:
                if self._is_external_import(import_stmt):
                    continue
                    
                # Checks if this dependency has already been resolved
                dep_class_name = import_stmt.split('.')[-1]
                if dep_class_name in current_dependencies or dep_class_name in new_dependencies:
                    continue
                
                # Resolves the dependency
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
        """Automatically detects the project base package by analyzing Java files."""
        # Use cache if available
        if self._project_base_package_cache is not None:
            return self._project_base_package_cache
        
        java_files = self.find_java_files()
        if not java_files:
            self._project_base_package_cache = None
            return None
        
        packages = set()
        
        # Analyzes a sample of files for efficiency
        sample_size = min(50, len(java_files))
        for file_path in java_files[:sample_size]:
            code = leggi_file(file_path)
            if code:
                package = self._extract_package(code)
                if package:
                    # Extracts the base package (first two levels, e.g., com.example)
                    parts = package.split('.')
                    if len(parts) >= 2:
                        base_package = '.'.join(parts[:2])
                        packages.add(base_package)
        
        if packages:
            # Counts occurrences across all files
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
                # Returns the most common package
                result = max(package_counts.items(), key=lambda x: x[1])[0]
                self._project_base_package_cache = result
                return result
        
        self._project_base_package_cache = None
        return None
    
    def _is_external_import(self, import_stmt: str) -> bool:
        """Checks if an import is external (libraries, frameworks, etc.)."""
        external_prefixes = [
            'java.', 'javax.', 'org.springframework', 'org.hibernate',
            'com.fasterxml', 'org.apache', 'org.slf4j', 'org.junit',
            'junit.', 'org.mockito', 'org.hamcrest', 'org.springframework',
            'org.springframework.security', 'org.springframework.web',
            'org.springframework.boot', 'org.springframework.data'
        ]
        
        # Automatically detects the project base package
        project_base_package = self._get_project_base_package()
        if project_base_package and import_stmt.startswith(project_base_package):
            return False
            
        return any(import_stmt.startswith(prefix) for prefix in external_prefixes)

    
    def _extract_package(self, java_code: str) -> Optional[str]:
        """Extracts the package from Java code."""
        lines = java_code.split('\n')
        for line in lines:
            line = line.strip()
            if line.startswith('package '):
                return line[8:].rstrip(';')
        return None
    
    def _find_same_package_classes(self, target_class_path: str, target_package: str, java_files: List[str]) -> Dict[str, str]:
        """
        Finds all classes in the same package as the target class.
        Returns a dictionary {class_name: file_path} for classes in the same package.
        """
        same_package_classes = {}
        
        if not target_package:
            return same_package_classes
        
        target_file_normalized = os.path.normpath(target_class_path).lower()
        
        for file_path in java_files:
            # Skip the target class itself
            if os.path.normpath(file_path).lower() == target_file_normalized:
                continue
            
            # Optimization: read only first lines for package
            try:
                with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                    head = [next(f) for _ in range(20)]
                code_head = "".join(head)
                file_package = self._extract_package(code_head)
                
                # If not found in first lines, read all (rare case of long comments)
                if not file_package:
                    code = leggi_file(file_path)
                    file_package = self._extract_package(code)
                else:
                    code = None # Will read later if needed
                    
                if file_package == target_package:
                    if code is None:
                        code = leggi_file(file_path)
                    class_name = self.extract_class_name(code)
                    if class_name:
                        same_package_classes[class_name] = file_path
                        logger.debug(f"Found same package class: {class_name}")
            except StopIteration:
                pass # Empty file
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
            # Primitives
            'void', 'int', 'long', 'short', 'byte', 'char', 'boolean', 'float', 'double',
            # Wrapper
            'Integer', 'Long', 'Short', 'Byte', 'Character', 'Boolean', 'Float', 'Double',
            # Common JDK
            'String', 'Object', 'Class', 'System', 'Exception', 'RuntimeException',
            'Throwable', 'Error', 'Thread', 'Runnable',
            # Collections
            'List', 'ArrayList', 'LinkedList', 'Map', 'HashMap', 'TreeMap', 'LinkedHashMap',
            'Set', 'HashSet', 'TreeSet', 'LinkedHashSet', 'Collection', 'Collections',
            'Iterator', 'Iterable', 'Enumeration', 'Queue', 'Deque', 'ArrayDeque',
            'Optional', 'Stream', 'Collectors',
            # Other common types
            'Arrays', 'Math', 'StringBuilder', 'StringBuffer', 'Comparator', 'Comparable',
            'Date', 'Calendar', 'LocalDate', 'LocalDateTime', 'Instant', 'Duration',
            'Pattern', 'Matcher', 'UUID', 'Random', 'File', 'Path', 'Files',
            'InputStream', 'OutputStream', 'Reader', 'Writer', 'BufferedReader', 'BufferedWriter',
            'IOException', 'FileNotFoundException', 'NullPointerException', 'IllegalArgumentException',
            'IllegalStateException', 'UnsupportedOperationException', 'IndexOutOfBoundsException',
            # Common annotations
            'Override', 'Deprecated', 'SuppressWarnings', 'FunctionalInterface',
            # Generics placeholder
            'T', 'E', 'K', 'V', 'R', 'U'
        }
        
        try:
            tree = javalang.parse.parse(java_code)
            
            for path, node in tree:
                # Extracts types from local variable declarations
                if isinstance(node, javalang.tree.LocalVariableDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Extracts types from method parameters
                if isinstance(node, javalang.tree.FormalParameter):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Extracts types from field declarations
                if isinstance(node, javalang.tree.FieldDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Extracts types from method return types
                if isinstance(node, javalang.tree.MethodDeclaration):
                    if hasattr(node, 'return_type') and node.return_type and hasattr(node.return_type, 'name'):
                        type_name = node.return_type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Extracts types from object creation (new Classe())
                if isinstance(node, javalang.tree.ClassCreator):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Extracts types from cast
                if isinstance(node, javalang.tree.Cast):
                    if hasattr(node, 'type') and hasattr(node.type, 'name'):
                        type_name = node.type.name.split('<')[0].split('[')[0]
                        if type_name not in excluded_types:
                            types_used.add(type_name)
                
                # Extracts types from instanceof
                if isinstance(node, javalang.tree.BinaryOperation):
                    if hasattr(node, 'operator') and node.operator == 'instanceof':
                        if hasattr(node, 'operandr') and hasattr(node.operandr, 'name'):
                            type_name = node.operandr.name.split('<')[0].split('[')[0]
                            if type_name not in excluded_types:
                                types_used.add(type_name)
                
                # Extracts types from throws
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
                
                # Extracts types from extends/implements
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
        Identifies which classes in the same package are actually used in the target code.
        Returns {class_name: class_code} for same-package dependencies.
        """
        dependencies = {}
        
        if not same_package_classes:
            return dependencies
        
        # Extracts all types used in the code
        types_used = self._extract_types_used_in_code(target_code)
        logger.debug(f"Types used in code: {types_used}")
        
        # Checks which types correspond to classes in the same package
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
        Creates a complete context for test generation, including
        the target class and its main dependencies.
        """
        dependencies = self.analyze_class_dependencies(target_class_path)
        
        # Limit the number of dependencies to avoid overly long prompts
        if len(dependencies) > max_dependencies:
            logger.warning(f"Too many dependencies ({len(dependencies)}), limiting to {max_dependencies}")
            # Keeps the target class and the first dependencies
            limited_deps = dict(list(dependencies.items())[:max_dependencies])
        else:
            limited_deps = dependencies
        
        # Creates the context for the prompt
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
        Extracts only the methods actually used by the original method from the dependent classes.
        Instead of passing the entire class, pass only the called methods.
        """
        logger.info(f"Extracting methods used from method {nome_metodo}")
        
        # Extracts the original method
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
        
        # Extracts class fields to identify dependencies
        campi_classe = self._estrai_campi_classe(target_code)
        logger.debug(f"Class fields: {campi_classe}")
        
        # Analyzes the method to find method calls (including fields and parameters)
        metodi_chiamati_per_classe = self._analizza_chiamate_metodi(metodo_originale, dependencies, campi_classe)
        logger.info(f"Methods called per class: {metodi_chiamati_per_classe}")
        
        # Extracts only the methods used by each class, along with fields and constructors
        # Returns a dictionary with structure: {class_name: {'nome_classe': str, 'fields': List[str], 'costruttori': List[str], 'metodi': List[str]}}
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
                    # Extracts class name
                    nome_classe_estratto = self.extract_class_name(codice_classe) or class_name
                    
                    # Extracts fields and constructors of the external class (AST parsing once)
                    campi_codice = []
                    costruttori_codice = []
                    try:
                        tree = javalang.parse.parse(codice_classe)
                        lines = codice_classe.split('\n')
                        for path, node in tree:
                            # Extracts fields
                            if isinstance(node, javalang.tree.FieldDeclaration):
                                if hasattr(node, 'position') and node.position:
                                    start_line = node.position.line - 1
                                    end_line = start_line
                                    # Finds the end of the declaration (up to ;)
                                    for i in range(start_line, len(lines)):
                                        if ';' in lines[i]:
                                            end_line = i
                                            break
                                    campo_codice = '\n'.join(lines[start_line:end_line + 1])
                                    campi_codice.append(campo_codice)
                            
                            # Extracts constructors (complete with implementation)
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
                        # Fallback for constructors: use only signatures
                        costruttori_signature = self._estrai_costruttori(codice_classe)
                        for costruttore_signature in costruttori_signature:
                            # Removes trailing ; and adds empty body
                            signature_clean = costruttore_signature.rstrip(';').strip()
                            costruttori_codice.append(f"{signature_clean} {{}}")
                    
                    # Saves in separate structure for better prompt formatting
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
                    # Builds signature from node components
                    modifiers = " ".join(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else ""
                    return_type = node.return_type.name if hasattr(node, 'return_type') and node.return_type else "void"
                    params_list = []
                    if hasattr(node, 'parameters'):
                        for p in node.parameters:
                            if hasattr(p, 'type') and hasattr(p, 'name'):
                                # Handles modifiers (e.g., final)
                                p_modifiers = ""
                                if hasattr(p, 'modifiers') and 'final' in p.modifiers:
                                     p_modifiers = "final "
                                
                                # Handles type (base logic, complex generics omitted for now but arrays handled)
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
            # Fallback: search for signature in code
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
        Extracts signatures of all public getter methods in a class.
        Includes: get*, is*, has*, size(), isEmpty(), length(), iterator(), toArray(), etc.
        
        Useful for providing the LLM with available methods for test assertions.
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
                    # Checks if it's public
                    modifiers = set(node.modifiers) if hasattr(node, 'modifiers') and node.modifiers else set()
                    if 'public' not in modifiers:
                        continue
                    
                    method_name = node.name
                    is_getter = False
                    
                    # Check if it's a getter pattern (getX, isX, etc.)
                    for pattern in getter_patterns:
                        if method_name.startswith(pattern) and len(method_name) > len(pattern):
                            # Character after pattern must be uppercase (getOptions, not getoptions)
                            if method_name[len(pattern)].isupper():
                                is_getter = True
                                break
                    
                    # Check if it's a utility method
                    if method_name in utility_methods:
                        is_getter = True
                    
                    if is_getter:
                        # Build signature
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
                        
                        # Parameters
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
        Extracts class fields with complete information.
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
        Useful for providing the LLM with correct class paths.
        
        Also resolves wildcard imports (e.g., import com.example.*) by searching
        all Java files in the corresponding project directory.
        """
        import_mapping = {}
        wildcard_packages = []  # List of packages with wildcard to resolve
        
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
                            # Wildcard import - save package for later resolution
                            package_path = fqn[:-2]  # Remove ".*"
                            wildcard_packages.append(package_path)
                        else:
                            import_mapping[simple_name] = fqn
                elif line.startswith('public class') or line.startswith('class') or \
                     line.startswith('public interface') or line.startswith('public abstract'):
                    # Stop when class definition starts
                    break
            
            # Resolve wildcard imports by searching in the project
            if wildcard_packages and self.project_root:
                for package in wildcard_packages:
                    resolved_classes = self._resolve_wildcard_import(package)
                    for simple_name, fqn in resolved_classes.items():
                        # Don't overwrite explicit imports
                        if simple_name not in import_mapping:
                            import_mapping[simple_name] = fqn
                            
        except Exception as e:
            logger.warning(f"Error extracting import mapping: {e}")
        return import_mapping
    
    def _resolve_wildcard_import(self, package: str) -> Dict[str, str]:
        """
        Resolves a wildcard import by searching for all Java classes in the specified package.
        """
        resolved = {}
        try:
            # Convert package to relative path (com.example -> com/example)
            package_path = package.replace('.', os.sep)
            
            # Search for package directory in various possible src paths
            possible_src_dirs = ['src/main/java', 'src', 'java', '']
            
            for src_dir in possible_src_dirs:
                if src_dir:
                    full_package_dir = os.path.join(self.project_root, src_dir, package_path)
                else:
                    full_package_dir = os.path.join(self.project_root, package_path)
                
                if os.path.isdir(full_package_dir):
                    # Found! List all .java files in the directory
                    for filename in os.listdir(full_package_dir):
                        if filename.endswith('.java'):
                            # Extract class name (remove .java)
                            class_name = filename[:-5]
                            # Build FQN
                            fqn = f"{package}.{class_name}"
                            resolved[class_name] = fqn
                    
                    if resolved:
                        logger.debug(f"Resolved wildcard import {package}.* -> {len(resolved)} classes")
                    break  # Found, exit loop
                    
        except Exception as e:
            logger.warning(f"Error resolving wildcard import {package}.*: {e}")
        
        return resolved

    def _estrai_parametri_metodo(self, codice_metodo: str) -> Dict[str, str]:
        """Extracts method parameters {parameter_name: type}."""
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
        Analyzes method code to find method calls on objects of dependent classes.
        Returns {class_name: {called_method_names}}
        Also includes internal methods called directly (method() or this.method()).
        """
        metodi_per_classe = {}
        
        if not codice_metodo:
            return metodi_per_classe
        
        if campi_classe is None:
            campi_classe = {}
        
        # Extracts method parameters
        parametri_metodo = self._estrai_parametri_metodo(codice_metodo)
        
        # Creates case-insensitive map of class names in dependencies
        class_names_lower = {name.lower(): name for name in dependencies.keys()}
        
        # Identifies internal methods called directly (method() or this.method())
        if nome_classe_target:
            # Patterns for direct calls: method() or this.method()
            # Excludes Java keywords and common methods
            java_keywords = {'if', 'for', 'while', 'switch', 'catch', 'try', 'new', 'return', 'throw', 'assert', 'synchronized'}
            common_methods = {'toString', 'equals', 'hashCode', 'getClass', 'notify', 'notifyAll', 'wait', 'print', 'println'}
            
            # Pattern for direct method calls (not on objects)
            # Searches for: method( or this.method(
            # Uses negative lookbehind to exclude calls with a preceding dot (e.g., obj.method)
            pattern_direct = r'(?<!\.)\b(?:this\.)?(\w+)\s*\('
            matches_direct = re.finditer(pattern_direct, codice_metodo)
            
            for match in matches_direct:
                method_name = match.group(1)
                
                # Skip Java keywords and common methods
                if method_name in java_keywords or method_name in common_methods:
                    continue
                
                start_pos = match.start()
                
                # Check that it's not a variable declaration or something else
                # Search for pattern: Type method( which would indicate a declaration
                before_match = codice_metodo[max(0, start_pos - 50):start_pos]
                if re.search(r'\b(public|private|protected|static|final|abstract|synchronized|native|strictfp)\s+(\w+\s+)*' + re.escape(method_name) + r'\s*\(', before_match):
                    continue  # Probably a method declaration
                
                # Adds as internal method of the target class
                metodi_per_classe.setdefault(nome_classe_target, set()).add(method_name)
                logger.debug(f"Found direct internal method call: {method_name}() -> {nome_classe_target}")
        
        # Pattern regex to find method calls: variable.method() or this.variable.method()
        pattern = r'(?:this\.)?(\w+)\.(\w+)\s*\('
        matches = re.finditer(pattern, codice_metodo)

        # Pattern for method references: this::method or ClassName::method or var::method
        method_ref_pattern = r'(?:this|(\w+))::(\w+)'
        method_ref_matches = re.finditer(method_ref_pattern, codice_metodo)
        
        for match in method_ref_matches:
            qualifier = match.group(1) # None if 'this', otherwise class/var name
            method_name = match.group(2)
            
            if not qualifier: # Case this::method
                metodi_per_classe.setdefault(nome_classe_target, set()).add(method_name)
                logger.debug(f"Found internal method reference: this::{method_name} -> {nome_classe_target}")
            else:
                # Case ClassName::method or var::method
                # 1. Check if it's a class (static method or array constructor)
                if qualifier.lower() in class_names_lower:
                     class_name = class_names_lower[qualifier.lower()]
                     metodi_per_classe.setdefault(class_name, set()).add(method_name)
                     logger.debug(f"Found static method reference: {qualifier}::{method_name} -> {class_name}")
                # 2. Check if it's a variable (field, parameter, local)
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
            
            # Skip common methods that are not dependency calls
            if method_name in ('toString', 'equals', 'hashCode', 'getClass', 'notify', 'notifyAll', 'wait'):
                continue
            
            class_name = None
            
            # Check if it's a static call (var_name starts with uppercase and matches a class)
            if var_name[0].isupper() and var_name.lower() in class_names_lower:
                class_name = class_names_lower[var_name.lower()]
                logger.debug(f"Found static call: {var_name}.{method_name}() -> {class_name}")
            
            # Check if it's a class field
            elif var_name in campi_classe:
                tipo_campo = campi_classe[var_name]
                if tipo_campo.lower() in class_names_lower:
                    class_name = class_names_lower[tipo_campo.lower()]
                    logger.debug(f"Found call (class field): {var_name}.{method_name}() -> {class_name}")
            
            # Check if it's a method parameter
            elif var_name in parametri_metodo:
                tipo_param = parametri_metodo[var_name]
                if tipo_param.lower() in class_names_lower:
                    class_name = class_names_lower[tipo_param.lower()]
                    logger.debug(f"Found call (method parameter): {var_name}.{method_name}() -> {class_name}")
            
            # Check if it's a for-each variable
            elif var_name in foreach_vars:
                tipo_foreach = foreach_vars[var_name]
                if tipo_foreach.lower() in class_names_lower:
                    class_name = class_names_lower[tipo_foreach.lower()]
                    logger.debug(f"Found call (for-each variable): {var_name}.{method_name}() -> {class_name}")
            
            # 3. Search for local variable declaration in the method
            else:
                var_pattern = rf'\b(\w+)\s+{re.escape(var_name)}\s*[=;]'
                var_match = re.search(var_pattern, codice_metodo)
                if var_match:
                    potential_type = var_match.group(1)
                    if potential_type.lower() in class_names_lower:
                        class_name = class_names_lower[potential_type.lower()]
                        logger.debug(f"Found call (local variable): {var_name}.{method_name}() -> {class_name}")
                else:
                    # 4. If the variable name matches (case-insensitive) a class in dependencies
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
                # Extracts local variables
                if isinstance(node, javalang.tree.LocalVariableDeclaration):
                    if hasattr(node, 'type') and hasattr(node.type, 'name') and hasattr(node, 'declarators'):
                        tipo = node.type.name.split('<')[0].split('[')[0]
                        for declarator in node.declarators:
                            if hasattr(declarator, 'name'):
                                variabili_tipi[declarator.name] = tipo
                
                # Extracts for-each loop variables
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
                            # Search in local variables (including for-each)
                            if qualifier in variabili_tipi:
                                class_name = variabili_tipi[qualifier]
                                if class_name.lower() in class_names_lower:
                                    class_name = class_names_lower[class_name.lower()]
                                    if hasattr(node, 'member') and node.member:
                                        metodi_per_classe.setdefault(class_name, set()).add(node.member)
                                        logger.debug(f"AST: {qualifier}.{node.member}() -> {class_name}")
                            # Check if it's a static call (qualifier starts with uppercase)
                            elif qualifier[0].isupper() and qualifier.lower() in class_names_lower:
                                class_name = class_names_lower[qualifier.lower()]
                                if hasattr(node, 'member') and node.member:
                                    metodi_per_classe.setdefault(class_name, set()).add(node.member)
                                    logger.debug(f"AST static call: {qualifier}.{node.member}() -> {class_name}")
                            # Search directly in dependencies (case-insensitive)
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
            # Check if it's a known dependency (exact case)
            if type_name in class_names_lower.values(): 
                if type_name not in metodi_per_classe:
                    metodi_per_classe[type_name] = set()
                    logger.debug(f"Found cast to {type_name}: forcing context inclusion")
            elif type_name.lower() in class_names_lower: # Case-insensitive check
                 real_name = class_names_lower[type_name.lower()]
                 if real_name not in metodi_per_classe:
                    metodi_per_classe[real_name] = set()
                    logger.debug(f"Found cast to {real_name} (from {type_name}): forcing context inclusion")

        # Extracts types from GENERICS to ensure their context is included (e.g. List<Type>)
        # Pattern: <Type> or <Type, Type2>
        generics_pattern = r'<([^>]+)>'
        generics_matches = re.finditer(generics_pattern, codice_metodo)
        for match in generics_matches:
            content = match.group(1)
            # Handles multiple types (Map<K,V>)
            types = [t.strip().split('[')[0] for t in content.split(',')] # Removes array dimensions
            for type_name in types:
                type_name = type_name.strip()
                if not type_name or type_name == '?': continue 
                
                # Handles Wildcards (List<? extends Type>)
                if 'extends' in type_name:
                    type_name = type_name.split('extends')[1].strip()
                elif 'super' in type_name:
                    type_name = type_name.split('super')[1].strip()
                
                type_name = type_name.split('<')[0].strip() # Handles nested generics (Type<Sub>)
                
                if type_name in class_names_lower.values():
                    if type_name not in metodi_per_classe:
                        metodi_per_classe[type_name] = set()
                        logger.debug(f"Found generic type {type_name}: forcing context inclusion")
                elif type_name.lower() in class_names_lower:
                    real_name = class_names_lower[type_name.lower()]
                    if real_name not in metodi_per_classe:
                        metodi_per_classe[real_name] = set()
                        logger.debug(f"Found generic type {real_name} (from {type_name}): forcing context inclusion")

        # Extracts types from STATIC CONSTANTS (Class.CONSTANT)
        # Pattern: Class.PROP or Class.CONST_NAME (only uppercase for constants or PascalCase for static properties)
        static_const_pattern = r'\b([A-Z]\w+)\.([A-Z][A-Z0-9_]*|[A-Z]\w+)\b'
        static_matches = re.finditer(static_const_pattern, codice_metodo)
        for match in static_matches:
            class_name = match.group(1)
            member_name = match.group(2)
            
            # Excludes method calls already handled (e.g. Class.method()) - here we look for fields
            # If followed by ( it's a method, already handled
            # Search in original code if there is a ( after the match
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
        Extracts exceptions declared in a method's throws clause.
        """
        eccezioni = []
        try:
            tree = javalang.parse.parse(codice_classe)
            
            # Helper function for signature matching
            def _match_signature(node, signature):
                if not signature:
                    return True
                    
                # Extracts parameter types from the searched signature
                import re
                match = re.search(r'\(([^)]*)\)', signature)
                if not match:
                    return True
                    
                params_str = match.group(1).strip()
                if not params_str:
                    expected_types = []
                else:
                    # Extracts the type from parameters, handling 'final' keyword
                    expected_types = []
                    for p in params_str.split(','):
                        parts = p.strip().split()
                        # Removes 'final' if present
                        type_parts = [part for part in parts if part != 'final']
                        if type_parts:
                            expected_types.append(type_parts[0])  # The first remaining is the type
                
                # Compare with node parameters
                if not hasattr(node, 'parameters') or not node.parameters:
                    return len(expected_types) == 0
                    
                if len(node.parameters) != len(expected_types):
                    return False
                    
                for i, param in enumerate(node.parameters):
                    if hasattr(param, 'type') and hasattr(param.type, 'name'):
                        param_type = param.type.name
                        # Handles arrays
                        if hasattr(param.type, 'dimensions') and param.type.dimensions:
                            param_type += '[]' * len(param.type.dimensions)
                        
                        # Simplified comparison (only base type name)
                        if param_type.split('<')[0].split('[')[0] != expected_types[i].split('<')[0].split('[')[0]:
                            return False
                            
                return True
            
            for path, node in tree:
                if isinstance(node, javalang.tree.MethodDeclaration) and node.name == nome_metodo:
                    # Verify signature if provided
                    if metodo_signature and not _match_signature(node, metodo_signature):
                        continue
                    
                    # Extracts exceptions from the throws clause
                    if hasattr(node, 'throws') and node.throws:
                        for throw_type in node.throws:
                            if hasattr(throw_type, 'name'):
                                eccezioni.append(throw_type.name)
                            elif isinstance(throw_type, str):
                                eccezioni.append(throw_type)
                    
                    # If we find the right method, stop
                    if not metodo_signature or _match_signature(node, metodo_signature):
                        break
                        
        except Exception as e:
            logger.warning(f"Error extracting method exceptions {nome_metodo}: {e}")
            # Fallback with regex
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
        Finds the exception class in the project and extracts its constructors.
        """
        if java_files is None:
            java_files = self.find_java_files()
        
        # Standard Java exceptions to ignore
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
        
        # Search for the exception class file
        exception_file = None
        for file_path in java_files:
            if file_path.endswith(f'{exception_name}.java'):
                exception_file = file_path
                break
        
        if not exception_file:
            logger.debug(f"Exception file {exception_name} not found in project")
            return None
        
        # Read and analyze the file
        codice_eccezione = leggi_file(exception_file)
        if not codice_eccezione:
            return None
        
        # Extracts package
        package = self._extract_package(codice_eccezione)
        
        # Extracts constructors
        costruttori = self._estrai_costruttori(codice_eccezione)
        
        return {
            'nome_classe': exception_name,
            'costruttori': costruttori,
            'package': package,
            'file_path': exception_file
        }

    
    def _estrai_imports_completi(self, codice_classe: str) -> str:
        """Extracts all imports from the code."""
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
        """Extracts all constructors of the class (signature only)."""
        costruttori = []
        try:
            tree = javalang.parse.parse(codice_classe)
            lines = codice_classe.split('\n')
            for path, node in tree:
                if isinstance(node, javalang.tree.ConstructorDeclaration):
                    # Builds the constructor signature
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
            # Fallback: search constructors with regex
            try:
                pattern = r'(public|private|protected)?\s*(\w+)\s*\([^)]*\)\s*\{'
                matches = re.finditer(pattern, codice_classe)
                class_name = self.extract_class_name(codice_classe)
                if class_name:
                    for match in matches:
                        if match.group(2) == class_name:  # The constructor has the same name as the class
                            signature = match.group(0).split('{')[0].strip() + ";"
                            if signature not in costruttori:
                                costruttori.append(signature)
            except:
                pass
        return costruttori

    def _estrai_enum_values(self, codice_classe: str) -> Dict[str, List[str]]:
        """
        Extracts all enums defined in the class and their values.
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
            # Fallback: search enum with regex
            try:
                # Pattern for enum: public enum Type { VALUE1, VALUE2, ... }
                # or inner enum: enum Type { VALUE1(true), VALUE2(false) }
                pattern = r'(?:public\s+)?enum\s+(\w+)\s*\{([^}]+)\}'
                for match in re.finditer(pattern, codice_classe, re.DOTALL):
                    enum_name = match.group(1)
                    body = match.group(2)
                    # Extract values (before the first method or constructor)
                    # Values can be: VALUE, VALUE(args), VALUE { body }
                    values = []
                    # Split on ';' to separate values from methods
                    parts = body.split(';')
                    if parts:
                        values_section = parts[0]
                        # Extract values names
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
    