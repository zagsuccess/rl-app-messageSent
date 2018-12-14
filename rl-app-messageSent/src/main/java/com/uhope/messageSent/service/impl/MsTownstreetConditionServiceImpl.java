package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsTownstreetConditionMapper;
import com.uhope.messageSent.domain.MsTownstreetCondition;
import com.uhope.messageSent.service.MsTownstreetConditionService;
import com.uhope.messageSent.dto.MsTownstreetConditionDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 镇街制度执行情况表-ServiceImpl接口实现类
 * @author zhangaiguo on 2018/12/10
 */
@Service
@Transactional
public class MsTownstreetConditionServiceImpl extends AbstractService<MsTownstreetCondition, MsTownstreetConditionDTO, String> implements MsTownstreetConditionService {
    @Resource
    private MsTownstreetConditionMapper msTownstreetConditionMapper;

    @Override
    public String selectRole(String id) {
        return msTownstreetConditionMapper.selectRole(id);
    }

    @Override
    public String selectRegion(Long regionId) {
        return msTownstreetConditionMapper.selectRegion(regionId);
    }

    @Override
    public String selectGrade(Long regionId) {
        return msTownstreetConditionMapper.selectGrade(regionId);
    }
}
