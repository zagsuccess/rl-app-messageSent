package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsSentReportsMapper;
import com.uhope.messageSent.domain.MsSentReports;
import com.uhope.messageSent.service.MsSentReportsService;
import com.uhope.messageSent.dto.MsSentReportsDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 工作简报报送表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsSentReportsServiceImpl extends AbstractService<MsSentReports, MsSentReportsDTO, String> implements MsSentReportsService {
    @Resource
    private MsSentReportsMapper msSentReportsMapper;


    @Override
    public MsSentReports findByReportId(String id, String region) {
        return msSentReportsMapper.findByReportId(id,region);
    }

    @Override
    public MsSentReports findByWorkId(String id) {
        return msSentReportsMapper.findByWorkId(id);
    }
}
