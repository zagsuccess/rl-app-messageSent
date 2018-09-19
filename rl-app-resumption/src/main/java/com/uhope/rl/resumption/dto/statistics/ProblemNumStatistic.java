package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/19
 * @description: 首页界面问题较多河道统计DTO
 */
@Data
public class ProblemNumStatistic implements Serializable {
    /**
     * 河道段
     */
    private String reachName;
    /**
     * 问题数
     */
    private Integer problemNum;
    /**
     * 河长
     */
    private String reachManName;
    /**
     * 河长级别
     */
    private Integer grade;
}
