package com.uhope.assessment.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class AssessGradeType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String gradetype;

    private String gradelevel;

    private String weight;

    private String totalpoints;

    private String sort;

    private String parentid;
}