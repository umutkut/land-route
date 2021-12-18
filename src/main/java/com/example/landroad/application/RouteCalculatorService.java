package com.example.landroad.application;

import com.example.landroad.infrastructure.CountryCache;
import com.example.landroad.model.Country;
import com.example.landroad.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RouteCalculatorService {
    private final CountryCache countryCache;

    public List<String> calculateRoute(String origin, String destination) {
        var from = findCountryFromCca3(origin);
        var to = findCountryFromCca3(destination);

        Set<Country> visited = new HashSet<>();
        var route = findRoute(visited, from, to, new ArrayList<>());
        if (route.isEmpty()) throw new IllegalArgumentException(Constants.PATH_NOT_FOUND);
        return route;
    }

    private List<String> findRoute(Set<Country> visited, Country from, Country to, List<String> route) {
        if (visited.contains(from))
            return List.of();
        route.add(from.getCca3());
        if (from.equals(to)) {
            return route;
        }

        visited.add(from);
        List<String> shortestRoute = new ArrayList<>();
        for (String border : from.getBorders()) {
            var borderCountry = findCountryFromCca3(border);
            var routeFromBoarder = findRoute(visited, borderCountry, to, new ArrayList<>(route));

            if (routeFromBoarder.isEmpty()) continue;

            if (shortestRoute.isEmpty()) shortestRoute = routeFromBoarder;

            if (shortestRoute.size() > routeFromBoarder.size())
                shortestRoute = routeFromBoarder;
        }

        return shortestRoute;
    }

    private Country findCountryFromCca3(String cca3) {
        return countryCache.find(cca3).orElseThrow(() -> new IllegalArgumentException(Constants.COUNTRY_NOT_FOUND));
    }

}
