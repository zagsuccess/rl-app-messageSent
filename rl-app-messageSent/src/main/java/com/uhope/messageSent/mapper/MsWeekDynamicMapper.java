package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsWeekDynamic;

import java.util.List;

public interface MsWeekDynamicMapper extends Mapper<MsWeekDynamic> {
    List<String> selectArea();
}