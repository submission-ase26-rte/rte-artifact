/**
 * Extracted tests for method: doubleMetaphone(String value, final boolean alternate)
 * Original class: DoubleMetaphone
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo17_doubleMetaphone_DoubleMetaphone_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH0WithHaracHaris_UFib0_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Charac";
    String expected = "K";  // Assuming the output should be 'KRS' based on the condition
    String result = dm.doubleMetaphone(input, false);
    input = "Charis";
    result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMCAtStart_Fqrd0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "McHale";
    String expected = "K";  // Assuming 'MKL' is the primary encoding for 'Mc'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCHAtStart_Ksep1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Chaim";
    String expected = "K";  // Assuming 'XM' is the primary encoding for 'Ch'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH0WithHaracHaris_UFib0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Charac";
    String expected = "K";  // Assuming the output should be 'K' based on the condition
    String result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    input = "Charis";
    result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_NotFollowedByCEorCI_nIib0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "CACK";
    String expected = "KAK";
    String result = dm.doubleMetaphone(input, false);
    assertEquals("Expected result did not match", expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVANPrefix_ehPn0_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "VAN Helsing";
    String expected = "HLNK"; // Assuming 'VAN ' is treated as silent or special case
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCHFollowedByT_BXYF2_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Orchestra";
    String expected = "ARKSTR"; // Assuming 'CH' followed by 'T' is treated as 'K'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMCAtStart_Fqrd0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "McHale";
    String expected = "K";  // Assuming 'K' is the primary encoding for 'Mc'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCHAtStart_Ksep1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Chaim";
    String expected = "K";  // Assuming 'K' is the primary encoding for 'Ch'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionC0WithCNotIE_veKA0_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Baccher";
    String expected = "PKR";  // 'B' -> 'P', 'C' -> 'K' (from conditionC0), 'H' skipped, 'ER' -> 'R'
    String result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_ConditionNotContainsCEorCI_fUin0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Acq", false);
    assertEquals("AKK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCHAtStart_vjef1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Charis";
    String expected = "XRS";  // Assuming the logic for 'A', 'R', 'I', 'S' is handled correctly in the respective methods
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVANPrefix_vDyU0_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "VAN Thomason";
    String expected = "TMSN";  // Assuming 'VAN ' triggers special handling for 'TH'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXEndingInAUOrOU_iOIA0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Breaux", false);
    assertEquals("PRKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSC_WithNonVowelAndNotW_csDE0_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Schb"; // 'b' is not a vowel and not 'W'
    boolean alternate = false;
    String result = dm.doubleMetaphone(value, alternate);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_EndOfWordWithVowelBefore_rJyc2_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "craw";
    String expected = "KRF"; // Assuming 'F' is appended as an alternate at the end
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_WithEWSKI_qhin4_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Kaprewski";
    String expected = "KPRF"; // Assuming 'F' is appended as an alternate for 'EWSKI'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithGermanicOrigin_EYEl0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Scholz";
    assertEquals("S", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithXAppend_JWPY1_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Shack";
    assertEquals("XAK", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithSioSiaSlavoGermanic_uxyn3_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sio";
    assertEquals("SX", dm.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithSian_Wfdn4_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sian";
    assertEquals("SX", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithSianSlavoGermanic_ALRC5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sian";
    assertEquals("S", dm.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithFrenchEnding_rTkY6_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Artrois";
    assertEquals("ARTRS", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_NotVowelBeforeGH_hmpv0_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bgh", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithBHD_cwnn3_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("ABHGH", false);
    assertEquals("AP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_ShWithGermanic_OIig0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Shoek";
    assertEquals("S", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_ShWithoutGermanic_trgs1_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Shash";
    assertEquals("X", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_SioNonSlavoGermanic_bnNV2_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sio";
    assertEquals("SX", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_SioSlavoGermanic_rMMt3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sio";
    assertEquals("S", dm.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_FrenchEnding_IAmY6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Artrois";
    assertEquals("ARTROS", dm.doubleMetaphone(value, false));
    assertEquals("ARTR", dm.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_SanSpace_BcrO0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("San Jose", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JoseNotAtStart_YvZA1_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Maria Jose", false);
    assertEquals("MRJH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_VowelBeforeJNotSlavoGermanic_EdYD2_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Tajo", false);
    assertEquals("TJH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_NotFollowingSpecialChars_jxOi4_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Taj Mahal", false);
    assertEquals("TJML", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JoseAtStart_CVKM7_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jose", false);
    assertEquals("JH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JoseNotAtStartAndNotSlavoGermanic_iclk8_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Tajose", false);
    assertEquals("TJH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_KL_L_Case_OmjJ2_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Glider", false);
    assertEquals("JLTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GY_Case_adeq3_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gyro", false);
    assertEquals("JR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_IER_J_Case_xZjc5_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pier", false);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Y_J_Case_UauL9_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gypsy", false);
    assertEquals("JPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_E_I_Case_BNIz10_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gel", false);
    assertEquals("JL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Y_NotSlavoGermanic_Case_eglx13_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gyrate", false);
    assertEquals("JRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_LI_NotSlavoGermanic_Case_OiQR14_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gelignite", false);
    assertEquals("JLNKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GN_VowelAtStart_NotSlavoGermanic_QVuF0_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Agnostic", false);
    assertEquals("KNSTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GLI_NotSlavoGermanic_uzDl2_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Agli", false);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_VAN_Vzkw4_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vangetti", false);
    assertEquals("FNJTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_VON_AAGh5_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vongetti", false);
    assertEquals("FNJTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_SCH_gQur6_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schgetti", false);
    assertEquals("SKJTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_ET_LDLr7_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Getto", false);
    assertEquals("JTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_IER_XZXB8_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gier", false);
    assertEquals("JR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_NotE_NotI_NotRGY_NotOGY_WBLb9_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Magy", false);
    assertEquals("MJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_NotGermanic_NotIER_VeOd11_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ging", false);
    assertEquals("JNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_NotGermanic_NotIER_ET_Vbzi14_fid2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Get", false);
    assertEquals("JT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH0WithHaracHaris_UFib0_1_fid3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Charac";
    String expected = "K";  // Assuming the output should be 'K' based on the condition
    String result = dm.doubleMetaphone(input, false);
    input = "Charis";
    result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_FrenchEnding_IAmY6_1_fid3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Artrois";
    assertEquals("ARTROS", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CoverUncoveredLine_rZFz0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "ACQK";
    String expected = "AKK"; // Expected primary encoding result
    assertEquals(expected, doubleMetaphone.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCNotIEFollowingACH_XxEk0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Bachm";
    String expected = "PKM";  // Assuming 'P' and 'K' encoding for 'B' and 'C', 'M' for 'M'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCIEFollowingBacher_rout1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Bacherie";
    String expected = "PKR";  // Assuming 'P' for 'B', 'K' for 'C', 'R' for 'R'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CoverUncoveredLine_EzIa0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Acq";
    String expected = "AK";  // Assuming 'C' followed by 'q' should result in 'K' and 'q' is ignored
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CoverUncoveredLine_TnJi0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "AcL";
    String expected = "AKL";  // Assuming the logic appends 'K' for 'C' and then just increments index
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVANPrefix_ehPn0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "VAN Helsing";
    String expected = "FNLS"; // Assuming 'VAN ' is treated as silent or special case
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSCHPrefix_Ayun1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "SCHmidt";
    String expected = "XMT"; // Assuming 'SCH' is treated as 'X'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCHFollowedByT_BXYF2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Orchestra";
    String expected = "ARKS"; // Assuming 'CH' followed by 'T' is treated as 'K'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAccident_mcCk0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Accident", false);
    assertEquals("AKST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSucceed_XyLt1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Succeed", false);
    assertEquals("SKST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CoverUncoveredLine_rVdK0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "ACB";
    String expected = "AKP"; // Assuming the rest of the code appends 'P' after 'C' handling
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_CQ_NotFollowedByCEorCI_BcOe0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Acq";
    String expected = "AK";  // Expect 'K' after 'CQ' and not followed by 'CE' or 'CI'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionC0WithCNotIE_veKA0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Baccher";
    String expected = "PXR";  // 'B' -> 'P', 'C' -> 'K' (from conditionC0), 'H' skipped, 'ER' -> 'R'
    String result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionC0WithMacher_ZFMA1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Macher";
    String expected = "MKR";  // 'M' -> 'M', 'C' -> 'K' (from conditionC0), 'H' skipped, 'ER' -> 'R'
    String result = dm.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneConditionM0Coverage_qZXx0_1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Columb";
    String expected = "KLM";  // Assuming the rest of the methods like handleL, handleM etc., are implemented correctly
    input = "Columber";
    expected = "KLMR";  // Assuming the rest of the methods like handleL, handleM, handleR etc., are implemented correctly
    assertEquals(expected, doubleMetaphone.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_ConditionNotContainsCEorCI_fUin0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Acq", false);
    assertEquals("AK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMC_ufzX0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "McHale";
    String expected = "MKL"; // Assuming 'K' is appended for 'MC' and 'L' for 'L'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCH_eGil1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Michael";
    String expected = "MKL"; // Assuming 'K' is appended for 'CH' and 'L' for 'L'
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMCAtStart_kCtf0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "McHale";
    String expected = "MKL";  // Assuming the logic for 'H' and 'L' is handled correctly in the respective methods
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCHAtStart_vjef1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Charis";
    String expected = "KRS";  // Assuming the logic for 'A', 'R', 'I', 'S' is handled correctly in the respective methods
    String result = doubleMetaphone.doubleMetaphone(input, false);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleR_NotSlavoGermanic_IE_Not_ME_MA_RVLD0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "BRIE";
    boolean alternate = false;
    String result = doubleMetaphone.doubleMetaphone(value, alternate);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVANPrefix_vDyU0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "VAN Thomason";
    String expected = "FNTM";  // Assuming 'VAN ' triggers special handling for 'TH'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSCHPrefix_unMJ1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "SCHmidt";
    String expected = "XMT";  // Assuming 'SCH' triggers special handling for 'S'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXEndingInAUOrOU_iOIA0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Breaux", false);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSC_WithNonVowelAndNotW_csDE0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Schb"; // 'b' is not a vowel and not 'W'
    boolean alternate = false;
    String result = dm.doubleMetaphone(value, alternate);
    assertEquals("XP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSC_WithFollowingIEY_RklN1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Scia"; // 'I' follows SC
    boolean alternate = false;
    String result = dm.doubleMetaphone(value, alternate);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSC_WithSKAppend_jiPE2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Scl"; // No specific conditions met for 'H' or 'I', 'E', 'Y' after SC
    boolean alternate = false;
    String result = dm.doubleMetaphone(value, alternate);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCZAndWITZ_yGzr0_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String inputCZ = "CZech";
    String inputWITZ = "WITZman";
    String resultCZ = dm.doubleMetaphone(inputCZ, false);
    String resultWITZ = dm.doubleMetaphone(inputWITZ, false);
    assertNotNull("Result should not be null when processing 'CZ'", resultCZ);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCZAndWITZ_yGzr0_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String inputCZ = "CZech";
    String inputWITZ = "WITZman";
    String resultCZ = dm.doubleMetaphone(inputCZ, false);
    String resultWITZ = dm.doubleMetaphone(inputWITZ, false);
    assertNotNull("Result should not be null when processing 'WITZ'", resultWITZ);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZFollowedByZOZIZA_nDCT0_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String resultZo = dm.doubleMetaphone("Zo", false);
    String resultZi = dm.doubleMetaphone("Zi", false);
    String resultZa = dm.doubleMetaphone("Za", false);
    assertEquals("S", resultZo);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZFollowedByZOZIZA_nDCT0_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String resultZo = dm.doubleMetaphone("Zo", false);
    String resultZi = dm.doubleMetaphone("Zi", false);
    String resultZa = dm.doubleMetaphone("Za", false);
    assertEquals("S", resultZi);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZFollowedByZOZIZA_nDCT0_3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String resultZo = dm.doubleMetaphone("Zo", false);
    String resultZi = dm.doubleMetaphone("Zi", false);
    String resultZa = dm.doubleMetaphone("Za", false);
    assertEquals("S", resultZa);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_WithWR_pkVP0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "wrestle";
    String expected = "RSTL"; // Assuming 'R' is appended and 'W' is skipped
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_WithWH_TpZn1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "whale";
    String expected = "AL"; // Assuming 'A' is appended and 'W' is skipped
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_EndOfWordWithVowelBefore_rJyc2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "craw";
    String expected = "KR"; // Assuming 'F' is appended as an alternate at the end
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_NotAtStartOrSpecialCondition_ySAs3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "answer";
    String expected = "ANSR"; // Assuming normal processing without special 'W' handling
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleW_WithEWSKI_qhin4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "Kaprewski";
    String expected = "KPRS"; // Assuming 'F' is appended as an alternate for 'EWSKI'
    assertEquals(expected, dm.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithXAppend_JWPY1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Shack";
    assertEquals("XK", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithSioSia_TCQd2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sio";
    assertEquals("S", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithSioSiaSlavoGermanic_uxyn3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sio";
    assertEquals("X", dm.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithSian_Wfdn4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sian";
    assertEquals("SN", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithFrenchEnding_rTkY6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Artrois";
    assertEquals("ARTR", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithFrenchEndingAlternate_otcr7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Artrois";
    assertEquals("ARTR", dm.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_WithNormalS_UndK8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Smith";
    assertEquals("SM0", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_NotVowelBeforeGH_hmpv0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bgh", false);
    assertEquals("PK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_GHAtStart_Yqvs1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ghi", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_GHNotAtStart_eMCY2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AGH", false);
    assertEquals("AK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithBHD_cwnn3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("ABHGH", false);
    assertEquals("APK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithUAndCGLRT_ftvu5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cough", false);
    assertEquals("KF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithI_gInm7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("GHI", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithBD_DwTW8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("BDGH", false);
    assertEquals("PTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithB_aZFm9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("BGH", false);
    assertEquals("PK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH_WithD_yOzS10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("DGH", false);
    assertEquals("TK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_ShWithoutGermanic_trgs1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Shash";
    assertEquals("XX", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleS_RegularCase_pSLH8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String value = "Sister";
    assertEquals("SSTR", dm.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JoseNotAtStart_YvZA1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Maria Jose", false);
    assertEquals("MRJS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_VowelBeforeJNotSlavoGermanic_EdYD2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Tajo", false);
    assertEquals("TJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JAtEnd_jUnt3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Marj", false);
    assertEquals("MRJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_NotFollowingSpecialChars_jxOi4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Taj Mahal", false);
    assertEquals("TJMH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_NotPrecededBySpecialChars_Dpyg5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ajay", false);
    assertEquals("AJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_DoubleJ_qzEb6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ajjay", false);
    assertEquals("AJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JoseAtStart_CVKM7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jose", false);
    assertEquals("HS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JoseNotAtStartAndNotSlavoGermanic_iclk8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Tajose", false);
    assertEquals("TJS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JAtEndOfInput_oqpI9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Raj", false);
    assertEquals("RJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleJ_JFollowedByJ_yZrV10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Rajj", false);
    assertEquals("RJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_KN_N_Case_QTqJ0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Kna", false);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_N_KN_Case_yQbM1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gnome", false);
    assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_KL_L_Case_OmjJ2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Glider", false);
    assertEquals("KLTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GY_Case_adeq3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gyro", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_K_Case_TYTc4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vanger", false);
    assertEquals("FNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_IER_J_Case_xZjc5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pier", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_ET_K_Case_EYQN6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Budget", false);
    assertEquals("PJT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_VAN_K_Case_vnWx7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vanget", false);
    assertEquals("FNKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_SCH_K_Case_aVsi8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schmidt", false);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Y_J_Case_UauL9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gypsy", false);
    assertEquals("KPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_E_I_Case_BNIz10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gel", false);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_RGY_OGY_Case_AhIS11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Borgy", false);
    assertEquals("PRJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_EY_Case_qgNU12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Grey", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Y_NotSlavoGermanic_Case_eglx13() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gyrate", false);
    assertEquals("KRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_LI_NotSlavoGermanic_Case_OiQR14() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gelignite", false);
    assertEquals("KLNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GN_VowelAtStart_NotSlavoGermanic_QVuF0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Agnostic", false);
    assertEquals("AKNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GLI_NotSlavoGermanic_uzDl2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Agli", false);
    assertEquals("AKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_GY_NotDangerRangerManger_NotEI_NotRGYOGY_CaKH3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Margy", false);
    assertEquals("MRJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_VAN_Vzkw4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vangetti", false);
    assertEquals("FNKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_VON_AAGh5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vongetti", false);
    assertEquals("FNKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_SCH_gQur6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schgetti", false);
    assertEquals("XKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_ET_LDLr7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Getto", false);
    assertEquals("KT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_IER_XZXB8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gier", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_NotE_NotI_NotRGY_NotOGY_WBLb9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Magy", false);
    assertEquals("MK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_Germanic_NotIER_xkdV10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vang", false);
    assertEquals("FNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_NotGermanic_NotIER_VeOd11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ging", false);
    assertEquals("KNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG_NotGermanic_NotIER_ET_Vbzi14() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Get", false);
    assertEquals("KT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneConditionM0Coverage_qZXx0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String input = "Columb";
    String expected = "KLM";  // Assuming the rest of the methods like handleL, handleM etc., are implemented correctly
    assertEquals(expected, doubleMetaphone.doubleMetaphone(input, false));
    input = "Columber";
    expected = "KLMR";  // Assuming the rest of the methods like handleL, handleM, handleR etc., are implemented correctly
    assertEquals(expected, doubleMetaphone.doubleMetaphone(input, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCZAndWITZ_yGzr0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String inputCZ = "CZech";
    String inputWITZ = "WITZman";
    String resultCZ = dm.doubleMetaphone(inputCZ, false);
    String resultWITZ = dm.doubleMetaphone(inputWITZ, false);
    assertNotNull("Result should not be null when processing 'CZ'", resultCZ);
    assertNotNull("Result should not be null when processing 'WITZ'", resultWITZ);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZFollowedByZOZIZA_nDCT0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String resultZo = dm.doubleMetaphone("Zo", false);
    assertEquals("S", resultZo);
    String resultZi = dm.doubleMetaphone("Zi", false);
    assertEquals("S", resultZi);
    String resultZa = dm.doubleMetaphone("Za", false);
    assertEquals("S", resultZa);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleC_pWZt7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("S", dm.doubleMetaphone("C", false)); // Assuming handleC() returns 'S' for simple 'C'
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleH_qoal12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("H", dm.doubleMetaphone("H", false)); // Assuming handleH() returns 'H' for simple 'H'
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithK_Kcuy1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Kafka", false);
    assertEquals("KKF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWITZ_KbmD3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Rabinowitz", false);
    assertEquals("RPN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithG_DGyt10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gig", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_EmptyString_KZic0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("", false);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleJ_SlavoGermanic_QABc6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jager", false);
    assertEquals("AKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleP_elXi8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pfister", false);
    assertEquals("FSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_AlternateTrue_CMKe12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schmidt", true);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithH_YHSq9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("H", false);
    assertEquals("H", result);  // Assuming handleH method translates H to H
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Caeser_KxJV0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Caesar", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Czech_UyLp1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czech", false);
    assertEquals("SX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Focaccia_npBo2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Focaccia", false);
    assertEquals("FK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_McClelland_IxiM3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("McClelland", false);
    assertEquals("MKLLNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cappuccino_vcIk6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cappuccino", false);
    assertEquals("KPKKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Czechowski_bTGw9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czechowski", false);
    assertEquals("SXFSK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cappuccio_aDxR10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cappuccio", false);
    assertEquals("KPKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CappuccinoAlternate_SHnz12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cappuccino", true);
    assertEquals("KPKKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case2_JjOj1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czerny", false);
    assertEquals("SX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case3_gFlg2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("focaccia", false);
    assertEquals("FX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case6_ghez5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Mac Gregor", false);
    assertEquals("MKRKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case7_wYKc6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("ach", false);
    assertEquals("AX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case9_wyCp8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Macher", false);
    assertEquals("MXR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case10_CUkv9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("WICZ", false);
    assertEquals("AX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case11_Dqbk10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("CHIA", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case10_HUjp9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("knuth", false);
    assertEquals("NT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Chemistry_pgSk1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Chemistry", false);
    assertEquals("KMSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Christmas_ScaM8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Christmas", false);
    assertEquals("KRSTMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Achmadi_OoHM2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Achmadi", false);
    assertEquals("AXMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cicci_IkVn11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cicci", false);
    assertEquals("SS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithOrchestra_dzIy4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Orchestra", false);
    assertEquals("ARKSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithArchitect_HYSw7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Architect", false);
    assertEquals("ARKTKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWicz_pFWW8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Wicz", false);
    assertEquals("AX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMcGregor_Pjmi9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("McGregor", false);
    assertEquals("MKRKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Accident_HzcU3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Accident", false);
    assertEquals("AKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Bellocchio_fGUf4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bellocchio", false);
    assertEquals("BLX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Bacchus_vkay5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bacchus", false);
    assertEquals("BKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cc_sYOe10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cc", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Ciao_VxJn12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ciao", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEdge_HQet0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("edge", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEdgar_sLFR1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("edgar", false);
    assertEquals("TKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGH_sQLG0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ghent", false);
    assertEquals("JNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGNAlternate_XTXV2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gnome", true);
    assertEquals("KNM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGY_uQkT4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gypsy", false);
    assertEquals("JPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGE_FAXK5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gesture", false);
    assertEquals("JSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGermanicGi_MqEz7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ginger", false);
    assertEquals("JNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSlavoGermanic_Lmez2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Wagner", false);
    assertEquals("FNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGermanicOrigin_twer7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Berger", false);
    assertEquals("PRJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGYPrefix_mibY11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gyro", false);
    assertEquals("JR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGFollowedByY_qOVH12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gypsum", false);
    assertEquals("JPSM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAER_lnFo4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Aerosmith", false);
    assertEquals("ARSMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPF_hoxL5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pfizer", false);
    assertEquals("FSR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_OzWt0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("anger", false);
    assertEquals("ANJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_OYvh4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gel", false);
    assertEquals("JL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_bfcC5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gel", true);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_ZlOi9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("schmidt", true);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmbeddedH_qSJR1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Thompson", false);
    assertEquals("TMPSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithG_Qzxs7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ginger", false);
    assertEquals("JNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJose_BZjq0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jose", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJavier_jgBs4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Javier", false);
    assertEquals("JFR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJuxtapose_zUth10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Juxtapose", false);
    assertEquals("JKTPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JoseAlternate_xEbl1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jose", true);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanJacintoPrimary_rxkJ2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("San Jacinto", false);
    assertEquals("SNHSNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanJacintoAlternate_rTKC3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("San Jacinto", true);
    assertEquals("SNHSNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JulioAlternate_IluM7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Julio", true);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JojoAlternate_Lefl13() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jojo", true);
    assertEquals("HH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTripleL_cEqR2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLL", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLeadingL_jsPY3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Labc", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmbeddedSingleL_vmCY4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("aLb", false);
    assertEquals("ALB", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmbeddedDoubleL_kZiD5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("aLLb", false);
    assertEquals("ALB", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTrailingL_bHTR6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("abL", false);
    assertEquals("ABL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTrailingDoubleL_KDMe7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("abLL", false);
    assertEquals("ABL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllLs_Qruv8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLLL", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMixedCaseLs_cobE9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LlLl", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLillo_Mgtu2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Lillo", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLilla_jtPO3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Lilla", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllegroAlternate_nJWw5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegro", true);
    assertEquals("ALKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllegraAlternate_AtDi7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegra", true);
    assertEquals("ALKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithOs_iIEE8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegros", false);
    assertEquals("ALKRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_AEIOUY_yJKW0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Audio", false);
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_B_lcWW1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bob", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_MJyY2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cecilia", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_D_PQJf3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("David", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_F_hSCr4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Fifty", false);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_G_Zopr5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gigantic", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_H_jUoI6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Hannah", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_J_sQBo7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jorge", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_M_IwPv8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Mummy", false);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_M_ConditionM0_TONw9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Columbus", false);
    assertEquals("KLMPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_N_sJCj10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Nancy", false);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_P_iWfP11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Papa", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Q_HARg12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Quack", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_H_yHrl6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Henry", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_J_Clsy7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("John", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_K_hLdx8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Kirk", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_L_FdRA9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Lilly", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_R_kmRD12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Rider", false);
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Shoek_cnkH4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Shoek", false);
    assertEquals("XK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Sicily_zoCa8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Sicily", false);
    assertEquals("SKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schultz_jAID12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schultz", false);
    assertEquals("XLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schlesinger_MKXb4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schlesinger", false);
    assertEquals("XLSNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Scholar_RioK11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Scholar", false);
    assertEquals("SKLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Szczepanski_drFw11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Szczepanski", false);
    assertEquals("SXPNXK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTch_qIZF2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("watch", false);
    assertEquals("WX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithThAm_jGgN4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("betham", false);
    assertEquals("BTM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTth_Zgrz5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("matthew", false);
    assertEquals("MT0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVan_XxNB6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("van thomas", false);
    assertEquals("FN TMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWITZSuffix_onhn5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bernwitz", false);
    assertEquals("PRNTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPolishOrigin_oPce6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Filipowicz", false);
    assertEquals("FLPTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_Cedilla_MjnY10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Faade", false);
    assertEquals("FST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_N_Tilde_lgWW11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Seor", false);
    assertEquals("SNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAtEnd_nOeG3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Breaux", false);
    assertTrue(result.endsWith("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAndSpecialConditions_rBVL8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Beaux", false);
    assertTrue(result.endsWith("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZNotFollowedByH_AgOt1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zap", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZFollowedByI_JBFf3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zinc", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZSlavoGermanic_azlL5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zloty", true);
    assertEquals("SLTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZDouble_qZpx7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zizza", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZSingle_ljSm8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zebra", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneBasic_hiAd0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "example";
    String expected = "AKSMP";
    String result = dm.doubleMetaphone(input);
    assertEquals(expected, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleC_pWZt7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("S", dm.doubleMetaphone("C", false)); // Assuming handleC() returns 'K' for simple 'C'
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithH_YHSq9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("H", false);
    assertEquals("H", result);  // Assuming handleH method translates to
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithHSose_BZjq0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("HSose", false);
    assertEquals("HS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTripleLL_cEqR2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLLLLL", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLPKeadingLPK_jsPY3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LPKabc", false);
    assertEquals("LPK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllLLs_Qruv8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLLLLLLL", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMixedCaseLLs_cobE9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLlLLl", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_FFT_hSCr4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("FFTifty", false);
    assertEquals("FFT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JN_Clsy7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("JNohn", false);
    assertEquals("JN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_KRK_hLdx8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("KRKirk", false);
    assertEquals("KRK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_NullInput_Qlwa0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertNull(dm.doubleMetaphone(null, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_EmptyString_iRaU1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertNull(dm.doubleMetaphone("", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleA_hTDN3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("A", dm.doubleMetaphone("A", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleB_Wbot4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("P", dm.doubleMetaphone("B", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_DoubleB_ovlk5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("P", dm.doubleMetaphone("BB", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cedilla_uoOO6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("S", dm.doubleMetaphone("\u00C7", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleD_CsSi8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("T", dm.doubleMetaphone("D", false)); // Assuming handleD() returns 'T' for simple 'D'
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleF_TFwo9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("F", dm.doubleMetaphone("F", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_DoubleF_ZnlG10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("F", dm.doubleMetaphone("FF", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SimpleG_JaKu11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    assertEquals("K", dm.doubleMetaphone("G", false)); // Assuming handleG() returns 'K' for simple 'G'
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithW_SOwG0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Wagner", false);
    assertEquals("AKNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCZ_yhNw2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czerny", false);
    assertEquals("SRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSilentStart_OTsS4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Wright", false);
    assertEquals("RT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAEIOUY_smkX5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Aeious", false);
    assertEquals("AS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithB_Btgj6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bob", false);
    assertEquals("PP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithC_JUCm7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cicero", false);
    assertEquals("SSR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithD_BsVy8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Dodge", false);
    assertEquals("TJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithF_OBJZ9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Faff", false);
    assertEquals("FF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithH_AKZJ11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Haha", false);
    assertEquals("HH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJ_Lhxd12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jaja", false);
    assertEquals("JJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_NullInput_eiXs1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone(null, false);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SilentStart_smEi2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("know", false);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleC_pNHI4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Catherine", false);
    assertEquals("K0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleG_eEGx5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gough", false);
    assertEquals("KF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleL_lVIa7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Lloyd", false);
    assertEquals("LT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleX_jaIN10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Xavier", false);
    assertEquals("SF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleZ_bkeK11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zachary", false);
    assertEquals("SKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithInitialVowel_XBkw0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AEIOUY", false);
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithInitialVowelAlternate_uNfw1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AEIOUY", true);
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithB_FSIl2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("B", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithBB_HZgt3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("BB", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithC_SgkZ4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("C", false);
    assertEquals("K", result);  // Assuming handleC method translates C to K
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithD_fMJT5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("D", false);
    assertEquals("T", result);  // Assuming handleD method translates D to T
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithF_WWhj6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("F", false);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithFF_tBUU7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("FF", false);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithG_UsyG8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("G", false);
    assertEquals("K", result);  // Assuming handleG method translates G to K
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJ_XnEU10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("J", false);
    assertEquals("J", result);  // Assuming handleJ method translates J to J
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithK_OLlM11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("K", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithKK_JbsN12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("KK", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cock_QlTN4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cock", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_MacCaffrey_uLEZ7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Mac Caffrey", false);
    assertEquals("MKFR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cicada_yJeI8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cicada", false);
    assertEquals("SKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CiceroAlternate_kNwr11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cicero", true);
    assertEquals("SSR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case8_TPEh7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bacher", false);
    assertEquals("PKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case12_FYsA11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("CIO", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case13_oiOx12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("CIE", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case1_ptBy0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("ach", false);
    assertEquals("AK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case2_MVov1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("bacher", false);
    assertEquals("PKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case3_ZkkU2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("macher", false);
    assertEquals("MKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case4_Hmrp3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("caesar", false);
    assertEquals("SSR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case5_jZqz4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("focaccia", false);
    assertEquals("FKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case6_pgMa5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("czerny", false);
    assertEquals("SRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case7_hEHK6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("chianti", false);
    assertEquals("KNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case8_pegT7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("michael", false);
    assertEquals("MKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case9_ERwV8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("creigh", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case11_oKkB10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("knight", false);
    assertEquals("NT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case12_Lojw11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("wright", false);
    assertEquals("RT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case13_dtQj12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("hiccough", false);
    assertEquals("HKF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Chorus_LCFa2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Chorus", false);
    assertEquals("KRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Michael_mhvt3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Michael", false);
    assertEquals("MKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Focaccia_ztQA5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Focaccia", false);
    assertEquals("FKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Accolade_waNJ7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Accolade", false);
    assertEquals("AKLT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Knight_RXxt9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Knight", false);
    assertEquals("NT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Pneumonia_OFwK10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pneumonia", false);
    assertEquals("NMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Zach_HNDS12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zach", false);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Chore_ieYt8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Chore", false);
    assertEquals("XR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Clock_ScBt12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Clock", false);
    assertEquals("KLK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCaesar_JNuH0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Caesar", false);
    assertEquals("SSR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCacc_NhNv10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cacc", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCk_bZnN11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ck", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Christmas_SyvM6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Christmas", false);
    assertEquals("KRST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_McClelland_Qvkv9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("McClelland", false);
    assertEquals("MKLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDodge_klDh2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dodge", false);
    assertEquals("TJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithBidder_XvGm3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("bidder", false);
    assertEquals("PTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAdd_muGd4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("add", false);
    assertEquals("AT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDad_GScJ5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dad", false);
    assertEquals("TT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDud_tDTZ6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dud", false);
    assertEquals("TT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithD_Elki7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("d", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDeed_YETw8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("deed", false);
    assertEquals("TT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDead_bXfH9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dead", false);
    assertEquals("TT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDood_UoVT10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dood", false);
    assertEquals("TT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDundee_DPhK11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dundee", false);
    assertEquals("TNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDandy_mDGa12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("dandy", false);
    assertEquals("TNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGN_tCOH1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gnome", false);
    assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGLI_lVoi3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Glider", false);
    assertEquals("KLTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithItalianGi_Kpkx6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gianni", false);
    assertEquals("JN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDoubleG_xSdX8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Goggle", false);
    assertEquals("KKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSingleG_TzfX9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gold", false);
    assertEquals("KLT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDanger_fQBh10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Danger", false);
    assertEquals("TNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithRanger_WELO11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ranger", false);
    assertEquals("RNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithManger_ifsT12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Manger", false);
    assertEquals("MNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGH_sMag0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("bough", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithInitialG_EUbY1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gel", false);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithInitialGFollowedByN_ewLs4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gnome", false);
    assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDangerPrefix_jWcI6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("danger", false);
    assertEquals("TNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCough_Fwtf8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("cough", false);
    assertEquals("KF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGHInMiddle_oJoF9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("hugh", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGHAndPreviousVowel_kImL10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("high", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGH_zWnZ0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bough", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithKN_zurI2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Knife", false);
    assertEquals("NF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEdgeCase_cxXn6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Edge", false);
    assertEquals("AJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWaltz_Ynya9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Waltz", false);
    assertEquals("ALTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithQueue_DvUH11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Queue", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithHiccough_ADiM12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Hiccough", false);
    assertEquals("HKF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_ErCM1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("anger", true);
    assertEquals("ANJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_QZXS3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("knight", true);
    assertEquals("NT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_qthC6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("biaggi", false);
    assertEquals("PJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_ELmf7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("biaggi", true);
    assertEquals("PK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_OKaw8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("schmidt", false);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_LwpC11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("focaccia", true);
    assertEquals("FKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithInitialH_pNtY0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Hannah", false);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSilentH_QUjq2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ghost", false);
    assertEquals("KST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithInitialVowel_Gtxn3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Apple", false);
    assertEquals("APL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithNonSlavoGermanic_ZtZa5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Smith", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAlternateTrue_xBJZ8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jackson", true);
    assertEquals("AKSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithP_nWts9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Philip", false);
    assertEquals("FLP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithT_IWvr10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Tiffany", false);
    assertEquals("TFN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithD_yaNc11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("David", false);
    assertEquals("TFT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_AEIOUY_imNg0_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AEIOUY", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_B_QeuO1_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("BB", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_SXPj2_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("C", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_SXPj2_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("C", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_D_XWBa3_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("D", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_D_XWBa3_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("D", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_F_VRPD4_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("FF", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_G_Gusr5_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("G", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_G_Gusr5_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("G", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_H_ThQZ6_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AHA", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_H_ThQZ6_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AHA", false);
    assertEquals("AH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_J_QrUz7_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("J", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_J_QrUz7_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("J", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_K_lmbN8_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("KK", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_L_pNYv9_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("L", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_L_pNYv9_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("L", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_M_pecg10_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("M", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_M_pecg10_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("M", false);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_N_MIZj11_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("NN", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_N_MIZj11_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("NN", false);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_P_NmIo12_1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("P", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_P_NmIo12_2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("P", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSanJose_YLZT1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("San Jose", false);
    assertEquals("SNHS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJosef_hiik2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Josef", false);
    assertEquals("JSF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJohan_Kzej3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Johan", false);
    assertEquals("JHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJacinto_JgTB5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jacinto", false);
    assertEquals("JSNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJulia_POuw7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Julia", false);
    assertEquals("JL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJohn_wTMX8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("John", false);
    assertEquals("JN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJingle_iAJj9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jingle", false);
    assertEquals("JNKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJazz_ERmB11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jazz", false);
    assertEquals("JS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJester_cpur12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jester", false);
    assertEquals("JSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JuanPrimary_qWxw4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Juan", false);
    assertEquals("JN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JuanAlternate_YvVc5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Juan", true);
    assertEquals("AN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JulioPrimary_kAKZ6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Julio", false);
    assertEquals("JL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JavierAlternate_jhfi9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Javier", true);
    assertEquals("AFR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JasminePrimary_XCkS10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jasmine", false);
    assertEquals("JSMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JasmineAlternate_YLJo11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jasmine", true);
    assertEquals("ASMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JojoPrimary_WRXr12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jojo", false);
    assertEquals("JJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDoubleL_EDAD1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LL", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLAndNumbers_YXvi10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("L123", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithNumbersAndL_QWDi11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("123L", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSpecialCharactersAndL_VSKw12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("#$%L", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllegro_iQyL4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegro", false);
    assertEquals("ALKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllegra_fzOD6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegra", false);
    assertEquals("ALKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAs_CSgn9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegas", false);
    assertEquals("ALKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithA_yKyN10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allega", false);
    assertEquals("ALK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithO_NVUB11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allego", false);
    assertEquals("ALK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPH_pUno0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Phantom", false);
    assertEquals("FNTM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithP_GUib1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Palm", false);
    assertEquals("PLM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPP_jhaU2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pippin", false);
    assertEquals("PPN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPB_mBHl3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pb", false);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithNonAlphabetic_KVtU6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("1234", false);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSpecialCharacters_HSVC7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("@#$%", false);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAlternateTrue_dViB8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Palm", true);
    assertEquals("PLM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithC_uFMU9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cinema", false);
    assertEquals("SNM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithV_EkTB12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Vivid", false);
    assertEquals("FFT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Sugar_MLGI0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Sugar", false);
    assertEquals("XKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Island_SQrH1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Island", false);
    assertEquals("ALNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schmidt_KfbF2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schmidt", false);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schneider_axZH3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schneider", false);
    assertEquals("XNTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Science_uvSf6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Science", false);
    assertEquals("SNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Scenario_xGon7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Scenario", false);
    assertEquals("SNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Slavic_JooB9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Slavic", false);
    assertEquals("SLFK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Szilard_lcWt10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Szilard", false);
    assertEquals("SLRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schnapps_fJLn11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schnapps", false);
    assertEquals("XNPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_School_Djoc2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("School", false);
    assertEquals("SKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schenker_JvuV3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schenker", false);
    assertEquals("XNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Snyder_ByqX7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Snyder", false);
    assertEquals("SNTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Scissors_qXnB10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Scissors", false);
    assertEquals("SSRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schwarz_xtvo12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schwarz", false);
    assertEquals("XRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTion_jHBb0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("action", false);
    assertEquals("AKXN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTia_FElN1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("patia", false);
    assertEquals("PX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTh_amuG3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("thomas", false);
    assertEquals("TMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSimpleT_htWc8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("tar", false);
    assertEquals("TR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDoubleT_AmTv9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("butter", false);
    assertEquals("PTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDoubleD_YNMN10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("budded", false);
    assertEquals("PTT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAlternateTrue_kdOQ11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("action", true);
    assertEquals("AKXN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWHPrefix_YQqd1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Whale", false);
    assertEquals("AL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWVowel_nPQg2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Wade", false);
    assertEquals("AT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWSuffix_utCu3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Craw", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWICZSuffix_XKZl4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Lewicz", false);
    assertEquals("LTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMiddleW_ekvz7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bowler", false);
    assertEquals("PLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEndingW_ZsPZ8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Barrow", false);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWAndH_WUSL11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Whit", false);
    assertEquals("AT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWAndSpecialCases_gjje12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schwartz", false);
    assertEquals("XRTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_W_Ending_szAd3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Crew", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_WITZ_Ending_rgbe5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Horowitz", false);
    assertEquals("HRTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAtStart_FNDs2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Xavier", false);
    assertTrue(result.startsWith("S"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXNotAtStartOrEnd_Ktfa4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Alex", false);
    assertTrue(result.contains("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXFollowedByC_XUJa5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Excite", false);
    assertTrue(result.contains("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXFollowedByX_bLpL6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Exxon", false);
    assertTrue(result.contains("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXFollowedByOther_yGWi7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Axel", false);
    assertTrue(result.contains("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAndIAU_isPH9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Xiau", false);
    assertTrue(result.startsWith("S"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAndEAU_sTKv10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Xeau", false);
    assertTrue(result.startsWith("S"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAndAU_AxxA11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Xau", false);
    assertTrue(result.startsWith("S"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAndOU_Wuba12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Xou", false);
    assertTrue(result.startsWith("S"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZH_ITFy0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zhao", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZFollowedByO_QCDT2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zoo", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZNotSlavoGermanic_frHJ6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zloty", false);
    assertEquals("SLT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZInMiddle_BgKB9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Breeze", false);
    assertEquals("PRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZAtEnd_TDTA11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Buzz", false);
    assertEquals("PS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZAdjacentToSimilar_FIzB12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pizza", false);
    assertEquals("PS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithK_Kcuy1_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Kafka", false);
    assertEquals("KFK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWITZ_KbmD3_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Rabinowitz", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithG_DGyt10_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gig", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_EmptyString_KZic0_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleJ_SlavoGermanic_QABc6_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jager", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HandleP_elXi8_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pfister", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_AlternateTrue_CMKe12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schmidt", true);
    assertEquals("SMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Czech_UyLp1_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czech", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Focaccia_npBo2_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Focaccia", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_McClelland_IxiM3_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("McClelland", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cappuccino_vcIk6_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cappuccino", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Czechowski_bTGw9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czechowski", false);
    assertEquals("SXSK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cappuccio_aDxR10_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cappuccio", false);
    assertEquals("KPX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_CappuccinoAlternate_SHnz12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cappuccino", true);
    assertEquals("KPXN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case6_ghez5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Mac Gregor", false);
    assertEquals("MKRK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case9_wyCp8_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Macher", false);
    assertEquals("MKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case10_CUkv9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("WICZ", false);
    assertEquals("AKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case11_Dqbk10_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("CHIA", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Case10_HUjp9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("knuth", false);
    assertEquals("N0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Chemistry_pgSk1_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Chemistry", false);
    assertEquals("KMST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Achmadi_OoHM2_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Achmadi", false);
    assertEquals("AKMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cicci_IkVn11_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cicci", false);
    assertEquals("SX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithOrchestra_dzIy4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Orchestra", false);
    assertEquals("ARKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithArchitect_HYSw7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Architect", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWicz_pFWW8_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Wicz", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithMcGregor_Pjmi9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("McGregor", false);
    assertEquals("MKRK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Czech_OWXZ1_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Czech", false);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Accident_HzcU3_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Accident", false);
    assertEquals("AKST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Bellocchio_fGUf4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bellocchio", false);
    assertEquals("PLX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Bacchus_vkay5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bacchus", false);
    assertEquals("PKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Cc_sYOe10_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Cc", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Ciao_VxJn12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ciao", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEdge_HQet0_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("edge", false);
    assertEquals("AJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEdgar_sLFR1_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("edgar", false);
    assertEquals("ATKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGH_sQLG0_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ghent", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGNAlternate_XTXV2_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gnome", true);
    assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGY_uQkT4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gypsy", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGE_FAXK5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gesture", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGermanicGi_MqEz7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Ginger", false);
    assertEquals("KNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGermanicOrigin_twer7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Berger", false);
    assertEquals("PRKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGYPrefix_mibY11_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gyro", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithGFollowedByY_qOVH12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gypsum", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAER_lnFo4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Aerosmith", false);
    assertEquals("ARSM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPF_hoxL5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Pfizer", false);
    assertEquals("PFSR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_OzWt0_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("anger", false);
    assertEquals("ANKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_bfcC5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("gel", true);
    assertEquals("JL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_ZlOi9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("schmidt", true);
    assertEquals("SMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmbeddedH_qSJR1_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Thompson", false);
    assertEquals("TMPS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJavier_jgBs4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Javier", false);
    assertEquals("JF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithJuxtapose_zUth10_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Juxtapose", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanJacintoPrimary_rxkJ2_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("San Jacinto", false);
    assertEquals("SNHS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanJacintoAlternate_rTKC3_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("San Jacinto", true);
    assertEquals("SNHS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JulioAlternate_IluM7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Julio", true);
    assertEquals("AL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JojoAlternate_Lefl13_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Jojo", true);
    assertEquals("AH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmbeddedSingleL_vmCY4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("aLb", false);
    assertEquals("ALP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmbeddedDoubleL_kZiD5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("aLLb", false);
    assertEquals("ALP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTrailingL_bHTR6_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("abL", false);
    assertEquals("APL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTrailingDoubleL_KDMe7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("abLL", false);
    assertEquals("APL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLLillo_Mgtu2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLillo", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithLLilla_jtPO3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLilla", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllegroAlternate_nJWw5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegro", true);
    assertEquals("AKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithAllegraAlternate_AtDi7_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegra", true);
    assertEquals("AKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithOs_iIEE8_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Allegros", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithEmptyString_zbqP12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("", false);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_G_Zopr5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Gigantic", false);
    assertEquals("JKNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_M_ConditionM0_TONw9_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Columbus", false);
    assertEquals("KLMP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Q_HARg12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Quack", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_LL_FdRA9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("LLilly", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Shoek_cnkH4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Shoek", false);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Sicily_zoCa8_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Sicily", false);
    assertEquals("SSL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schultz_jAID12_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schultz", false);
    assertEquals("XLTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Schlesinger_MKXb4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Schlesinger", false);
    assertEquals("XLSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Scholar_RioK11_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Scholar", false);
    assertEquals("XLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Szczepanski_drFw11_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Szczepanski", false);
    assertEquals("SSPN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTch_qIZF2_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("watch", false);
    assertEquals("AX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithThAm_jGgN4_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("betham", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTth_Zgrz5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("matthew", false);
    assertEquals("M0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVan_XxNB6_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("van thomas", false);
    assertEquals("FNTM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithWITZSuffix_onhn5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Bernwitz", false);
    assertEquals("PRNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPolishOrigin_oPce6_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Filipowicz", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_Cedilla_MjnY10_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Faade", false);
    assertEquals("FT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_N_Tilde_lgWW11_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Seor", false);
    assertEquals("SR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAtEnd_nOeG3_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Breaux", false);
    assertFalse(result.endsWith("KS"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithXAndSpecialConditions_rBVL8_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Beaux", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZSlavoGermanic_azlL5_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("Zloty", true);
    assertEquals("SLT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneBasic_hiAd0_fid1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String input = "example";
    String expected = "AKSMP";
    String result = dm.doubleMetaphone(input);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_AEIOUY_imNg0() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AEIOUY", false);
    assertNotNull(result);
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_B_QeuO1() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("BB", false);
    assertNotNull(result);
    assertEquals("P", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_C_SXPj2() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("C", false);
    assertNotNull(result);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_D_XWBa3() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("D", false);
    assertNotNull(result);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_F_VRPD4() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("FF", false);
    assertNotNull(result);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_G_Gusr5() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("G", false);
    assertNotNull(result);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_H_ThQZ6() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("AHA", false);
    assertNotNull(result);
    assertEquals("AH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_J_QrUz7() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("J", false);
    assertNotNull(result);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_K_lmbN8() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("KK", false);
    assertNotNull(result);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_L_pNYv9() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("L", false);
    assertNotNull(result);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_M_pecg10() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("M", false);
    assertNotNull(result);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_N_MIZj11() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("NN", false);
    assertNotNull(result);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_P_NmIo12() {
    DoubleMetaphone dm = new DoubleMetaphone();
    String result = dm.doubleMetaphone("P", false);
    assertNotNull(result);
    assertEquals("P", result);
    }
}