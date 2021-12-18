package com.example.landroad.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RouteCalculatorServiceTest {
    @Autowired
    RouteCalculatorService routeCalculatorService;

    @Test
    void sameLandTest(){
        System.out.println(routeCalculatorService.calculateRoute("TUR", "CZE"));
        System.out.println(routeCalculatorService.calculateRoute("TUR", "GRC"));
        System.out.println(routeCalculatorService.calculateRoute("POL", "RUS"));
        System.out.println(routeCalculatorService.calculateRoute("FRA", "HUN"));
        System.out.println(routeCalculatorService.calculateRoute("TUR", "CHN"));
    }
}