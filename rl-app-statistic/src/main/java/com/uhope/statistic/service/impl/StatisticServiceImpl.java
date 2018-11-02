package com.uhope.statistic.service.impl;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.WaterQualityDTO;
import com.uhope.statistic.mapper.StatisticMapper;
import com.uhope.statistic.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<WaterQualityDTO> listWaterQualityData() {
        return statisticMapper.listWaterQualityData();
    }
}
