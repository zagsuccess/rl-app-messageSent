package com.uhope.messageSent.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.messageSent.domain.MsWorkBulletin;
import com.uhope.messageSent.dto.MsWorkBulletinDTO;
import com.uhope.messageSent.mapper.MsWorkBulletinMapper;
import com.uhope.messageSent.service.MsWorkBulletinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MsWorkBulletinServiceImpl extends AbstractService<MsWorkBulletin, MsWorkBulletinDTO, String> implements MsWorkBulletinService {

    @Resource
    private MsWorkBulletinMapper msWorkBulletinMapper;


    @Override
    public List<Map<String, Object>> selectList(String grade) {
        return msWorkBulletinMapper.selectList(grade);
    }

    @Override
    public Map<String, Object> selectDetail(String id) {
        return msWorkBulletinMapper.selectDetail(id);
    }

}
