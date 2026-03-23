/**
 * Extracted tests for method: addPet(Pet pet)
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

public class Metodo47_addPet_Owner_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_ExistingPetIgnoreCaseSensitive_VUin0_1() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("buddy", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_ExistingPetIgnoreCaseSensitive_VUin0_2() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("buddy", false);
    assertEquals("Buddy", result.getName());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_ExistingPetCaseSensitive_vYLK1_1() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("Buddy", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_ExistingPetCaseSensitive_vYLK1_2() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("Buddy", false);
    assertEquals("Buddy", result.getName());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_NonExistingPet_njLN2() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("Max", false);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_PetNameWithDifferentCase_rXtf5_1() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("BUDDY", false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_PetNameWithDifferentCase_rXtf5_2() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("BUDDY", false);
    assertEquals("Buddy", result.getName());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPetWithValidIdReturnsPet_ndaA0_YMjQ0_1() {
    Owner owner = new Owner();
    Pet pet1 = new Pet();
    pet1.setId(1);
    owner.addPet(pet1);
    Pet result = owner.getPet(1);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPetWithValidIdReturnsPet_ndaA0_YMjQ0_2() {
    Owner owner = new Owner();
    Pet pet1 = new Pet();
    pet1.setId(1);
    owner.addPet(pet1);
    Pet result = owner.getPet(1);
    assertEquals(pet1, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPetWithInvalidIdReturnsNull_aWTx1_Nrpl0() {
    Owner owner = new Owner();
    Pet pet1 = new Pet();
    pet1.setId(1);
    owner.addPet(pet1);
    Pet result = owner.getPet(2);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_ExistingPetIgnoreCaseSensitive_VUin0() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("buddy", false);
    assertNotNull(result);
    assertEquals("Buddy", result.getName());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_ExistingPetCaseSensitive_vYLK1() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("Buddy", false);
    assertNotNull(result);
    assertEquals("Buddy", result.getName());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_PetNameWithDifferentCase_rXtf5() {
    Owner owner = new Owner();
    Pet pet = new Pet();
    pet.setName("Buddy");
    owner.addPet(pet);
    Pet result = owner.getPet("BUDDY", false);
    assertNotNull(result);
    assertEquals("Buddy", result.getName());
    }
}