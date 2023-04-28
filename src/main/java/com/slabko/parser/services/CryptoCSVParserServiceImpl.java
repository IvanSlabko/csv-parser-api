package com.slabko.parser.services;

import com.opencsv.bean.CsvToBeanBuilder;
import com.slabko.parser.mapper.CryptoMapper;
import com.slabko.parser.model.CryptoCSVBean;
import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.services.exceptions.FileParsingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CryptoCSVParserServiceImpl implements CryptoCSVParserService {

    @Autowired
    private CryptoMapper cryptoMapper;

    @Override
    public List<CryptoEntity> parse(String filename) {
        try {
            List<CryptoCSVBean> cryptoList = new CsvToBeanBuilder<CryptoCSVBean>(new FileReader(filename))
                .withSkipLines(1)
                .withType(CryptoCSVBean.class)
                .build()
                .parse();
            return cryptoMapper.toCryptoEntityList(cryptoList);
        } catch (IOException e) {
            throw new FileParsingException(e);
        }
    }

}
