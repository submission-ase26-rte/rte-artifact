# Prompt Templates Documentation

This document describes the 4 main prompts used by the Tester Agent, including the **dynamic** and **conditional** instructions injected by the system.

---

## 1. Test Generation Prompt

### Prompt Structure

```
┌─────────────────────────────────────────────────────────┐
│ SECTION 1: ROLE                                         │
└─────────────────────────────────────────────────────────┘
ROLE:
You are an expert Java developer specialized in writing complete and robust JUnit 5 tests.

┌─────────────────────────────────────────────────────────┐
│ SECTION 2: TEST CLASS PACKAGE                          │
└─────────────────────────────────────────────────────────┘
TEST CLASS PACKAGE:
The test class MUST use the following package (same as the target class):
package <package_name>;

┌─────────────────────────────────────────────────────────┐
│ SECTION 3: PROJECT CONTEXT (Structural Mode)           │
└─────────────────────────────────────────────────────────┘
PROJECT CONTEXT:
// CORRECT IMPORT PATHS (use these in test imports):
//   Class -> java.util.Class
//   ...

// === Class: TargetClass ===
// Fields (with actual visibility):
    public static final Class STRING_VALUE;
    private String name;  // Visibility shown
// Constructors:
    public TargetClass(String name) { ... }
// Getter/Setter Methods (for test assertions):
    public String getName();
// Internal Method Signatures (used by target method):
    private static T helper();

// === External Class: Dependency ===
// Full import path: com.example.Dependency
// Type: INTERFACE (cannot instantiate - use Mockito.mock(...)) [CONDITIONAL]
// Enum Values (use ONLY these constants): [CONDITIONAL - only for Enum]
//   Status: ACTIVE, INACTIVE, PENDING
// Fields, Constructors, Method Signatures...

┌─────────────────────────────────────────────────────────┐
│ SECTION 4: EXCEPTION CONTEXT [CONDITIONAL]             │
└─────────────────────────────────────────────────────────┘
// === Exception Class: CustomException ===
// NOTE: The target method throws this exception.
// Constructors:
    public CustomException(String message)
    public CustomException(String message, Throwable cause)

EXCEPTION HANDLING IN TESTS - Use ONE of these patterns:
Pattern 1 (for tests that EXPECT the exception):
    assertThrows(CustomException.class, () -> targetMethod(...));

Pattern 2 (for tests where exception should NOT occur):
    void testMethodSucceeds() throws CustomException { ... }

┌─────────────────────────────────────────────────────────┐
│ SECTION 5: TARGET METHOD TO TEST                       │
└─────────────────────────────────────────────────────────┘
TARGET METHOD TO TEST:
The following is the specific method you need to test (including its JavaDoc):  
/** JavaDoc... */
public ReturnType targetMethod(...) { ... }

┌─────────────────────────────────────────────────────────┐
│ SECTION 6: TASK                                         │
└─────────────────────────────────────────────────────────┘
TASK:
Generate a complete JUnit 5 test class for the method "<methodName>" of class "<className>".

┌─────────────────────────────────────────────────────────┐
│ SECTION 7: REQUIREMENTS                                │
└─────────────────────────────────────────────────────────┘
REQUIREMENTS:
- Test class name: <ClassName>_<MethodName>TestGenerated
- Include all necessary imports and the package declaration
- Cover all code paths deterministically reachable from the method's public API
- Test error scenarios by asserting actual runtime behavior
- Cover expected exceptions declared in the method signature
- The target method throws <Exception>. Tests MUST handle this. [CONDITIONAL]

┌─────────────────────────────────────────────────────────┐
│ SECTION 8: CONSTRAINTS (BASE)                          │
└─────────────────────────────────────────────────────────┘
CONSTRAINTS:
- DO NOT use @Nested annotation
- Use JUnit annotations where is necessary(@Test, @BeforeEach, etc.)
- DO NOT create inner classes or custom implementations
- Helper methods are allowed if they don't reimplement production logic
- CRITICAL: DO NOT mock the target class itself - instantiate directly
- You MAY mock DEPENDENCIES but NEVER the class under test
- CRITICAL: DO NOT instantiate INTERFACES or ABSTRACT classes directly
- DO NOT modify static final fields via ReflectionTestUtils - IMMUTABLE
- For exceptions, use JUnit 5 assertThrows() - NOT JUnit 4 syntax
- Use ONLY enum values explicitly shown in context
- Use ONLY import paths provided in 'CORRECT IMPORT PATHS' section
- Generate ONLY the test class without explanations

┌─────────────────────────────────────────────────────────┐
│ SECTION 9: CONSTRAINTS (CONDITIONAL - Framework)       │
└─────────────────────────────────────────────────────────┘
[IF has_servlet=True]:
- DO NOT mock javax.servlet classes directly - use MockHttpServletRequest/Response

[IF has_spring OR has_cdi OR has_ejb]:
- For PRIVATE fields without setters (@Inject, @Autowired):
  * Use ReflectionTestUtils.setField(object, "field", mock)
  * OR Mockito's @InjectMocks with @Mock annotations
- DO NOT access private fields directly (object.privateField = value)

[IF has_jndi OR has_ejb]:
- For JNDI lookups, mock the DataSource/service and inject via ReflectionTestUtils

[IF has_static_utils]:
- For static method calls (Log.error()), use Mockito.mockStatic()
  try (MockedStatic<Log> mock = mockStatic(Log.class)) { ... }

┌─────────────────────────────────────────────────────────┐
│ SECTION 10: ABSTRACT CLASS INSTRUCTIONS [CONDITIONAL]  │
└─────────────────────────────────────────────────────────┘
IMPORTANT: The target class "<ClassName>" is ABSTRACT.
- DO NOT use Mockito to mock the class itself
- INSTANTIATE using this REAL ANONYMOUS SUBCLASS template:
    TargetClass instance = new TargetClass(args) {
        @Override protected void abstractMethod() { /* minimal impl */ }
    };
- DO NOT create custom concrete subclass files

┌─────────────────────────────────────────────────────────┐
│ SECTION 11: DOMAIN-SPECIFIC INSTRUCTIONS [OPTIONAL]    │
└─────────────────────────────────────────────────────────┘
DOMAIN-SPECIFIC INSTRUCTIONS:
<content from prompt.yaml file if present>
```

