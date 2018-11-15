package com.uhope.inspection.service;

import com.uhope.inspection.domain.ScSupervise;
import com.uhope.inspection.dto.ScSuperviseDTO;
import com.uhope.core.Service;

import java.util.List;

public interface SuperviseService extends Service<ScSupervise, ScSuperviseDTO,String> {
    List<String> selectArea();

    List<ScSupervise> selectById(String inspectionid);
}
