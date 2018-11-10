package com.uhope.inspection.service;

import com.uhope.inspection.domain.ScResultFeedback;
import com.uhope.inspection.dto.ScResultFeedbackDTO;
import com.uhope.core.Service;

public interface ResultFeedbackService extends Service<ScResultFeedback, ScResultFeedbackDTO,String> {
    ScResultFeedback selectById(String inspectionId);
}
