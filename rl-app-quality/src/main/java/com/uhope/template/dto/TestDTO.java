package com.uhope.template.dto;

import com.uhope.uip.dto.UserDTO;

/**
 * @Author :shujihui
 * @Date : 2018/10/18
 * @Time : 18:33
 * Aim :
 */

public class TestDTO extends UserDTO {
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
