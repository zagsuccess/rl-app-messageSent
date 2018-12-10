package com.uhope.water.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.water.domain.SurfaceWater;
import com.uhope.water.service.SurfaceWaterService;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import com.uhope.water.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 描述:
 * 水质评分报告
 *
 * @author a4994
 * @create 2018-09-28 10:15
 */
@RestController
@RequestMapping("/v1/SurfaceWater")
public class SurfaceWaterController {
    @Autowired
    private SurfaceWaterService surfaceWaterService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result<SurfaceWater> add(@RequestParam String title,
                                    @RequestParam String issue,
                                    @RequestParam String createtime,
                                    @RequestParam String createUser,
                                    @RequestParam String remark) throws ParseException {
        SurfaceWater surfaceWater = new SurfaceWater();
        surfaceWater.setTitle(title);
        surfaceWater.setIssue(issue);
        surfaceWater.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createtime));
        surfaceWater.setCreateuser(createUser);
        surfaceWater.setRemark(remark);
        surfaceWater.setStatus("2");
        surfaceWaterService.insert(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    //查询是否这个月份是否创建过报告
    @GetMapping("/selectHave")
    public Result<String> selectHave(@RequestParam String issue) {
        SurfaceWater surfaceWater = surfaceWaterService.selectHave(issue);
        String msg="没有";
        if (surfaceWater!=null){
            msg="有";
        }
        return ResponseMsgUtil.success(msg);
    }

    @GetMapping("/list")
    public Result<PageInfo<SurfaceWater>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                               @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                               @RequestParam String objectid,
                                               String issue, String status, String createUser) {
        String num="";
        if("市河长办".equals(surfaceWaterService.selectRole(objectid))){
            num="2";
        }
        return ResponseMsgUtil.success(surfaceWaterService.list(pageNumber,pageSize,issue,status,createUser,num));
    }

    @PutMapping("/update")
    public Result<SurfaceWater> update(@RequestParam String id,
                                       @RequestParam String status) {

        SurfaceWater surfaceWater = new SurfaceWater();
        surfaceWater.setId(id);
        surfaceWater.setStatus(status);
        surfaceWaterService.update(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    @PutMapping("/updatelist")
    public Result<SurfaceWater> updatelist(@RequestParam String id,
                                           @RequestParam String title,
                                           @RequestParam String issue,
                                           @RequestParam String createtime,
                                           @RequestParam String createUser,
                                           @RequestParam String remark) throws ParseException {

        SurfaceWater surfaceWater = new SurfaceWater();
        surfaceWater.setId(id);
        surfaceWater.setTitle(title);
        surfaceWater.setIssue(issue);
        surfaceWater.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createtime));
        surfaceWater.setCreateuser(createUser);
        surfaceWater.setRemark(remark);
        surfaceWater.setStatus("2");
        surfaceWaterService.update(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    @GetMapping("/detail")
    public Result<SurfaceWater> detail (@RequestParam String id) {
        return ResponseMsgUtil.success(surfaceWaterService.get(id));
    }

    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(surfaceWaterService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if("环保局".equals(surfaceWaterService.selectRole(userDTO.getId()))){
            grade="01";
        }

        return ResponseMsgUtil.success(grade);
    }

    @GetMapping("/userinfo1")
    public Result<String> userinfo(String id){
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(surfaceWaterService.selectRole(id))){
            grade="02";
        }

        if("环保局".equals(surfaceWaterService.selectRole(id))){
            grade="01";
        }

        return ResponseMsgUtil.success(grade);
    }
}
