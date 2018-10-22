package com.uhope.assessment.mapper;

import com.uhope.assessment.domain.AssessGradeType;
import com.uhope.assessment.dto.AssessGradeTypeDTO;
import com.uhope.core.Mapper;

import java.util.HashMap;
import java.util.List;

public interface AssessGradeTypeMapper extends Mapper<AssessGradeType> {

    List<AssessGradeTypeDTO> listDTO(HashMap<String, Object> maps);
}