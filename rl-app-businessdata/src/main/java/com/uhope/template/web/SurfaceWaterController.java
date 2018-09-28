package com.uhope.template.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.template.domain.SurfaceWater;
import com.uhope.template.service.SurfaceWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                                               String issue1, String issue2, Integer status, String creatUser) {
        return ResponseMsgUtil.success(surfaceWaterService.list(pageNumber,pageSize,issue1,issue2,status,creatUser));
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


}
