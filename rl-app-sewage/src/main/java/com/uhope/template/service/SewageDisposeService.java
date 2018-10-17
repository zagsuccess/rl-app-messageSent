package com.uhope.template.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.template.domain.SewageDispose;
import com.uhope.template.dto.SewageDisposeDTO;

public interface SewageDisposeService extends Service<SewageDispose, SewageDisposeDTO,String> {
    public PageInfo<SewageDispose> list(Integer pageNumber, Integer pageSize, String issue, Integer status, String createUser, Integer num);

    public String selectSHZB();

    public String selectSHBJ();
}
