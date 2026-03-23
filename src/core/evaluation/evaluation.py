import difflib
import re
import hashlib
import json
from typing import Dict, List, Any, Optional
from collections import Counter
import javalang
from crystalbleu import sentence_bleu, SmoothingFunction
import os
from utils.tracking.token_tracker import get_token_info_for_report, reset_token_info
from utils.tracking.naturalness_evaluator import calcola_naturalezza_test



def rimuovi_commenti_java(codice: str) -> str:
    """
    Rimuove tutti i commenti Java dal codice:
    - Commenti multi-linea: /* ... */
    - Commenti single-line: // ...
    - Commenti Javadoc: /** ... */
    """
    codice = re.sub(r"/\*[\s\S]*?\*/", "", codice)
    codice = re.sub(r"//.*$", "", codice, flags=re.MULTILINE)
    codice = re.sub(r"\n\s*\n\s*\n", "\n\n", codice)
    return codice



def estrai_metodo_singolo(codice: str, nome_metodo: str, include_javadoc: bool = False, signature: str = None) -> str:
    """
    Estrae un singolo metodo dal codice Java.
    """
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
            # Estrai il tipo dal nodo javalang
            param_type = param.type
            tipo_completo = ""
            
            # Gestisci il nome base del tipo e eventuali sottotipi (es: HandlingEvent.Type)
            if hasattr(param_type, 'name'):
                tipo_completo = param_type.name
                # Gestisci puntatori/inner classes (es: HandlingEvent.Type -> ReferenceType(name=HandlingEvent, sub_type=ReferenceType(name=Type)))
                current_type = param_type
                while hasattr(current_type, 'sub_type') and current_type.sub_type:
                    current_type = current_type.sub_type
                    if hasattr(current_type, 'name'):
                         tipo_completo += '.' + current_type.name

            else:
                tipo_completo = str(param_type)
            
            # Gestisci array (javalang usa dimensions)
            if hasattr(param_type, 'dimensions') and param_type.dimensions:
                tipo_completo += '[]' * len(param_type.dimensions)
            
            # Normalizza per confronto
            tipo_node = _normalizza_tipo(tipo_completo)
            tipo_expected = _normalizza_tipo(signature_types[i])
            
            if tipo_node != tipo_expected:
                return False
        return True
    
    # Estrai tipi parametri dalla signature se fornita
    signature_types = _estrai_tipi_parametri_da_signature(signature) if signature else None
    
    candidati = []
    
    try:
        tree = javalang.parse.parse(codice)
        lines = codice.split("\n")
        for path, node in tree:
            if (
                isinstance(node, javalang.tree.MethodDeclaration)
                and node.name == nome_metodo
            ):
                # Se signature fornita, verifica che i parametri corrispondano
                if signature_types is not None:
                    node_params = node.parameters if node.parameters else []
                    if not _matcha_parametri(node_params, signature_types):
                        continue  # Non corrisponde, cerca il prossimo
                
                if hasattr(node, "position") and node.position:
                    start_line = node.position.line - 1
                else:
                    continue
                
                # Se include_javadoc, risali per trovare JavaDoc e annotazioni
                javadoc_start_line = start_line
                if include_javadoc:
                    # Risali per trovare JavaDoc/annotazioni
                    for i in range(start_line - 1, -1, -1):
                        stripped = lines[i].strip()
                        if stripped.startswith('@') or stripped.startswith('*') or stripped.startswith('/*') or stripped.startswith('//') or stripped.endswith('*/') or stripped == '':
                            javadoc_start_line = i
                        elif stripped:
                            # Trovata una riga non vuota che non è annotazione/commento
                            break
                    # Rimuovi le righe vuote iniziali
                    while javadoc_start_line < start_line and lines[javadoc_start_line].strip() == '':
                        javadoc_start_line += 1
                
                effective_start_line = javadoc_start_line if include_javadoc else start_line
                start_char = sum(len(lines[i]) + 1 for i in range(effective_start_line))
                
                # Trova la fine del metodo dalla posizione originale (non JavaDoc)
                method_start_char = sum(len(lines[i]) + 1 for i in range(start_line))
                brace_count = 0
                in_method = False
                end_char = len(codice)
                for i in range(method_start_char, len(codice)):
                    if codice[i] == "{":
                        brace_count += 1
                        in_method = True
                    elif codice[i] == "}":
                        brace_count -= 1
                        if in_method and brace_count == 0:
                            end_char = i + 1
                            break
                
                metodo_estratto = codice[start_char:end_char]
                
                if signature_types is not None:
                    return metodo_estratto
                
                candidati.append(metodo_estratto)
        
        if candidati:
            # Restituisce il candidato più lungo (euristica per overload principale)
            return max(candidati, key=len)
            
    except Exception:
        pass
    
    # Fallback con regex (senza JavaDoc) - SOLO SE NON E' RICHIESTA SIGNATURE ESATTA
    if not signature:
        pattern = rf"(public|private|protected)?\s*(static)?\s*([\w\.<>\[\]]+)\s+({re.escape(nome_metodo)})\s*\([^)]*\)\s*\{{[^{{}}]*(?:\{{[^{{}}]*\}}[^{{}}]*)*\}}"
        match = re.search(pattern, codice, re.MULTILINE | re.DOTALL)
        if match:
            return match.group(0)
    
    return ""


def estrai_metodo_con_javadoc(codice: str, nome_metodo: str, signature: str = None) -> str:
    """
    Estrae un metodo CON i commenti JavaDoc e le annotazioni.
    Usato per il display nel report HTML.
    """
    return estrai_metodo_singolo(codice, nome_metodo, include_javadoc=True, signature=signature)



