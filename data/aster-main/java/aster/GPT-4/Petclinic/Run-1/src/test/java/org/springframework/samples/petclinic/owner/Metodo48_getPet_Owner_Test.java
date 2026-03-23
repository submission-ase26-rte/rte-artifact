/**
 * Extracted tests for method: getPet(String name, boolean ignoreNew)
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

public class Metodo48_getPet_Owner_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithNameMatchingAndIgnoringNew_bgZD0() {
    Owner owner = new Owner();
    Pet mockPet = mock(Pet.class);
    when(mockPet.getName()).thenReturn("Buddy");
    when(mockPet.isNew()).thenReturn(true);
    Owner spyOwner = spy(owner);
    doReturn(Arrays.asList(mockPet)).when(spyOwner).getPets();
    Pet result = spyOwner.getPet("buddy", true);
    assertNull(result, "Pet should not be returned because it is new and ignoreNew is true");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithNameMatchingAndNotIgnoringNew_oIav1_1() {
    Owner owner = new Owner();
    Pet mockPet = mock(Pet.class);
    when(mockPet.getName()).thenReturn("Buddy");
    when(mockPet.isNew()).thenReturn(false);
    Owner spyOwner = spy(owner);
    doReturn(Arrays.asList(mockPet)).when(spyOwner).getPets();
    Pet result = spyOwner.getPet("buddy", false);
    assertNotNull(result, "Pet should be returned because ignoreNew is false");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithNameMatchingAndNotIgnoringNew_oIav1_2() {
    Owner owner = new Owner();
    Pet mockPet = mock(Pet.class);
    when(mockPet.getName()).thenReturn("Buddy");
    when(mockPet.isNew()).thenReturn(false);
    Owner spyOwner = spy(owner);
    doReturn(Arrays.asList(mockPet)).when(spyOwner).getPets();
    Pet result = spyOwner.getPet("buddy", false);
    assertEquals(mockPet, result, "Returned pet should match the expected mock pet");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithEmptyPetList_YjvV0() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    when(spyOwner.getPets()).thenReturn(Collections.emptyList());
    Pet result = spyOwner.getPet(1);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithNewPetOnly_ZQNs1() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet newPet = mock(Pet.class);
    when(newPet.isNew()).thenReturn(true);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(newPet));
    Pet result = spyOwner.getPet(1);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithNonMatchingId_izbz2() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet existingPet = mock(Pet.class);
    when(existingPet.isNew()).thenReturn(false);
    when(existingPet.getId()).thenReturn(2);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(existingPet));
    Pet result = spyOwner.getPet(1);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithMatchingId_tMny3_1() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet matchingPet = mock(Pet.class);
    when(matchingPet.isNew()).thenReturn(false);
    when(matchingPet.getId()).thenReturn(1);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(matchingPet));
    Pet result = spyOwner.getPet(1);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithMatchingId_tMny3_2() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet matchingPet = mock(Pet.class);
    when(matchingPet.isNew()).thenReturn(false);
    when(matchingPet.getId()).thenReturn(1);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(matchingPet));
    Pet result = spyOwner.getPet(1);
    assertEquals(matchingPet, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithMultiplePetsNoneMatching_LCSa4() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet pet1 = mock(Pet.class);
    Pet pet2 = mock(Pet.class);
    when(pet1.isNew()).thenReturn(false);
    when(pet1.getId()).thenReturn(2);
    when(pet2.isNew()).thenReturn(false);
    when(pet2.getId()).thenReturn(3);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(pet1, pet2));
    Pet result = spyOwner.getPet(1);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithMultiplePetsOneMatching_UxoC5_1() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet pet1 = mock(Pet.class);
    Pet pet2 = mock(Pet.class);
    when(pet1.isNew()).thenReturn(false);
    when(pet1.getId()).thenReturn(1);
    when(pet2.isNew()).thenReturn(false);
    when(pet2.getId()).thenReturn(3);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(pet1, pet2));
    Pet result = spyOwner.getPet(1);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPet_WithMultiplePetsOneMatching_UxoC5_2() {
    Owner owner = new Owner();
    Owner spyOwner = spy(owner);
    Pet pet1 = mock(Pet.class);
    Pet pet2 = mock(Pet.class);
    when(pet1.isNew()).thenReturn(false);
    when(pet1.getId()).thenReturn(1);
    when(pet2.isNew()).thenReturn(false);
    when(pet2.getId()).thenReturn(3);
    when(spyOwner.getPets()).thenReturn(Arrays.asList(pet1, pet2));
    Pet result = spyOwner.getPet(1);
    assertEquals(pet1, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testGetPetReturnsNullForUnknownPet_Hhdp0() {
    Owner owner = new Owner();
    Pet result = owner.getPet("Unknown");
    assertNull(result);
    }

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