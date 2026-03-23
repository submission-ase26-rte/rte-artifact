/**
 * Filtered unit tests for method: createArchiveInputStream(final String archiverName, final InputStream in, final String actualEncoding)
 * Original class: ArchiveStreamFactory
 * Tests that actually call the target method
 */
package org.apache.commons.compress.archivers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.utils.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo26_unit_createArchiveInputStream_ArchiveStreamFactory_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamSevenZ_dHZR8() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    try {
    factory.createArchiveInputStream("SEVEN_Z", in, null);
    Assertions.fail();
    } catch (StreamingNotSupportedException e) {
    Assertions.assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamUnknownArchiverName_FIhm11() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    try {
    factory.createArchiveInputStream("UNKNOWN", in, null);
    Assertions.fail();
    } catch (ArchiveException e) {
    Assertions.assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamNullEncoding_wpjv12_osBl0() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ArchiveInputStream result = factory.createArchiveInputStream("ARJ", in, null);
    org.junit.Assert.assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamNullArchiverName_ipon9() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    try {
    factory.createArchiveInputStream(null, in, null);
    Assertions.fail();
    } catch (IllegalArgumentException e) {
    Assertions.assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamNullInputStream_TJkQ10() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    try {
    factory.createArchiveInputStream("AR", null, null);
    Assertions.fail();
    } catch (IllegalArgumentException e) {
    Assertions.assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamZip_zTMB2_Btcz0() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ArchiveInputStream result = (ArchiveInputStream) factory.createArchiveInputStream("ZIP", in, null);
    Assertions.assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamCpio_VdaE6_DXHZ0() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ArchiveInputStream result = factory.createArchiveInputStream("CPio", in, null);
    Assertions.assertNotNull(result);
    }
}