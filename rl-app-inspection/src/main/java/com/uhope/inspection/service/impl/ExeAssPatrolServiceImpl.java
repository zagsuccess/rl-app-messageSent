package com.uhope.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.inspection.domain.ExeAssPatrol;
import com.uhope.inspection.dto.ExeAssPatrolDTO;
import com.uhope.inspection.mapper.ExeAssPatrolMapper;
import com.uhope.inspection.service.ExeAssPatrolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * 描述:
 * 巡查管理服务实现类
 *
 * @author a4994
 * @create 2018-10-27 16:33
 */
@Service
public class ExeAssPatrolServiceImpl extends AbstractService<ExeAssPatrol, ExeAssPatrolDTO, String> implements ExeAssPatrolService {
    @Resource
    private ExeAssPatrolMapper exeAssPatrolMapper;




    @Override
    public PageInfo<ExeAssPatrol> list(Integer pageNumber, Integer pageSize, String riverName, String region, String riverQuestion, String patorPerson, String patrolDateStart, String patrolDateEnd) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        List<ExeAssPatrol> list = exeAssPatrolMapper.selectList(riverName, region, riverQuestion, patorPerson, patrolDateStart, patrolDateEnd);
        PageInfo<ExeAssPatrol> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<String> selectGradeWay(String gradeDetailed) {
        return exeAssPatrolMapper.selectGradeWay(gradeDetailed);
    }

    @Override
    public List<String> selectGradeDedetailed() {
        return exeAssPatrolMapper.selectGradeDedetailed();
    }

    @Override
    public List<String> selectDeductMarks(String gradeDetailed) {
        return exeAssPatrolMapper.selectDeductMarks(gradeDetailed);
    }
}
