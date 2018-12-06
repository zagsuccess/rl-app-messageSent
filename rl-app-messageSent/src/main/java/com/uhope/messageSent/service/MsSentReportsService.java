package com.uhope.messageSent.service;
import com.uhope.messageSent.domain.MsSentReports;
import com.uhope.messageSent.dto.MsSentReportsDTO;
import com.uhope.core.Service;
import java.lang.String;

/**
 * 工作简报报送表-Service接口类
 * @author mengaoran on 2018/11/27
 */
public interface MsSentReportsService extends Service<MsSentReports, MsSentReportsDTO, String> {

     MsSentReports findByReportId(String id,String region);

    MsSentReports findByWorkId(String id);
}
