package com.uhope.statistic.mapper;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.SuperviseDTO;
import com.uhope.statistic.dto.SurfaceWaterDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
public interface StatisticMapper {

    List<RiverStatisticDTO> listRegionName();

    List<WaterQualityDTO> listWaterQualityData(HashMap<String, Object> hashMap);

    List<SurfaceWaterDTO> listSurfaceWaterDTO(HashMap<String, Object> hashMap);

    List<SuperviseDTO> listSuperviseDTOs(HashMap<String, Object> hashMap);
}
