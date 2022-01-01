package com.example.landroute.domain;

import com.example.landroute.domain.domainservice.routingstrategy.BreadthFirstSearchStrategy;
import com.example.landroute.domain.domainservice.routingstrategy.IRoutingStrategy;
import com.example.landroute.domain.valueobject.CountryCode;
import com.example.landroute.domain.valueobject.Region;
import com.example.landroute.domain.valueobject.Route;
import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.cache.IRouteCache;
import com.example.landroute.infrastructure.dto.CountryDTO;
import com.example.landroute.utils.PreconditionUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@ToString
@Getter
@Slf4j
public class Country {

    private final CountryCode code;

    private final List<String> borders;

    private final Region region;

    public static Country fromDTO(CountryDTO dto) {
        return new Country(new CountryCode(dto.getCca3()), dto.getBorders(), Region.valueOf(dto.getRegion().toUpperCase(Locale.ENGLISH)));
    }

    public Route findRouteTo(Country destination, IRoutingStrategy routingStrategy) {
        log.info("Finding route from: {} to: {}", this.code, destination.code);

        PreconditionUtils.checkJourneyPreconditions(this, destination);

        return routingStrategy.findRoute(this, destination);
    }

}
