package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsMeetingConditionMapper;
import com.uhope.messageSent.domain.MsMeetingCondition;
import com.uhope.messageSent.service.MsMeetingConditionService;
import com.uhope.messageSent.dto.MsMeetingConditionDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 会议制度执行情况表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsMeetingConditionServiceImpl extends AbstractService<MsMeetingCondition, MsMeetingConditionDTO, String> implements MsMeetingConditionService {
    @Resource
    private MsMeetingConditionMapper msMeetingConditionMapper;

    @Override
    public String selectRole(String id) {
        return msMeetingConditionMapper.selectRole(id);
    }

    @Override
    public String selectRegion(Long regionId) {
        return msMeetingConditionMapper.selectRegion(regionId);
    }
}
