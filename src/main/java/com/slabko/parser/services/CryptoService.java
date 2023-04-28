package com.slabko.parser.services;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CryptoService {

    void saveAll(List<CryptoEntity> cryptoEntities);

    List<CryptoStatistic> calculateCryptoStatistics(LocalDateTime startDate, LocalDateTime endDate);

    List<CryptoStatistic> calculateCryptoStatistics(String name);

    List<CryptoStatistic> findCryptoStatisticsSortedByNormalizedRange();

    CryptoStatistic findHighestNormalizedCrypto(LocalDate date);

}
