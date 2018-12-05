package com.uhope.messageSent.service;
import com.uhope.messageSent.domain.MsMeetingCondition;
import com.uhope.messageSent.dto.MsMeetingConditionDTO;
import com.uhope.core.Service;
import java.lang.String;

/**
 * 会议制度执行情况表-Service接口类
 * @author mengaoran on 2018/11/27
 */
public interface MsMeetingConditionService extends Service<MsMeetingCondition, MsMeetingConditionDTO, String> {

    String selectRole(String id);

    String selectRegion(Long regionId);
}
