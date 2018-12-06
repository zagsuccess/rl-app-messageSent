package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsMeetingCondition;
import org.apache.ibatis.annotations.Param;

public interface MsMeetingConditionMapper extends Mapper<MsMeetingCondition> {
    String selectRole(@Param("id") String id);

    String selectRegion(@Param("regionId") Long regionId);
}