package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsSentReports;
import org.apache.ibatis.annotations.Param;

public interface MsSentReportsMapper extends Mapper<MsSentReports> {
    MsSentReports findByReportId(@Param("id") String id,@Param("region") String region);
}