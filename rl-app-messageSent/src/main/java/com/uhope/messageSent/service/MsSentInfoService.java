package com.uhope.messageSent.service;

import com.uhope.core.Service;
import com.uhope.messageSent.domain.MsSentInfo;
import com.uhope.messageSent.dto.MsSentInfoDTO;

import java.util.List;

public interface MsSentInfoService extends Service<MsSentInfo, MsSentInfoDTO, String> {
    List<String> selectRegion();
}
