from typing import Dict

# Global variable to store the latest token information
_last_token_usage = {
    "prompt_eval_count": None,
    "eval_count": None, 
    "prompt_tokens": None,
    "completion_tokens": None
}


def get_last_token_usage() -> Dict:
    """
    Returns the token information from the last call.
    Supports both Ollama format (prompt_eval_count/eval_count) and Groq format (prompt_tokens/completion_tokens).
    """
    return _last_token_usage.copy()


def reset_token_usage():
    """Resets the token information."""
    global _last_token_usage
    _last_token_usage = {
        "prompt_eval_count": None,
        "eval_count": None,
        "prompt_tokens": None,
        "completion_tokens": None
    }


def update_token_usage_ollama(prompt_eval_count: int = None, eval_count: int = None):
    """Updates the token information for the Ollama provider."""
    global _last_token_usage
    if prompt_eval_count is not None:
        _last_token_usage["prompt_eval_count"] = prompt_eval_count
    if eval_count is not None:
        _last_token_usage["eval_count"] = eval_count


def update_token_usage_groq(prompt_tokens: int = None, completion_tokens: int = None):
    """Updates the token information for the Groq provider."""
    global _last_token_usage
    if prompt_tokens is not None:
        _last_token_usage["prompt_tokens"] = prompt_tokens
    if completion_tokens is not None:
        _last_token_usage["completion_tokens"] = completion_tokens

