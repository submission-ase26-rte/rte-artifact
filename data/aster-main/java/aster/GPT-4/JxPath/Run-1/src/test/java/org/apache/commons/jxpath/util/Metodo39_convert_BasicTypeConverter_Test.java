/**
 * Extracted tests for method: convert(final Object object, final Class toType)
 * Original class: BasicTypeConverter
 * Source: ASTER GPT-4
 */
package org.apache.commons.jxpath.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo39_convert_BasicTypeConverter_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToSingleElement_lrdL8() {
    BasicTypeConverter converter = new BasicTypeConverter();
    String[] array = {"a", "b", "c"};
    String result = (String) converter.convert(array, String.class);
    assertEquals("Converting array to single element should return the first element", "a", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertEmptyArrayToSingleElement_lunR9() {
    BasicTypeConverter converter = new BasicTypeConverter();
    String[] array = {};
    String result = (String) converter.convert(array, String.class);
    assertEquals("Converting empty array to single element should return empty string", "", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertCollectionToSingleElement_fgen12() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<String> list = Arrays.asList("a", "b", "c");
    String result = (String) converter.convert(list, String.class);
    assertEquals("Converting Collection to single element should return the first element", "a", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertEmptyCollectionToSingleElement_byXf13() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<String> list = new ArrayList<>();
    String result = (String) converter.convert(list, String.class);
    assertEquals("Converting empty Collection to single element should return empty string", "", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToArrayOfSameType_gqDi5_EZnh0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] array = {1, 2, 3};
    Integer[] result = (Integer[]) converter.convert(array, Integer[].class);
    assertArrayEquals(array, result, "Converting array to array of same type should return the same array");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToDifferentArrayType_VSnJ6_oLOu0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] array = {1, 2, 3};
    Double[] result = (Double[]) converter.convert(array, Double[].class);
    assertArrayEquals(new Double[]{1.0, 2.0, 3.0}, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertCollectionToArray_Rsxy10_aTgz0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<String> list = Arrays.asList("a", "b", "c");
    String[] result = (String[]) converter.convert(list, String[].class);
    assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToDate_lzyO27() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Date date = new Date(1234567890L);
    assertEquals(date, converter.convert("1234567890", Date.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToCalendar_txIh28() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1234567890L);
    assertEquals(calendar, converter.convert("1234567890", Calendar.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToLocale_sfbb29() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(Locale.US, converter.convert("en_US", Locale.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNullToPrimitiveInt_WYIH0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(0, converter.convert(null, int.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNullToPrimitiveBoolean_mQEk1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(false, converter.convert(null, boolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertObjectToObject_NfXL2() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Object obj = new Object();
    assertEquals(obj, converter.convert(obj, Object.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToString_Utuj3() {
    BasicTypeConverter converter = new BasicTypeConverter();
    String str = "test";
    assertEquals(str, converter.convert(str, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertBooleanToNumber_xyet4() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(1, converter.convert(true, Integer.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNumberToBoolean_JNpj5() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(true, converter.convert(1, Boolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToPrimitiveInt_Qwwp6() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(123, converter.convert("123", int.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToArray_kjrN8() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] intArray = {1, 2, 3};
    Integer[] result = (Integer[]) converter.convert(intArray, Integer[].class);
    assertArrayEquals(intArray, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertCollectionToArray_WNOG9() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> list = Arrays.asList(1, 2, 3);
    Integer[] result = (Integer[]) converter.convert(list, Integer[].class);
    assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertArrayToCollection_AxZb10() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] intArray = {1, 2, 3};
    List<Integer> result = (List<Integer>) converter.convert(intArray, List.class);
    assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertCollectionToCollection_OlMr11() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> list = Arrays.asList(1, 2, 3);
    List<Integer> result = (List<Integer>) converter.convert(list, List.class);
    assertEquals(list, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToBoolean_IAaJ14() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(true, converter.convert("true", Boolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertNumberToString_yqwc16() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals("123", converter.convert(123, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertBooleanToString_BuIw17() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals("true", converter.convert(true, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToInteger_scMQ18() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(123, converter.convert("123", Integer.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToLong_jLow21() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(123L, converter.convert("123", Long.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToShort_PCFt22() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals((short) 123, converter.convert("123", Short.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToByte_aBAi23() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals((byte) 123, converter.convert("123", Byte.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToCharacter_PQmN24() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals('a', converter.convert("a", Character.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToBigInteger_UYXB25() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(new BigInteger("123"), converter.convert("123", BigInteger.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToBigDecimal_SZXY26() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(new BigDecimal("123.45"), converter.convert("123.45", BigDecimal.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToPrimitiveDouble_xOOU7_vNaU0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(123.45, (Double) converter.convert("123.45", double.class), 0.001);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToDouble_vnkN19_euKb0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(123.45, (Double) converter.convert("123.45", Double.class), 0.001);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToFloat_ZoMy20_dqjd0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(123.45f, (float) converter.convert("123.45", Float.class), 0.001f);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToFile_Ezwd32_BCBW0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(new java.io.File("test.txt"), converter.convert("test.txt", java.io.File.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConvertStringToClass_rJGI33_jLER0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertEquals(String.class, converter.convert("java.lang.String", Class.class));
    }
}