JAVA_SQL_STOPWORDS = {
    "public",
    "private",
    "protected",
    "static",
    "final",
    "void",
    "int",
    "long",
    "short",
    "byte",
    "char",
    "boolean",
    "new",
    "return",
    "if",
    "else",
    "for",
    "while",
    "do",
    "switch",
    "case",
    "break",
    "continue",
    "try",
    "catch",
    "finally",
    "throw",
    "throws",
    "class",
    "interface",
    "extends",
    "implements",
    "this",
    "super",
    "import",
    "package",
    "{",
    "}",
    "(",
    ")",
    ";",
    "=",
    ",",
    ".",
    "select",
    "from",
    "where",
    "in",
    "and",
    "or",
    "join",
    "on",
    "as",
    "order",
    "by",
    "group",
}


def split_identifiers(token: str) -> List[str]:
    parts = re.sub(r"([a-z0-9])([A-Z])", r"\1 \2", token)
    parts = parts.replace("_", " ")
    return [p.lower() for p in parts.split() if p]


def estrai_token_per_crystal(codice: str) -> List[str]:
    codice_pulito = rimuovi_commenti_java(codice)
    tokens = re.findall(r"\b[A-Za-z_][A-Za-z0-9_]*\b", codice_pulito)
    out = []
    for t in tokens:
        tl = t.lower()
        if tl in JAVA_SQL_STOPWORDS:
            continue
        subtokens = split_identifiers(t)
        for s in subtokens:
            if s and not s.isdigit():
                out.append(s)
    return out


def calcola_similarita_crystalbleu(
    codice1: str, codice2: str, use_stopwords: bool = True
) -> float:
    if use_stopwords:
        ref_tokens = estrai_token_per_crystal(codice1)
        hyp_tokens = estrai_token_per_crystal(codice2)
    else:
        ref_tokens = rimuovi_commenti_java(codice1).split()
        hyp_tokens = rimuovi_commenti_java(codice2).split()
    if not ref_tokens or not hyp_tokens:
        return 0.0
    try:
        smoothing = SmoothingFunction().method1
        score = sentence_bleu([ref_tokens], hyp_tokens, smoothing_function=smoothing)
        return float(score)
    except Exception:
        return difflib.SequenceMatcher(
            None, " ".join(ref_tokens), " ".join(hyp_tokens)
        ).ratio()


def estrai_token_java(codice: str) -> List[str]:
    codice_pulito = rimuovi_commenti_java(codice)
    tokens = re.findall(r"\b\w+\b|[{}();,=+\-*/<>!&|]", codice_pulito)
    return [t for t in tokens if len(t) > 1 and not t.isdigit()]


def calcola_similarita_token(codice1: str, codice2: str) -> float:
    tokens1 = set(estrai_token_java(codice1))
    tokens2 = set(estrai_token_java(codice2))
    if not tokens1 and not tokens2:
        return 1.0
    intersection = len(tokens1.intersection(tokens2))
    union = len(tokens1.union(tokens2))
    return intersection / union if union > 0 else 0.0


def analizza_ast_java(codice: str) -> Dict[str, Any]:
    """Analizza l'AST del codice Java e restituisce un dizionario con i conteggi degli elementi strutturali."""

    elementi = Counter(
        {
            "metodi": 0,
            "variabili": 0,
            "if_statements": 0,
            "for_loops": 0,
            "while_loops": 0,
            "return_statements": 0,
            "method_invocations": 0,
            "assignments": 0,
        }
    )

    # Rimuovi commenti prima di analizzare l'AST per evitare che influenzino il parsing
    codice_pulito = rimuovi_commenti_java(codice)
    codice_da_parsare = codice_pulito.strip()
    if not codice_da_parsare.startswith(
        ("package", "import", "public class", "class", "private class")
    ):
        codice_da_parsare = f"public class TempClass {{\n{codice_da_parsare}\n}}"

    try:
        tree = javalang.parse.parse(codice_da_parsare)

        for path, node in tree:
            if isinstance(node, javalang.tree.MethodDeclaration):
                elementi["metodi"] += 1
            elif isinstance(node, javalang.tree.VariableDeclaration):
                elementi["variabili"] += 1
            elif isinstance(node, javalang.tree.LocalVariableDeclaration):
                elementi["variabili"] += 1
            elif isinstance(node, javalang.tree.IfStatement):
                elementi["if_statements"] += 1
            elif isinstance(node, javalang.tree.ForStatement):
                elementi["for_loops"] += 1
            elif isinstance(node, javalang.tree.WhileStatement):
                elementi["while_loops"] += 1
            elif isinstance(node, javalang.tree.ReturnStatement):
                elementi["return_statements"] += 1
            elif isinstance(node, javalang.tree.MethodInvocation):
                elementi["method_invocations"] += 1
            elif isinstance(node, javalang.tree.Assignment):
                elementi["assignments"] += 1

        return dict(elementi)
    except Exception:
        # Se il parsing con wrapper fallisce, prova senza wrapper (usa codice pulito)
        try:
            tree = javalang.parse.parse(codice_pulito)
            for path, node in tree:
                if isinstance(node, javalang.tree.IfStatement):
                    elementi["if_statements"] += 1
                elif isinstance(node, javalang.tree.MethodDeclaration):
                    elementi["metodi"] += 1
                elif isinstance(node, javalang.tree.VariableDeclaration) or isinstance(
                    node, javalang.tree.LocalVariableDeclaration
                ):
                    elementi["variabili"] += 1
                elif isinstance(node, javalang.tree.ReturnStatement):
                    elementi["return_statements"] += 1
                elif isinstance(node, javalang.tree.MethodInvocation):
                    elementi["method_invocations"] += 1
                elif isinstance(node, javalang.tree.Assignment):
                    elementi["assignments"] += 1
            return dict(elementi)
        except Exception:
            # Se il parsing fallisce completamente, restituisce un dizionario di default
            return {
                "metodi": 1,
                "variabili": 0,
                "if_statements": 0,
                "for_loops": 0,
                "while_loops": 0,
                "return_statements": 0,
                "method_invocations": 0,
                "assignments": 0,
            }


