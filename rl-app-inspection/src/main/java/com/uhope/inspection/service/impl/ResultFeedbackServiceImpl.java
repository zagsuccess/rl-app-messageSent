package com.uhope.inspection.service.impl;

import com.uhope.inspection.domain.ScResultFeedback;
import com.uhope.inspection.dto.ScResultFeedbackDTO;
import com.uhope.inspection.mapper.ResultFeedbackMapper;
import com.uhope.inspection.service.ResultFeedbackService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述:
 * 结果反馈
 *
 * @author a4994
 * @create 2018-11-06 14:07
 */
@Service
public class ResultFeedbackServiceImpl extends AbstractService<ScResultFeedback, ScResultFeedbackDTO,String> implements ResultFeedbackService {
    @Resource
    private ResultFeedbackMapper resultFeedbackMapper;

    @Override
    public ScResultFeedback selectById(String inspectionId) {
        return resultFeedbackMapper.selectById(inspectionId);
    }
}
