package com.uhope.spotcheck.mapper;

import com.uhope.core.Mapper;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import com.uhope.spotcheck.dto.RegionDTO;

import java.util.HashMap;
import java.util.List;

public interface ScSpotcheckProblemMapper extends Mapper<ScSpotcheckProblem> {

    String findRoleIdByName(HashMap map);

    List<RegionDTO> listRegion();
}