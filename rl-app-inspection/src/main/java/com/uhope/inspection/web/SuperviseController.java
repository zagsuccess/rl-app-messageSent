package com.uhope.inspection.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.inspection.domain.ScSupervise;
import com.uhope.inspection.service.SuperviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述:
 *
 * @author a4994
 * @create 2018-11-07 17:47
 */
@RestController
@RequestMapping("/v1/Supervise")
public class SuperviseController {
    @Autowired
    private SuperviseService superviseService;

    @GetMapping("/selectArea")
    public Result<List<String>> selectArea(){
        return ResponseMsgUtil.success(superviseService.selectArea());
    }

    @PostMapping("/add")
    public Result<ScSupervise> add(
            @RequestParam String groupLeader,
            @RequestParam String grouping,
            @RequestParam String area,
            @RequestParam String contact,
            @RequestParam String phone,
            @RequestParam String department,
            @RequestParam String inspectionid,
            @RequestParam String supervisePerson,
            @RequestParam String personPhone
    ){
        ScSupervise scSupervise =new ScSupervise();
        scSupervise.setArea(area);
        scSupervise.setContact(contact);
        scSupervise.setDepartment(department);
        scSupervise.setGrouping(grouping);
        scSupervise.setPersonPhone(personPhone);
        scSupervise.setSupervisePerson(supervisePerson);
        scSupervise.setGroupLeader(groupLeader);
        scSupervise.setPhone(phone);
        scSupervise.setInspectionid(inspectionid);
        superviseService.insert(scSupervise);
        return ResponseMsgUtil.success(scSupervise);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<ScSupervise>> selectById(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                        @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                        String inspectionid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ScSupervise> list = superviseService.selectById(inspectionid);
        PageInfo<ScSupervise> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }
}
