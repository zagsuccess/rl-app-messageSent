package com.uhope.template.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.template.domain.WaterQuality;
import com.uhope.template.dto.WaterQualityDTO;
import com.uhope.template.mapper.WaterQualityMapper;
import com.uhope.template.service.WaterQualityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 * 水质评分业务实现类
 *
 * @author a4994
 * @create 2018-09-28 9:09
 */
@Service
public class WaterQualityServiceImpl extends AbstractService<WaterQuality, WaterQualityDTO,String> implements WaterQualityService {
    @Resource
    private WaterQualityMapper waterQualityMapper;

    @Override
    public PageInfo<WaterQuality> list(Integer pageNumber, Integer pageSize, String issue, Integer status, String createUser,Integer num) {
        PageHelper.startPage(pageNumber, pageSize);
        List<WaterQuality> list=waterQualityMapper.selectList(issue,status,createUser,num);
        PageInfo<WaterQuality> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public String selectSHZB() {
        return waterQualityMapper.selectSHZB();
    }

    @Override
    public String selectSHBJ() {
        return waterQualityMapper.selectSHBJ();
    }

    @Override
    public WaterQuality get1(String id) {
        return waterQualityMapper.get1(id);
    }
}
