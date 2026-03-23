def get_provider_function(provider_nome: str):
    """Carica il provider solo quando necessario"""
    providers = {
        "huggingface": "providers.huggingface_provider",
        "ollama_local": "providers.ollama_local_provider",
        "ollama_cloud": "providers.ollama_cloud_provider",
        "ollama_colab": "providers.ollama_colab_provider",
        "google_gemini": "providers.google_gemini_provider",
        "groq": "providers.groq_provider",
        "chutes": "providers.chutes_provider"
    }
    if provider_nome in providers:
        module = __import__(f"{providers[provider_nome]}", fromlist=[f"genera_{provider_nome}"])
        return getattr(module, f"genera_{provider_nome}")
    return None

