package com.uhope.duban.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.duban.domain.DubanSupervision;
import com.uhope.duban.dto.DeadlineDTO;
import com.uhope.duban.dto.DubanSupervisionDTO;
import com.uhope.duban.mapper.DubanSupervisionMapper;
import com.uhope.duban.service.DubanSupervisionService;
import com.uhope.template.domain.Template;
import com.uhope.template.dto.TemplateDTO;
import com.uhope.template.mapper.TemplateMapper;
import com.uhope.template.service.TemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class DubanSupervisionServiceImpl extends AbstractService<DubanSupervision, DubanSupervisionDTO, String> implements DubanSupervisionService {
    @Resource
    private DubanSupervisionMapper dubanSupervisionMapper;

    @Override
    public String selectRoleId(String rolename) {
        return dubanSupervisionMapper.selectRoleId(rolename);
    }

    @Override
    public List<String> selectDeadlineUser() {
        return dubanSupervisionMapper.selectDeadlineUser();
    }

    @Override
    public List<DeadlineDTO> selectDeadlineUserh() {
        return dubanSupervisionMapper.selectDeadlineUserh();
    }

    @Override
    public PageInfo<DubanSupervision> list(Integer pageNumber, Integer pageSize, String issuedtime, String objectname, String status) {
        PageHelper.startPage(pageNumber, pageSize);
        List<DubanSupervision> list = dubanSupervisionMapper.list(issuedtime,objectname,status);
        PageInfo<DubanSupervision> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
