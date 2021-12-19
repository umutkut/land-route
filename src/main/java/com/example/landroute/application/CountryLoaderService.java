package com.example.landroute.application;


import com.example.landroute.infrastructure.ICountryCache;
import com.example.landroute.model.Country;
import com.example.landroute.model.Region;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Service("countryLoaderService")
@Slf4j
public class CountryLoaderService {
    private final ICountryCache countryCache;

    public CountryLoaderService(ICountryCache countryCache){
        this.countryCache = countryCache;
        loadCountriesFromJson();
    }

    private void loadCountriesFromJson() {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("countries.json"));

            JSONArray countries = (JSONArray) obj;

            countries.forEach(this::saveCountryFromJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCountryFromJson(Object countryObj){
        JSONObject countryJson = (JSONObject) countryObj;

        String cca3 = (String) countryJson.get("cca3");
        List<String> borders = (List<String>) countryJson.get("borders");
        String region = (String) countryJson.get("region");

        Country country = new Country(cca3, borders, Region.valueOf(region.toUpperCase(Locale.ENGLISH)));
        countryCache.save(country);

        log.debug("Cached: {}", country);
    }

}
