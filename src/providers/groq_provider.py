import time
import javalang
from javalang.tokenizer import LexerError
from openai import OpenAI
from config import get_groq_api_key
from providers.provider_base import update_token_usage_groq
from utils.text.text_utils import estrai_codice_java, estrai_solo_metodo

def genera_groq(prompt: str, model_name: str, max_retries: int = 10, max_429_retries: int = 10) -> str:
    """
    Generates text using the Groq model.
    Groq is compatible with the OpenAI API, so we use the OpenAI client with Groq's base_url.
    Includes retry logic to handle None responses or temporary errors.
    
    For 429 errors (rate limit), waits with exponential backoff up to max_429_retries.
    429 retries are SEPARATE from other errors.
    """
    api_key = get_groq_api_key()
    if not api_key:
        print("✗ Groq API key not set. Set GROQ_API_KEY as an environment variable.")
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
                client = OpenAI(
                    base_url="https://api.groq.com/openai/v1",
                    api_key=api_key,
                    timeout=120  # Timeout 120s per fail-fast su overload
                )

                response = client.chat.completions.create(
                    model=model_name,
                    messages=[
                        {"role": "user", "content": prompt}
                    ],
                    temperature=0.7
                )

                # Extract token information from response
                prompt_tokens = None
                completion_tokens = None
                if hasattr(response, 'usage') and response.usage:
                    prompt_tokens = getattr(response.usage, 'prompt_tokens', None)
                    completion_tokens = getattr(response.usage, 'completion_tokens', None)
                    total_tokens = getattr(response.usage, 'total_tokens', 0)
                    
                    if prompt_tokens is not None or completion_tokens is not None:
                        print(f"   Token usage - prompt: {prompt_tokens}, completion: {completion_tokens}, total: {total_tokens}")

                # Extract text from response
                if response.choices and len(response.choices) > 0:
                    content = response.choices[0].message.content
                    
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
                            if codice_estratto:
                                prime_righe = '\n'.join(codice_estratto.split('\n')[:5])
                                print(f"   DEBUG: First 5 lines of code: {prime_righe}")
                            last_error = f"Invalid Java syntax: {error_msg}"
                            break  # Exit 429 loop, increment attempt
                    else:
                        print(f"   WARNING: Empty or None response (attempt {attempt}/{max_retries}) - tokens NOT counted")
                        last_error = "Empty response"
                        break
                else:
                    print(f"   WARNING: No 'choices' in response (attempt {attempt}/{max_retries})")
                    last_error = "No choices in response"
                    break

            except Exception as e:
                error_str = str(e)
                status_code = getattr(e, 'status_code', None)
                
                # === TEMPORARY ERROR HANDLING (429 Rate Limit, 502 Bad Gateway, 503 Service Unavailable) ===
                if status_code in [429, 502, 503] or "429" in error_str or "rate limit" in error_str.lower():
                    retry_429_count += 1
                    error_names = {429: "Rate limit 429", 502: "Bad Gateway 502", 503: "Service Unavailable 503"}
                    error_name = error_names.get(status_code, "Rate limit / temporary error")
                    
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"{error_name} (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue  # Retry WITHOUT incrementing attempt
                    else:
                        print(f"{error_name}: reached max retry ({max_429_retries}). Aborting.")
                        return None
                        
                elif status_code in [502, 503] or "502" in error_str or "503" in error_str or "service unavailable" in error_str.lower():
                    retry_429_count += 1
                    error_name = "Service unavailable"
                    
                    if retry_429_count <= max_429_retries:
                        wait_time = min(base_wait_429 * (2 ** (retry_429_count - 1)), 300)
                        print(f"{error_name} (attempt {retry_429_count}/{max_429_retries}). Waiting {wait_time}s...")
                        time.sleep(wait_time)
                        print(f"   Waiting finished, retrying call...")
                        continue
                    else:
                        print(f"{error_name}: reached max retry ({max_429_retries}). Aborting.")
                        return None
                
                elif "timeout" in error_str.lower():
                    print(f"Timeout during Groq call (attempt {attempt}/{max_retries})")
                    last_error = "Timeout"
                    break  # Exit 429 loop, increment attempt
                    
                else:
                    print(f"Error during Groq call: {e}")
                    import traceback
                    traceback.print_exc()
                    last_error = str(e)
                    return None  # Do not retry for non-temporary errors
        
        # Pause between attempts (outside 429 loop)
        if attempt < max_retries:
            time.sleep(2)
    
    print(f"All attempts failed. Last error: {last_error}")
    return None
