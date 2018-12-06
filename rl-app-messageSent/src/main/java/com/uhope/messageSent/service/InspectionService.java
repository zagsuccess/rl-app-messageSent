package com.uhope.messageSent.service;

import com.uhope.messageSent.domain.ScInspection;
import com.uhope.messageSent.dto.ScInspectionDTO;
import com.uhope.core.Service;

import java.util.List;

public interface InspectionService extends Service<ScInspection, ScInspectionDTO, String> {
    public String selectRole(String id);


    List<ScInspection> selectBelong(String sentUnit);
}
