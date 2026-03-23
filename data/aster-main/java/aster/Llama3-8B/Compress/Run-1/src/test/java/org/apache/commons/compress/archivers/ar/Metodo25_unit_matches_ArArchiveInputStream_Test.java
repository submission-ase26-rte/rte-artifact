/**
 * Filtered unit tests for method: matches(final byte[] signature, final int length)
 * Original class: ArArchiveInputStream
 * Tests that actually call the target method
 */
package org.apache.commons.compress.archivers.ar;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo25_unit_matches_ArArchiveInputStream_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureLength9_LuVe2() {
    byte[] signature = new byte[] {0x21, 0x3c, 0x61, 0x72, 0x63, 0x68, 0x3e, 0x0a, 0x0b};
    boolean result = ArArchiveInputStream.matches(signature, 9);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureNull_Ynmv5() {
    boolean result = ArArchiveInputStream.matches(null, 8);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureLength8_CQAv0() {
    byte[] signature = new byte[] {0x21, 0x3c, 0x61, 0x72, 0x63, 0x68, 0x3e, 0x0a};
    boolean result = ArArchiveInputStream.matches(signature, 8);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureLength7_JmUp1() {
    byte[] signature = new byte[] {0x21, 0x3c, 0x61, 0x72, 0x63, 0x68, 0x3e};
    boolean result = ArArchiveInputStream.matches(signature, 7);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureNotEqual_UUHk3() {
    byte[] signature = new byte[] {0x20, 0x3c, 0x61, 0x72, 0x63, 0x68, 0x3e, 0x0a};
    boolean result = ArArchiveInputStream.matches(signature, 8);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureLength0_JwRJ6() {
    boolean result = ArArchiveInputStream.matches(new byte[0], 0);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesSignatureLength1_OdAG7() {
    boolean result = ArArchiveInputStream.matches(new byte[] {0x20}, 1);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesPosix_MgEA0() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '0', (byte) '0', (byte) '0', (byte) '1'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesGnuSpace_ieSB1() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '0', (byte) '0', (byte) '0', (byte) '2'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesGnuZero_pYEc2() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '0', (byte) '0', (byte) '0', (byte) '0'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesAnt_YdZl3() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '1', (byte) '0', (byte) '0', (byte) '1'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesTooShort_AYSR4() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesMagicMismatch_Pbic5() {
    byte[] signature = new byte[] {(byte) 'x', (byte) 'y', (byte) 'z', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '0', (byte) '0', (byte) '0', (byte) '1'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesVersionMismatch_oKPu6() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '0', (byte) '0', (byte) '0', (byte) '1', (byte) 'x'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMatchesTooShortVersion_RXmA7() {
    byte[] signature = new byte[] {(byte) 'u', (byte) 's', (byte) 't', (byte) 'a', (byte) 'r', (byte) ' ', (byte) '0', (byte) '0'};
    boolean result = TarArchiveInputStream.matches(signature, signature.length);
    assert !result;
    }
}