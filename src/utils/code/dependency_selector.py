import math
import re
from typing import Dict, List, Tuple, Set
from sklearn.feature_extraction.text import TfidfVectorizer
from rank_bm25 import BM25Okapi
import networkx as nx
import javalang


def _tokenize(text: str) -> List[str]:
    return re.findall(r"[A-Za-z_][A-Za-z0-9_]+", text)


def _estrai_metodo_da_classe(codice_classe: str, nome_metodo: str) -> str:
    tree = javalang.parse.parse(codice_classe)
    lines = codice_classe.split('\n')
    for path, node in tree:
        if isinstance(node, javalang.tree.MethodDeclaration) and node.name == nome_metodo:
            if hasattr(node, 'position') and node.position:
                start_line = node.position.line - 1
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
    return ""


def _estrai_classi_usate(codice_metodo: str) -> Set[str]:
    classi = set()
    common_types = {'String', 'Integer', 'Boolean', 'Long', 'Double', 'Float', 
                    'List', 'Map', 'Set', 'ArrayList', 'HashMap', 'HashSet', 
                    'Optional', 'Object', 'boolean', 'int', 'long', 'double', 'float',
                    'System', 'ModelAndView'}
    
    if not codice_metodo:
        return classi
    
    codice_wrapped = f"class Temp {{\n{codice_metodo}\n}}"
    
    try:
        tree = javalang.parse.parse(codice_wrapped)
        for path, node in tree:
            if isinstance(node, javalang.tree.FormalParameter):
                if hasattr(node, 'type') and hasattr(node.type, 'name'):
                    base_type = node.type.name.split('<')[0].split('[')[0]
                    if base_type not in common_types:
                        classi.add(base_type)
            
            if isinstance(node, javalang.tree.VariableDeclarator):
                if hasattr(node, 'type') and hasattr(node.type, 'name'):
                    base_type = node.type.name.split('<')[0].split('[')[0]
                    if base_type not in common_types:
                        classi.add(base_type)
            
            if isinstance(node, javalang.tree.LocalVariableDeclaration):
                if hasattr(node, 'type') and hasattr(node.type, 'name'):
                    base_type = node.type.name.split('<')[0].split('[')[0]
                    if base_type not in common_types:
                        classi.add(base_type)
    except Exception:
        pass
    
    method_call_pattern = r'(?:this\.)?([a-z][a-zA-Z0-9_]*)\.[a-zA-Z]'
    method_calls = re.findall(method_call_pattern, codice_metodo)
    for var in method_calls:
        if len(var) > 2 and var[0].islower():
            class_name = var[0].upper() + var[1:]
            classi.add(class_name)
    
    return classi


def _filtra_per_classi_esatte(dependencies: Dict[str, str], classi_query: Set[str], target_class_name: str) -> Dict[str, str]:
    filtered = {}
    classi_query_lower = {c.lower() for c in classi_query}
    
    for cls_name, cls_code in dependencies.items():
        if cls_name == target_class_name:
            continue
        
        cls_name_lower = cls_name.lower()
        if cls_name_lower in classi_query_lower:
            filtered[cls_name] = cls_code
    
    return filtered


def _costruisci_query_ranking(codice_metodo: str) -> str:
    classi = _estrai_classi_usate(codice_metodo)
    
    invalid_terms = {'ystem', 'mview', 'view', 'model', 'temp', 'obj', 'val'}
    filtered_classi = {c for c in classi if len(c) > 2 and c.lower() not in invalid_terms}
    
    java_keywords = {'public', 'private', 'protected', 'static', 'final', 'void', 'int', 'long', 
                     'short', 'byte', 'char', 'boolean', 'float', 'double', 'String', 'if', 'else',
                     'for', 'while', 'do', 'switch', 'case', 'break', 'continue', 'try', 'catch',
                     'finally', 'throw', 'throws', 'return', 'new', 'class', 'interface', 'extends',
                     'implements', 'this', 'super', 'import', 'package', 'null', 'true', 'false'}
    
    system_methods = {'println', 'print', 'out', 'system', 'exit', 'gc', 'currentTimeMillis'}
    
    method_calls = re.findall(r'\.([a-z][a-zA-Z0-9_]*)\s*\(', codice_metodo)
    method_calls.extend(re.findall(r'\b([a-z][a-zA-Z0-9_]*)\s*\(', codice_metodo))
    
    metodi = set()
    for method in method_calls:
        method_lower = method.lower()
        if (not any(method.startswith(p) for p in ['get', 'set', 'is', 'has']) and
            method_lower not in system_methods and 
            method_lower not in invalid_terms and
            len(method) > 2):
            metodi.add(method)
    
    query_parts = sorted(filtered_classi) + sorted(metodi)
    return " ".join(query_parts)


def score_tfidf_cosine(dependencies: Dict[str, str], query: str) -> List[Tuple[str, float]]:
    if not dependencies:
        return []
    classes = list(dependencies.keys())
    docs = [dependencies[c] for c in classes]
    vectorizer = TfidfVectorizer(token_pattern=r"[A-Za-z_][A-Za-z0-9_]+", lowercase=False, norm="l2")
    X = vectorizer.fit_transform(docs)
    q = vectorizer.transform([query])
    scores_vec = (X @ q.T).toarray().ravel()
    scores = [(classes[i], float(max(s, 1e-6))) for i, s in enumerate(scores_vec)]
    scores.sort(key=lambda x: x[1], reverse=True)
    return scores


