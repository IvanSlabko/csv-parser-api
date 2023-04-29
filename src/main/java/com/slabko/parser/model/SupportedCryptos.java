package com.slabko.parser.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Enumeration of supported cryptos.
 */
public enum SupportedCryptos {

    BTC,
    DOGE,
    ETH,
    LTC,
    XRP;

    /**
     * Checks if supported cryptos contain specified name.
     *
     * @param name Crypto name
     * @return boolean
     */
    public static boolean contains(String name) {
        return Arrays.stream(SupportedCryptos.values())
            .map(Objects::toString)
            .anyMatch(value -> value.equalsIgnoreCase(name));
    }

}
