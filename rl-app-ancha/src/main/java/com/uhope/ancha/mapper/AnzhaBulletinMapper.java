package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.Template;
import com.uhope.ancha.dto.AnzhaBulletinDTO;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnzhaBulletinMapper extends Mapper<AnzhaBulletin> {

    List<AnzhaBulletinDTO> selectList(@Param("month") String month,
                                      @Param("title")String title,
                                      @Param("status")String status,
                                      @Param("num")String num);
}