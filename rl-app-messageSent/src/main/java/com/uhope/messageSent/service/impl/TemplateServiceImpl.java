package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.mapper.TemplateMapper;
import com.uhope.messageSent.domain.Template;
import com.uhope.messageSent.service.TemplateService;
import com.uhope.messageSent.dto.TemplateDTO;
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
