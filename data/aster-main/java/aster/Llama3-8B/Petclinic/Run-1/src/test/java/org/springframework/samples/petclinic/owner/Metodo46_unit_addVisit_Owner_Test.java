/**
 * Filtered unit tests for method: addVisit(Integer petId, Visit visit)
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

public class Metodo46_unit_addVisit_Owner_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisit_PetIdNotNull_VisitNotNull_zDVb2_ImdZ1() {
    Owner owner = new Owner();
    Visit visit = new Visit();
    owner.addVisit(1, visit);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisit_dkvM0() {
    Owner owner = new Owner();
    Visit visit = new Visit();
    owner.addVisit(1, visit);
    }
}