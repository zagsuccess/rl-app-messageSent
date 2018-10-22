package com.uhope.assessment.dto;

import com.uhope.assessment.domain.AssessGradeType;

import java.util.List;

public class AssessGradeTypeDTO extends AssessGradeType {

    private List<AssessGradeTypeDTO> children;

    /**
     * @return children
     */
    public List<AssessGradeTypeDTO> getChildren() {
        return children;
    }

    /**
     * @param children
     */
    public void setChildren(List<AssessGradeTypeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AssessGradeTypeDTO{" +
                "children=" + children +
                '}';
    }
}