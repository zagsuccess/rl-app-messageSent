package com.uhope.rl.resumption.service.impl;

import com.uhope.rl.resumption.dto.statistics.*;
import com.uhope.rl.resumption.mapper.ResumptionMapper;
import com.uhope.rl.resumption.service.ResumptionService;
import com.uhope.rl.resumption.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: StivenYang
 * @date:2018/7/31 14:35
 * @description:
 */
@Service
public class ResumptionServiceImpl implements ResumptionService {

    @Autowired
    private ResumptionMapper resumptionMapper;

    @Override
    public List<ReachPatrolNumStatisticDTO> findReachNeedPatrolNumStatistic(Integer type,Integer intervalMonths, Integer currentGrade, Integer pageNumber,Integer pageSize, Integer grade, Long regionId) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("type", type);
        params.put("intervalMonths", intervalMonths);
        params.put("start", (pageNumber-1)*pageSize);
        params.put("pageSize", pageSize);
        params.put("regionGrade",currentGrade);
        params.put("grade",grade+1);
        params.put("regionId", regionId);
        return resumptionMapper.findNeedPatrolNum(params);
    }

    @Override
    public List<ReachPatrolNumStatisticDTO> findReachHadPatrolNumStatistic(Integer type,String startTime, String endTime, Integer currentGrade,Integer pageNumber,Integer pageSize, Integer grade, Long regionId) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("startTime", startTime);
        params.put("endTime",endTime);
        params.put("start", (pageNumber-1)*pageSize);
        params.put("pageSize", pageSize);
        params.put("type", type);
        params.put("grade",grade+1);
        params.put("regionGrade",currentGrade);
        params.put("regionId", regionId);
        return resumptionMapper.findHadPatrolNum(params);
    }

    @Override
    public List<ReachmanPatrolNumStatisticDTO> findPersonPatrolNum(Integer intervalMonths, String regionId, String startTime, String endTime, Integer currentGrade, Integer userGrade) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("startTime",startTime);
        params.put("regionId", regionId);
        params.put("endTime",endTime);
        params.put("intervalMonths", intervalMonths);
        params.put("regionGrade",currentGrade);
        params.put("userGrade", userGrade+1);
        return resumptionMapper.findPersonPatrolNum(params);
    }

    @Override
    public List<ProblemTypeStatisticDTO> findRegionTypeNumStatistic(String regionId, int grade, String startTime, String endTime) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("regionId", regionId);
        params.put("grade", grade);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        return resumptionMapper.findRegionNumStatistic(params);
    }

    @Override
    public List<ProblemStatisticDTO> findAllRegionProblemStatistic(String parentId) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("parentId", parentId);
        return resumptionMapper.findAllRegionNumStatistic(params);
    }

    @Override
    public List<RiverProblemStatistic> findWithMoreProblemReach() {
        return resumptionMapper.findWithMoreProblemReach();
    }
}
