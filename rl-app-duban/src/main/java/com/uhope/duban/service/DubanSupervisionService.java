package com.uhope.duban.service;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Service;
import com.uhope.duban.domain.ScDubanSupervision;
import com.uhope.duban.dto.DeadlineDTO;
import com.uhope.duban.dto.DubanSupervisionDTO;
import com.uhope.template.domain.Template;
import com.uhope.template.dto.TemplateDTO;

import java.util.List;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface DubanSupervisionService extends Service<ScDubanSupervision, DubanSupervisionDTO, String> {

    String selectRoleId(String rolename);

    List<String> selectDeadlineUser();

    List<ScDubanSupervision> selectDeadlineUserh();

    PageInfo<ScDubanSupervision> list(Integer pageNumber, Integer pageSize, String issuedtime, String objectname, String status,String objectid);

}
