package com.uhope.statistic.mapper;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
public interface StatisticMapper {

    List<RiverStatisticDTO> listRegionName();

    List<WaterQualityDTO> listWaterQualityData();
}
