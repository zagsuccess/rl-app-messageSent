package com.uhope.template.mapper;

import com.uhope.core.Mapper;
import com.uhope.template.domain.SewageDispose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SewageDisposeMapper extends Mapper<SewageDispose> {
    public List<SewageDispose> selectList(@Param("issue") String issue, @Param("status")Integer status,
                                          @Param("createUser") String createUser, @Param("num")Integer num);

    public String selectSHZB();

    public String selectSHBJ();
}
