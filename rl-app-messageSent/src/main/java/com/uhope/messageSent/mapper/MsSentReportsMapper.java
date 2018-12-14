package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsSentReports;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MsSentReportsMapper extends Mapper<MsSentReports> {
    MsSentReports findByReportId(@Param("id") String id,@Param("region") String region);

    MsSentReports findByWorkId(@Param("id") String id);

    List<MsSentReports> selectByRegion(@Param("region")String region);
}