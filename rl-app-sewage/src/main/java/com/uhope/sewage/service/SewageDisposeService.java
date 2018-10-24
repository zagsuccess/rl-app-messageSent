package com.uhope.sewage.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.sewage.domain.SewageDispose;
import com.uhope.sewage.dto.SewageDisposeDTO;

public interface SewageDisposeService extends Service<SewageDispose, SewageDisposeDTO,String> {
    public PageInfo<SewageDispose> list(Integer pageNumber, Integer pageSize, String issue, Integer status, String createUser, Integer num);

    public String selectSHZB();

    public String selectSHBJ();
}