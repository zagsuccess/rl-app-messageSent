package com.uhope.ancha.service.impl;

import com.uhope.ancha.domain.AnzhaStandingBook;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaStandingBookDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.ancha.mapper.AnzhaStandingBookMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaStandingBookService;
import com.uhope.ancha.service.TemplateService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaStandingBookServiceImpl extends AbstractService<AnzhaStandingBook, AnzhaStandingBookDTO, String> implements AnzhaStandingBookService {
    @Resource
    private AnzhaStandingBookMapper anzhaStandingBookMapper;

    @Override
    public List<AnzhaStandingBook> list(String bulletinid) {
        return anzhaStandingBookMapper.selectList(bulletinid);
    }
}
