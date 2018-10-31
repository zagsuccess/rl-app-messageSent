package com.uhope.spotcheck.mapper;

import com.uhope.core.Mapper;
import com.uhope.spotcheck.domain.ScSpotcheck;
import com.uhope.spotcheck.dto.ProblemTypeDTO;

import java.util.List;

public interface ScSpotcheckMapper extends Mapper<ScSpotcheck> {
    List<String> findRiverByRegion(String regionName);

    List<ProblemTypeDTO> listProblemType();
}