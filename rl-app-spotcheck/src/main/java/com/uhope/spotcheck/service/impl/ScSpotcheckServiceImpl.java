package com.uhope.spotcheck.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.spotcheck.domain.ScSpotcheck;
import com.uhope.spotcheck.dto.ScSpotcheckDTO;
import com.uhope.spotcheck.mapper.ScSpotcheckMapper;
import com.uhope.spotcheck.service.ScSpotcheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/24
 * @description:
 */
@Service
public class ScSpotcheckServiceImpl extends AbstractService<ScSpotcheck, ScSpotcheckDTO, String> implements ScSpotcheckService {
    @Resource
    private ScSpotcheckMapper scSpotcheckMapper;
}
