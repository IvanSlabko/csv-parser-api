package com.slabko.parser.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvNumber;
import com.slabko.parser.services.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CryptoCSVBean {

    @CsvCustomBindByPosition(position = 0, converter = LocalDateTimeConverter.class)
    private LocalDateTime date;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvNumber("#.#")
    @CsvBindByPosition(position = 2)
    private BigDecimal price;

}
