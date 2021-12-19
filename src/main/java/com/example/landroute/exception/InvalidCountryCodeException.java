package com.example.landroute.exception;

import lombok.Getter;

@Getter
public class InvalidCountryCodeException extends RuntimeException {
    private final String countryCode;

    public InvalidCountryCodeException(String message, String countryCode) {
        super(message);
        this.countryCode = countryCode;
    }

}
