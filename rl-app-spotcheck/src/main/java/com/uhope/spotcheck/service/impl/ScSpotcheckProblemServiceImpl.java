package com.uhope.spotcheck.service.impl;

import com.google.common.collect.Maps;
import com.uhope.core.AbstractService;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import com.uhope.spotcheck.dto.RegionDTO;
import com.uhope.spotcheck.dto.ScSpotcheckProblemDTO;
import com.uhope.spotcheck.mapper.ScSpotcheckProblemMapper;
import com.uhope.spotcheck.service.ScSpotcheckProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/25
 * @description:
 */
@Service
public class ScSpotcheckProblemServiceImpl extends AbstractService<ScSpotcheckProblem, ScSpotcheckProblemDTO, String> implements ScSpotcheckProblemService {
    @Resource
    private ScSpotcheckProblemMapper scSpotcheckProblemMapper;

    @Override
    public String findRoleIdByName(String roleName) {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("roleName", roleName);
        return scSpotcheckProblemMapper.findRoleIdByName(map);
    }

    @Override
    public List<RegionDTO> listRegion() {
        return scSpotcheckProblemMapper.listRegion();
    }
}
