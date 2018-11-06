package com.uhope.ancha.service;

import com.uhope.ancha.domain.AnzhaReview;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaReviewDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.core.Service;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaReviewService extends Service<AnzhaReview, AnzhaReviewDTO, String> {

    public AnzhaReview selectOneById(String id);

    public void insertAnzhaReview(AnzhaReview anzhaReview);
}
