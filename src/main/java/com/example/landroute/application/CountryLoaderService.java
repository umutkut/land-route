package com.example.landroute.application;

import com.example.landroute.domain.Country;
import com.example.landroute.infrastructure.CountryRepository;
import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.dto.CountryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("countryLoaderService")
@RequiredArgsConstructor
@Slf4j
public class CountryLoaderService {

    private final ICountryCache countryCache;
    private final CountryRepository countryRepository;

    @PostConstruct
    private void cacheCountries() {
        var countryDTOs = countryRepository.findAll();

        for (CountryDTO dto : countryDTOs) {
            countryCache.cache(Country.fromDTO(dto));
            log.info("Country cached: {}", dto.toString());
        }
    }

}

