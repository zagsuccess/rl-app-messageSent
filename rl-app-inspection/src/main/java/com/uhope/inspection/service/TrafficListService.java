package com.uhope.inspection.service;

import com.uhope.inspection.domain.ScTrafficList;
import com.uhope.inspection.dto.ScTrafficListDTO;
import com.uhope.core.Service;

public interface TrafficListService extends Service<ScTrafficList, ScTrafficListDTO,String> {
    ScTrafficList selectById(String inspectionId);
}
