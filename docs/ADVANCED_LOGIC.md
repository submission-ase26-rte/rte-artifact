# System Advanced Logic

## Phase 1: Test Generation and Initial Repair

### 1.1 Test Generation
1. The LLM generates a JUnit 5 test suite for the target method
2. The tests are saved in the project directory

### 1.2 Initial Repair Loop
If the tests do not compile:
1. The LLM receives the compilation errors and fixes the tests
2. Max `max_retries_repair` attempts (default: 2)
3. If after repair there are still errors → **iterative removal** of non-compilable tests
4. If no test compiles → **experiment failed** with failure report

### 1.3 Test Execution on the Original Method
- The tests are executed on the original method
- Calculated metrics: line coverage, branch coverage, tests passed/failed
- The **Weighted Pass Rate** is calculated:
  - Passed tests: weight 1.0
  - Tests with ERROR (runtime): weight 0.5
  - Tests with FAILURE (assert): weight 0.0

---

## Phase 2: Threshold Verification and First Regeneration

### 2.1 Minimum Thresholds for Regeneration
- **Weighted Pass Rate** ≥ 70% (configurable)
- **Line Coverage** ≥ 30% (configurable)

### 2.2 If thresholds are met:
1. The LLM regenerates the method from the tests
2. The tests are executed on the regenerated method (in a temporary class)
3. The **similarity** between original and regenerated is calculated
4. If similarity ≥ threshold → **success**, otherwise → refinement loop

### 2.3 If thresholds are NOT met:
- Skip regeneration
- Enter directly into the refinement loop to improve the test suite

---

## Phase 3: Refinement Loop

### 3.1 Exit Conditions
The loop terminates when:
- The **similarity** reaches the threshold (success)
- There is no **significant improvement** (early stop)
- The **maximum iteration limit** is reached (5 if `max_retries_refinement=-1`)

### 3.2 Refinement Iteration
For each iteration:
1. The LLM generates new tests based on failed tests/missing coverage
2. The new tests are **merged** with the existing test suite
3. If the unified suite does not compile → repair loop
4. The tests are executed on the original method
5. Thresholds are verified

### 3.3 If thresholds are met:
1. The LLM regenerates the method
2. Similarity is calculated
3. If similarity ≥ threshold → **break** (success)

### 3.4 Optimal Conditions (100% pass rate + 100% coverage)
When optimal conditions are reached but similarity is still below threshold:
- The system enters **Similarity Enhancement Mode**
- Continues generating additional tests and modifying existing ones to better capture the semantics
- Accepts a drop in pass rate % as long as the **absolute number of passed tests does not decrease**
- The goal is to improve similarity without worsening the other metrics too much

### 3.5 If thresholds are NOT met:
1. Checks whether there is improvement compared to the previous iteration
2. If NO improvement → **forced final regeneration** and then break

---

## Improvement Conditions

The same thresholds are used both with and without regeneration:

| Metric | Improvement Threshold |
|--------|----------------------|
| **Coverage** | Adaptive: +5% (if <60%), +3% (if <85%), +1% (if <95%), +0.5% (if ≥95%) |
| **Tests Passed** | Absolute number of passed tests (must not decrease) |
| **Similarity** | ±2% relative OR ±0.01 absolute (only if regenerated) |

### Multi-Metric Quality Check

At least one metric must improve significantly, the other must not worsen.

**IMPORTANT**: The check uses the **absolute number of passed tests** (`tests_passed`) instead of the pass rate %.
This avoids false stops when expanding from a trivial suite (1 test, 100%) to a complete one (11/14 tests, 78%).

Let **i** be the current iteration and **i-1** the previous iteration.
An iteration **i** passes the quality criteria if at least one of the following conditions is verified:

1. **Coverage increases** compared to i-1 AND the **number of passed tests does not decrease** (absolute, should not happen)
2. The **number of passed tests increases** (absolute) compared to i-1 AND **coverage does not decrease**
3. The **semantic similarity increases** compared to i-1 AND both coverage and absolute passed tests do not decrease

```python
quality_ok = (coverage_improved AND tests_passed >= prev_tests_passed) OR
             (tests_passed > prev_tests_passed AND coverage >= prev_coverage) OR
             (similarity_improved AND coverage >= prev AND tests_passed >= prev)
```

