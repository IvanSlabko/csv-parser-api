package com.slabko.parser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO to represent crypto statistic.
 */
@Data
public class CryptoStatisticDTO {

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal oldestPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal newestPrice;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
