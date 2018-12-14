package com.uhope.statistic.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.domain.AmWaterQuality;
import com.uhope.statistic.service.WaterQualityService;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/13
 * @description: 水质变化细则表
 */
@RestController
@RequestMapping("/v1/waterQuality")
public class WaterQualityController {

    @Autowired
    private WaterQualityService waterQualityService;
    @Autowired
    private TokenService tokenService;

    /**
     * 新增水质变化细则
     * @param request
     * @param waterQuality
     * @return
     */
    @PostMapping("/addWaterQuality")
    public Result<AmWaterQuality> addWaterQuality(HttpServletRequest request, AmWaterQuality waterQuality){
        waterQuality.setCreateTime(new Date());
        waterQuality.setCreator(tokenService.getUserIdByRequest(request));
        waterQualityService.insert(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    /**
     * 删除水质变化细则
     * @param id
     * @return
     */
    @DeleteMapping("/deleteWaterQuality")
    public Result<String> deleteWaterQuality(String id){
        waterQualityService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    /**
     * 更新水质变化细则
     * @param waterQuality
     * @return
     */
    @PutMapping("/updateWaterQuality")
    public Result<AmWaterQuality> updateWaterQuality(AmWaterQuality waterQuality){
        waterQualityService.update(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    /**
     * 水质变化细则列表
     * @param waterQualityRule
     * @param paramType
     * @return
     */
    @GetMapping("/listWaterQuality")
    public Result<PageInfo> listWaterQuality(
            @RequestParam(name = "waterQualityRule", required = true) String waterQualityRule
            , @RequestParam(name = "paramType", required = false) String paramType
    ){
        Condition condition = new Condition(AmWaterQuality.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("assess_rule like '%" + waterQualityRule + "%'");

        if (paramType != null && paramType != "") {
            criteria.andCondition("param_type like '%" + paramType + "%'");
        }
        condition.orderBy("sortOrder");
        List<AmWaterQuality> list = waterQualityService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);

        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 水质变化细则详情
     * @param id 水质变化细则id
     * @return 水质变化细则详情
     */
    @GetMapping("/findWaterQualityById")
    public Result<AmWaterQuality> findWaterQualityById(String id){
        AmWaterQuality waterQuality = waterQualityService.get(id);
        return ResponseMsgUtil.success(waterQuality);
    }
}
