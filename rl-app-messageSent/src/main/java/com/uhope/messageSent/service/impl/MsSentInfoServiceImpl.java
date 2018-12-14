package com.uhope.messageSent.service.impl;

import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.core.OrderBy;
import com.uhope.messageSent.domain.MsSentInfo;
import com.uhope.messageSent.dto.MsSentInfoDTO;
import com.uhope.messageSent.mapper.MsSentInfoMapper;
import com.uhope.messageSent.service.MsSentInfoService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 *
 * @author a4994
 * @create 2018-12-10 9:55
 */
@Service
@Transactional
public class MsSentInfoServiceImpl extends AbstractService<MsSentInfo, MsSentInfoDTO, String> implements MsSentInfoService {
    @Resource
    private MsSentInfoMapper msSentInfoMapper;
    @Override
    public List<String> selectRegion() {
        return msSentInfoMapper.selectRegion();
    }
}
