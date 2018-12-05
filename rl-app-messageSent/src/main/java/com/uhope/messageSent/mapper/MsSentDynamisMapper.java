package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsSentDynamis;
import org.apache.ibatis.annotations.Param;

public interface MsSentDynamisMapper extends Mapper<MsSentDynamis> {
    MsSentDynamis findByReportId(@Param("id") String id,@Param("sentUnit") String sentUnit);
}