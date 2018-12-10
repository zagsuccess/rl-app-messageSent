package com.uhope.statistic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
@Data
public class RegionStatisticDTO implements Serializable {

    /**
     * 区名
     */
    private String regionName;

    /**
     * 区域地表水环境质量考核成绩(45%)
     */
    private Double surfaceWaterScore;

    /**
     * 河湖水生态环境质量考核成绩(45%)
     */
    private Double riverStatisticScore;

    /**
     * 社会监督评价考核成绩(10%)
     */
    private Double supervisionScore;

    /**
     * 月度考核总成绩
     */
    private Double resultScore;
}
