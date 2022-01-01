package com.example.landroute.infrastructure.cache;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.domain.Country;
import com.example.landroute.exception.InvalidCountryCodeException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class CountryCache implements ICountryCache {
    private final Map<String, Country> idCountryMap = new ConcurrentHashMap<>();
    private static final int NUMBER_OF_EXPECTED_COUNTRIES = 250;

    @Override
    public void cache(Country country) {
        idCountryMap.put(country.getCode().getValue(), country);
    }

    @Override
    public Country get(String cca3) {
        if (!idCountryMap.containsKey(cca3)) throw new InvalidCountryCodeException(ErrorMessage.INVALID_COUNTRY, cca3);

        return idCountryMap.get(cca3);
    }

    @Override
    public boolean hasCached() {
        return this.size() == NUMBER_OF_EXPECTED_COUNTRIES;
    }

    int size() {
        return idCountryMap.size();
    }

}
