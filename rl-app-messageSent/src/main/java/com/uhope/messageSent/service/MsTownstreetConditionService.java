package com.uhope.messageSent.service;
import com.uhope.messageSent.domain.MsTownstreetCondition;
import com.uhope.messageSent.dto.MsTownstreetConditionDTO;
import com.uhope.core.Service;
import java.lang.String;

/**
 * 镇街制度执行情况表-Service接口类
 * @author zhangaiguo on 2018/12/10
 */
public interface MsTownstreetConditionService extends Service<MsTownstreetCondition, MsTownstreetConditionDTO, String> {
    String selectRole(String id);

    String selectRegion(Long regionId);

    String selectGrade(Long regionId);
}
