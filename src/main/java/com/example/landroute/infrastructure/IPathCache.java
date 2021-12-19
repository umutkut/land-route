package com.example.landroute.infrastructure;

import java.util.List;

public interface IPathCache {
    void cache(String origin, String destination, List<String> path);

    boolean contains(String origin, String destination);

    List<String> getCachedPath(String origin, String destination);
}
