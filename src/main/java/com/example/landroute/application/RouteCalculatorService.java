package com.example.landroute.application;

import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.cache.IPathCache;
import com.example.landroute.utils.PreconditionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteCalculatorService {
    private final ICountryCache countryCache;
    private final IPathCache pathCache;


    public List<String> calculateRoute(String origin, String destination) {
        var from = countryCache.get(origin);
        var to = countryCache.get(destination);

        var route = from.findRouteTo(to, countryCache, pathCache);

        PreconditionUtils.checkRouteNotEmpty(route);

        return route;
    }

}
