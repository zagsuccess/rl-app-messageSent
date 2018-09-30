package com.uhope.undercover.service.impl;

import com.uhope.undercover.mapper.TemplateMapper;
import com.uhope.undercover.domain.Template;
import com.uhope.undercover.service.TemplateService;
import com.uhope.undercover.dto.TemplateDTO;
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
