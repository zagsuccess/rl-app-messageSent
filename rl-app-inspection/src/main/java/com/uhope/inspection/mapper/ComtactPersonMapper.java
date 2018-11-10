package com.uhope.inspection.mapper;

import com.uhope.inspection.domain.ScComtactPerson;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComtactPersonMapper extends Mapper<ScComtactPerson> {
    List<ScComtactPerson> selectById(@Param("inspectionid") String inspectionid);
}
