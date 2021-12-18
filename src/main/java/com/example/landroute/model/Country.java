package com.example.landroute.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
public class Country {

    private final String cca3;

    private final List<String> borders;

}
