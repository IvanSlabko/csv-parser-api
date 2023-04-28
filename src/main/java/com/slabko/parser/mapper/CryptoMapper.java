package com.slabko.parser.mapper;

import com.slabko.parser.dtos.CryptoDTO;
import com.slabko.parser.dtos.CryptoStatisticDTO;
import com.slabko.parser.model.CryptoCSVBean;
import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoMapper {

    List<CryptoEntity> toCryptoEntityList(List<CryptoCSVBean> cryptoCSVBeans);

    List<CryptoStatisticDTO> toCryptoStatisticDTOList(List<CryptoStatistic> cryptoStatistics);

    CryptoStatisticDTO toCryptoStatisticDTO(CryptoStatistic cryptoStatistics);

}