def calcola_similarita_ast(codice1: str, codice2: str) -> float:
    """Calcola la similarità strutturale AST tra due metodi Java."""
    ast1 = analizza_ast_java(codice1)
    ast2 = analizza_ast_java(codice2)

    common, total = 0, 0

    keys_to_compare = set(ast1.keys()) | set(ast2.keys())
    keys_to_compare.discard("metodi")

    for key in keys_to_compare:
        val1 = ast1.get(key, 0)
        val2 = ast2.get(key, 0)
        common += min(val1, val2)
        total += max(val1, val2)

    return common / total if total > 0 else 0.0


    return common / total if total > 0 else 0.0


def calcola_complexity_loc(codice: str) -> Dict[str, int]:
    """
    Calcola Lines of Code (LOC), Source Lines of Code (SLOC) e Complessità Ciclomatica.
    """
    if not codice:
        return {"loc": 0, "sloc": 0, "complexity": 1}

    # Calcolo LOC (totale righe)
    lines = codice.splitlines() if isinstance(codice, str) else []
    
    loc = len(lines)
    
    # Calcolo SLOC (esclude righe vuote e commenti single-line banali)
    sloc = 0
    in_block_comment = False
    for line in lines:
        line = line.strip()
        if not line:
            continue
        
        # Gestione commenti multi-linea semplice
        if "/*" in line and "*/" in line:
            if not line.startswith("/") and not line.startswith("*"):
                 sloc += 1
            continue
            
        if "/*" in line:
            in_block_comment = True
            if not line.startswith("/*"): # Codice prima del commento
                sloc += 1
            continue
            
        if "*/" in line:
            in_block_comment = False
            if not line.endswith("*/"): # Codice dopo il commento
                sloc += 1
            continue
            
        if in_block_comment:
            continue
            
        if line.startswith("//"):
            continue
            
        sloc += 1

    # Calcolo Complexity
    complexity = 1
    try:
        # Usa il parser javalang
        codice_pulito = rimuovi_commenti_java(codice)
        
        # Wrapper per parsing se necessario
        codice_da_parsare = codice_pulito.strip()
        if not codice_da_parsare.startswith(("package", "import", "class", "public", "protected", "private", "interface", "enum")):
             codice_da_parsare = f"public class TempClassWrapper {{ {codice_da_parsare} }}"
             
        try:
            tree = javalang.parse.parse(codice_da_parsare)
        except javalang.parser.JavaSyntaxError:
            try:
                tree = javalang.parse.parse_member_declaration(codice_da_parsare)
            except:
                return {"loc": loc, "sloc": sloc, "complexity": _calcola_complexity_regex(codice)}

        if isinstance(tree, list):
             nodes = tree
        else:
             nodes = [tree]
             
        for root_node in nodes:
            if not root_node: continue
            
            # Conta decision points
            if hasattr(root_node, 'filter'):
                for path, node in root_node.filter(javalang.tree.Node):
                    if isinstance(node, (javalang.tree.IfStatement, 
                                         javalang.tree.WhileStatement, 
                                         javalang.tree.DoStatement, 
                                         javalang.tree.ForStatement,
                                         javalang.tree.CatchClause,
                                         javalang.tree.ThrowStatement)):
                        complexity += 1
                    
                    if type(node).__name__ == 'TernaryExpression' or type(node).__name__ == 'ConditionalExpression':
                        complexity += 1

                    elif isinstance(node, javalang.tree.BinaryOperation):
                         if node.operator in ('&&', '||'):
                             complexity += 1
                             
                    elif isinstance(node, javalang.tree.SwitchStatement):
                         if hasattr(node, 'cases') and node.cases:
                             for case in node.cases:
                                  complexity += 1
    except Exception as e:
        print(f"Error calculating complexity AST: {e}, using regex fallback.")
        complexity = _calcola_complexity_regex(codice)
        
    return {"loc": loc, "sloc": sloc, "complexity": complexity}

def _calcola_complexity_regex(codice: str) -> int:
    """Fallback approssimativo con regex"""
    complexity = 1
    # Use raw strings with single backslash for word boundary
    keywords = [r'\bif\b', r'\bfor\b', r'\bwhile\b', r'\bcase\b', r'\bcatch\b', r'\bthrow\b', r'&&', r'\|\|', r'\?']
    for pattern in keywords:
        complexity += len(re.findall(pattern, codice))
    return complexity


def calcola_soglia_dinamica(sloc: int, base: float = 0.70) -> float:
    """
    Calcola la soglia di similarità dinamica basata sulle SLOC del metodo originale.
    
    - Metodi piccoli (< 10 SLOC): soglia più alta (~0.80) - meno variabilità implementativa
    - Metodi medi (~20 SLOC): soglia base (0.70)
    - Metodi grandi (> 50 SLOC): soglia più bassa (~0.60) - più variabilità stilistica
    
    Args:
        sloc: Source Lines of Code (esclude commenti e righe vuote)
        base: Soglia base di partenza (default 0.70)
    
    Returns:
        Soglia di similarità (tra 0.55 e 0.80)
    """
    import math

    if sloc <= 1:
        return 0.80

    decremento = 0.05 * math.log10(max(sloc, 10) / 10)
    soglia = base - decremento

    return min(0.80, max(0.55, soglia))

_unixcoder_tokenizer = None
_unixcoder_model = None


