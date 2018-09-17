package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/17
 * @description:
 */
@Data
public class ProblemTypeStatisticDTO implements Serializable {

    /**
     * 已办结数量
     */
    private Integer finishedNum;
    /**
     * 在办数量
     */
    private Integer unfinishedNum;
}
