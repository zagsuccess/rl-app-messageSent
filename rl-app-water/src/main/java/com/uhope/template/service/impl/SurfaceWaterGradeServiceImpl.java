package com.uhope.template.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.template.domain.SurfaceWaterGrade;
import com.uhope.template.dto.SurfaceWaterGradeDTO;
import com.uhope.template.mapper.SurfaceWaterGradeMapper;
import com.uhope.template.service.SurfaceWaterGradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 * 条目业务类实现
 *
 * @author a4994
 * @create 2018-09-28 9:06
 */
@Service
public class SurfaceWaterGradeServiceImpl extends AbstractService<SurfaceWaterGrade, SurfaceWaterGradeDTO,String> implements SurfaceWaterGradeService {
    @Resource
    private SurfaceWaterGradeMapper surfaceWaterGradeMapper;

    @Override
    public List<SurfaceWaterGrade> list(String parentid) {
        List<SurfaceWaterGrade> list=surfaceWaterGradeMapper.selectList(parentid);
        return list;
    }

    @Override
    public List<String> districtlist() {
        return surfaceWaterGradeMapper.districtlist();
    }

    @Override
    public void deletelist(String parentid) {
        surfaceWaterGradeMapper.deletelist(parentid);
    }
}