---

## 2. Repair Prompt

### Prompt Structure

```
┌─────────────────────────────────────────────────────────┐
│ SECTION 1: ROLE                                         │
└─────────────────────────────────────────────────────────┘
ROLE:
You are an expert Java developer. Task: fix compilation errors.

┌─────────────────────────────────────────────────────────┐
│ SECTION 2: TEST CLASS INFO                             │
└─────────────────────────────────────────────────────────┘
TEST CLASS INFO:
Package: package <package_name>;
Class name (MUST be exactly): <ClassName>_<MethodName>TestGenerated

┌─────────────────────────────────────────────────────────┐
│ SECTION 3: CURRENT TEST SUITE (with line numbers)      │
└─────────────────────────────────────────────────────────┘
CURRENT TEST SUITE (with errors):
1: package com.example;
2: import org.junit.jupiter.api.Test;
...

┌─────────────────────────────────────────────────────────┐
│ SECTION 4: COMPILATION ERRORS                          │
└─────────────────────────────────────────────────────────┘
COMPILATION ERRORS:
/path/to/File.java:[42] error: cannot find symbol
/path/to/File.java:[55] error: method does not exist
...

┌─────────────────────────────────────────────────────────┐
│ SECTION 5: INSTRUCTIONS                                │
└─────────────────────────────────────────────────────────┘
INSTRUCTIONS:
- Fix ALL compilation errors
- Follow suggestions in error messages (e.g., "Use...", "Replace...")
- Fix missing imports for "cannot find symbol" errors
- IMPORTANT: All methods must have JUnit annotations (e.g., @Test, @BeforeEach, @AfterEach, etc.)
- DO NOT create inner classes, helper classes, or custom Driver/Mock implementations inside the test class
- DO NOT mock the target class
- When using enum types from dependencies, use ONLY values explicitly shown in the context. If enum values are not listed, use a mock instead of guessing enum constants.
- Keep the SAME class name: Parser_parseTestGenerated
- Generate ONLY the test class, not the original class
- Provide ONLY Java code without explanations
```

