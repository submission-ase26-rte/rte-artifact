import os
from dotenv import load_dotenv

load_dotenv()

# Token e API key
HUGGINGFACE_TOKEN = os.getenv("HUGGINGFACEHUB_API_TOKEN")
OLLAMA_API_KEY = os.getenv("OLLAMA_API_KEY")
OLLAMA_API_KEY_2 = os.getenv("OLLAMA_API_KEY_2")  # Fallback API key
OLLAMA_COLAB_URL = os.getenv("OLLAMA_COLAB_URL")  # URL tunnel Cloudflare da Colab
GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
GROQ_API_KEY = os.getenv("GROQ_API_KEY")
CHUTES_API_KEY = os.getenv("CHUTES_API_KEY")
CHUTES_CHUTE_ID = os.getenv("CHUTES_CHUTE_ID")

# Percorsi base
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
PROJECT_ROOT = os.path.dirname(BASE_DIR)

# Centralizzazione risultati in experiments/results (root progetto)
RESULTS_DIR = os.path.join(PROJECT_ROOT, "experiments", "results")
LOGS_DIR = os.path.join(RESULTS_DIR, "logs")

os.makedirs(LOGS_DIR, exist_ok=True)


def get_huggingface_token():
    return HUGGINGFACE_TOKEN

def get_ollama_key():
    return OLLAMA_API_KEY

def get_ollama_colab_url():
    return OLLAMA_COLAB_URL

def get_gemini_api_key():
    return GEMINI_API_KEY

def get_groq_api_key():
    return GROQ_API_KEY

def get_chutes_api_key():
    return CHUTES_API_KEY

def get_chutes_chute_id():
    return CHUTES_CHUTE_ID

