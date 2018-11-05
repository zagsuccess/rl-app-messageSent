package com.uhope.statistic.web;

import com.google.common.collect.Lists;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.WaterQualityDTO;
import com.uhope.statistic.service.StatisticService;
import com.uhope.statistic.utils.WaterCalc;
import com.uhope.uip.service.RoleService;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/02
 * @description:
 */
@RestController
@RequestMapping("/v1/statistic")
public class StatisticController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StatisticService statisticService;

    /**
     * 河湖水生态质量排名统计
     */
    @GetMapping("/riverStatistic")
    public Result<List<RiverStatisticDTO>> riverStatistic(){
        //获得区域列表
        List<RiverStatisticDTO> list = Lists.newArrayList();
        //获得水质数据
        List<WaterQualityDTO> waterQualityDTOS = statisticService.listWaterQualityData();
        //计算水质数据扣分项
        for (WaterQualityDTO waterQualityDTO : waterQualityDTOS) {
            RiverStatisticDTO riverStatisticDTO = WaterCalc.calcResult(waterQualityDTO);
            list.add(riverStatisticDTO);
        }
        //返回水质总分
        return ResponseMsgUtil.success(list);
    }

    @GetMapping("regionStatistic")
    public void regionStatistic(){
        //1. 获得区列表
        //2. 查水质元素浓度
        //3.
    }
}
