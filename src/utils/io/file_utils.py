import os

def leggi_file(path: str) -> str:
    try:
        with open(path, "r", encoding="utf-8") as f:
            return f.read()
    except UnicodeDecodeError:
        try:
            # Fallback a latin-1 (common for legacy Java files)
            with open(path, "r", encoding="latin-1") as f:
                return f.read()
        except Exception:
            try:
                # Last resort: ignore/replace errors
                with open(path, "r", encoding="utf-8", errors="replace") as f:
                    return f.read()
            except Exception as e:
                print(f" Critical error reading file {path}: {e}")
                return ""
    except Exception as e:
        print(f" Error reading file {path}: {e}")
        return ""

def salva_file(percorso_dir: str, nome_file: str, contenuto: str):
    os.makedirs(percorso_dir, exist_ok=True)
    file_path = os.path.join(percorso_dir, nome_file)
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(contenuto)
    return file_path


def get_test_dir(main_class_path: str, project_root: str) -> str:
    """Ottiene la directory dei test corrispondente a una classe main"""
    rel_path = os.path.relpath(main_class_path, project_root)
    rel_path_normalized = rel_path.replace("\\", "/")

    if "src/main/java" in rel_path_normalized:
        test_path = rel_path_normalized.replace("src/main/java", "src/test/java")
        test_path_parts = test_path.split("/")
        return os.path.join(project_root, *test_path_parts[:-1])

    return os.path.dirname(main_class_path)


def get_main_dir(test_file_path: str, project_root: str) -> str:
    """Ottiene la directory main corrispondente a un file di test"""
    rel_path = os.path.relpath(test_file_path, project_root)
    rel_path_normalized = rel_path.replace("\\", "/")

    if "src/test/java" in rel_path_normalized:
        main_path = rel_path_normalized.replace("src/test/java", "src/main/java")
        main_path_parts = main_path.split("/")
        return os.path.join(project_root, *main_path_parts[:-1])

    return os.path.dirname(test_file_path)