package com.uhope.rl.resumption.mapper;

import com.uhope.rl.resumption.dto.statistics.*;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author: StivenYang
 * @date:2018/7/31 14:36
 * @description:
 */
public interface ResumptionMapper {

    /**
     * 以区为单位统计各级河长每月应巡次数
     * @param params
     * @return
     */
    List<ReachPatrolNumStatisticDTO> findNeedPatrolNum(Map<String, Object> params);

    /**
     * 以区为单位统计各级河长每月已巡次数
     * @param params
     * @return
     */
    List<ReachPatrolNumStatisticDTO> findHadPatrolNum(Map<String, Object> params);

    /**
     * 以区为单位统计各级河长每月应巡次数
     * @param params
     * @return
     */
    List<ReachmanPatrolNumStatisticDTO> findPersonPatrolNum(Map<String, Object> params);

    /**
     * 查询指定区下的所有二级分类的统计信息
     * @param params
     * @return
     */
    List<ProblemTypeStatisticDTO> findRegionNumStatistic(Map<String, Object> params);

    /**
     * 找到左右的区
     * @param params
     * @return
     */
    List<ProblemStatisticDTO> findAllRegionNumStatistic(Map<String, Object> params);

    /**
     * 找到本周问题较多河道，取前10条
     * @return 返回对象列表
     */
    List<RiverProblemStatistic> findWithMoreProblemReach();
}
