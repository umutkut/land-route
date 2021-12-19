package com.example.landroute.exception;

import lombok.Getter;

@Getter
public class PathNotFoundException extends RuntimeException {

    private final String originCountryCode;
    private final String destinationCountryCode;

    public PathNotFoundException(String message, String originCountryCode, String destinationCountryCode) {
        super(message);

        this.originCountryCode = originCountryCode;
        this.destinationCountryCode = destinationCountryCode;
    }


}
