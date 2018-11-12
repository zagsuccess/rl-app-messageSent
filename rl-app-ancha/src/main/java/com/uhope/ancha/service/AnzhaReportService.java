package com.uhope.ancha.service;

import com.uhope.ancha.domain.AnzhaReport;
import com.uhope.ancha.dto.AnzhaReportDTO;
import com.uhope.core.Service;

import java.util.List;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaReportService extends Service<AnzhaReport, AnzhaReportDTO, String> {

    public List<AnzhaReport> list(String anzhaid);

}
