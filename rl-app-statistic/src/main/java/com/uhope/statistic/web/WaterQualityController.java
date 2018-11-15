package com.uhope.statistic.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
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

    @PostMapping("/addWaterQuality")
    public Result<AmWaterQuality> addWaterQuality(HttpServletRequest request, AmWaterQuality waterQuality){
        waterQuality.setCreateTime(new Date());
        waterQuality.setCreator(tokenService.getUserIdByRequest(request));
        waterQualityService.insert(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    @DeleteMapping("/deleteWaterQuality")
    public Result<String> deleteWaterQuality(String id){
        waterQualityService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    @PutMapping("/updateWaterQuality")
    public Result<AmWaterQuality> updateWaterQuality(AmWaterQuality waterQuality){
        waterQualityService.update(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    @GetMapping("/listWaterQuality")
    public Result<PageInfo> listWaterQuality(
            @RequestParam(name = "paramType", required = false) String paramType
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(AmWaterQuality.class);
        Example.Criteria criteria = condition.createCriteria();

        if (paramType != null && paramType != "") {
            criteria.andCondition("param_type like '%" + paramType + "%'");
        }
        condition.orderBy("sortOrder");
        List<AmWaterQuality> list = waterQualityService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/findWaterQualityById")
    public Result<AmWaterQuality> findWaterQualityById(String id){
        AmWaterQuality waterQuality = waterQualityService.get(id);
        return ResponseMsgUtil.success(waterQuality);
    }
}
