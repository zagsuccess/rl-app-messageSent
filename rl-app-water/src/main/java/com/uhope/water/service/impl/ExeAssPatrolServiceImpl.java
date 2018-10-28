package com.uhope.water.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.water.domain.ExeAssPatrol;
import com.uhope.water.dto.ExeAssPatrolDTO;
import com.uhope.water.mapper.ExeAssPatrolMapper;
import com.uhope.water.service.ExeAssPatrolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ExeAssPatrolServiceImpl extends AbstractService<ExeAssPatrol, ExeAssPatrolDTO,String> implements ExeAssPatrolService {
    @Resource
    private ExeAssPatrolMapper exeAssPatrolMapper;
    @Override
    public PageInfo<ExeAssPatrol> list(Integer pageNumber, Integer pageSize, String riverName, String region, String riverQuestion, String patorPerson, Date patrolDateStart, Date patrolDateEnd) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ExeAssPatrol>list=exeAssPatrolMapper.selectList(riverName,region,riverQuestion,patorPerson,patrolDateStart,patrolDateEnd);
        PageInfo<ExeAssPatrol> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
