package com.uhope.water.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.water.domain.ExeAssPatrol;
import com.uhope.water.dto.ExeAssPatrolDTO;

import java.util.Date;

public interface ExeAssPatrolService extends Service<ExeAssPatrol, ExeAssPatrolDTO, String> {
    public PageInfo<ExeAssPatrol> list(Integer pageNumber, Integer pageSize, String riverName, String region, String riverQuestion, String patorPerson, Date patrolDateStart, Date patrolDateEnd);

}
