package com.uhope.spotcheck.service;

import com.uhope.core.Service;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import com.uhope.spotcheck.dto.RegionDTO;
import com.uhope.spotcheck.dto.ScSpotcheckProblemDTO;

import java.util.List;

/**
 * @author: StivenYang
 * @date: 18/10/25
 * @description:
 */
public interface ScSpotcheckProblemService extends Service<ScSpotcheckProblem, ScSpotcheckProblemDTO, String> {

    /**
     * 根据角色名查找对应的角色ID
     * @return
     */
    String findRoleIdByName(String roleName);

    /**
     * 查找所有的区
     * @return
     */
    List<RegionDTO> listRegion();
}
