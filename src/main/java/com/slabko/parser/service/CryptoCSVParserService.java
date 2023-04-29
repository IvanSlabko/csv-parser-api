package com.slabko.parser.service;

import com.slabko.parser.model.CryptoEntity;

import java.io.InputStream;
import java.util.List;

/**
 * Service for parcing CSV files.
 */
public interface CryptoCSVParserService {

    /**
     * Parse CSV file.
     *
     * @param inputStream InputStream from CSV file
     * @return List of CryptoEntity
     */
    List<CryptoEntity> parse(InputStream inputStream);

}
