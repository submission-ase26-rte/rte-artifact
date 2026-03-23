/**
 * Filtered unit tests for method: fetchRoutesForSpecification(RouteSpecification routeSpecification)
 * Original class: ExternalRoutingService
 * Tests that actually call the target method
 */
package org.eclipse.cargotracker.infrastructure.routing;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.cargotracker.domain.model.cargo.Itinerary;
import org.eclipse.cargotracker.domain.model.cargo.RouteSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo10_unit_fetchRoutesForSpecification_ExternalRoutingService_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFetchRoutesForSpecificationNullRouteSpecification_zFaI0() {
    ExternalRoutingService externalRoutingService = new ExternalRoutingService();
    RouteSpecification routeSpecification = null;
    List<Itinerary> result = externalRoutingService.fetchRoutesForSpecification(routeSpecification);
    Assertions.assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFetchRoutesForSpecificationEmptyRouteSpecification_JJFF1() {
    ExternalRoutingService externalRoutingService = new ExternalRoutingService();
    RouteSpecification routeSpecification = new RouteSpecification();
    List<Itinerary> result = externalRoutingService.fetchRoutesForSpecification(routeSpecification);
    Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFetchRoutesForSpecificationOriginAndDestinationNull_CvEz4_MxDI0() {
    ExternalRoutingService externalRoutingService = new ExternalRoutingService();
    RouteSpecification routeSpecification = new RouteSpecification(null, null, null);
    List<Itinerary> result = externalRoutingService.fetchRoutesForSpecification(routeSpecification);
    Assertions.assertTrue(result.isEmpty());
    }
}