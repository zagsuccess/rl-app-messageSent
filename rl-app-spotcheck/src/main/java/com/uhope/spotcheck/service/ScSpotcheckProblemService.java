package com.uhope.spotcheck.service;

import com.uhope.core.Service;
import com.uhope.spotcheck.domain.ScSpotcheckProblem;
import com.uhope.spotcheck.dto.ScSpotcheckProblemDTO;

import java.util.List;

/**
 * @author: StivenYang
 * @date: 18/10/25
 * @description:
 */
public interface ScSpotcheckProblemService extends Service<ScSpotcheckProblem, ScSpotcheckProblemDTO, String> {

    /**
     * 查找所有的市级河长
     * @return
     */
    List<String> listSendPerson();

    /**
     * 查找所有的区
     * @return
     */
    List<String> listRegion();
}
