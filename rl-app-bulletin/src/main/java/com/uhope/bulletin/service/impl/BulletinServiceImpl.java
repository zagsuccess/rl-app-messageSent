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
    public PageInfo<Bulletin> list(Integer pageNumber, Integer pageSize, Integer type, String post_time){
        PageHelper.startPage(pageNumber, pageSize);
        List<Bulletin>list = bulletinMapper.selectlist(type, post_time);
        PageInfo<Bulletin> pageInfo = new PageInfo(list);
        return pageInfo;
    }


    @Override
    public List<Bulletin> selectByFirst(Integer type) {
        List<Bulletin>bulletin = bulletinMapper.selectByFirst(type);
        return bulletin;
    }


}
