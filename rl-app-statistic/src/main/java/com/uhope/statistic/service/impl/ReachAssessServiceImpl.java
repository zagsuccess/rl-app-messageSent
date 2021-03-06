package com.uhope.statistic.service.impl;

import com.uhope.core.AbstractService;
import com.uhope.statistic.domain.AmReachAssess;
import com.uhope.statistic.mapper.AmReachAssessMapper;
import com.uhope.statistic.service.ReachAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/13
 * @description: 河段考核配置
 */
@Service
public class ReachAssessServiceImpl extends AbstractService<AmReachAssess, AmReachAssess, String> implements ReachAssessService {

    @Resource
    private AmReachAssessMapper reachAssessMapper;

    @Override
    public List<String> regionList() {
        return reachAssessMapper.regionList();
    }

    @Override
    public List<String> waterQualityRuleList() {
        return reachAssessMapper.waterQualityRuleList();
    }

    @Override
    public List<String> listSections() {
        return reachAssessMapper.listSections();
    }

    /**
     * 重写update方法，对象值为null也绑定到数据库中，此方法需要前端传回对象的所有参数
     * @param model
     * @return
     */
    @Override
    public int update(AmReachAssess model) {
        return mapper.updateByPrimaryKey(model);
    }
}
