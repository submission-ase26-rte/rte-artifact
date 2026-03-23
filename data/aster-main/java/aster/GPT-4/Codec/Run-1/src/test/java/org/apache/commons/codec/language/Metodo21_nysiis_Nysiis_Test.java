/**
 * Extracted tests for method: nysiis(String str)
 * Original class: Nysiis
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
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo21_nysiis_Nysiis_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeQToG_vcDR0() {
    Nysiis nysiis = new Nysiis();
    assertEquals("G", nysiis.nysiis("Quincy"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeKNToNN_aEND2() {
    Nysiis nysiis = new Nysiis();
    assertEquals("NN", nysiis.nysiis("Kn"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeSCHToSSS_NIGR3() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SSS", nysiis.nysiis("Sch"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodePToFF_SZov4() {
    Nysiis nysiis = new Nysiis();
    assertEquals("FF", nysiis.nysiis("Ph"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeHWithNonVowelNext_pyjU6() {
    Nysiis nysiis = new Nysiis();
    assertEquals("C", nysiis.nysiis("Hc"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeZToS_qCYh8() {
    Nysiis nysiis = new Nysiis();
    assertEquals("S", nysiis.nysiis("Z"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeMToN_gHrP9() {
    Nysiis nysiis = new Nysiis();
    assertEquals("N", nysiis.nysiis("M"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeKtoNN_tFWj1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("NN", nysiis.nysiis("Knack"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeKtoC_hSXi2() {
    Nysiis nysiis = new Nysiis();
    assertEquals("C", nysiis.nysiis("Kite"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeSCHtoSSS_lbYC3() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SSS", nysiis.nysiis("School"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodePHtoFF_kYNG4() {
    Nysiis nysiis = new Nysiis();
    assertEquals("FF", nysiis.nysiis("Phantom"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeHWithNonVowelPrev_Fofv5() {
    Nysiis nysiis = new Nysiis();
    assertEquals("C", nysiis.nysiis("Char"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeHWithNonVowelNext_tdQt6() {
    Nysiis nysiis = new Nysiis();
    assertEquals("C", nysiis.nysiis("Ache"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeWWithVowelPrev_BBIL7() {
    Nysiis nysiis = new Nysiis();
    assertEquals("A", nysiis.nysiis("Caw"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeWWithNonVowelPrev_erdg8() {
    Nysiis nysiis = new Nysiis();
    assertEquals("W", nysiis.nysiis("Tweak"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeRemainingWithMultipleRules_PIBM9_fid2() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SSSFF", nysiis.nysiis("SchwartzPh"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeQToQANCY_vcDR0() {
    Nysiis nysiis = new Nysiis();
    assertEquals("QANCY", nysiis.nysiis("Quincy"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeKToC_OAsH1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("C", nysiis.nysiis("K"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeKNToN_aEND2() {
    Nysiis nysiis = new Nysiis();
    assertEquals("N", nysiis.nysiis("Kn"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeSCHToS_NIGR3() {
    Nysiis nysiis = new Nysiis();
    assertEquals("S", nysiis.nysiis("Sch"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodePToF_SZov4() {
    Nysiis nysiis = new Nysiis();
    assertEquals("F", nysiis.nysiis("Ph"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeHWithNonVowelPrev_QsDz5() {
    Nysiis nysiis = new Nysiis();
    assertEquals("C", nysiis.nysiis("Ch"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeWWithVowelPrev_SyTK7() {
    Nysiis nysiis = new Nysiis();
    assertEquals("A", nysiis.nysiis("Aw"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeKtoNAC_tFWj1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("NAC", nysiis.nysiis("Knack"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeSCHtoSAL_lbYC3() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SAL", nysiis.nysiis("School"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodePHtoFANTAN_kYNG4() {
    Nysiis nysiis = new Nysiis();
    assertEquals("FANTAN", nysiis.nysiis("Phantom"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeTWACTWACithNonVowelPrev_erdg8() {
    Nysiis nysiis = new Nysiis();
    assertEquals("TWAC", nysiis.nysiis("Tweak"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testTranscodeRemainingWithMultipleRules_PIBM9() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SWARTS", nysiis.nysiis("SchwartzPh"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MacPrefix_RQKC2() {
    Nysiis nysiis = new Nysiis();
    assertEquals("MCCARTY", nysiis.nysiis("MacCarthy"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KnPrefix_KjhF3() {
    Nysiis nysiis = new Nysiis();
    assertEquals("NNIGHT", nysiis.nysiis("Knight"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KPrefix_FQiG4() {
    Nysiis nysiis = new Nysiis();
    assertEquals("CING", nysiis.nysiis("King"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_PhPfPrefix_FvNN5() {
    Nysiis nysiis = new Nysiis();
    assertEquals("FFILIP", nysiis.nysiis("Philip"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_SchPrefix_RvHZ6() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SSSULTAN", nysiis.nysiis("Schultz"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_EvToAf_Omij7() {
    Nysiis nysiis = new Nysiis();
    assertEquals("STAFN", nysiis.nysiis("Steven"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_VowelsToA_sRhp8() {
    Nysiis nysiis = new Nysiis();
    assertEquals("ALAN", nysiis.nysiis("Ellen"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_QToG_PPKe9() {
    Nysiis nysiis = new Nysiis();
    assertEquals("GACK", nysiis.nysiis("Quack"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_ZToS_eNbr10() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SUSAN", nysiis.nysiis("Zuzan"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MToN_eCVK11() {
    Nysiis nysiis = new Nysiis();
    assertEquals("NANCY", nysiis.nysiis("Mancy"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_HHandling_rzQl12() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SMITH", nysiis.nysiis("Smith"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_WHandling_jkxc13() {
    Nysiis nysiis = new Nysiis();
    assertEquals("CRAW", nysiis.nysiis("Crew"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MacPrefix_sBHR2() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("MacDonald");
    assertEquals("MCCDNLD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KnPrefix_RBuZ3() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Knight");
    assertEquals("NNYT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_PhPfPrefix_wSGw4() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Pfister");
    assertEquals("FFSTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_SchPrefix_GfFx5() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Schmidt");
    assertEquals("SSSMTD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_EeIeSuffix_afBi6() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Rosie");
    assertEquals("RSY", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_QToG_EiQl9() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Quincy");
    assertEquals("GNCY", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_ZToS_rbbw10() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Zoo");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MToN_tPmW11() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Moo");
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KToC_aIkO12() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Kite");
    assertEquals("CT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KnToNn_IvBW13() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Knack");
    assertEquals("NNAC", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_NullInput_tdvs0() {
    Nysiis nysiis = new Nysiis();
    assertNull(nysiis.nysiis(null));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_EmptyString_uTaE1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("", nysiis.nysiis(""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_NullInput_osNH0() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis(null);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_EmptyString_Tjnl1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("");
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_VowelConversion_NojU8() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Abe");
    assertEquals("AB", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MacPrefix_RQKC2_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("MCARTY", nysiis.nysiis("MacCarthy"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KnPrefix_KjhF3_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("NAGT", nysiis.nysiis("Knight"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KPrefix_FQiG4_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("CANG", nysiis.nysiis("King"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_PhPfPrefix_FvNN5_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("FALAP", nysiis.nysiis("Philip"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_SchPrefix_RvHZ6_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SALT", nysiis.nysiis("Schultz"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_EvToAf_Omij7_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("STAFAN", nysiis.nysiis("Steven"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_VowelsToA_sRhp8_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("ELAN", nysiis.nysiis("Ellen"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_QToG_PPKe9_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("QAC", nysiis.nysiis("Quack"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_ZToS_eNbr10_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("ZASAN", nysiis.nysiis("Zuzan"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MToN_eCVK11_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("MANCY", nysiis.nysiis("Mancy"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_HHandling_rzQl12_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("SNAT", nysiis.nysiis("Smith"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_WHandling_jkxc13_fid1() {
    Nysiis nysiis = new Nysiis();
    assertEquals("CR", nysiis.nysiis("Crew"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MacPrefix_sBHR2_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("MacDonald");
    assertEquals("MCDANA", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KnPrefix_RBuZ3_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Knight");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_PhPfPrefix_wSGw4_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Pfister");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_SchPrefix_GfFx5_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Schmidt");
    assertEquals("SNAD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_EeIeSuffix_afBi6_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Rosie");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_QToG_EiQl9_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Quincy");
    assertEquals("QANCY", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_MToN_tPmW11_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Moo");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KToC_aIkO12_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Kite");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNysiis_KnToNn_IvBW13_fid1() {
    Nysiis nysiis = new Nysiis();
    String result = nysiis.nysiis("Knack");
    assertEquals("NAC", result);
    }
}