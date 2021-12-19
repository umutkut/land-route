package com.example.landroute.infrastructure;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PathCache implements IPathCache {
    private final Table<String, String, List<String>> originDestinationPathTable = HashBasedTable.create();

    @Override
    public void cache(String origin, String destination, List<String> path) {
        originDestinationPathTable.put(origin, destination, path);
        var reversedPath = new ArrayList<>(path);
        Collections.reverse(reversedPath);
        originDestinationPathTable.put(destination, origin, reversedPath);
    }

    @Override
    public boolean contains(String origin, String destination) {
        return originDestinationPathTable.contains(origin, destination);
    }

    @Override
    public List<String> getCachedPath(String origin, String destination) {
        log.info("{} - {} read from cache", origin, destination);
        return originDestinationPathTable.get(origin, destination);
    }
}
