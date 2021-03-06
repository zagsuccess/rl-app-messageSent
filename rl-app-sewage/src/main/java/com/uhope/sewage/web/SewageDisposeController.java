package com.uhope.sewage.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.sewage.domain.SewageDispose;
import com.uhope.sewage.service.SewageDisposeService;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.uhope.sewage.utils.CommonUtil.getFeigionServiceResultData;

/**
 * 描述:
 * 水质评分报告
 *
 * @author a4994
 * @create 2018-09-28 10:15
 */
@RestController
@RequestMapping("/v1/SewageDispose")
public class SewageDisposeController {
    @Autowired
    private SewageDisposeService sewageDisposeService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result<SewageDispose> add(@RequestParam String title,
                                     @RequestParam String issue,
                                     @RequestParam String createtime,
                                     @RequestParam String createUser,
                                     @RequestParam String remark) throws ParseException {
        SewageDispose sewageDispose = new SewageDispose();
        sewageDispose.setTitle(title);
        sewageDispose.setIssue(issue);
        sewageDispose.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createtime));
        sewageDispose.setCreateuser(createUser);
        sewageDispose.setRemark(remark);
        sewageDispose.setStatus("2");
        sewageDisposeService.insert(sewageDispose);
        return ResponseMsgUtil.success(sewageDispose);
    }

    //查询是否这个月份是否创建过报告
    @GetMapping("/selectHave")
    public Result<String> selectHave(@RequestParam String issue) {
        SewageDispose waterQuality = sewageDisposeService.selectHave(issue);
        String msg="没有";
        if (waterQuality!=null){
            msg="有";
        }
        return ResponseMsgUtil.success(msg);
    }

    @GetMapping("/list")
    public Result<PageInfo<SewageDispose>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                               @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                @RequestParam String objectid,
                                                String issue, String status, String createUser) {
        String num="";
        if("市河长办".equals(sewageDisposeService.selectRole(objectid))){
            num="2";
        }
        return ResponseMsgUtil.success(sewageDisposeService.list(pageNumber,pageSize,issue,status,createUser,num));
    }

    @PutMapping("/update")
    public Result<SewageDispose> update(@RequestParam String id,
                                       @RequestParam String status) {

        SewageDispose surfaceWater = new SewageDispose();
        surfaceWater.setId(id);
        surfaceWater.setStatus(status);
        sewageDisposeService.update(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    @PutMapping("/updatelist")
    public Result<SewageDispose> updatelist(@RequestParam String id,
                                           @RequestParam String title,
                                           @RequestParam String issue,
                                           @RequestParam String createtime,
                                           @RequestParam String createUser,
                                           @RequestParam String remark) throws ParseException {

        SewageDispose surfaceWater = new SewageDispose();
        surfaceWater.setId(id);
        surfaceWater.setTitle(title);
        surfaceWater.setIssue(issue);
        surfaceWater.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createtime));
        surfaceWater.setCreateuser(createUser);
        surfaceWater.setRemark(remark);
        surfaceWater.setStatus("2");
        sewageDisposeService.update(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    @GetMapping("/detail")
    public Result<SewageDispose> detail (@RequestParam String id) {
        return ResponseMsgUtil.success(sewageDisposeService.get(id));
    }

    @GetMapping("/userinfo")
    public Result<Integer> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        int grade=00;
        if("市河长办".equals(sewageDisposeService.selectRole(userDTO.getId()))){
            grade=02;
        }

        if("排监处".equals(sewageDisposeService.selectRole(userDTO.getId()))){
            grade=03;
        }

        return ResponseMsgUtil.success(grade);
    }

    @GetMapping("/userinfo1")
    public Result<String> userinfo(String id){
        //获取当前用户信息
       /* UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }*/
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(sewageDisposeService.selectRole(id))){
            grade="02";
        }

        if("排监处".equals(sewageDisposeService.selectRole(id))){
            grade="01";
        }

        return ResponseMsgUtil.success(grade);
    }
}
