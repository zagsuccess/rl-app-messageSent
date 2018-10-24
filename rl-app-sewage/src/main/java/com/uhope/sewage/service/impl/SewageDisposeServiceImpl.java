package com.uhope.sewage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.sewage.domain.SewageDispose;
import com.uhope.sewage.dto.SewageDisposeDTO;
import com.uhope.sewage.mapper.SewageDisposeMapper;
import com.uhope.sewage.service.SewageDisposeService;
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
public class SewageDisposeServiceImpl extends AbstractService<SewageDispose, SewageDisposeDTO,String> implements SewageDisposeService {
    @Resource
    private SewageDisposeMapper sewageDisposeMapper;

    @Override
    public PageInfo<SewageDispose> list(Integer pageNumber, Integer pageSize, String issue, Integer status, String createUser,Integer num) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SewageDispose> list=sewageDisposeMapper.selectList(issue,status,createUser,num);
        PageInfo<SewageDispose> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public String selectSHZB() {
        return sewageDisposeMapper.selectSHZB();
    }

    @Override
    public String selectSHBJ() {
        return sewageDisposeMapper.selectSHBJ();
    }
}
