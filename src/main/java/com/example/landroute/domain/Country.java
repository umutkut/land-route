package com.example.landroute.domain;

import com.example.landroute.domain.domainservice.routingstrategy.BreadthFirstSearchStrategy;
import com.example.landroute.domain.domainservice.routingstrategy.IRoutingStrategy;
import com.example.landroute.domain.valueobject.Region;
import com.example.landroute.infrastructure.dto.CountryDTO;
import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.cache.IPathCache;
import com.example.landroute.utils.PreconditionUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@ToString
@Getter
@Slf4j
public class Country {

    private final String code;

    private final List<String> borders;

    private final Region region;

    public static Country fromDTO(CountryDTO dto) {
        return new Country(dto.getCca3(), dto.getBorders(), Region.valueOf(dto.getRegion().toUpperCase(Locale.ENGLISH)));
    }

    public List<String> findRouteTo(Country destination, ICountryCache countryCache, IPathCache pathCache) {
        log.info("Finding route from: {} to: {}", this.code, destination.code);

        PreconditionUtils.checkJourneyPreconditions(this, destination);


        if (pathCache.contains(this.code, destination.code)) {
            log.info("Returning precalculated route from cache.");
            return pathCache.getCachedPath(this.code, destination.code);
        }

        IRoutingStrategy routingStrategy = new BreadthFirstSearchStrategy(countryCache, pathCache);
        return routingStrategy.findRoute(this, destination);
    }

}
