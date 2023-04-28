package com.slabko.parser.model;

import java.util.Arrays;
import java.util.Objects;

public enum SupportedCryptos {

    BTC,
    DOGE,
    ETH,
    LTC,
    XRP;

    public static boolean contains(String name) {
        return Arrays.stream(SupportedCryptos.values())
            .map(Objects::toString)
            .anyMatch(value -> value.equalsIgnoreCase(name));
    }

}
