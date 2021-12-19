package com.example.landroute.application;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.exception.PathNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RouteCalculatorServiceTest {
    @Autowired
    RouteCalculatorService routeCalculatorService;

    @Test
    void testCountriesSameRegion() {
        var route = routeCalculatorService.calculateRoute("TUR", "CHN");
        Assertions.assertIterableEquals(List.of("TUR",
                "IRN",
                "AFG",
                "CHN"), route);
    }

    @Test
    void testCountriesConnectedRegion() {
        testEuropeAndAsia();
        testEuropeAndAfrica();
    }

    @Test
    void testCountriesNotConnectedRegion() {
        testAmericansAndAsia();
        testEuropeAndAmericans();
        testOceaniaAndEurope();
    }

    @Test
    void testCountriesNoBorder() {
        try {
            routeCalculatorService.calculateRoute("JPN", "CHN");
            Assertions.fail();
        } catch (PathNotFoundException exception) {
            Assertions.assertEquals(ErrorMessage.PATH_NOT_FOUND, exception.getMessage());
        }
    }
    private void testEuropeAndAsia() {
        var route = routeCalculatorService.calculateRoute("FRA", "CHN");
        Assertions.assertIterableEquals(List.of("FRA",
                "DEU",
                "POL",
                "RUS",
                "CHN"), route);

    }

    private void testEuropeAndAfrica() {
        var route = routeCalculatorService.calculateRoute("POL", "CAF");
        Assertions.assertIterableEquals(List.of("POL",
                "DEU",
                "FRA",
                "ESP",
                "MAR",
                "DZA",
                "LBY",
                "TCD",
                "CAF"), route);
    }

    private void testAmericansAndAsia() {
        try {
            routeCalculatorService.calculateRoute("USA", "TUR");
            Assertions.fail();
        } catch (PathNotFoundException exception) {
            Assertions.assertEquals(ErrorMessage.PATH_NOT_FOUND, exception.getMessage());
        }
    }

    private void testOceaniaAndEurope() {
        try {
            routeCalculatorService.calculateRoute("AUS", "CZE");
            Assertions.fail();
        } catch (PathNotFoundException exception) {
            Assertions.assertEquals(ErrorMessage.PATH_NOT_FOUND, exception.getMessage());
        }
    }

    private void testEuropeAndAmericans() {
        try {
            routeCalculatorService.calculateRoute("CZE", "CAN");
            Assertions.fail();
        } catch (PathNotFoundException exception) {
            Assertions.assertEquals(ErrorMessage.PATH_NOT_FOUND, exception.getMessage());
        }
    }
}