package com.uhope.ancha.service.impl;

import com.uhope.ancha.domain.AnzhaReport;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaReportDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.ancha.mapper.AnzhaReportMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaReportService;
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
public class AnzhaReportServiceImpl extends AbstractService<AnzhaReport, AnzhaReportDTO, String> implements AnzhaReportService {
    @Resource
    private AnzhaReportMapper anzhaReportMapper;

    @Override
    public List<AnzhaReport> list(String anzhaid) {
        return anzhaReportMapper.selectList(anzhaid);
    }

}
