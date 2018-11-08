package com.uhope.duban.service;

import com.uhope.core.Service;
import com.uhope.duban.domain.DubanFeedback;
import com.uhope.duban.dto.DubanFeedbackDTO;
import com.uhope.template.domain.Template;
import com.uhope.template.dto.TemplateDTO;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface DubanFeedbackService extends Service<DubanFeedback, DubanFeedbackDTO, String> {

    DubanFeedback selectFeedback(DubanFeedback dubanFeedback);

    String selectRole(String id);
}
