package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/26
 * @description:
 */
@Data
public class RiverProblemStatistic implements Serializable {
    /**
     * 区名
     */
    private String regionName;

    /**
     * 河段名称
     */
    private String riverName;

    /**
     * 河长姓名
     */
    private String chairmanName;

    /**
     * 问题类型
     */
    private String problemType;
}
