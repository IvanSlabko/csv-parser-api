package com.slabko.parser.repository;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Crypto entity.
 */
public interface CryptoRepository extends CrudRepository<CryptoEntity, Long> {

    /**
     * Calculates oldest/newest/min/max prices for each crypto for the specified period of time.
     *
     * @param startDate start date to search with
     * @param endDate end date to search with
     * @return List of CryptoStatistic
     */
    @Query("SELECT new com.slabko.parser.model.CryptoStatistic(MAX(ce.price) AS maxPrice,\n" +
        "       MIN(ce.price) AS minPrice,\n" +
        "       ce.name AS name,\n" +
        "       (SELECT price\n" +
        "        FROM CryptoEntity\n" +
        "        WHERE date BETWEEN :startDate AND :endDate\n" +
        "          AND date = " +
        "               (SELECT MAX(date) " +
        "                FROM CryptoEntity " +
        "                WHERE date BETWEEN :startDate AND :endDate" +
        "                   AND name = ce.name)\n" +
        "          AND name = ce.name) AS newestPrice,\n" +
        "       (SELECT price\n" +
        "        FROM CryptoEntity\n" +
        "        WHERE date BETWEEN :startDate AND :endDate\n" +
        "          AND date = " +
        "               (SELECT MIN(date) " +
        "               FROM CryptoEntity " +
        "               WHERE date BETWEEN :startDate AND :endDate AND name = ce.name)\n" +
        "          AND name = ce.name) AS oldestPrice)\n" +
        " FROM CryptoEntity AS ce\n" +
        " WHERE ce.date BETWEEN :startDate AND :endDate\n" +
        " GROUP BY ce.name")
    List<CryptoStatistic> findCryptoStatistics(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Calculates the oldest/newest/min/max prices for the requested crypto.
     *
     * @param name requsted crypto name
     * @return List of CryptoStatistic
     */
    @Query("SELECT new com.slabko.parser.model.CryptoStatistic(MAX(ce.price) AS maxPrice,\n" +
        "       MIN(ce.price) AS minPrice,\n" +
        "       ce.name AS name,\n" +
        "       (SELECT price\n" +
        "        FROM CryptoEntity\n" +
        "        WHERE name = :name\n" +
        "          AND date = (SELECT MAX(date) FROM CryptoEntity WHERE name = ce.name)\n" +
        "          AND name = ce.name) AS newestPrice,\n" +
        "       (SELECT price\n" +
        "        FROM CryptoEntity\n" +
        "        WHERE name = :name\n" +
        "          AND date = (SELECT MIN(date) FROM CryptoEntity WHERE name = ce.name)\n" +
        "          AND name = ce.name) AS oldestPrice)\n" +
        " FROM CryptoEntity AS ce\n" +
        " WHERE name = :name\n" +
        " GROUP BY ce.name")
    List<CryptoStatistic> findCryptoStatisticsByName(String name);

    /**
     * Finds the descending sorted list of all the cryptos, comparing the normalized range (max-min)/min).
     *
     * @return List of CryptoStatistic
     */
    @Query("SELECT new com.slabko.parser.model.CryptoStatistic(MAX(price) AS maxPrice, MIN(price) AS minPrice, name)\n" +
        " FROM CryptoEntity\n" +
        " GROUP BY name\n" +
        " ORDER BY (MAX(price) - MIN(price)) / MIN(price) DESC ")
    List<CryptoStatistic> findCryptoStatisticsSortedByNormalizedRange();

    /**
     * Finds the crypto with the highest normalized range for a specific date.
     *
     * @param date specific date to search with
     * @return CryptoStatistic
     */
    @Query("SELECT new com.slabko.parser.model.CryptoStatistic(MAX(price) AS maxPrice, MIN(price) AS minPrice, name)\n" +
        "FROM CryptoEntity\n" +
        "WHERE YEAR(date) = YEAR(:date) and MONTH(date) = MONTH(:date) and DAY(date) = DAY(:date)\n" +
        "GROUP BY name\n" +
        "ORDER BY (MAX(price) - MIN(price)) / MIN(price) DESC " +
        "LIMIT 1")
    CryptoStatistic findHighestNormalizedCrypto(LocalDate date);

}
