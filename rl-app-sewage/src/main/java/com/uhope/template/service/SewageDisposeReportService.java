package com.uhope.template.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.template.domain.SewageDisposeReport;
import com.uhope.template.dto.SewageDisposeReportDTO;

import java.util.List;

public interface SewageDisposeReportService extends Service<SewageDisposeReport, SewageDisposeReportDTO,String> {

   public PageInfo<SewageDisposeReport> list(Integer pageNumber, Integer pageSize, String parentid);

   public List<String> districtlist();

   public void deletelist(String parentid);

   List<SewageDisposeReport> haveSewage(String parentid);

   public List<String> selectWorks();
}
