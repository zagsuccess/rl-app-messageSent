package com.uhope.template.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.template.domain.SurfaceWater;
import com.uhope.template.dto.SurfaceWaterDTO;

public interface SurfaceWaterService extends Service<SurfaceWater, SurfaceWaterDTO,String> {
    public PageInfo<SurfaceWater> list(Integer pageNumber, Integer pageSize, String issue, Integer status, String createUser,Integer num);

    public String selectSHZB();

    public String selectSHBJ();
}
