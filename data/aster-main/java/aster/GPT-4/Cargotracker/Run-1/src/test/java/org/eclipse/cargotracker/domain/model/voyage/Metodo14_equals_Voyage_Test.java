/**
 * Extracted tests for method: equals(Object o)
 * Original class: Voyage
 * Source: ASTER GPT-4
 */
package org.eclipse.cargotracker.domain.model.voyage;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Metodo14_equals_Voyage_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_SameIdentity_fid2() throws Exception {
        VoyageNumber voyageNumber = mock(VoyageNumber.class);
        Schedule schedule = mock(Schedule.class);
        Voyage voyage = new Voyage(voyageNumber, schedule);
    Voyage anotherVoyage = new Voyage(voyageNumber, schedule);
    assertTrue(voyage.equals(anotherVoyage), "Two voyages with the same identity should be equal.");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_Reflexive() throws Exception {
        VoyageNumber voyageNumber = mock(VoyageNumber.class);
    Schedule schedule = mock(Schedule.class);
        Voyage voyage = new Voyage(voyageNumber, schedule);
    assertTrue(voyage.equals(voyage), "A voyage should be equal to itself.");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_Null() throws Exception {
        VoyageNumber voyageNumber = mock(VoyageNumber.class);
        Schedule schedule = mock(Schedule.class);
        Voyage voyage = new Voyage(voyageNumber, schedule);
    assertFalse(voyage.equals(null), "A voyage should not be equal to null.");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_DifferentClass() throws Exception {
        VoyageNumber voyageNumber = mock(VoyageNumber.class);
    Schedule schedule = mock(Schedule.class);
    Voyage voyage = new Voyage(voyageNumber, schedule);
    Object differentObject = new Object();
    assertFalse(voyage.equals(differentObject), "A voyage should not be equal to an object of a different class.");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_SameIdentity() throws Exception {
    VoyageNumber voyageNumber = mock(VoyageNumber.class);
    Schedule schedule = mock(Schedule.class);
    Voyage voyage = new Voyage(voyageNumber, schedule);
    Voyage anotherVoyage = new Voyage(voyageNumber, schedule);
    assertFalse(voyage.equals(anotherVoyage), "Two voyages with the same identity should be equal.");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_DifferentIdentity() throws Exception {
    VoyageNumber voyageNumber = mock(VoyageNumber.class);
    Schedule schedule = mock(Schedule.class);
    Voyage voyage = new Voyage(voyageNumber, schedule);
    VoyageNumber differentVoyageNumber = mock(VoyageNumber.class);
    Schedule differentSchedule = mock(Schedule.class);
    Voyage differentVoyage = new Voyage(differentVoyageNumber, differentSchedule);
    assertFalse(voyage.equals(differentVoyage), "Two voyages with different identities should not be equal.");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_Reflexive_RAvs0() {
    Voyage voyage = new Voyage(new VoyageNumber("123"), new Schedule());
    assertTrue(voyage.equals(voyage));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_Symmetric_EUNI1() {
    Voyage voyage1 = new Voyage(new VoyageNumber("123"), new Schedule());
    Voyage voyage2 = new Voyage(new VoyageNumber("123"), new Schedule());
    assertTrue(voyage1.equals(voyage2) && voyage2.equals(voyage1));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_Null_nuHp2() {
    Voyage voyage = new Voyage(new VoyageNumber("123"), new Schedule());
    assertFalse(voyage.equals(null));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEquals_DifferentClass_ZUKg3() {
    Voyage voyage = new Voyage(new VoyageNumber("123"), new Schedule());
    Object other = new Object();
    assertFalse(voyage.equals(other));
    }
}