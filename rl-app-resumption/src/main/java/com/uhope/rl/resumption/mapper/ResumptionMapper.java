package com.uhope.rl.resumption.mapper;

import com.uhope.rl.resumption.dto.statistics.ProblemTypeStatisticDTO;
import com.uhope.rl.resumption.dto.statistics.ReachPatrolNumStatisticDTO;
import com.uhope.rl.resumption.dto.statistics.ReachmanPatrolNumStatisticDTO;
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

    @MapKey("regionId")
    Map<String, ProblemTypeStatisticDTO> getTypelist(Map<String, Object> params);
}
