package com.uhope.statistic.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.uhope.statistic.dto.*;
import com.uhope.statistic.mapper.StatisticMapper;
import com.uhope.statistic.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    private StatisticMapper statisticMapper;


    @Override
    public List<RiverStatisticDTO> listRegionName() {
        return statisticMapper.listRegionName();
    }

    @Override
    public List<WaterQualityDTO> listWaterQualityData(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.listWaterQualityData(hashMap);
    }

    @Override
    public List<SuperviseDTO> listSurfaceWaterDTO(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.listSurfaceWaterDTO(hashMap);
    }

    @Override
    public List<SuperviseDTO> queryWaterQualityAssessment(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.queryWaterQualityAssessment(hashMap);
    }

    @Override
    public List<SuperviseDTO> queryWaterSurfaceSanitation(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.queryWaterSurfaceSanitation(hashMap);
    }

    @Override
    public List<SuperviseDTO> queryShorelineManagement(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.queryShorelineManagement(hashMap);
    }

    @Override
    public List<SuperviseDTO> queryPatrolSituation(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.queryPatrolSituation(hashMap);
    }

    @Override
    public List<SuperviseDTO> querySupervisorSatisfaction(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.querySupervisorSatisfaction(hashMap);
    }

    @Override
    public List<SuperviseDTO> querySupervisionPhone(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.querySupervisionPhone(hashMap);
    }

    @Override
    public List<SuperviseDTO> queryNetworkAcceptance(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        return statisticMapper.queryNetworkAcceptance(hashMap);
    }

    @Override
    public List<SuperviseDTO> getSupervisionScore(String date) {
        //社会监督员满意度调查成绩
        List<SuperviseDTO> supervisorSatisfaction = querySupervisorSatisfaction(date);
        //河长制社会监督电话受理事项处置情况
        List<SuperviseDTO> supervisionPhone = querySupervisionPhone(date);
        //网络舆情等网络受理事项处置情况
        List<SuperviseDTO> networkAcceptance = queryNetworkAcceptance(date);

        //设置成绩
        ArrayList<SuperviseDTO> list= Lists.newArrayList();
        for (int i=0; i<supervisorSatisfaction.size(); i++){
            SuperviseDTO superviseDTO = new SuperviseDTO();
            superviseDTO.setRegionId(supervisorSatisfaction.get(i).getRegionId());
            superviseDTO.setRegionName(supervisorSatisfaction.get(i).getRegionName());
            superviseDTO.setGrade(supervisorSatisfaction.get(i).getGrade()+
                    supervisionPhone.get(i).getGrade()+
                    networkAcceptance.get(i).getGrade());
            list.add(superviseDTO);
        }
        return list;
    }

    @Override
    public List<RiverStatisticDTO> getRiverStatistic(String date) {
        //1. 河湖水质考核成绩
        List<SuperviseDTO> waterQualityAssessment = queryWaterQualityAssessment(date);
        //2. 河湖堤岸水面环境卫生考核成绩
        List<SuperviseDTO> waterSurfaceSanitation = queryWaterSurfaceSanitation(date);
        //3. 河湖岸线管理考核成绩
        List<SuperviseDTO> shorelineManagement = queryShorelineManagement(date);
        //4. 河长巡河情况扣分成绩
//        List<SuperviseDTO> patrolSituation = statisticService.queryPatrolSituation(date);

        List<RiverStatisticDTO> list = Lists.newArrayList();
        for (int i=0; i<waterQualityAssessment.size(); i++){
            RiverStatisticDTO riverStatisticDTO = new RiverStatisticDTO();
            riverStatisticDTO.setRegionName(waterQualityAssessment.get(i).getRegionName());
            Double waterQualityAssessmentScore = waterQualityAssessment.get(i).getGrade();
            Double shorelineManagementScore = shorelineManagement.get(i).getGrade();
            Double waterSurfaceSanitationScore = waterSurfaceSanitation.get(i).getGrade();
            Double patrolSituationScore = 0D;
            riverStatisticDTO.setRiverScore(waterQualityAssessmentScore + 42);
            riverStatisticDTO.setShorelineScore(shorelineManagementScore);
            riverStatisticDTO.setHygieneScore(waterSurfaceSanitationScore);
            riverStatisticDTO.setDeductionScore(patrolSituationScore);
            riverStatisticDTO.setResultScore(riverStatisticDTO.getHygieneScore()+riverStatisticDTO.getShorelineScore()+riverStatisticDTO.getRiverScore()-riverStatisticDTO.getDeductionScore());
            list.add(riverStatisticDTO);
        }
        return list;
    }

    @Override
    public List<RegionStatisticDTO> getRegionStatistic(String date) {
        //1. 获得区域地表水环境质量考核成绩
        List<SuperviseDTO> surfaceWaterDTO = listSurfaceWaterDTO(date);
        //2. 获得河湖生态环境质量考核成绩
        List<RiverStatisticDTO> riverStatistic = getRiverStatistic(date);
        //3. 获得社会监督评价考核成绩
        List<SuperviseDTO> supervisionScore = getSupervisionScore(date);

        ArrayList<RegionStatisticDTO> list = Lists.newArrayList();
        for (int i=0; i<surfaceWaterDTO.size(); i++){
            RegionStatisticDTO regionStatisticDTO = new RegionStatisticDTO();
            regionStatisticDTO.setRegionName(supervisionScore.get(i).getRegionName());
            regionStatisticDTO.setSurfaceWaterScore(0.45*surfaceWaterDTO.get(i).getGrade());
            regionStatisticDTO.setRiverStatisticScore(0.45*riverStatistic.get(i).getResultScore());
            regionStatisticDTO.setSupervisionScore(0.1*supervisionScore.get(i).getGrade());
            regionStatisticDTO.setResultScore(regionStatisticDTO.getSupervisionScore()+
                    regionStatisticDTO.getSurfaceWaterScore()+
                    regionStatisticDTO.getRiverStatisticScore());
            list.add(regionStatisticDTO);
        }

        return list;
    }


}
