/**
 * Filtered unit tests for method: encode(String input, final Languages.LanguageSet languageSet)
 * Original class: PhoneticEngine
 * Tests that actually call the target method
 */
package org.apache.commons.codec.language.bm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.language.bm.Languages.LanguageSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo19_unit_encode_PhoneticEngine_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_SephardicPrefix_TUif3_fwch0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.RULES, true, 10);
    String input = "Rabbi John";
    String expected = "(John)";
    assertEquals(expected, engine.encode(input, Languages.LanguageSet.from(new HashSet<>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_GenericPrefix_ZWxF2_bnIE0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, true, 10);
    String input = "Mr. John";
    String expected = "(John)-Mr John";
    assertEquals(expected, engine.encode(input, LanguageSet.from(new HashSet<String>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_GenericNoPrefix_HgxW5_aGaE0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, true, 10);
    String input = "John";
    String expected = "(John)";
    assertEquals(expected, engine.encode(input, LanguageSet.from(new HashSet<String>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_SephardicNoPrefix_QCpF6_fIzo0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.RULES, true, 10);
    String input = "John";
    String expected = "(John)";
    assertEquals(expected, engine.encode(input, Languages.LanguageSet.from(new HashSet<>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_NoConcatenate_pLHK9_kkuh0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, false, 10);
    String input = "John Paul";
    String expected = "(John)-(Paul)";
    assertEquals(expected, engine.encode(input, Languages.LanguageSet.from(new HashSet<>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Concatenate_dQnT8_AGtr0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, true, 10);
    String input = "John Paul";
    String expected = "(John)-(Paul)";
    assertEquals(expected, engine.encode(input, Languages.LanguageSet.from(new HashSet<>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_EmptyInput_jeuu11_cvel0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, true, 10);
    String input = "";
    String expected = "";
    assertEquals(expected, engine.encode(input, Languages.LanguageSet.from(new HashSet<>(Arrays.asList("en")))));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Sephardic_Rules_hbfY0_VkDJ0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.RULES, true, 10);
    String result = engine.encode("d'Avraham", LanguageSet.from(new HashSet<String>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("(Avraham)-", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Ashkenazi_Rules_drWu1_PybR0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.RULES, true, 10);
    String result = engine.encode("Avraham", LanguageSet.from(new HashSet<String>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("Avraham", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Generic_Rules_xHyi2_rRIt0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, true, 10);
    String result = engine.encode("Avraham", LanguageSet.from(new HashSet<String>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("Avraham", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Sephardic_NoRules_GyOQ3_bGsU0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.EXACT, true, 10);
    String result = engine.encode("d'Avraham", LanguageSet.from(new HashSet<String>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("d'Avraham", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Generic_NoRules_nPOr5_dmmG0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, true, 10);
    String result = engine.encode("Avraham", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("Avraham", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Sephardic_Concatenate_zBei6_hsqD0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.RULES, true, 10);
    String result = engine.encode("Avraham ben Yitzchak", LanguageSet.from(new HashSet<>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("(Avraham ben Yitzchak)-", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Ashkenazi_Concatenate_Pjue7_fcyS0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.ASHKENAZI, RuleType.RULES, true, 10);
    String result = engine.encode("Avraham ben Yitzchak", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("Avraham ben Yitzchak", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Generic_Concatenate_NdBh8_evpM0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.RULES, true, 10);
    String result = engine.encode("Avraham ben Yitzchak", LanguageSet.from(new HashSet<String>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("Avraham ben Yitzchak", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Sephardic_Empty_cdRq9_TsQO0() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.RULES, true, 10);
    String result = engine.encode("", LanguageSet.from(new HashSet<>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Sephardic_NoRules_GyOQ3_bGsU0_fid2() {
    PhoneticEngine engine = new PhoneticEngine(NameType.SEPHARDIC, RuleType.EXACT, true, 10);
    String result = engine.encode("", LanguageSet.from(new HashSet<String>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEncode_Generic_NoRules_nPOr5_dmmG0_fid2() {
    PhoneticEngine engine = new PhoneticEngine(NameType.GENERIC, RuleType.EXACT, true, 10);
    String result = engine.encode("", Languages.LanguageSet.from(new HashSet<>(Arrays.asList("hebrew"))));
    Assertions.assertEquals("", result);
    }
}