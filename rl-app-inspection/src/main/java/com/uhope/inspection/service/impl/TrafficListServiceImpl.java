package com.uhope.inspection.service.impl;

import com.uhope.inspection.domain.ScTrafficList;
import com.uhope.inspection.dto.ScTrafficListDTO;
import com.uhope.inspection.mapper.TrafficListMapper;
import com.uhope.inspection.service.TrafficListService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述:
 * 通报服务类
 *
 * @author a4994
 * @create 2018-11-07 9:15
 */
@Service
public class TrafficListServiceImpl extends AbstractService<ScTrafficList, ScTrafficListDTO,String> implements TrafficListService {
    @Resource
    private TrafficListMapper trafficListMapper;

    @Override
    public ScTrafficList selectById(String inspectionId) {
        return trafficListMapper.selectById(inspectionId);
    }
}
