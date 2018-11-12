package com.uhope.bulletin.mapper;

import com.github.pagehelper.PageInfo;
import com.uhope.core.Mapper;
import com.uhope.bulletin.domain.Bulletin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BulletinMapper extends Mapper<Bulletin> {
    public List<Bulletin> selectByFirst(Integer type);

    public List<Bulletin> selectlist(@Param("type") Integer type, @Param("post_time") String post_time);
}
