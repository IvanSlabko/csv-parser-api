package com.slabko.parser.services;

import com.slabko.parser.model.CryptoEntity;

import java.io.InputStream;
import java.util.List;

public interface CryptoCSVParserService {

    List<CryptoEntity> parse(InputStream inputStream);
}
