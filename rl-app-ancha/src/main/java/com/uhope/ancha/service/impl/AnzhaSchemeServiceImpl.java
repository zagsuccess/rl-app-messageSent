package com.uhope.ancha.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaScheme;
import com.uhope.ancha.dto.AnzhaSchemeDTO;
import com.uhope.ancha.mapper.AnzhaSchemeMapper;
import com.uhope.ancha.service.AnzhaSchemeService;
import com.uhope.core.AbstractService;
import com.uhope.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaSchemeServiceImpl extends AbstractService<AnzhaScheme, AnzhaSchemeDTO, String> implements AnzhaSchemeService {
    @Resource
    private AnzhaSchemeMapper anzhaSchemeMapper;
    @Autowired
    protected Mapper<AnzhaBulletin> mapper;

    @Override
    public AnzhaScheme selectHave(String issue) {
        return anzhaSchemeMapper.selectHave(issue);
    }

    @Override
    public PageInfo<AnzhaSchemeDTO> list(Integer pageNumber, Integer pageSize, String title, String issue) {
        PageHelper.startPage(pageNumber, pageSize);
        List<AnzhaSchemeDTO> list=anzhaSchemeMapper.selectList(title,issue);
        for (AnzhaSchemeDTO anzhaSchemeDTO:list) {
            if (anzhaSchemeDTO != null) {
                AnzhaBulletin anzhaBulletin = mapper.selectByPrimaryKey(anzhaSchemeDTO.getBulletinid());
                if(anzhaBulletin !=null){
                    anzhaSchemeDTO.setBulletinName(anzhaBulletin.getTitle());
                }
            }
        }
        PageInfo<AnzhaSchemeDTO> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
