package com.example.landroute.infrastructure.cache;

import com.example.landroute.domain.valueobject.Route;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
class RouteCache implements IRouteCache {
    private final Table<String, String, Route> originDestinationRouteTable = HashBasedTable.create();

    @Override
    public void cache(Route route) {
        String origin = route.getOrigin().getValue();
        String destination = route.getDestination().getValue();

        originDestinationRouteTable.put(origin, destination, route);

        originDestinationRouteTable.put(destination, origin, route.getReversed());
    }

    @Override
    public boolean contains(String origin, String destination) {
        return originDestinationRouteTable.contains(origin, destination);
    }

    @Override
    public Route getCachedPath(String origin, String destination) {
        log.info("{} - {} read from cache", origin, destination);
        return originDestinationRouteTable.get(origin, destination);
    }
}
