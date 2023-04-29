package com.slabko.parser.service;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.SupportedCryptos;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Component for initializing crypto data.
 */
@Component
public class CryptoInitializer {

    @Autowired
    private CryptoCSVParserService cryptoCSVParserService;
    @Autowired
    private CryptoService cryptoService;
    private final Logger logger = LoggerFactory.getLogger(CryptoInitializer.class);
    @Value("classpath:static/*")
    private Resource[] csvFiles;

    private static final String VALUE_SEPARATOR = "_values.csv";

    /**
     * Initializing crypto data.
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        for (Resource csvFile : csvFiles) {
            String cryptoName = StringUtils.substringBefore(csvFile.getFilename(), VALUE_SEPARATOR);
            if (SupportedCryptos.contains(cryptoName)) {
                List<CryptoEntity> cryptoEntityList = cryptoCSVParserService.parse(csvFile.getInputStream());
                cryptoService.saveAll(cryptoEntityList);
            }  else {
                logger.warn("{} is not supported", cryptoName);
            }
        }
    }
}
