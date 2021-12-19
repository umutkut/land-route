package com.example.landroute.infrastructure;

import com.example.landroute.model.Country;

public interface ICountryCache {
    void save(Country country);

    Country get(String cca3);
}
