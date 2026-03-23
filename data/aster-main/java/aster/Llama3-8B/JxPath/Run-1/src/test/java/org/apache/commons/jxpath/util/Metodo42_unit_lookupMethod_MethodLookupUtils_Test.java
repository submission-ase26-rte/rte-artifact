/**
 * Filtered unit tests for method: lookupMethod(Class targetClass, final String name, final Object[] parameters)
 * Original class: MethodLookupUtils
 * Tests that actually call the target method
 */
package org.apache.commons.jxpath.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.commons.jxpath.ExpressionContext;
import org.apache.commons.jxpath.JXPathException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo42_unit_lookupMethod_MethodLookupUtils_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodStaticMethod_FEuu8() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "method", new Object[]{String.class});
    assertNotNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupStaticMethodNonStaticMethod_DuAN1_ttPa0() {
    try {
    Class targetClass = Class.forName("TargetClass");
    Method method = MethodLookupUtils.lookupMethod(targetClass, "methodName", new Object[]{"param1"});
    assertNull(method);
    } catch (ClassNotFoundException e) {
    fail("ClassNotFoundException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupStaticMethodNullParameterTypes_Koil7_BrTf0() {
    try {
    Class targetClass = Class.forName("TargetClass");
    Method method = MethodLookupUtils.lookupMethod(targetClass, "methodName", null);
    assertNull(method);
    } catch (ClassNotFoundException e) {
    fail("ClassNotFoundException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupStaticMethodNonStaticMethodWithNullParameter_xDOI12_wqXv0() {
    try {
    Class targetClass = Class.forName("TargetClass");
    Method method = MethodLookupUtils.lookupMethod(targetClass, "methodName", new Object[]{null});
    assertNull(method);
    } catch (ClassNotFoundException e) {
    fail("ClassNotFoundException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodNullParameters_TDSp0() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "method", null);
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodEmptyParameters_lQXT1() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "method", new Object[0]);
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodNullParameter_dBzh2() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "method", new Object[]{null});
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodNoMatch_letG3() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "method", new Object[]{Object.class});
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodMethodNotFound_bffn7() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "nonExistingMethod", new Object[]{String.class});
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodSingleParameter_RzMZ2() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "getMethod", new Object[]{"test"});
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodStaticMethod_yXrh7() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "forName", new Object[]{"test"});
    assertNotNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodNonStaticMethod_uzdS8() {
    Method method = MethodLookupUtils.lookupMethod(Object.class, "toString", new Object[0]);
    assertNotNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodMultipleMatches_uAkE9() {
    try {
    MethodLookupUtils.lookupMethod(Class.class, "getMethods", new Object[]{});
    fail("Expected JXPathException");
    } catch (JXPathException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodExactMatch_cwlq4_RhHb0() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "getName", new Object[]{null});
    assertNotNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodNullParameters_tYPJ0() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "getMethod", null);
    assertNull(method);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLookupMethodEmptyParameters_DEvi1() {
    Method method = MethodLookupUtils.lookupMethod(Class.class, "getMethod", new Object[0]);
    assertNull(method);
    }
}