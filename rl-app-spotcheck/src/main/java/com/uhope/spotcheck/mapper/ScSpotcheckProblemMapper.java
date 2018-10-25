package com.uhope.spotcheck.mapper;

import com.uhope.core.Mapper;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import java.util.List;

public interface ScSpotcheckProblemMapper extends Mapper<ScSpotcheckProblem> {

    List<String> listSendPerson();

    List<String> listRegion();
}