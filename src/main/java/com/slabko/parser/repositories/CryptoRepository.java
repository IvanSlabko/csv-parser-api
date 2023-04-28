package com.slabko.parser.repositories;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CryptoRepository extends CrudRepository<CryptoEntity, Long> {

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

    @Query("SELECT new com.slabko.parser.model.CryptoStatistic(MAX(price) AS maxPrice, MIN(price) AS minPrice, name)\n" +
        " FROM CryptoEntity\n" +
        " GROUP BY name\n" +
        " ORDER BY (MAX(price) - MIN(price)) / MIN(price) DESC ")
    List<CryptoStatistic> findCryptoStatisticsSortedByNormalizedRange();

    @Query("SELECT new com.slabko.parser.model.CryptoStatistic(MAX(price) AS maxPrice, MIN(price) AS minPrice, name)\n" +
        "FROM CryptoEntity\n" +
        "WHERE YEAR(date) = YEAR(:date) and MONTH(date) = MONTH(:date) and DAY(date) = DAY(:date)\n" +
        "GROUP BY name\n" +
        "ORDER BY (MAX(price) - MIN(price)) / MIN(price) DESC " +
        "LIMIT 1")
    CryptoStatistic findHighestNormalizedCrypto(LocalDate date);

}
