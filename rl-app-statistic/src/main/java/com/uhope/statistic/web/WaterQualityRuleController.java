package com.uhope.statistic.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.domain.AmWaterQualityRule;
import com.uhope.statistic.service.WaterQualityRuleService;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/13
 * @description: 水质评分规则表
 */
@RestController
@RequestMapping("/v1/waterQualityRule")
public class WaterQualityRuleController {

    @Autowired
    private WaterQualityRuleService waterQualityRuleService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/addWaterRule")
    public Result<AmWaterQualityRule> addWaterRule(HttpServletRequest request, AmWaterQualityRule waterQualityRule) {
        waterQualityRule.setCreateTime(new Date());
        waterQualityRule.setCreator(tokenService.getUserIdByRequest(request));
        waterQualityRuleService.insert(waterQualityRule);
        return ResponseMsgUtil.success(waterQualityRule);
    }

    @DeleteMapping("/deleteWaterRule")
    public Result<String> deleteWaterRule(String id) {
        waterQualityRuleService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    @PutMapping("/updateWaterRule")
    public Result<AmWaterQualityRule> updateWaterRule(AmWaterQualityRule waterQualityRule) {
        waterQualityRuleService.update(waterQualityRule);
        return ResponseMsgUtil.success(waterQualityRule);
    }

    @GetMapping("/listWaterRule")
    public Result<PageInfo> listWaterRule(
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ) {
        PageHelper.startPage(pageNumber, pageSize);
        List<AmWaterQualityRule> list = waterQualityRuleService.find();
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/findWaterRuleById")
    public Result<AmWaterQualityRule> findWaterRuleById(String id) {
        AmWaterQualityRule waterQualityRule = waterQualityRuleService.get(id);
        return ResponseMsgUtil.success(waterQualityRule);
    }
}
