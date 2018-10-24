package com.uhope.sewage.mapper;

import com.uhope.core.Mapper;
import com.uhope.sewage.domain.SewageDisposeReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SewageDisposeReportMapper extends Mapper<SewageDisposeReport> {
    public List<SewageDisposeReport> selectList(@Param("parentid") String parentid);

    public List<String> districtlist();

    public void deletelist(String parentid);

    List<SewageDisposeReport> haveSewage(String parentid);

    List<String> selectWorks();
}
