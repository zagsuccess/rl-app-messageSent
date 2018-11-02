package com.uhope.supervise.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.supervise.domain.ShSocialReport;
import com.uhope.supervise.dto.ShSocialReportDTO;
import com.uhope.supervise.mapper.ShSocialReportMapper;
import com.uhope.supervise.service.ShSocialEvaluationService;
import com.uhope.supervise.service.ShSocialReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/30
 * @description:
 */
@Service
public class ShSocialReportServiceImpl extends AbstractService<ShSocialReport, ShSocialReportDTO, String> implements ShSocialReportService {
    @Resource
    private ShSocialReportMapper socialReportMapper;
}
