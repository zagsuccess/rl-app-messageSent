package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.domain.Template;
import com.uhope.core.Mapper;

public interface AnzhaFeedbackMapper extends Mapper<AnzhaFeedback> {
    public AnzhaFeedback selectOneById(String bulletinid);

    public void insertAnzhaFeedback(AnzhaFeedback anzhaFeedback);
}