package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsWeekDynamicMapper;
import com.uhope.messageSent.domain.MsWeekDynamic;
import com.uhope.messageSent.service.MsWeekDynamicService;
import com.uhope.messageSent.dto.MsWeekDynamicDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;
import java.util.List;

import javax.annotation.Resource;

/**
 * 周动态表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsWeekDynamicServiceImpl extends AbstractService<MsWeekDynamic, MsWeekDynamicDTO, String> implements MsWeekDynamicService {
    @Resource
    private MsWeekDynamicMapper msWeekDynamicMapper;

    @Override
    public List<String> selectArea() {
        return msWeekDynamicMapper.selectArea();
    }
}
