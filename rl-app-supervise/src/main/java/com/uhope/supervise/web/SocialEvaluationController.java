package com.uhope.supervise.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.core.OrderBy;
import com.uhope.supervise.domain.ShSocialEvaluation;
import com.uhope.supervise.service.ShSocialEvaluationService;
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
 * @date: 18/10/30
 * @description: 社会监督评价
 */
@RestController
@RequestMapping("v1/SocialEvaluation")
public class SocialEvaluationController {

    @Autowired
    private ShSocialEvaluationService socialEvaluationService;
    @Autowired
    private TokenService tokenService;

    /**
     * 评价列表
     * @param termNumber
     * @param riverName
     * @param regionName
     * @param isSatisfied
     * @param supervisor
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/listEvaluation")
    public Result<PageInfo> listEvaluation(
            @RequestParam(name = "termNumber", required = false) String termNumber,
            @RequestParam(name = "riverName", required = false) String riverName,
            @RequestParam(name = "regionName", required = false) String regionName,
            @RequestParam(name = "isSatisfied", required = false) String isSatisfied,
            @RequestParam(name = "supervisor", required = false) String supervisor,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(ShSocialEvaluation.class);
        Example.Criteria criteria = condition.createCriteria();
        if (termNumber != null && termNumber != "") {
            criteria.andCondition("term_number like '%" + termNumber + "%'");
        }
        if (riverName != null && riverName != "") {
            criteria.andCondition("river_name like '%" + riverName + "%'");
        }
        if (regionName != null && regionName != "") {
            criteria.andCondition("region_name like '%" + regionName + "%'");
        }
        if (isSatisfied != null && isSatisfied != "") {
            criteria.andCondition("is_satisfied like '%" + isSatisfied + "%'");
        }
        if (supervisor != null && supervisor != "") {
            criteria.andCondition("supervisor like '%"+ supervisor + "%'");
        }
        condition.orderBy("createTime");
        List<ShSocialEvaluation> list = socialEvaluationService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 新增评价
     * @param socialEvaluation
     * @param request
     * @return
     */
    @PostMapping("/addEvaluation")
    public Result<ShSocialEvaluation> addEvaluation(ShSocialEvaluation socialEvaluation, HttpServletRequest request) {
        socialEvaluation.setEvaluationDate(new Date());
        socialEvaluation.setCreateTime(new Date());
        socialEvaluation.setCreator(tokenService.getUserIdByRequest(request));
        socialEvaluationService.insert(socialEvaluation);
        return ResponseMsgUtil.success(socialEvaluation);
    }

    /**
     * 评价详情
     * @param id
     * @return
     */
    @GetMapping("/detailEvaluation")
    public Result<ShSocialEvaluation> detailEvaluation(String id){
        ShSocialEvaluation shSocialEvaluation = socialEvaluationService.get(id);
        return ResponseMsgUtil.success(shSocialEvaluation);
    }

    /**
     * 删除评价
     * @param id
     */
    @DeleteMapping("/deleteEvaluation")
    public Result<String> deleteEvaluation(String id){
        socialEvaluationService.remove(id);
        return ResponseMsgUtil.success("删除成功");
    }
}
