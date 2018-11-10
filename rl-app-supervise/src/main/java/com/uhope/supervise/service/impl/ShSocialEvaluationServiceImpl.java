package com.uhope.supervise.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.supervise.domain.ShSocialEvaluation;
import com.uhope.supervise.dto.ShSocialEvaluationDTO;
import com.uhope.supervise.mapper.ShSocialEvaluationMapper;
import com.uhope.supervise.service.ShSocialEvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/30
 * @description:
 */
@Service
public class ShSocialEvaluationServiceImpl extends AbstractService<ShSocialEvaluation, ShSocialEvaluationDTO, String> implements ShSocialEvaluationService {
    @Resource
    private ShSocialEvaluationMapper socialEvaluationMapper;
}
