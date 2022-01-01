package com.example.landroute.application;

import com.example.landroute.domain.Country;
import com.example.landroute.infrastructure.CountryRepository;
import com.example.landroute.infrastructure.cache.ICountryCache;
import com.example.landroute.infrastructure.dto.CountryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service("countryLoaderService")
@Slf4j
public class CountryLoaderService {

    private final ICountryCache countryCache;
    private final CountryRepository countryRepository;
    ScheduledExecutorService scheduledLoader;

    public CountryLoaderService(ICountryCache countryCache, CountryRepository countryRepository) {
        this.countryCache = countryCache;
        this.countryRepository = countryRepository;
        this.scheduledLoader = Executors.newScheduledThreadPool(1);
    }

    @PostConstruct
    private void scheduleLoader() {
        scheduledLoader.scheduleAtFixedRate(this::cacheCountries,
                0,
                500,
                TimeUnit.MILLISECONDS);
    }

    private void cacheCountries() {

        if (countryCache.hasCached()) {
            log.info("Countries already cached, terminating Scheduled Loader.");
            scheduledLoader.shutdown();
        }

        var countryDTOs = countryRepository.findAll();

        for (CountryDTO dto : countryDTOs) {
            countryCache.cache(Country.fromDTO(dto));
            log.info("Country cached: {}", dto);
        }

    }

}

