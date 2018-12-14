package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsTownstreetCondition;
import org.apache.ibatis.annotations.Param;

public interface MsTownstreetConditionMapper extends Mapper<MsTownstreetCondition> {
    String selectRole(@Param("id") String id);

    String selectRegion(@Param("regionId") Long regionId);

    String selectGrade(@Param("regionId") Long regionId);
}