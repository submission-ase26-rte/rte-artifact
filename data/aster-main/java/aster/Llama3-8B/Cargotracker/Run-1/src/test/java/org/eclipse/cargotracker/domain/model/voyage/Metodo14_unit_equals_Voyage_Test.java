/**
 * Filtered unit tests for method: equals(Object o)
 * Original class: Voyage
 * Tests that actually call the target method
 */
package org.eclipse.cargotracker.domain.model.voyage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.cargotracker.domain.model.voyage.VoyageNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.mock;

public class Metodo14_unit_equals_Voyage_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsSameObject_cgZA0() {
    VoyageNumber voyageNumber = new VoyageNumber("123");
    boolean result = voyageNumber.equals(voyageNumber);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsNull_QTig1() {
    VoyageNumber voyageNumber = new VoyageNumber("123");
    boolean result = voyageNumber.equals(null);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsDifferentClass_insf2() {
    VoyageNumber voyageNumber = new VoyageNumber("123");
    Object object = new Object();
    boolean result = voyageNumber.equals(object);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsDifferentValue_hifv3() {
    VoyageNumber voyageNumber1 = new VoyageNumber("123");
    VoyageNumber voyageNumber2 = new VoyageNumber("456");
    boolean result = voyageNumber1.equals(voyageNumber2);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsSameObject_QDLd0() {
    Voyage voyage = new Voyage(new VoyageNumber("123"), new Schedule());
    boolean result = voyage.equals(voyage);
    assert result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsNull_wcts1() {
    Voyage voyage = new Voyage(new VoyageNumber("123"), new Schedule());
    boolean result = voyage.equals(null);
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsDifferentClass_TsHT2() {
    Voyage voyage = new Voyage(new VoyageNumber("123"), new Schedule());
    boolean result = voyage.equals(new Object());
    assert !result;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testEqualsDifferentVoyage_uQmv3() {
    Voyage voyage1 = new Voyage(new VoyageNumber("123"), new Schedule());
    Voyage voyage2 = new Voyage(new VoyageNumber("456"), new Schedule());
    boolean result = voyage1.equals(voyage2);
    assert !result;
    }
}