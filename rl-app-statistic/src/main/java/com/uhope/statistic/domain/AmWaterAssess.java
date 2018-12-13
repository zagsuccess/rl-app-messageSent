package com.uhope.statistic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

@Data
public class AmWaterAssess implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    /**
     * id
     */
    private String id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 最大值
     */
    private Double maxValue;

    /**
     * 最小值
     */
    private Double minValue;

    /**
     * 水质类别
     */
    private Integer waterQualityType;

    /**
     * 得分系数
     */
    private Double scoreCoefficient;

    /**
     * 修正系数
     */
    private Double factorCoefficient;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建人
     */
    transient private String creator;

    /**
     * 创建时间
     */
    transient private Date createTime;
}