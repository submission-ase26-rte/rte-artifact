# System Baseline Logic

This document describes how the system worked **BEFORE** the changes made to improve the logic.

---

## Main Differences Compared to the Advanced Logic

### 1. Different Similarity Tolerance

**Before**: 1% relative tolerance as a gate to continue refinement (`Similarity: 0.6280 >= 0.6254 (tol 1%)`).
**Now**: 2% relative OR 1% absolute (more permissive).

### 2. Stop with Optimal Conditions

**Before**: If pass rate = 100% and coverage = 100%, the experiment stopped even if similarity had not been reached.
**Now**: The system continues to improve the test suite to attempt reaching the similarity threshold (Similarity Enhancement Mode).

### 3. No Gate for Regeneration

**Before**: The method was regenerated from tests even with very low pass rate (e.g., 33.33%) and low coverage.
**Now**: Minimum thresholds required: 30% coverage and 70% weighted pass rate.


### 4. Previous Method in Prompt

**Before**: In the method regeneration prompt from tests, the method generated in the previous iteration was also passed.
**Now**: The previous method is no longer passed in the prompt.

### 5. Fixed Improvement Thresholds

**Before**: Fixed thresholds: +0.5% coverage (if already high), +2% pass rate.
**Now**: Adaptive coverage threshold: +5% (if <60%), +3% (if <85%), +1% (if <95%), +0.5% (if ≥95%).

### 6. No Forced Final Regeneration

**Before**: If refinement ended without reaching the threshold, the system stopped.
**Now**: When thresholds are not met and there is no improvement, a forced final regeneration is performed.


## Limitations of the Baseline Logic

1. **Regeneration with Poor Tests**: Method regenerated even with 33% pass rate
2. **Premature Stop**: Stopped with optimal conditions even without similarity reached
3. **Strict Tolerance**: 1% relative without absolute tolerance
4. **Non-Adaptive Thresholds**: Same thresholds for low and high coverage
5. **Initial Test Generation prompt**: prompt not included some extra information in structural mode
6. **No Final Regeneration**: If stopped due to no improvement, no final method
