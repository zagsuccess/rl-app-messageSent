package com.uhope.assessment.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.assessment.domain.AssessGradeType;
import com.uhope.assessment.service.AssessGradeTypeService;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/22
 * @description:
 */
@RestController
@RequestMapping("/v1/assessment")
public class AssessGradeTypeController {

    @Autowired
    private AssessGradeTypeService assessGradeTypeService;

    /**
     * 增加考核评分
     * @param assessGradeType
     */
    @PostMapping("/add")
    public Result<AssessGradeType> add(AssessGradeType assessGradeType){
        assessGradeTypeService.insert(assessGradeType);
        return ResponseMsgUtil.success(assessGradeType);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id){
        assessGradeTypeService.remove(id);
        return ResponseMsgUtil.success();
    }

    /**
     * 修改
     * @param assessGradeType
     * @return
     */
    @PutMapping("/update")
    public Result<AssessGradeType> update(AssessGradeType assessGradeType){
        assessGradeTypeService.update(assessGradeType);
        return ResponseMsgUtil.success(assessGradeType);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<AssessGradeType> detail(@RequestParam String id){
        AssessGradeType assessGradeType = assessGradeTypeService.get(id);
        return ResponseMsgUtil.success(assessGradeType);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link com.uhope.base.constants.Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link com.uhope.base.constants.Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<List<AssessGradeType>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                              @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<AssessGradeType> list = assessGradeTypeService.find();
        PageInfo<AssessGradeType> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(list);
    }

}
