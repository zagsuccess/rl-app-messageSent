package com.uhope.spotcheck.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.spotcheck.domain.ScSpotcheck;
import com.uhope.spotcheck.service.ScSpotcheckProblemService;
import com.uhope.spotcheck.service.ScSpotcheckService;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/24
 * @description:
 */
@RestController
@RequestMapping("v1/spotcheck")
public class ScSpotcheckController {

    @Autowired
    private ScSpotcheckProblemService scSpotcheckProblemService;
    @Autowired
    private ScSpotcheckService scSpotcheckService;
    @Autowired
    private TokenService tokenService;

    /**
     * 新增抽查记录
     * @param scSpotcheck
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result<ScSpotcheck> add(ScSpotcheck scSpotcheck, HttpServletRequest request){
        scSpotcheck.setCreateTime(new Date());
        scSpotcheck.setStatus(0);
        scSpotcheck.setCreator(tokenService.getUserIdByRequest(request));
        scSpotcheckService.insert(scSpotcheck);
        return ResponseMsgUtil.success(scSpotcheck);
    }

    /**
     * 查看抽查记录详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<ScSpotcheck> detail(String id){
        return ResponseMsgUtil.success(scSpotcheckService.get(id));
    }

    /**
     * 查看抽查记录列表
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<ScSpotcheck>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                 @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(ScSpotcheck.class);
        condition.orderBy("createTime");
        List<ScSpotcheck> list = scSpotcheckService.findByCondition(condition);
        PageInfo<ScSpotcheck> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/listSendPerson")
    public Result<List<String>> listSendPerson(){
        //查找所有的市河长
        return ResponseMsgUtil.success(scSpotcheckProblemService.listSendPerson());
    }

    @GetMapping("/listRegion")
    public Result<List<String>> listRegion(){
        //查找所有的16个区
        return ResponseMsgUtil.success(scSpotcheckProblemService.listRegion());
    }

    @GetMapping("/listRiver")
    public void listRiver(@RequestParam String regionStr){
        //得到选中的区字符串，例如：河东区，河西区，津南区

        //分解字符串，得到区名
        //根据区名取得所对应的所有河段
        //返回所有的河段
    }

    @GetMapping("/detailCheckResult")
    public void detailCheckResult(@RequestParam String id){

    }
}
