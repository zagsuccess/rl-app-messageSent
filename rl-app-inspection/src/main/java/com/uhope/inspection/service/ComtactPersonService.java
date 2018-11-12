package com.uhope.inspection.service;

import com.uhope.inspection.domain.ScComtactPerson;
import com.uhope.inspection.dto.ScComtactPersonDTO;
import com.uhope.core.Service;

import java.util.List;

public interface ComtactPersonService extends Service<ScComtactPerson, ScComtactPersonDTO,String> {

    List<ScComtactPerson> selectById(String inspectionid);
}
