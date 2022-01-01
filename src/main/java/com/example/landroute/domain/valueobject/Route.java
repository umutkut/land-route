package com.example.landroute.domain.valueobject;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.exception.PathNotFoundException;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@Getter
public class Route {
    CountryCode origin;
    CountryCode destination;
    List<String> path;

    public Route(CountryCode origin, CountryCode destination, List<String> path) {
        if (path.isEmpty())
            throw new PathNotFoundException(ErrorMessage.PATH_NOT_FOUND, origin.getValue(), destination.getValue());

        this.origin = origin;
        this.destination = destination;
        this.path = path;
    }

    public Route getReversed() {
        var reversedPath = new ArrayList<>(this.getPath());
        Collections.reverse(reversedPath);

        return new Route(destination, origin, reversedPath);
    }

}
