import time
import httpx
import javalang
from javalang.tokenizer import LexerError
from ollama import Client
from config import get_ollama_colab_url
from providers.provider_base import update_token_usage_ollama
from utils.text.text_utils import estrai_codice_java, estrai_solo_metodo

def genera_ollama_colab(prompt: str, model_name: str, max_retries: int = 10, max_429_retries: int = 10) -> str:
    """
    Generates text using Ollama on Google Colab via Cloudflare tunnel.
    The tunnel URL must be configured in config.py.
    Includes retry logic to handle None responses or temporary errors.
    
    For 429 (rate limit) errors, waits with exponential backoff up to max_429_retries.
    429 retries are SEPARATE from other errors.
    """
    colab_url = get_ollama_colab_url()
    if not colab_url:
        print("✗ Ollama Colab URL not set. Set OLLAMA_COLAB_URL in config.py")
        return None

    last_error = None
    retry_429_count = 0
    base_wait_429 = 5  # Initial wait time for 429 (seconds)
    attempt = 0
    
    while attempt < max_retries:
        attempt += 1
        
        # Inner loop to handle 429/502/503 without consuming attempt
        while True:
            try:
                # Configure client to point to Colab tunnel with timeout
                client = Client(
                    host=colab_url,
                    timeout=httpx.Timeout(120)
                )

                messages = [{"role": "user", "content": prompt}]
                response = client.chat(model=model_name, messages=messages)

                # Extract token information from response
                prompt_eval_count = None
                eval_count = None
                content = None
                
                if isinstance(response, dict):
                    prompt_eval_count = response.get("prompt_eval_count")
                    eval_count = response.get("eval_count")
                    content = response.get("message", {}).get("content")
                elif hasattr(response, "prompt_eval_count"):
                    prompt_eval_count = getattr(response, "prompt_eval_count", None)
                    eval_count = getattr(response, "eval_count", None)
                    if hasattr(response, "message"):
                        content = getattr(response.message, "content", None)
                    else:
                        content = str(response)
                else:
                    if hasattr(response, "message"):
                        prompt_eval_count = getattr(response, "prompt_eval_count", None)
                        eval_count = getattr(response, "eval_count", None)
                        content = getattr(response.message, "content", None)
                    else:
                        content = str(response)
                
                if prompt_eval_count is not None or eval_count is not None:
                    total_tokens = (prompt_eval_count or 0) + (eval_count or 0)
                    print(f"   Token usage - prompt: {prompt_eval_count}, completion: {eval_count}, total: {total_tokens}")

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
                                dummy_class = f"public class Dummy {{ {codice_estratto} }}"
                                try:
                                    javalang.parse.parse(dummy_class)
                                except (javalang.parser.JavaSyntaxError, LexerError):
                                    # If it fails again, try to extract only the method
                                    codice_metodo = estrai_solo_metodo(codice_estratto)
                                    if codice_metodo and codice_metodo != codice_estratto:
                                        dummy_class = f"public class Dummy {{ {codice_metodo} }}"
                                        javalang.parse.parse(dummy_class)
                                        print(f"   INFO: Extracted only method (imports removed) - validation OK")
                                    else:
                                        raise  # Raise original error
                        else:
                            # Empty extracted code
                            print(f"   WARNING: No Java code block found in response")
                            print(f"   DEBUG: First 200 chars of response: {content[:200] if content else 'None'}...")
                            last_error = "No Java code block found in response"
                            break
                        # Update tokens using the common function
                        update_token_usage_ollama(prompt_eval_count, eval_count)
                        return content
                    except (javalang.parser.JavaSyntaxError, LexerError) as e:
                        error_msg = str(e) if str(e) else "Unknown parsing error"
                        print(f"   WARNING: Invalid or incomplete Java syntax (attempt {attempt}/{max_retries}): {error_msg}")
                        if codice_estratto:
                            prime_righe = '\n'.join(codice_estratto.split('\n')[:5])
                            print(f"   DEBUG: First 5 lines of code: {prime_righe}")
                        last_error = f"Invalid Java syntax: {error_msg}"
                        break  # Exit 429 loop, increment attempt
                else:
                    print(f"   WARNING: Empty or None response (attempt {attempt}/{max_retries}) - tokens NOT counted")
                    last_error = "Empty response"
                    break

            except httpx.TimeoutException:
                print(f"✗ Timeout during Ollama Colab call (attempt {attempt}/{max_retries})")
                last_error = "Timeout"
                break  # Exit 429 loop, increment attempt
                
            except httpx.HTTPStatusError as e:
                status_code = e.response.status_code if hasattr(e, 'response') and e.response is not None else None
                
                # === TEMPORARY ERROR HANDLING (429 Rate Limit, 502 Bad Gateway, 503 Service Unavailable) ===
                if status_code in [429, 502, 503]:
                    retry_429_count += 1
                    error_names = {429: "Rate limit 429", 502: "Bad Gateway 502", 503: "Service Unavailable 503"}
                    error_name = error_names.get(status_code, f"HTTP {status_code}")
                    
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"⏳ {error_name} (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        try:
                            error_detail = e.response.json()
                            print(f"   Detail: {error_detail.get('detail', 'N/A')}")
                        except:
                            pass
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue  # Retry WITHOUT incrementing attempt
                    else:
                        print(f"✗ {error_name}: reached max retry ({max_429_retries}). Aborting.")
                        return None
                else:
                    # Other HTTP errors (not 429/502/503)
                    print(f"✗ HTTP error during Ollama Colab call: {e}")
                    if hasattr(e, 'response') and e.response is not None:
                        print(f"   Status code: {e.response.status_code}")
                        try:
                            error_detail = e.response.json()
                            print(f"   Detail: {error_detail}")
                        except:
                            print(f"   Body: {e.response.text[:500]}")
                    last_error = str(e)
                    return None  # Do not retry for non-temporary HTTP errors
                    
            except Exception as e:
                error_str = str(e)
                
                # Check if it's a masked HTTP error
                if "429" in error_str or "rate limit" in error_str.lower():
                    retry_429_count += 1
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"Rate limit detected (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue
                    else:
                        print(f"Rate limit: reached max retry ({max_429_retries}). Aborting.")
                        return None
                        
                elif "502" in error_str or "503" in error_str or "service unavailable" in error_str.lower():
                    retry_429_count += 1
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"Service unavailable (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue
                    else:
                        print(f"Service unavailable: reached max retry ({max_429_retries}). Aborting.")
                        return None
                        
                elif "timeout" in error_str.lower():
                    print(f"Timeout during Ollama Colab call (attempt {attempt}/{max_retries})")
                    last_error = "Timeout"
                    break
                    
                else:
                    print(f"Error during Ollama Colab call: {e}")
                    import traceback
                    traceback.print_exc()
                    last_error = str(e)
                    return None 
        
        # Pause between attempts (outside 429 loop)
        if attempt < max_retries:
            time.sleep(2)
    
    print(f"All attempts failed. Last error: {last_error}")
    return None