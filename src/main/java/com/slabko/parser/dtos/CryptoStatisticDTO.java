package com.slabko.parser.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

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
