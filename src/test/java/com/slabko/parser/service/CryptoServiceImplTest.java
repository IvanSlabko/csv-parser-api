package com.slabko.parser.service;

import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.repository.CryptoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CryptoServiceImplTest {

    @Mock
    private CryptoRepository cryptoRepository;
    @InjectMocks
    private CryptoServiceImpl cryptoService;

    @Test
    void testSaveAll() {
        List<CryptoEntity> cryptoEntities = List.of(initCryptoEntity());
        cryptoService.saveAll(cryptoEntities);
        verify(cryptoRepository).saveAll(cryptoEntities);
    }

    @Test
    void testCalculateCryptoStatistics_byDates() {
        LocalDateTime startDate = LocalDateTime.parse("2022-01-01T05:00");
        LocalDateTime endDate = LocalDateTime.parse("2022-06-01T05:00");
        cryptoService.calculateCryptoStatistics(startDate, endDate);
        verify(cryptoRepository).findCryptoStatistics(startDate, endDate);
    }

    @Test
    void testCalculateCryptoStatistics_byName() {
        String cryptoName = "BTC";
        cryptoService.calculateCryptoStatistics(cryptoName);
        verify(cryptoRepository).findCryptoStatisticsByName(cryptoName);
    }

    @Test
    void testFindCryptoStatisticsSortedByNormalizedRange() {
        cryptoService.findCryptoStatisticsSortedByNormalizedRange();
        verify(cryptoRepository).findCryptoStatisticsSortedByNormalizedRange();
    }

    @Test
    void testFindHighestNormalizedCrypto() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        cryptoService.findHighestNormalizedCrypto(date);
        verify(cryptoRepository).findHighestNormalizedCrypto(date);
    }

    private CryptoEntity initCryptoEntity() {
        CryptoEntity cryptoEntity = new CryptoEntity();
        cryptoEntity.setName("BTC");
        cryptoEntity.setDate(LocalDateTime.parse("2022-01-01T05:00"));
        cryptoEntity.setPrice(BigDecimal.valueOf(46813.21));
        return cryptoEntity;
    }
}
