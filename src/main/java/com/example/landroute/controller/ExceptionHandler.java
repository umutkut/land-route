package com.example.landroute.controller;

import com.example.landroute.exception.InvalidCountryCodeException;
import com.example.landroute.exception.PathNotFoundException;
import com.example.landroute.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({InvalidCountryCodeException.class})
    public ResponseEntity<ErrorResponse> invalidCountryCode(InvalidCountryCodeException ex) {
        var errorResponse = ErrorResponse.of(
                ex.getMessage()
                        + " Country Code: "
                        + ex.getCountryCode(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponse,
                errorResponse.getStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({PathNotFoundException.class})
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

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
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
