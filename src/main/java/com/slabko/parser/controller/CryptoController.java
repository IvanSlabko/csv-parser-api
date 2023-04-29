package com.slabko.parser.controller;

import com.slabko.parser.dto.CryptoStatisticDTO;
import com.slabko.parser.mapper.CryptoMapper;
import com.slabko.parser.service.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private CryptoMapper cryptoMapper;

    @Operation(description = "Returns the oldest/newest/min/max prices for each crypto for the specified period of time")
    @GetMapping("/statistics")
    public List<CryptoStatisticDTO> getCryptoStatistics(@Parameter(description = "Date pattern yyyy-MM-dd'T'HH:mm:ss", example = "2022-01-01T01:30:00")
                                                        @RequestParam
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        LocalDateTime startDate,
                                                        @Parameter(description = "Date pattern yyyy-MM-dd'T'HH:mm:ss", example = "2022-01-30T22:30:00")
                                                        @RequestParam
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        LocalDateTime endDate) {
        return cryptoMapper.toCryptoStatisticDTOList(cryptoService.calculateCryptoStatistics(startDate, endDate));
    }

    @Operation(description = "Returns a descending sorted list of all the cryptos, comparing the normalized range")
    @GetMapping("/normalized")
    public List<CryptoStatisticDTO> getCryptoStatisticsSortedByNormalizedRange() {
        return cryptoMapper.toCryptoStatisticDTOList(cryptoService.findCryptoStatisticsSortedByNormalizedRange());
    }

    @Operation(description = "Returns the oldest/newest/min/max prices for a requested crypto")
    @GetMapping("/statistics/{name}")
    public List<CryptoStatisticDTO> getCryptoStatisticsByName(@Parameter(description = "The name of the requested crypto")
                                                              @PathVariable String name) {
        return cryptoMapper.toCryptoStatisticDTOList(cryptoService.calculateCryptoStatistics(name));
    }

    @Operation(description = "Returns the crypto with the highest normalized range for a specific day")
    @GetMapping("/statistics/highest")
    public CryptoStatisticDTO getHighestNormalizedCrypto(@Parameter(
                                                            description = "Date for searching highest normalized range. Date pattern yyyy-MM-dd",
                                                            example = "2022-01-01")
                                                         @RequestParam
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         LocalDate date) {
        return cryptoMapper.toCryptoStatisticDTO(cryptoService.findHighestNormalizedCrypto(date));
    }

}
