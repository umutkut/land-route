package com.example.landroute.infrastructure;

import com.example.landroute.infrastructure.dto.CountryDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<CountryDTO, String> {

}
