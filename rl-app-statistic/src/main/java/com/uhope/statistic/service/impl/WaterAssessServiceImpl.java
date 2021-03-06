package com.uhope.statistic.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.statistic.domain.AmWaterAssess;
import com.uhope.statistic.service.WaterAssessService;
import org.springframework.stereotype.Service;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/13
 * @description: 水质评分细则表
 */
@Service
public class WaterAssessServiceImpl extends AbstractService<AmWaterAssess, AmWaterAssess, String> implements WaterAssessService {

    @Override
    public int update(AmWaterAssess model) {
        return mapper.updateByPrimaryKey(model);
    }
}
