package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsWeekReturnMapper;
import com.uhope.messageSent.domain.MsWeekReturn;
import com.uhope.messageSent.service.MsWeekReturnService;
import com.uhope.messageSent.dto.MsWeekReturnDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 周动态退回原因表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsWeekReturnServiceImpl extends AbstractService<MsWeekReturn, MsWeekReturnDTO, String> implements MsWeekReturnService {
    @Resource
    private MsWeekReturnMapper msWeekReturnMapper;

}
