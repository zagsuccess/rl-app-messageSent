package com.uhope.template.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.template.domain.SurfaceWater;
import com.uhope.template.service.SurfaceWaterService;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
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
        surfaceWater.setStatus(2);
        surfaceWaterService.insert(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    @GetMapping("/list")
    public Result<PageInfo<SurfaceWater>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                               @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                               String issue, Integer status, String createUser,Integer num) {
        return ResponseMsgUtil.success(surfaceWaterService.list(pageNumber,pageSize,issue,status,createUser,num));
    }

    @PutMapping("/update")
    public Result<SurfaceWater> update(@RequestParam String id,
                                       @RequestParam Integer status) {

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
        surfaceWater.setStatus(2);
        surfaceWaterService.update(surfaceWater);
        return ResponseMsgUtil.success(surfaceWater);
    }

    @GetMapping("/detail")
    public Result<SurfaceWater> detail (@RequestParam String id) {
        return ResponseMsgUtil.success(surfaceWaterService.get(id));
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
        if(userDTO.getId().equals(surfaceWaterService.selectSHZB())){
            grade=02;
        }

        if(userDTO.getId().equals(surfaceWaterService.selectSHBJ())){
            grade=01;
        }

        return ResponseMsgUtil.success(grade);
    }
}
