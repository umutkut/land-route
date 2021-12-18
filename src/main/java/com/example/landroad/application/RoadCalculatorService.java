package com.example.landroad.application;

import com.example.landroad.infrastructure.CountryCache;
import com.example.landroad.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RoadCalculatorService {
    private final CountryCache countryCache;

    public List<Country> calculateRoad(String fromId, String toId){
        var from = findCountryFromCca3(fromId);
        var to = findCountryFromCca3(toId);

        Set<Country> visited = new HashSet<>();
        var path = findPath(visited, from, to, new ArrayList<>());
        if(path.isEmpty()) throw new IllegalArgumentException();
        return path;
    }

    private List<Country> findPath(Set<Country> visited, Country from, Country to, List<Country> path){
        if(visited.contains(from))
            return List.of();
        path.add(from);
        if(from.equals(to)){
            return path;
        }

        visited.add(from);

        for(String border: from.getBorders()){
            var borderCountry = findCountryFromCca3(border);
            var pathFromBoarder = findPath(visited, borderCountry, to, path);
            if (!pathFromBoarder.isEmpty())
                return pathFromBoarder;
        }

        return List.of();
    }

    private Country findCountryFromCca3(String cca3){
        return countryCache.find(cca3).orElseThrow(IllegalArgumentException::new);
    }

    private void addAllBordersToQueue(Queue<Country> countries, List<String> borders){
        for (String border: borders){
            var country = countryCache.find(border).orElseThrow(IllegalArgumentException::new);
            countries.add(country);
        }
    }

}
