package com.uhope.template.mapper;

import com.uhope.core.Mapper;
import com.uhope.template.domain.SurfaceWater;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurfaceWaterMapper extends Mapper<SurfaceWater> {
    public List<SurfaceWater> selectList(@Param("issue1") String issue1, @Param("issue2") String issue2, @Param("status")Integer status, @Param("createUser") String createUser);
}
