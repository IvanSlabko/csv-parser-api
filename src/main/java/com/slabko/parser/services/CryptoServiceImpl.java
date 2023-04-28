package com.slabko.parser.services;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;
import com.slabko.parser.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    @Override
    public void saveAll(List<CryptoEntity> cryptoEntities) {
        cryptoRepository.saveAll(cryptoEntities);
    }

    @Override
    public List<CryptoStatistic> calculateCryptoStatistics(LocalDateTime startDate, LocalDateTime endDate) {
        return cryptoRepository.findCryptoStatistics(startDate, endDate);
    }

    @Override
    public List<CryptoStatistic> calculateCryptoStatistics(String name) {
        return cryptoRepository.findCryptoStatisticsByName(name);
    }

    @Override
    public List<CryptoStatistic> findCryptoStatisticsSortedByNormalizedRange() {
        return cryptoRepository.findCryptoStatisticsSortedByNormalizedRange();
    }

    @Override
    public CryptoStatistic findHighestNormalizedCrypto(LocalDate date) {
        return cryptoRepository.findHighestNormalizedCrypto(date);
    }

}
