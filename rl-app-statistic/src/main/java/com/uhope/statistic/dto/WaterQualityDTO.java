package com.uhope.statistic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description: 水质数据DTO
 */
@Data
public class WaterQualityDTO implements Serializable {

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 高猛酸盐
     */
    private Double permanganate;

    /**
     * 氨氮
     */
    private Double AN;

    /**
     * 总磷含量
     */
    private Double phosphorus;

    /**
     * 溶氧量
     */
    private Double oxygen;
}
