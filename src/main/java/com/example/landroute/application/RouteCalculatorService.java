package com.example.landroute.application;

import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.cache.IRouteCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RouteCalculatorService {
    private final ICountryCache countryCache;
    private final IRouteCache routeCache;


    public List<String> calculateRoute(String origin, String destination) {
        if (routeCache.contains(origin, destination)) {
            log.info("Returning precalculated route from cache.");
            return routeCache.getCachedPath(origin, destination).getPath();
        }

        var from = countryCache.get(origin);
        var to = countryCache.get(destination);

        var route = from.findRouteTo(to, countryCache.getAll());
        routeCache.cache(route);

        return route.getPath();
    }

}
