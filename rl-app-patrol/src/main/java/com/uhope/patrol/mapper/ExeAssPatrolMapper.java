package com.uhope.patrol.mapper;

import com.uhope.core.Mapper;
import com.uhope.patrol.domain.ExeAssPatrol;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExeAssPatrolMapper extends Mapper<ExeAssPatrol> {

    public List<ExeAssPatrol> selectList(@Param("riverName") String riverName, @Param("region") String region,
                                         @Param("riverQuestion") String riverQuestion, @Param("patorPerson") String patorPerson,
                                         @Param("patrolDateStart") String patrolDateStart, @Param("patrolDateEnd") String patrolDateEnd);

    public List<String> selectGradeWay(@Param("gradeDetailed") String gradeDetailed);

    public List<String> selectDeductMarks(@Param("gradeDetailed") String gradeDetailed);


    public List<String> selectGradeDedetailed();
}
