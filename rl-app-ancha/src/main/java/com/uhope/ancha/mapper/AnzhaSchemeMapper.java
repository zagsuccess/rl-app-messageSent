package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaScheme;
import com.uhope.ancha.dto.AnzhaSchemeDTO;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnzhaSchemeMapper extends Mapper<AnzhaScheme> {
    AnzhaScheme selectHave(@Param("issue") String issue);

    List<AnzhaSchemeDTO> selectList(@Param("title")String title, @Param("issue")String issue);
}