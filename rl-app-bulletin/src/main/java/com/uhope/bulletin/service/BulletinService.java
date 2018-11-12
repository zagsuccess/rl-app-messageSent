package com.uhope.bulletin.service;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.core.Service;
import com.uhope.bulletin.domain.Bulletin;
import com.uhope.bulletin.dto.BulletinDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

/**
 * @author: StivenYang
 * @date: 2018/9/8
 * @description: 暗查暗访表-Service接口类
 */
public interface BulletinService extends Service<Bulletin, BulletinDTO, String> {

    /**
     * 首页展示
     *
     * @param type
     * @return
     */
    public List<Bulletin> selectByFirst(Integer type);

    PageInfo<Bulletin> list(Integer pageNumber, Integer pageSize, Integer type, String post_time);
}
