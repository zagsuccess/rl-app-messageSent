package com.uhope.inspection.service;

import com.github.pagehelper.PageInfo;
import com.uhope.inspection.domain.ScInspection;
import com.uhope.inspection.dto.ScInspectionDTO;
import com.uhope.core.Service;

import java.text.ParseException;
import java.util.List;

public interface InspectionService extends Service<ScInspection, ScInspectionDTO, String> {
    public String selectRole(String id);


    List<ScInspection> selectBelong(String sentUnit);

    String selectRegion(Long regionId);
}
