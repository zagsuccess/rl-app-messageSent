package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.MsWorkReturnMapper;
import com.uhope.messageSent.domain.MsWorkReturn;
import com.uhope.messageSent.service.MsWorkReturnService;
import com.uhope.messageSent.dto.MsWorkReturnDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 工作简报退回原因表-ServiceImpl接口实现类
 * @author mengaoran on 2018/11/27
 */
@Service
@Transactional
public class MsWorkReturnServiceImpl extends AbstractService<MsWorkReturn, MsWorkReturnDTO, String> implements MsWorkReturnService {
    @Resource
    private MsWorkReturnMapper msWorkReturnMapper;

}
