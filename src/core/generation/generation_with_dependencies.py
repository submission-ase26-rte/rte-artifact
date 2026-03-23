from core.analysis.dependency_analyzer import JavaDependencyAnalyzer
from utils.code.dependency_selector import select_top_k
from utils.text.text_utils import estrai_codice_java
from utils.io.file_utils import salva_file, get_test_dir, get_main_dir, leggi_file
from utils.prompt.prompt_logger import salva_prompt_log
from utils.prompt.prompt_helpers import (
    costruisci_metodo_precedente_info,
    costruisci_struttura_classe_info,
    costruisci_test_info_section,
    costruisci_sezione_istruzioni_specifiche
)
from utils.tracking.token_tracker import (
    save_test_generation_tokens, 
    save_method_generation_tokens,
    capture_llm_tokens
)
from core.evaluation.evaluation import estrai_metodo_singolo
from utils.code.code_analysis import estrai_metodo_completo
import os
import re
import javalang


# Importa la funzione unificata da token_tracker
from utils.tracking.token_tracker import capture_llm_tokens

def _capture_llm_tokens(phase: str, provider_name: str = None, refinement_iteration: int = None):
    """
    Cattura i token usati dall'ultima chiamata LLM (Ollama Cloud, Ollama Colab o Groq).
    Wrapper per retrocompatibilità - ora usa la funzione unificata in token_tracker.
    """
    capture_llm_tokens(operation_type=phase, refinement_iteration=refinement_iteration)


