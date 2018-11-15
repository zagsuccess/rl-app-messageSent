package com.uhope.duban.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.core.AbstractService;
import com.uhope.duban.domain.ScDubanSupervision;
import com.uhope.duban.dto.DeadlineDTO;
import com.uhope.duban.dto.DubanSupervisionDTO;
import com.uhope.duban.mapper.DubanSupervisionMapper;
import com.uhope.duban.service.DubanSupervisionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class DubanSupervisionServiceImpl extends AbstractService<ScDubanSupervision, DubanSupervisionDTO, String> implements DubanSupervisionService {
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
    public PageInfo<ScDubanSupervision> list(Integer pageNumber, Integer pageSize, String issuedtime, String objectname, String status,String objectid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ScDubanSupervision> list = dubanSupervisionMapper.list(issuedtime,objectname,status,objectid);
        PageInfo<ScDubanSupervision> pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
