package com.example.landroute.controller;

import com.example.landroute.application.RouteCalculatorService;
import com.example.landroute.response.ErrorResponse;
import com.example.landroute.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DependsOn(value = "countryLoaderService")
@RequiredArgsConstructor
@Slf4j
public class LandRouteController {

    private final RouteCalculatorService routeCalculatorService;

    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<SuccessResponse> routing(@PathVariable String origin, @PathVariable String destination) {
        log.info("Route requested from {} to {}", origin, destination);
        return new ResponseEntity<>(SuccessResponse.of(routeCalculatorService.calculateRoute(origin.toUpperCase(), destination.toUpperCase())), HttpStatus.OK);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> notFound(IllegalArgumentException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
