package com.uhope.inspection.mapper;

import com.uhope.inspection.domain.ScSupervise;
import com.uhope.core.Mapper;

import java.util.List;

public interface SuperviseMapper extends Mapper<ScSupervise> {
    List<String> selectArea();
}
