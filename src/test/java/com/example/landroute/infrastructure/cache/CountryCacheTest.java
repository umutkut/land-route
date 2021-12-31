package com.example.landroute.infrastructure.cache;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryCacheTest {
    @Autowired
    CountryCache countryCache;

    @Test
    void testLoadCountriesToCache() {
        Assertions.assertEquals(250, countryCache.size());
    }
}