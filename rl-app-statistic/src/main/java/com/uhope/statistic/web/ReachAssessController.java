package com.uhope.statistic.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.domain.AmReachAssess;
import com.uhope.statistic.service.ReachAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/11/13
 * @description: 河段考核配置
 */
@RestController
@RequestMapping("/v1/reachAssess")
public class ReachAssessController {

    @Autowired
    private ReachAssessService reachAssessService;

    @PostMapping("/addReachAssess")
    public Result<AmReachAssess> addReachAssess(AmReachAssess amReachAssess){
        reachAssessService.insert(amReachAssess);
        return ResponseMsgUtil.success(amReachAssess);
    }

    @DeleteMapping("/deleteReachAssess")
    public Result<String> deleteReachAssess(String id){
        reachAssessService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    @PutMapping("/updateReachAssess")
    public Result<AmReachAssess> updateReachAssess(AmReachAssess reachAssess){
        reachAssessService.update(reachAssess);
        return ResponseMsgUtil.success(reachAssess);
    }

    @GetMapping("/listReachAssess")
    public Result<PageInfo> listReachAssess(
            @RequestParam(name = "regionName", required = false)String regionName
            ,@RequestParam(name = "isSewage", required = false)String isSewage
            ,@RequestParam(name = "isCompensation", required = false)String isCompensation
            ,@RequestParam(name = "isScoringRules", required = false)String isScoringRules
            ,@RequestParam(name = "isWaterQualityAssess", required = false)String isWaterQualityAssess
            ,@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            ,@RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(AmReachAssess.class);
        Example.Criteria criteria = condition.createCriteria();
        if (regionName != null && regionName != ""){
            criteria.andCondition("region_name like %" + regionName + "%");
        }
        if (isSewage != null && isSewage != ""){
            criteria.andCondition("is_sewage_factory like %" + isSewage + "%");
        }
        if (isCompensation != null && isCompensation != ""){
            criteria.andCondition("is_compensation like %" + isCompensation + "%");
        }
        if (isScoringRules != null && isScoringRules != ""){
            criteria.andCondition("scoring_rules like %" + isScoringRules + "%");
        }
        if (isWaterQualityAssess != null && isWaterQualityAssess != ""){
            criteria.andCondition("is_water_quality_assess like %" + isWaterQualityAssess + "%");
        }
        
        List<AmReachAssess> list = reachAssessService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/findReachAssessById")
    public Result<AmReachAssess> findReachAssessById(String id){
        AmReachAssess reachAssess = reachAssessService.get(id);
        return ResponseMsgUtil.success(reachAssess);
    }
}