---

## 3. Refinement Prompt (Improvement)

### Prompt Structure

```
┌─────────────────────────────────────────────────────────┐
│ SECTION 1: ROLE                                         │
└─────────────────────────────────────────────────────────┘
ROLE:
You are an expert Java developer. Task: fix failed tests. [or "improve coverage"]

┌─────────────────────────────────────────────────────────┐
│ SECTION 2: TEST CLASS INFO                             │
└─────────────────────────────────────────────────────────┘
TEST CLASS INFO:
Package: package <package_name>;
Class name: <ClassName>_<MethodName>TestGenerated

┌─────────────────────────────────────────────────────────┐
│ SECTION 3: FAILED TESTS (MUST BE FIXED)                │
└─────────────────────────────────────────────────────────┘
FAILED TESTS (MUST BE FIXED):
FAILURE REASONS:
TEST: testMethodName
ERROR: expected: <5> but was: <3>
--------------------------------------------------

TEST CODE TO FIX:
<complete_failed_test_code>

┌─────────────────────────────────────────────────────────┐
│ SECTION 4: VALID TESTS (REFERENCE ONLY)                │
└─────────────────────────────────────────────────────────┘
VALID TESTS (REFERENCE ONLY):
DO NOT include these - system will merge automatically.
Valid test signatures:
    @Test void testValidCase() {}

┌─────────────────────────────────────────────────────────┐
│ SECTION 5: PROJECT CONTEXT                             │
└─────────────────────────────────────────────────────────┘
[Same format as Test Generation]

┌─────────────────────────────────────────────────────────┐
│ SECTION 6: EXISTING HELPER METHODS                     │
└─────────────────────────────────────────────────────────┘
EXISTING HELPER METHODS (DO NOT MODIFY):
    @BeforeEach void setUp() { ... }

┌─────────────────────────────────────────────────────────┐
│ SECTION 7: ORIGINAL METHOD (with coverage annotations) │
└─────────────────────────────────────────────────────────┘
ORIGINAL METHOD TO TEST:
NOTE: Current coverage is X%.

public ReturnType targetMethod(...) {
    if (condition) {  // NOT COVERED
        return x;     // NOT COVERED
    }
    return y;
}

┌─────────────────────────────────────────────────────────┐
│ SECTION 8: COVERAGE & INSTRUCTIONS                     │
└─────────────────────────────────────────────────────────┘
COVERAGE: X% line coverage achieved.
[IF coverage >= 100%]: Focus on fixing failed tests, not adding new coverage.
[IF coverage < threshold]: You MUST add new tests for uncovered lines.

INSTRUCTIONS:
- Fix failed tests
- CRITICAL FOR FIXING ASSERTIONS: When an error shows 'expected: <X> but was: <Y>', the ACTUAL VALUE is Y (what the method returns). You MUST change your assertion to expect Y, not X. For example: if error is 'expected: <null> but was: <java.lang.String>' you must change assertNull(...) to assertEquals(String.class, ...)
- Valid tests are for REFERENCE ONLY - do not include them in output
- Generate ONLY the test class without explanations or comments outside the code
- Use JUnit5 annotations where is necessary (e.g., @Test, @BeforeEach, @AfterEach, etc.)
- DO NOT use @Nested annotations
- DO NOT create inner classes, helper classes, or custom implementations inside the test class
- USE EXISTING HELPER METHODS if available (see above). Create NEW helpers only if ensuring unique names.
- CRITICAL: DO NOT mock the target class itself - ALWAYS instantiate it directly using 'new TargetClass(...)'. Mocking the target class defeats the purpose of testing. You MAY mock DEPENDENCIES but NEVER the class under test
- For expected exceptions, use JUnit 5 assertThrows() - DO NOT use JUnit4 syntax @Test(expected=...) which is NOT valid in JUnit5
- ENSURE the file starts with the correct package declaration
- Keep the SAME test class name as shown in TEST CLASS INFO
- IGNORE any stray code fragments (loops, assertions, statements) NOT preceded by JUnit annotations - they are extraction artifacts
---

## 3b. Refinement Prompt (Optimization/Robustness)

**Scenario:** Coverage 100%, all tests pass, but structural/semantic similarity is low.
**Trigger:** `is_similarity_enhancement = True` in `similarity_info`.

### Prompt Structure

```
┌─────────────────────────────────────────────────────────┐
│ SECTION 1: ROLE (Specific for Optimization)            │
└─────────────────────────────────────────────────────────┘
ROLE:
You are an expert Java developer. The current test suite achieves 100% code coverage 
and all tests pass, but the tests are TOO GENERIC.

