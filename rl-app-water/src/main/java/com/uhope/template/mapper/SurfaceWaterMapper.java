package com.uhope.template.mapper;

import com.uhope.core.Mapper;
import com.uhope.template.domain.SurfaceWater;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurfaceWaterMapper extends Mapper<SurfaceWater> {
    public List<SurfaceWater> selectList(@Param("issue") String issue, @Param("status")Integer status,
                                         @Param("createUser") String createUser,@Param("num")Integer num);

    public String selectSHZB();

    public String selectSHBJ();
}
