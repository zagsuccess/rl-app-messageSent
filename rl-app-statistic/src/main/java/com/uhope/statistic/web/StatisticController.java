package com.uhope.statistic.web;

import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.dto.*;
import com.uhope.statistic.service.StatisticService;
import com.uhope.uip.service.RoleService;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
     * 河湖水生态环境质量排名统计
     * @param date 查询月份
     */
    @GetMapping("/riverStatistic")
    public Result<List<RiverStatisticDTO>> riverStatistic(
            @RequestParam(required = false, name = "date") String date
    ) {
        if (date == null || date == ""){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            date = LocalDate.now().format(formatter);
        }
        List<RiverStatisticDTO> list = statisticService.getRiverStatistic(date);

        return ResponseMsgUtil.success(list);
    }

    /**
     * 区域考核排名统计
     * @param date 查询月份
     */
    @GetMapping("/regionStatistic")
    public Result<List<RegionStatisticDTO>> regionStatistic(
            @RequestParam(required = false, name = "date") String date
    ) {
        if (date == null || date == ""){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            date = LocalDate.now().format(formatter);
        }
        List<RegionStatisticDTO> list = statisticService.getRegionStatistic(date);

        return ResponseMsgUtil.success(list);
    }
}
