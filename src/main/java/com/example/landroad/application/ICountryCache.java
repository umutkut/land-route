package com.example.landroad.application;

import com.example.landroad.model.Country;

import java.util.Optional;

public interface ICountryCache {
    void save(Country country);
    Optional<Country> find(String cca3);
}
