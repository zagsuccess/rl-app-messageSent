package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaReport;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnzhaReportMapper extends Mapper<AnzhaReport> {
    List<AnzhaReport> selectList(@Param("anzhaid") String anzhaid,
                                 @Param("status") String status);

}