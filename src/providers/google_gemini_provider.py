import time
import javalang
from javalang.tokenizer import LexerError
from google import genai
from config import get_gemini_api_key
from providers.provider_base import update_token_usage_groq
from utils.text.text_utils import estrai_codice_java, estrai_solo_metodo

def genera_google_gemini(prompt: str, model_name: str, max_retries: int = 10, max_429_retries: int = 10) -> str:
    """
    Genera testo utilizzando il modello Google Gemini.
    Include retry logic per gestire risposte None o errori temporanei.
    
    Per errori 429 (rate limit), attende con backoff esponenziale fino a max_429_retries.
    I retry per 429 sono SEPARATI dai retry per altri errori.
    """
    api_key = get_gemini_api_key()
    if not api_key:
        print("✗ Google Gemini API key not set. Set GEMINI_API_KEY as an environment variable.")
        return None

    last_error = None
    retry_429_count = 0
    base_wait_429 = 5  # Initial wait time for 429 (seconds)
    attempt = 0
    
    while attempt < max_retries:
        attempt += 1
        
        # Loop interno per gestire 429/502/503 senza consumare attempt
        while True:
            try:
                # Inizializza il client con la chiave API
                client = genai.Client(api_key=api_key)
                
                # Genera contenuto usando il modello specificato
                response = client.models.generate_content(
                    model=model_name,
                    contents=prompt
                )
                
                # Estrai informazioni sui token dalla risposta
                prompt_tokens = None
                completion_tokens = None
                if hasattr(response, 'usage_metadata') and response.usage_metadata:
                    prompt_tokens = getattr(response.usage_metadata, 'prompt_token_count', None)
                    completion_tokens = getattr(response.usage_metadata, 'candidates_token_count', None)
                    total_tokens = getattr(response.usage_metadata, 'total_token_count', 0)
                    
                    if prompt_tokens is not None or completion_tokens is not None:
                        print(f"   Token usage - prompt: {prompt_tokens}, completion: {completion_tokens}, total: {total_tokens}")
                
                # Estrae il testo dalla risposta
                content = None
                if hasattr(response, 'text'):
                    content = response.text
                elif hasattr(response, 'candidates') and len(response.candidates) > 0:
                    candidate = response.candidates[0]
                    if hasattr(candidate, 'content'):
                        if hasattr(candidate.content, 'parts'):
                            parts = candidate.content.parts
                            if parts and len(parts) > 0:
                                content = parts[0].text if hasattr(parts[0], 'text') else str(parts[0])
                        elif hasattr(candidate.content, 'text'):
                            content = candidate.content.text
                elif isinstance(response, dict):
                    if 'text' in response:
                        content = response['text']
                    elif 'candidates' in response and len(response['candidates']) > 0:
                        candidate = response['candidates'][0]
                        if 'content' in candidate and 'parts' in candidate['content']:
                            parts = candidate['content']['parts']
                            if parts and len(parts) > 0:
                                content = parts[0].get('text', '')
                
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
                        update_token_usage_groq(prompt_tokens, completion_tokens)
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
                status_code = getattr(e, 'code', None) or getattr(e, 'status_code', None)
                
                # === GESTIONE ERRORI TEMPORANEI (429 Rate Limit, 502 Bad Gateway, 503 Service Unavailable) ===
                is_rate_limit = (status_code in [429, 502, 503] or 
                                "429" in error_str or "rate limit" in error_str.lower() or
                                "resource exhausted" in error_str.lower() or
                                "502" in error_str or "503" in error_str or
                                "service unavailable" in error_str.lower())
                
                if is_rate_limit:
                    retry_429_count += 1
                    error_names = {429: "Rate limit 429", 502: "Bad Gateway 502", 503: "Service Unavailable 503"}
                    error_name = error_names.get(status_code, "Rate limit / temporary error")
                    
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"⏳ {error_name} (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue  # Ritenta SENZA incrementare attempt
                    else:
                        print(f"✗ {error_name}: reached max retry ({max_429_retries}). Aborting.")
                        return None
                
                elif "timeout" in error_str.lower() or "deadline" in error_str.lower():
                    print(f"✗ Timeout during Google Gemini call (attempt {attempt}/{max_retries})")
                    last_error = "Timeout"
                    break  # Esci dal loop 429, incrementa attempt
                    
                else:
                    print(f"✗ Error during Google Gemini call: {e}")
                    import traceback
                    traceback.print_exc()
                    last_error = str(e)
                    return None  # Non ritentare per errori non-temporanei
        
        # Pausa tra tentativi (fuori dal loop 429)
        if attempt < max_retries:
            time.sleep(2)
    
    print(f"✗ All attempts failed. Last error: {last_error}")
    return None