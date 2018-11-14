package com.uhope.statistic.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class AmWaterAssess implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String ruleName;

    private String paramType;

    private String boundaryValue;

    private String matchRule;

    private String waterQualityType;

    private String scoreCoefficient;

    private String factorCoefficient;

    private Integer sortOrder;

    private String creator;

    private Date createTime;
}