package com.uhope.inspection.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.inspection.domain.ScComtactPerson;
import com.uhope.inspection.service.ComtactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述:联络员名录
 *
 * @author a4994
 * @create 2018-11-07 17:46
 */
@RestController
@RequestMapping("/v1/ComtactPerson")
public class ComtactPersonController {
    @Autowired
    private ComtactPersonService comtactPersonService;

    @RequestMapping("/add")
    public Result<ScComtactPerson> add(
            @RequestParam String inspectionid,
            @RequestParam String name,
            @RequestParam String duty,
             String phone
    ) {
        ScComtactPerson scComtactPerson = new ScComtactPerson();
        scComtactPerson.setDuty(duty);
        scComtactPerson.setName(name);
        scComtactPerson.setPhone(phone);
        scComtactPerson.setInspectionid(inspectionid);
        comtactPersonService.insert(scComtactPerson);
        return ResponseMsgUtil.success(scComtactPerson);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<ScComtactPerson>> selectById(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                  @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                  String inspectionid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ScComtactPerson> list = comtactPersonService.selectById(inspectionid);
        PageInfo<ScComtactPerson> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @PutMapping("/update")
    public Result<ScComtactPerson> update(@RequestParam String id,
                                          String name,
                                          String duty,
                                          String phone) {

        ScComtactPerson scComtactPerson = new ScComtactPerson();
        scComtactPerson.setId(id);
        scComtactPerson.setName(name);
        scComtactPerson.setDuty(duty);
        scComtactPerson.setPhone(phone);
        comtactPersonService.update(scComtactPerson);
        return ResponseMsgUtil.success(scComtactPerson);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        comtactPersonService.remove(id);
        return ResponseMsgUtil.success();
    }


}
