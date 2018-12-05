package com.uhope.messageSent.mapper;

import com.uhope.messageSent.domain.ScInspection;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InspectionMapper extends Mapper<ScInspection> {
    public String selectRole(@Param("id")String id);


    List<ScInspection> selectBelong(@Param("sentUnit")String sentUnit);
}
