import os
from typing import Optional
from utils.code.code_analysis import sostituisci_metodo_in_classe

class SafeClassInjector:
    """
    Gestisce l'iniezione sicura di metodi in una classe Java, preservando l'originale.
    
    Flusso:
    1. inject_and_backup:
       - Rinomina Originale -> Originale.disabled (backup sicuro)
       - Copia Originale.disabled -> Originale (working copy)
       - Inietta il metodo nella working copy
    
    2. restore:
       - Cancella Originale (working copy modificata)
       - Rinomina Originale.disabled -> Originale (ripristino)
    """
    
    def __init__(self, target_class_path: str):
        self.target_class_path = target_class_path
        self.disabled_path = target_class_path + ".disabled"
        self._is_active = False

    def inject_and_backup(self, new_method_code: str, method_name: str) -> bool:
        """
        Esegue il backup dell'originale e inietta il nuovo metodo in una copia di lavoro.
        """
        if not os.path.exists(self.target_class_path):
            print(f" Error: Target file not found: {self.target_class_path}")
            return False
            
        # Se esiste già un .disabled, qualcosa è andato storto in precedenza (crash?)
        # Tenta di ripristinare prima di procedere
        if os.path.exists(self.disabled_path):
            print(f"WARNING: Found residual .disabled file. Preventive restore attempt...")
            self.restore()

        try:
            # 1. Rename Original -> .disabled
            os.rename(self.target_class_path, self.disabled_path)
            
            # 2. Leggi il contenuto originale (dal backup)
            with open(self.disabled_path, 'r', encoding='utf-8') as f:
                original_content = f.read()
            
            # 3. Sostituisci il metodo in memoria
            new_content = sostituisci_metodo_in_classe(original_content, method_name, new_method_code)
            
            # 4. Scrivi il nuovo contenuto nel percorso originale (working copy)
            with open(self.target_class_path, 'w', encoding='utf-8') as f:
                f.write(new_content)
                
            self._is_active = True
            print(f" Safe Injector: Method {method_name} injected (Original -> .disabled)")
            return True
            
        except Exception as e:
            print(f" Safe Injector Error during injection: {e}")
            # Tentativo di rollback immediato se possibile
            if os.path.exists(self.disabled_path) and not os.path.exists(self.target_class_path):
                 try:
                     os.rename(self.disabled_path, self.target_class_path)
                 except: pass
            return False

    def restore(self) -> bool:
        """
        Ripristina il file originale dal backup .disabled.
        """
        try:
            # Se siamo attivi, significa che abbiamo creato il .disabled e la working copy
            if os.path.exists(self.disabled_path):
                # 1. Rimuovi la working copy (se esiste)
                if os.path.exists(self.target_class_path):
                    os.remove(self.target_class_path)
                
                # 2. Ripristina il backup
                os.rename(self.disabled_path, self.target_class_path)
                self._is_active = False
                print(f" Safe Injector: Original restored.")
                return True
            else:
                # Se non c'è .disabled ma self._is_active era True, è strano
                if self._is_active:
                    print(f"WARNING: Safe Injector: .disabled file not found for restore!")
                return False
                
        except Exception as e:
            print(f" Safe Injector Error during restore: {e}")
            return False

