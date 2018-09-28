package com.uhope.template.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.template.domain.SurfaceWater;
import com.uhope.template.dto.SurfaceWaterDTO;
import com.uhope.template.mapper.SurfaceWaterMapper;
import com.uhope.template.service.SurfaceWaterService;
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
    public PageInfo<SurfaceWater> list(Integer pageNumber, Integer pageSize, String issue1, String issue2, Integer status, String createUser) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SurfaceWater> list=surfaceWaterMapper.selectList(issue1,issue2,status,createUser);
        PageInfo<SurfaceWater> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
