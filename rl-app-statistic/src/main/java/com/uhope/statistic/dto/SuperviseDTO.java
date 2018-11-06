package com.uhope.statistic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/05
 * @description: 社会监督成绩统计
 */
@Data
public class SuperviseDTO implements Serializable {

    private String regionName;

    private Double grade;
}
