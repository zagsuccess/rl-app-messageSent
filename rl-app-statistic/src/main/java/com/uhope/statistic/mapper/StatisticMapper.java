package com.uhope.statistic.mapper;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.SuperviseDTO;
import com.uhope.statistic.dto.SurfaceWaterDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
public interface StatisticMapper {

    /**
     * 查询16个区名
     * @return
     */
    List<RiverStatisticDTO> listRegionName();

    /**
     * 根据期号查询各区水质数据列表
     * @param hashMap
     * @return
     */
    List<WaterQualityDTO> listWaterQualityData(HashMap<String, Object> hashMap);

    /**
     * 1. 根据期号查询各区区域地表水成绩
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> listSurfaceWaterDTO(HashMap<String, Object> hashMap);

    /**
     * 2.1. 河湖水质考核成绩
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> queryWaterQualityAssessment(HashMap<String, Object> hashMap);

    /**
     * 2.2. 河湖堤岸水面环境卫生考核成绩
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> queryWaterSurfaceSanitation(HashMap<String, Object> hashMap);

    /**
     * 2.3. 河湖岸线管理考核成绩
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> queryShorelineManagement(HashMap<String, Object> hashMap);

    /**
     * 2.4. 河长巡河情况扣分成绩
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> queryPatrolSituation(HashMap<String, Object> hashMap);

    /**
     * 3.1. 社会监督员满意度调查成绩
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> querySupervisorSatisfaction(HashMap<String, Object> hashMap);

    /**
     * 3.2.1. 河长制社会监督电话受理事项处置情况
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> querySupervisionPhone(HashMap<String, Object> hashMap);

    /**
     * 3.2.2. 网络舆情等网络受理事项处置情况
     * @param hashMap
     * @return
     */
    List<SuperviseDTO> queryNetworkAcceptance(HashMap<String, Object> hashMap);
}
