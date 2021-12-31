package com.example.landroute.infrastructure.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("country")
@ToString
public class CountryDTO {

    private final String cca3;

    private final List<String> borders;

    private final String region;
}
