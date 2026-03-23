/**
 * Filtered unit tests for method: parsePattern(final String pattern)
 * Original class: PatternOptionBuilder
 * Tests that actually call the target method
 */
package org.apache.commons.cli;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo2_unit_parsePattern_PatternOptionBuilder_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOption_ZLdK0_3() {
    String pattern = "a";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertEquals("a", option.getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArg_lzMo1_3() {
    String pattern = "a:";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertEquals("a", option.getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArgAndRequired_LheD2_3() {
    String pattern = "a:!";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertEquals("a", option.getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOption_ZLdK0_1() {
    String pattern = "a";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOption_ZLdK0_2() {
    String pattern = "a";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertNotNull(option);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArg_lzMo1_1() {
    String pattern = "a:";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArg_lzMo1_2() {
    String pattern = "a:";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertNotNull(option);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArg_lzMo1_4() {
    String pattern = "a:";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertTrue(option.hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArgAndRequired_LheD2_1() {
    String pattern = "a:!";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArgAndRequired_LheD2_2() {
    String pattern = "a:!";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertNotNull(option);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArgAndRequired_LheD2_4() {
    String pattern = "a:!";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertTrue(option.hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndArgAndRequired_LheD2_5() {
    String pattern = "a:!";
    Options options = PatternOptionBuilder.parsePattern(pattern);
    Option option = options.getOption("a");
    assertTrue(option.isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern1_MAmz0() {
    Options options = PatternOptionBuilder.parsePattern("a");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern2_mQgP1() {
    Options options = PatternOptionBuilder.parsePattern("a:b");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern3_xoSM2() {
    Options options = PatternOptionBuilder.parsePattern("a:b!");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    assertEquals(true, options.getOption("a").hasArg());
    assertEquals(true, options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern4_wsOI3() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern5_NZsh4() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c!");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    assertEquals(true, options.getOption("a").hasArg());
    assertEquals(true, options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern6_KtCg5() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern7_JDGI6() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d!");
    assertEquals(1, options.getOptions().size());
    assertEquals("a", options.getOption("a").getLongOpt());
    assertEquals(true, options.getOption("a").hasArg());
    assertEquals(true, options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern1_MAmz0_2() {
    Options options = PatternOptionBuilder.parsePattern("a");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern2_mQgP1_1_fid2() {
    Options options = PatternOptionBuilder.parsePattern("a:b");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern2_mQgP1_2() {
    Options options = PatternOptionBuilder.parsePattern("a:b");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern3_xoSM2_1() {
    Options options = PatternOptionBuilder.parsePattern("a:b!");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern3_xoSM2_2() {
    Options options = PatternOptionBuilder.parsePattern("a:b!");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern3_xoSM2_4() {
    Options options = PatternOptionBuilder.parsePattern("a:b!");
    assertEquals(true, options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern4_wsOI3_1() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern4_wsOI3_2() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern5_NZsh4_1() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c!");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern5_NZsh4_2() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c!");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern5_NZsh4_4() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c!");
    assertEquals(true, options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern6_KtCg5_1() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern6_KtCg5_2() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern7_JDGI6_1() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d!");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern7_JDGI6_2() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d!");
    assertEquals("a", options.getOption("a").getLongOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern7_JDGI6_4() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d!");
    assertEquals(true, options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern1_MAmz0_1() {
    Options options = PatternOptionBuilder.parsePattern("a");
    assertEquals(1, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern2_mQgP1_1() {
    Options options = PatternOptionBuilder.parsePattern("a:b");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern2_mQgP1_3() {
    Options options = PatternOptionBuilder.parsePattern("a:b");
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern3_xoSM2_3() {
    Options options = PatternOptionBuilder.parsePattern("a:b!");
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern4_wsOI3_3() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c");
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern5_NZsh4_3() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c!");
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern6_KtCg5_3() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d");
    assertEquals(true, options.getOption("a").hasArg());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePattern7_JDGI6_3() {
    Options options = PatternOptionBuilder.parsePattern("a:b:c:d!");
    assertEquals(true, options.getOption("a").hasArg());
    }
}