def score_bm25(dependencies: Dict[str, str], query: str) -> List[Tuple[str, float]]:
    if not dependencies:
        return []
    classes = list(dependencies.keys())
    tokenized_corpus = [_tokenize(dependencies[c]) for c in classes]
    bm25 = BM25Okapi(tokenized_corpus)
    query_tokens = _tokenize(query)
    scores_vec = bm25.get_scores(query_tokens)
    scores = [(classes[i], float(max(s, 1e-6))) for i, s in enumerate(scores_vec)]
    scores.sort(key=lambda x: x[1], reverse=True)
    return scores


def score_pagerank_like(dependencies: Dict[str, str]) -> List[Tuple[str, float]]:
    class_names = list(dependencies.keys())
    name_set = set(class_names)
    edges = []
    
    for cls, code in dependencies.items():
        imports = set()
        for line in code.splitlines():
            line = line.strip()
            if line.startswith("import ") and not line.startswith("import static"):
                match = re.search(r"import\s+([\w\.]+);?", line)
                if match:
                    imported = match.group(1).split(".")[-1]
                    if imported in name_set and imported != cls:
                        imports.add(imported)
        for dst in imports:
            edges.append((cls, dst))
    
    G = nx.DiGraph()
    G.add_nodes_from(class_names)
    G.add_edges_from(edges)
    
    for node in G.nodes:
        if node not in G[node]:
            G.add_edge(node, node)
    
    pr = nx.pagerank(G, alpha=0.85, max_iter=200)
    return sorted([(c, float(max(s, 1e-9))) for c, s in pr.items()], key=lambda x: x[1], reverse=True)


def _annotate_rankings(rankings: Dict[str, List[Tuple[str, float]]]) -> Dict[str, List[Dict[str, float]]]:
    annotated = {}
    for key, lst in rankings.items():
        if not lst:
            annotated[key] = []
            continue
        lst_sorted = sorted(lst, key=lambda x: x[1], reverse=True)
        scores = [float(s) for _, s in lst_sorted]
        max_s = max(scores)
        exp_scores = [math.exp(s - max_s) for s in scores]
        sum_exp = sum(exp_scores) or 1.0
        probs = [e / sum_exp for e in exp_scores]
        out = []
        for idx, ((cls, s), p) in enumerate(zip(lst_sorted, probs), start=1):
            out.append({"class_name": cls, "score": float(s), "norm_score": float(max(p, 1e-9)), "rank": idx})
        annotated[key] = out
    return annotated


def select_top_k(
    dependencies: Dict[str, str],
    target_class_name: str,
    nome_metodo: str,
    metodo: str = "hybrid",
    top_k: int = 10,
    weights: Tuple[float, float, float] = (0.45, 0.45, 0.10),
) -> Tuple[Dict[str, str], List[Tuple[str, float]], Dict[str, List[Dict[str, float]]]]:
    if not dependencies:
        return {}, [], {}
    
    if target_class_name not in dependencies:
        raise ValueError(f"La classe target '{target_class_name}' non è presente nelle dipendenze.")
    
    selected = {target_class_name: dependencies[target_class_name]}
    codice_classe_target = dependencies[target_class_name]
    codice_metodo = _estrai_metodo_da_classe(codice_classe_target, nome_metodo)
    
    if metodo == "exact":
        classi_query = _estrai_classi_usate(codice_metodo) if codice_metodo else set()
        filtered = _filtra_per_classi_esatte(dependencies, classi_query, target_class_name)
        ranked = [(cls, 1.0) for cls in filtered.keys()]
        rankings_all = {"exact": [{"class_name": cls, "score": 1.0, "norm_score": 1.0, "rank": i+1} 
                                  for i, cls in enumerate(filtered.keys())]}
    else:
        if not codice_metodo:
            query = target_class_name
        else:
            query = _costruisci_query_ranking(codice_metodo)
        
        print(f"Query: {query}")
        
        ranked_tfidf = score_tfidf_cosine(dependencies, query)
        ranked_bm25 = score_bm25(dependencies, query)
        ranked_pagerank = score_pagerank_like(dependencies)
        
        raw_rankings = {"tfidf": ranked_tfidf, "bm25": ranked_bm25, "pagerank": ranked_pagerank}
        
        # Calcola ranking hybrid se necessario, prima di annotare
        if metodo == "hybrid":
            all_scores = {}
            for name, weight in zip(["tfidf", "bm25", "pagerank"], weights):
                for cls, s in raw_rankings[name]:
                    all_scores[cls] = all_scores.get(cls, 0.0) + weight * s
            ranked_hybrid = sorted(all_scores.items(), key=lambda x: x[1], reverse=True)
            raw_rankings["hybrid"] = ranked_hybrid
        
        rankings_all = _annotate_rankings(raw_rankings)
        
        if metodo == "pagerank":
            ranked = ranked_pagerank
        elif metodo == "bm25":
            ranked = ranked_bm25
        elif metodo == "tfidf":
            ranked = ranked_tfidf
        else:
            # Usa il ranking hybrid già calcolato
            ranked = raw_rankings.get("hybrid", [])
    
    EPS = 1e-6
    external_count = 0
    for cls, score in ranked:
        if cls == target_class_name or score <= EPS:
            continue
        selected[cls] = dependencies[cls]
        external_count += 1
        if external_count >= top_k:
            break
    
    return selected, ranked, rankings_all