def genera_test_per_metodo_con_dipendenze(
    target_class_path: str,
    project_root: str,
    nome_metodo: str,
    provider_func,
    model_name: str,
    prompt_test_metodo: str,
    output_dir: str,
    versione: str,
    max_dependencies: int = 10,
    metodo_selezione: str = None,
    top_k_selezione: int = None,
    pesi_selezione: tuple = (0.45, 0.45, 0.10),
    nome_esperimento: str = None,
    metodo_signature: str = None,
    usa_istruzioni_speciali: bool = True,  # Flag per istruzioni condizionali
) -> tuple:
    """
    Genera test per un metodo con analisi delle dipendenze.
    """

    analyzer = JavaDependencyAnalyzer(project_root)
    target_class_name = os.path.basename(target_class_path).replace(".java", "")

    selezione_info = None
    if metodo_selezione in ("tfidf", "bm25", "pagerank", "hybrid", "exact", "summary", "structural"):
        all_dependencies = analyzer.analyze_class_dependencies(target_class_path)

        metodo = metodo_selezione
        
        if metodo == "summary":
            # Modalità summary: classe target completa + signature metodi esterni usati + campi e costruttori classi esterne
            target_code = leggi_file(target_class_path)
            # Usa estrai_metodo_completo che è più robusto con la regex aggiornata
            metodo_originale, _, _ = estrai_metodo_completo(target_code, nome_metodo, signature=metodo_signature)
            
            if metodo_originale:
                campi_classe = analyzer._estrai_campi_classe(target_code)
                metodi_chiamati_per_classe = analyzer._analizza_chiamate_metodi(metodo_originale, all_dependencies, campi_classe, nome_classe_target=target_class_name)
                
                # Nella modalità summary passa l'intero sorgente della classe, quindi non serve aggiungere le signature dei metodi interni
                parts = [f"// === Class: {target_class_name} ===\n{target_code}\n"]
                
                # Aggiunge signature metodi esterni usati + campi e costruttori delle classi esterne
                for class_name, metodi_chiamati in metodi_chiamati_per_classe.items():
                    if class_name in all_dependencies and class_name != target_class_name:
                        codice_classe = all_dependencies[class_name]
                        
                        # Estrae i campi della classe esterna
                        campi_classe_esterna = analyzer._estrai_campi_classe(codice_classe)
                        fields_lines = []
                        if campi_classe_esterna:
                            for nome_campo, tipo_campo in campi_classe_esterna.items():
                                fields_lines.append(f"    private {tipo_campo} {nome_campo};")
                        
                        # Estrae i costruttori della classe esterna
                        costruttori = analyzer._estrai_costruttori(codice_classe)
                        
                        # Estrae le signature dei metodi usati
                        signatures = []
                        for metodo_nome in metodi_chiamati:
                            sig = analyzer._estrai_signature_metodo(codice_classe, metodo_nome)
                            if sig:
                                signatures.append(sig)
                        
                        # Costruisce la sezione per la classe esterna
                        if fields_lines or signatures or costruttori:
                            class_section_parts = [f"// === Class: {class_name} ==="]
                            
                            # Aggiunge i campi se presenti
                            if fields_lines:
                                class_section_parts.append("// Fields:")
                                class_section_parts.extend(fields_lines)
                                class_section_parts.append("")  # Linea vuota
                            
                            # Aggiunge i costruttori se presenti
                            if costruttori:
                                class_section_parts.append("// Constructors:")
                                class_section_parts.extend(costruttori)
                                class_section_parts.append("")  # Linea vuota
                            
                            # Aggiunge le signature dei metodi se presenti
                            if signatures:
                                class_section_parts.append("// Method Signatures:")
                                class_section_parts.extend(signatures)
                            
                            parts.append("\n".join(class_section_parts) + "\n")
                
                context = "\n".join(parts)
                selezione_info = {
                    "active": True,
                    "method": "summary",
                    "top_k": None,
                    "weights": None,
                    "selected_classes": [
                        {"class_name": cls, "score": None, "norm_score": None, "rank": None}
                        for cls in metodi_chiamati_per_classe.keys() if cls != target_class_name
                    ],
                    "rankings": {},
                }
            else:
                context, _ = analyzer.create_test_context(target_class_path, max_dependencies)
        
        elif metodo == "structural":
            # Modalità structural: solo elementi strutturali necessari
            target_code = leggi_file(target_class_path)
            # Usa estrai_metodo_completo che è più robusto con la regex aggiornata
            metodo_originale, _, _ = estrai_metodo_completo(target_code, nome_metodo, signature=metodo_signature)
            
            if metodo_originale:
                # Estrae tutti gli import della classe target
                imports = analyzer._estrai_imports_completi(target_code)
                
                # Estrae import mapping per riferire i percorsi corretti
                import_mapping = analyzer._estrai_import_mapping(target_code)
                
                # === RILEVAMENTO TIPO PROGETTO DAGLI IMPORT ===
                # Rileva caratteristiche del progetto per istruzioni condizionali
                imports_str = imports if imports else ""
                has_servlet = 'javax.servlet' in imports_str or 'jakarta.servlet' in imports_str
                has_spring = 'springframework' in imports_str
                has_jpa = 'javax.persistence' in imports_str or 'jakarta.persistence' in imports_str
                has_ejb = 'javax.ejb' in imports_str or 'jakarta.ejb' in imports_str
                has_cdi = 'javax.inject' in imports_str or 'jakarta.inject' in imports_str
                has_jndi = 'javax.naming' in imports_str
                
                # Rileva anche dal codice del metodo se usa classi utility statiche comuni
                has_static_utils = any(util in metodo_originale for util in ['Log.', 'TradeConfig.', 'Logger.'])
                
                # Estrae i campi della classe target con visibilità reale
                campi_classe_vis = analyzer._estrai_campi_classe_con_visibilita(target_code)
                campi_classe = analyzer._estrai_campi_classe(target_code)  # Per compatibilità
                fields_lines = []
                if campi_classe_vis:
                    for nome_campo, info in campi_classe_vis.items():
                        visibilita = info['visibilita']
                        tipo = info['tipo']
                        static_mod = 'static ' if info.get('static') else ''
                        final_mod = 'final ' if info.get('final') else ''
                        fields_lines.append(f"    {visibilita} {static_mod}{final_mod}{tipo} {nome_campo};")
                
                # Estrae i costruttori della classe target
                costruttori_target = analyzer._estrai_costruttori(target_code)
                
                # Analizza le chiamate a metodi per distinguere interni ed esterni
                metodi_chiamati_per_classe = analyzer._analizza_chiamate_metodi(metodo_originale, all_dependencies, campi_classe, nome_classe_target=target_class_name)
                
                # Identifica metodi interni (chiamati sulla classe target stessa)
                # ESCLUDE il metodo target stesso dalla lista
                metodi_interni = metodi_chiamati_per_classe.get(target_class_name, set())
                metodi_interni = {m for m in metodi_interni if m != nome_metodo}  # Esclude il metodo target
                implementazioni_metodi_interni = []
                for metodo_nome in metodi_interni:
                    # STRUCTURAL: Estrai l'implementazione completa, non solo la signature
                    impl = analyzer._estrai_metodo_da_classe(target_code, metodo_nome)
                    if impl:
                        implementazioni_metodi_interni.append(impl)
                
                # Costruisce la sezione per la classe target
                parts = []
                
                # Estrae il package della classe target per costruire FQN
                package_target = analyzer._extract_package(target_code)
                
                # Aggiunge SOLO il mapping import -> fully qualified names (più utile per l'LLM)
                # Rimossa la sezione raw imports per evitare duplicazione
                # IMPORTANTE: Aggiungiamo anche la classe target stessa e le classi esterne al mapping
                extended_import_mapping = dict(import_mapping) if import_mapping else {}
                
                # Aggiunge la classe target stessa al mapping (fondamentale per test nello stesso package)
                if package_target:
                    extended_import_mapping[target_class_name] = f"{package_target}.{target_class_name}"
                
                # Aggiunge le classi esterne usate (stesso package o risolte come dipendenze)
                for ext_class_name in metodi_chiamati_per_classe.keys():
                    if ext_class_name != target_class_name and ext_class_name in all_dependencies:
                        ext_class_code = all_dependencies[ext_class_name]
                        ext_package = analyzer._extract_package(ext_class_code)
                        if ext_package and ext_class_name not in extended_import_mapping:
                            extended_import_mapping[ext_class_name] = f"{ext_package}.{ext_class_name}"
                
                if extended_import_mapping:
                    import_lines = [f"//   {simple} -> {fqn}" for simple, fqn in extended_import_mapping.items()]
                    parts.append("// CORRECT IMPORT PATHS (use these in test imports):")
                    parts.extend(import_lines)
                    parts.append("")
                
                # Aggiunge nome classe
                parts.append(f"// === Class: {target_class_name} ===")
                
                # Aggiunge campi con visibilità reale
                if fields_lines:
                    parts.append("// Fields (with actual visibility):")
                    parts.extend(fields_lines)
                    parts.append("")
                
                # Aggiunge costruttori
                if costruttori_target:
                    parts.append("// Constructors:")
                    parts.extend(costruttori_target)
                    parts.append("")
                
                # Aggiunge getter/setter dalla classe target
                # Estrae tutti i metodi che iniziano con get/set/is
                getter_setter_signatures = []
                try:
                    import re as re_module
                    # Pattern per getter/setter/is: public/protected/private [static] ReturnType getX/setX/isX(...)
                    pattern = r'(?:public|protected|private)\s+(?:static\s+)?(?:final\s+)?[\w<>\[\],\s]+\s+(get[A-Z]\w*|set[A-Z]\w*|is[A-Z]\w*)\s*\([^)]*\)'
                    matches = re_module.finditer(pattern, target_code)
                    for match in matches:
                        method_name = match.group(1)
                        # Estrai la signature completa fino alla {
                        start = match.start()
                        # Trova l'inizio della riga per catturare eventuali annotazioni
                        line_start = target_code.rfind('\n', 0, start) + 1
                        # Trova la fine della signature (prima di '{' o ';')
                        end = match.end()
                        while end < len(target_code) and target_code[end] not in '{;':
                            end += 1
                        signature = target_code[match.start():end].strip()
                        if signature and signature not in getter_setter_signatures:
                            getter_setter_signatures.append(signature + ";")
                except Exception as e:
                    print(f"   WARNING:  Error extracting getter/setter: {e}")
                
                if getter_setter_signatures:
                    parts.append("// Getter/Setter Methods (for test assertions):")
                    parts.extend(getter_setter_signatures)
                    parts.append("")
                
                # Aggiunge IMPLEMENTAZIONI metodi interni usati (fondamentale per STRUCTURAL)
                if implementazioni_metodi_interni:
                    parts.append("// Internal Methods (full implementation for context):")
                    parts.extend(implementazioni_metodi_interni)
                    parts.append("")

                # Inclusione contesto SUPERCLASS (se estende qualcosa)
                try:
                    tree = javalang.parse.parse(target_code)
                    superclass_name = None
                    for path, node in tree:
                        if isinstance(node, javalang.tree.ClassDeclaration):
                            if hasattr(node, 'extends') and node.extends:
                                if hasattr(node.extends, 'name'):
                                    superclass_name = node.extends.name.split('<')[0]
                            break
                    
                    if superclass_name and superclass_name in all_dependencies:
                        superclass_code = all_dependencies[superclass_name]
                        
                        parts.append(f"// === Superclass: {superclass_name} (inherited members) ===")
                        
                        # Estrae campi protected/public dalla superclasse
                        campi_super = analyzer._estrai_campi_classe_con_visibilita(superclass_code)
                        if campi_super:
                            parts.append("// Inherited Fields:")
                            for nome, info in campi_super.items():
                                if info['visibilita'] in ('public', 'protected'):
                                    static_mod = 'static ' if info.get('static') else ''
                                    final_mod = 'final ' if info.get('final') else ''
                                    parts.append(f"    {info['visibilita']} {static_mod}{final_mod}{info['tipo']} {nome};")
                            parts.append("")

                        # Estrae metodi protected/public dalla superclasse (solo signature)
                        # Nota: Filtriamo metodi Object/common per pulizia
                        metodi_super_signatures = []
                        tree_super = javalang.parse.parse(superclass_code)
                        for path, node in tree_super:
                            if isinstance(node, javalang.tree.MethodDeclaration):
                                modifiers = set(node.modifiers)
                                if 'public' in modifiers or 'protected' in modifiers:
                                    # Skip common methods
                                    if node.name in ('toString', 'equals', 'hashCode', 'getClass', 'clone', 'finalize', 'wait', 'notify', 'notifyAll'):
                                        continue
                                        
                                    sig = analyzer._estrai_signature_metodo(superclass_code, node.name)
                                    if sig:
                                        metodi_super_signatures.append(sig)
                        
                        if metodi_super_signatures:
                            parts.append("// Inherited Method Signatures:")
                            parts.extend(metodi_super_signatures)
                            parts.append("")

                except Exception as e:
                    print(f"   WARNING:  Error extracting superclass context: {e}")

                
                
                # Aggiunge informazioni sulle classi esterne utilizzate
                for class_name, metodi_chiamati in metodi_chiamati_per_classe.items():
                    if class_name in all_dependencies and class_name != target_class_name:
                        codice_classe = all_dependencies[class_name]
                        
                        # Estrae nome classe, campi con visibilità e costruttori
                        campi_classe_esterna = analyzer._estrai_campi_classe_con_visibilita(codice_classe)
                        costruttori_esterni = analyzer._estrai_costruttori(codice_classe)
                        
                        # Estrae import mapping della classe esterna
                        import_mapping_ext = analyzer._estrai_import_mapping(codice_classe)
                        
                        # Estrae le signature dei metodi esterni usati
                        signatures_esterne = []
                        for metodo_nome in metodi_chiamati:
                            sig = analyzer._estrai_signature_metodo(codice_classe, metodo_nome)
                            if sig:
                                signatures_esterne.append(sig)
                        
                        # Costruisce la sezione per la classe esterna
                        if campi_classe_esterna or signatures_esterne or costruttori_esterni:
                            # Estrae il package della classe esterna per costruire il FQN
                            ext_package = analyzer._extract_package(codice_classe)
                            ext_fqn = f"{ext_package}.{class_name}" if ext_package else class_name
                            
                            # Rileva tipo classe (interface, abstract, enum, class)
                            tipo_classe = analyzer._estrai_tipo_classe(codice_classe)
                            
                            class_section_parts = [f"// === External Class: {class_name} ==="]
                            class_section_parts.append(f"// Full import path: {ext_fqn}")
                            
                            # Aggiunge warning per interfacce e classi astratte
                            if tipo_classe == "interface":
                                class_section_parts.append(f"// Type: INTERFACE (cannot instantiate - use Mockito.mock({class_name}.class) instead)")
                            elif tipo_classe == "abstract":
                                class_section_parts.append(f"// Type: ABSTRACT CLASS (cannot instantiate directly - use Mockito.mock() or anonymous subclass)")
                            elif tipo_classe == "enum":
                                class_section_parts.append(f"// Type: ENUM")
                            
                            # Aggiunge import mapping se presente
                            if import_mapping_ext:
                                class_section_parts.append("// Import paths:")
                                for simple, fqn in list(import_mapping_ext.items())[:5]:  # Limita per brevità
                                    class_section_parts.append(f"//   {simple} -> {fqn}")
                            
                            # Aggiunge i campi con visibilità reale se presenti
                            if campi_classe_esterna:
                                class_section_parts.append("// Fields (with actual visibility):")
                                for nome_campo, info in campi_classe_esterna.items():
                                    vis = info['visibilita']
                                    tipo = info['tipo']
                                    static_mod = 'static ' if info.get('static') else ''
                                    final_mod = 'final ' if info.get('final') else ''
                                    # Aggiunge warning per static final (non modificabili via reflection)
                                    immutable_comment = ""
                                    if info.get('static') and info.get('final'):
                                        immutable_comment = "  // IMMUTABLE - cannot modify via ReflectionTestUtils"
                                    class_section_parts.append(f"    {vis} {static_mod}{final_mod}{tipo} {nome_campo};{immutable_comment}")
                                class_section_parts.append("")
                            
                            # === NEW: Aggiunge enum values se presenti ===
                            # Questo aiuta l'LLM a usare i valori enum corretti invece di inventarli
                            enum_values = analyzer._estrai_enum_values(codice_classe)
                            if enum_values:
                                class_section_parts.append("// Enum Values (use ONLY these constants):")
                                for enum_name, values in enum_values.items():
                                    values_str = ", ".join(values)
                                    class_section_parts.append(f"//   {enum_name}: {values_str}")
                                class_section_parts.append("")
                            
                            # Aggiunge i costruttori se presenti
                            if costruttori_esterni:
                                class_section_parts.append("// Constructors:")
                                class_section_parts.extend(costruttori_esterni)
                                class_section_parts.append("")
                            
                            # Aggiunge le signature dei metodi esterni usati
                            if signatures_esterne:
                                class_section_parts.append("// Method Signatures (used by target method):")
                                class_section_parts.extend(signatures_esterne)
                            
                            # === NEW: Aggiunge getter pubblici per le asserzioni nei test ===
                            # Questo aiuta l'LLM a conoscere i metodi disponibili per verificare i risultati
                            getter_signatures = analyzer._estrai_getter_pubblici(codice_classe)
                            if getter_signatures:
                                # Evita duplicati con le signature già estratte
                                existing_methods = set(s.split('(')[0].split()[-1] for s in signatures_esterne if s)
                                nuovi_getter = [g for g in getter_signatures 
                                              if g.split('(')[0].split()[-1] not in existing_methods]
                                if nuovi_getter:
                                    class_section_parts.append("")
                                    class_section_parts.append("// Public Getter Methods (for test assertions):")
                                    class_section_parts.extend(nuovi_getter[:15])  # Limita a 15 per brevità
                            
                            parts.append("\n".join(class_section_parts) + "\n")
                
                context = "\n".join(parts)
                selezione_info = {
                    "active": True,
                    "method": "structural",
                    "top_k": None,
                    "weights": None,
                    "selected_classes": [
                        {"class_name": cls, "score": None, "norm_score": None, "rank": None}
                        for cls in metodi_chiamati_per_classe.keys() if cls != target_class_name
                    ],
                    "rankings": {},
                    # Project type flags per istruzioni condizionali
                    "project_type_flags": {
                        "has_servlet": has_servlet,
                        "has_spring": has_spring,
                        "has_jpa": has_jpa,
                        "has_ejb": has_ejb,
                        "has_cdi": has_cdi,
                        "has_jndi": has_jndi,
                        "has_static_utils": has_static_utils,
                    }
                }
            else:
                context, _ = analyzer.create_test_context(target_class_path, max_dependencies)
        else:
            top_k = top_k_selezione or max_dependencies

            deps_sel, ranked, rankings_all = select_top_k(
                all_dependencies,
                target_class_name,
                nome_metodo,
                metodo=metodo,
                top_k=top_k,
                weights=pesi_selezione if metodo == "hybrid" else None,
            )

            # Costruisce contesto dalle classi selezionate
            parts = [f"// === Class: {cls} ===\n{code}\n" for cls, code in deps_sel.items()]
            context = "\n".join(parts)

            ranking_key = metodo if metodo in ("tfidf", "bm25", "pagerank", "hybrid", "exact") else "tfidf"
            ranking_list = rankings_all.get(ranking_key, [])
            rank_map = {
                item["class_name"]: (item.get("score"), item.get("norm_score"), item.get("rank"))
                for item in ranking_list
            }

            selezione_info = {
                "active": True,
                "method": metodo,
                "top_k": top_k,
                "weights": pesi_selezione if metodo == "hybrid" else None,
                "selected_classes": [
                    {
                        "class_name": cls,
                        "score": float(rank_map.get(cls, (None, None, None))[0])
                        if rank_map.get(cls)
                        else None,
                        "norm_score": float(rank_map.get(cls, (None, None, None))[1])
                        if rank_map.get(cls)
                        else None,
                        "rank": int(rank_map.get(cls, (None, None, None))[2])
                        if rank_map.get(cls) and rank_map.get(cls)[2] is not None
                        else None,
                    }
                    for cls in deps_sel.keys()
                ],
                "rankings": {
                    key: [
                        {
                            "class_name": item.get("class_name"),
                            "score": float(item.get("score", 0.0)),
                            "norm_score": float(item.get("norm_score", 0.0)),
                            "rank": int(item.get("rank", 0)),
                        }
                        for item in vals
                    ]
                    for key, vals in rankings_all.items()
                },
            }
    else:
        # Modalità senza selezione intelligente
        context, _ = analyzer.create_test_context(target_class_path, max_dependencies)

    sezione_istruzioni_specifiche = costruisci_sezione_istruzioni_specifiche(prompt_test_metodo)
    
    # Estrai il package dalla classe target
    package_target = None
    if target_class_path and os.path.exists(target_class_path):
        try:
            codice_classe_target = leggi_file(target_class_path)
            package_match = re.search(r'^package\s+([\w.]+);', codice_classe_target, re.MULTILINE)
            package_target = package_match.group(1) if package_match else None
        except Exception:
            pass
    
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
        if target_class_path and os.path.exists(target_class_path):
            codice_classe_target = leggi_file(target_class_path)
            # Usa estrai_metodo_completo che include JavaDoc e annotazioni
            metodo_target_con_javadoc, _, _ = estrai_metodo_completo(codice_classe_target, nome_metodo, signature=metodo_signature)
            if not metodo_target_con_javadoc:
                # Fallback: usa estrai_metodo_singolo (già importato globalmente)
                metodo_target_con_javadoc = estrai_metodo_singolo(codice_classe_target, nome_metodo, include_javadoc=True, signature=metodo_signature)
    except Exception as e:
        print(f"   WARNING:  Error extracting method with JavaDoc: {e}")
    
    # Costruisci la sezione metodo target
    metodo_target_section = ""
    if metodo_target_con_javadoc:
        metodo_target_section = f"""
TARGET METHOD TO TEST:
The following is the specific method you need to test (including its JavaDoc comments if present):

{metodo_target_con_javadoc}

"""
    else:
        # SE IL METODO NON VIENE TROVATO (es. signature mismatch) -> ABORT
        print(f" CRITICAL ERROR: Method '{nome_metodo}' not found in class '{target_class_name}' with signature '{metodo_signature}'.")
        print("   Immediate experiment interruption to avoid incorrect prompts.")
        import sys
        sys.exit(1)
    
    # Test class name
    nome_classe_test = f"{target_class_name}_{nome_metodo}TestGenerated{versione if versione else ''}"
    
    # Rileva se la classe target è astratta
    is_target_abstract = False
    abstract_class_instructions = ""
    try:
        from utils.code.code_analysis import is_abstract_class, generate_abstract_class_instantiation
        if target_class_path and os.path.exists(target_class_path):
            codice_target = leggi_file(target_class_path)
            is_target_abstract = is_abstract_class(codice_target)
            if is_target_abstract:
                print(f"   WARNING:  Class {target_class_name} is abstract - special instructions in the prompt")
                instantiation_tmpl = generate_abstract_class_instantiation(target_class_name, codice_target)
                
                abstract_class_instructions = f"""
IMPORTANT: The target class "{target_class_name}" is ABSTRACT and cannot be instantiated directly.
- DO NOT use Mockito to mock the class itself (unless you need a partial mock).
- INSTANTIATE using this REAL ANONYMOUS SUBCLASS template (copy exactly):
```java
{instantiation_tmpl}
```
- DO NOT create custom concrete subclasses files.
- DO NOT invent classes that do not exist in the codebase.
"""
    except Exception as e:
        print(f"   WARNING:  Error detecting abstract class: {e}")
    
    # Estrai eccezioni dichiarate dal metodo target e aggiungi contesto
    exception_context_section = ""
    exception_instruction = ""
    try:
        if target_class_path and os.path.exists(target_class_path):
            codice_classe_target = leggi_file(target_class_path)
            
            # Usa il metodo per estrarre le eccezioni
            eccezioni = analyzer.estrai_eccezioni_metodo(codice_classe_target, nome_metodo, metodo_signature)
            
            if eccezioni:
                print(f"Method throws: {', '.join(eccezioni)}")
                exception_parts = []
                java_files = analyzer.find_java_files()
                
                for exception_name in eccezioni:
                    # Ottieni contesto dell'eccezione (costruttori)
                    exception_info = analyzer.estrai_contesto_eccezione(exception_name, java_files)
                    
                    if exception_info and exception_info.get('costruttori'):
                        exception_parts.append(f"\n// === Exception Class: {exception_name} ===")
                        exception_parts.append(f"// NOTE: The target method throws this exception.")
                        exception_parts.append("// Constructors:")
                        for costruttore in exception_info['costruttori']:
                            exception_parts.append(costruttore)
                        # Aggiungi esempio di come gestire l'eccezione nei test
                        exception_parts.append(f"\n")
                        exception_parts.append(f"EXCEPTION HANDLING IN TESTS - Use ONE of these patterns:")
                        exception_parts.append(f"Pattern 1 (for tests that EXPECT the exception to be thrown):")
                        exception_parts.append(f"```java")
                        exception_parts.append(f"@Test")
                        exception_parts.append(f"void testMethodThrows{exception_name}() {{")
                        exception_parts.append(f"assertThrows({exception_name}.class, () -> targetMethod(...));")
                        exception_parts.append(f"}}")
                        exception_parts.append(f"```")
                        exception_parts.append(f"\n")
                        exception_parts.append(f"Pattern 2 (for tests where exception should NOT occur):")
                        exception_parts.append(f"```java")
                        exception_parts.append(f"@Test")
                        exception_parts.append(f"void testMethodSucceeds() throws {exception_name} {{")
                        exception_parts.append(f"// Call method directly - exception propagates if thrown")
                        exception_parts.append(f"result = targetMethod(...);")
                        exception_parts.append(f"assertNotNull(result);")
                        exception_parts.append(f"}}")
                        exception_parts.append(f"```")
                
                if exception_parts:
                    exception_context_section = "\n".join(exception_parts) + "\n"
                
                # Aggiungi istruzione specifica per gestione eccezioni
                exception_instruction = f"\n- The target method throws {', '.join(eccezioni)}. Tests MUST properly handle or expect this exception in error scenarios."
    except Exception as e:
        print(f"   WARNING:  Error extracting exceptions: {e}")
    
    # === BUILD CONDITIONAL INSTRUCTIONS BASED ON PROJECT TYPE ===
    project_flags = selezione_info.get("project_type_flags", {}) if selezione_info else {}
    
    # Base instructions always present
    base_constraints = """- DO NOT use @Nested annotation
- All methods MUST have JUnit annotations (@Test, @BeforeEach, @AfterEach, etc.)
- DO NOT create inner classes or custom implementations.
- Helper methods inside the test class are allowed if they do not reimplement production logic.
- DO NOT use getter/setter methods that are not present in the provided class context
- CRITICAL: DO NOT mock the target class itself - ALWAYS instantiate it directly using 'new TargetClass(...)'. Mocking the target class defeats the purpose of testing its actual behavior
- You MAY mock DEPENDENCIES (constructor parameters, injected services) using @Mock, but NEVER the class under test
- CRITICAL: DO NOT instantiate INTERFACES or ABSTRACT classes directly - use Mockito.mock(Interface.class) instead
- DO NOT try to modify static final fields via ReflectionTestUtils - they are IMMUTABLE
- Do not infer undocumented behavior.
- Do not rely on implementation details not observable from the method signature.
- Do not assert exact outputs if they depend on non-deterministic factors.
- For expected exceptions, use JUnit 5 assertThrows() - DO NOT use JUnit 4 syntax @Test(expected=...) which is NOT valid in JUnit 5
- When using enum types from dependencies, use ONLY values explicitly shown in the context. If enum values are not listed, use a mock instead of guessing enum constants.
- Use ONLY the import paths explicitly provided in the 'CORRECT IMPORT PATHS' section - do not guess or invent import paths.
- Generate ONLY the test class without explanations or comments outside the code"""
    
    # Istruzioni condizionali per progetti Java EE/Spring (solo se flag abilitato)
    conditional_constraints = []
    
    if usa_istruzioni_speciali and project_flags:
        # Per Servlet (Java EE)
        if project_flags.get("has_servlet"):
            conditional_constraints.append("- DO NOT mock javax.servlet classes directly (ServletOutputStream, etc.) - use MockHttpServletRequest/Response from spring-test instead")
        
        # Per progetti con dependency injection (Spring, CDI, EJB)
        if project_flags.get("has_spring") or project_flags.get("has_cdi") or project_flags.get("has_ejb"):
            conditional_constraints.append("""- For PRIVATE fields without public setters (e.g., @Inject, @Autowired), use one of these approaches:
  * Spring's ReflectionTestUtils.setField(object, "fieldName", mockValue) - requires import org.springframework.test.util.ReflectionTestUtils
  * OR Mockito's @InjectMocks with @Mock annotations
- DO NOT try to access private fields directly (e.g., object.privateField = value) - this will NOT compile""")
        
        # Per JNDI lookups
        if project_flags.get("has_jndi") or project_flags.get("has_ejb"):
            conditional_constraints.append("- For JNDI lookups (DataSource, EJB), mock the DataSource/service directly and inject it using ReflectionTestUtils. Do NOT try to test JNDI resolution in unit tests")
        
        # Per static utility methods (Log, TradeConfig, etc.)
        if project_flags.get("has_static_utils"):
            conditional_constraints.append("- For static method calls (e.g., Log.error(), TradeConfig.getUserID()), use Mockito.mockStatic() to mock static methods. Example: try (MockedStatic<Log> mockedLog = mockStatic(Log.class)) { ... }")
    
    # Combina tutte le istruzioni
    all_constraints = base_constraints
    if conditional_constraints:
        all_constraints += "\n" + "\n".join(conditional_constraints)
    
    # Prompt finale - struttura consolidata con istruzioni migliorate
    prompt = f"""ROLE:
You are an expert Java developer specialized in writing complete and robust JUnit 5 tests.

{package_section}PROJECT CONTEXT:
{context}
{exception_context_section}{metodo_target_section}

TASK:
Generate a complete JUnit 5 test class for the method "{nome_metodo}" of class "{target_class_name}".

REQUIREMENTS:
- Test class name: {nome_classe_test}
- Include all necessary imports and the package declaration
- Cover all code paths that are:
  - deterministically reachable from the method’s public API
  - independent from hidden, external, or runtime-loaded data
- Test error scenarios by asserting the actual runtime behavior
  when invalid or null inputs are provided.
- Do not assume defensive checks unless they are explicitly present.
- Cover expected exceptions declared in the method signature if present{exception_instruction}

CONSTRAINTS:
{all_constraints}
{abstract_class_instructions}
{sezione_istruzioni_specifiche}"""

    salva_prompt_log(prompt, "Test_generation", target_class_name, versione, nome_esperimento, metodo_da_testare=nome_metodo, model_name=model_name)

    test_generato = provider_func(prompt, model_name)
    
    # Cattura i token usati per la generation test (Ollama Cloud o Groq)
    _capture_llm_tokens("test_generation")
    
    # === ERROR HANDLING: Provider returned None ===
    # This can happen if the syntax validation fails (e.g. invalid escape sequence)
    if test_generato is None:
        print(f" CRITICAL ERROR: The LLM provider returned None. Impossible to generate tests.")
        print("   Probable cause: syntax error in the code generated by the LLM.")
        import sys
        sys.exit(1)
    
    # Save the LLM response for the first generation
    from utils.prompt.prompt_logger import salva_risposta_llm
    risposta_path = salva_risposta_llm(test_generato, "Test_generation", target_class_name, versione, retry_count=0, nome_esperimento=nome_esperimento, metodo_da_testare=nome_metodo, model_name=model_name)
    
    versione_suffix = versione if versione else ""
    nome_classe_test_attesa = f"{target_class_name}_{nome_metodo}TestGenerated{versione_suffix}"
    codice_test = estrai_codice_java(test_generato, nome_classe_test_attesa)

    # IMPORTANTE: Aggiungi @Timeout a livello di classe per prevenire infinite loop
    from utils.test.test_utils import aggiungi_timeout_classe
    codice_test = aggiungi_timeout_classe(codice_test)

    # Salvataggio test
    test_dir = get_test_dir(target_class_path, project_root)
    nome_file = f"{target_class_name}_{nome_metodo}TestGenerated{versione_suffix}.java"
    percorso_file = salva_file(test_dir, nome_file, codice_test)

    return percorso_file, selezione_info, prompt, context


