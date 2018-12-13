package com.uhope.statistic.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.uhope.statistic.constants.Constants;
import com.uhope.statistic.dto.*;
import com.uhope.statistic.mapper.StatisticMapper;
import com.uhope.statistic.service.StatisticService;
import com.uhope.statistic.utils.WaterQualityStatisticUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        //1.1. 河湖水质考核成绩，【河湖区界断面出入境水体水质变化情况】
        List<SuperviseDTO> waterQualityAssessment = queryWaterQualityAssessment(date);
        //1.2. 河湖区界断面出入境水体水质变化情况成绩
        List<SuperviseDTO> waterQualityStatistic = getWaterQualityStatistic(date);
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
            //水质考核成绩，不包括断面考核成绩
            Double waterQualityAssessmentScore = waterQualityAssessment.get(i).getGrade();
            Double shorelineManagementScore = shorelineManagement.get(i).getGrade();
            Double waterSurfaceSanitationScore = waterSurfaceSanitation.get(i).getGrade();
            Double sectionScore = waterQualityStatistic.get(i).getGrade();
            Double patrolSituationScore = 0D;
            riverStatisticDTO.setRiverScore(waterQualityAssessmentScore + sectionScore);
            riverStatisticDTO.setShorelineScore(shorelineManagementScore);
            riverStatisticDTO.setHygieneScore(waterSurfaceSanitationScore);
            riverStatisticDTO.setDeductionScore(patrolSituationScore);
            riverStatisticDTO.setResultScore(0.15*riverStatisticDTO.getHygieneScore()+0.15*riverStatisticDTO.getShorelineScore()+0.7*riverStatisticDTO.getRiverScore()-riverStatisticDTO.getDeductionScore());
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
            regionStatisticDTO.setSurfaceWaterScore(surfaceWaterDTO.get(i).getGrade());
            regionStatisticDTO.setRiverStatisticScore(riverStatistic.get(i).getResultScore());
            regionStatisticDTO.setSupervisionScore(supervisionScore.get(i).getGrade());
            regionStatisticDTO.setResultScore(0.1*regionStatisticDTO.getSupervisionScore()+
                    0.45*regionStatisticDTO.getSurfaceWaterScore()+
                    0.45*regionStatisticDTO.getRiverStatisticScore());
            list.add(regionStatisticDTO);
        }

        return list;
    }

    @Override
    public List<SuperviseDTO> getWaterQualityStatistic(String date) {
        //1. 查到k值列表
        List<KValueDTO> list = queryKValueList(date);
        HashMap<String, Double> sumMap = Maps.newHashMap();
        HashMap<String, Integer> countMap = Maps.newHashMap();
        for (KValueDTO kValueDTO : list) {
            //2. 根据k值算出n值
            kValueDTO.setNCodmn(queryNByK(kValueDTO.getScoringRules(), Constants.CODMN, kValueDTO.getKCodmn()));
            kValueDTO.setNDo(queryNByK(kValueDTO.getScoringRules(), Constants.DO, kValueDTO.getKDo()));
            kValueDTO.setNTp(queryNByK(kValueDTO.getScoringRules(), Constants.TP, kValueDTO.getKTp()));
            kValueDTO.setNNh4n(queryNByK(kValueDTO.getScoringRules(), Constants.NH4N, kValueDTO.getKNh4n()));
            //3. 根据浓度c值算出当前水质等级
            kValueDTO.setLevelCodmn(queryLevelByC(kValueDTO.getScoringRules(), Constants.CODMN, kValueDTO.getCCodmn()));
            kValueDTO.setLevelDo(queryLevelByC(kValueDTO.getScoringRules(), Constants.DO, kValueDTO.getCDo()));
            kValueDTO.setLevelNh4n(queryLevelByC(kValueDTO.getScoringRules(), Constants.NH4N, kValueDTO.getCNh4n()));
            kValueDTO.setLevelTp(queryLevelByC(kValueDTO.getScoringRules(), Constants.TP, kValueDTO.getCTp()));
            //4. 根据当前水质等级得出f
            kValueDTO.setFCodmn(WaterQualityStatisticUtil.calcF(kValueDTO.getLevelCodmn(), kValueDTO.getTargetWaterQuality()));
            kValueDTO.setFDo(WaterQualityStatisticUtil.calcF(kValueDTO.getLevelDo(), kValueDTO.getTargetWaterQuality()));
            kValueDTO.setFNh4n(WaterQualityStatisticUtil.calcF(kValueDTO.getLevelNh4n(), kValueDTO.getTargetWaterQuality()));
            kValueDTO.setFTp(WaterQualityStatisticUtil.calcF(kValueDTO.getLevelTp(), kValueDTO.getTargetWaterQuality()));
            //5. 计算最终得分
            kValueDTO = WaterQualityStatisticUtil.calcN(kValueDTO);
            //这里利用两个map统计各个区河道考核的总分以及各个区所拥有的河道数
            if (sumMap.containsKey(kValueDTO.getRegionName())){
                sumMap.put(kValueDTO.getRegionName(), sumMap.get(kValueDTO.getRegionName())+kValueDTO.getResult());
                countMap.put(kValueDTO.getRegionName(), countMap.get(kValueDTO.getRegionName())+1);
            }else{
                sumMap.put(kValueDTO.getRegionName(), kValueDTO.getResult());
                countMap.put(kValueDTO.getRegionName(), 1);
            }
        }
        //得到考核河道所在区的平均成绩
        for (String s : sumMap.keySet()) {
            sumMap.put(s, sumMap.get(s)/countMap.get(s));
        }
        //初始化一个DTO列表用来存放水质考核成绩
        List<SuperviseDTO> resultList = statisticMapper.listSuperviseDTO();
        for (SuperviseDTO superviseDTO : resultList) {
            if (sumMap.get(superviseDTO.getRegionName()) != null){
                superviseDTO.setGrade(sumMap.get(superviseDTO.getRegionName()));
            }
        }

        return resultList;
    }

    @Override
    public Double queryNByK(String ruleName, String paramType, Double value) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("ruleName", ruleName);
        hashMap.put("paramType", paramType);
        hashMap.put("value", value);
        return statisticMapper.queryNByK(hashMap);
    }

    @Override
    public Integer queryLevelByC(String ruleName, String paramType, Double value) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("ruleName", ruleName);
        hashMap.put("paramType", paramType);
        hashMap.put("value", value);
        return statisticMapper.queryLevelByC(hashMap);
    }

    @Override
    public List<KValueDTO> queryKValueList(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        List<KValueDTO> list = statisticMapper.queryKValueList(hashMap);
        return list;
    }

    @Override
    public List<KValueDTO> queryCValueList(String date) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("date", date);
        List<KValueDTO> list = statisticMapper.queryCValueList(hashMap);
        return list;
    }



}
