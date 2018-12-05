package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.domain.ScInspection;
import com.uhope.messageSent.dto.ScInspectionDTO;
import com.uhope.messageSent.mapper.InspectionMapper;
import com.uhope.messageSent.service.InspectionService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public List<ScInspection> selectBelong(String sentUnit) {
        return inspectionMapper.selectBelong(sentUnit);
    }


}
