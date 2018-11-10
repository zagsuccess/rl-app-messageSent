package com.uhope.statistic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
@Data
public class RiverStatisticDTO implements Serializable {
    /**
     * 考核区域
     */
    private String regionName;

    /**
     * 河湖水质评分
     */
    private Double riverScore;

    /**
     * 河湖堤岸水面环境卫生
     */
    private Double hygieneScore;

    /**
     * 河湖岸线管理
     */
    private Double shorelineScore;

    /**
     * 河长巡河扣分
     */
    private Double deductionScore;

    /**
     * 考核结果
     */
    private Double resultScore;
}
