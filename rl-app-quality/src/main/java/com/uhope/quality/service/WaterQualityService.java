package com.uhope.quality.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.quality.domain.WaterQuality;
import com.uhope.quality.dto.WaterQualityDTO;

public interface WaterQualityService extends Service<WaterQuality, WaterQualityDTO,String> {
    public PageInfo<WaterQuality> list(Integer pageNumber, Integer pageSize, String issue, Integer status, String createUser, Integer num);

    public String selectSHZB();

    public String selectSHBJ();

    WaterQuality get1(String id);
}
