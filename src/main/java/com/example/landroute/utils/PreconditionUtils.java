package com.example.landroute.utils;

import com.example.landroute.constants.ErrorMessage;
import com.example.landroute.model.Country;
import com.google.common.base.Preconditions;

public class PreconditionUtils {
    private PreconditionUtils() {
        throw new IllegalStateException("Util class.");
    }

    public static void checkJourneyPreconditions(Country origin, Country destination) {
        Preconditions.checkArgument(
                hasAnyBoarder(origin, destination)
                        && regionsConnected(origin, destination),
                ErrorMessage.PATH_NOT_FOUND);
    }

    private static boolean hasAnyBoarder(Country origin, Country destination) {
        return !origin.getBorders().isEmpty() && !destination.getBorders().isEmpty();
    }

    private static boolean regionsConnected(Country origin, Country destination) {
        return origin.getRegion().getBorderSet().contains(destination.getRegion());
    }

}
