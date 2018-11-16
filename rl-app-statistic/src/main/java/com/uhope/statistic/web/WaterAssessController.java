package com.uhope.statistic.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.domain.AmWaterAssess;
import com.uhope.statistic.service.WaterAssessService;
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
 * @date: 18/11/14
 * @description: 水质评分细则表
 */
@RestController
@RequestMapping("/v1/waterAssess")
public class WaterAssessController {

    @Autowired
    private WaterAssessService waterAssessService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/addWaterAssess")
    public Result<AmWaterAssess> addWaterAssess(HttpServletRequest request, AmWaterAssess waterAssess) {
        waterAssess.setCreateTime(new Date());
        waterAssess.setCreator(tokenService.getUserIdByRequest(request));
        waterAssessService.insert(waterAssess);
        return ResponseMsgUtil.success(waterAssess);
    }

    @DeleteMapping("/deleteWaterAssess")
    public Result<String> deleteWaterAssess(String id) {
        waterAssessService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    @PutMapping("/updateWaterAssess")
    public Result<AmWaterAssess> updateWaterAssess(AmWaterAssess waterAssess) {
        waterAssessService.update(waterAssess);
        return ResponseMsgUtil.success(waterAssess);
    }

    @GetMapping("/listWaterAssess")
    public Result<PageInfo> listWaterAssess(
            @RequestParam(name = "waterQualityRule", required = true) String waterQualityRule
            , @RequestParam(name = "paramType", required = false) String paramType
            , @RequestParam(name = "waterQualityType", required = false) String waterQualityType
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ) {
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(AmWaterAssess.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("assess_rule like '%" + waterQualityRule + "%'");

        if (paramType != null && paramType != "") {
            criteria.andCondition("param_type like '%" + paramType + "%'");
        }
        if (waterQualityType != null && waterQualityType != "") {
            criteria.andCondition("water_quality_type like '%" + waterQualityType + "%'");
        }

        condition.orderBy("sortOrder");
        List<AmWaterAssess> list = waterAssessService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);

        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/findWaterAssessById")
    public Result<AmWaterAssess> findWaterAssessById(String id) {
        AmWaterAssess amWaterAssess = waterAssessService.get(id);
        return ResponseMsgUtil.success(amWaterAssess);
    }
}
