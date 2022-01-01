package com.example.landroute.application;

import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.cache.IRouteCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteCalculatorService {
    private final ICountryCache countryCache;
    private final IRouteCache pathCache;


    public List<String> calculateRoute(String origin, String destination) {

        var from = countryCache.get(origin);
        var to = countryCache.get(destination);

        var route = from.findRouteTo(to, countryCache, pathCache);

        return route.getPath();
    }

}
