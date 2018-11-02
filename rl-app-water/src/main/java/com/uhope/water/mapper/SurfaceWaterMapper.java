package com.uhope.water.mapper;

import com.uhope.core.Mapper;
import com.uhope.water.domain.SurfaceWater;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurfaceWaterMapper extends Mapper<SurfaceWater> {
    public List<SurfaceWater> selectList(@Param("issue") String issue, @Param("status")Integer status,
                                         @Param("createUser") String createUser,@Param("num")Integer num);

    String selectRole(@Param("id") String id);

    SurfaceWater selectHave(String issue);
}
