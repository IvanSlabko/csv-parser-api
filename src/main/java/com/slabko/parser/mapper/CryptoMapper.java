package com.slabko.parser.mapper;

import com.slabko.parser.dto.CryptoStatisticDTO;
import com.slabko.parser.model.CryptoCSVBean;
import com.slabko.parser.model.CryptoEntity;
import com.slabko.parser.model.CryptoStatistic;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Class for mapping Crypto models.
 */
@Mapper(componentModel = "spring")
public interface CryptoMapper {

    /**
     * Maps List of CryptoCSVBean to List of CryptoEntity.
     *
     * @param cryptoCSVBeans List of CryptoCSVBean to map
     * @return Mapped list of CryptoEntity
     */
    List<CryptoEntity> toCryptoEntityList(List<CryptoCSVBean> cryptoCSVBeans);

    /**
     * Maps List of CryptoStatistic to List of CryptoStatisticDTO.
     *
     * @param cryptoStatistics List of CryptoStatistic to map
     * @return Mapped list of CryptoStatisticDTO
     */
    List<CryptoStatisticDTO> toCryptoStatisticDTOList(List<CryptoStatistic> cryptoStatistics);

    /**
     * Maps CryptoStatistic object to CryptoStatisticDTO object.
     *
     * @param cryptoStatistics CryptoStatistic object to map
     * @return Mapped CryptoStatisticDTO object
     */
    CryptoStatisticDTO toCryptoStatisticDTO(CryptoStatistic cryptoStatistics);

}
