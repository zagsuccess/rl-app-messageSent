package com.uhope.inspection.service.impl;

import com.uhope.inspection.domain.ScSupervise;
import com.uhope.inspection.dto.ScSuperviseDTO;
import com.uhope.inspection.mapper.SuperviseMapper;
import com.uhope.inspection.service.SuperviseService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 *
 * @author a4994
 * @create 2018-11-07 17:45
 */
@Service
public class SuperviseServiceImpl extends AbstractService<ScSupervise, ScSuperviseDTO,String> implements SuperviseService {

    @Resource
    private SuperviseMapper superviseMapper;

    @Override
    public List<String> selectArea() {
        return superviseMapper.selectArea();
    }

    @Override
    public List<ScSupervise> selectById(String inspectionid) {
        return superviseMapper.selectById(inspectionid);
    }
}
