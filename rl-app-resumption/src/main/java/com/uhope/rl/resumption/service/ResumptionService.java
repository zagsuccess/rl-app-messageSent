package com.uhope.rl.resumption.service;

import com.uhope.rl.resumption.dto.statistics.*;

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
    List<ReachPatrolNumStatisticDTO> findReachNeedPatrolNumStatistic(Integer type,Integer intervalMonths, Integer currentGrade,Integer pageNumber,Integer pageSize);

    /**
     * 以区为单位查找各级河长已巡次数
     * @param currentGrade 河长等级
     * @return
     */
    List<ReachPatrolNumStatisticDTO> findReachHadPatrolNumStatistic(Integer type,String startTime, String endTime, Integer currentGrade,Integer pageNumber,Integer pageSize);

    /**
     * 根据条件查询指定区下面的各个等级河长的巡查情况
     * @param parentRegionId 区级代码
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param currentGrade 当前等级
     * @return
     */
    List<ReachmanPatrolNumStatisticDTO> findPersonPatrolNum(Integer intervalMonths, String parentRegionId, String startTime, String endTime, Integer currentGrade);

    /**
     * 根据regionId查询区域下面的所有二级分类的统计信息
     * @param regionId
     * @return
     */
    List<ProblemTypeStatisticDTO> findRegionTypeNumStatistic(String regionId, int grade, String startTime, String endTime);

    /**
     * 获取所有的区并返回需要的对象
     * @return 返回对象列表
     */
    List<ProblemStatisticDTO> findAllRegionProblemStatistic(String parentId);

    /**
     * 找到本周问题较多河道，取前10条
     * @return 返回对象列表
     */
    List<RiverProblemStatistic> findWithMoreProblemReach();
}
