package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsWorkReportsMapper;
import com.uhope.messageSent.domain.MsWorkReports;
import com.uhope.messageSent.service.MsWorkReportsService;
import com.uhope.messageSent.dto.MsWorkReportsDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 工作简报表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsWorkReportsServiceImpl extends AbstractService<MsWorkReports, MsWorkReportsDTO, String> implements MsWorkReportsService {
    @Resource
    private MsWorkReportsMapper msWorkReportsMapper;

    @Override
    public String selectRegion(Long regionId) {
        return msWorkReportsMapper.selectRegion(regionId);
    }
}
