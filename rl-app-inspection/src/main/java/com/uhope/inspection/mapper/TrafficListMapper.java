package com.uhope.inspection.mapper;

import com.uhope.inspection.domain.ScTrafficList;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * TrafficListMapper继承基类
 */

public interface TrafficListMapper extends Mapper<ScTrafficList> {
    ScTrafficList selectById(@Param("inspectionId") String inspectionId);
}