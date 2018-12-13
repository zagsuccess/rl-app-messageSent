package com.uhope.statistic.service;

import com.uhope.statistic.dto.*;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
public interface StatisticService {

    /**
     * 查取所有的区名称
     * @return
     */
    List<RiverStatisticDTO> listRegionName();

    /**
     * 根据期号查取水质数据
     * @param date
     * @return
     */
    List<WaterQualityDTO> listWaterQualityData(String date);

    /**
     * 1. 根据期号查询各区区域地表水成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> listSurfaceWaterDTO(String date);

    /**
     * 2.1. 河湖水质考核成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> queryWaterQualityAssessment(String date);

    /**
     * 2.2. 河湖堤岸水面环境卫生考核成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> queryWaterSurfaceSanitation(String date);

    /**
     * 2.3. 河湖岸线管理考核成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> queryShorelineManagement(String date);

    /**
     * 2.4. 河长巡河情况扣分成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> queryPatrolSituation(String date);

    /**
     * 3.1. 社会监督员满意度调查成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> querySupervisorSatisfaction(String date);

    /**
     * 3.2.1. 河长制社会监督电话受理事项处置情况
     * @param date
     * @return
     */
    List<SuperviseDTO> querySupervisionPhone(String date);

    /**
     * 3.2.2. 网络舆情等网络受理事项处置情况
     * @param date
     * @return
     */
    List<SuperviseDTO> queryNetworkAcceptance(String date);

    /**
     * 获得社会监督评价考核成绩(45%)
     * @param date
     * @return
     */
    List<SuperviseDTO> getSupervisionScore(String date);

    /**
     * 获得河湖水质排名统计数据
     * @param date
     * @return
     */
    List<RiverStatisticDTO> getRiverStatistic(String date);

    /**
     * 获得区考核排名统计数据
     * @param date
     * @return
     */
    List<RegionStatisticDTO> getRegionStatistic(String date);

    /**
     * 获得水质数据得分
     * @param date
     */
    List<SuperviseDTO> getWaterQualityStatistic(String date);
    /**
     * 根据k值列表统计得到n值列表
     * @param paramType 参数类型
     * @param ruleName 评分规则类型
     * @param value k值
     * @return
     */
    Double queryNByK(String ruleName, String paramType, Double value);

    /**
     * 根据平均浓度c值计算水质等级
     * @param ruleName 参数类型
     * @param paramType 评分规则类型
     * @param value 起止断面平均浓度
     * @return
     */
    Integer queryLevelByC(String ruleName, String paramType, Double value);

    /**
     * 查询k值列表
     * @param date
     * @return
     */
    List<KValueDTO> queryKValueList(String date);

    /**
     * 查询河段浓度列表
     * @param date
     * @return
     */
    List<KValueDTO> queryCValueList(String date);
}
