package com.example.landroute.infrastructure.cache;

import com.example.landroute.domain.valueobject.Route;

public interface IRouteCache {
    void cache(Route route);

    boolean contains(String origin, String destination);

    Route getCachedPath(String origin, String destination);
}
