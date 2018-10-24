package com.uhope.quality.mapper;

import com.uhope.core.Mapper;
import com.uhope.quality.domain.WaterQuality;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaterQualityMapper extends Mapper<WaterQuality> {
    public List<WaterQuality> selectList(@Param("issue") String issue, @Param("status") Integer status,
                                         @Param("createUser") String createUser, @Param("num") Integer num);

    public String selectSHZB();

    public String selectSHBJ();

    WaterQuality get1(String id);
}