CURRENT SITUATION:
- Line Coverage: 100%
- Tests: N/N passed (all passing)
- Similarity Score: 0.XXXX (threshold: 0.YY)

The low similarity score indicates that a DIFFERENT implementation could also pass 
all these tests. Your task is to ADD MORE SPECIFIC tests OR MODIFY the existing tests to capture the unique 
behavior of the original method.

┌─────────────────────────────────────────────────────────┐
│ SECTION 2: TEST CLASS INFO                             │
└─────────────────────────────────────────────────────────┘
TEST CLASS INFO:
Package: package <package_name>;
Class name (MUST be exactly): <ClassName>_<MethodName>TestGenerated

┌─────────────────────────────────────────────────────────┐
│ SECTION 3: EXISTING TEST SUITE (100% COVERAGE, PASSING)│
└─────────────────────────────────────────────────────────┘
EXISTING TEST SUITE (100% COVERAGE, ALL PASSING):
The following is the COMPLETE current test suite. It achieves 100% coverage and all tests pass.
It is provided fully implemented so you can see EXACTLY what logic is already covered.

CRITICAL INSTRUCTIONS:
1. READ these tests carefully to understand the current coverage.
2. Your task is to ADD NEW TESTS OR MODIFY the existing tests to make them more specific and less generic.
3. Avoid adding tests that are redundant with the ones below.

<complete_existing_test_suite_code>

┌─────────────────────────────────────────────────────────┐
│ SECTION 4: PROJECT CONTEXT                             │
└─────────────────────────────────────────────────────────┘
[Same format as Test Generation]

┌─────────────────────────────────────────────────────────┐
│ SECTION 5: EXISTING HELPER METHODS                     │
└─────────────────────────────────────────────────────────┘
EXISTING HELPER METHODS (Available for use, DO NOT MODIFY):
The following helper methods are already present in the test class. You CAN use them, 
but DO NOT modify or reimplement them unless necessary for a fix.

<helper_methods_code>

┌─────────────────────────────────────────────────────────┐
│ SECTION 6: ORIGINAL METHOD TO TEST                     │
└─────────────────────────────────────────────────────────┘
ORIGINAL METHOD TO TEST:
The following is the complete original method that needs to be tested. 
NOTE: The current test suite achieves 100% line coverage on this method:

<original_method_code>

┌─────────────────────────────────────────────────────────┐
│ SECTION 7: INSTRUCTIONS (Focus on Specificity)         │
└─────────────────────────────────────────────────────────┘
INSTRUCTIONS:
- STRENGTHEN existing tests that do not uniquely characterize the original method behavior
- ADD new tests to constrain behaviors that could be satisfied by alternative implementations
- Focus on SPECIFIC RETURN VALUES - test exact values, not just types
- Focus on BOUNDARY CONDITIONS - edge cases unique to this implementation
- Focus on SPECIFIC EXCEPTION MESSAGES if applicable
- Focus on INTERNAL LOGIC PATHS specific to this implementation
- Generate ONLY the test class without explanations or comments outside the code
- All methods MUST have JUnit annotations (@Test, @BeforeEach, @AfterEach, etc.)
- DO NOT use @Nested annotations
- DO NOT create inner classes or custom implementations.
- USE EXISTING HELPER METHODS if available (see above). Create NEW helpers methods only if ensuring unique names.
- CRITICAL: DO NOT mock the target class itself - ALWAYS instantiate it directly using 'new TargetClass(...)'. Mocking the target class defeats the purpose of testing. You MAY mock DEPENDENCIES but NEVER the class under test
- Do not infer undocumented behavior.
- Do not rely on implementation details not observable from the method signature.
- Do not assert exact outputs if they depend on non-deterministic factors.
- For expected exceptions, use JUnit 5 assertThrows() - DO NOT use JUnit 4 syntax @Test(expected=...) which is NOT valid in JUnit 5
- ENSURE the file starts with the correct package declaration
- Keep the SAME test class name as shown in TEST CLASS INFO
- IGNORE any stray code fragments (loops, assertions, statements) NOT preceded by JUnit annotations - they are extraction artifacts
```

---

## 4. Method Regeneration Prompt (Round-Trip)

### Prompt Structure

```
┌─────────────────────────────────────────────────────────┐
│ SECTION 1: ROLE                                         │
└─────────────────────────────────────────────────────────┘
ROLE:
You are an expert Java developer specialized in regenerating methods from JUnit tests.

