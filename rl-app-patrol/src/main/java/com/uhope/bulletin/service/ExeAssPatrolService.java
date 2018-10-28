package com.uhope.bulletin.service;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.core.Service;
import com.uhope.bulletin.domain.ExeAssPatrol;
import com.uhope.bulletin.dto.ExeAssPatrolDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;

public interface ExeAssPatrolService extends Service<ExeAssPatrol, ExeAssPatrolDTO, String> {
    public PageInfo<ExeAssPatrol> list(
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam String riverName,
            @RequestParam String region,
            @RequestParam String riverQuestion,
            @RequestParam String patorPerson,
            @RequestParam Date patrolDateStart,
            @RequestParam Date patrolDateEnd) throws ParseException;

}
