package com.uhope.rl.resumption.dto.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/17
 * @description: 问题统计DTO
 */
@Data
public class ProblemStatisticDTO implements Serializable {
    /**
     * 区名
     */
    private String regionName;
    /**
     * 区id
     */
    private String regionId;
    /**
     * 分类列表
     */
    private List<ProblemTypeStatisticDTO> list;
}
