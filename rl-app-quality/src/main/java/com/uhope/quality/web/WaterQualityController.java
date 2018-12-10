package com.uhope.quality.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.quality.domain.WaterQuality;
import com.uhope.quality.service.WaterQualityService;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.uhope.quality.utils.CommonUtil.getFeigionServiceResultData;

/**
 * 描述:
 * 水质评分报告
 *
 * @author a4994
 * @create 2018-09-28 10:15
 */
@RestController
@RequestMapping("/v1/WaterQuality")
public class WaterQualityController {
    @Autowired
    private WaterQualityService waterQualityService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result<WaterQuality> add(@RequestParam String title,
                                    @RequestParam String issue,
                                    @RequestParam String createtime,
                                    @RequestParam String createUser,
                                    @RequestParam String remark,
                                    String batchNumber) throws ParseException {
        WaterQuality waterQuality = new WaterQuality();
        waterQuality.setTitle(title);
        waterQuality.setIssue(issue);
        waterQuality.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createtime));
        waterQuality.setCreateuser(createUser);
        waterQuality.setRemark(remark);
        waterQuality.setBATCHNumber(batchNumber);
        waterQuality.setStatus("2");
        waterQualityService.insert(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    //查询是否这个月份是否创建过报告
    @GetMapping("/selectHave")
    public Result<String> selectHave(@RequestParam String issue) {
        WaterQuality waterQuality = waterQualityService.selectHave(issue);
        String msg="没有";
        if (waterQuality!=null){
            msg="有";
        }
        return ResponseMsgUtil.success(msg);
    }

    @GetMapping("/list")
    public Result<PageInfo<WaterQuality>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                               @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                               @RequestParam String objectid,
                                               String issue, String status, String createUser) {
        String num="";
        if("市河长办".equals(waterQualityService.selectRole(objectid))){
            num="2";
        }
        return ResponseMsgUtil.success(waterQualityService.list(pageNumber,pageSize,issue,status,createUser,num));
    }

    @PutMapping("/update")
    public Result<WaterQuality> update(@RequestParam String id,
                                       @RequestParam String status) {

        WaterQuality waterQuality = new WaterQuality();
        waterQuality.setId(id);
        waterQuality.setStatus(status);
        waterQualityService.update(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    @PutMapping("/updatelist")
    public Result<WaterQuality> updatelist(@RequestParam String id,
                                           @RequestParam String title,
                                           @RequestParam String issue,
                                           @RequestParam String createtime,
                                           @RequestParam String createUser,
                                           @RequestParam String remark,
                                           String batchNumber) throws ParseException {

        WaterQuality waterQuality = new WaterQuality();
        waterQuality.setId(id);
        waterQuality.setTitle(title);
        waterQuality.setIssue(issue);
        waterQuality.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createtime));
        waterQuality.setCreateuser(createUser);
        waterQuality.setRemark(remark);
        waterQuality.setBATCHNumber(batchNumber);
        waterQuality.setStatus("2");
        waterQualityService.update(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    @GetMapping("/detail")
    public Result<WaterQuality> detail (@RequestParam String id) {
        return ResponseMsgUtil.success(waterQualityService.get1(id));
    }

    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示水文水资源中心  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(waterQualityService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if("水文水资源中心".equals(waterQualityService.selectRole(userDTO.getId()))){
            grade="04";
        }

        return ResponseMsgUtil.success(grade);
    }

    @GetMapping("/userinfo1")
    public Result<String> userinfo(String id){

        //默认是00   （00表示都不是  01表示水文水资源中心  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(waterQualityService.selectRole(id))){
            grade="02";
        }

        if("水文水资源中心".equals(waterQualityService.selectRole(id))){
            grade="01";
        }

        return ResponseMsgUtil.success(grade);
    }
}
