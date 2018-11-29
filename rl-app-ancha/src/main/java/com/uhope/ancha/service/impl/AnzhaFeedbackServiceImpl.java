package com.uhope.ancha.service.impl;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaFeedbackDTO;
import com.uhope.ancha.dto.TemplateDTO;
import com.uhope.ancha.mapper.AnzhaFeedbackMapper;
import com.uhope.ancha.mapper.TemplateMapper;
import com.uhope.ancha.service.AnzhaFeedbackService;
import com.uhope.ancha.service.TemplateService;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class AnzhaFeedbackServiceImpl extends AbstractService<AnzhaFeedback, AnzhaFeedbackDTO, String> implements AnzhaFeedbackService {
    @Resource
    private AnzhaFeedbackMapper anzhaFeedbackMapper;

    @Override
    public List<AnzhaFeedbackDTO> selectOneById(String bulletinid,String objectid) {
        return anzhaFeedbackMapper.selectOneById(bulletinid,objectid);
    }

    @Override
    public void insertAnzhaFeedback(AnzhaFeedback anzhaFeedback) {
        anzhaFeedbackMapper.insertAnzhaFeedback(anzhaFeedback);
    }

    @Override
    public List<AnzhaFeedback> selectFeedbackBydubanid(String id, Date deadlinetime) {
        return anzhaFeedbackMapper.selectFeedbackBydubanid(id,deadlinetime);
    }

    @Override
    public int selectcountByyinum(String id) {
        return anzhaFeedbackMapper.selectcountByyinum(id);
    }

    @Override
    public String selectHaveyi(String bulletinid, String objectid) {
        return anzhaFeedbackMapper.selectHaveyi(bulletinid,objectid);
    }
}
