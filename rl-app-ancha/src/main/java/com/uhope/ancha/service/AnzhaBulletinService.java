package com.uhope.ancha.service;

import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.dto.AnzhaBulletinDTO;
import com.uhope.core.Service;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaBulletinService extends Service<AnzhaBulletin, AnzhaBulletinDTO, String> {

    public PageInfo<AnzhaBulletinDTO> list(Integer pageNumber, Integer pageSize, String month, String title, String status, String num);
}
