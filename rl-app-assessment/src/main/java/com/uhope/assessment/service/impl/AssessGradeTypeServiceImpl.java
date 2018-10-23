package com.uhope.assessment.service.impl;

import com.google.common.collect.Maps;
import com.uhope.assessment.domain.AssessGradeType;
import com.uhope.assessment.dto.AssessGradeTypeDTO;
import com.uhope.assessment.mapper.AssessGradeTypeMapper;
import com.uhope.assessment.service.AssessGradeTypeService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/22
 * @description:
 */
@Service
public class AssessGradeTypeServiceImpl  extends AbstractService<AssessGradeType, AssessGradeTypeDTO, String> implements AssessGradeTypeService {

    @Resource
    private AssessGradeTypeMapper assessGradeTypeMapper;

    @Override
    public List<AssessGradeTypeDTO> listDTO(String parentId) {
        HashMap<String, Object> maps = Maps.newHashMap();
        maps.put("parentId", parentId);
        return assessGradeTypeMapper.listDTO(maps);
    }
}
