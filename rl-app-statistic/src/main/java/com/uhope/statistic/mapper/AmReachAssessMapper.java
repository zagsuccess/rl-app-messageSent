package com.uhope.statistic.mapper;

import com.uhope.core.Mapper;
import com.uhope.statistic.domain.AmReachAssess;

import java.util.List;

public interface AmReachAssessMapper extends Mapper<AmReachAssess> {

    List<String> regionList();

    List<String> waterQualityRuleList();
}