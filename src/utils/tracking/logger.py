import logging
import os
import sys
import datetime

class Tee:
    """Duplicate output to both a file and a stream (stdout/stderr)."""
    def __init__(self, name, mode, stream):
        self.file = open(name, mode, encoding='utf-8', errors='replace')
        self.stream = stream
        self.encoding = 'utf-8'

    def write(self, data):
        self.file.write(data)
        self.file.flush()
        self.stream.write(data)
        self.stream.flush()

    def flush(self):
        self.file.flush()
        self.stream.flush()

    def close(self):
        self.file.close()

from config import LOGS_DIR

def setup_logger(name="main", log_dir=LOGS_DIR):
    """Legacy logger setup, kept for compatibility."""
    os.makedirs(log_dir, exist_ok=True)

    log_path = os.path.join(log_dir, f"{name}.log")

    logging.basicConfig(
        filename=log_path,
        level=logging.INFO,
        format="%(asctime)s [%(levelname)s] %(message)s",
    )
    return logging.getLogger(name)

def setup_console_logging(experiment_name: str) -> str:
    """
    # Sets up logging to capture all console output (stdout and stderr) 
    # to a file in experiments/results/logs/logs/<experiment_name>.
    # Returns the path to the created log file.
    # """
    from config import LOGS_DIR
    
    # Define log directory structure
    base_log_dir = os.path.join(LOGS_DIR, "logs", experiment_name)


    os.makedirs(base_log_dir, exist_ok=True)

    
    # Generate log filename with timestamp
    timestamp = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
    log_filename = f"{experiment_name}_{timestamp}.log"
    log_path = os.path.join(base_log_dir, log_filename)
    
    # Redirect stdout and stderr using Tee
    # We check if sys.stdout is already a Tee to avoid nesting causing issues if called multiple times
    if not isinstance(sys.stdout, Tee):
        sys.stdout = Tee(log_path, "a", sys.stdout)
    
    if not isinstance(sys.stderr, Tee):
        sys.stderr = Tee(log_path, "a", sys.stderr)
        
    return log_path

