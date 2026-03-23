/**
 * Filtered unit tests for method: toString()
 * Original class: OptionGroup
 * Tests that actually call the target method
 */
package org.apache.commons.cli;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo3_unit_toString_OptionGroup_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_SingleOptionWithOpt_jqxt1() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt", "description"));
    assertEquals("[-opt, description]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_SingleOptionWithoutOpt_FTIY2() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option(null, "description"));
    assertEquals("[--description]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_MultipleOptionsWithOpt_CzGu3() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt1", "description1"));
    optionGroup.getOptions().add(new Option("opt2", "description2"));
    assertEquals("[-opt1, description1, -opt2, description2]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_MultipleOptionsWithoutOpt_CsuQ4() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option(null, "description1"));
    optionGroup.getOptions().add(new Option(null, "description2"));
    assertEquals("[--description1, --description2]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_MixedOptions_ZXYl5() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt1", "description1"));
    optionGroup.getOptions().add(new Option(null, "description2"));
    assertEquals("[-opt1, description1, --description2]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithDescription_eFzU6() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt1", "description1"));
    optionGroup.getOptions().add(new Option(null, "description2 with space"));
    assertEquals("[-opt1, description1, --description2 with space]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithCommaInDescription_VvyM8() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt1", "description, with, comma"));
    assertEquals("[-opt1, description, with, comma]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithCommaInOpt_ZYrP9() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt, with, comma", "description"));
    assertEquals("[-opt, with, comma, description]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithCommaInLongOpt_yUha10() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option(null, "description, with, comma"));
    assertEquals("[--description, with, comma]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithMultipleCommas_LmLm11() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt, with, multiple, commas", "description, with, multiple, commas"));
    assertEquals("[-opt, with, multiple, commas, description, with, multiple, commas]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithEscapedCommas_shgP12() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt,\\,with,comma", "description,\\,with,comma"));
    assertEquals("[-opt,\\,with,comma, description,\\,with,comma]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_OptionsWithBackslashes_VegS13() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt,with\\backslash,comma", "description,with\\backslash,comma"));
    assertEquals("[-opt,with\\backslash,comma, description,with\\backslash,comma]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_EmptyOptionGroup_dExk0() {
    OptionGroup optionGroup = new OptionGroup();
    assertEquals("[]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_EmptyOptions_aQQo0() {
    OptionGroup optionGroup = new OptionGroup();
    String result = optionGroup.toString();
    assert result.equals("[]");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_SingleOption_ASuR1() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt", "description"));
    String result = optionGroup.toString();
    assert result.equals("[-opt description]");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_MultipleOptions_iHKA2() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("opt1", "description1"));
    optionGroup.getOptions().add(new Option("opt2", "description2"));
    String result = optionGroup.toString();
    assert result.equals("[-opt1 description1, --opt2 description2]");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_SingleLongOption_wbWW3_QmRY1() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("o", "longOpt", true, "description"));
    String result = optionGroup.toString();
    assert result.equals("[--longOpt, description]");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_MultipleLongOptions_rGVF4_GbKq0() {
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.getOptions().add(new Option("o1", "longOpt1", true, "description1"));
    optionGroup.getOptions().add(new Option(null, "longOpt2", false, "description2"));
    String result = optionGroup.toString();
    assert result.equals("[--longOpt1 description1, --longOpt2 description2]");
    }
}