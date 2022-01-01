package com.example.landroute.domain.domainservice.routingstrategy;

import com.example.landroute.domain.Country;
import com.example.landroute.domain.valueobject.Route;

public interface IRoutingStrategy {
    Route findRoute(Country from, Country to);
}
