package com.slabko.parser.service;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for crypto logic.
 */
public interface CryptoService {

    /**
     * Save all crypto entities.
     *
     * @param cryptoEntities crypto entities to save
     */
    void saveAll(List<CryptoEntity> cryptoEntities);

    /**
     * Calculates oldest/newest/min/max prices for each crypto for the specified period of time.
     *
     * @param startDate start date to search with
     * @param endDate end date to search with
     * @return List of CryptoStatistic
     */
    List<CryptoStatistic> calculateCryptoStatistics(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Calculates the oldest/newest/min/max prices for the requested crypto.
     *
     * @param name requsted crypto name
     * @return List of CryptoStatistic
     */
    List<CryptoStatistic> calculateCryptoStatistics(String name);

    /**
     * Finds the descending sorted list of all the cryptos, comparing the normalized range (max-min)/min).
     *
     * @return List of CryptoStatistic
     */
    List<CryptoStatistic> findCryptoStatisticsSortedByNormalizedRange();

    /**
     * Finds the crypto with the highest normalized range for a specific date.
     *
     * @param date specific date to search with
     * @return CryptoStatistic
     */
    CryptoStatistic findHighestNormalizedCrypto(LocalDate date);

}
