/**
 * Filtered unit tests for method: doubleMetaphone(String value, final boolean alternate)
 * Original class: DoubleMetaphone
 * Tests that actually call the target method
 */
package org.apache.commons.codec.language;

import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo17_unit_doubleMetaphone_DoubleMetaphone_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_DoubleMetaphone_qQGa4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "CIA";
    String result = doubleMetaphone.doubleMetaphone(value, false);
    assert(result.equals("IA"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_DoubleMetaphone_Negative_Xpoe5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "ABC";
    String result = doubleMetaphone.doubleMetaphone(value, false);
    assert(result.equals("BC"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPB_jbgE3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("PPLble", false);
    assertEquals("PPL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JKWithFollowingNonVowelConsonantAndVowel_fUGf13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("JK", doubleMetaphone.doubleMetaphone("JKaq", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JKKWithFollowingNonVowelConsonantAndNonVowelConsonant_agal14() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("JKK", doubleMetaphone.doubleMetaphone("JKKkq", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testX_kcVo0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("X", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHM_QfOi1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HM", doubleMetaphone.doubleMetaphone("HM", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHK_ykzs2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HK", doubleMetaphone.doubleMetaphone("HK", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHLM_Vmgm3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HLM", doubleMetaphone.doubleMetaphone("HLM", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHLS_xCqj4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HLS", doubleMetaphone.doubleMetaphone("HLS", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionM0True_ryNh0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "MUM";
    String result = doubleMetaphone.doubleMetaphone(value, false);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionM0False_TisR1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "UMB";
    String result = doubleMetaphone.doubleMetaphone(value, false);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1_SWXG0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Vanchur";
    String expected = "VNXR";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1Sch_oRjf1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Schatz";
    String expected = "SXT";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1Orchid_HdXS2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Orchid";
    String expected = "RKDT";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1Space_qejB3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Hans Space";
    String expected = "HN SPS";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1End_NQbY4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Hans";
    String expected = "HN";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMc_dEgh0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McDonald", false);
    assertEquals("MCKNL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCaesar_dQgN0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KS", doubleMetaphone.doubleMetaphone("Caesar", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMcClelland_Kkpe1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("MKLND", doubleMetaphone.doubleMetaphone("McClelland", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCC_mAez2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KK", doubleMetaphone.doubleMetaphone("Backpack", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCCNotMc_YwwJ3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KK", doubleMetaphone.doubleMetaphone("Kuckuck", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCIA_hIfC4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Focaccia", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCZ_MUav5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("Czerny", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCKCGCQ_Iwgv6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("Check", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCIO_KBri8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("Cio", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCAndSpace_aacb9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("Mac Caffrey", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCia_lJfi3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("Cia", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneFocus_OGKU5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("FX", doubleMetaphone.doubleMetaphone("Focus", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMcGregor_vYjP6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("MGR", doubleMetaphone.doubleMetaphone("McGregor", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneDoubleCC_nAfX7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KK", doubleMetaphone.doubleMetaphone("Kockock", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_OgWx0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("BACHER", false);
    assertEquals("PACHER", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_FZLa1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("focaccia", false);
    assertEquals("FOX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_YpTa2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CAESAR", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_hIAQ3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_BFsi4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CIA", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_nrXN8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CH", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCC_qrVn1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Macclelland", false);
    assertEquals("MXL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCIA_YZrX3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("focaccia", false);
    assertEquals("FX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCQAndCE_EFdp9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CQCE", false);
    assertEquals("KX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCAndSpace_ABjm10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("C MacGregor", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneL1_YxvA1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Lalla", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_McClelland_ttQj1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_doubleCC_PrlE3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bellocchio", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_accident_QCBl4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("accident", false);
    assertEquals("KS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_bacci_qpOr5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bacci", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_MacCaffrey_dpnA6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("MacCaffrey", false);
    assertEquals("MK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_MacGregor_sJIk7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("MacGregor", false);
    assertEquals("MK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_noCC_HgwP8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hello", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCaesar_aNux0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KSRS", doubleMetaphone.doubleMetaphone("CAESAR", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDoubleCC_FNGP2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KLLND", doubleMetaphone.doubleMetaphone("McKelland", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCIA_JsHh3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("focaccia", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCH_iuEJ5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("church", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCK_OKJk6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("check", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCQG_OgCV12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("CQG", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCQGAlternate_HDYb13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KX", doubleMetaphone.doubleMetaphone("CQG", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_pQcr0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bellocchio", false);
    assertEquals("BKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_lgjc1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bacchus", false);
    assertEquals("BKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_NatZ2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("accident", false);
    assertEquals("AKSNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_AYnP3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("accede", false);
    assertEquals("AKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_TYfZ4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("succeed", false);
    assertEquals("SKD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_dwjA5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("SXRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_kHTC6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("focaccia", false);
    assertEquals("FKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithH_Mllw0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Phoebe", false);
    assertEquals("FEB", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPH_oSSW1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Philip", false);
    assertEquals("FILIP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithBP_vKUs2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bobbie", false);
    assertEquals("BEB", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPB_jbgE3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Pebble", false);
    assertEquals("Peb", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneIE_uggY0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("FIE", true);
    assertEquals("FI", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWR_iiEb0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Write", true);
    assertEquals("RiT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWRInMiddle_Jdmc1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Writers", true);
    assertEquals("RiTz", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneSCH_EZix2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schenck", true);
    assertEquals("SxNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWRAndSCH_RmVU3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("SchenckWrite", true);
    assertEquals("SxNKRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTH_qWbI0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("thomas", false);
    assertEquals("T0MAS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVAN_fkia1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("van", false);
    assertEquals("AN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVON_pIuD2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("von", false);
    assertEquals("ON", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTAndD_hoDj4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Tend", false);
    assertEquals("T0ND", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZo_ukSA0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zoology", false);
    assertEquals("Z0L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZi_JrSV1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zi", false);
    assertEquals("Z", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZa_iprS2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Za", false);
    assertEquals("Z", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithoutSlavoGermanic_tXcB4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zo", true);
    assertEquals("Z", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG1_vcwS0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hannah", false);
    assertEquals("AN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG2_hrAt1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Nancy", false);
    assertEquals("NK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG3_aQEt2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Lily", false);
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG4_BdDd3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knee", false);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG5_xZUF4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Yancy", false);
    assertEquals("JN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG6_sAYU5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gery", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG7_GMtr6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Danger", false);
    assertEquals("DNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG8_dHFX7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ranger", false);
    assertEquals("RNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG9_WaUz8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Manger", false);
    assertEquals("MNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG10_yoUx9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Aggie", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG11_MKaq10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Vanity", false);
    assertEquals("VNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG12_fsSX11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schie", false);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG13_btpU12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Eiger", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG14_BtHb13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Geyer", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG15_QmmL14() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hegy", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG16_UCpG15_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knee", true);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG17_OaZR16_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Yancy", true);
    assertEquals("JN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG1_GPvz0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Hanger";
    boolean slavoGermanic = false;
    assertEquals("HNJR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG2_rALJ1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Nanger";
    boolean slavoGermanic = false;
    assertEquals("NNGR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG3_ZkPJ2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Linger";
    boolean slavoGermanic = false;
    assertEquals("LNJR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG4_XppZ3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Ginger";
    boolean slavoGermanic = false;
    assertEquals("JNJR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG5_JMPi4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = true;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG6_KtSN5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = false;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG7_XMjM6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = false;
    assertEquals("KNED", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG8_FnGq7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knee";
    boolean slavoGermanic = false;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG9_XADQ8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = true;
    assertEquals("KNED", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG10_aPLJ9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knee";
    boolean slavoGermanic = true;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG11_zhOW10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = false;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG12_ifze11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = true;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG13_VZTz12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = false;
    assertEquals("KNED", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG14_xlfn13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knee";
    boolean slavoGermanic = false;
    assertEquals("KN", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG15_PDUa14_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = true;
    assertEquals("KNED", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Jose_ycHK0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HOSE", doubleMetaphone.doubleMetaphone("Jose", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanJacinto_waJr1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SNJKNT", doubleMetaphone.doubleMetaphone("San Jacinto", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithVowelBeforeAndAAfter_fQLk5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HA", doubleMetaphone.doubleMetaphone("Ja", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithVowelBeforeAndOAfter_WuPs6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HO", doubleMetaphone.doubleMetaphone("Jo", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithFollowingJ_tNBw9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HH", doubleMetaphone.doubleMetaphone("JJ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithFollowingNonVowelConsonant_gwLS10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("Jk", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithFollowingNonVowelConsonantAndVowel_fUGf13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("Jaq", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithFollowingNonVowelConsonantAndNonVowelConsonant_agal14() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("Jkq", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_S_index_1_TDPU2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("aS", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_KS_QAaP3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KS", doubleMetaphone.doubleMetaphone("aux", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_KS_index_2_srNO4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KS", doubleMetaphone.doubleMetaphone("eau", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_KS_index_3_AYjh5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KS", doubleMetaphone.doubleMetaphone("ou", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_KS_index_4_oDOa6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KS", doubleMetaphone.doubleMetaphone("auxx", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_KS_index_5_tWgg7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KS", doubleMetaphone.doubleMetaphone("eauu", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH1_HCNb0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("HUGH", false);
    assertEquals("HWG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG2_ZuGi7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("gy", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG3_ebrk8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("german", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG4_nqTw9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("germany", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG5_fsZh10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("germanic", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG6_FVFO11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("germanic", true);
    assertEquals("KRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithISL_JYkE0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("island", false);
    assertEquals("LN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithYSL_INto1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("ysle", false);
    assertEquals("SL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSUGAR_LwxS2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sugar", false);
    assertEquals("XR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSH_DCCN3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sheim", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSIAN_NNPi4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sian", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSC_LPvK5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schermerhorn", false);
    assertEquals("XKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndOOER_UusX6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("school", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndEN_NfQu8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schenker", false);
    assertEquals("XK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndNotVowel_jaRQ9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hsiao", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndY_jJCr10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hoy", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSH_kcVo0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SH", doubleMetaphone.doubleMetaphone("SH", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHEIM_QfOi1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HEIM", doubleMetaphone.doubleMetaphone("HEIM", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHOEK_ykzs2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HOEK", doubleMetaphone.doubleMetaphone("HOEK", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHOLM_Vmgm3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HOLM", doubleMetaphone.doubleMetaphone("HOLM", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHOLZ_xCqj4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HOLZ", doubleMetaphone.doubleMetaphone("HOLZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIO_BzHZ5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SIO", doubleMetaphone.doubleMetaphone("SIO", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIA_BJQv6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SIA", doubleMetaphone.doubleMetaphone("SIA", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIAN_ANTT7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SIAN", doubleMetaphone.doubleMetaphone("SIAN", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSHwithZ_RFaH8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("SHZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIOwithZ_vrTJ9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("SIZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIANwithZ_DhqP11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("SIANZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSH_qKiv12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SHX", doubleMetaphone.doubleMetaphone("SH", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSIO_NagW13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("SIO", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSIA_zLGf14_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("SIA", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSIAN_YZZn15_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SX", doubleMetaphone.doubleMetaphone("SIAN", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSHHEIM_UaXt1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SHEIM", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSHHOEK_FrCB2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SHOEK", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSHHOLM_wzCh3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SHOLM", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSHHOLZ_UdLk4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SHOLZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIAN_guRU7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SIAN", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ_twDE8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("Z", doubleMetaphone.doubleMetaphone("Z", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ1_wuGt9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z1", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ2_jGIz10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z2", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ3_MUPw11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z3", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ4_UUPT12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z4", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ5_uxXQ13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z5", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ6_vPVv14_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z6", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ7_Advo15_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z7", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ8_nuTG16_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z8", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ9_cglm17_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z9", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ10_HKWB18_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z10", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ11_GGap19_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("Z11", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_HARAC_iAjR0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("HARAC", false);
    assertEquals("HRK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1_SWXG0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Vanchur";
    String expected = "FNXR";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1Sch_oRjf1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Schatz";
    String expected = "XTS";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1Orchid_HdXS2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Orchid";
    String expected = "ARKT";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1Space_qejB3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Hans Space";
    String expected = "HNSS";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testConditionCH1End_NQbY4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Hans";
    String expected = "HNS";
    assertEquals(expected, doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMc_dEgh0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McDonald", false);
    assertEquals("MKTN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneK_LTmL1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kirk", false);
    assertEquals("KRK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMc_MTZf0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneK_yBTU1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("K", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMc_Rxdq0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McDonald", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneK_GnpB1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Khan", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCaesar_dQgN0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SSR", doubleMetaphone.doubleMetaphone("Caesar", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMcClelland_Kkpe1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("MKLL", doubleMetaphone.doubleMetaphone("McClelland", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCC_mAez2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("PKPK", doubleMetaphone.doubleMetaphone("Backpack", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCCNotMc_YwwJ3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KKK", doubleMetaphone.doubleMetaphone("Kuckuck", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCIA_hIfC4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("FKX", doubleMetaphone.doubleMetaphone("Focaccia", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCZ_MUav5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SRN", doubleMetaphone.doubleMetaphone("Czerny", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCXKCGCQ_Iwgv6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("XK", doubleMetaphone.doubleMetaphone("Check", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCI_dMlo7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Ciao", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCIO_KBri8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Cio", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCAndSpace_aacb9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("MKFR", doubleMetaphone.doubleMetaphone("Mac Caffrey", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCia_lJfi3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Cia", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneFocus_OGKU5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("FKS", doubleMetaphone.doubleMetaphone("Focus", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMcGregor_vYjP6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("MKRK", doubleMetaphone.doubleMetaphone("McGregor", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneDoubleCC_nAfX7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KKK", doubleMetaphone.doubleMetaphone("Kockock", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCCNotMac_RVFG8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KK", doubleMetaphone.doubleMetaphone("Kock", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_OgWx0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("BACHER", false);
    assertEquals("PKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_FZLa1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("focaccia", false);
    assertEquals("FKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_BFsi4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CIA", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_oKjy5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CHIA", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_odCH6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CIO", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_xgmk7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CIA", true);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_nrKN8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CH", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_SwBh9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CZ", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCaesar_nrnr0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CAESAR", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneFocus_xwIC2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("focaccia", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCio_ldnw3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cio", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCia_LWLT4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cia", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCzerny_corv5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneDoubleCC_umcx6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Mcclelland", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMacCaffrey_CRPr7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Mac Caffrey", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMacGregor_mMPQ8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Mac Gregor", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneNoCC_baDh9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McGill", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCC_qrVn1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Macclelland", false);
    assertEquals("MKLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleCCNotMcClelland_BgEI2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hacc", false);
    assertEquals("HK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCQ_MaEm4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CQ", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCI_ccBJ5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CI", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCK_slMT6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CK", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCG_bbXF7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CG", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCQAndCE_EFdp9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CQCE", false);
    assertEquals("KS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCWithCAndSpace_ABjm10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("C MacGregor", false);
    assertEquals("KMKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithConditionL0True_FOEw0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Llama";
    String result = doubleMetaphone.doubleMetaphone(value, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithConditionL0False_sOKq1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Llano";
    String result = doubleMetaphone.doubleMetaphone(value, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneL0_TqFc0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Lillo", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneLL1_YxvA1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("LLalla", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMcCauley_fdYO1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McCauley", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneDoubleCC_WDPG5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("MacClelland", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCWithCKCGCQ_hrFe7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("check", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCWithCI_UttF8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ciao", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_McClelland_ttQj1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    assertEquals("MKLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_doubleCC_PrlE3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bellocchio", false);
    assertEquals("PLX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_accident_QCBl4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("accident", false);
    assertEquals("AKST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_bacci_qpOr5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bacci", false);
    assertEquals("PX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_MacCaffrey_dpnA6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("MacCaffrey", false);
    assertEquals("MKFR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_MacGregor_sJIk7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("MacGregor", false);
    assertEquals("MKRK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_noCCAlternate_mpbR9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hello", true);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCaesar_aNux0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SSR", doubleMetaphone.doubleMetaphone("CAESAR", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithDoubleCC_FNGP2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("MKLN", doubleMetaphone.doubleMetaphone("McKelland", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCIA_JsHh3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("FKX", doubleMetaphone.doubleMetaphone("focaccia", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCH_iuEJ5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("XRX", doubleMetaphone.doubleMetaphone("church", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCXK_OXKJk6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("XK", doubleMetaphone.doubleMetaphone("check", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCQ_qsmp7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("CQ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCG_jBun8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("K", doubleMetaphone.doubleMetaphone("CG", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCIO_bFTT9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("CIO", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCIE_HCtS10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("CIE", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCIA_BtJa11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("CIA", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCQG_OgCV12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KK", doubleMetaphone.doubleMetaphone("CQG", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithCQGAlternate_HDYb13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("KK", doubleMetaphone.doubleMetaphone("CQG", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_lgjc1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("bacchus", false);
    assertEquals("PKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_AYnP3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("accede", false);
    assertEquals("AKST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_TYfZ4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("succeed", false);
    assertEquals("SKST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_dwjA5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("SRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithH_Mllw0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Phoebe", false);
    assertEquals("FP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithPH_oSSW1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Philip", false);
    assertEquals("FLP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithBP_vKUs2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bobbie", false);
    assertEquals("PP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneIE_uggY0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("FE", true);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMEAndMA_EYth1_1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("ME", true);
    result = doubleMetaphone.doubleMetaphone("MA", true);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneR_viqb2_1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("R", true);
    result = doubleMetaphone.doubleMetaphone("Ra", true);
    result = doubleMetaphone.doubleMetaphone("Rae", true);
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWR_iiEb0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Write", true);
    assertEquals("RT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWRInMiddle_Jdmc1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Writers", true);
    assertEquals("RTRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneSCH_EZix2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schenck", true);
    assertEquals("SKNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWRAndSCH_RmVU3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("SchenckWrite", true);
    assertEquals("SKNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTH_qWbI0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("thomas", false);
    assertEquals("TMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithVFN_fkia1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("van", false);
    assertEquals("FN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSCH_dERM3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("SCHmidt", false);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithTAndD_hoDj4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Tend", false);
    assertEquals("TNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithZo_ukSA0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zoology", false);
    assertEquals("SLJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSi_JrSV1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Si", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSa_iprS2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Sa", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithSlavoGermanic_rPOf3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zo", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWithoutSlavoGermanic_tXcB4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("So", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG1_vcwS0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hannah", false);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG2_hrAt1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Nancy", false);
    assertEquals("NNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG3_aQEt2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("LLily", false);
    assertEquals("LL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG4_BdDd3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knee", false);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG5_xZUF4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Yancy", false);
    assertEquals("ANS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG6_sAYU5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gery", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG7_GMtr6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Danger", false);
    assertEquals("TNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG8_dHFX7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ranger", false);
    assertEquals("RNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG9_WaUz8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Manger", false);
    assertEquals("MNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG10_yoUx9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Aggie", false);
    assertEquals("AJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG11_MKaq10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Vanity", false);
    assertEquals("FNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG12_fsSX11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schie", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG13_btpU12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Eiger", false);
    assertEquals("AJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG14_BtHb13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Geyer", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG16_UCpG15() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knee", true);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG17_OaZR16() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Yancy", true);
    assertEquals("ANS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG1_GPvz0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Hanger";
    boolean slavoGermanic = false;
    assertEquals("HNKR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG2_rALJ1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Nanger";
    boolean slavoGermanic = false;
    assertEquals("NNKR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG3_ZkPJ2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Linger";
    boolean slavoGermanic = false;
    assertEquals("LNKR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG4_XppZ3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Ginger";
    boolean slavoGermanic = false;
    assertEquals("KNKR", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG5_JMPi4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = true;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG6_KtSN5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = false;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG7_XMjM6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = false;
    assertEquals("NT", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG8_FnGq7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knee";
    boolean slavoGermanic = false;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG9_XADQ8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = true;
    assertEquals("NT", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG10_aPLJ9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knee";
    boolean slavoGermanic = true;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG11_zhOW10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = false;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG12_ifze11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knie";
    boolean slavoGermanic = true;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG13_VZTz12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = false;
    assertEquals("NT", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG14_xlfn13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knee";
    boolean slavoGermanic = false;
    assertEquals("N", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG15_PDUa14() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String value = "Knead";
    boolean slavoGermanic = true;
    assertEquals("NT", doubleMetaphone.doubleMetaphone(value, true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_Jose_ycHK0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("HS", doubleMetaphone.doubleMetaphone("Jose", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanJacinto_waJr1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SNHS", doubleMetaphone.doubleMetaphone("San Jacinto", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_SanWithoutSpace_kBbs3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SN", doubleMetaphone.doubleMetaphone("San", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithoutVowelBefore_eras4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("J", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithVowelBeforeAndAAfter_fQLk5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("Ja", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithVowelBeforeAndOAfter_WuPs6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("Jo", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JWithFollowingJ_tNBw9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("J", doubleMetaphone.doubleMetaphone("JJ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_JKWithFollowingNonVowelConsonant_gwLS10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("JK", doubleMetaphone.doubleMetaphone("JKk", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_S_xtks0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("S", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_A_QAaP3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("A", doubleMetaphone.doubleMetaphone("aux", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_A_index_2_srNO4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("A", doubleMetaphone.doubleMetaphone("eau", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_A_index_3_AYjh5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("A", doubleMetaphone.doubleMetaphone("ou", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_AKS_index_4_oDOa6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("AKS", doubleMetaphone.doubleMetaphone("auxx", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone_A_index_5_tWgg7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("A", doubleMetaphone.doubleMetaphone("eauu", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH1_HCNb0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("HUGH", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH2_KqCl1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("laugh", false);
    assertEquals("LF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH3_xEpn2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("cough", false);
    assertEquals("KF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH4_ylHi3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("gough", false);
    assertEquals("KF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH5_pnhk4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("rough", false);
    assertEquals("RF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleGH6_SWqP5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("tough", false);
    assertEquals("TF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG1_sWex6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("ger", false);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG2_ZuGi7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("gy", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG3_ebrk8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("german", false);
    assertEquals("KRMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG4_nqTw9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("germany", false);
    assertEquals("KRMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG5_fsZh10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("germanic", false);
    assertEquals("KRMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleG6_FVFO11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("germanic", true);
    assertEquals("JRMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithISL_JYkE0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("island", false);
    assertEquals("ALNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithYAL_INto1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("ysle", false);
    assertEquals("AL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSUGAR_LwxS2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sugar", false);
    assertEquals("XKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSH_DCCN3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sheim", false);
    assertEquals("SM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithSC_LPvK5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schermerhorn", false);
    assertEquals("XRMR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndOOER_UusSKL6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("school", false);
    assertEquals("SKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndEN_NfQu8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schenker", false);
    assertEquals("XNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleSWithHAndNotVowel_jaRQ9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hsiao", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCZAndWITZ_xllN0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CZIT", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneCZ_XkId1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CZAR", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneWITZ_FmfN2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("WITZEL", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneOtherCases_XMMY3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hello", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneNullInput_ZwPR4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone(null, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneEmptyInput_fkIz5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneSingleCharacterInput_LxeP6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("A", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMultiCharacterInput_ZEKi7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("HelloWorld", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSN_ANTT7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SN", doubleMetaphone.doubleMetaphone("SN", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSHwithZ_RFaH8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("XS", doubleMetaphone.doubleMetaphone("SHZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIOwithZ_vrTJ9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SS", doubleMetaphone.doubleMetaphone("SIZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIANwithZ_DhqP11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("SNS", doubleMetaphone.doubleMetaphone("SIANZ", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSH_qKiv12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("SH", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSIO_NagW13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("SIO", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSIA_zLGf14() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("X", doubleMetaphone.doubleMetaphone("SIA", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAlternateSIAN_YZZn15() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("XN", doubleMetaphone.doubleMetaphone("SIAN", true));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIO_ecay5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SIO", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSIA_qNDz6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("SIA", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ1_wuGt9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z1", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ2_jGIz10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z2", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ3_MUPw11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z3", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ4_UUPT12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z4", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ5_uxSQ13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z5", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ6_vPVv14() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z6", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ7_Advo15() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z7", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ8_nuTG16() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z8", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ9_cglm17() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z9", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ10_HKWB18() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z10", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testZ11_GGap19() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    assertEquals("S", doubleMetaphone.doubleMetaphone("Z11", false));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneMEAndMA_EYth1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("ME", true);
    assertEquals("M", result);
    result = doubleMetaphone.doubleMetaphone("MA", true);
    assertEquals("M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphoneR_viqb2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("R", true);
    assertEquals("R", result);
    result = doubleMetaphone.doubleMetaphone("Ra", true);
    assertEquals("R", result);
    result = doubleMetaphone.doubleMetaphone("Rae", true);
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_qvQU2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("K0RN", false);
    assertEquals("K0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_zOzd3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("CzeSK", false);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_WUKV10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("FKSocus", false);
    assertEquals("FKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_mVPd11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("FKSocus", true);
    assertEquals("FKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_dwPRo11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("PRauer", true);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_VKql5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("", true);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_Emsh11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("", false);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_cexN0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Archer", true);
    Assertions.assertEquals("ER0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_nWNR1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Archer", false);
    Assertions.assertEquals("ER0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_bbYr2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", true);
    Assertions.assertEquals("P0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_QYWd3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", false);
    Assertions.assertEquals("P0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_ByzH4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Harrison", true);
    Assertions.assertEquals("HRNZN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_bviQ5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Harrison", false);
    Assertions.assertEquals("HRNZN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_nMnw6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Keller", true);
    Assertions.assertEquals("K0L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_huVR7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Keller", false);
    Assertions.assertEquals("K0L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_gsRw8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", true);
    Assertions.assertEquals("SM0T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_owyO9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", false);
    Assertions.assertEquals("SM0T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_yeIF10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wagner", true);
    Assertions.assertEquals("WAGN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_FYOk11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wagner", false);
    Assertions.assertEquals("WAGN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_cQDt0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karl", false);
    assertEquals("Karl", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_FFYH1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karl", true);
    assertEquals("Karol", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_qvQU2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kathryn", false);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_DWCa3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kathryn", true);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_YXTx4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherine", false);
    assertEquals("Ketrin", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_jMdf5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherine", true);
    assertEquals("Ketrin", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_PLKf8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katheryn", false);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_uroD9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katheryn", true);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_WXou10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynn", false);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_kVfA11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynn", true);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_FVmE12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynnn", false);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_uKtS13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynnn", true);
    assertEquals("Kathryn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_QIOt2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Smith", false);
    assertEquals("Sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_Wvtd3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Smith", true);
    assertEquals("Sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_veNT4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", false);
    assertEquals("K0ch", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_YcWD5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", true);
    assertEquals("K0ch", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_KMbf6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bach", false);
    assertEquals("Bach", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_gztv7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bach", true);
    assertEquals("Bach", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_Chlr8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hans", false);
    assertEquals("H0ns", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_fNbc9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hans", true);
    assertEquals("H0ns", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_XBsb10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Klaus", false);
    assertEquals("Kl0s", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_rKYh11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Klaus", true);
    assertEquals("Kl0s", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_XQDn12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wagner", false);
    assertEquals("Wagn0r", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_nHkL0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hello", false);
    assertEquals("hll", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_UeST1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("aeiou", false);
    assertEquals("0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_JZwD2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bobby", false);
    assertEquals("b", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_zOzd3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czech", false);
    assertEquals("ch", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_jsBi4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Dough", false);
    assertEquals("0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_WynQ5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Eagle", false);
    assertEquals("egl", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_CElZ6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Fancy", false);
    assertEquals("fn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_UcDC7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gnome", false);
    assertEquals("nm", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_HpvC8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Honey", false);
    assertEquals("hn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_zwlA9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Igloo", false);
    assertEquals("gl", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_jveH10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jazz", false);
    assertEquals("js", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_xegL11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kite", false);
    assertEquals("kt", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_psdg12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Llama", false);
    assertEquals("lm", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_IntA0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cecilia", false);
    assertEquals("Ss", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_hMxg1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cecil", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_FhgK2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cecil", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_Herg3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czech", false);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_pFqK4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("S X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_NxEV6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cia", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_NVWx7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cock", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_tyCm8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cock", true);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_vJXZ9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cocky", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_qcAG10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cocky", true);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_LWLO1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cecilia", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_cOEl3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czech", true);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_GkWf7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ciao", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_TZlE8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_luQG9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_WUKV10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focus", false);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_mVPd11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focus", true);
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_znDH12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    assertEquals("MKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_spng0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Catherine", true);
    assertEquals("Ketrn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_YbQS4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focaccia", true);
    assertEquals("FX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_aagY5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Clelland", true);
    assertEquals("KLND", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_hnuy6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Caffrey", true);
    assertEquals("KFRY", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_LgvZ7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gregor", true);
    assertEquals("GR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_AjOX8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chia", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_DSoa10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Clelland", false);
    assertEquals("KLND", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_ICNr11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Caffrey", false);
    assertEquals("KFRY", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_QOjm12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gregor", false);
    assertEquals("GR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_pIHC0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katz", false);
    assertEquals("KTZ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_wxhZ2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", false);
    assertEquals("KX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_UhwH3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", true);
    assertEquals("KX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_pkfW5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", true);
    assertEquals("SRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_WteH6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focaccia", false);
    assertEquals("FK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_zyFf7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focaccia", true);
    assertEquals("FK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_ihQz9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", true);
    assertEquals("MKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_PKAU10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Michael", false);
    assertEquals("MXL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_Drwo12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chemistry", false);
    assertEquals("KEMSTRY", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_JUmU0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", false);
    assertEquals("KH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_Kxct2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focaccia", false);
    assertEquals("FX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_uGpT5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Michael", false);
    assertEquals("MX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_TPfS6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chemistry", false);
    assertEquals("KEMSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_jBAg8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", true);
    assertEquals("KH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_JzDc2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chen", false);
    assertEquals("CHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_bCnK3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chen", true);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_lCmE4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("SZN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_UTJO5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", true);
    assertEquals("SZN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_ngyN8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    assertEquals("MCL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_uFur9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", true);
    assertEquals("MCL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_kVZs10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Michael", false);
    assertEquals("MXCL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_eeXQ11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Michael", true);
    assertEquals("MXCL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_sJeb2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cesar", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_gzOB3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cesar", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_lTiT10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    assertEquals("MKLND", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_UHYL11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", true);
    assertEquals("MKLND", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_SZYs0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Edge", true);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_IjlT3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DGT", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_Zwbs4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DG", true);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_JIyZ5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DTT", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_MFlu7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DGT", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_fxUD8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DG", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_ktHj0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Graham", false);
    assertEquals("Grhm", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_ctUY1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Grogan", false);
    assertEquals("Krkn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_Vdee2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Grogan", true);
    assertEquals("Krkn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Kyau1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knecht", true);
    assertEquals("NK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_lnjw0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", true);
    Assertions.assertEquals("BK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_LUsa1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", false);
    Assertions.assertEquals("BK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_pLwn2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hague", true);
    Assertions.assertEquals("HG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_YALy3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hague", false);
    Assertions.assertEquals("HG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_fYqA4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kneel", true);
    Assertions.assertEquals("KNL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_nuFV5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kneel", false);
    Assertions.assertEquals("KNL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_lOyN10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ranger", true);
    Assertions.assertEquals("RNJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_Gowr11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ranger", false);
    Assertions.assertEquals("RNJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_DPBa12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Danger", true);
    Assertions.assertEquals("DNJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_iswd13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Danger", false);
    Assertions.assertEquals("DNJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_STAo0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kangaroo", false);
    assertEquals("KNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_eFjc1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kangaroo", true);
    assertEquals("KNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_pRdk2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gang", false);
    assertEquals("GN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_ylDt3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gang", true);
    assertEquals("GN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_ivRV4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kang", false);
    assertEquals("KNG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_VEGB5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kang", true);
    assertEquals("KNG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_MKjt6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gan", false);
    assertEquals("GN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_AFek7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gan", true);
    assertEquals("GN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_NEdB10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("G", false);
    assertEquals("G", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_tKmx11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("G", true);
    assertEquals("G", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Klto1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hello", true);
    assertEquals("h0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_QWpa2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("helle", false);
    assertEquals("hll", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_nHJO3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("helle", true);
    assertEquals("h0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_mtoG4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("holl", false);
    assertEquals("hll", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_eHZO5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("holl", true);
    assertEquals("h0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_RxWV0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Archer", false);
    assertEquals("ER0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_tdLo2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("H", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_mKuO6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hearse", false);
    assertEquals("HR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_kaZO7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hearsay", false);
    assertEquals("HR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_LGlk9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hearse", true);
    assertEquals("HR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_tqlt10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hearsay", true);
    assertEquals("HR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_uyXp0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jesse", true);
    assertEquals("JES", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_UeWI1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jose", true);
    assertEquals("H0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_qBqm2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("San Jacinto", true);
    assertEquals("SN JCKNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_gcvM3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johan", true);
    assertEquals("HVH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_eaui4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannes", true);
    assertEquals("HVNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_LMPa5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquin", true);
    assertEquals("HAKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_AxJU6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquim", true);
    assertEquals("HAKM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_tfIQ7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquino", true);
    assertEquals("HKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_jiHZ8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquinia", true);
    assertEquals("HKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_GmfS9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquino", false);
    assertEquals("HAKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_QgkD10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquim", false);
    assertEquals("HAKM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_fGoc0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("John", true);
    assertEquals("JHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_vyJy3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jacinth", true);
    assertEquals("JHNTH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_Jxbc4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannes", true);
    assertEquals("HNNHS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_DIWJ5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquin", true);
    assertEquals("HWKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_zDDN6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johan", true);
    assertEquals("HNN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_qvjj7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johanna", true);
    assertEquals("HNN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_qcBf8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannesburg", true);
    assertEquals("HNNSBRG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_IyQD9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johann", true);
    assertEquals("HNN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_yjzx10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johan", false);
    assertEquals("HNN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_aSlG11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johanna", false);
    assertEquals("HNN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_Uhlf12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannes", false);
    assertEquals("HNNHS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_sdad1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karl", true);
    assertEquals("Karl", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_bzVJ2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlson", false);
    assertEquals("Karlsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_IMVr3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlson", true);
    assertEquals("Karlsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_xcbv4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlsson", false);
    assertEquals("Karlsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_YcMa5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlsson", true);
    assertEquals("Karlsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_zbjc6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlssen", false);
    assertEquals("Karlsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_ZWYQ7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlssen", true);
    assertEquals("Karlsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_jGIl2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", false);
    assertEquals("SMHT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_BXtP3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", true);
    assertEquals("SM0T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_ZfJD5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", true);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_Rsfm6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hoffmann", false);
    assertEquals("HFNMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_zTeR7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hoffmann", true);
    assertEquals("HFNMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_XIPT8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kraus", false);
    assertEquals("KRAS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_gDWm9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kraus", true);
    assertEquals("KR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_RaSv10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bauer", false);
    assertEquals("BRR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_dwBo11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bauer", true);
    assertEquals("B", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_qMve12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Klaus", false);
    assertEquals("KLAS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_ihWK13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Klaus", true);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_DOqT0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", false);
    assertEquals("BK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_oItg1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", true);
    assertEquals("BK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_lHvT2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bakers", false);
    assertEquals("BKRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_wfhV3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bakers", true);
    assertEquals("BKRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_kzFy0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koala", false);
    assertEquals("KLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_gMUw1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koalas", false);
    assertEquals("KLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_KeuD2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koalas", true);
    assertEquals("KLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_OwbD3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koala", true);
    assertEquals("KLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_JJkr1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karl", false);
    assertEquals("Kar", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_tgfY0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smith", false);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_MtHk1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smiths", false);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_gvnp2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithson", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_sddz3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonn", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_ePpx4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsona", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_MzPS5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonb", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_mKDz6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonc", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_FGHC7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsond", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_hfCY8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsone", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_fygl9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonf", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_VvuI10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsoni", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_BsqS11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonj", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_gPHW12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonk", false);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_ncPo3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithe", false);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_kkjf4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithee", false);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_lmEK5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smitheo", false);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_TYLn6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithey", false);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_ZztI8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smitheo", true);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_lJgn9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smiths", true);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_HuFO10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithson", true);
    assertEquals("sm0thsn", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_XQyg11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithee", true);
    assertEquals("sm0th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_aHlE1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smith", true);
    assertEquals("sm1th", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_ZJGG2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schmidt", false);
    assertEquals("sm0t", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_esVq3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schmidt", true);
    assertEquals("sm1t", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_XjNi4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("snider", false);
    assertEquals("sn1d", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_JhtF5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("snider", true);
    assertEquals("sn1d", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_aTPy6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sugar", false);
    assertEquals("sg1r", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_gATt7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sugar", true);
    assertEquals("sg1r", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_qMLa8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("island", false);
    assertEquals("isl1nd", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_lNjj9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("island", true);
    assertEquals("isl1nd", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_ajAs10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("carlisle", false);
    assertEquals("kr1sl", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_TtiQ11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("carlisle", true);
    assertEquals("kr1sl", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_yNJM12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("resnais", false);
    assertEquals("rzn1s", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_Npri13_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("resnais", true);
    assertEquals("rzn1s", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_FXUI0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomas", true);
    assertEquals("T0M5", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_MAND1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thames", true);
    assertEquals("T0M5", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_hIrP2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Tion", true);
    assertEquals("X0N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_JetW3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Tia", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_SzOj4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Tch", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_pQeh7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thoma", true);
    assertEquals("T0M5", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_VyfC8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thoms", true);
    assertEquals("T0M5", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_OSsN9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomson", true);
    assertEquals("T0M5N1", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_vHVd10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomson", false);
    assertEquals("T0M5N1", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_KihI11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thom", true);
    assertEquals("T0M", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_KoNb12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomsen", true);
    assertEquals("T0M5N1", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Djys1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wasserman", true);
    assertEquals("VSRMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_gXye2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Uomo", true);
    assertEquals("UM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_pzes3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Filipowicz", true);
    assertEquals("FPLP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_utWK4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Arnow", false);
    assertEquals("ARNF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_mZNH5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wasserman", false);
    assertEquals("VSRMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_PFoF6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Uomo", false);
    assertEquals("UM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_Vith7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Filipowicz", false);
    assertEquals("FPLP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_VRke2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Womo", true);
    assertEquals("VOM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_vioI4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Sch", true);
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_VKql5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("W", true);
    assertEquals("W", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_bHqF7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wh", true);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_EmpD8_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("EWSKI", true);
    assertEquals("FSK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_BOGg9_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("WICZ", true);
    assertEquals("TS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_xmpH10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("WITZ", true);
    assertEquals("TS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_Emsh11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("W", false);
    assertEquals("W", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_yCIU0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Breaux", false);
    assertEquals("BRKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_aYSZ1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Braun", false);
    assertEquals("BRAN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_UgYf2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Braun", true);
    assertEquals("BRAN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_bsVo0_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kathryn", false);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Jrxw1_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kathryn", true);
    assertEquals("KTHRHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_OVNf2_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", false);
    assertEquals("SMXDT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_DgVa3_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", true);
    assertEquals("SMXDT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_FjEB4_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zhao", false);
    assertEquals("ZHR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_CMTp5_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zhao", true);
    assertEquals("ZHR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_cmqb6_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Angelina", false);
    assertEquals("NJLN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_Ldqk7_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Angelina", true);
    assertEquals("NJLNLN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_ltux10_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Khan", false);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_YqRL11_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Khan", true);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_cKsQ12_fid2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kohn", false);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_cexN0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Archer", true);
    Assertions.assertEquals("ARKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_nWNR1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Archer", false);
    Assertions.assertEquals("ARXR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_bbYr2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", true);
    Assertions.assertEquals("PKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_QYWd3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", false);
    Assertions.assertEquals("PKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_ByzH4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Harrison", true);
    Assertions.assertEquals("HRSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_bviQ5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Harrison", false);
    Assertions.assertEquals("HRSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_nMnw6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Keller", true);
    Assertions.assertEquals("KLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_huVR7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Keller", false);
    Assertions.assertEquals("KLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_gsRw8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", true);
    Assertions.assertEquals("SMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_owyO9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", false);
    Assertions.assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_yeIF10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wagner", true);
    Assertions.assertEquals("FKNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_FYOk11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wagner", false);
    Assertions.assertEquals("AKNR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_FzwW12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("", true);
    Assertions.assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_cQDt0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("KRL", false);
    assertEquals("KRL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_FFYH1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karl", true);
    assertEquals("KRL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_DWCa3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("KTRN", true);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_YXTx4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherine", false);
    assertEquals("K0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_jMdf5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherine", true);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_PLKf8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katheryn", false);
    assertEquals("K0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_uroD9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katheryn", true);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_WXou10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynn", false);
    assertEquals("K0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_kVfA11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynn", true);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_FVmE12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynnn", false);
    assertEquals("K0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_uKtS13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katherynnn", true);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_QIOt2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Smith", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_Wvtd3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Smith", true);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_veNT4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_YcWD5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_KMbf6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("PK", false);
    assertEquals("PK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_gztv7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("PK", true);
    assertEquals("PK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_Chlr8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hans", false);
    assertEquals("HNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_fNbc9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hans", true);
    assertEquals("HNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_XBsb10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Klaus", false);
    assertEquals("KLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_rKYh11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Klaus", true);
    assertEquals("KLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_XQDn12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wagner", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_nHkL0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hello", false);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_UeST1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("aeiou", false);
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_jsBi4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Dough", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_WynQ5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Eagle", false);
    assertEquals("AKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_CElZ6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Fancy", false);
    assertEquals("FNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_UcDC7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gnome", false);
    assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_HpvC8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Honey", false);
    assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_zwlA9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("IAKLoo", false);
    assertEquals("AKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_jveH10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jazz", false);
    assertEquals("JS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_xegL11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kite", false);
    assertEquals("KT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_psdg12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Llama", false);
    assertEquals("LM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_IntA0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cecilia", false);
    assertEquals("SSL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_Herg3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czech", false);
    assertEquals("SK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_pFqK4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", false);
    assertEquals("SRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_AxnX5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cia", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_NVWx7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cock", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_tyCm8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cock", true);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_vJXZ9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cocky", false);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_qcAG10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cocky", true);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_cOEl3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czech", true);
    assertEquals("XK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_vpAr4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chia", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_DGFR5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chia", true);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_CRTM6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ciao", false);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_znDH12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", false);
    assertEquals("MKLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_spng0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Catherine", true);
    assertEquals("KTRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_YbQS4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focaccia", true);
    assertEquals("FKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_aagY5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Clelland", true);
    assertEquals("KLLN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_hnuy6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Caffrey", true);
    assertEquals("KFR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_LgvZ7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gregor", true);
    assertEquals("KRKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_DSoa10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Clelland", false);
    assertEquals("KLLN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_ICNr11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Caffrey", false);
    assertEquals("KFR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_QOjm12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gregor", false);
    assertEquals("KRKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_pIHC0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katz", false);
    assertEquals("KTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_WrRl1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Katz", true);
    assertEquals("KTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_UhwH3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koch", true);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_pkfW5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Czerny", true);
    assertEquals("XRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_WteH6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Focaccia", false);
    assertEquals("FKX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_ihQz9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("McClelland", true);
    assertEquals("MKLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_PKAU10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Michael", false);
    assertEquals("MKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_RVSm11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Michael", true);
    assertEquals("MXL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_Drwo12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chemistry", false);
    assertEquals("KMST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_TCJx1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cherny", false);
    assertEquals("XRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_eXvd7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chorus", false);
    assertEquals("KRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_wzIT9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Cherny", true);
    assertEquals("XRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_JzDc2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chen", false);
    assertEquals("XN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_bCnK3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Chen", true);
    assertEquals("XN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_SZYs0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Edge", true);
    assertEquals("AJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_fDjq1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DT", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_ZIzS2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DT", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_Zwbs4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DG", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_JIyZ5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DTT", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_MFlu7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("DGT", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_WEgk9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("D", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_uJzu10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("D", false);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_gMFc11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_lyNZ12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone(null, true);
    assertEquals(null, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_ktHj0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Graham", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_ctUY1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Grogan", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_Vdee2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Grogan", true);
    assertEquals("KRKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_QVKB0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knecht", false);
    assertEquals("NKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Kyau1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knecht", true);
    assertEquals("NKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_IXtW2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knick", false);
    assertEquals("NK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_HaDw3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knick", true);
    assertEquals("NK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_UgzJ4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knie", false);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_ljnN5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Knie", true);
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_pLwn2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hague", true);
    Assertions.assertEquals("HK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_YALy3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hague", false);
    Assertions.assertEquals("HK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_fYqA4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kneel", true);
    Assertions.assertEquals("NL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_nuFV5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kneel", false);
    Assertions.assertEquals("NL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_qWdg6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gnome", true);
    Assertions.assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_bwIG7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gnome", false);
    Assertions.assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_keJV8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Honey", true);
    Assertions.assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_yLcq9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Honey", false);
    Assertions.assertEquals("HN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_lOyN10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ranger", true);
    Assertions.assertEquals("RNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_Gowr11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Ranger", false);
    Assertions.assertEquals("RNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_DPBa12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Danger", true);
    Assertions.assertEquals("TNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_iswd13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Danger", false);
    Assertions.assertEquals("TNJR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_STAo0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kangaroo", false);
    assertEquals("KNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_eFjc1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kangaroo", true);
    assertEquals("KNKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_pRdk2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gang", false);
    assertEquals("KNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_ylDt3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gang", true);
    assertEquals("KNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_ivRV4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kang", false);
    assertEquals("KNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_VEGB5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kang", true);
    assertEquals("KNK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_MKjt6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gan", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_AFek7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Gan", true);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_zwSI8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kan", false);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_wfvD9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kan", true);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_NEdB10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("K", false);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_tKmx11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("K", true);
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_uzQz12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Klto1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("hello", true);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_QWpa2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("helle", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_nHJO3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("helle", true);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_mtoG4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("holl", false);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_eHZO5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("holl", true);
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_RxWV0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Archer", false);
    assertEquals("ARXR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_PVVR1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Harrison", false);
    assertEquals("HRSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_kBCp3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("He", false);
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_AvwJ4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hear", false);
    assertEquals("HR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_DKfD5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hearst", false);
    assertEquals("HRST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_hwEc8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hearst", true);
    assertEquals("HRST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_kPyb12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone(null, false);
    assertEquals(null, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_uyXp0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jesse", true);
    assertEquals("AS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_UeWI1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jose", true);
    assertEquals("HS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_qBqm2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("San Jacinto", true);
    assertEquals("SNHS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_gcvM3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johan", true);
    assertEquals("AHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_eaui4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannes", true);
    assertEquals("AHNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_LMPa5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquin", true);
    assertEquals("AKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_AxJU6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquim", true);
    assertEquals("AKM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_tfIQ7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquino", true);
    assertEquals("AKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_jiHZ8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquinia", true);
    assertEquals("AKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_GmfS9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquino", false);
    assertEquals("JKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_QgkD10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Joaquim", false);
    assertEquals("JKM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_fGoc0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("John", true);
    assertEquals("AN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_nDyr2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("San", true);
    assertEquals("SN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_vyJy3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Jacinth", true);
    assertEquals("ASNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_qvjj7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johanna", true);
    assertEquals("AHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_qcBf8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannesburg", true);
    assertEquals("AHNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_IyQD9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johann", true);
    assertEquals("AHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_yjzx10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johan", false);
    assertEquals("JHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_aSlG11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johanna", false);
    assertEquals("JHN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_Uhlf12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Johannes", false);
    assertEquals("JHNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_sdad1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("KRL", true);
    assertEquals("KRL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_bzVJ2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlson", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_IMVr3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlson", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_xcbv4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlsson", false);
    assertEquals("KRLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_YcMa5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlsson", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_zbjc6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlssen", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_ZWYQ7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Karlssen", true);
    assertEquals("KRLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_jGIl2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", false);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_BXtP3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Schmidt", true);
    assertEquals("SMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_ZfJD5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("KKoch", true);
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_Rsfm6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hoffmann", false);
    assertEquals("HFMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_zTeR7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Hoffmann", true);
    assertEquals("HFMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_XIPT8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kraus", false);
    assertEquals("KRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_gDWm9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kraus", true);
    assertEquals("KRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_RaSv10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bauer", false);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_DOqT0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", false);
    assertEquals("PKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_oItg1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Baker", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_lHvT2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bakers", false);
    assertEquals("PKRS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_wfhV3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Bakers", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_kzFy0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koala", false);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_gMUw1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koalas", false);
    assertEquals("KLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_KeuD2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koalas", true);
    assertEquals("KLS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_OwbD3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Koala", true);
    assertEquals("KL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_JJkr1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("KRLl", false);
    assertEquals("KRL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_tgfY0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smith", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_MtHk1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smiths", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_gvnp2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithson", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_sddz3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonn", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_ePpx4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsona", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_MzPS5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonb", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_mKDz6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonc", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_FGHC7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsond", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_hfCY8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsone", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_fygl9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonf", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_VvuI10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsoni", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_BsqS11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonj", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_gPHW12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithsonk", false);
    assertEquals("SM0S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_ncPo3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithe", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_kkjf4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithee", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_lmEK5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smitheo", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_TYLn6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithey", false);
    assertEquals("SM0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_ZztI8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smitheo", true);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_lJgn9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smiths", true);
    assertEquals("XMTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_HuFO10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithson", true);
    assertEquals("XMTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_XQyg11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smithee", true);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_aHlE1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("smith", true);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_ZJGG2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schmidt", false);
    assertEquals("XMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_esVq3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("schmidt", true);
    assertEquals("SMT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_XjNi4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("snider", false);
    assertEquals("SNTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_JhtF5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("snider", true);
    assertEquals("XNTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_aTPy6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sugar", false);
    assertEquals("XKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_gATt7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("sugar", true);
    assertEquals("SKR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_qMLa8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("island", false);
    assertEquals("ALNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_lNjj9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("island", true);
    assertEquals("ALNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_ajAs10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("carlisle", false);
    assertEquals("KRLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_TtiQ11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("carlisle", true);
    assertEquals("KRLL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_yNJM12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("resnais", false);
    assertEquals("RSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone14_Npri13() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("resnais", true);
    assertEquals("RSNS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_FXUI0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomas", true);
    assertEquals("TMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_MAND1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thames", true);
    assertEquals("TMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_hIrP2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Tion", true);
    assertEquals("XN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_jHlw5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Th", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_dCyp6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("T", true);
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_pQeh7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thoma", true);
    assertEquals("TM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_VyfC8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thoms", true);
    assertEquals("TMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_OSsN9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomson", true);
    assertEquals("TMSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_vHVd10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomson", false);
    assertEquals("TMSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_KihI11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thom", true);
    assertEquals("TM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_KoNb12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Thomsen", true);
    assertEquals("TMSN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_QTwB0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Arnow", true);
    assertEquals("ARNF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Djys1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wasserman", true);
    assertEquals("FSRM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_gXye2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Uomo", true);
    assertEquals("AM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone4_pzes3() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Filipowicz", true);
    assertEquals("FLPF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_utWK4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Arnow", false);
    assertEquals("ARN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_mZNH5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wasserman", false);
    assertEquals("ASRM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_PFoF6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Uomo", false);
    assertEquals("AM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_Vith7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Filipowicz", false);
    assertEquals("FLPT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_VRke2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Womo", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_vioI4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Sch", true);
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_pdhV6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wr", true);
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_bHqF7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Wh", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone9_EmpD8() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("EWSKI", true);
    assertEquals("AFSK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone10_BOGg9() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("WICZ", true);
    assertEquals("FKTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_xmpH10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("WITZ", true);
    assertEquals("FTS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_yCIU0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Breaux", false);
    assertEquals("PR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_aYSZ1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Braun", false);
    assertEquals("PRN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone3_UgYf2() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Braun", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone1_bsVo0() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kathryn", false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone2_Jrxw1() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kathryn", true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone5_FjEB4() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zhao", false);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone6_CMTp5() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Zhao", true);
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone7_cmqb6() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Angelina", false);
    assertEquals("ANJL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone8_Ldqk7() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Angelina", true);
    assertEquals("ANKL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone11_ltux10() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Khan", false);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone12_YqRL11() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Khan", true);
    assertEquals("KN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testDoubleMetaphone13_cKsQ12() {
    DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
    String result = doubleMetaphone.doubleMetaphone("Kohn", false);
    assertEquals("KN", result);
    }
}