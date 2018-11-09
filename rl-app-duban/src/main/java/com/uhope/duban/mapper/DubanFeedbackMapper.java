package com.uhope.duban.mapper;

import com.uhope.core.Mapper;
import com.uhope.duban.domain.ScDubanFeedback;
import com.uhope.template.domain.Template;

public interface DubanFeedbackMapper extends Mapper<ScDubanFeedback> {
    ScDubanFeedback selectFeedback(ScDubanFeedback dubanFeedback);

    String selectRole(String id);
}