def rigenera_metodo_dai_test_con_dipendenze(
    test_file_path: str,
    project_root: str,
    nome_metodo: str,
    provider_func,
    model_name: str,
    prompt_rigenera_metodo: str,
    output_dir: str,
    versione: str,
    max_dependencies: int = 10,
    metodo_precedente_path: str = None,
    target_class_path: str = None,
    test_results_originale: dict = None,
    nome_esperimento: str = None,
    offusca_nome_metodo: bool = False,
    usa_dipendenze: bool = False,
    retry_count: int = None,
    metodo_signature: str = None,  # Signature completa per distinguere overload
    precomputed_context: str = None, # Contesto precalcolato dalla fase 1
    metodo_precedente_codice: str = None,
) -> str:
    """
    Rigenera un metodo a partire dai test forniti e dai risultati di coverage.
    
    """

    codice_test = leggi_file(test_file_path)

    test_filename = os.path.basename(test_file_path)
    if "TestGenerated" in test_filename:
        target_class_name = test_filename.split("_")[0]
    else:
        target_class_name = (
            test_filename.replace("TestGenerated", "")
            .replace(f"{versione}.java", "")
            .replace(f"_{nome_metodo}", "")
        )

    # Costruisce le sezioni del prompt usando helper functions
    metodo_precedente_info = costruisci_metodo_precedente_info(metodo_precedente_path, nome_metodo, offusca_nome=offusca_nome_metodo, metodo_precedente_codice=metodo_precedente_codice)
    struttura_classe_info = costruisci_struttura_classe_info(target_class_path=target_class_path)
    # Se c'è un metodo precedente, i test_results si riferiscono a quel metodo, non all'originale
    is_metodo_precedente = metodo_precedente_path is not None and os.path.exists(metodo_precedente_path)
    test_info_section, _, _ = costruisci_test_info_section(test_results_originale, codice_test, is_metodo_precedente=is_metodo_precedente)
    
    # Estrae contesto della classe target (nome, fields, costruttori, metodi interni usati)
    context_target_class = ""
    context_dipendenze = ""
    
    if not precomputed_context and target_class_path and os.path.exists(target_class_path):
        analyzer = JavaDependencyAnalyzer(project_root)
        target_code = leggi_file(target_class_path)
        
        if target_code:
            # Estrae nome classe
            nome_classe_target = analyzer.extract_class_name(target_code) or target_class_name
            
            # Estrae il metodo target per analizzare le chiamate
            metodo_originale = analyzer._estrai_metodo_da_classe(target_code, nome_metodo)
            
            if metodo_originale:
                # Estrae fields della classe target
                campi_classe = analyzer._estrai_campi_classe(target_code)
                fields_lines = []
                if campi_classe:
                    try:
                        tree = javalang.parse.parse(target_code)
                        lines = target_code.split('\n')
                        for path, node in tree:
                            if isinstance(node, javalang.tree.FieldDeclaration):
                                if hasattr(node, 'position') and node.position:
                                    start_line = node.position.line - 1
                                    end_line = start_line
                                    for i in range(start_line, len(lines)):
                                        if ';' in lines[i]:
                                            end_line = i
                                            break
                                    campo_codice = '\n'.join(lines[start_line:end_line + 1])
                                    fields_lines.append(campo_codice)
                    except Exception as e:
                        print(f"Error extracting target class fields: {e}")
                
                # Extract target class constructors (complete with implementation)
                costruttori_target = []
                try:
                    tree = javalang.parse.parse(target_code)
                    lines = target_code.split('\n')
                    for path, node in tree:
                        if isinstance(node, javalang.tree.ConstructorDeclaration):
                            if hasattr(node, 'position') and node.position:
                                start_line = node.position.line - 1
                                start_char = sum(len(lines[i]) + 1 for i in range(start_line))
                                brace_count = 0
                                in_constructor = False
                                end_char = len(target_code)
                                for i in range(start_char, len(target_code)):
                                    if target_code[i] == '{':
                                        brace_count += 1
                                        in_constructor = True
                                    elif target_code[i] == '}':
                                        brace_count -= 1
                                        if in_constructor and brace_count == 0:
                                            end_char = i + 1
                                            break
                                costruttore_completo = target_code[start_char:end_char]
                                costruttori_target.append(costruttore_completo)
                except Exception as e:
                    print(f"Error extracting target class constructors: {e}")
                
                # Analyze method calls to identify internal methods used
                dependencies = analyzer.analyze_class_dependencies(target_class_path)
                metodi_chiamati_per_classe = analyzer._analizza_chiamate_metodi(metodo_originale, dependencies, campi_classe, nome_classe_target=nome_classe_target)
                
                # Identifica metodi interni (chiamati sulla classe target stessa)
                # ESCLUDE il metodo target stesso dalla lista
                metodi_interni_nomi = metodi_chiamati_per_classe.get(nome_classe_target, set())
                metodi_interni_nomi = {m for m in metodi_interni_nomi if m != nome_metodo}  # Esclude il metodo target
                metodi_interni_implementazioni = []
                for metodo_nome_interno in metodi_interni_nomi:
                    metodo_completo = analyzer._estrai_metodo_da_classe(target_code, metodo_nome_interno)
                    if metodo_completo:
                        metodi_interni_implementazioni.append(metodo_completo)
                
                # Estrae import dalla classe originale
                import_lines = []
                try:
                    import_pattern = re.compile(r'^import\s+(?:static\s+)?[\w.*]+;', re.MULTILINE)
                    import_matches = import_pattern.findall(target_code)
                    if import_matches:
                        import_lines = import_matches
                except Exception as e:
                    print(f"Error extracting target class imports: {e}")
                
                # Build the CONTEXT - TARGET CLASS STRUCTURE section
                context_parts = []
                context_parts.append(f"CONTEXT - TARGET CLASS STRUCTURE:")
                context_parts.append(f"// === Original Class: {nome_classe_target} ===")
                
                # Sezione Import
                if import_lines:
                    context_parts.append("\n// --- Imports ---")
                    context_parts.extend(import_lines)
                
                # Sezione Fields
                if fields_lines:
                    context_parts.append("\n// --- Fields ---")
                    context_parts.extend(fields_lines)
                
                # Sezione Costruttori
                if costruttori_target:
                    context_parts.append("\n// --- Constructors ---")
                    context_parts.extend(costruttori_target)
                
                # Sezione Metodi interni usati
                if metodi_interni_implementazioni:
                    context_parts.append("\n// --- Internal Methods (used by target method) ---")
                    context_parts.extend(metodi_interni_implementazioni)
                
                context_parts.append("")  # Linea vuota finale
                context_target_class = "\n".join(context_parts)
    
    # Estrae dipendenze (solo metodi usati) se richiesto
    # Estrae dipendenze (solo metodi usati) se richiesto
    if not precomputed_context and usa_dipendenze and target_class_path and os.path.exists(target_class_path):
        print(f"\n=== DEPENDENCY EXTRACTION FOR REGENERATION ===")
        analyzer = JavaDependencyAnalyzer(project_root)
        
        # Analizza tutte le dipendenze
        dependencies = analyzer.analyze_class_dependencies(target_class_path)
        
        # Estrae solo i metodi effettivamente usati dal metodo originale
        metodi_estratti = analyzer.extract_methods_used_from_original_method(
            target_class_path=target_class_path,
            nome_metodo=nome_metodo,
            dependencies=dependencies
        )
        
        if metodi_estratti:
            context_parts = []
            for class_name, class_info in metodi_estratti.items():
                # Verifica se è la nuova struttura (dict) o la vecchia (string)
                if isinstance(class_info, dict):
                    # Nuova struttura con sezioni separate
                    nome_classe = class_info.get('nome_classe', class_name)
                    fields = class_info.get('fields', [])
                    costruttori = class_info.get('costruttori', [])
                    metodi = class_info.get('metodi', [])
                    
                    section_parts = []
                    section_parts.append(f"// === External class: {nome_classe} ===")
                    
                    # Sezione Fields
                    if fields:
                        section_parts.append("\n// --- Fields ---")
                        section_parts.extend(fields)
                    
                    # Sezione Costruttori
                    if costruttori:
                        section_parts.append("\n// --- Constructors ---")
                        section_parts.extend(costruttori)
                    
                    # Sezione Metodi usati
                    if metodi:
                        section_parts.append("\n// --- Methods used by the target method ---")
                        section_parts.extend(metodi)
                    
                    section_parts.append("")  # Linea vuota finale
                    context_parts.append("\n".join(section_parts))
                else:
                    # Vecchia struttura (string) - fallback per compatibilità
                    context_parts.append(f"// === Methods used from class: {class_name} ===\n{class_info}\n")
            
            context_dipendenze = "\n".join(context_parts)
            print(f"Extracted methods from {len(metodi_estratti)} dependent classes")
        else:
            print("No methods extracted from dependencies")
    
    # Extract exceptions declared by the target method and add context
    exception_context_section = ""
    try:
        if target_class_path and os.path.exists(target_class_path):
            codice_classe_target = leggi_file(target_class_path)
            
            analyzer = JavaDependencyAnalyzer(project_root)
            eccezioni = analyzer.estrai_eccezioni_metodo(codice_classe_target, nome_metodo, metodo_signature)
            
            if eccezioni:
                print(f"Method throws: {', '.join(eccezioni)}")
                exception_parts = []
                java_files = analyzer.find_java_files()
                
                for exception_name in eccezioni:
                    exception_info = analyzer.estrai_contesto_eccezione(exception_name, java_files)
                    
                    if exception_info and exception_info.get('costruttori'):
                        exception_parts.append(f"\n// === Exception Class: {exception_name} ===")
                        exception_parts.append(f"// NOTE: The target method throws this exception.")
                        exception_parts.append("// Constructors:")
                        for costruttore in exception_info['costruttori']:
                            exception_parts.append(costruttore)
                
                if exception_parts:
                    exception_context_section = "\n".join(exception_parts) + "\n"
    except Exception as e:
        print(f"   WARNING:  Error extracting exceptions for method generation: {e}")
    
    test_section = f"PROVIDED TESTS:\n{codice_test}\n" if codice_test.strip() else ""
    sezione_istruzioni_specifiche = costruisci_sezione_istruzioni_specifiche(prompt_rigenera_metodo)
    
    if precomputed_context:
        context_target_section = ""
        context_section = f"\nPROJECT CONTEXT:\n{precomputed_context}\n{exception_context_section}"
    else:
        context_target_section = f"\n{context_target_class}\n" if context_target_class else ""
        context_section = f"\nDEPENDENCIES (methods used by the original method):\n{context_dipendenze}\n{exception_context_section}" if context_dipendenze else (f"\n{exception_context_section}" if exception_context_section else "")


    

    # Estrai signature esatta dal metodo originale per forzarla nel prompt
    signature_esatta = f"public <return_type> {nome_metodo}(<parameters>)" # Default
    try:
        from core.evaluation.evaluation import estrai_metodo_singolo
        # Leggi codice classe target se non disponibile (target_class_path è passato come argomento)
        if target_class_path and os.path.exists(target_class_path):
            codice_target = leggi_file(target_class_path)
            
            metodo_orig = estrai_metodo_singolo(codice_target, nome_metodo, signature=metodo_signature)
            if metodo_orig:
                # Estrai signature fino alla prima graffa
                match = re.search(r'^(.*?)({|throws)', metodo_orig, re.DOTALL | re.MULTILINE)
                if match:
                    signature_part1 = match.group(1).strip()
                    if match.group(2) == 'throws':
                        match_throws = re.search(r'(throws.*?){', metodo_orig, re.DOTALL | re.MULTILINE)
                        if match_throws:
                            signature_esatta = signature_part1 + " " + match_throws.group(1).strip()
                        else:
                             signature_esatta = signature_part1
                    else:
                        signature_esatta = signature_part1
                
                # Rimuovi annotazioni
                lines = signature_esatta.split('\n')
                lines = [l.strip() for l in lines if not l.strip().startswith('@')]
                signature_esatta = ' '.join(lines)
    except Exception as e:
        print(f"WARNING: Error extracting exact signature: {e}")

    
    prompt = f"""ROLE:
You are an expert Java developer specialized in regenerating methods from JUnit tests.

{test_section}
{test_info_section}
{metodo_precedente_info}
{context_target_section}{context_section}
TASK:
Regenerate ONLY the method "{nome_metodo}" OF CLASS "{target_class_name}" by analyzing the provided tests.

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
- The regenerated method must pass all VALID tests (failing tests provide context only)
- Maintain the exact same method signature as the original (as shown in EXPECTED OUTPUT FORMAT)

CONSTRAINTS:
- Output ONLY the method code with its annotations (if any), signature, and body
- DO NOT include package declaration, import statements, or class declaration
- DO NOT include fields or constructors
- DO NOT generate helper methods - output ONLY the single method "{nome_metodo}"
– Allowed imports: exactly those already present in {target_class_name}
– Forbidden: new imports, wildcard imports, fully-qualified class references
– If something is missing: rewrite the logic, do not extend dependencies
- Provide ONLY Java code without explanations

{sezione_istruzioni_specifiche}"""

    salva_prompt_log(prompt, "Method_generation", target_class_name, versione, nome_esperimento, retry_count=retry_count, metodo_da_testare=nome_metodo, model_name=model_name)

    risposta_llm = provider_func(prompt, model_name)
    
    # Cattura i token usati per la generation metodo (Ollama Cloud o Groq)
    # retry_count indica l'iterazione di refinement (None = generation iniziale)
    _capture_llm_tokens("method_generation", refinement_iteration=retry_count)
    
    # Salva la risposta dell'LLM
    from utils.prompt.prompt_logger import salva_risposta_llm
    salva_risposta_llm(risposta_llm, "Method_generation", target_class_name, versione, retry_count=retry_count, nome_esperimento=nome_esperimento, metodo_da_testare=nome_metodo, model_name=model_name)
    
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
        raise ValueError("The regenerated method has an invalid syntax.")

    # 1. Backup del metodo originale (per completezza)
    metodo_originale_backup = backup_metodo_originale(target_class_path, nome_metodo, metodo_signature)
    
    # 2. NON modifichiamo il file qui. Restituiamo il codice per SafeClassInjector.
    test_info = {
        "valid_tests": locals().get("valid_tests", []),
        "invalid_tests": locals().get("invalid_tests", []),
        "metodo_originale_backup": metodo_originale_backup,  # Per metriche/ripristino
        "metodo_rigenerato_codice": nuovo_metodo  # Passiamo il codice per l'iniezione
    }

    # Ritorna il percorso (nominale), info e prompt
    return target_class_path, test_info, prompt
