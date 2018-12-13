package com.uhope.statistic.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class AmWaterQuality implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT REPLACE(UUID(),\"-\",\"\")")
    /**
     * id
     */
    private String id;

    /**
     * 评分规则
     */
    private String assessRule;

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
     * 扣分数
     */
    private Double deductValue;

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