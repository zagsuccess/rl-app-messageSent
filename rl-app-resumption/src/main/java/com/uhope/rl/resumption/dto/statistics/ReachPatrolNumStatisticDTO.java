package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/11
 * @description:
 */
@Data
public class ReachPatrolNumStatisticDTO implements Serializable {

    private static final long serialVersionUID = -3126892029662024681L;

    /**统计维度；1:年；2:月；3:上月*/
    private Integer     type = 2;
    /**区域ID*/
    private String      regionId;
    /**区域名称*/
    private String      regionName;
    /**行政区域等级*/
    private Integer     regionGrade;

    /**区级河长应巡次数**/
    private Integer      countyNeedPatrolNum;
    /**区级河长已巡次数**/
    private Integer     countyHasPatrolNum;
    /**区级河长未巡查次数**/
    private Integer     countyNonePatrolNum;
    /**区级河长达标率**/
    private Double      countyPatrolRate;

    /**镇级河长应巡次数**/
    private Integer      townNeedPatrolNum;
    /**镇级河长已巡次数**/
    private Integer     townHasPatrolNum;
    /**镇级河长未巡查次数**/
    private Integer     townNonePatrolNum;
    /**镇级河长达标率**/
    private Double      townPatrolRate;

    /**村级河长应巡次数**/
    private Integer      villageNeedPatrolNum;
    /**村级河长已巡次数**/
    private Integer     villageHasPatrolNum;
    /**村级河长未巡查次数**/
    private Integer     villageNonePatrolNum;
    /**村级河长达标率**/
    private Double      villagePatrolRate;
    /** 查询开始时间 **/
    private String startTime;
    /** 查询结束时间 **/
    private String endTime;
}
