package com.example.landroad.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public abstract class Response {
    private final HttpStatus status;

    private final Integer statusCode;

    Response(HttpStatus status) {
        this.status = status;
        this.statusCode = status.value();
    }
}
