import re
from typing import Dict, List

class NaturalnessEvaluator:
    """Valutatore di naturalezza per test Java - Stile ASTER."""
    
    def __init__(self):
        """Inizializza l'evaluator."""
        self._nltk_available = False
        try:
            import nltk
            from nltk import word_tokenize, ngrams
            nltk.download('punkt', quiet=True)
            nltk.download('punkt_tab', quiet=True)
            self._nltk_available = True
        except ImportError:
            pass
    
    def evaluate_test_file(self, test_code: str, focal_method_name: str = None) -> Dict:
        """
        Valuta la naturalezza di un file di test Java (stile ASTER).
        """
        # Estrai i metodi di test
        test_methods = self._extract_test_methods(test_code)
        
        if not test_methods:
            return self._empty_metrics()
        
        # Calcola metriche per ogni test method
        per_method_metrics = []
        for method in test_methods:
            method_metrics = self._compute_method_metrics(method, focal_method_name)
            per_method_metrics.append(method_metrics)
        
        # Aggrega le metriche
        aggregated = self._aggregate_metrics(per_method_metrics, test_methods, focal_method_name)
        
        return aggregated
    
    def _empty_metrics(self) -> Dict:
        """Restituisce metriche vuote."""
        return {
            "num_test_methods": 0,
            "assertion_complexity": 0.0,
            "pct_null_assertions": 0.0,
            "pct_duplicate_assertions": 0.0,
            "pct_exception_assertions": 0.0,
            "pct_no_assertion": 0.0,
            "naturalness_method_name": 0.0,
            "pct_focal_method_covered": 0.0,
            "diversity_literal_values": 0.0,
            "overall_naturalness_score": 0.0,
        }
    
    def _extract_test_methods(self, code: str) -> List[Dict]:
        """
        Estrae i metodi di test dal codice.
        Conta le occorrenze di @Test per determinare il numero di test.
        Usa conteggio parentesi graffe per estrarre i corpi dei metodi.
        """
        test_methods = []
        
        # Trova tutte le posizioni di @Test (indipendentemente dal contesto)
        # Questo è il modo più affidabile per contare i test
        test_positions = []
        for match in re.finditer(r'@Test\b', code):
            test_positions.append(match.start())
        
        # Per ogni @Test trovato, cerca di estrarre il metodo
        for test_pos in test_positions:
            # Cerca la signature del metodo dopo @Test
            remaining_code = code[test_pos:]
            
            # Pattern per la signature del metodo (dopo @Test e eventuali altre annotazioni)
            signature_pattern = r'@Test[^\{]*?(public|private|protected)?\s*(void|[A-Z]\w*)\s+(\w+)\s*\([^)]*\)\s*(?:throws\s+[\w,\s]+)?\s*\{'
            signature_match = re.match(signature_pattern, remaining_code, re.DOTALL)
            
            if not signature_match:
                # Se non riesce a parsare la signature, crea comunque un entry con dati minimali
                # per garantire che il conteggio sia corretto
                test_methods.append({
                    "name": f"test_method_{len(test_methods) + 1}",
                    "body": "",
                    "full_code": "",
                })
                continue
            
            method_name = signature_match.group(3)
            
            # Trova il corpo del metodo usando conteggio delle parentesi graffe
            body_start = test_pos + signature_match.end()
            brace_count = 1
            pos = body_start
            in_string = False
            in_char = False
            escape_next = False
            
            while pos < len(code) and brace_count > 0:
                char = code[pos]
                
                if escape_next:
                    escape_next = False
                    pos += 1
                    continue
                
                if char == '\\':
                    escape_next = True
                    pos += 1
                    continue
                
                if not in_char and char == '"':
                    in_string = not in_string
                elif not in_string and char == "'":
                    in_char = not in_char
                elif not in_string and not in_char:
                    if char == '{':
                        brace_count += 1
                    elif char == '}':
                        brace_count -= 1
                
                pos += 1
            
            if brace_count == 0:
                method_body = code[body_start:pos-1]  # Escludi la } finale
                full_code = code[test_pos:pos]
                
                test_methods.append({
                    "name": method_name,
                    "body": method_body,
                    "full_code": full_code,
                })
            else:
                # Se non riesce a trovare la fine del metodo, aggiungi comunque
                test_methods.append({
                    "name": method_name,
                    "body": "",
                    "full_code": "",
                })
        
        return test_methods
    
    def _compute_method_metrics(self, method: Dict, focal_method_name: str = None) -> Dict:
        """
        Calcola le metriche per un singolo metodo di test (come ASTER).
        """
        body = method["body"]
        name = method["name"]
        
        # 1. Assertion metrics (come compute_assertion_score in ASTER)
        assertion_metrics = self._compute_assertion_score(body)
        
        # 2. Method name naturalness (come compute_test_method_name_score in ASTER)
        method_name_score = self._compute_method_name_score(name, body, focal_method_name)
        
        # 3. Focal method coverage
        focal_covered = self._is_focal_method_covered(name, body, focal_method_name)
        
        # 4. Literal values per questo metodo
        literals = self._extract_literals(body)
        
        return {
            "total_assertions": assertion_metrics["total_assertions"],
            "null_assertions": assertion_metrics["null_assertions"],
            "exception_assertions": assertion_metrics["exception_assertions"],
            "duplicate_assertions": assertion_metrics["duplicate_assertions"],
            "assertion_complexity": assertion_metrics["assertion_complexity"],
            "has_no_assertion": assertion_metrics["total_assertions"] == 0,
            "method_name_score": method_name_score,
            "focal_covered": focal_covered,
            "literals": literals,
        }
    
    def _compute_assertion_score(self, method_body: str) -> Dict:
        """
        Calcola metriche assertion come ASTER (compute_assertion_score).
        """
        lines = [l.strip() for l in method_body.split('\n') if l.strip()]
        
        # Estrai tutte le assertion
        assertion_lines = []
        for line in lines:
            if ('assert' in line.lower() or 'fail(' in line or 
                'verify(' in line or 'verifyException' in line):
                assertion_lines.append(line)
        
        total_assertions = len(assertion_lines)
        
        # Conta null assertions
        null_assertions = sum(1 for a in assertion_lines 
                             if 'assertNull' in a or 'assertNotNull' in a)
        
        # Conta exception assertions
        exception_assertions = sum(1 for a in assertion_lines 
                                   if 'fail(' in a or 'verifyException' in a or 'assertThrows' in a)
        
        # Conta duplicate (esattamente identiche)
        unique_assertions = set(assertion_lines)
        duplicate_assertions = len(assertion_lines) - len(unique_assertions)
        
        # Assertion complexity (assertion per linea)
        assertion_complexity = total_assertions / len(lines) if lines else 0.0
        
        return {
            "total_assertions": total_assertions,
            "null_assertions": null_assertions,
            "exception_assertions": exception_assertions,
            "duplicate_assertions": duplicate_assertions,
            "assertion_complexity": assertion_complexity,
        }
    
    def _compute_method_name_score(self, method_name: str, method_body: str, 
                                    focal_method_name: str = None) -> float:
        """
        Calcola naturalezza del nome del metodo (come compute_test_method_name_score in ASTER).
        
        Formula ASTER: (match_score + max_similarity) / 2
        - match_score = 1 se il nome contiene il focal method
        - max_similarity = levenshtein similarity tra parti del nome e body
        """
        # Match score: 1 se il nome del test contiene il focal method
        match_score = 0.0
        if focal_method_name:
            focal_lower = focal_method_name.lower()
            name_lower = method_name.lower()
            if focal_lower in name_lower:
                match_score = 1.0
        
        # Rimuovi "test" e focal method dal nome per analisi
        clean_name = method_name.lower()
        clean_name = clean_name.replace('test', '')
        if focal_method_name:
            clean_name = clean_name.replace(focal_method_name.lower(), '')
        
        # Split per underscore e camelCase
        name_parts = self._split_method_name(clean_name)
        
        # Calcola similarity con il body
        body_tokens = self._extract_identifiers(method_body)
        
        if not name_parts or not body_tokens:
            return match_score / 2 if focal_method_name else 0.5
        
        # Calcola max similarity (levenshtein)
        similarities = []
        for part in name_parts:
            if len(part) < 2:
                continue
            max_sim = 0.0
            for token in body_tokens:
                sim = self._levenshtein_similarity(part, token.lower())
                max_sim = max(max_sim, sim)
            if max_sim > 0:
                similarities.append(max_sim)
        
        avg_similarity = sum(similarities) / len(similarities) if similarities else 0.0
        
        # Formula ASTER: (match_score + similarity) / 2
        return (match_score + avg_similarity) / 2
    
    def _is_focal_method_covered(self, test_name: str, test_body: str, 
                                  focal_method_name: str = None) -> bool:
        """Verifica se il test copre il metodo focale."""
        if not focal_method_name:
            return False
        
        # Controlla se il nome del test contiene il focal method
        if focal_method_name.lower() in test_name.lower():
            return True
        
        # Controlla se il body chiama il focal method
        if focal_method_name in test_body:
            return True
        
        return False
    
    def _extract_literals(self, code: str) -> List[str]:
        """Estrae valori letterali dal codice."""
        literals = []
        
        # Stringhe
        strings = re.findall(r'"([^"]*)"', code)
        literals.extend(strings)
        
        # Numeri
        numbers = re.findall(r'\b(\d+\.?\d*)\b', code)
        literals.extend(numbers)
        
        return literals
    
    def _extract_identifiers(self, code: str) -> List[str]:
        """Estrae identificatori dal codice."""
        # Pattern per identificatori Java
        identifiers = re.findall(r'\b([a-zA-Z_][a-zA-Z0-9_]*)\b', code)
        # Rimuovi keywords Java comuni
        keywords = {'public', 'private', 'protected', 'void', 'int', 'String', 
                   'boolean', 'new', 'return', 'if', 'else', 'for', 'while',
                   'class', 'static', 'final', 'null', 'true', 'false',
                   'assertEquals', 'assertTrue', 'assertFalse', 'assertNull',
                   'assertNotNull', 'assertThrows', 'Test', 'BeforeEach', 'AfterEach'}
        return [i for i in identifiers if i not in keywords and len(i) > 1]
    
    def _split_method_name(self, name: str) -> List[str]:
        """Split nome metodo per underscore e camelCase (come ASTER)."""
        parts = []
        
        # Split per underscore
        for part in name.split('_'):
            # Split per camelCase
            camel_parts = re.findall(r'[A-Z]?[a-z]+|[A-Z]+(?=[A-Z]|$)', part)
            parts.extend([p.lower() for p in camel_parts if p])
        
        return [p for p in parts if p and len(p) > 1]
    
    def _levenshtein_similarity(self, s1: str, s2: str) -> float:
        """
        Calcola similarità Levenshtein (come ASTER).
        Formula: 1 - (edit_distance / max(len(s1), len(s2)))
        """
        if not s1 or not s2:
            return 0.0
        
        if s1 == s2:
            return 1.0
        
        # Calcola edit distance
        len1, len2 = len(s1), len(s2)
        dp = [[0] * (len2 + 1) for _ in range(len1 + 1)]
        
        for i in range(len1 + 1):
            dp[i][0] = i
        for j in range(len2 + 1):
            dp[0][j] = j
        
        for i in range(1, len1 + 1):
            for j in range(1, len2 + 1):
                cost = 0 if s1[i-1] == s2[j-1] else 1
                dp[i][j] = min(dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + cost)
        
        edit_distance = dp[len1][len2]
        return 1 - (edit_distance / max(len1, len2))
    
    def _compute_literal_diversity(self, all_literals: List[List[str]]) -> float:
        """
        Calcola diversità dei letterali usando n-gram (come ASTER).
        
        Formula: unique_ngrams / total_ngrams (usando 2-grams)
        """
        if not self._nltk_available:
            # Fallback senza nltk
            all_values = []
            for literals in all_literals:
                all_values.extend(literals)
            if not all_values:
                return 0.0
            unique = set(all_values)
            return len(unique) / len(all_values)
        
        try:
            from nltk import word_tokenize, ngrams
            
            all_ngrams = []
            for literals in all_literals:
                text = ' '.join(literals)
                if text:
                    tokens = word_tokenize(text)
                    if len(tokens) >= 2:
                        ngrams_list = list(ngrams(tokens, 2))
                        all_ngrams.extend(ngrams_list)
            
            if not all_ngrams:
                return 0.0
            
            unique_ngrams = set(all_ngrams)
            return len(unique_ngrams) / len(all_ngrams)
        except:
            return 0.0
    
    def _aggregate_metrics(self, per_method_metrics: List[Dict], 
                          test_methods: List[Dict], 
                          focal_method_name: str = None) -> Dict:
        """
        Aggrega le metriche di tutti i metodi (come ASTER).
        """
        num_methods = len(per_method_metrics)
        if num_methods == 0:
            return self._empty_metrics()
        
        # Somme per calcolare medie
        total_assertions = sum(m["total_assertions"] for m in per_method_metrics)
        total_null = sum(m["null_assertions"] for m in per_method_metrics)
        total_exception = sum(m["exception_assertions"] for m in per_method_metrics)
        total_duplicate = sum(m["duplicate_assertions"] for m in per_method_metrics)
        
        # Conta metodi senza assertion
        no_assertion_count = sum(1 for m in per_method_metrics if m["has_no_assertion"])
        
        # Assertion complexity media
        avg_complexity = sum(m["assertion_complexity"] for m in per_method_metrics) / num_methods
        
        # Method name score medio
        avg_method_name_score = sum(m["method_name_score"] for m in per_method_metrics) / num_methods
        
        # Focal method coverage
        focal_covered_count = sum(1 for m in per_method_metrics if m["focal_covered"])
        pct_focal_covered = focal_covered_count / num_methods
        
        # Literal diversity (n-gram)
        all_literals = [m["literals"] for m in per_method_metrics]
        literal_diversity = self._compute_literal_diversity(all_literals)
        
        # Percentuali
        methods_with_assertions = num_methods - no_assertion_count
        pct_null = total_null / total_assertions if total_assertions > 0 else 0.0
        pct_exception = total_exception / total_assertions if total_assertions > 0 else 0.0
        pct_duplicate = total_duplicate / total_assertions if total_assertions > 0 else 0.0
        pct_no_assertion = no_assertion_count / num_methods
        
        # Overall score (formula ASTER-style)
        # Pesi: method_name 0.25, assertion_quality 0.25, focal_coverage 0.25, literal_diversity 0.25
        assertion_quality = 1.0 - pct_null * 0.3 - pct_duplicate * 0.2 - pct_no_assertion * 0.5
        assertion_quality = max(0, min(1, assertion_quality))
        
        overall_score = (
            avg_method_name_score * 0.25 +
            assertion_quality * 0.25 +
            pct_focal_covered * 0.25 +
            literal_diversity * 0.25
        )
        
        return {
            "num_test_methods": num_methods,
            "assertion_complexity": round(avg_complexity, 4),
            "pct_null_assertions": round(pct_null, 4),
            "pct_duplicate_assertions": round(pct_duplicate, 4),
            "pct_exception_assertions": round(pct_exception, 4),
            "pct_no_assertion": round(pct_no_assertion, 4),
            "naturalness_method_name": round(avg_method_name_score, 4),
            "pct_focal_method_covered": round(pct_focal_covered, 4),
            "diversity_literal_values": round(literal_diversity, 4),
            "overall_naturalness_score": round(overall_score, 4),
        }


def calcola_naturalezza_test(test_code: str, focal_method_name: str = None) -> Dict:
    """
    Funzione di utilità per calcolare la naturalezza di un file di test.
    """
    evaluator = NaturalnessEvaluator()
    return evaluator.evaluate_test_file(test_code, focal_method_name)


if __name__ == "__main__":
    # Test del modulo
    test_example = '''
    @Test
    public void testEvaluateScore_NegativeScore_ReturnsInvalid() {
        UserController controller = new UserController();
        String result = controller.evaluateScore(-5);
        assertEquals("INVALID", result);
    }
    
    @Test
    public void testEvaluateScore_ValidScore_ReturnsPass() {
        UserController controller = new UserController();
        String result = controller.evaluateScore(75);
        assertEquals("PASS", result);
        assertNotNull(result);
    }
    
    @Test
    public void testEvaluateScore_HighScore_ReturnsExcellent() {
        UserController controller = new UserController();
        String result = controller.evaluateScore(95);
        assertEquals("EXCELLENT", result);
    }
    '''
    
    metrics = calcola_naturalezza_test(test_example, "evaluateScore")
    print("=== NATURALNESS METRICS (ASTER-style) ===")
    import json
    print(json.dumps(metrics, indent=2))
