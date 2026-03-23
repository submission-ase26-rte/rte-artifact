/**
 * Extracted tests for method: toString()
 * Original class: OptionGroup
 * Source: ASTER GPT-4
 */
package org.apache.commons.cli;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Metodo3_toString_OptionGroup_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringEmptyOptionGroup_YbEN0() {
    OptionGroup optionGroup = new OptionGroup();
    assertEquals("[]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringSingleShortOption_nQvp1() {
    OptionGroup optionGroup = new OptionGroup();
    Option option = new Option("a", "alpha", false, "Alpha option");
    optionGroup.addOption(option);
    assertEquals("[-a Alpha option]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringSingleLongOption_Riwn2() {
    OptionGroup optionGroup = new OptionGroup();
    Option option = new Option(null, "beta", false, "Beta option");
    optionGroup.addOption(option);
    assertEquals("[--beta Beta option]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringMultipleOptions_YIYB3() {
    OptionGroup optionGroup = new OptionGroup();
    Option option1 = new Option("a", "alpha", false, "Alpha option");
    Option option2 = new Option("b", null, false, "Beta option");
    optionGroup.addOption(option1);
    optionGroup.addOption(option2);
    assertEquals("[-a Alpha option, -b Beta option]", optionGroup.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToStringOptionWithoutDescription_XqGs4() {
    OptionGroup optionGroup = new OptionGroup();
    Option option = new Option("c", "charlie", false, null);
    optionGroup.addOption(option);
    assertEquals("[-c]", optionGroup.toString());
    }
}