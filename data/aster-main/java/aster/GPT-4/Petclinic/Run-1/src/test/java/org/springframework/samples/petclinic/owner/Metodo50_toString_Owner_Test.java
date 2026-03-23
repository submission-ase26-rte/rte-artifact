/**
 * Extracted tests for method: toString()
 * Original class: Owner
 * Source: ASTER GPT-4
 */
package org.springframework.samples.petclinic.owner;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo50_toString_Owner_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_qVqy0_pBpy0() {
    Owner owner = new Owner();
    owner.setId(1);
    owner.setLastName("Smith");
    owner.setFirstName("John");
    owner.setAddress("1234 Elm Street");
    owner.setCity("Springfield");
    owner.setTelephone("1234567890");
    String expected = "Owner[id=1,lastName=Smith,firstName=John,address=1234 Elm Street,city=Springfield,telephone=1234567890]";
    assertEquals(expected, owner.toString());
    }
}