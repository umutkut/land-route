package com.example.landroad.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@ToString
@Getter
public class SuccessResponse extends Response {
    List<String> route;

    private SuccessResponse(List<String> route) {
        super(HttpStatus.OK);
        this.route = route;
    }

    public static SuccessResponse of(List<String> route) {
        return new SuccessResponse(route);
    }
}