def _get_unixcoder():
    """
    Carica UniXcoder solo quando necessario.
    """
    global _unixcoder_tokenizer, _unixcoder_model
    if _unixcoder_tokenizer is None or _unixcoder_model is None:
        try:
            from transformers import AutoTokenizer, AutoModel

            model_name = "microsoft/unixcoder-base"
            _unixcoder_tokenizer = AutoTokenizer.from_pretrained(model_name)
            _unixcoder_model = AutoModel.from_pretrained(model_name)
            _unixcoder_model.eval()
        except Exception as e:
            print(f"Warning: UniXcoder not available: {e}")
            return None, None
    return _unixcoder_tokenizer, _unixcoder_model


def calcola_similarita_embedding(codice1: str, codice2: str) -> float:
    """
    Calcola la similarità semantica tra due metodi usando embeddings UniXcoder.
    
    Restituisce cosine similarity tra 0 e 1.
    """
    tokenizer, model = _get_unixcoder()

    if tokenizer is None or model is None:
        raise RuntimeError(
            "UniXcoder non disponibile. Installare transformers e torch:\n"
            "  pip install transformers torch\n"
            "L'esperimento non può continuare senza embedding similarity."
        )

    try:
        import torch
        from torch.nn.functional import cosine_similarity

        def get_embedding(codice):
            """
            Calcola embedding usando UniXcoder.
            Usa sliding window per metodi più lunghi di 512 token.
            Usa il token [CLS] per la rappresentazione del codice.
            """
            max_length = 512
            
            # Tokenizza il codice completo per vedere quanti token ha
            tokens = tokenizer.encode(codice, add_special_tokens=False)
            
            # Se il codice è corto, processa normalmente
            if len(tokens) <= max_length - 2:  # -2 per [CLS] e [SEP]
                inputs = tokenizer(
                    codice,
                    return_tensors="pt",
                    truncation=True,
                    max_length=max_length,
                    padding=True,
                )
                
                with torch.no_grad():
                    outputs = model(**inputs)
                    # Usa l'embedding del token [CLS] (primo token)
                    cls_embedding = outputs.last_hidden_state[:, 0, :]
                    # Normalizza l'embedding
                    cls_embedding = torch.nn.functional.normalize(cls_embedding, p=2, dim=1)
                    return cls_embedding
            
            # Se il codice è più lungo, usa sliding window
            block_embeddings = []
            stride = (max_length - 2) // 2  # Overlap del 50%
            max_tokens_per_block = max_length - 2  # Spazio per [CLS] e [SEP]
            
            # Token speciali
            cls_token_id = tokenizer.cls_token_id if tokenizer.cls_token_id is not None else 0
            sep_token_id = tokenizer.sep_token_id if tokenizer.sep_token_id is not None else 2
            pad_token_id = tokenizer.pad_token_id if tokenizer.pad_token_id is not None else 1
            
            for start_idx in range(0, len(tokens), stride):
                end_idx = min(start_idx + max_tokens_per_block, len(tokens))
                block_tokens = tokens[start_idx:end_idx]
                
                # Aggiungi [CLS] e [SEP]
                input_ids = [cls_token_id] + block_tokens + [sep_token_id]
                
                # Padding se necessario
                attention_mask = [1] * len(input_ids)
                if len(input_ids) < max_length:
                    padding_length = max_length - len(input_ids)
                    input_ids = input_ids + [pad_token_id] * padding_length
                    attention_mask = attention_mask + [0] * padding_length
                
                # Converte in tensor
                input_ids_tensor = torch.tensor([input_ids], dtype=torch.long)
                attention_mask_tensor = torch.tensor([attention_mask], dtype=torch.long)
                
                with torch.no_grad():
                    outputs = model(
                        input_ids=input_ids_tensor,
                        attention_mask=attention_mask_tensor
                    )
                    # Usa l'embedding del token [CLS]
                    cls_embedding = outputs.last_hidden_state[:, 0, :]
                    block_embeddings.append(cls_embedding)
                
                # Se abbiamo raggiunto la fine, termina
                if end_idx >= len(tokens):
                    break
            
            # Media degli embeddings di tutti i blocchi
            if block_embeddings:
                stacked = torch.cat(block_embeddings, dim=0)
                mean_embedding = stacked.mean(dim=0, keepdim=True)
                # Normalizza l'embedding medio
                mean_embedding = torch.nn.functional.normalize(mean_embedding, p=2, dim=1)
                return mean_embedding
            else:
                # Fallback (non dovrebbe mai accadere)
                inputs = tokenizer(
                    codice,
                    return_tensors="pt",
                    truncation=True,
                    max_length=max_length,
                    padding=True,
                )
                with torch.no_grad():
                    outputs = model(**inputs)
                    cls_embedding = outputs.last_hidden_state[:, 0, :]
                    cls_embedding = torch.nn.functional.normalize(cls_embedding, p=2, dim=1)
                    return cls_embedding

        emb1 = get_embedding(codice1)
        emb2 = get_embedding(codice2)

        # Calcola cosine similarity
        similarity = cosine_similarity(emb1, emb2).item()
        
        # Con UniXcoder e CLS token, la similarity è più discriminativa.
        # Assicuriamo range [0, 1]
        similarity = max(0.0, min(1.0, similarity))
        
        return float(similarity)

    except ImportError as e:
        raise RuntimeError(
            f"Dipendenze mancanti per embedding similarity: {e}\n"
            "Installare: pip install transformers torch\n"
            "L'esperimento non può continuare senza embedding similarity."
        )
    except Exception as e:
        raise RuntimeError(
            f"Errore nel calcolo di embedding similarity: {e}\n"
            "L'esperimento non può continuare senza embedding similarity."
        ) from e


