package com.uhope.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.inspection.domain.ScInspection;
import com.uhope.inspection.dto.ScInspectionDTO;
import com.uhope.inspection.mapper.InspectionMapper;
import com.uhope.inspection.service.InspectionService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * 描述:
 * 督导服务类
 *
 * @author a4994
 * @create 2018-11-06 14:02
 */
@Service
public class InspectionServiceImpl extends AbstractService<ScInspection, ScInspectionDTO,String> implements InspectionService {
    @Resource
    private InspectionMapper inspectionMapper;


    @Override
    public String selectRole(String id) {
        return inspectionMapper.selectRole(id);
    }




}
