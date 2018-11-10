package com.uhope.inspection.mapper;

import com.uhope.inspection.domain.ScResultFeedback;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

public interface ResultFeedbackMapper extends Mapper<ScResultFeedback> {
    ScResultFeedback selectById(@Param("inspectionId") String inspectionId);
}
