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
     * 河湖水质(70%)
     */
    private Double riverScore;

    /**
     * 河湖堤岸水面环境卫生(15%)
     */
    private Double hygieneScore;

    /**
     * 河湖岸线管理(15%)
     */
    private Double shorelineScore;

    /**
     * 河长巡河扣分成绩
     */
    private Double deductionScore;

    /**
     * 河湖水质考核成绩
     */
    private Double resultScore;
}
