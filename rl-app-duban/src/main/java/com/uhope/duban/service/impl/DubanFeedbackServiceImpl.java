package com.uhope.duban.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.duban.domain.DubanFeedback;
import com.uhope.duban.dto.DubanFeedbackDTO;
import com.uhope.duban.mapper.DubanFeedbackMapper;
import com.uhope.duban.service.DubanFeedbackService;
import com.uhope.template.domain.Template;
import com.uhope.template.dto.TemplateDTO;
import com.uhope.template.mapper.TemplateMapper;
import com.uhope.template.service.TemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class DubanFeedbackServiceImpl extends AbstractService<DubanFeedback, DubanFeedbackDTO, String> implements DubanFeedbackService {
    @Resource
    private DubanFeedbackMapper dubanFeedbackMapper;

    @Override
    public DubanFeedback selectFeedback(DubanFeedback dubanFeedback) {
        return dubanFeedbackMapper.selectFeedback(dubanFeedback);
    }

    @Override
    public String selectRole(String id) {
        return dubanFeedbackMapper.selectRole(id);
    }
}
