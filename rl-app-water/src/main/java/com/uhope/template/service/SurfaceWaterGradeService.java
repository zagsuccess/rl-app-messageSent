package com.uhope.template.service;

import com.uhope.core.Service;
import com.uhope.template.domain.SurfaceWaterGrade;
import com.uhope.template.dto.SurfaceWaterGradeDTO;

import java.util.List;

public interface SurfaceWaterGradeService extends Service<SurfaceWaterGrade, SurfaceWaterGradeDTO,String> {
   public List<SurfaceWaterGrade> list(String parentid);

   public List<String> districtlist();

   public void deletelist(String parentid);
}
