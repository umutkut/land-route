package com.example.landroute.infrastructure;

import com.example.landroute.application.ICountryCache;
import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.exception.InvalidCountryCodeException;
import com.example.landroute.model.Country;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryCache implements ICountryCache {
    private final Map<String, Country> idCountryMap = new HashMap<>();

    @Override
    public void save(Country country) {
        idCountryMap.put(country.getCode(), country);
    }

    @Override
    public Country get(String cca3) {
        if (!idCountryMap.containsKey(cca3)) throw new InvalidCountryCodeException(ErrorMessage.INVALID_COUNTRY, cca3);

        return idCountryMap.get(cca3);
    }


    @Override
    public Map<String, Country> getAll() {
        return idCountryMap;
    }
}
