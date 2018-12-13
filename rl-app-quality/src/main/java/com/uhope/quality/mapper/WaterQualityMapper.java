package com.uhope.quality.mapper;

import com.uhope.core.Mapper;
import com.uhope.quality.domain.WaterQuality;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaterQualityMapper extends Mapper<WaterQuality> {
    public List<WaterQuality> selectList(@Param("issue") String issue, @Param("status") String status,
                                         @Param("createUser") String createUser, @Param("num") String num);


    WaterQuality get1(String id);

    public String selectRole(@Param("id") String id);

    WaterQuality selectHave(String issue);
}
