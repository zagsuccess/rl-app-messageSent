package com.uhope.assessment.dto;

import com.uhope.assessment.domain.AssessGradeType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AssessGradeTypeDTO extends AssessGradeType implements Serializable {

    private List<AssessGradeTypeDTO> children;
}