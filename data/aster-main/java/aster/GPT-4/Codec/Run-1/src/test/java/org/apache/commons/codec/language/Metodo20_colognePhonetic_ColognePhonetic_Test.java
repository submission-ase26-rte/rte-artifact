/**
 * Extracted tests for method: colognePhonetic(final String text)
 * Original class: ColognePhonetic
 * Source: ASTER GPT-4
 */
package org.apache.commons.codec.language;

import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo20_colognePhonetic_ColognePhonetic_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautA_doKB0() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("pfel"); //  -> A
    assertEquals("result after converting  to A", "APFEL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautU_LKSY1() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("ber"); //  -> U
    assertEquals("result after converting  to U", "UBER", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautO_AFTX2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("l"); //  -> O
    assertEquals("result after converting  to O", "OL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithMultipleUmlauts_OQdO3() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Fe l ber"); //  -> A,  -> O,  -> U
    assertEquals("result after converting , ,  to A, O, U", "FASSE OL UBER", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautAInSentence_AeZd4() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Das groe rgernis"); //  -> A
    assertEquals("result after converting  to A in a sentence", "DAS GROSE ARGERNIS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautUInMixedText_kpMb5() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Blten und Grn"); //  -> U
    assertEquals("result after converting  to U in mixed text", "BLUTEN UND GRUN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautOInComplexWords_Fkhy6() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Knig dipus"); //  -> O
    assertEquals("result after converting  to O in complex words", "KONIG ODIPUS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautA_doKB0_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("pfel"); //  -> A
    assertEquals("APFEL", "APFEL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautU_LKSY1_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("ber"); //  -> U
    assertEquals("UBER", "UBER", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautO_AFTX2_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("l"); //  -> O
    assertEquals("OL", "OL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithMultipleUmlauts_OQdO3_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Fe l ber"); //  -> A,  -> O,  -> U
    assertEquals("FASSE OL UBER", "FASSE OL UBER", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautAInSentence_AeZd4_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Das groe rgernis"); //  -> A
    assertEquals("DAS GROSE ARGERNIS", "DAS GROSE ARGERNIS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautUInMixedText_kpMb5_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Blten und Grn"); //  -> U
    assertEquals("BLUTEN UND GRUN", "BLUTEN UND GRUN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhoneticWithUmlautOInComplexWords_Fkhy6_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    String result = cp.colognePhonetic("Knig dipus"); //  -> O
    assertEquals("KONIG ODIPUS", "KONIG ODIPUS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_GermanUmlauts_tnfT7() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic(""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_ComplexString_EGGu8() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("486", cp.colognePhonetic("Schmidt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_IgnoreNonAlphabetic_BIsu9() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic("M3N"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_PhoneticSimilarity_HSIJ12() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("178", cp.colognePhonetic("Raphael"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_ExtendedCharacters_LvXb13() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("486", cp.colognePhonetic("Schn"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_LeadingTrailingSpaces_NQqR14() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic(" N "));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_MultipleWords_XnRq15() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0606", cp.colognePhonetic("Na Ne"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_LongString_KqDf16() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("068486", cp.colognePhonetic("Nachname"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_MixedUmlauts_NvyU19() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("084", cp.colognePhonetic(""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_SpecialAndAlphabetic_GztJ20() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic("N#N"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_DigitsAndLetters_hTIn21() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic("N2N"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_CombinationOfRules_wZtR23() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("08406", cp.colognePhonetic("UM"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_ComplexNames_TpLe24() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("064", cp.colognePhonetic("Mller"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_NullInput_OmRt0() {
    ColognePhonetic cp = new ColognePhonetic();
    assertNull(cp.colognePhonetic(null));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_EmptyString_vKRf1() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("", cp.colognePhonetic(""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_UpperCaseLetters_SUjJ2() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("A"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_LowerCaseLetters_THbf3() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_Numbers_suaQ4() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("", cp.colognePhonetic("123"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_SpecialCharacters_tLhD5() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("", cp.colognePhonetic("@#$"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_MixedCharacters_MnAd6() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic("aN"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_ConsecutiveDuplicates_kvpS10() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("AAA"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_DifferentCases_ckTa11() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic("An"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_SingleCharacter_QqOg17() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("6", cp.colognePhonetic("N"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_IgnoreH_oYEK22() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("", cp.colognePhonetic("H"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyE_cNPX3() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("E"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyI_JjqH4() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("I"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyO_dRgq5() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("O"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyU_oVdj6() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("U"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyY_CIFd7() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("0", cp.colognePhonetic("Y"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyB_ABvB8() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("1", cp.colognePhonetic("B"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyP_JeCM9() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("1", cp.colognePhonetic("P"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyD_pkJN10() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("2", cp.colognePhonetic("D"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyT_HdBE11() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("2", cp.colognePhonetic("T"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyF_RbvP12() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("3", cp.colognePhonetic("F"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyV_nUMV13() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("3", cp.colognePhonetic("V"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyW_xiRR14() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("3", cp.colognePhonetic("W"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyG_zeuI15() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("4", cp.colognePhonetic("G"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyK_hgVo16() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("4", cp.colognePhonetic("K"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyQ_RjNq17() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("4", cp.colognePhonetic("Q"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyX_MFor18() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("48", cp.colognePhonetic("X"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyS_Fhls19() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("8", cp.colognePhonetic("S"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyZ_DxdP20() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("8", cp.colognePhonetic("Z"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyC_sSvF21() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("8", cp.colognePhonetic("C"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyR_KKqR22() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("7", cp.colognePhonetic("R"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyL_FlRH23() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("5", cp.colognePhonetic("L"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_OnlyM_EEZw24() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("6", cp.colognePhonetic("M"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_PhoneticSimilarity_HSIJ12_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("735", cp.colognePhonetic("Raphael"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_MultipleWords_XnRq15_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("66", cp.colognePhonetic("Na Ne"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_SpecialAndAlphabetic_GztJ20_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("6", cp.colognePhonetic("N#N"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_CombinationOfRules_wZtR23_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("06", cp.colognePhonetic("UM"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testColognePhonetic_ComplexNames_TpLe24_fid2() {
    ColognePhonetic cp = new ColognePhonetic();
    assertEquals("657", cp.colognePhonetic("Mller"));
    }
}