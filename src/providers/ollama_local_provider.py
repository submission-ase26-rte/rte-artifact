import time
import javalang
from javalang.tokenizer import LexerError
from ollama import chat
from providers.provider_base import update_token_usage_ollama
from utils.text.text_utils import estrai_codice_java, estrai_solo_metodo

def genera_ollama_local(prompt: str, model_name: str, max_retries: int = 10, max_429_retries: int = 10) -> str:
    """
    Genera testo utilizzando Ollama in locale.
    Include retry logic per gestire risposte None o errori temporanei.
    
    Per errori 429 (rate limit), attende con backoff esponenziale fino a max_429_retries.
    I retry per 429 sono SEPARATI dai retry per altri errori.
    """
    last_error = None
    retry_429_count = 0
    base_wait_429 = 5  # Initial wait time for 429 (seconds)
    attempt = 0
    
    while attempt < max_retries:
        attempt += 1
        
        # Loop interno per gestire 429/502/503 senza consumare attempt
        while True:
            try:
                response = chat(
                    model=model_name,
                    messages=[{'role': 'user', 'content': prompt}]
                )
                
                # Estrai informazioni sui token dalla risposta
                prompt_eval_count = None
                eval_count = None
                content = None
                
                if isinstance(response, dict):
                    prompt_eval_count = response.get("prompt_eval_count")
                    eval_count = response.get("eval_count")
                    content = response.get("message", {}).get("content")
                elif hasattr(response, "message"):
                    prompt_eval_count = getattr(response, "prompt_eval_count", None)
                    eval_count = getattr(response, "eval_count", None)
                    content = getattr(response.message, "content", None)
                else:
                    content = str(response)
                
                if prompt_eval_count is not None or eval_count is not None:
                    total_tokens = (prompt_eval_count or 0) + (eval_count or 0)
                    print(f"   Token usage - prompt: {prompt_eval_count}, completion: {eval_count}, total: {total_tokens}")

                if content is not None and content.strip() != "":
                    # --- VALIDAZIONE SINTASSI ---
                    codice_estratto = estrai_codice_java(content)
                    try:
                        # Prova prima come classe Java completa
                        if codice_estratto and codice_estratto.strip():
                            try:
                                javalang.parse.parse(codice_estratto)
                            except javalang.parser.JavaSyntaxError:
                                # Se fallisce come classe, prova come metodo singolo
                                dummy_class = f"public class Dummy {{ {codice_estratto} }}"
                                try:
                                    javalang.parse.parse(dummy_class)
                                except (javalang.parser.JavaSyntaxError, LexerError):
                                    # Se fallisce ancora, prova a estrarre solo il metodo
                                    codice_metodo = estrai_solo_metodo(codice_estratto)
                                    if codice_metodo and codice_metodo != codice_estratto:
                                        dummy_class = f"public class Dummy {{ {codice_metodo} }}"
                                        javalang.parse.parse(dummy_class)
                                        print(f"   INFO: Extracted only method (imports removed) - validation OK")
                                    else:
                                        raise  # Rilancia l'errore originale
                        else:
                            # Codice estratto vuoto
                            print(f"   WARNING: No Java code block found in response")
                            print(f"   DEBUG: First 200 chars of response: {content[:200] if content else 'None'}...")
                            last_error = "No Java code block found in response"
                            break
                        # Aggiorna i token usando la funzione comune
                        update_token_usage_ollama(prompt_eval_count, eval_count)
                        return content
                    except (javalang.parser.JavaSyntaxError, LexerError) as e:
                        error_msg = str(e) if str(e) else "Unknown parsing error"
                        print(f"   WARNING: Invalid or incomplete Java syntax (attempt {attempt}/{max_retries}): {error_msg}")
                        if codice_estratto:
                            prime_righe = '\n'.join(codice_estratto.split('\n')[:5])
                            print(f"   DEBUG: First 5 lines of code: {prime_righe}")
                        last_error = f"Invalid Java syntax: {error_msg}"
                        break  # Esci dal loop 429, incrementa attempt
                else:
                    print(f"   WARNING: Empty or None response (attempt {attempt}/{max_retries}) - tokens NOT counted")
                    last_error = "Empty response"
                    break

            except Exception as e:
                error_str = str(e)
                
                # Controlla se è un errore HTTP mascherato
                if "429" in error_str or "rate limit" in error_str.lower():
                    retry_429_count += 1
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"⏳ Rate limit detected (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue
                    else:
                        print(f"✗ Rate limit: reached max retry ({max_429_retries}). Aborting.")
                        return None
                        
                elif "502" in error_str or "503" in error_str or "service unavailable" in error_str.lower():
                    retry_429_count += 1
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"⏳ Service unavailable (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue
                    else:
                        print(f"✗ Service unavailable: reached max retry ({max_429_retries}). Aborting.")
                        return None
                        
                elif "timeout" in error_str.lower():
                    print(f"✗ Timeout during Ollama Local call (attempt {attempt}/{max_retries})")
                    last_error = "Timeout"
                    break
                    
                else:
                    print(f"✗ Error during Ollama Local call: {e}")
                    import traceback
                    traceback.print_exc()
                    last_error = str(e)
                    break  # Esci dal loop 429, incrementa attempt
        
        # Pausa tra tentativi (fuori dal loop 429)
        if attempt < max_retries:
            time.sleep(2)
    
    print(f"✗ All attempts failed. Last error: {last_error}")
    return None
