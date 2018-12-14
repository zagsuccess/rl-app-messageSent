package com.uhope.statistic.dto;

import com.uhope.statistic.domain.AmReachAssess;
import com.uhope.statistic.domain.AmWaterQualityRule;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang Jiaheng
 * @date: 18/12/11
 * @description: 考核河段中间表
 */
@Data
public class KValueDTO extends AmReachAssess implements Serializable {

    /**
     * 期号
     */
    private Double issue;

    /**
     * k值字段：根据河段配置表查数据库得到
     */
    private Double kTp;
    private Double kNh4n;
    private Double kDo;
    private Double kCodmn;

    /**
     * n值字段：根据k值分等级扣分计算得到，最多60分
     */
    private Double nTp;
    private Double nNh4n;
    private Double nDo;
    private Double nCodmn;

    /**
     * 平均浓度字段：根据河段配置查数据库得到
     */
    private Double cTp;
    private Double cNh4n;
    private Double cDo;
    private Double cCodmn;

    /**
     * 水质等级字段：根据河段平均浓度计算
     */
    private Integer levelTp;
    private Integer levelNh4n;
    private Integer levelDo;
    private Integer levelCodmn;

    /**
     * f值字段:f根据水质等级字段确定
     */
    private Double fTp;
    private Double fNh4n;
    private Double fDo;
    private Double fCodmn;

    /**
     * 最后计算结果字段：N = n * f
     */
    private Double result;
}
