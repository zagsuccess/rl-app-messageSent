package com.uhope.water.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.water.domain.SurfaceWater;
import com.uhope.water.dto.SurfaceWaterDTO;
import com.uhope.water.mapper.SurfaceWaterMapper;
import com.uhope.water.service.SurfaceWaterService;
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
public class SurfaceWaterServiceImpl extends AbstractService<SurfaceWater, SurfaceWaterDTO,String> implements SurfaceWaterService {
    @Resource
    private SurfaceWaterMapper surfaceWaterMapper;

    @Override
    public PageInfo<SurfaceWater> list(Integer pageNumber, Integer pageSize, String issue, String status, String createUser,Integer num) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SurfaceWater> list=surfaceWaterMapper.selectList(issue,status,createUser,num);
        PageInfo<SurfaceWater> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public String selectRole(String id) {
        return surfaceWaterMapper.selectRole(id);
    }

    @Override
    public SurfaceWater selectHave(String issue) {
        return surfaceWaterMapper.selectHave(issue);
    }
}
