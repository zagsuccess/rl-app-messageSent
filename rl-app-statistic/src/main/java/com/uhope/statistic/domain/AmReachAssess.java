package com.uhope.statistic.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 河段考核
 */
@Data
public class AmReachAssess implements Serializable {
    /**
     * 河段考核配置
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    private String id;

    /**
     * 区域名
     */
    private String regionName;

    /**
     * 河道名称
     */
    private String riverName;

    /**
     * 河段名称
     */
    private String reachName;

    /**
     * 入境断面
     */
    private String inputSection;

    /**
     * 出境断面
     */
    private String outputSection;

    /**
     * 月度目标水质类别
     */
    private Integer targetWaterQuality;

    /**
     * 年度目标水质类别
     */
    private Integer yearTargetWaterQuality;

    /**
     * 是否含有污水处理厂
     */
    private Integer isSewageFactory;

    /**
     * 是否水质检测补偿
     */
    private Integer isCompensation;

    /**
     * 水质评分规则
     */
    private String scoringRules;

    /**
     * 是否水质考核
     */
    private Integer isWaterQualityAssess;
}