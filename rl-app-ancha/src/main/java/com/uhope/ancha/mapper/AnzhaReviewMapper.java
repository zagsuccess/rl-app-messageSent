package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaReview;
import com.uhope.ancha.domain.Template;
import com.uhope.core.Mapper;

public interface AnzhaReviewMapper extends Mapper<AnzhaReview> {
    public AnzhaReview selectOneById(String id);

    void insertAnzhaReview(AnzhaReview anzhaReview);
}