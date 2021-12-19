package com.example.landroute.utils;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.exception.PathNotFoundException;
import com.example.landroute.model.Country;
import com.google.common.base.Preconditions;

import java.util.List;

public class PreconditionUtils {
    private PreconditionUtils() {
        throw new IllegalStateException("Util class.");
    }

    public static void checkJourneyPreconditions(Country origin, Country destination) {
        if (!(hasAnyBoarder(origin, destination)
                && regionsConnected(origin, destination)))
            throw new PathNotFoundException(ErrorMessage.PATH_NOT_FOUND,
                    origin.getCode(),
                    destination.getCode());
    }

    private static boolean hasAnyBoarder(Country origin, Country destination) {
        return !origin.getBorders().isEmpty() && !destination.getBorders().isEmpty();
    }

    private static boolean regionsConnected(Country origin, Country destination) {
        var allowableRegions = origin.getRegion().getBorderSet();
        var destinationRegion = destination.getRegion();

        return allowableRegions.contains(destinationRegion);
    }

    public static void checkRouteNotEmpty(List<String> route) {
        Preconditions.checkArgument(!route.isEmpty());
    }
}
