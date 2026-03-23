import requests
import json
import javalang
from javalang.tokenizer import LexerError
from config import get_chutes_api_key, get_chutes_chute_id
from providers.provider_base import update_token_usage_groq
from utils.text.text_utils import estrai_codice_java, estrai_solo_metodo

# Chute ID letto dal .env tramite config
CHUTE_ID = get_chutes_chute_id()

def get_chutes_quota_usage() -> dict:
    """
    Recupera le informazioni sulla quota usage da Chutes API.
    Restituisce un dizionario con le informazioni sulla quota o None in caso di errore.
    """
    api_key = get_chutes_api_key()
    if not api_key:
        return None
    
    try:
        url = f"https://api.chutes.ai/users/me/quota_usage/{CHUTE_ID}"
        headers = {
            "Content-Type": "application/json",
            "Authorization": f"Bearer {api_key}"
        }
        
        response = requests.get(url, headers=headers, timeout=10)
        response.raise_for_status()
        return response.json()
    except Exception as e:
        print(f"   WARNING: Unable to retrieve Chutes quota usage: {e}")
        return None

def genera_chutes(prompt: str, model_name: str, max_retries: int = 10, max_429_retries: int = 10) -> str:
    """
    Genera testo utilizzando il provider Chutes.
    Usa l'API REST con streaming disabilitato per chiamata sincrona.
    Include retry logic per gestire risposte None o errori temporanei.
    
    Per errori 429 (rate limit), attende con backoff esponenziale fino a max_429_retries.
    I retry per 429 sono SEPARATI dai retry per altri errori.
    """
    import time
    
    api_key = get_chutes_api_key()
    if not api_key:
        print("✗ Chutes API key not set. Set CHUTES_API_KEY as an environment variable.")
        return None

    last_error = None
    retry_429_count = 0
    base_wait_429 = 5  # Initial wait time for 429 (reduced from 30 for more frequent early retries)
    attempt = 0
    
    while attempt < max_retries:
        attempt += 1
        
        # Loop interno per gestire 429 senza consumare attempt
        while True:
            try:
                headers = {
                    "Authorization": f"Bearer {api_key}",
                    "Content-Type": "application/json"
                }
                
                body = {
                    "model": model_name,
                    "messages": [
                        {"role": "user", "content": prompt}
                    ],
                    "stream": False,
                    "temperature": 0.7,
                    #"max_tokens": 8192,
                }

                response = requests.post(
                    "https://llm.chutes.ai/v1/chat/completions",
                    headers=headers,
                    json=body,
                    timeout=120 # Timeout ridotto a 120s (da 300s) per fail-fast su overload
                )
                
                response.raise_for_status()
                data = response.json()

                prompt_tokens = None
                completion_tokens = None
                usage = data.get("usage")
                if usage:
                    prompt_tokens = usage.get("prompt_tokens")
                    completion_tokens = usage.get("completion_tokens")
                    total_tokens = usage.get("total_tokens", 0)
                    
                    if prompt_tokens is not None or completion_tokens is not None:
                        print(f"   Token usage - prompt: {prompt_tokens}, completion: {completion_tokens}, total: {total_tokens}")

                choices = data.get("choices", [])
                if choices and len(choices) > 0:
                    message = choices[0].get("message", {})
                    content = message.get("content")
                    
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
                                    # Avvolgi in una classe dummy per il parsing
                                    dummy_class = f"public class Dummy {{ {codice_estratto} }}"
                                    try:
                                        javalang.parse.parse(dummy_class)
                                    except (javalang.parser.JavaSyntaxError, LexerError):
                                        # Se fallisce ancora, prova a estrarre solo il metodo
                                        # (rimuove import, package, etc.)
                                        codice_metodo = estrai_solo_metodo(codice_estratto)
                                        if codice_metodo and codice_metodo != codice_estratto:
                                            dummy_class = f"public class Dummy {{ {codice_metodo} }}"
                                            javalang.parse.parse(dummy_class)
                                            # Se arriviamo qui, il parsing è riuscito
                                            print(f"   INFO: Extracted only method (imports removed) - validation OK")
                                        else:
                                            raise  # Rilancia l'errore originale
                            else:
                                # Codice estratto vuoto - l'LLM non ha generato blocchi ```java
                                print(f"   WARNING: No Java code block found in response")
                                print(f"   DEBUG: First 200 chars of response: {content[:200] if content else 'None'}...")
                                last_error = "No Java code block found in response"
                                break
                            update_token_usage_groq(prompt_tokens, completion_tokens)
                            return content
                        except (javalang.parser.JavaSyntaxError, LexerError) as e:
                            error_msg = str(e) if str(e) else "Unknown parsing error"
                            print(f"   WARNING: Invalid or incomplete Java syntax (attempt {attempt}/{max_retries}): {error_msg}")
                            # DEBUG: mostra le prime righe del codice problematico
                            if codice_estratto:
                                prime_righe = '\n'.join(codice_estratto.split('\n')[:5])
                                print(f"   DEBUG: First 5 lines of code: {prime_righe}")
                            last_error = f"Invalid Java syntax: {error_msg}"
                            break  # Esci dal loop 429, incrementa attempt
                    else:
                        print(f"   WARNING: Empty or None response (attempt {attempt}/{max_retries}) - tokens NOT counted")
                        last_error = "Empty response"
                        break  # Esci dal loop 429, incrementa attempt
                else:
                    print(f"   WARNING: No 'choices' in response (attempt {attempt}/{max_retries})")
                    last_error = "No choices in response"
                    break  # Esci dal loop 429, incrementa attempt

            except requests.exceptions.Timeout:
                print(f"✗ Timeout during Chutes call (attempt {attempt}/{max_retries})")
                last_error = "Timeout"
                break  # Esci dal loop 429, incrementa attempt
                
            except requests.exceptions.HTTPError as e:
                status_code = e.response.status_code if hasattr(e, 'response') and e.response is not None else None
                
                # === GESTIONE ERRORI TEMPORANEI (429 Rate Limit, 502 Bad Gateway, 503 Service Unavailable) ===
                if status_code in [429, 502, 503]:
                    retry_429_count += 1
                    error_names = {429: "Rate limit 429", 502: "Bad Gateway 502", 503: "Service Unavailable 503"}
                    error_name = error_names.get(status_code, f"HTTP {status_code}")
                    
                    if retry_429_count <= max_429_retries:
                        # Backoff esponenziale: 5s, 10s, 20s, 40s... max 300s
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        
                        print(f"⏳ {error_name} (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        try:
                            error_detail = e.response.json()
                            print(f"   Detail: {error_detail.get('detail', 'N/A')}")
                        except:
                            pass
                        
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue  # Ritenta SENZA incrementare attempt
                    else:
                        print(f"✗ {error_name}: reached max retry ({max_429_retries}). Aborting.")
                        return None
                else:
                    # Altri errori HTTP (non 429/503)
                    print(f"✗ HTTP error during Chutes call: {e}")
                    if hasattr(e, 'response') and e.response is not None:
                        print(f"   Status code: {e.response.status_code}")
                        try:
                            error_detail = e.response.json()
                            print(f"   Detail: {error_detail}")
                        except:
                            print(f"   Body: {e.response.text[:500]}")
                    last_error = str(e)
                    return None  # Non ritentare per errori HTTP non-429
                    
            except requests.exceptions.RequestException as e:
                print(f"✗ Connection error to Chutes: {e}")
                last_error = str(e)
                break  # Esci dal loop 429, incrementa attempt
                
            except Exception as e:
                print(f"✗ Unexpected error during Chutes call: {e}")
                import traceback
                traceback.print_exc()
                last_error = str(e)
                return None
        
        # Pausa tra tentativi (fuori dal loop 429)
        if attempt < max_retries:
            time.sleep(2)
    
    print(f"✗ All attempts failed. Last error: {last_error}")
    return None
