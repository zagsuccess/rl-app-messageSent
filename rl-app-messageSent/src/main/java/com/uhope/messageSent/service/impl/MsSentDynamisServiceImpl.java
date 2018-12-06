package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsSentDynamisMapper;
import com.uhope.messageSent.domain.MsSentDynamis;
import com.uhope.messageSent.service.MsSentDynamisService;
import com.uhope.messageSent.dto.MsSentDynamisDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 周动态报送表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsSentDynamisServiceImpl extends AbstractService<MsSentDynamis, MsSentDynamisDTO, String> implements MsSentDynamisService {
    @Resource
    private MsSentDynamisMapper msSentDynamisMapper;

    @Override
    public MsSentDynamis findByReportId(String id, String sentUnit) {
        return msSentDynamisMapper.findByReportId(id,sentUnit);
    }
}
