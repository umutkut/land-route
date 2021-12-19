package com.example.landroute.application;

import com.example.landroute.application.strategy.IRoutingStrategy;
import com.example.landroute.infrastructure.ICountryCache;
import com.example.landroute.infrastructure.IPathCache;
import com.example.landroute.utils.PreconditionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteCalculatorService {
    private final ICountryCache countryCache;
    private final IPathCache pathCache;
    private final IRoutingStrategy routingStrategy;


    public List<String> calculateRoute(String origin, String destination) {
        if (pathCache.contains(origin, destination))
            return pathCache.getCachedPath(origin, destination);


        var from = countryCache.get(origin);
        var to = countryCache.get(destination);

        PreconditionUtils.checkJourneyPreconditions(from, to);

        var route = routingStrategy.route(from, to);

        PreconditionUtils.checkRouteNotEmpty(route);

        return route;
    }

}
