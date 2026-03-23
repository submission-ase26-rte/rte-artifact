/**
 * Filtered unit tests for method: convert(final Object object, final Class toType)
 * Original class: BasicTypeConverter
 * Tests that actually call the target method
 */
package org.apache.commons.jxpath.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathInvalidAccessException;
import org.apache.commons.jxpath.JXPathTypeConversionException;
import org.apache.commons.jxpath.NodeSet;
import org.apache.commons.jxpath.Pointer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo39_unit_convert_BasicTypeConverter_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertObjectToPrimitive_dxQs1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = new Object();
    Object result = converter.convert(object, int.class);
    Assertions.assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNullToPrimitive_pJvS0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object result = converter.convert(null, int.class);
    Assertions.assertEquals(0, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToPrimitive_lQDw2() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = "123";
    Object result = converter.convert(object, int.class);
    Assertions.assertEquals(123, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNumberToPrimitive_GMnQ4() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = 123.0;
    Object result = converter.convert(object, int.class);
    Assertions.assertEquals(123, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToPrimitive_iLNU5() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = new int[]{1, 2, 3};
    Object result = converter.convert(object, int[].class);
    Assertions.assertArrayEquals(new int[]{1, 2, 3}, (int[]) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertObjectToObject_oNqe9() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = new Object();
    Object result = converter.convert(object, Object.class);
    Assertions.assertEquals(object, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToObject_wcgm10() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = "123";
    Object result = converter.convert(object, Object.class);
    Assertions.assertEquals(object, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertBooleanToObject_vRpm11() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = true;
    Object result = converter.convert(object, Object.class);
    Assertions.assertEquals(object, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNumberToObject_nPAW12() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = 123.0;
    Object result = converter.convert(object, Object.class);
    Assertions.assertEquals(object, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToObject_QgWf13() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = new int[]{1, 2, 3};
    Object result = converter.convert(object, Object.class);
    Assertions.assertEquals(object, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertBooleanToPrimitive_Owml3_vgjf0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object object = true;
    Object result = converter.convert(String.valueOf(object), boolean.class);
    Assertions.assertTrue((boolean) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertCollectionToPrimitive_rYEj6_yzso0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] object = new Integer[]{1, 2, 3};
    Object result = converter.convert(object, int[].class);
    Assertions.assertArrayEquals(new int[]{1, 2, 3}, (int[]) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertCollectionToObject_Czsx14_YRen0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> object = new ArrayList<Integer>();
    object.add(1);
    object.add(2);
    object.add(3);
    Object result = converter.convert(object, Object.class);
    Assertions.assertEquals(object, result);
    }
}