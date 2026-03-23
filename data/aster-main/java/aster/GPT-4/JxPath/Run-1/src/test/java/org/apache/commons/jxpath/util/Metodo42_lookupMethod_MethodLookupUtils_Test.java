/**
 * Extracted tests for method: lookupMethod(Class targetClass, final String name, final Object[] parameters)
 * Original class: MethodLookupUtils
 * Source: ASTER GPT-4
 */
package org.apache.commons.jxpath.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.apache.commons.jxpath.JXPathException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo42_lookupMethod_MethodLookupUtils_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithNullParameters_ULSM0() {
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", null);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithEmptyParameters_HLxV1() {
    Object[] parameters = {};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithNullFirstParameter_vmYE2() {
    Object[] parameters = {null};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithNoMatchType_yJNq3() {
    Object[] parameters = {new Object()};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithConversion_ahEk4() {
    Object[] parameters = {"test", 1};
    Method result = MethodLookupUtils.lookupMethod(Object.class, "substring", parameters);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithExactMatch_JopO5() {
    Object[] parameters = {"test", 1, 2};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithNullInParameters_MsOG6() {
    Object[] parameters = {"test", null};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithNoExactMatch_SdJU7() {
    Object[] parameters = {"test", 1, 2};
    Method result = MethodLookupUtils.lookupMethod(String.class, "contains", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithAmbiguousMatch_gaGE8() {
    Object[] parameters = {new StringBuilder(), "test"};
    try {
    Method result = MethodLookupUtils.lookupMethod(Appendable.class, "append", parameters);
    fail("Expected an JXPathException to be thrown");
    } catch (JXPathException e) {
    assertEquals("Ambiguous method call: append", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithNoMethodsFound_ZOtY9() {
    Object[] parameters = {5};
    Method result = MethodLookupUtils.lookupMethod(Integer.class, "nonExistentMethod", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithStaticMethod_DAfX10() {
    Object[] parameters = {5};
    Method result = MethodLookupUtils.lookupMethod(Math.class, "abs", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithInstanceMethod_tOQG11() {
    Object[] parameters = {"test"};
    Method result = MethodLookupUtils.lookupMethod(String.class, "length", parameters);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodWithInheritance_XnVE12() {
    Object[] parameters = {"test"};
    Method result = MethodLookupUtils.lookupMethod(Object.class, "toString", parameters);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_NullParameters_fpHQ0() {
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", null);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_EmptyParameters_jFkB1() {
    Object[] parameters = {};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_FirstParameterNull_XrXl2() {
    Object[] parameters = {null};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_NoMatchType_JqQK3() {
    Object[] parameters = {new Object()};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_ExactMatch_kISD4() throws NoSuchMethodException {
    Object[] parameters = {new String("example"), 0, 3};
    Method expected = String.class.getMethod("substring", int.class, int.class);
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_ExactMatchSingleParameter_ROoP5() throws NoSuchMethodException {
    Object[] parameters = {new String("example"), 0};
    Method expected = String.class.getMethod("substring", int.class);
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_NullInParameters_jzul6() {
    Object[] parameters = {new String("example"), null};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_AmbiguousCall_XCLY7() {
    Object[] parameters = {new String("example"), 0, 3.0};
    try {
    MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    fail("Expected JXPathException");
    } catch (JXPathException e) {
    assertEquals("Ambiguous method call: substring", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_NoSuchMethod_kqGn8() {
    Object[] parameters = {new String("example"), "test"};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_StaticMethodExclusion_PaZt9() {
    Object[] parameters = {Math.class, 0.0};
    Method result = MethodLookupUtils.lookupMethod(Math.class, "abs", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_DifferentClass_KJLE11() {
    Object[] parameters = {new Integer(123), 0, 3};
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_MatchWithSuperType_vLoX12() throws NoSuchMethodException {
    Object[] parameters = {new Object(), "test"};
    Method expected = Object.class.getMethod("toString");
    Method result = MethodLookupUtils.lookupMethod(Object.class, "toString", parameters);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_InvalidMethodName_QelU13() {
    Object[] parameters = {new String("example"), 0};
    Method result = MethodLookupUtils.lookupMethod(String.class, "nonExistentMethod", parameters);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_ValidMethodWithCorrectTypes_MjUM15() throws NoSuchMethodException {
    Object[] parameters = {new String("example"), 0, 7};
    Method expected = String.class.getMethod("substring", int.class, int.class);
    Method result = MethodLookupUtils.lookupMethod(String.class, "substring", parameters);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethod_WithInheritance_ZmUH16() throws NoSuchMethodException {
    Object[] parameters = {new Throwable("error"), "error"};
    Method expected = Throwable.class.getMethod("getMessage");
    Method result = MethodLookupUtils.lookupMethod(Throwable.class, "getMessage", parameters);
    assertEquals(expected, result);
    }
}