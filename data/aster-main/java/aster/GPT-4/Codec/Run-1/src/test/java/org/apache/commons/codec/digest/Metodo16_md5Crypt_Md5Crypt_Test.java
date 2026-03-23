/**
 * Extracted tests for method: md5Crypt(final byte[] keyBytes, final String salt, final String prefix, final Random random)
 * Original class: Md5Crypt
 * Source: ASTER GPT-4
 */
package org.apache.commons.codec.digest;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo16_md5Crypt_Md5Crypt_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithEmptySalt_DMGk3() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithPrefixOnly_Bfrp8() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$1$";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithNoPrefixInSalt_JWus10() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "usesalt";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithValidInput_hfOc0_fid2() {
    String expectedOutput = "someExpectedHashValue"; // Replace with the actual expected MD5 hash
    byte[] keyBytes = "password".getBytes();
    String salt = "12345678";
    String result = Md5Crypt.md5Crypt(keyBytes, salt);
    assertEquals(expectedOutput, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithValidInputs_McFX0_fid2() {
    String expectedOutput = "someExpectedHashValue"; // Replace with actual expected hash value
    byte[] keyBytes = "password".getBytes();
    String salt = "12345678";
    String prefix = "$1$";
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix);
    assertEquals(expectedOutput, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithValidSalt_ISLs0() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$1$salt1234";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithNullSalt_aJBW1() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithLongSalt_EyJL4() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$1$123456789";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithSpecialCharacterSalt_RzQh5() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$1$./@*#&";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithNumericSalt_FUBT6() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$1$12345678";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithAlphaNumericSalt_Gaom7() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$1$abc12345";
    String prefix = "$1$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithDifferentPrefix_FNbJ9() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "$2$usesalt";
    String prefix = "$2$";
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithRandomBytes_LWSa0_1() {
    byte[] keyBytes = new byte[16];
    new Random().nextBytes(keyBytes);
    String result = Md5Crypt.md5Crypt(keyBytes);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithRandomBytes_LWSa0_2() {
    byte[] keyBytes = new byte[16];
    new Random().nextBytes(keyBytes);
    String result = Md5Crypt.md5Crypt(keyBytes);
    assertTrue(result.startsWith(Md5Crypt.MD5_PREFIX));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithRandomSalt_UZeR0_1() {
    byte[] keyBytes = "testKey".getBytes();
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, random);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithRandomSalt_UZeR0_2() {
    byte[] keyBytes = "testKey".getBytes();
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, random);
    assertTrue(result.startsWith(Md5Crypt.MD5_PREFIX));
    }

    @Test
    public void testMd5CryptWithInvalidSalt_JMaJ2_cQKx0() {
    byte[] keyBytes = "password".getBytes(StandardCharsets.UTF_8);
    String salt = "invalidsalt";
    String prefix = "$1$";
    Random random = new Random();
    assertThrows(IllegalArgumentException.class, () -> {
    Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithRandomBytes_LWSa0() {
    byte[] keyBytes = new byte[16];
    new Random().nextBytes(keyBytes);
    String result = Md5Crypt.md5Crypt(keyBytes);
    assertNotNull(result);
    assertTrue(result.startsWith(Md5Crypt.MD5_PREFIX));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testMd5CryptWithRandomSalt_UZeR0() {
    byte[] keyBytes = "testKey".getBytes();
    Random random = new Random();
    String result = Md5Crypt.md5Crypt(keyBytes, random);
    assertNotNull(result);
    assertTrue(result.startsWith(Md5Crypt.MD5_PREFIX));
    }
}