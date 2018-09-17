package com.uhope.rl.resumption.service;

import com.uhope.rl.resumption.dto.statistics.ReachPatrolNumStatisticDTO;
import com.uhope.rl.resumption.dto.statistics.ReachmanPatrolNumStatisticDTO;

import java.util.List;

/**
 * @author: StivenYang
 * @date:2018/7/31 14:32
 * @description:
 */
public interface ResumptionService {

    /**
     * 以区为单位查找各级河长应巡次数
     * @param currentGrade 河长等级
     * @return
     */
    List<ReachPatrolNumStatisticDTO> findReachNeedPatrolNumStatistic(Integer type,String regionId,Integer intervalMonths, Integer currentGrade,Integer pageNumber,Integer pageSize);

    /**
     * 以区为单位查找各级河长已巡次数
     * @param currentGrade 河长等级
     * @return
     */
    List<ReachPatrolNumStatisticDTO> findReachHadPatrolNumStatistic(Integer type,String regionId,String startTime, String endTime, Integer currentGrade,Integer pageNumber,Integer pageSize);

    /**
     * 根据条件查询指定区下面的各个等级河长的巡查情况
     * @param parentRegionId 区级代码
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param currentGrade 当前等级
     * @return
     */
    List<ReachmanPatrolNumStatisticDTO> findPersonPatrolNum(Integer intervalMonths, String parentRegionId, String startTime, String endTime, Integer currentGrade);
}
