package com.slabko.parser.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Model to store crypto statistics.
 */
@Data
public class CryptoStatistic {

    private String name;
    private BigDecimal oldestPrice;
    private BigDecimal newestPrice;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public CryptoStatistic(BigDecimal maxPrice, BigDecimal minPrice, String name, BigDecimal newestPrice, BigDecimal oldestPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.name = name;
        this.newestPrice = newestPrice;
        this.oldestPrice = oldestPrice;
    }

    public CryptoStatistic(BigDecimal maxPrice, BigDecimal minPrice, String name) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.name = name;
    }

}
