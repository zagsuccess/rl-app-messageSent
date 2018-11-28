package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaFeedbackDTO;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AnzhaFeedbackMapper extends Mapper<AnzhaFeedback> {
    public List<AnzhaFeedbackDTO> selectOneById(@Param("bulletinid") String bulletinid, @Param("objectid") String objectid);

    public void insertAnzhaFeedback(AnzhaFeedback anzhaFeedback);

    List<AnzhaFeedback> selectFeedbackBydubanid(@Param("id")String id,@Param("deadlinetime") Date deadlinetime);

    int selectcountByyinum(String id);

    String selectHaveyi(@Param("id")String id,@Param("objectid") String objectid);
}