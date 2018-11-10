package com.uhope.inspection.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.inspection.domain.ExeAssPatrol;
import com.uhope.inspection.dto.ExeAssPatrolDTO;

import java.text.ParseException;
import java.util.List;

public interface ExeAssPatrolService extends Service<ExeAssPatrol, ExeAssPatrolDTO, String> {

    public PageInfo<ExeAssPatrol> list(
            Integer pageNumber,
            Integer pageSize,
            String riverName,
            String region,
            String riverQuestion,
            String patorPerson,
            String patrolDateStart,
            String patrolDateEnd) throws ParseException;
    public List<String> selectGradeWay(String gradeDetailed);

    public List<String> selectGradeDedetailed();

    public List<String> selectDeductMarks(String gradeDetailed);

}
