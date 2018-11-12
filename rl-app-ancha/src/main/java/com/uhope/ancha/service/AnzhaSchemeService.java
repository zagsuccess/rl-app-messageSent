package com.uhope.ancha.service;

import com.github.pagehelper.PageInfo;
import com.uhope.ancha.domain.AnzhaScheme;
import com.uhope.ancha.dto.AnzhaSchemeDTO;
import com.uhope.core.Service;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaSchemeService extends Service<AnzhaScheme, AnzhaSchemeDTO, String> {

    public AnzhaScheme selectHave(String issue);

    public PageInfo<AnzhaSchemeDTO> list(Integer pageNumber, Integer pageSize, String title, String issue);
}
