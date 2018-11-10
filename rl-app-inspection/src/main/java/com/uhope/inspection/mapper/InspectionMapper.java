package com.uhope.inspection.mapper;

import com.uhope.inspection.domain.ScInspection;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InspectionMapper extends Mapper<ScInspection> {
    public String selectRole(@Param("id")String id);


}
