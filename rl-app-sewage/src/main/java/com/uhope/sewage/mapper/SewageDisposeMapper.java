package com.uhope.sewage.mapper;

import com.uhope.core.Mapper;
import com.uhope.sewage.domain.SewageDispose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SewageDisposeMapper extends Mapper<SewageDispose> {
    public List<SewageDispose> selectList(@Param("issue") String issue, @Param("status")String status,
                                          @Param("createUser") String createUser, @Param("num")String num);


    String selectRole(@Param("id")String id);

    SewageDispose selectHave(String issue);
}
