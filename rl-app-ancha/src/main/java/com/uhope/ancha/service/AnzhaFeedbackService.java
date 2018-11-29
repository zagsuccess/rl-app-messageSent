package com.uhope.ancha.service;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.dto.AnzhaFeedbackDTO;
import com.uhope.core.Service;

import java.util.Date;
import java.util.List;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaFeedbackService extends Service<AnzhaFeedback, AnzhaFeedbackDTO, String> {

    public List<AnzhaFeedbackDTO> selectOneById(String bulletinid,String objectid);

    public void insertAnzhaFeedback(AnzhaFeedback anzhaFeedback);

    List<AnzhaFeedback> selectFeedbackBydubanid(String id, Date deadlinetime);

    int selectcountByyinum(String id);

    String selectHaveyi(String bulletinid, String objectid);
}
