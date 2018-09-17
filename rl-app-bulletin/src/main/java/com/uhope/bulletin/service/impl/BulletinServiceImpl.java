package com.uhope.bulletin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.bulletin.domain.Bulletin;
import com.uhope.bulletin.dto.BulletinDTO;
import com.uhope.bulletin.mapper.BulletinMapper;
import com.uhope.bulletin.service.BulletinService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 暗查暗访表-接口实现类
 */
@Service
public class BulletinServiceImpl extends AbstractService<Bulletin, BulletinDTO, String> implements BulletinService {
    @Resource
    private BulletinMapper bulletinMapper;

    @Override
    public PageInfo<Bulletin> list(Integer pageNumber, Integer pageSize, Integer type, String year, String month) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        Example example = new Example(Bulletin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("type","%"+type+"%");
        criteria.andLike("year","%"+year+"%");
        criteria.andLike("month","%"+month+"%");
        List<Bulletin> list = bulletinMapper.selectByCondition(example);
        PageInfo<Bulletin> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void delete(Integer id) {
        bulletinMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Bulletin detail(Integer id) {
        Bulletin undercover = bulletinMapper.selectByPrimaryKey(id);
        return undercover;
    }

    @Override
    public Bulletin selectByFirst(Integer type) {
        Bulletin bulletin = bulletinMapper.selectByFirst(type);
        return bulletin;
    }


}
