package com.uhope.messageSent.service;
import com.uhope.messageSent.domain.MsSupervisionCondition;
import com.uhope.messageSent.dto.MsSupervisionConditionDTO;
import com.uhope.core.Service;
import java.lang.String;

/**
 * 督导检查情况表-Service接口类
 * @author zhangaiguo on 2018/12/10
 */
public interface MsSupervisionConditionService extends Service<MsSupervisionCondition, MsSupervisionConditionDTO, String> {
    String selectRole(String id);

    String selectRegion(Long regionId);
}
