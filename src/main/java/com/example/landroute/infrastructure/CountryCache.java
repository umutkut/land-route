package com.example.landroute.infrastructure;

import com.example.landroute.application.ICountryCache;
import com.example.landroute.model.Country;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CountryCache implements ICountryCache {
    private Map<String, Country> idCountryMap = new HashMap<>();

    @Override
    public void save(Country country){
        idCountryMap.put(country.getCca3(), country);
    }

    @Override
    public Optional<Country> find(String cca3){
        return Optional.ofNullable(idCountryMap.get(cca3));
    }

}
