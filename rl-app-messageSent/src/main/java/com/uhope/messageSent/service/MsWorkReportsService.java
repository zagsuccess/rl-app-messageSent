package com.uhope.messageSent.service;
import com.uhope.messageSent.domain.MsWorkReports;
import com.uhope.messageSent.dto.MsWorkReportsDTO;
import com.uhope.core.Service;
import java.lang.String;

/**
 * 工作简报表-Service接口类
 * @author mengaoran on 2018/11/27
 */
public interface MsWorkReportsService extends Service<MsWorkReports, MsWorkReportsDTO, String> {

    String selectRegion(Long regionId);
}
