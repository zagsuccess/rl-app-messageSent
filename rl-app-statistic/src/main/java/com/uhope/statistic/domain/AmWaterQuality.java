package com.uhope.statistic.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class AmWaterQuality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    private String assessRule;

    private String paramType;

    private String boundaryValue;

    private String leftMatch;

    private String rightMatch;

    private String deductValue;

    private Integer sortOrder;

    private String creator;

    private Date createTime;
}