package com.uhope.statistic.service;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
public interface StatisticService {

    List<RiverStatisticDTO> listRegionName();

    List<WaterQualityDTO> listWaterQualityData();
}
