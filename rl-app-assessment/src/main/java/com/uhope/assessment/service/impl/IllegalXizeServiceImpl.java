package com.uhope.assessment.service.impl;

import com.google.common.collect.Maps;
import com.uhope.assessment.domain.IllegalXize;
import com.uhope.assessment.dto.IllegalXizeDTO;
import com.uhope.assessment.mapper.IllegalXizeMapper;
import com.uhope.assessment.service.IllegalXizeService;
import com.uhope.core.AbstractService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class IllegalXizeServiceImpl extends AbstractService<IllegalXize, IllegalXizeDTO, String> implements IllegalXizeService {
    @Resource
    private IllegalXizeMapper illegalXizeMapper;

    @Override
    public List<IllegalXizeDTO> list(String parentid) {
        HashMap<String, Object> maps = Maps.newHashMap();
        maps.put("parentId", parentid);
        return illegalXizeMapper.list(maps);
    }

    @Override
    public List<IllegalXizeDTO> list1() {
        return illegalXizeMapper.list1();
    }

    @Override
    public String selectSHZB() {
        return illegalXizeMapper.selectSHZB();
    }
}
