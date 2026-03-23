/**
 * Filtered unit tests for method: toString()
 * Original class: Owner
 * Tests that actually call the target method
 */
package org.springframework.samples.petclinic.owner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo50_unit_toString_Owner_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testToString_PEIr0_pRLA0() {
    Owner owner = new Owner();
    owner.setId(1);
    owner.setLastName("lastName");
    owner.setFirstName("firstName");
    owner.setAddress("address");
    owner.setCity("city");
    owner.setTelephone("telephone");
    String result = owner.toString();
    assertEquals("id=1&lastName=lastName&firstName=firstName&address=address&city=city&telephone=telephone",
    result);
    }
}