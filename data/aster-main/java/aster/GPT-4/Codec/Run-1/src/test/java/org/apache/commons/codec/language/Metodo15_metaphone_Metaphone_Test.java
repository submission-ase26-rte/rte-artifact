/**
 * Extracted tests for method: metaphone(final String txt)
 * Original class: Metaphone
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo15_metaphone_Metaphone_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneKT_opRG18() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("KTite");
    assertEquals("KT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWN_gKGH24() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("WNine");
    assertEquals("WN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneYL_LRGq25() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("YLellow");
    assertEquals("YL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex11_SUbn38() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("WXatch");
    assertEquals("WX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex17_PSoT44() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("YXTacht");
    assertEquals("YXT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialKFollowedByN_LHhY3_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Knot");
    assertEquals("NOT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialGFollowedByN_xidA4_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gnome");
    assertEquals("NOME", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialPFollowedByN_LpJD5_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pneumatic");
    assertEquals("NEUMATIC", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialA_txUB6_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Axe");
    assertEquals("AXE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialAE_FBYc7_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Aether");
    assertEquals("ETHER", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialWFollowedByR_sIwb8_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Write");
    assertEquals("RITE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialWFollowedByH_WXOn9_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Whale");
    assertEquals("WALE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialX_ZCic10_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xylophone");
    assertEquals("SYLOPHONE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialK_raEx11_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Kite");
    assertEquals("KITE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialG_xVwe12_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gold");
    assertEquals("GOLD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialP_VbrY13_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pterodactyl");
    assertEquals("TERODACTYL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialW_GOpy14_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wool");
    assertEquals("WOOL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialAAndVowel_dwAr15_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Apple");
    assertEquals("APPLE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialB_dloF16_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Bat");
    assertEquals("BAT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialC_Cuiq17_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cat");
    assertEquals("KAT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialD_Vgnv18_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Dog");
    assertEquals("TOG", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialF_ZvyZ19_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Fish");
    assertEquals("FISH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialJ_VdjE20_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Jazz");
    assertEquals("JAZZ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialL_QiFN21_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Lion");
    assertEquals("LION", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialM_ASUF22_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Mouse");
    assertEquals("MOUSE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialN_luLK23_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Nose");
    assertEquals("NOSE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialR_sedJ24() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Rat");
    assertEquals("RAT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialS_QpSP25_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sat");
    assertEquals("SAT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialT_dlaO26_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Tat");
    assertEquals("TAT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialV_iZBv27_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vat");
    assertEquals("FAT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialZ_lUGI28_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zoo");
    assertEquals("SOO", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialY_LPhs29_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yak");
    assertEquals("YAK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialWAndVowel_ZMwS30_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wet");
    assertEquals("WET", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialXAndConsonant_FixI31_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xmas");
    assertEquals("SMAS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialWWithR_xgnP6_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wright");
    assertEquals("RIGHT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneBAtEndAfterM_kOWd10_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Climb");
    assertEquals("CLIM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithSBeforeAndEAfter_vAcz11() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Scene");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithEAfter_wdju13() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cello");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithSHAfter_fihN14_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cash");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCHAtStart_bdzr15_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Chrome");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneDWithGAndEAfter_ivuj16_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Edge");
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneDWithoutGAndEAfter_LXvh17_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Duck");
    assertEquals("T", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGWithHAtEnd_VPkZ18() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Dough");
    assertEquals("D", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGWithNAfter_ilsK19_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sign");
    assertEquals("SIN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGWithJSound_TWdh20() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gel");
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneHWithVowelAfter_YgAU22_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Halo");
    assertEquals("HALO", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphonePWithHAfter_pAyF23() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Phone");
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWNotAtStartWithVowelAfter_YpGS26_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Coward");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithFrontVowel_ypvf9() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cite");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCH_RZzu11_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Chord");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGH_OFDs13_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Ghost");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGN_oJfe14_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gnome");
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGFrontVowel_FXul15_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Giant");
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGHard_pDPp16_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gag");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneH_MTCc17() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Honor");
    assertEquals("H", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneK_opRG18() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Kite");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneQ_QFqG20_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Quake");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneS_WAtz21_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sash");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneT_Gxch22_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Thorn");
    assertEquals("0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneV_EaLZ23_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vine");
    assertEquals("F", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneW_gKGH24() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wine");
    assertEquals("W", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneY_LRGq25() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yellow");
    assertEquals("Y", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneXEnd_lWjm26_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Box");
    assertEquals("KS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneZ_KSxB27() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zebra");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex1_qasE28() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Psychology");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex2_zLuW29() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Knight");
    assertEquals("N", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex4_iPkI31_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Christmas");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex5_tcbl32() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("School");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex6_XRdz33() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Chemistry");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex7_ZPTF34_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Achieve");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex8_rjNy35_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Character");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex10_LSLK37() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wright");
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex11_SUbn38_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Watch");
    assertEquals("W", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex12_PBmy39() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xerox");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex13_jGWr40_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Django");
    assertEquals("J", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex15_aqEN42() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Psyche");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex16_HSXk43() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Rhythm");
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex17_PSoT44_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yacht");
    assertEquals("Y", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex18_zSiu45() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zephyr");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialAWithE_eTpY3_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Aero");
    assertEquals("R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialX_BbaE6() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xylophone");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithSBeforeAndHAfter_bsOr11_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Scholar");
    assertEquals("KLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithHAfter_KEoY12_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Character");
    assertEquals("KRKTR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialKGN_YuYj3_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Kgnome");
    assertEquals("NOME", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialC_vNYt8_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cite");
    assertEquals("SITE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialP_DFDN9_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pneumonia");
    assertEquals("NEUMONIA", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialA_VYdg10_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Apple");
    assertEquals("APLE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialW_uMbB11_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wrestle");
    assertEquals("RESTLE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialB_WCSq13_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Bread");
    assertEquals("BREAD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialD_BmlW14_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Dwell");
    assertEquals("DWELL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialJ_OynV16_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Jump");
    assertEquals("JUMP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialL_xiah17_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Lamp");
    assertEquals("LAMP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialM_HwiL18_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Moose");
    assertEquals("MOOSE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialR_LCFZ20_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Rope");
    assertEquals("ROPE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialS_QZaW21_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sail");
    assertEquals("SAIL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialT_yamP22_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Tall");
    assertEquals("TALL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialV_PpTx23_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vase");
    assertEquals("FASE", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialY_krgN24_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yard");
    assertEquals("YARD", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialZ_pPMo25_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zebra");
    assertEquals("SEBRA", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialQ_caWE26_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Quilt");
    assertEquals("KILT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialH_MrIN27_fid2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Hill");
    assertEquals("HILL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithNullInput_SSOX0() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone(null);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithEmptyString_CWjG1() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("");
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithSingleCharacter_tnMt2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("A");
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialKFollowedByN_LHhY3() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Knot");
    assertEquals("NT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialGFollowedByN_xidA4() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gnome");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialPFollowedByN_LpJD5() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pneumatic");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialA_txUB6() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Axe");
    assertEquals("AKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialAE_FBYc7() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Aether");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialWFollowedByR_sIwb8() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Write");
    assertEquals("RT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialWFollowedByH_WXOn9() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Whale");
    assertEquals("WL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialX_ZCic10() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xylophone");
    assertEquals("SLFN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialK_raEx11() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Kite");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialG_xVwe12() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gold");
    assertEquals("KLT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialP_VbrY13() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pterodactyl");
    assertEquals("PTRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialW_GOpy14() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wool");
    assertEquals("WL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialAAndVowel_dwAr15() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Apple");
    assertEquals("APL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialB_dloF16() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Bat");
    assertEquals("BT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialC_Cuiq17() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cat");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialD_Vgnv18() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Dog");
    assertEquals("TK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialF_ZvyZ19() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Fish");
    assertEquals("FX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialJ_VdjE20() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Jazz");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialL_QiFN21() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Lion");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialM_ASUF22() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Mouse");
    assertEquals("MS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialN_luLK23() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Nose");
    assertEquals("NS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialS_QpSP25() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sat");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialT_dlaO26() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Tat");
    assertEquals("TT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialV_iZBv27() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vat");
    assertEquals("FT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialZ_lUGI28() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zoo");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialY_LPhs29() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yak");
    assertEquals("YK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialWAndVowel_ZMwS30() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wet");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialXAndConsonant_FixI31() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xmas");
    assertEquals("SMS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialKWithPN_AVvK2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Knot");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialGWithPN_jijz3() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gnome");
    assertEquals("NM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialPWithPN_biys4() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pneumatic");
    assertEquals("NMTK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialAWithE_yhDP5() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Aether");
    assertEquals("E0R", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialWWithR_xgnP6() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wright");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneVowelNotFirst_PAfx9() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("BA");
    assertEquals("B", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneBAtEndAfterM_kOWd10() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Climb");
    assertEquals("KLM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithIA_Vqih12() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cia");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithSHAfter_fihN14() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cash");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCHAtStart_bdzr15() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Chrome");
    assertEquals("XRM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneDWithGAndEAfter_ivuj16() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Edge");
    assertEquals("EJ", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneDWithoutGAndEAfter_LXvh17() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Duck");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGWithNAfter_ilsK19() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sign");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGWithJLSound_TWdh20() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gel");
    assertEquals("JL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneHAtEnd_YXul21() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Ah");
    assertEquals("A", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneHWithVowelAfter_YgAU22() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Halo");
    assertEquals("HL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphonePWithHAfter_pAyFN23() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Phone");
    assertEquals("FN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneSWithHAfter_INPy24() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Shoe");
    assertEquals("X", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneTWithHAfter_TcHh25() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("The");
    assertEquals("0", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWNotAtStartWithVowelAfter_YpGS26() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Coward");
    assertEquals("KWRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneXInMiddle_WcyF27() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Box");
    assertEquals("BKS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneZ_hRCq28() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zoo");
    assertEquals("S", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneXPrefix_yNdc6() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Xylophone");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCH_RZzu11() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Chord");
    assertEquals("KRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneDG_mOLK12() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Edge");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGH_OFDs13() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Ghost");
    assertEquals("KST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGFrontVowel_FXul15() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Giant");
    assertEquals("JNT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGHard_pDPp16() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Gag");
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneQ_QFqG20() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Quake");
    assertEquals("KK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneS_WAtz21() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sash");
    assertEquals("SX", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneT_Gxch22() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Thorn");
    assertEquals("0RN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneV_EaLZ23() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vine");
    assertEquals("FN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex4_iPkI31() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Christmas");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex6_KMSTRdz33() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Chemistry");
    assertEquals("KMST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex7_ZPTF34() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Achieve");
    assertEquals("AXF", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex8_rjNy35() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Character");
    assertEquals("KRKT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex9_KCsC36() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Queue");
    assertEquals("K", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex13_jGWr40() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Django");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneComplex14_LSCm41() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Laugh");
    assertEquals("L", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialKWithPN_ilQy2() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Knap");
    assertEquals("NP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialAWithE_eTpY3() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Aero");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneInitialWWithR_rdxV4() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wrap");
    assertEquals("RP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneVowelInMiddle_FnEv9() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Able");
    assertEquals("ABL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneBNotAfterMAtEnd_Dpnq10() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Rob");
    assertEquals("RB", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneCWithSBeforeAndHAfter_bsOr11() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Scholar");
    assertEquals("SKLR", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneGWithNAfter_HlLd15() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sign");
    assertEquals("SN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneHInMiddle_EkEC16() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Aha");
    assertEquals("AH", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneV_pZfp20() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vase");
    assertEquals("FS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWAtStartWithVowelAfter_rRXa21() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wet");
    assertEquals("WT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneYAtStartWithVowelAfter_jKnq24() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yet");
    assertEquals("YT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneKNotAfterC_sXUo25() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Akin");
    assertEquals("AKN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneL_kPLg26() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Lime");
    assertEquals("LM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneM_NKpE27() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Mime");
    assertEquals("MM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneN_cqNe28() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Nine");
    assertEquals("NN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneR_VrlM29() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Rope");
    assertEquals("RP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneF_xnwG30() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Fame");
    assertEquals("FM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneJ_LmyI31() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Joke");
    assertEquals("JK", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialKGN_YuYj3() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Kgnome");
    assertEquals("KNM", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialC_vNYt8() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Cite");
    assertEquals("ST", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialP_DFDN9() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Pneumonia");
    assertEquals("NMN", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialA_VYdg10() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Apple");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialW_uMbB11() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Wrestle");
    assertEquals("RSTL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialB_WCSq13() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Bread");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialD_BmlW14() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Dwell");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialJ_OynV16() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Jump");
    assertEquals("JMP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialL_xiah17() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Lamp");
    assertEquals("LMP", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialM_HwiL18() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Moose");
    assertEquals("MS", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialR_LCFZ20() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Rope");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialS_QZaW21() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Sail");
    assertEquals("SL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialT_yamP22() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Tall");
    assertEquals("TL", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialV_PpTx23() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Vase");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialY_krgN24() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Yard");
    assertEquals("YRT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialZ_pPMo25() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Zebra");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialQ_caWE26() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Quilt");
    assertEquals("KLT", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMetaphoneWithInitialH_MrIN27() {
    Metaphone metaphone = new Metaphone();
    String result = metaphone.metaphone("Hill");
    }
}