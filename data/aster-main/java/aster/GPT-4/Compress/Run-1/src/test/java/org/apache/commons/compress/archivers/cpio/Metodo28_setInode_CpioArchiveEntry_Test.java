/**
 * Extracted tests for method: setInode(long inode)
 * Original class: CpioArchiveEntry
 * Source: ASTER GPT-4
 */
package org.apache.commons.compress.archivers.cpio;

import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo28_setInode_CpioArchiveEntry_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSetInode_sxQp0() {
    CpioArchiveEntry entry = new CpioArchiveEntry("testEntry");
    entry.setInode(12345L);
    assertEquals(12345L, entry.getInode());
    }
}