package com.uhope.messageSent.service;
import com.uhope.messageSent.domain.MsSentDynamis;
import com.uhope.messageSent.dto.MsSentDynamisDTO;
import com.uhope.core.Service;
import java.lang.String;

/**
 * 周动态报送表-Service接口类
 * @author mengaoran on 2018/11/27
 */
public interface MsSentDynamisService extends Service<MsSentDynamis, MsSentDynamisDTO, String> {

    MsSentDynamis findByReportId(String id, String sentUnit);
}
