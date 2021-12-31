package com.example.landroute.infrastructure.cache;

import com.example.landroute.application.RouteCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PathCacheTest {
    @Autowired
    PathCache pathCache;

    @Autowired
    RouteCalculatorService routeCalculatorService;

    @Test
    void patchCachingTest() {
        routeCalculatorService.calculateRoute("TUR", "CZE");
        Assertions.assertTrue(pathCache.contains("TUR", "CZE"));
    }

    @Test
    void reversePatchCachingTest() {
        routeCalculatorService.calculateRoute("FRA", "CZE");
        Assertions.assertTrue(pathCache.contains("CZE", "FRA"));
    }


}
