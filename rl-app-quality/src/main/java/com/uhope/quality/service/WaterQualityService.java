package com.uhope.quality.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.quality.domain.WaterQuality;
import com.uhope.quality.dto.WaterQualityDTO;

public interface WaterQualityService extends Service<WaterQuality, WaterQualityDTO,String> {
    public PageInfo<WaterQuality> list(Integer pageNumber, Integer pageSize, String issue, String status, String createUser, String num);

    WaterQuality get1(String id);

    public String selectRole(String id);

    WaterQuality selectHave(String issue);
}
