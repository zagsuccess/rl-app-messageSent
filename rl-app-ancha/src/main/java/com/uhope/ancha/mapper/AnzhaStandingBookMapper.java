package com.uhope.ancha.mapper;

import com.uhope.ancha.domain.AnzhaStandingBook;
import com.uhope.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnzhaStandingBookMapper extends Mapper<AnzhaStandingBook> {
    List<AnzhaStandingBook> selectList(@Param("bulletinid") String bulletinid);
}