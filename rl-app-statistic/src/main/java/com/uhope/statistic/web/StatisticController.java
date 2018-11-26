package com.uhope.statistic.web;

import com.google.common.collect.Lists;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.dto.RiverStatisticDTO;
import com.uhope.statistic.dto.SuperviseDTO;
import com.uhope.statistic.dto.SurfaceWaterDTO;
import com.uhope.statistic.dto.WaterQualityDTO;
import com.uhope.statistic.service.StatisticService;
import com.uhope.statistic.utils.WaterCalc;
import com.uhope.uip.service.RoleService;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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
        // 1. 河湖水体水质感官情况
        // 2. 河湖区界断面出入境水体水质变化情况（分别计算跨区河道、水库）
        // 3. 河湖口门排水情况
        // 4. 污水处理厂达标排放情况
        // 获得水质数据
        //
        List<RiverStatisticDTO> list = Lists.newArrayList();
        //获得水质数据
        List<WaterQualityDTO> waterQualityDTOS = statisticService.listWaterQualityData(date);
        //计算水质数据扣分项
        for (WaterQualityDTO waterQualityDTO : waterQualityDTOS) {
            RiverStatisticDTO riverStatisticDTO = WaterCalc.calcResult(waterQualityDTO);
            list.add(riverStatisticDTO);
        }
        //返回水质总分
        return ResponseMsgUtil.success(list);
    }

    /**
     * 区域考核排名统计
     * @param date 查询月份
     */
    @GetMapping("/regionStatistic")
    public void regionStatistic(
            @RequestParam(required = false, name = "date") String date
    ) {
        //1. 获得区域地表水环境质量考核成绩
        List<SurfaceWaterDTO> surfaceWaterDTOS = statisticService.listSurfaceWaterDTO(date);
        //2. 获得河湖生态环境质量考核成绩
        List<RiverStatisticDTO> listRiverStatisticDTO = Lists.newArrayList();
        List<WaterQualityDTO> waterQualityDTOS = statisticService.listWaterQualityData(date);
        //计算水质数据扣分项
        for (WaterQualityDTO waterQualityDTO : waterQualityDTOS) {
            RiverStatisticDTO riverStatisticDTO = WaterCalc.calcResult(waterQualityDTO);
            listRiverStatisticDTO.add(riverStatisticDTO);
        }
        //3. 计算社会监督评价考核成绩
        List<SuperviseDTO> superviseDTOS = statisticService.listSuperviseDTOs(date);
    }
}
