package com.example.landroute.utils;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.domain.Country;
import com.example.landroute.exception.PathNotFoundException;

public class PreconditionUtils {
    private PreconditionUtils() {
        throw new IllegalStateException("Util class.");
    }

    public static void checkJourneyPreconditions(Country origin, Country destination) {
        if (!(hasAnyBoarder(origin, destination)
                && regionsConnected(origin, destination)))
            throw new PathNotFoundException(ErrorMessage.PATH_NOT_FOUND,
                    origin.getCode().toString(),
                    destination.getCode().toString());
    }

    private static boolean hasAnyBoarder(Country origin, Country destination) {
        return !origin.getBorders().isEmpty() && !destination.getBorders().isEmpty();
    }

    private static boolean regionsConnected(Country origin, Country destination) {
        var allowableRegions = origin.getRegion().getBorderSet();
        var destinationRegion = destination.getRegion();

        return allowableRegions.contains(destinationRegion);
    }

}
