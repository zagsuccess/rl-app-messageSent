package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/12
 * @description:
 */
@Data
public class ReachmanPatrolNumStatisticDTO implements Serializable {

    private static final long serialVersionUID = -3126892029662024681L;

    /** 河长姓名 **/
    private String reachmanName;
    /** 河长级别 **/
    private Integer reachmanLevel;
    /** 应巡次数 **/
    private Integer needReachNum;
    /** 已巡次数 **/
    private Integer hadReachNum;
    /** 达标率 **/
    private Double patrolRate;
}
