package com.example.landroute.infrastructure.cache;

import com.example.landroute.domain.valueobject.Route;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
class RouteCache implements IRouteCache {
    private final Table<String, String, Route> originDestinationPathTable = HashBasedTable.create();

    @Override
    public void cache(Route route) {
        String origin = route.getOrigin().getValue();
        String destination = route.getDestination().getValue();

        originDestinationPathTable.put(origin, destination, route);

        originDestinationPathTable.put(destination, origin, route.getReversed());
    }

    @Override
    public boolean contains(String origin, String destination) {
        return originDestinationPathTable.contains(origin, destination);
    }

    @Override
    public Route getCachedPath(String origin, String destination) {
        log.info("{} - {} read from cache", origin, destination);
        return originDestinationPathTable.get(origin, destination);
    }
}
