package com.uhope.ancha.service.impl;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaFeedbackDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.ancha.mapper.AnzhaFeedbackMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaFeedbackService;
import com.uhope.ancha.service.TemplateService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaFeedbackServiceImpl extends AbstractService<AnzhaFeedback, AnzhaFeedbackDTO, String> implements AnzhaFeedbackService {
    @Resource
    private AnzhaFeedbackMapper anzhaFeedbackMapper;

    @Override
    public AnzhaFeedback selectOneById(String bulletinid) {
        return anzhaFeedbackMapper.selectOneById(bulletinid);
    }

    @Override
    public void insertAnzhaFeedback(AnzhaFeedback anzhaFeedback) {
        anzhaFeedbackMapper.insertAnzhaFeedback(anzhaFeedback);
    }
}
