package com.slabko.parser.service;

import com.slabko.parser.mapper.CryptoMapper;
import com.slabko.parser.model.CryptoCSVBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CryptoCSVParserServiceImplTest {

    @Mock
    private CryptoMapper cryptoMapper;
    @InjectMocks
    private CryptoCSVParserServiceImpl cryptoCSVParserService;
    @Captor
    private ArgumentCaptor<List<CryptoCSVBean>> cryptoCSVBeansCaptor;

    @Test
    void testParse() throws IOException {
        InputStream is = Files.newInputStream(Paths.get("src/test/resources/BTC_values.csv"));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        cryptoCSVParserService.parse(is);
        verify(cryptoMapper).toCryptoEntityList(cryptoCSVBeansCaptor.capture());
        List<CryptoCSVBean> actual = cryptoCSVBeansCaptor.getValue();
        assertEquals(2, actual.size());
        assertEquals(BigDecimal.valueOf(46813.21), actual.get(0).getPrice());
        assertEquals(LocalDateTime.parse("2022-01-01T04:00"), actual.get(0).getDate());
        assertEquals("BTC", actual.get(0).getName());
        assertEquals(BigDecimal.valueOf(46979.61), actual.get(1).getPrice());
        assertEquals(LocalDateTime.parse("2022-01-01T07:00"), actual.get(1).getDate());
    }
}
