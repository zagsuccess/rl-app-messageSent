package com.uhope.rl.resumption.service.impl;

import com.uhope.rl.resumption.dto.statistics.ReachPatrolNumStatisticDTO;
import com.uhope.rl.resumption.dto.statistics.ReachmanPatrolNumStatisticDTO;
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
    public List<ReachPatrolNumStatisticDTO> findReachNeedPatrolNumStatistic(Integer type,String regionId,Integer intervalMonths, Integer currentGrade, Integer pageNumber,Integer pageSize) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("type", type);
        params.put("regionId", regionId);
        params.put("intervalMonths", intervalMonths);
        params.put("start", (pageNumber-1)*pageSize);
        params.put("pageSize", pageSize);
        params.put("regionGrade",currentGrade);
        return resumptionMapper.findNeedPatrolNum(params);
    }

    @Override
    public List<ReachPatrolNumStatisticDTO> findReachHadPatrolNumStatistic(Integer type,String regionId,String startTime, String endTime, Integer currentGrade,Integer pageNumber,Integer pageSize) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("startTime", startTime);
        params.put("regionId", regionId);
        params.put("endTime",endTime);
        params.put("start", (pageNumber-1)*pageSize);
        params.put("pageSize", pageSize);
        params.put("type", type);
        params.put("regionGrade",currentGrade);
        return resumptionMapper.findHadPatrolNum(params);
    }

    @Override
    public List<ReachmanPatrolNumStatisticDTO> findPersonPatrolNum(Integer intervalMonths, String regionId, String startTime, String endTime, Integer currentGrade) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("startTime",startTime);
        params.put("regionId", regionId);
        params.put("endTime",endTime);
        params.put("intervalMonths", intervalMonths);
        params.put("regionGrade",currentGrade);
        return resumptionMapper.findPersonPatrolNum(params);
    }
}
