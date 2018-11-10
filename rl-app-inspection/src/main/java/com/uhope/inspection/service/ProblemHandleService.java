package com.uhope.inspection.service;

import com.uhope.inspection.domain.ScProblemHandle;
import com.uhope.inspection.dto.ScProblemHandleDTO;
import com.uhope.core.Service;

public interface ProblemHandleService extends Service<ScProblemHandle, ScProblemHandleDTO,String> {

    ScProblemHandle selectById(String inspectionId);
}
