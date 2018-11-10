package com.uhope.inspection.mapper;

import com.uhope.inspection.domain.ScProblemHandle;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

public interface ProblemHandleMapper extends Mapper<ScProblemHandle> {
    ScProblemHandle selectById(@Param("inspectionId") String inspectionId);
}
