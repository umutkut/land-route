package com.example.landroute.constants;

public class ErrorMessage {
    public static final String PATH_NOT_FOUND = "Path not found.";
    public static final String INVALID_COUNTRY = "Country code is not valid.";

    private ErrorMessage() {
        throw new IllegalStateException("Constant class");
    }

}
