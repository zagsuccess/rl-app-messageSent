package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsWorkReports;
import org.apache.ibatis.annotations.Param;

public interface MsWorkReportsMapper extends Mapper<MsWorkReports> {
    String selectRegion(@Param("regionId") Long regionId);
}