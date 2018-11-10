package com.uhope.statistic.service.impl;

import com.google.common.collect.Maps;
import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.SuperviseDTO;
import com.uhope.statistic.dto.SurfaceWaterDTO;
import com.uhope.statistic.dto.WaterQualityDTO;
import com.uhope.statistic.mapper.StatisticMapper;
import com.uhope.statistic.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    private StatisticMapper statisticMapper;


    @Override
    public List<RiverStatisticDTO> listRegionName() {
        return statisticMapper.listRegionName();
    }

    @Override
    public List<WaterQualityDTO> listWaterQualityData(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.listWaterQualityData(hashMap);
    }

    @Override
    public List<SurfaceWaterDTO> listSurfaceWaterDTO(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.listSurfaceWaterDTO(hashMap);
    }

    @Override
    public List<SuperviseDTO> listSuperviseDTOs(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.listSuperviseDTOs(hashMap);
    }
}
