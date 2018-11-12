package com.uhope.inspection.service.impl;

import com.uhope.inspection.domain.ScProblemHandle;
import com.uhope.inspection.dto.ScProblemHandleDTO;
import com.uhope.inspection.mapper.ProblemHandleMapper;
import com.uhope.inspection.service.ProblemHandleService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述:
 * 问题处理表
 *
 * @author a4994
 * @create 2018-11-06 14:05
 */
@Service
public class ProblemHandleServiceImpl extends AbstractService<ScProblemHandle, ScProblemHandleDTO,String> implements ProblemHandleService {
    @Resource
    private ProblemHandleMapper problemHandleMapper;

    @Override
    public ScProblemHandle selectById(String inspectionId) {
        return problemHandleMapper.selectById(inspectionId);
    }
}
