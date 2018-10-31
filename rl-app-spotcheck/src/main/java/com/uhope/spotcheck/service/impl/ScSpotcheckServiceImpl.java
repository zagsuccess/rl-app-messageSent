package com.uhope.spotcheck.service.impl;

import com.google.common.collect.Maps;
import com.uhope.core.AbstractService;
import com.uhope.spotcheck.domain.ScSpotcheck;
import com.uhope.spotcheck.dto.ProblemTypeDTO;
import com.uhope.spotcheck.dto.ScSpotcheckDTO;
import com.uhope.spotcheck.mapper.ScSpotcheckMapper;
import com.uhope.spotcheck.service.ScSpotcheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/24
 * @description:
 */
@Service
public class ScSpotcheckServiceImpl extends AbstractService<ScSpotcheck, ScSpotcheckDTO, String> implements ScSpotcheckService {
    @Resource
    private ScSpotcheckMapper scSpotcheckMapper;

    @Override
    public List<String> findRiverByRegion(String regionName) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("regionName", regionName);
        return scSpotcheckMapper.findRiverByRegion(regionName);
    }

    @Override
    public List<ProblemTypeDTO> listProblemType() {
        return scSpotcheckMapper.listProblemType();
    }
}
