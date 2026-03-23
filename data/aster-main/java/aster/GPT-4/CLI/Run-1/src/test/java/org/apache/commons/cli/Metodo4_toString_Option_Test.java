/**
 * Extracted tests for method: toString()
 * Original class: Option
 * Source: ASTER GPT-4
 */
package org.apache.commons.cli;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo4_toString_Option_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithSingleArg_sTkM2() {
    Option option = new Option("o", "option", true, "Option with single arg");
    String expected = "[ Option o option [ARG] :: Option with single arg :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithoutArgs_fBKK3() {
    Option option = new Option("o", "option", false, "Option without args");
    String expected = "[ Option o option :: Option without args :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithNullLongOption_nHRy4() {
    Option option = new Option("o", null, false, "Option with null long option");
    String expected = "[ Option o :: Option with null long option :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithMultipleArgs_QnyM1_ocGm0() {
    Option option = new Option("o", "option", true, "Option with multiple args");
    String expected = "[ Option o option [ARG...] :: Option with multiple args :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithSingleArg_sTkM2_fid2() {
    Option option = new Option("o", "option", true, "Option with single arg");
    String expected = "[ Option o option [ARG] :: Option with single arg :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithoutArgs_fBKK3_fid2() {
    Option option = new Option("o", "option", false, "Option without args");
    String expected = "[ Option o option :: Option without args :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithNullLongOption_nHRy4_fid2() {
    Option option = new Option("o", null, false, "Option with null long option");
    String expected = "[ Option o :: Option with null long option :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithMultipleArgs_QnyM1_ocGm0_fid2() {
    Option option = new Option("o", "option", true, "Option with multiple args");
    String expected = "[ Option o option [ARG] :: Option with multiple args :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithLongOptionAndArg_LiYG0() {
    Option option = new Option("o", "longOption", true, "description");
    String expected = "[ Option o longOption [ARG] :: description :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithOnlyShortOptionAndArg_DaRV1() {
    Option option = new Option("o", true, "description");
    String expected = "[ Option o [ARG] :: description :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithOnlyShortOptionNoArg_QObN2() {
    Option option = new Option("o", "description");
    String expected = "[ Option o :: description :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithLongOptionNoArg_juTc3() {
    Option option = new Option("o", "longOption", false, "description");
    String expected = "[ Option o longOption :: description :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithDeprecatedOption_MNMC4_nLHH0() {
    Option option = new Option("o", "longOption", false, "description");
    String expected = "[ Option o longOption [DEPRECATED] :: description :: null ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithLongOptionAndArg_LiYG0_fid1() {
    Option option = new Option("o", "longOption", true, "description");
    String expected = "[ Option o longOption [ARG] :: description :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithOnlyShortOptionAndArg_DaRV1_fid1() {
    Option option = new Option("o", true, "description");
    String expected = "[ Option o [ARG] :: description :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithOnlyShortOptionNoArg_QObN2_fid1() {
    Option option = new Option("o", "description");
    String expected = "[ Option o :: description :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringWithLongOptionNoArg_juTc3_fid1() {
    Option option = new Option("o", "longOption", false, "description");
    String expected = "[ Option o longOption :: description :: class java.lang.String ]";
    assertEquals(expected, option.toString());
    }
}