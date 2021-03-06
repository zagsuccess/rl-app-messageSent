package com.uhope.statistic.service;

import com.uhope.core.Service;
import com.uhope.statistic.domain.AmReachAssess;

import java.util.List;

/**
 * @author: StivenYang
 * @date: 18/11/13
 * @description: 河段考核配置
 */
public interface ReachAssessService extends Service<AmReachAssess, AmReachAssess, String> {

    List<String> regionList();

    List<String> waterQualityRuleList();

    List<String> listSections();
}
