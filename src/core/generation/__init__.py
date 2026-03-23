"""
Modulo per la generation di test e metodi.
Contiene funzioni per generation con e senza dipendenze.
"""

from .generation import (
    genera_test_per_metodo,
    rigenera_metodo_dai_test,
)

from .generation_with_dependencies import (
    genera_test_per_metodo_con_dipendenze,
    rigenera_metodo_dai_test_con_dipendenze,
)

__all__ = [
    'genera_test_per_metodo',
    'rigenera_metodo_dai_test',
    'genera_test_per_metodo_con_dipendenze',
    'rigenera_metodo_dai_test_con_dipendenze',
]

