package com.slabko.parser.service;

import com.opencsv.bean.AbstractBeanField;
import com.slabko.parser.model.CryptoCSVBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class for converting Timestamp to LocalDateTime.
 */
public class LocalDateTimeConverter extends AbstractBeanField<CryptoCSVBean, LocalDateTime> {
    @Override
    protected LocalDateTime convert(String timestamp) {
        return LocalDateTime.ofInstant(new Date(Long.parseLong(timestamp)).toInstant(), ZoneId.systemDefault());
    }
}
