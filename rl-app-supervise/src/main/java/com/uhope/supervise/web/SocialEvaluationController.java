package com.uhope.supervise.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.supervise.domain.ShSocialEvaluation;
import com.uhope.supervise.service.ShSocialEvaluationService;
import com.uhope.supervise.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(SocialEvaluationController.class);

    @Autowired
    private ShSocialEvaluationService socialEvaluationService;
    @Autowired
    private TokenService tokenService;

    /**
     * 新增评价
     * @param socialEvaluation 社会监督满意评价对象
     * @param request 请求对象
     * @return 社会监督满意评价对象
     */
    @PostMapping("/addEvaluation")
    public Result<ShSocialEvaluation> addEvaluation(ShSocialEvaluation socialEvaluation, HttpServletRequest request) {
        //联系方式可能后面会加，所以先设置上
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        socialEvaluation.setContactType(userDTO==null ? "":userDTO.getCellphone());
        socialEvaluation.setCreateTime(new Date());
        socialEvaluation.setCreator(tokenService.getUserIdByRequest(request));
        socialEvaluationService.insert(socialEvaluation);
        return ResponseMsgUtil.success(socialEvaluation);
    }

    /**
     * 删除评价
     * @param id 社会监督评价id
     */
    @DeleteMapping("/deleteEvaluation")
    public Result<String> deleteEvaluation(String id){
        socialEvaluationService.remove(id);
        return ResponseMsgUtil.success("删除成功");
    }

    /**
     * 修改社会监督评价
     * @param socialEvaluation 修改后的社会监督评价表单对象
     * @return 修改后的社会监督评价表单对象
     */
    @PutMapping("/updateEvaluation")
    public Result<ShSocialEvaluation> updateEvaluation(ShSocialEvaluation socialEvaluation){
        socialEvaluationService.update(socialEvaluation);
        return ResponseMsgUtil.success(socialEvaluationService.get(socialEvaluation.getId()));
    }

    /**
     * 评价列表
     * @param termNumber 搜索-期号
     * @param riverName 搜索-流域名
     * @param regionName 搜索-区域名
     * @param isSatisfied 搜索-是否满意
     * @param supervisor 搜索-监督员
     * @param problemPosition 搜索-具体位置
     * @param pageNumber 分页-页号
     * @param pageSize 分页-分页大小
     * @return 社会监督评价列表
     */
    @GetMapping("/listEvaluation")
    public Result<PageInfo> listEvaluation(
            @RequestParam(name = "termNumber", required = false) String termNumber,
            @RequestParam(name = "riverName", required = false) String riverName,
            @RequestParam(name = "regionName", required = false) String regionName,
            @RequestParam(name = "isSatisfied", required = false) String isSatisfied,
            @RequestParam(name = "supervisor", required = false) String supervisor,
            @RequestParam(name = "problemPosition", required = false) String problemPosition,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(ShSocialEvaluation.class);
        Example.Criteria criteria = condition.createCriteria();
        if (termNumber != null && !"".equals(termNumber)) {
            criteria.andCondition("term_number like '%" + termNumber + "%'");
        }
        if (riverName != null && !"".equals(riverName)) {
            criteria.andCondition("river_name like '%" + riverName + "%'");
        }
        if (regionName != null && !"".equals(regionName)) {
            criteria.andCondition("region_name like '%" + regionName + "%'");
        }
        if (isSatisfied != null && !"".equals(isSatisfied)) {
            criteria.andCondition("is_satisfied like '%" + isSatisfied + "%'");
        }
        if (supervisor != null && !"".equals(supervisor)) {
            criteria.andCondition("supervisor like '%"+ supervisor + "%'");
        }
        if (problemPosition != null && !"".equals(problemPosition)) {
            criteria.andCondition("problem_position like '%" + problemPosition + "%'");
        }
        condition.orderBy("termNumber");
        List<ShSocialEvaluation> list = socialEvaluationService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 评价详情
     * @param id 社会监督举报id
     * @return 社会监督举报详情
     */
    @GetMapping("/detailEvaluation")
    public Result<ShSocialEvaluation> detailEvaluation(String id){
        ShSocialEvaluation shSocialEvaluation = socialEvaluationService.get(id);
        return ResponseMsgUtil.success(shSocialEvaluation);
    }
}
