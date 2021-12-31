package com.example.landroute.infrastructure.cache;

import com.example.landroute.domain.Country;

public interface ICountryCache {
    void save(Country country);

    Country get(String cca3);
}
