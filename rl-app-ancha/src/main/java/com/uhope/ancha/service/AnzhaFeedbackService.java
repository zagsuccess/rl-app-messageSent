package com.uhope.ancha.service;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.dto.AnzhaFeedbackDTO;
import com.uhope.core.Service;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaFeedbackService extends Service<AnzhaFeedback, AnzhaFeedbackDTO, String> {

    public AnzhaFeedback selectOneById(String bulletinid);

    public void insertAnzhaFeedback(AnzhaFeedback anzhaFeedback);
}
