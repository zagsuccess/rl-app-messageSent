package com.uhope.statistic.service;

import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.SuperviseDTO;
import com.uhope.statistic.dto.SurfaceWaterDTO;
import com.uhope.statistic.dto.WaterQualityDTO;

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
     * 根据期号查取所有地表水成绩
     * @param date
     * @return
     */
    List<SurfaceWaterDTO> listSurfaceWaterDTO(String date);

    /**
     * 根据时间查取所有的社会监督满意度考核成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> listSuperviseDTOs(String date);

    /**
     * 根据期号查取网络舆情扣分成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> listSuperviseSentiment(String date);

    /**
     * 根据期号查取社会监督电话扣分成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> listSuperviseTelephone(String date);

    /**
     * 根据期号查取河湖堤岸水面环境卫生扣分成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> listWaterSanitation(String date);

    /**
     * 根据期号查取河湖岸线管理扣分成绩
     * @param date
     * @return
     */
    List<SuperviseDTO> listShoreLineManage(String date);

    /**
     * 根据期号查取河长巡河情况扣分项
     * @param date
     * @return
     */
    List<SuperviseDTO> listRiverPatrol(String date);


}
