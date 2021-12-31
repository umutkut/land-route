package com.example.landroute.domain.domainservice.routingstrategy;

import com.example.landroute.domain.Country;

import java.util.List;

public interface IRoutingStrategy {
    List<String> findRoute(Country from, Country to);
}
