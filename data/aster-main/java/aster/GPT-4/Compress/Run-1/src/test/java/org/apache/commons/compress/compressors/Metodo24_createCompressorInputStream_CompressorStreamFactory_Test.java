/**
 * Extracted tests for method: createCompressorInputStream(final String name, final InputStream in, final boolean actualDecompressConcatenated)
 * Original class: CompressorStreamFactory
 * Source: ASTER GPT-4
 */
package org.apache.commons.compress.compressors;

import java.util.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo24_createCompressorInputStream_CompressorStreamFactory_Test {

    @Test
    public void testCreateCompressorInputStreamWithNullInputStream_PYXN15_EbMS0() {
    CompressorStreamFactory factory = new CompressorStreamFactory();
    assertThrows(IllegalArgumentException.class, () -> factory.createCompressorInputStream("GZIP", null, false));
    }
}