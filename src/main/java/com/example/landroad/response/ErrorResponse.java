package com.example.landroad.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class ErrorResponse extends Response {
    String errorMessage;

    private ErrorResponse(String errorMessage) {
        super(HttpStatus.BAD_REQUEST);
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse of(String errorMessage) {
        return new ErrorResponse(errorMessage);
    }
}
