package com.uhope.assessment.service;

import com.uhope.assessment.domain.IllegalXize;
import com.uhope.assessment.dto.IllegalXizeDTO;
import com.uhope.core.Service;

import java.util.List;

/**
 * 模版表-Service接口类
 *
 * @author ChenBin on 2018/09/04
 */
public interface IllegalXizeService extends Service<IllegalXize, IllegalXizeDTO, String> {

    List<IllegalXizeDTO> list(String parentid);

    List<IllegalXizeDTO> list1();
}
