package com.slabko.parser.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.slabko.parser.mapper.CryptoMapper;
import com.slabko.parser.model.CryptoCSVBean;
import com.slabko.parser.model.CryptoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Implementation of CryptoCSVParserService.
 */
@Service
public class CryptoCSVParserServiceImpl implements CryptoCSVParserService {

    @Autowired
    private CryptoMapper cryptoMapper;

    @Override
    public List<CryptoEntity> parse(InputStream inputStream) {
        List<CryptoCSVBean> cryptoList = new CsvToBeanBuilder<CryptoCSVBean>(new InputStreamReader(inputStream))
            .withSkipLines(1)
            .withType(CryptoCSVBean.class)
            .build()
            .parse();
        return cryptoMapper.toCryptoEntityList(cryptoList);
    }

}
