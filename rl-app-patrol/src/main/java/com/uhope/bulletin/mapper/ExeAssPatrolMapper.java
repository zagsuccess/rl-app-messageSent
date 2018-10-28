package com.uhope.bulletin.mapper;

import com.uhope.core.Mapper;
import com.uhope.bulletin.domain.ExeAssPatrol;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExeAssPatrolMapper extends Mapper<ExeAssPatrol> {
    public List<ExeAssPatrol> selectList(@Param("riverName") String riverName, @Param("region") String region,
                                         @Param("riverQuestion") String riverQuestion, @Param("patorPerson") String patorPerson,
                                         @Param("patrolDateStart") Date patrolDateStart, @Param("patrolDateEnd") Date patrolDateEnd);
}
