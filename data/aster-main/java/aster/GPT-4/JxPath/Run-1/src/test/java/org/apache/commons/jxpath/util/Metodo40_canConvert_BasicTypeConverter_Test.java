/**
 * Extracted tests for method: canConvert(final Object object, final Class toType)
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

public class Metodo40_canConvert_BasicTypeConverter_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_NullObject_lCld0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(null, Object.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_AssignableType_YtnZ1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("Hello", String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_ToStringType_btKQ2() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(123, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_BooleanToNumber_VRZv3() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(true, Integer.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_NumberToBoolean_WJUi4() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(1, Boolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_StringToPrimitive_XjLR5() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("123", int.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_EmptyArrayToCollection_Uysu6() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(new int[]{}, ArrayList.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_NonEmptyArrayToCollection_btpp7() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(new int[]{1, 2, 3}, ArrayList.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_ArrayToArrayOfDifferentType_klPL8() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(new int[]{1, 2, 3}, Double[].class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_CollectionToArray_rAXW9() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> list = Arrays.asList(1, 2, 3);
    assertTrue(converter.canConvert(list, Integer[].class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_CollectionToCollection_kJgm10() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> list = Arrays.asList(1, 2, 3);
    assertTrue(converter.canConvert(list, HashSet.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_CollectionToDifferentCollection_LKNm11() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> list = Arrays.asList(1, 2, 3);
    assertTrue(converter.canConvert(list, ArrayList.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_EmptyCollectionToType_linP12() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<String> list = new ArrayList<>();
    assertTrue(converter.canConvert(list, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvert_NonEmptyCollectionToType_yVjP13() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<String> list = Arrays.asList("hello");
    assertTrue(converter.canConvert(list, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertNumberToAtomicBoolean_zvOx23_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(1, java.util.concurrent.atomic.AtomicBoolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToAtomicBoolean_auDV24_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("true", java.util.concurrent.atomic.AtomicBoolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToUUID_yIiL28_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("123e4567-e89b-12d3-a456-426614174000", java.util.UUID.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToLocale_BRdN29_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("en_US", java.util.Locale.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToCurrency_aPFu30_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("USD", java.util.Currency.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToTimeZone_Lgtp31_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("GMT", java.util.TimeZone.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToCharset_dkSh32_fid1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("UTF-8", java.nio.charset.Charset.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertNullObject_uucg0() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(null, String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertSameType_ifzU1() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(123, Integer.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToBoolean_geBL2() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("true", Boolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToCharacter_NeIH3() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("a", Character.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToByte_YrzW4() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("1", Byte.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToShort_HlnM5() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("1", Short.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToInteger_rZiY6() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("1", Integer.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToLong_PkjQ7() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("1", Long.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToFloat_Cgfs8() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("1.0", Float.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToDouble_lHMM9() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("1.0", Double.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertBooleanToNumber_OLJi10() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(true, Integer.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertNumberToBoolean_RCUi11() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(1, Boolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertArrayToArray_orkV12() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] intArray = {1, 2, 3};
    assertTrue(converter.canConvert(intArray, Integer[].class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertArrayToCollection_PVfm13() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] intArray = {1, 2, 3};
    assertTrue(converter.canConvert(intArray, List.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertCollectionToArray_ZRjx14() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> intList = Arrays.asList(1, 2, 3);
    assertTrue(converter.canConvert(intList, Integer[].class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertCollectionToCollection_PxLQ15() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> intList = Arrays.asList(1, 2, 3);
    assertTrue(converter.canConvert(intList, List.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertEmptyArrayToArray_AClE16() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] intArray = {};
    assertTrue(converter.canConvert(intArray, Integer[].class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertEmptyArrayToCollection_tvEa17() {
    BasicTypeConverter converter = new BasicTypeConverter();
    Integer[] intArray = {};
    assertTrue(converter.canConvert(intArray, List.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertEmptyCollectionToArray_RGEC18() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> intList = Collections.emptyList();
    assertTrue(converter.canConvert(intList, Integer[].class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertEmptyCollectionToCollection_GZPJ19() {
    BasicTypeConverter converter = new BasicTypeConverter();
    List<Integer> intList = Collections.emptyList();
    assertTrue(converter.canConvert(intList, List.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertBooleanToAtomicBoolean_drxU22() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert(true, java.util.concurrent.atomic.AtomicBoolean.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToBigInteger_FEaR25() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("123456789", java.math.BigInteger.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToBigDecimal_wfSj26() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("12345.6789", java.math.BigDecimal.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToDate_oprq27() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("2023-01-01", java.util.Date.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToCurrency_aPFu30() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertFalse(converter.canConvert("USD", java.util.Currency.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToTimeZone_Lgtp31() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertFalse(converter.canConvert("GMT", java.util.TimeZone.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToCharset_dkSh32() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertFalse(converter.canConvert("UTF-8", java.nio.charset.Charset.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToFile_vLKn33() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("path/to/file", java.io.File.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCanConvertStringToURL_ymhu34() {
    BasicTypeConverter converter = new BasicTypeConverter();
    assertTrue(converter.canConvert("http://example.com", java.net.URL.class));
    }
}