package com.uhope.bulletin.mapper;

import com.uhope.core.Mapper;
import com.uhope.bulletin.domain.Bulletin;

public interface BulletinMapper extends Mapper<Bulletin> {
    public Bulletin selectByFirst(Integer type);
}
