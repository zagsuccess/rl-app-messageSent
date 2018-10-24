package com.uhope.quality.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.quality.domain.WaterQualityGrade;
import com.uhope.quality.dto.MdSectionDTO;
import com.uhope.quality.dto.WaterQualityGradeDTO;
import com.uhope.quality.mapper.WaterQualityGradeMapper;
import com.uhope.quality.service.WaterQualityGradeService;
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
public class WaterQualityGradeServiceImpl extends AbstractService<WaterQualityGrade, WaterQualityGradeDTO,String> implements WaterQualityGradeService {
    @Resource
    private WaterQualityGradeMapper waterQualityGradeMapper;

    @Override
    public PageInfo<WaterQualityGrade> list(Integer pageNumber, Integer pageSize, String parentid, String riverName, String name) {
        PageHelper.startPage(pageNumber, pageSize);
        List<WaterQualityGrade> list=waterQualityGradeMapper.selectList(parentid,riverName,name);
        PageInfo<WaterQualityGrade> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<String> districtlist() {
        return waterQualityGradeMapper.districtlist();
    }

    @Override
    public void deletelist(String parentid) {
        waterQualityGradeMapper.deletelist(parentid);
    }

    @Override
    public List<MdSectionDTO> selectCascade() {
        return waterQualityGradeMapper.selectCascade();
    }

    @Override
    public void insert1(WaterQualityGrade waterQualityGrade) {
        waterQualityGradeMapper.insert1(waterQualityGrade);
    }

    @Override
    public String selectRiverName(String code) {

        return waterQualityGradeMapper.selectRiverName(code);
    }

    @Override
    public WaterQualityGrade get1(String id) {
        return waterQualityGradeMapper.get1(id);
    }

    @Override
    public void update1(WaterQualityGrade waterQualityGrade) {
        waterQualityGradeMapper.update1(waterQualityGrade);
    }

    @Override
    public List<WaterQualityGrade> haveSection(String parentid) {
        return waterQualityGradeMapper.haveSection(parentid);
    }

    @Override
    public String selectRiver(String name) {
        return waterQualityGradeMapper.selectRiver(name);
    }
}
