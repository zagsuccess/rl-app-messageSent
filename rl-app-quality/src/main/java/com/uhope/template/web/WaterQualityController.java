package com.uhope.template.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.template.domain.WaterQuality;
import com.uhope.template.dto.TestDTO;
import com.uhope.template.service.WaterQualityService;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.uhope.template.utils.CommonUtil.getFeigionServiceResultData;

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
        waterQuality.setStatus(2);
        waterQualityService.insert(waterQuality);
        return ResponseMsgUtil.success(waterQuality);
    }

    @GetMapping("/list")
    public Result<PageInfo<WaterQuality>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                               @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                               String issue, Integer status, String createUser,Integer num) {
        return ResponseMsgUtil.success(waterQualityService.list(pageNumber,pageSize,issue,status,createUser,num));
    }

    @PutMapping("/update")
    public Result<WaterQuality> update(@RequestParam String id,
                                       @RequestParam Integer status) {

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
        waterQuality.setStatus(2);
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
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if(userDTO.getId().equals(waterQualityService.selectSHZB())){
            grade="02";
        }

        if(userDTO.getId().equals(waterQualityService.selectSHBJ())){
            grade="01";
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

        if(id.equals(waterQualityService.selectSHZB())){
            grade="02";
        }

        if(id.equals(waterQualityService.selectSHBJ())){
            grade="01";
        }

        return ResponseMsgUtil.success(grade);
    }
}
