package com.uhope.statistic.web;

import com.github.pagehelper.PageInfo;
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

    /**
     * 新增水质评分细则
     * @param request
     * @param waterAssess
     * @return
     */
    @PostMapping("/addWaterAssess")
    public Result<AmWaterAssess> addWaterAssess(HttpServletRequest request, AmWaterAssess waterAssess) {
        waterAssess.setCreateTime(new Date());
        waterAssess.setCreator(tokenService.getUserIdByRequest(request));
        waterAssessService.insert(waterAssess);
        return ResponseMsgUtil.success(waterAssess);
    }

    /**
     * 删除水质评分细则
     * @param id
     * @return
     */
    @DeleteMapping("/deleteWaterAssess")
    public Result<String> deleteWaterAssess(String id) {
        waterAssessService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    /**
     * 更新水质评分细则
     * @param waterAssess
     * @return
     */
    @PutMapping("/updateWaterAssess")
    public Result<AmWaterAssess> updateWaterAssess(AmWaterAssess waterAssess) {
        waterAssessService.update(waterAssess);
        return ResponseMsgUtil.success(waterAssess);
    }

    /**
     * 水质评分细则列表
     * @param waterQualityRule
     * @param paramType
     * @param waterQualityType
     * @return
     */
    @GetMapping("/listWaterAssess")
    public Result<PageInfo> listWaterAssess(
            @RequestParam(name = "waterQualityRule", required = true) String waterQualityRule
            , @RequestParam(name = "paramType", required = false) String paramType
            , @RequestParam(name = "waterQualityType", required = false) String waterQualityType
    ) {
        Condition condition = new Condition(AmWaterAssess.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("rule_name= '" + waterQualityRule + "'");

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

    /**
     * 根据id查找水质评分细则详情
     * @param id
     * @return
     */
    @GetMapping("/findWaterAssessById")
    public Result<AmWaterAssess> findWaterAssessById(String id) {
        AmWaterAssess amWaterAssess = waterAssessService.get(id);
        return ResponseMsgUtil.success(amWaterAssess);
    }
}
