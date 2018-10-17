package com.uhope.template.mapper;

import com.uhope.core.Mapper;
import com.uhope.template.domain.WaterQualityGrade;
import com.uhope.template.dto.MdSectionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaterQualityGradeMapper extends Mapper<WaterQualityGrade> {
    public List<WaterQualityGrade> selectList(@Param("parentid") String parentid,
                                              @Param("riverName") String riverName,
                                              @Param("name") String name);

    public List<String> districtlist();

    public void deletelist(String parentid);

    public List<MdSectionDTO> selectCascade();

    public void insert1(WaterQualityGrade waterQualityGrade);

    public String selectRiverName(String code);

    WaterQualityGrade get1(String id);

    void update1(WaterQualityGrade waterQualityGrade);

    List<WaterQualityGrade> haveSection(String parentid);

    String selectRiver(String name);
}
