package com.uhope.duban.mapper;

import com.uhope.core.Mapper;
import com.uhope.duban.domain.ScDubanFeedback;
import com.uhope.duban.dto.DubanFeedbackDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DubanFeedbackMapper extends Mapper<ScDubanFeedback> {
    ScDubanFeedback selectFeedback(ScDubanFeedback dubanFeedback);

    String selectRole(String id);

    List<ScDubanFeedback> selectFeedbackBydubanid(@Param("id") String id, @Param("deadlinedate")Date deadlinedate);

    List<ScDubanFeedback> selectFeedbackBys(ScDubanFeedback dubanFeedback);

    List<DubanFeedbackDTO> selectFeedbackByobjectid(ScDubanFeedback dubanFeedback);
}