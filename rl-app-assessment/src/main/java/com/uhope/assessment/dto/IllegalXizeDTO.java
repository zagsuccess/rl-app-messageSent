package com.uhope.assessment.dto;

import com.uhope.assessment.domain.IllegalXize;

import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/10/22
 * @Time : 9:51
 * Aim :
 */

public class IllegalXizeDTO extends IllegalXize {
    private List<IllegalXizeDTO> childList;

    public List<IllegalXizeDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<IllegalXizeDTO> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "IllegalXizeDTO{" +
                "childList=" + childList +
                '}';
    }
}
