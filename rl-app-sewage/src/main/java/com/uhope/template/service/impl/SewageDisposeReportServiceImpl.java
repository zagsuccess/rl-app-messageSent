package com.uhope.template.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.template.domain.SewageDisposeReport;
import com.uhope.template.dto.SewageDisposeReportDTO;
import com.uhope.template.mapper.SewageDisposeReportMapper;
import com.uhope.template.service.SewageDisposeReportService;
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
public class SewageDisposeReportServiceImpl extends AbstractService<SewageDisposeReport, SewageDisposeReportDTO,String> implements SewageDisposeReportService {
    @Resource
    private SewageDisposeReportMapper sewageDisposeReportMapper;

    @Override
    public PageInfo<SewageDisposeReport> list(Integer pageNumber, Integer pageSize,String parentid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SewageDisposeReport> list=sewageDisposeReportMapper.selectList(parentid);
        PageInfo<SewageDisposeReport> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<String> districtlist() {
        return sewageDisposeReportMapper.districtlist();
    }

    @Override
    public void deletelist(String parentid) {
        sewageDisposeReportMapper.deletelist(parentid);
    }

    @Override
    public List<SewageDisposeReport> haveSewage(String parentid) {
        return sewageDisposeReportMapper.haveSewage(parentid);
    }

    @Override
    public List<String> selectWorks() {
        return sewageDisposeReportMapper.selectWorks();
    }
}
