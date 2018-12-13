package com.uhope.messageSent.service;

import com.uhope.core.Service;
import com.uhope.messageSent.domain.MsWorkBulletin;
import com.uhope.messageSent.dto.MsWorkBulletinDTO;

import java.util.List;
import java.util.Map;

/**
 * 工作简报
 * @author  wanglijun
 */
public interface MsWorkBulletinService extends Service<MsWorkBulletin, MsWorkBulletinDTO, String>{

    List<Map<String, Object>> selectList(String grade);

    Map<String, Object> selectDetail(String id);
}
