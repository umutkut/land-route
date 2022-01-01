package com.example.landroute.infrastructure.cache;

import com.example.landroute.domain.Country;

public interface ICountryCache {
    void cache(Country country);

    Country get(String code);
}
