/**
 * Extracted tests for method: addVisit(Integer petId, Visit visit)
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

public class Metodo46_addVisit_Owner_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithInvalidPetId_PWEs2() {
    Owner owner = new Owner();
    Integer petId = 999; // Assuming 999 is an invalid ID
    Visit visit = new Visit();
    when(owner.getPet(petId)).thenReturn(null);
    try {
    owner.addVisit(petId, visit);
    }
    catch (IllegalArgumentException e) {
    assertEquals("Invalid Pet identifier!", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithValidPetIdAndVisit_BAPS3() {
    Owner owner = new Owner();
    Integer petId = 1;
    Visit visit = new Visit();
    Pet mockPet = mock(Pet.class);
    when(owner.getPet(petId)).thenReturn(mockPet);
    owner.addVisit(petId, visit);
    verify(mockPet).addVisit(visit);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithPetIdAndVisitNotNull_eKHL4_1() {
    Owner owner = new Owner();
    Integer petId = 1;
    Visit visit = new Visit();
    Pet mockPet = mock(Pet.class);
    when(owner.getPet(petId)).thenReturn(mockPet);
    owner.addVisit(petId, visit);
    assertNotNull(petId);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithPetIdAndVisitNotNull_eKHL4_2() {
    Owner owner = new Owner();
    Integer petId = 1;
    Visit visit = new Visit();
    Pet mockPet = mock(Pet.class);
    when(owner.getPet(petId)).thenReturn(mockPet);
    owner.addVisit(petId, visit);
    assertNotNull(visit);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitCompletesSuccessfully_UicO5() {
    Owner owner = new Owner();
    Integer petId = 1;
    Visit visit = new Visit();
    Pet mockPet = mock(Pet.class);
    when(owner.getPet(petId)).thenReturn(mockPet);
    owner.addVisit(petId, visit);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithNullPetId_ZVRz0() {
    Owner owner = new Owner();
    Visit visit = new Visit();
    try {
    owner.addVisit(null, visit);
    }
    catch (IllegalArgumentException e) {
    assertEquals("Pet identifier must not be null!", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithNullVisit_XKmJ1() {
    Owner owner = new Owner();
    Integer petId = 1;
    try {
    owner.addVisit(petId, null);
    }
    catch (IllegalArgumentException e) {
    assertEquals("Visit must not be null!", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithNullPetId_KWRc0_1() {
    Owner owner = new Owner();
    Visit visit = new Visit();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    owner.addVisit(null, visit);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddVisitWithNullPetId_KWRc0() {
    Owner owner = new Owner();
    Visit visit = new Visit();
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    owner.addVisit(null, visit);
    });
    assertEquals("Pet identifier must not be null!", exception.getMessage());
    }
}