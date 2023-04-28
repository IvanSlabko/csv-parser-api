package com.slabko.parser.services;

import com.slabko.parser.model.CryptoEntity;

import java.util.List;

public interface CryptoCSVParserService {

    List<CryptoEntity> parse(String filename);
}
