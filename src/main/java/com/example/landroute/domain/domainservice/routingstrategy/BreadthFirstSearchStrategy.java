package com.example.landroute.domain.domainservice.routingstrategy;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.domain.Country;
import com.example.landroute.domain.valueobject.Route;
import com.example.landroute.exception.PathNotFoundException;
import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.cache.IRouteCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class BreadthFirstSearchStrategy implements IRoutingStrategy {

    private final ICountryCache countryCache;
    private final IRouteCache routeCache;

    //FIXME: Some countries have independent lands. This causes false output. The info in the json file is not sufficient to fix that issue.

    public final Route findRoute(Country from, Country to) {
        log.info("Calculating route with {}", this.getClass().getSimpleName());

        Map<Country, Country> previousCountry = new HashMap<>();
        Set<Country> visited = new HashSet<>();

        var current = to;

        Queue<Country> countryQueue = new ArrayDeque<>();

        countryQueue.add(current);
        visited.add(current);

        while (!countryQueue.isEmpty()) {
            current = countryQueue.poll();

            if (current.equals(from)) break;

            for (var neighbour : current.getBorders()) {
                var neighbourCountry = countryCache.get(neighbour);

                if (visited.contains(neighbourCountry)) continue;

                countryQueue.add(neighbourCountry);
                visited.add(neighbourCountry);
                previousCountry.put(neighbourCountry, current);

                if (neighbourCountry.equals(from)) {
                    current = neighbourCountry;
                    return calculatePath(previousCountry, from, to, current);
                }
            }
        }

        return calculatePath(previousCountry, from, to, current);
    }

    private Route calculatePath(Map<Country, Country> previousCountry, Country from, Country to, Country current) {
        if (!current.equals(from))
            throw new PathNotFoundException(ErrorMessage.PATH_NOT_FOUND, from.getCode().getValue(), to.getCode().getValue());

        List<String> path = new ArrayList<>();
        Country curr = from;
        while (curr != null) {
            path.add(curr.getCode().getValue());
            curr = previousCountry.get(curr);
        }

        var route = new Route(from.getCode(), to.getCode(), path);

        routeCache.cache(route);

        return route;
    }
}