def rileva_cloni_semantici(codice1: str, codice2: str) -> Dict[str, Any]:
    hash1, hash2 = (
        hashlib.md5(codice1.encode()).hexdigest(),
        hashlib.md5(codice2.encode()).hexdigest(),
    )
    window_size = 50
    blocks1 = [
        codice1[i : i + window_size]
        for i in range(0, len(codice1) - window_size + 1, window_size // 2)
    ]
    blocks2 = [
        codice2[i : i + window_size]
        for i in range(0, len(codice2) - window_size + 1, window_size // 2)
    ]
    similar_blocks = sum(
        1
        for b1 in blocks1
        for b2 in blocks2
        if difflib.SequenceMatcher(None, b1, b2).ratio() > 0.8
    )
    total_blocks = len(blocks1) + len(blocks2)
    clone_percentage = (similar_blocks * 2) / total_blocks if total_blocks > 0 else 0
    return {
        "exact_match": hash1 == hash2,
        "clone_percentage": clone_percentage,
        "similar_blocks": similar_blocks,
        "total_blocks": total_blocks,
    }


def confronta_metodi(
    metodo_originale: str,
    metodo_rigenerato: str,
    soglia_similarita: float = 0.7,
    cb_use_stopwords: bool = True,
) -> Dict[str, Any]:
    metodo_originale_pulito = rimuovi_commenti_java(metodo_originale)
    metodo_rigenerato_pulito = rimuovi_commenti_java(metodo_rigenerato)

    metriche = {
        "crystalbleu_similarity": calcola_similarita_crystalbleu(
            metodo_originale_pulito, metodo_rigenerato_pulito, cb_use_stopwords
        ),
        "string_similarity": difflib.SequenceMatcher(
            None, metodo_originale_pulito, metodo_rigenerato_pulito
        ).ratio(),
        "token_similarity": calcola_similarita_token(
            metodo_originale_pulito, metodo_rigenerato_pulito
        ),
        "ast_similarity": calcola_similarita_ast(
            metodo_originale_pulito, metodo_rigenerato_pulito
        ),
        "embedding_similarity": calcola_similarita_embedding(
            metodo_originale_pulito, metodo_rigenerato_pulito
        ),
        "clone_detection": rileva_cloni_semantici(
            metodo_originale_pulito, metodo_rigenerato_pulito
        ),
    }

    # PESI BILANCIATI PER LE METRICHE
    weights = {
        "crystalbleu_similarity": 0.20,
        "string_similarity": 0.08,
        "token_similarity": 0.15,
        "ast_similarity": 0.22,
        "embedding_similarity": 0.35,  # Peso alto per similarità semantica
    }

    # Normalizza tutti i valori a [0, 1]
    for key in [
        "crystalbleu_similarity",
        "string_similarity",
        "token_similarity",
        "ast_similarity",
        "embedding_similarity",
    ]:
        if key in metriche:
            metriche[key] = max(0.0, min(1.0, float(metriche[key])))

    overall_similarity = sum(metriche[k] * weights[k] for k in weights if k in metriche)
    overall_similarity = max(0.0, min(1.0, overall_similarity))

    metriche["weights"] = weights
    metriche["overall_similarity"] = overall_similarity
    metriche["passes_threshold"] = overall_similarity >= soglia_similarita
    return metriche


def _remove_refinement_prompts_from_retry_history(
    retry_history: Optional[List[Dict]], remove_methods: bool = False
) -> List[Dict]:
    """
    Rimuove solo i refinement_prompt e righe_non_coperte dal retry_history prima di salvare nel JSON.
    MANTIENE TUTTO IL RESTO: metodi originali/rigenerati, metriche di similarità, coverage, test results.
    Questo permette di tracciare l'intera storia di refinement/repair nel JSON.
    Rimuove entry duplicate e entry non necessarie (repair se non ci sono errori di compilazione).
    """
    if not retry_history:
        return []

    cleaned_history = []
    seen_types = {"repair": False, "refinement": False}

    for retry in retry_history:
        retry_type = retry.get("type", "unknown")

        # Rimuovi entry duplicate (stesso tipo e stessi risultati)
        is_duplicate = False
        for existing in cleaned_history:
            if (
                existing.get("type") == retry_type
                and existing.get("test_results") == retry.get("test_results")
                and existing.get("original_test_results")
                == retry.get("original_test_results")
                and existing.get("regenerated_test_results")
                == retry.get("regenerated_test_results")
            ):
                is_duplicate = True
                break

        if is_duplicate:
            continue

        # Traccia se ci sono stati repair/refinement
        if retry_type == "repair":
            seen_types["repair"] = True
        elif retry_type == "refinement":
            seen_types["refinement"] = True

        if retry_type == "repair":
            has_comp_errors = retry.get("has_compilation_errors", False) or retry.get(
                "test_results", {}
            ).get("has_compilation_errors", False)
            errori_comp = retry.get("compilation_errors")
            if not has_comp_errors and not errori_comp:
                continue

        cleaned_retry = retry.copy()
        cleaned_retry.pop("refinement_prompt", None)
        cleaned_retry.pop("righe_non_coperte", None)

        cleaned_history.append(cleaned_retry)

    return cleaned_history


def salva_metriche_esperimento(
    originale_path: str,
    rigenerata_path: str,
    metodo_originale: str,
    metodo_rigenerato: str,
    output_path: str,
    nome_metodo: str,
    soglia_similarita: float,
    fasi_eseguite: dict = None,
    versione: str = "1",
    prompt_test: str = "",
    prompt_rigenera: str = "",
    selezione_info: dict = None,
    test_content: str = "",
    cb_use_stopwords: bool = True,
    test_results_info: dict = None,
    nome_esperimento: str = None,
    descrizione_esperimento: str = None,
    provider_nome: str = None,
    model_name: str = None,
    prompt_test_completo: str = None,
    prompt_rigenera_completo: str = None,
    retry_history: list = None,
    soglia_coverage: float = None,
    test_file_path: str = None,
    metodo_originale_con_javadoc: str = None,  # Per il display nel report (con commenti)
    usa_soglia_dinamica: bool = True,
) -> None:
    metriche = {}
    passa_soglia = False
    if metodo_originale and metodo_rigenerato:
        metriche = confronta_metodi(
            metodo_originale, metodo_rigenerato, soglia_similarita, cb_use_stopwords
        )
        passa_soglia = metriche.get("overall_similarity", 0) >= soglia_similarita

    # Crea la directory se necessario
    os.makedirs(os.path.dirname(output_path), exist_ok=True)

    # Carica dati esistenti se il file esiste già (per aggiungere versioni invece di sovrascrivere)
    dati_esistenti = {}
    if os.path.exists(output_path):
        try:
            with open(output_path, "r", encoding="utf-8") as f:
                dati_esistenti = json.load(f)
        except:
            dati_esistenti = {}

    # Inizializza struttura se non esiste
    if "experiments" not in dati_esistenti:
        dati_esistenti["experiments"] = {}

    fasi_info = fasi_eseguite or {"first_phase": False, "second_phase": False}

    esperimento_data = {
        "tested_method": nome_metodo,
        "similarity_threshold": soglia_similarita,
        "passes_evaluation": passa_soglia,
        "timestamp": __import__("datetime").datetime.now().isoformat(),
        "executed_phases": fasi_info,
        "usa_soglia_dinamica": usa_soglia_dinamica,
        "metrics": metriche,
        "test_results": test_results_info or {},
        "test_filtering": test_results_info.get("regenerated", {}).get(
            "test_filtering", {}
        )
        if test_results_info and "regenerated" in test_results_info
        else {},
        "context": {"dependency_selection": selezione_info or {"active": False}},
        "paths": {
            "original_file": originale_path,
            "regenerated_file": rigenerata_path,
        },
        "original_metrics": calcola_complexity_loc(metodo_originale),
        "retry_history": _remove_refinement_prompts_from_retry_history(
            retry_history, remove_methods=True
        )
        if retry_history
        else [],
        # Salva i metodi per il report HTML (originale con JavaDoc, rigenerato senza)
        "original_method_display": metodo_originale_con_javadoc or metodo_originale,
        "regenerated_method_display": metodo_rigenerato,
    }
    
    # Aggiungi informazioni sui token usati (solo per Ollama Cloud)
    token_info = get_token_info_for_report()
    if token_info:
        esperimento_data["token_usage"] = token_info
        # Reset token info dopo averla salvata
        reset_token_info()
    
    # Calcola metriche di naturalezza per i test finali
    if test_file_path and os.path.exists(test_file_path):
        try:
            with open(test_file_path, "r", encoding="utf-8") as f:
                test_code = f.read()
            naturalezza = calcola_naturalezza_test(test_code, nome_metodo)
            esperimento_data["test_naturalness"] = naturalezza
        except Exception as e:
            print(f"WARNING: Error in calculating naturalness: {e}")

    # Add or update the version (does not overwrite other versions)
    dati_esistenti["experiments"][f"version_{versione}"] = esperimento_data

    # Update general info
    info_generale = {
        "tested_class": os.path.basename(originale_path).replace(".java", ""),
        "tested_method": nome_metodo,
        "last_update": __import__("datetime").datetime.now().isoformat(),
        "usa_soglia_dinamica": usa_soglia_dinamica,
    }

    # Aggiungi informazioni YAML se disponibili
    if descrizione_esperimento:
        info_generale["description"] = descrizione_esperimento
    if provider_nome:
        info_generale["provider"] = provider_nome
    if model_name:
        info_generale["model"] = model_name
    if soglia_similarita is not None:
        info_generale["similarity_threshold"] = soglia_similarita
    if soglia_coverage is not None and soglia_coverage > 0:
        info_generale["soglia_coverage"] = soglia_coverage

    dati_esistenti["general_info"] = info_generale

    with open(output_path, "w", encoding="utf-8") as f:
        json.dump(dati_esistenti, f, indent=2, ensure_ascii=False)


def aggiorna_metriche_esperimento(
    originale_path: str,
    rigenerata_path: str,
    metodo_originale: str,
    metodo_rigenerato: str,
    output_path: str,
    nome_metodo: str,
    soglia_similarita: float,
    versione: str,
    fasi_eseguite: dict = None,
    prompt_test: str = "",
    prompt_rigenera: str = "",
    selezione_info: dict = None,
    test_content: str = "",
    cb_use_stopwords: bool = True,
    test_results_info: dict = None,
    prompt_test_completo: str = None,
    prompt_rigenera_completo: str = None,
    retry_history: list = None,
    soglia_coverage: float = None,
    model_name: str = None,
    test_file_path: str = None,
    metodo_originale_con_javadoc: str = None,  # Per il display nel report (con commenti)
    usa_soglia_dinamica: bool = True,
) -> None:
    metriche = {}
    passa_soglia = False
    if metodo_originale and metodo_rigenerato:
        metriche = confronta_metodi(
            metodo_originale, metodo_rigenerato, soglia_similarita, cb_use_stopwords
        )
        passa_soglia = metriche.get("overall_similarity", 0) >= soglia_similarita

    dati_esistenti = {}

    if os.path.exists(output_path):
        try:
            with open(output_path, "r", encoding="utf-8") as f:
                dati_esistenti = json.load(f)
        except:
            dati_esistenti = {}

    if "experiments" not in dati_esistenti:
        dati_esistenti["experiments"] = {}

    fasi_info = fasi_eseguite or {"first_phase": False, "second_phase": False}

    esperimento_data = {
        "tested_method": nome_metodo,
        "similarity_threshold": soglia_similarita,
        "passes_evaluation": passa_soglia,
        "timestamp": __import__("datetime").datetime.now().isoformat(),
        "executed_phases": fasi_info,
        "usa_soglia_dinamica": usa_soglia_dinamica,
        "metrics": metriche,
        "test_results": test_results_info or {},
        "test_filtering": test_results_info.get("regenerated", {}).get(
            "test_filtering", {}
        )
        if test_results_info and "regenerated" in test_results_info
        else {},
        "context": {"dependency_selection": selezione_info or {"active": False}},
        "paths": {
            "original_file": originale_path,
            "regenerated_file": rigenerata_path,
        },
        "original_metrics": calcola_complexity_loc(metodo_originale),
        "retry_history": _remove_refinement_prompts_from_retry_history(
            retry_history, remove_methods=True
        )
        if retry_history
        else [],
        # Salva i metodi per il report HTML (originale con JavaDoc, rigenerato senza)
        "original_method_display": metodo_originale_con_javadoc or metodo_originale,
        "regenerated_method_display": metodo_rigenerato,
    }
    
    # Aggiungi informazioni sui token usati (solo per Ollama Cloud)
    token_info = get_token_info_for_report()
    if token_info:
        esperimento_data["token_usage"] = token_info
        # Reset token info dopo averla salvata
        reset_token_info()

    # Calcola metriche di naturalezza per i test finali
    naturalezza = None
    if test_file_path and os.path.exists(test_file_path):
        try:
            with open(test_file_path, "r", encoding="utf-8") as f:
                test_code = f.read()
            naturalezza = calcola_naturalezza_test(test_code, nome_metodo)
            esperimento_data["test_naturalness"] = naturalezza
        except Exception as e:
            print(f"WARNING: Error in calculating naturalness: {e}")

    if f"version_{versione}" in dati_esistenti["experiments"]:
        esperimento_existente = dati_esistenti["experiments"][f"version_{versione}"]
        # Update existing fields
        esperimento_existente.update(
            {
                "tested_method": nome_metodo,
                "similarity_threshold": soglia_similarita,
                "passes_evaluation": passa_soglia,
                "timestamp": __import__("datetime").datetime.now().isoformat(),
                "executed_phases": fasi_info,
                "metrics": metriche,
                "paths": {
                    "original_file": originale_path,
                    "regenerated_file": rigenerata_path,
                },
                "original_metrics": calcola_complexity_loc(metodo_originale),
            }
        )
        # Aggiorna test_results se forniti
        if test_results_info:
            if "test_results" not in esperimento_existente:
                esperimento_existente["test_results"] = {}
            esperimento_existente["test_results"].update(test_results_info)
        # Aggiorna retry_history se fornito (senza refinement_prompt e senza metodi)
        if retry_history is not None:
            esperimento_existente["retry_history"] = (
                _remove_refinement_prompts_from_retry_history(
                    retry_history, remove_methods=False
                )
            )
        # Aggiorna token_usage se disponibile (solo per Ollama Cloud)
        if token_info:
            esperimento_existente["token_usage"] = token_info
        # Aggiorna naturalezza se calcolata
        if naturalezza:
            esperimento_existente["test_naturalness"] = naturalezza
    else:
        dati_esistenti["experiments"][f"version_{versione}"] = esperimento_data

    # Aggiorna info generale mantenendo i valori esistenti
    if "general_info" not in dati_esistenti:
        dati_esistenti["general_info"] = {}

    info_generale = dati_esistenti["general_info"]
    info_generale.update(
        {
            "tested_class": os.path.basename(originale_path).replace(".java", ""),
            "tested_method": nome_metodo,
            "last_update": __import__("datetime").datetime.now().isoformat(),
            "usa_soglia_dinamica": usa_soglia_dinamica,
        }
    )

    # Aggiungi soglia_coverage se specificata
    if soglia_coverage is not None and soglia_coverage > 0:
        info_generale["soglia_coverage"] = soglia_coverage
    
    if model_name:
        info_generale["model"] = model_name

    dati_esistenti["general_info"] = info_generale

    with open(output_path, "w", encoding="utf-8") as f:
        json.dump(dati_esistenti, f, indent=2, ensure_ascii=False)


def salva_metriche_solo_originale(
    test_results_originale: dict,
    target_class_path: str,
    output_dir: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    provider_nome: str,
    versione_esperimento: str,
    soglia_similarita: float,
    prompt_test_metodo: str,
    selezione_info: dict,
    test_generated_content: str,
    cb_use_stopwords: bool,
    nome_esperimento: str = None,
    descrizione_esperimento: str = None,
    model_name: str = None,
    prompt_test_completo: str = None,
    retry_history: list = None,
    soglia_coverage: float = None,
    usa_soglia_dinamica: bool = True,
):
    """Salva le metriche quando viene eseguita solo la prima fase"""
    from utils.reporting.report_generator import genera_report_html

    # Crea cartella per esperimento se specificato
    # Sanitizza model_name se presente
    if model_name:
        model_name = model_name.replace(":", "_").replace("/", "_").replace("\\", "_")

    # Crea cartella per esperimento se specificato
    if nome_esperimento:
        if model_name:
            metrics_dir = os.path.join(output_dir, "metrics", nome_esperimento, model_name)
        else:
            metrics_dir = os.path.join(output_dir, "metrics", nome_esperimento)
    else:
        if model_name:
            metrics_dir = os.path.join(output_dir, "metrics", model_name)
        else:
            metrics_dir = os.path.join(output_dir, "metrics")
    os.makedirs(metrics_dir, exist_ok=True)
    metrics_path = os.path.join(
        metrics_dir,
        f"Experiment_{nome_classe_solo}_{metodo_da_testare}_{provider_nome}.json",
    )

    # Estrai test_info con tutti i campi necessari
    test_info_orig = test_results_originale.get(
        "test_info", {"valid_tests": [], "invalid_tests": [], "failed_assert_tests": [], "runtime_error_tests": []}
    )
    
    test_results_info = {
        "original": {
            "success": test_results_originale["success"],
            "tests_passed": test_results_originale["tests_passed"],
            "tests_total": test_results_originale["tests_total"],
            "tests_failed": test_results_originale.get("tests_total", 0)
            - test_results_originale.get("tests_passed", 0),
            "line_coverage": test_results_originale["line_coverage"],
            "branch_coverage": test_results_originale["branch_coverage"],
            "has_compilation_errors": test_results_originale.get(
                "has_compilation_errors", False
            ),
            # error_test_names rimosso - usare solo test_info
            "test_info": {
                "valid_tests": test_info_orig.get("valid_tests", []),
                "invalid_tests": test_info_orig.get("invalid_tests", []),
                "failed_assert_tests": test_info_orig.get("failed_assert_tests", []),
                "runtime_error_tests": test_info_orig.get("runtime_error_tests", []),
            },
        }
    }

    fasi_eseguite = {"first_phase": True, "second_phase": False}
    salva_metriche_esperimento(
        target_class_path,
        target_class_path,
        "",
        "",
        metrics_path,
        metodo_da_testare,
        soglia_similarita,
        fasi_eseguite,
        versione_esperimento,
        prompt_test_metodo,
        "",
        selezione_info,
        test_generated_content,
        cb_use_stopwords,
        test_results_info,
        nome_esperimento,
        descrizione_esperimento=descrizione_esperimento,
        provider_nome=provider_nome,
        model_name=model_name,
        prompt_test_completo=prompt_test_completo,
        retry_history=retry_history,
        soglia_coverage=soglia_coverage,
        usa_soglia_dinamica=usa_soglia_dinamica,
    )

    try:
        report_html_path = genera_report_html(metrics_path)
        if report_html_path:
            print(f"Report HTML generated: {report_html_path}")
    except Exception:
        pass


def gestisci_errori_sintassi(
    test_results: dict,
    target_class_path: str,
    output_dir: str,
    nome_classe_solo: str,
    metodo_da_testare: str,
    provider_nome: str,
    versione_esperimento: str,
    soglia_similarita: float,
    prompt_test_metodo: str,
    test_path: str,
    selezione_info: dict = None,
    nome_esperimento: str = None,
    retry_history: list = None,
    model_name: str = None,
    usa_soglia_dinamica: bool = True,
) -> bool:
    """Gestisce gli errori critici (sintassi/import o nessun test passato) e salva le metriche"""
    from utils.reporting.report_generator import genera_report_html

    tests_total = test_results.get("tests_total", 0)
    tests_passed = test_results.get("tests_passed", 0)
    has_compilation_errors = test_results.get("has_compilation_errors", False)

    # Determina il motivo del fallimento
    if has_compilation_errors or (tests_total == 0 and tests_passed == 0):
        failure_reason = "Syntax/import errors - no tests executed"
        error_message = (
            "Tests cannot be executed (syntax/import errors)!"
        )
    elif tests_total > 0 and tests_passed == 0:
        failure_reason = (
            f"No tests passed - all {tests_total} tests failed"
        )
        error_message = f"No tests passed on the original method (0/{tests_total})!"
    else:
        failure_reason = "Unknown error"
        error_message = "Critical error during test execution!"

    print(f"\n CRITICAL ERROR: {error_message}")

    test_results_info = {
        "original": {
            "success": False,
            "tests_passed": tests_passed,
            "tests_total": tests_total,
            "tests_failed": tests_total - tests_passed,
            "failure_reason": failure_reason,
            "line_coverage": test_results.get("line_coverage", 0.0),
            "branch_coverage": test_results.get("branch_coverage", 0.0),
            "has_compilation_errors": test_results.get("has_compilation_errors", False),
            # error_test_names rimosso - usare solo test_info
            "test_info": test_results.get(
                "test_info", {"valid_tests": [], "invalid_tests": []}
            ),
        }
    }

    # Crea cartella per esperimento se specificato
    # Sanitizza model_name se presente
    if model_name:
        model_name = model_name.replace(":", "_").replace("/", "_").replace("\\", "_")

    # Crea cartella per esperimento se specificato
    if nome_esperimento:
        if model_name:
            metrics_dir = os.path.join(output_dir, "metrics", nome_esperimento, model_name)
        else:
            metrics_dir = os.path.join(output_dir, "metrics", nome_esperimento)
    else:
        if model_name:
            metrics_dir = os.path.join(output_dir, "metrics", model_name)
        else:
            metrics_dir = os.path.join(output_dir, "metrics")
    os.makedirs(metrics_dir, exist_ok=True)
    metrics_path = os.path.join(
        metrics_dir,
        f"Experiment_{nome_classe_solo}_{metodo_da_testare}_{provider_nome}.json",
    )

    try:
        with open(test_path, "r", encoding="utf-8") as f:
            test_content = f.read()
    except Exception:
        test_content = ""

    fasi_eseguite = {"first_phase": True, "second_phase": False}
    salva_metriche_esperimento(
        target_class_path,
        target_class_path,
        "",
        "",
        metrics_path,
        metodo_da_testare,
        soglia_similarita,
        fasi_eseguite,
        versione_esperimento,
        prompt_test_metodo,
        "",
        selezione_info,
        test_content,
        True,
        test_results_info,
        nome_esperimento,
        retry_history=retry_history,
        model_name=model_name,
        usa_soglia_dinamica=usa_soglia_dinamica,
    )

    try:
        genera_report_html(metrics_path)
    except Exception:
        pass

    return False
