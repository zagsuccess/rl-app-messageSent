package com.uhope.statistic.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.statistic.domain.AmReachAssess;
import com.uhope.statistic.service.ReachAssessService;
import com.uhope.statistic.service.WaterQualityRuleService;
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
@RequestMapping("v1/reachAssess")
public class ReachAssessController {

    @Autowired
    private ReachAssessService reachAssessService;
    @Autowired
    private WaterQualityRuleService waterQualityRuleService;

    @PostMapping("/addReachAssess")
    public Result<AmReachAssess> addReachAssess(AmReachAssess amReachAssess){
        reachAssessService.insert(amReachAssess);
        return ResponseMsgUtil.success(amReachAssess);
    }

    /**
     * 删除河段考核配置
     * @param id
     * @return
     */
    @DeleteMapping("/deleteReachAssess")
    public Result<String> deleteReachAssess(String id){
        reachAssessService.remove(id);
        return ResponseMsgUtil.success(id);
    }

    /**
     * 更新河段考核配置
     * @param reachAssess
     * @return
     */
    @PutMapping("/updateReachAssess")
    public Result<AmReachAssess> updateReachAssess(AmReachAssess reachAssess){
        reachAssessService.update(reachAssess);
        return ResponseMsgUtil.success(reachAssess);
    }

    /**
     * 河段考核配置列表
     * @param regionName
     * @param isSewage
     * @param isCompensation
     * @param isScoringRules
     * @param isWaterQualityAssess
     * @param pageNumber
     * @param pageSize
     * @return
     */
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
            criteria.andCondition("region_name like '%" + regionName + "%'");
        }
        if (isSewage != null && isSewage != ""){
            criteria.andCondition("is_sewage_factory like '%" + isSewage + "%'");
        }
        if (isCompensation != null && isCompensation != ""){
            criteria.andCondition("is_compensation like '%" + isCompensation + "%'");
        }
        if (isScoringRules != null && isScoringRules != ""){
            criteria.andCondition("scoring_rules like '%" + isScoringRules + "%'");
        }
        if (isWaterQualityAssess != null && isWaterQualityAssess != ""){
            criteria.andCondition("is_water_quality_assess like '%" + isWaterQualityAssess + "%'");
        }
        
        List<AmReachAssess> list = reachAssessService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        
        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 通过id查找河段考核
     * @param id
     * @return
     */
    @GetMapping("/findReachAssessById")
    public Result<AmReachAssess> findReachAssessById(String id){
        AmReachAssess reachAssess = reachAssessService.get(id);
        return ResponseMsgUtil.success(reachAssess);
    }

    /**
     * 16个区列表
     * @return
     */
    @GetMapping("/regionList")
    public Result<List<String>> regionList(){
        List<String> list = reachAssessService.regionList();
        return ResponseMsgUtil.success(list);
    }

    /**
     * 水质评分规则列表
     * @return
     */
    @GetMapping("/waterQualityRuleList")
    public Result<List<String>> waterQualityRuleList(){
        List<String> list = reachAssessService.waterQualityRuleList();
        return ResponseMsgUtil.success(list);
    }

    /**
     * 断面列表
     * @return
     */
    @GetMapping("/listSections")
    public Result<List<String>> listSections(){
        return ResponseMsgUtil.success(reachAssessService.listSections());
    }
}
