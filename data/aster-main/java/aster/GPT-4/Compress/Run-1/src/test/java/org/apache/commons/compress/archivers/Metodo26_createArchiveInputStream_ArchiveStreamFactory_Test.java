/**
 * Extracted tests for method: createArchiveInputStream(final String archiverName, final InputStream in, final String actualEncoding)
 * Original class: ArchiveStreamFactory
 * Source: ASTER GPT-4
 */
package org.apache.commons.compress.archivers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo26_createArchiveInputStream_ArchiveStreamFactory_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamWithNullArchiverName_vEcs0() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    assertThrows(IllegalArgumentException.class, () -> factory.createArchiveInputStream(null, new ByteArrayInputStream(new byte[0]), "UTF-8"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamWithNullInputStream_bgCb1() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    assertThrows(IllegalArgumentException.class, () -> factory.createArchiveInputStream("ZIP", null, "UTF-8"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForAR_mSxt2() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ArArchiveInputStream result = (ArArchiveInputStream) factory.createArchiveInputStream("AR", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForARJWithEncoding_yzSz3() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ArjArchiveInputStream result = (ArjArchiveInputStream) factory.createArchiveInputStream("ARJ", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForARJWithoutEncoding_tVbl4() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ArjArchiveInputStream result = (ArjArchiveInputStream) factory.createArchiveInputStream("ARJ", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForZIPWithEncoding_QzhD5() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ZipArchiveInputStream result = (ZipArchiveInputStream) factory.createArchiveInputStream("ZIP", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForZIPWithoutEncoding_CQlI6() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    ZipArchiveInputStream result = (ZipArchiveInputStream) factory.createArchiveInputStream("ZIP", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForTARWithEncoding_UaTA7() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    TarArchiveInputStream result = (TarArchiveInputStream) factory.createArchiveInputStream("TAR", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForTARWithoutEncoding_IuVd8() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    TarArchiveInputStream result = (TarArchiveInputStream) factory.createArchiveInputStream("TAR", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForJARWithEncoding_JQmp9() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    JarArchiveInputStream result = (JarArchiveInputStream) factory.createArchiveInputStream("JAR", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForJARWithoutEncoding_EQkB10() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    JarArchiveInputStream result = (JarArchiveInputStream) factory.createArchiveInputStream("JAR", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForCPIOWithEncoding_RJae11() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    CpioArchiveInputStream result = (CpioArchiveInputStream) factory.createArchiveInputStream("CPIO", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForCPIOWithoutEncoding_VEPe12() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    CpioArchiveInputStream result = (CpioArchiveInputStream) factory.createArchiveInputStream("CPIO", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForDUMPWithEncoding_MHvw13() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    DumpArchiveInputStream result = (DumpArchiveInputStream) factory.createArchiveInputStream("DUMP", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForDUMPWithoutEncoding_fIlW14() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    DumpArchiveInputStream result = (DumpArchiveInputStream) factory.createArchiveInputStream("DUMP", in, null);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForUnsupportedFormat_QJHF15() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    assertThrows(StreamingNotSupportedException.class, () -> factory.createArchiveInputStream("SEVEN_Z", in, "UTF-8"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForUnknownFormat_bOAO16() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    assertThrows(ArchiveException.class, () -> factory.createArchiveInputStream("UNKNOWN", in, "UTF-8"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForAPKWithEncoding_boWU17() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    JarArchiveInputStream result = (JarArchiveInputStream) factory.createArchiveInputStream("APK", in, "UTF-8");
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCreateArchiveInputStreamForAPKWithoutEncoding_uCRv18() throws Exception {
    ArchiveStreamFactory factory = new ArchiveStreamFactory();
    InputStream in = new ByteArrayInputStream(new byte[0]);
    JarArchiveInputStream result = (JarArchiveInputStream) factory.createArchiveInputStream("APK", in, null);
    assertNotNull(result);
    }
}