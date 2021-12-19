package com.example.landroute.application.strategy;

import com.example.landroute.model.Country;

import java.util.List;

public interface IRoutingStrategy {
    List<String> route(Country from, Country to);
}
