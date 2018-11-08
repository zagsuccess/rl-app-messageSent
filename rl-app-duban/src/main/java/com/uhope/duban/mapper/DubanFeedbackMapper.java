package com.uhope.duban.mapper;

import com.uhope.core.Mapper;
import com.uhope.duban.domain.DubanFeedback;
import com.uhope.template.domain.Template;

public interface DubanFeedbackMapper extends Mapper<DubanFeedback> {
    DubanFeedback selectFeedback(DubanFeedback dubanFeedback);

    String selectRole(String id);
}