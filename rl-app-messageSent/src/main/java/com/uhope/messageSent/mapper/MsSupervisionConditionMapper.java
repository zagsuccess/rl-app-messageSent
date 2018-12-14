package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsSupervisionCondition;
import org.apache.ibatis.annotations.Param;

public interface MsSupervisionConditionMapper extends Mapper<MsSupervisionCondition> {
    String selectRole(@Param("id") String id);

    String selectRegion(@Param("regionId") Long regionId);
}
