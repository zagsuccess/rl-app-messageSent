package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsSupervisionConditionMapper;
import com.uhope.messageSent.domain.MsSupervisionCondition;
import com.uhope.messageSent.service.MsSupervisionConditionService;
import com.uhope.messageSent.dto.MsSupervisionConditionDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 督导检查情况表-ServiceImpl接口实现类
 * @author zhangaiguo on 2018/12/10
 */
@Service
@Transactional
public class MsSupervisionConditionServiceImpl extends AbstractService<MsSupervisionCondition, MsSupervisionConditionDTO, String> implements MsSupervisionConditionService {
    @Resource
    private MsSupervisionConditionMapper msSupervisionConditionMapper;

    @Override
    public String selectRole(String id) {
        return msSupervisionConditionMapper.selectRole(id);
    }

    @Override
    public String selectRegion(Long regionId) {
        return msSupervisionConditionMapper.selectRegion(regionId);
    }
}