**Example**: `1/1 (100%) → 11/14 (78%)` 
- Pass rate %: 100% → 78% = **drop** (baseline logic = STOP ❌)
- Tests passed: 1 → 11 = **+10 tests** (advanced logic = CONTINUE ✅)

This rule allows favoring the progress of one metric at a time, while avoiding regressions on the fundamental metrics.

---

## Dynamic Similarity Threshold

Calculated based on the SLOC of the original method:
- Larger methods → lower threshold (more tolerance)
- Smaller methods → higher threshold (less tolerance)

Formula: `threshold = base - (sloc - 10) * factor` with min/max limits

---

## Failure Handling

### If no test compiles:
- Generates failure report (JSON + HTML)
- Disables the test suite (.disabled)
- The experiment is marked as failed

### If similarity is not reached:
- The regenerated method is saved anyway
- The report includes all attempted iterations

---

## Structural Mode: Implementation Details

The `structural` mode (Context Builder) has been enhanced to provide robust context even in the absence of the complete source code of dependencies. The goal is to allow the LLM to correctly instantiate input objects (Value Objects, DTO) without having to mock or guess them.

### Structure of the Generated Context

For each dependency class, a section is generated with the following elements:

```
// === External Class: ClassName ===
// Full import path: org.example.package.ClassName
// Type: INTERFACE (cannot instantiate - use Mockito.mock() instead)
// Import paths:
//   LocalDate -> java.time.LocalDate
//   Collection -> java.util.Collection
// Fields (with actual visibility):
    private static final String REQUIRED;  // IMMUTABLE - cannot modify via ReflectionTestUtils
    private LocalDate birthDate;
    private PetType type;
// Enum Values (use ONLY these constants):
//   Type: LOAD, UNLOAD, RECEIVE, CLAIM, CUSTOMS
// Constructors:
public ClassName();
public ClassName(String param1, int param2);
// Method Signatures (used by target method):
public ReturnType methodName(String param);
// Public Getter Methods (for test assertions):
public String getName();
public int getId();
```

#### New Key Features (not in baseline)

1.  **Class Type Detection**:
    - Detects whether the dependency is an `interface`, `abstract class`, `enum`, or concrete class.
    - Warns the LLM with an explicit message: `// Type: INTERFACE (cannot instantiate - use Mockito.mock())`
    - Implemented in `dependency_analyzer._estrai_tipo_classe()`.

2.  **Enum Value Extraction**:
    - For enums, lists all valid values.
    - Example: `// Enum Values: LOAD, UNLOAD, RECEIVE, CLAIM, CUSTOMS`
    - Prevents "hallucinations" such as `LOADED` instead of `LOAD`.
    - Implemented in `dependency_analyzer._estrai_enum_values()`.

3.  **Static Final Field Warning**:
    - `static final` fields are marked as `// IMMUTABLE - cannot modify via ReflectionTestUtils`.
    - Prevents erroneous patterns like `ReflectionTestUtils.setField(obj, "CONSTANT", value)`.

4.  **Dynamic Cast Detection**:
    - Scans the method body for `(ClassName)` patterns.
    - Automatically includes the context of the casted class.
    - Resolves "hidden" dependencies behind `Object` or generic interfaces.

5.  **Advanced Generics Support**:
    - Extracts parametric types from `List<Type>`, `Map<K,V>`, and nested types.
    - Handles wildcards (`? extends Type`).
    - Includes the context of extracted types.  

6.  **Superclass Inclusion**:
    - Analyzes the hierarchy if the target class extends another.
    - Includes `protected/public` fields and inherited method signatures.

7.  **Static Constant Detection**:
    - Detects accesses to constants (`Class.FIELD`).
    - Includes the definition of the container class.

8.  **Helper Method Implementations**:
    - For internal methods called by the target (in the same class), provides the **full body**.
    - Allows understanding of side-effects and pre-conditions.

### Updated Constraints

```
- CRITICAL: DO NOT instantiate INTERFACES or ABSTRACT classes directly - use Mockito.mock()
- DO NOT try to modify static final fields via ReflectionTestUtils - they are IMMUTABLE
- Use ONLY enum values explicitly shown in context. If not listed, use mock()
- Use ONLY import paths in 'CORRECT IMPORT PATHS' section - do not guess
```
