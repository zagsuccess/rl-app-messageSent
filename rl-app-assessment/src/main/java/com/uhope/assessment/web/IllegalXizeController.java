package com.uhope.assessment.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.assessment.domain.IllegalXize;
import com.uhope.assessment.dto.IllegalXizeDTO;
import com.uhope.assessment.service.IllegalXizeService;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/10/22
 * @Time : 10:01
 * Aim :
 */
@RestController
@RequestMapping("/v1/IllegalXize")
public class IllegalXizeController {
    @Autowired
    private IllegalXizeService illegalXizeService;

    @PostMapping("/add")
    public Result<IllegalXize> add(@RequestParam String gradeIllegal) {
        IllegalXize illegalXize = new IllegalXize();
        illegalXize.setGradeillegal(gradeIllegal);
        illegalXize.setLevel(1);
        illegalXizeService.insert(illegalXize);
        return ResponseMsgUtil.success(illegalXize);
    }

    @PostMapping("/add1")
    public Result<IllegalXize> add1(@RequestParam String id,
                                    @RequestParam String gradetype) {
        IllegalXize illegalXize = new IllegalXize();
        illegalXize.setGradetype(gradetype);
        illegalXize.setLevel(2);
        illegalXize.setParentid(id);
        illegalXizeService.insert(illegalXize);
        return ResponseMsgUtil.success(illegalXize);
    }

    @PostMapping("/add2")
    public Result<IllegalXize> add2(@RequestParam String id,
                                    @RequestParam String gradedetailed,
                                    @RequestParam String gradeway,
                                    @RequestParam String deductMarks,
                                    @RequestParam String processLimitted) {
        IllegalXize illegalXize = new IllegalXize();
        illegalXize.setParentid(id);
        illegalXize.setGradedetailed(gradedetailed);
        illegalXize.setGradeway(gradeway);
        illegalXize.setDeductMarks(deductMarks);
        illegalXize.setProcessLimitted(processLimitted);
        illegalXizeService.update(illegalXize);
        illegalXize.setLevel(3);
        illegalXizeService.insert(illegalXize);
        return ResponseMsgUtil.success(illegalXize);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        illegalXizeService.remove(id);
        return ResponseMsgUtil.success();
    }

    @GetMapping("/detail")
    public Result<IllegalXize> detail(@RequestParam String id) {
        IllegalXize illegalXize = illegalXizeService.get(id);
        IllegalXize illegalXize2=illegalXizeService.get(illegalXize.getParentid());
        IllegalXize illegalXize3=illegalXizeService.get(illegalXize2.getParentid());
        illegalXize.setGradeillegal(illegalXize3.getGradeillegal());
        illegalXize.setGradetype(illegalXize2.getGradetype());
        return ResponseMsgUtil.success(illegalXize);
    }

    @PutMapping("/update")
    public Result<IllegalXize> update(@RequestParam String id,
                                      @RequestParam String gradedetailed,
                                      @RequestParam String gradeway,
                                      @RequestParam String deductMarks,
                                      @RequestParam String processLimitted) {

        IllegalXize illegalXize = new IllegalXize();
        illegalXize.setId(id);
        illegalXize.setGradedetailed(gradedetailed);
        illegalXize.setGradeway(gradeway);
        illegalXize.setDeductMarks(deductMarks);
        illegalXize.setProcessLimitted(processLimitted);
        illegalXizeService.update(illegalXize);
        return ResponseMsgUtil.success(illegalXize);
    }

    @GetMapping("/list")
    public Result<PageInfo<IllegalXize>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                 @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<IllegalXize> list = illegalXizeService.find();
        PageInfo<IllegalXize> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/list1")
    public Result<List<IllegalXizeDTO>> list1(){
        List<IllegalXizeDTO> list = illegalXizeService.list1();

        for (IllegalXizeDTO illegalXizeDTO : list) {
            illegalXizeDTO.setChildList(getChild(illegalXizeDTO.getId()));
            System.out.println(illegalXizeDTO.getId());
            System.out.println(illegalXizeDTO.getChildList());
        }

        return ResponseMsgUtil.success(list);
    }

    private List<IllegalXizeDTO> getChild(String parentid) {
        List<IllegalXizeDTO> childList = illegalXizeService.list(parentid);

        if (childList.size()==0){
            return null;
        }

        for (IllegalXizeDTO illegalXizeDTO : childList) {
            illegalXizeDTO.setChildList(getChild(illegalXizeDTO.getId()));
        }

        return childList;
    }
}
