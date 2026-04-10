import requests
import json
import javalang
from javalang.tokenizer import LexerError
from config import get_chutes_api_key, get_chutes_chute_id
from providers.provider_base import update_token_usage_groq
from utils.text.text_utils import estrai_codice_java, estrai_solo_metodo

# Chute ID read from .env via config
CHUTE_ID = get_chutes_chute_id()

def get_chutes_quota_usage() -> dict:
    """
    Retrieves quota usage information from Chutes API.
    Returns a dictionary with quota information or None in case of error.
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
    Generates text using the Chutes provider.
    Uses REST API with streaming disabled for synchronous call.
    Includes retry logic to handle None responses or temporary errors.
    
    For 429 errors (rate limit), waits with exponential backoff up to max_429_retries.
    429 retries are SEPARATE from other errors.
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
        
        # Inner loop to handle 429 without consuming attempt
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
                    timeout=120 # Timeout reduced to 120s (from 300s) for fail-fast on overload
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
                        # --- SYNTAX VALIDATION ---
                        codice_estratto = estrai_codice_java(content)
                        try:
                            # Try first as a complete Java class
                            if codice_estratto and codice_estratto.strip():
                                try:
                                    javalang.parse.parse(codice_estratto)
                                except javalang.parser.JavaSyntaxError:
                                    # If it fails as a class, try as a single method
                                    # Wrap in a dummy class for parsing
                                    dummy_class = f"public class Dummy {{ {codice_estratto} }}"
                                    try:
                                        javalang.parse.parse(dummy_class)
                                    except (javalang.parser.JavaSyntaxError, LexerError):
                                        # If it fails again, try to extract only the method
                                        # (removes imports, package, etc.)
                                        codice_metodo = estrai_solo_metodo(codice_estratto)
                                        if codice_metodo and codice_metodo != codice_estratto:
                                            dummy_class = f"public class Dummy {{ {codice_metodo} }}"
                                            javalang.parse.parse(dummy_class)
                                            # If we get here, parsing succeeded
                                            print(f"   INFO: Extracted only method (imports removed) - validation OK")
                                        else:
                                            raise  # Raise original error
                            else:
                                # Empty extracted code - LLM did not generate ```java blocks
                                print(f"   WARNING: No Java code block found in response")
                                print(f"   DEBUG: First 200 chars of response: {content[:200] if content else 'None'}...")
                                last_error = "No Java code block found in response"
                                break
                            update_token_usage_groq(prompt_tokens, completion_tokens)
                            return content
                        except (javalang.parser.JavaSyntaxError, LexerError) as e:
                            error_msg = str(e) if str(e) else "Unknown parsing error"
                            print(f"   WARNING: Invalid or incomplete Java syntax (attempt {attempt}/{max_retries}): {error_msg}")
                            # DEBUG: show first lines of problematic code
                            if codice_estratto:
                                prime_righe = '\n'.join(codice_estratto.split('\n')[:5])
                                print(f"   DEBUG: First 5 lines of code: {prime_righe}")
                            last_error = f"Invalid Java syntax: {error_msg}"
                            break  # Exit 429 loop, increment attempt
                    else:
                        print(f"   WARNING: Empty or None response (attempt {attempt}/{max_retries}) - tokens NOT counted")
                        last_error = "Empty response"
                        break  # Exit 429 loop, increment attempt
                else:
                    print(f"   WARNING: No 'choices' in response (attempt {attempt}/{max_retries})")
                    last_error = "No choices in response"
                    break  # Exit 429 loop, increment attempt

            except requests.exceptions.Timeout:
                print(f"Timeout during Chutes call (attempt {attempt}/{max_retries})")
                last_error = "Timeout"
                break  # Exit 429 loop, increment attempt
                
            except requests.exceptions.HTTPError as e:
                status_code = e.response.status_code if hasattr(e, 'response') and e.response is not None else None
                
                # === TEMPORARY ERROR HANDLING (429 Rate Limit, 502 Bad Gateway, 503 Service Unavailable) ===
                if status_code in [429, 502, 503]:
                    retry_429_count += 1
                    error_names = {429: "Rate limit 429", 502: "Bad Gateway 502", 503: "Service Unavailable 503"}
                    error_name = error_names.get(status_code, f"HTTP {status_code}")
                    
                    if retry_429_count <= max_429_retries:
                        # Exponential backoff: 5s, 10s, 20s, 40s... max 300s
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        
                        print(f"{error_name} (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        try:
                            error_detail = e.response.json()
                            print(f"   Detail: {error_detail.get('detail', 'N/A')}")
                        except:
                            pass
                        
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue  # Retry WITHOUT incrementing attempt
                    else:
                        print(f"{error_name}: reached max retry ({max_429_retries}). Aborting.")
                        return None
                else:
                    # Other HTTP errors (not 429/503)
                    print(f"HTTP error during Chutes call: {e}")
                    if hasattr(e, 'response') and e.response is not None:
                        print(f"   Status code: {e.response.status_code}")
                        try:
                            error_detail = e.response.json()
                            print(f"   Detail: {error_detail}")
                        except:
                            print(f"   Body: {e.response.text[:500]}")
                    last_error = str(e)
                    return None  # Do not retry for non-429 HTTP errors
                    
            except requests.exceptions.RequestException as e:
                print(f"Connection error to Chutes: {e}")
                last_error = str(e)
                break  # Exit 429 loop, increment attempt
                
            except Exception as e:
                print(f"Unexpected error during Chutes call: {e}")
                import traceback
                traceback.print_exc()
                last_error = str(e)
                return None
        
        # Pause between attempts (outside 429 loop)
        if attempt < max_retries:
            time.sleep(2)
    
    print(f"All attempts failed. Last error: {last_error}")
    return None
