package com.uhope.messageSent.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.messageSent.domain.MsReturnBack;
import com.uhope.messageSent.dto.MsReturnBackDTO;
import com.uhope.messageSent.mapper.MsReturnBackMapper;
import com.uhope.messageSent.service.MsReturnBackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class MsReturnBackServiceImpl extends AbstractService<MsReturnBack, MsReturnBackDTO, String> implements MsReturnBackService {

    @Resource
    private MsReturnBackMapper msReturnBackMapper;
}
