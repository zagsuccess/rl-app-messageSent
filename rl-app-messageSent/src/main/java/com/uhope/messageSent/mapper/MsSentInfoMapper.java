package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsSentInfo;

import java.util.List;

public interface MsSentInfoMapper extends Mapper<MsSentInfo> {
    List<String> selectRegion();
}
