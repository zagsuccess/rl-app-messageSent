package com.uhope.duban.mapper;

import com.uhope.core.Mapper;
import com.uhope.duban.domain.ScDubanSupervision;
import com.uhope.duban.dto.DeadlineDTO;
import com.uhope.template.domain.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DubanSupervisionMapper extends Mapper<ScDubanSupervision> {
    String selectRoleId(String rolename);

    List<String> selectDeadlineUser();

    List<ScDubanSupervision> selectDeadlineUserh();

    List<ScDubanSupervision> list(@Param("issuedtime") String issuedtime,
                                  @Param("objectname") String objectname,
                                  @Param("status") String status);

}