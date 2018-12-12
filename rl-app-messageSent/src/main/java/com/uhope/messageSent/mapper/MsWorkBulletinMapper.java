package com.uhope.messageSent.mapper;

import com.uhope.core.Mapper;
import com.uhope.messageSent.domain.MsWorkBulletin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 工作简报
 * @author  wanglijun
 */
public interface MsWorkBulletinMapper extends Mapper<MsWorkBulletin> {

    List<Map<String, Object>> selectList(@Param("grade") String grade);

    Map<String, Object> selectDetail(@Param("id") String id);
}