┌─────────────────────────────────────────────────────────┐
│ SECTION 2: PROVIDED TESTS                              │
└─────────────────────────────────────────────────────────┘
PROVIDED TESTS:
<complete_test_suite_code>

┌─────────────────────────────────────────────────────────┐
│ SECTION 3: COVERAGE ON ORIGINAL METHOD                 │
└─────────────────────────────────────────────────────────┘
COVERAGE ON ORIGINAL METHOD:
- Line: 100.00% | Branch: 100.00%
- Tests: 9/19 passed

TEST STATUS:
- PASSED (9): testA, testB, ...
- FAILED (8): testC, testD, ...
- ERRORS (2): testE, testF, ...

The regenerated method must pass all PASSED tests.

┌─────────────────────────────────────────────────────────┐
│ SECTION 4: PROJECT CONTEXT                             │
└─────────────────────────────────────────────────────────┘
[Same format as Test Generation]

┌─────────────────────────────────────────────────────────┐
│ SECTION 5: TASK                                         │
└─────────────────────────────────────────────────────────┘
TASK:
Regenerate ONLY the method "<methodName>" OF CLASS "<className>" by analyzing the tests.

┌─────────────────────────────────────────────────────────┐
│ SECTION 6: CRITICAL OUTPUT FORMAT                      │
└─────────────────────────────────────────────────────────┘
CRITICAL OUTPUT FORMAT:
- Generate ONLY the method implementation
- DO NOT generate full class, package, imports, or class declaration
- Output ONLY the method with annotations, signature, and body

EXPECTED OUTPUT FORMAT:
public static Options parsePattern(final String pattern) {
    // your implementation here
}

┌─────────────────────────────────────────────────────────┐
│ SECTION 7: REQUIREMENTS                                │
└─────────────────────────────────────────────────────────┘
REQUIREMENTS:
- The regenerated method must pass all VALID tests
- Maintain the exact same method signature as the original

┌─────────────────────────────────────────────────────────┐
│ SECTION 8: CONSTRAINTS                                 │
└─────────────────────────────────────────────────────────┘
CONSTRAINTS:
- Output ONLY method code with annotations, signature, and body
- DO NOT include package, imports, class declaration
- DO NOT include fields or constructors
- DO NOT generate helper methods - only the single target method
- Allowed imports: exactly those in <ClassName>
- Forbidden: new imports, wildcard imports, fully-qualified references
- If something is missing: rewrite the logic, do not extend dependencies
- Provide ONLY Java code without explanations
```

---

## Technical Implementation Notes

### Automatic Project Type Detection
The system automatically detects project characteristics from imports:
- `has_servlet`: `javax.servlet` or `jakarta.servlet`
- `has_spring`: `springframework`
- `has_jpa`: `javax.persistence` or `jakarta.persistence`
- `has_ejb`: `javax.ejb` or `jakarta.ejb`
- `has_cdi`: `javax.inject` or `jakarta.inject`
- `has_jndi`: `javax.naming`
- `has_static_utils`: Calls to `Log.`, `TradeConfig.`, `Logger.`

### Coverage Annotations on Methods
The system injects `// NOT COVERED` comments on lines not exercised by tests, extracted from the JaCoCo XML report.

### Abstract Class Handling
If the target class is abstract, an instantiation template via anonymous subclass with minimal implementations of abstract methods is injected.

### Exception Context Injection
If the target method declares `throws CustomException`, the system finds and injects the exception constructors into the prompt, allowing the LLM to correctly instantiate it in negative test cases.
