package com.uhope.statistic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/05
 * @description: 区域地表水环境质量考核成绩
 */
@Data
public class SurfaceWaterDTO implements Serializable {

    private String regionName;

    private Double grade;

}
