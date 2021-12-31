package com.example.landroute.controller;

import com.example.landroute.application.RouteCalculatorService;
import com.example.landroute.domain.Country;
import com.example.landroute.exception.InvalidCountryCodeException;
import com.example.landroute.exception.PathNotFoundException;
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

import java.util.Locale;

@RestController
@DependsOn(value = "countryLoaderService")
@RequiredArgsConstructor
@Slf4j
public class LandRouteController {

    private final RouteCalculatorService routeCalculatorService;

    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<SuccessResponse> routing(@PathVariable String origin, @PathVariable String destination) {
        log.info("Route requested from {} to {}", origin, destination);

        return new ResponseEntity<>(
                SuccessResponse.of(
                        routeCalculatorService.calculateRoute(
                                origin.toUpperCase(Locale.ENGLISH),
                                destination.toUpperCase(Locale.ENGLISH))),
                HttpStatus.OK);
    }

    @ExceptionHandler({InvalidCountryCodeException.class})
    public ResponseEntity<ErrorResponse> invalidCountryCode(InvalidCountryCodeException ex) {
        var errorResponse = ErrorResponse.of(
                ex.getMessage()
                        + " Country Code: "
                        + ex.getCountryCode(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponse,
                errorResponse.getStatus());
    }

    @ExceptionHandler({PathNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFound(PathNotFoundException ex) {
        var errorResponse = ErrorResponse.of(
                ex.getMessage()
                        + " Origin Country: " + ex.getOriginCountryCode()
                        + " Destination Country: " + ex.getDestinationCountryCode(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(
                errorResponse,
                errorResponse.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> unexpectedException(Exception ex) {
        log.error("Exception occurred: ", ex);

        var errorResponse = ErrorResponse.of(
                ex.getMessage()
                        + " Unexpected exception.",
                HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(
                errorResponse,
                errorResponse.getStatus());
    }
}
