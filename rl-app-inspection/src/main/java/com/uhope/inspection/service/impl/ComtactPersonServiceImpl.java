package com.uhope.inspection.service.impl;

import com.uhope.inspection.domain.ScComtactPerson;
import com.uhope.inspection.dto.ScComtactPersonDTO;
import com.uhope.inspection.mapper.ComtactPersonMapper;
import com.uhope.inspection.service.ComtactPersonService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 *
 * @author a4994
 * @create 2018-11-07 17:43
 */
@Service
public class ComtactPersonServiceImpl extends AbstractService<ScComtactPerson, ScComtactPersonDTO,String> implements ComtactPersonService {

    @Resource
    ComtactPersonMapper comtactPersonMapper;
    @Override
    public List<ScComtactPerson> selectById(String inspectionid) {
        return comtactPersonMapper.selectById(inspectionid);
    }
}
