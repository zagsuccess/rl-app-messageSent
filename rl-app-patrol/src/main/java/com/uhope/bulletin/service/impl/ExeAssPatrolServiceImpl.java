package com.uhope.bulletin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.bulletin.domain.ExeAssPatrol;
import com.uhope.bulletin.dto.ExeAssPatrolDTO;
import com.uhope.bulletin.mapper.ExeAssPatrolMapper;
import com.uhope.bulletin.service.ExeAssPatrolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
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
    public List<String> selectGradeWay() {
        return exeAssPatrolMapper.selectGradeWay();
    }

    @Override
    public List<String> selectGradeDedetailed() {
        return exeAssPatrolMapper.selectGradeDedetailed();
    }

    @Override
    public List<String> selectDeductMarks(String gradeWay) {
        return exeAssPatrolMapper.selectDeductMarks(gradeWay);
    }
}
