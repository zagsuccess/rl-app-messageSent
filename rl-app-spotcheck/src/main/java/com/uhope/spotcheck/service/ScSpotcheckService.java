package com.uhope.spotcheck.service;

import com.uhope.core.Service;
import com.uhope.spotcheck.domain.ScSpotcheck;
import com.uhope.spotcheck.dto.ProblemTypeDTO;
import com.uhope.spotcheck.dto.ScSpotcheckDTO;

import java.util.List;

/**
 * @author: StivenYang
 * @date: 18/10/24
 * @description:
 */
public interface ScSpotcheckService extends Service<ScSpotcheck, ScSpotcheckDTO, String> {
    List<String> findRiverByRegion(String regionName);

    List<ProblemTypeDTO> listProblemType();
}
