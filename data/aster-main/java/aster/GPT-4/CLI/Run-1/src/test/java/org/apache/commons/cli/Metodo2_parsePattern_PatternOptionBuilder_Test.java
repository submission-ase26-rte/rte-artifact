/**
 * Extracted tests for method: parsePattern(final String pattern)
 * Original class: PatternOptionBuilder
 * Source: ASTER GPT-4
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo2_parsePattern_PatternOptionBuilder_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithMultipleOptionsAndTypes_Otag4_1() {
    Options options = PatternOptionBuilder.parsePattern("a%d");
    assertEquals(String.class, options.getOption("a").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithMultipleOptionsAndTypes_Otag4_2() {
    Options options = PatternOptionBuilder.parsePattern("a%d");
    assertEquals(Integer.class, options.getOption("d").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithRequiredAndType_dzEX5_2() {
    Options options = PatternOptionBuilder.parsePattern("a!%");
    assertEquals(String.class, options.getOption("a").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithAllComplexities_sBNB6_2() {
    Options options = PatternOptionBuilder.parsePattern("a!%b@");
    assertEquals(String.class, options.getOption("a").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithAllComplexities_sBNB6_4() {
    Options options = PatternOptionBuilder.parsePattern("a!%b@");
    assertEquals(File.class, options.getOption("b").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndType_ddAR3_fid1() {
    Options options = PatternOptionBuilder.parsePattern("a%");
    assertEquals(String.class, options.getOption("a").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithMultipleOptionsAndTypes_Otag4() {
    Options options = PatternOptionBuilder.parsePattern("a%d");
    assertEquals(String.class, options.getOption("a").getType());
    assertEquals(Integer.class, options.getOption("d").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithRequiredAndType_dzEX5() {
    Options options = PatternOptionBuilder.parsePattern("a!%");
    assertTrue(options.getOption("a").isRequired());
    assertEquals(String.class, options.getOption("a").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithAllComplexities_sBNB6() {
    Options options = PatternOptionBuilder.parsePattern("a!%b@");
    assertTrue(options.getOption("a").isRequired());
    assertEquals(String.class, options.getOption("a").getType());
    assertTrue(options.hasOption("b"));
    assertEquals(File.class, options.getOption("b").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithSingleOption_qsuE0() {
    Options options = PatternOptionBuilder.parsePattern("a");
    assertTrue(options.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithRequiredOption_jBds1() {
    Options options = PatternOptionBuilder.parsePattern("a!");
    assertTrue(options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithMultipleOptions_kdYH2_1() {
    Options options = PatternOptionBuilder.parsePattern("ab");
    assertTrue(options.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithMultipleOptions_kdYH2_2() {
    Options options = PatternOptionBuilder.parsePattern("ab");
    assertTrue(options.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithOptionAndType_ddAR3() {
    Options options = PatternOptionBuilder.parsePattern("a%");
    assertEquals(Number.class, options.getOption("a").getType());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithRequiredAndType_dzEX5_1() {
    Options options = PatternOptionBuilder.parsePattern("a!%");
    assertTrue(options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithAllComplexities_sBNB6_1() {
    Options options = PatternOptionBuilder.parsePattern("a!%b@");
    assertTrue(options.getOption("a").isRequired());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithAllComplexities_sBNB6_3() {
    Options options = PatternOptionBuilder.parsePattern("a!%b@");
    assertTrue(options.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternEmpty_ptAz7() {
    Options options = PatternOptionBuilder.parsePattern("");
    assertEquals(0, options.getOptions().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParsePatternWithMultipleOptions_kdYH2() {
    Options options = PatternOptionBuilder.parsePattern("ab");
    assertTrue(options.hasOption("a"));
    assertTrue(options.hasOption("b"));
    }
}