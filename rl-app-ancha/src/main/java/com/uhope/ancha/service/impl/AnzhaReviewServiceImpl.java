package com.uhope.ancha.service.impl;

import com.uhope.ancha.domain.AnzhaReview;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaReviewDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.ancha.mapper.AnzhaReviewMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaReviewService;
import com.uhope.ancha.service.TemplateService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaReviewServiceImpl extends AbstractService<AnzhaReview, AnzhaReviewDTO, String> implements AnzhaReviewService {
    @Resource
    private AnzhaReviewMapper anzhaReviewMapper;

    @Override
    public AnzhaReview selectOneById(String id) {
        return anzhaReviewMapper.selectOneById(id);
    }

    @Override
    public void insertAnzhaReview(AnzhaReview anzhaReview) {
        anzhaReviewMapper.insertAnzhaReview(anzhaReview);
    }
}
