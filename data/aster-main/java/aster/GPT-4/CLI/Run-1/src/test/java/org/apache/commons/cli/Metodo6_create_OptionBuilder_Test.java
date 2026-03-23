/**
 * Extracted tests for method: create(final String opt)
 * Original class: OptionBuilder
 * Source: ASTER GPT-4
 */
package org.apache.commons.cli;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo6_create_OptionBuilder_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateWithSingleCharOption_ENDz0_1() {
    char opt = 'a';
    Option result = OptionBuilder.create(opt);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateWithSingleCharOption_ENDz0_2() {
    char opt = 'a';
    Option result = OptionBuilder.create(opt);
    assertEquals("a", result.getOpt());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateWithNullLongOptionThrowsException_wEvc0() {
    try {
    OptionBuilder.create();
    fail("Expected IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
    assertEquals("must specify longopt", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateWithSingleCharOption_ENDz0() {
    char opt = 'a';
    Option result = OptionBuilder.create(opt);
    assertNotNull(result);
    assertEquals("a", result.getOpt());
    }
}