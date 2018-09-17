package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/17
 * @description: 问题统计DTO
 */
@Data
public class ProblemStatisticDTO implements Serializable {
    /**
     * 曲名
     */
    private String regionName;
    /**
     * 分类列表
     */
    private List<ProblemTypeStatisticDTO> list;
    /**
     * 各区已办结合计
     */
    private Integer finishedSumNum;
    /**
     * 各区在办合计
     */
    private Integer unfinishedSumNum;
    /**
     * 所有区已办结合计
     */
    private Integer allFinishedSumNum;
    /**
     * 所有区已办结合计
     */
    private Integer allUnfinishedSumNum;
}
