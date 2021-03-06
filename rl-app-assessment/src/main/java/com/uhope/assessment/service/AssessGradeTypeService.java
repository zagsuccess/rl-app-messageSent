package com.uhope.assessment.service;

import com.uhope.assessment.domain.AssessGradeType;
import com.uhope.assessment.dto.AssessGradeTypeDTO;
import com.uhope.core.Service;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/22
 * @description:
 */
public interface AssessGradeTypeService extends Service<AssessGradeType, AssessGradeTypeDTO, String> {

    List<AssessGradeTypeDTO> listDTO(String parentId);

}
