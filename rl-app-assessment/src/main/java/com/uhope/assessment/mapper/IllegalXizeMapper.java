package com.uhope.assessment.mapper;

import com.uhope.assessment.domain.IllegalXize;
import com.uhope.assessment.dto.IllegalXizeDTO;
import com.uhope.core.Mapper;

import java.util.HashMap;
import java.util.List;

public interface IllegalXizeMapper extends Mapper<IllegalXize> {
    List<IllegalXizeDTO> list(HashMap<String, Object> maps);

    List<IllegalXizeDTO> list1();

    public String selectSHZB();

    String selectRole(String id);
}