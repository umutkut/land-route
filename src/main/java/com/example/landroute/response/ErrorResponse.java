package com.example.landroute.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class ErrorResponse extends Response {
    String errorMessage;

    private ErrorResponse(String errorMessage, HttpStatus status) {
        super(status);
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse of(String errorMessage, HttpStatus status) {
        return new ErrorResponse(errorMessage, status);
    }
}
