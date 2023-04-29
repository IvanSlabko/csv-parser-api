package com.slabko.parser.repository;

import com.slabko.parser.CsvParserAPI;
import com.slabko.parser.TestConfig;
import com.slabko.parser.model.CryptoStatistic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = { CsvParserAPI.class, TestConfig.class })
class CryptoRepositoryIntegrationTest {

    @Autowired
    private CryptoRepository cryptoRepository;

    @Test
    void testFindCryptoStatistics() {
        LocalDateTime startDate = LocalDateTime.parse("2022-01-01T05:00");
        LocalDateTime endDate = LocalDateTime.parse("2022-01-01T22:00");
        List<CryptoStatistic> cryptoStatistics = cryptoRepository.findCryptoStatistics(startDate, endDate);
        assertEquals(BigDecimal.valueOf(47143.98), cryptoStatistics.get(0).getMaxPrice());
    }

    @Test
    void testCalculateCryptoStatistics_byName() {
        String cryptoName = "BTC";
        List<CryptoStatistic> actual = cryptoRepository.findCryptoStatisticsByName(cryptoName);
        assertEquals(1, actual.size());
        assertEquals(BigDecimal.valueOf(47722.66), actual.get(0).getMaxPrice());
        assertEquals(BigDecimal.valueOf(33276.59), actual.get(0).getMinPrice());
        assertEquals(BigDecimal.valueOf(38415.79), actual.get(0).getNewestPrice());
        assertEquals(BigDecimal.valueOf(46813.21), actual.get(0).getOldestPrice());
    }

    @Test
    void testFindCryptoStatisticsSortedByNormalizedRange() {
        List<CryptoStatistic> actual = cryptoRepository.findCryptoStatisticsSortedByNormalizedRange();
        assertEquals(5, actual.size());
        assertEquals("ETH", actual.get(0).getName());
        assertEquals("BTC", actual.get(4).getName());
    }

    @Test
    void testFindHighestNormalizedCrypto() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        CryptoStatistic actual = cryptoRepository.findHighestNormalizedCrypto(date);
        assertEquals("XRP", actual.getName());
        assertEquals(BigDecimal.valueOf(0.85), actual.getMaxPrice());
        assertEquals(BigDecimal.valueOf(0.83), actual.getMinPrice());
    }

}
