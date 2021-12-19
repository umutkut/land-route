package com.example.landroute.model;

import java.util.EnumSet;

public enum Region {
    EUROPE,
    ASIA,
    AMERICAS,
    OCEANIA,
    ANTARCTIC,
    AFRICA;

    static {
        EUROPE.borderSet = EnumSet.of(ASIA, AFRICA, EUROPE);
        ASIA.borderSet = EnumSet.of(AFRICA, EUROPE, ASIA);
        AFRICA.borderSet = EnumSet.of(EUROPE, ASIA, AFRICA);
        OCEANIA.borderSet = EnumSet.noneOf(Region.class);
        ANTARCTIC.borderSet = EnumSet.noneOf(Region.class);
        AMERICAS.borderSet = EnumSet.noneOf(Region.class);
    }

    private EnumSet<Region> borderSet;

    public EnumSet<Region> getBorderSet() {
        return borderSet;
    }
}