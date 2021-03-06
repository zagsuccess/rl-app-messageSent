package com.uhope.water.mapper;

import com.uhope.core.Mapper;
import com.uhope.water.domain.SurfaceWaterGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurfaceWaterGradeMapper extends Mapper<SurfaceWaterGrade> {
    public List<SurfaceWaterGrade> selectList(@Param("parentid") String parentid);

    public List<String> districtlist();

    public void deletelist(String parentid);
}
