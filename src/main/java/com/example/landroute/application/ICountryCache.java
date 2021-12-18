package com.example.landroute.application;

import com.example.landroute.model.Country;

import java.util.Optional;

public interface ICountryCache {
    void save(Country country);
    Optional<Country> find(String cca3);
}
