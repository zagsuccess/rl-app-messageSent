package com.uhope.inspection.service.impl;

import com.uhope.inspection.mapper.TemplateMapper;
import com.uhope.inspection.domain.Template;
import com.uhope.inspection.service.TemplateService;
import com.uhope.inspection.dto.TemplateDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class TemplateServiceImpl extends AbstractService<Template, TemplateDTO, String> implements TemplateService {
    @Resource
    private TemplateMapper templateMapper;

}
