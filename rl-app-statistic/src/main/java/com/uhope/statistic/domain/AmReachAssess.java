package com.uhope.statistic.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class AmReachAssess implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String riverName;

    private String reachName;

    private String inputSection;

    private String outputSection;

    private String targetWaterQuality;

    private String yearTargetWaterQuality;

    private Integer isSewageFactory;

    private Integer isCompensation;

    private String scoringRules;

    private Integer isWaterQualityAssess;
}