package com.uhope.statistic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/05
 * @description: 统计使用DTO
 */
@Data
public class SuperviseDTO implements Serializable {

    /**
     * 区号
     */
    private String regionId;

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 区成绩
     */
    private Double grade;
}
