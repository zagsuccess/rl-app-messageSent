package com.uhope.ancha.service;

import com.uhope.ancha.domain.AnzhaStandingBook;
import com.uhope.ancha.dto.AnzhaStandingBookDTO;
import com.uhope.core.Service;

import java.util.List;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface AnzhaStandingBookService extends Service<AnzhaStandingBook, AnzhaStandingBookDTO, String> {

    public List<AnzhaStandingBook> list(String bulletinid);
}
