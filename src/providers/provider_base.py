"""
Modulo base per i provider LLM con funzioni comuni per la gestione dei token.
"""
from typing import Dict

# Variabile globale per memorizzare le ultime informazioni sui token
_last_token_usage = {
    "prompt_eval_count": None,
    "eval_count": None,
    "prompt_tokens": None,
    "completion_tokens": None
}


def get_last_token_usage() -> Dict:
    """
    Restituisce le informazioni sui token dell'ultima chiamata.
    Supporta sia il formato Ollama (prompt_eval_count/eval_count) che Groq (prompt_tokens/completion_tokens).
    """
    return _last_token_usage.copy()


def reset_token_usage():
    """Resetta le informazioni sui token."""
    global _last_token_usage
    _last_token_usage = {
        "prompt_eval_count": None,
        "eval_count": None,
        "prompt_tokens": None,
        "completion_tokens": None
    }


def update_token_usage_ollama(prompt_eval_count: int = None, eval_count: int = None):
    """Aggiorna le informazioni sui token per provider Ollama."""
    global _last_token_usage
    if prompt_eval_count is not None:
        _last_token_usage["prompt_eval_count"] = prompt_eval_count
    if eval_count is not None:
        _last_token_usage["eval_count"] = eval_count


def update_token_usage_groq(prompt_tokens: int = None, completion_tokens: int = None):
    """Aggiorna le informazioni sui token per provider Groq."""
    global _last_token_usage
    if prompt_tokens is not None:
        _last_token_usage["prompt_tokens"] = prompt_tokens
    if completion_tokens is not None:
        _last_token_usage["completion_tokens"] = completion_tokens

