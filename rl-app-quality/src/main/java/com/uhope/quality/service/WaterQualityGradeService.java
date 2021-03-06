package com.uhope.quality.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.quality.domain.WaterQualityGrade;
import com.uhope.quality.dto.MdSectionDTO;
import com.uhope.quality.dto.WaterQualityGradeDTO;

import java.util.List;

public interface WaterQualityGradeService extends Service<WaterQualityGrade, WaterQualityGradeDTO,String> {
   public PageInfo<WaterQualityGrade> list(Integer pageNumber, Integer pageSize, String parentid, String riverName, String name);

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
