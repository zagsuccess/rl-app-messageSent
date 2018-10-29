package com.uhope.bulletin.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.bulletin.utils.DateUtil;
import com.uhope.bulletin.utils.TimeUtil;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import com.uhope.bulletin.domain.ExeAssPatrol;
import com.uhope.bulletin.service.ExeAssPatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 巡查管理入口
 *
 * @author a4994
 * @create 2018-10-27 16:38
 */
@RestController
@RequestMapping("/v1/ExeAssPatrol")
public class ExeAssPatrolController {
    @Autowired
    private ExeAssPatrolService exeAssPatrolService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileManagerClient fileManagerClient;

    @GetMapping("/selectGradeWay")
    public List<String> selectGradeWay(){
        return exeAssPatrolService.selectGradeWay();
    }

    @GetMapping("/selectGradeDedetailed")
    public List<String> selectGradeDedetailed(){
        return exeAssPatrolService.selectGradeDedetailed();
    }

    @GetMapping("/selectList")
    public Result<PageInfo<ExeAssPatrol>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                     @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                     String riverName,
                                                     String region,
                                                     String riverQuestion,
                                                     String patorPerson,
                                                     String patrolDateStart,
                                                     String patrolDateEnd) throws ParseException {
        return ResponseMsgUtil.success(exeAssPatrolService.list(pageNumber, pageSize, riverName, region, riverQuestion, patorPerson,patrolDateStart,patrolDateEnd));
    }

    @PostMapping("/add")
    public Result<ExeAssPatrol> add(@RequestParam String riverName,
                                    @RequestParam String region,
                                    @RequestParam String riverQuestion,
                                    @RequestParam String patorPerson,
                                    @RequestParam String pointsType,
                                    @RequestParam Integer buckleScores,
                                    @RequestParam String location,
                                    @RequestParam(required = false) String photoUrl) throws ParseException {
        ExeAssPatrol exeAssPatrol = new ExeAssPatrol();
        exeAssPatrol.setRiverName(riverName);
        exeAssPatrol.setRegion(region);
        exeAssPatrol.setRiverQuestion(riverQuestion);
        exeAssPatrol.setPatorPerson(patorPerson);
        exeAssPatrol.setPointsType(pointsType);
        exeAssPatrol.setBuckleScores(buckleScores);
        exeAssPatrol.setPatrolDate(new Date());
        exeAssPatrol.setLocation(location);
        exeAssPatrol.setPhotoUrl(photoUrl);
        exeAssPatrolService.insert(exeAssPatrol);
        return ResponseMsgUtil.success(exeAssPatrol);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam(required = true) MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        Result<FileItem> itemResult = fileManagerClient.upload(bytes, fileName);
        return ResponseMsgUtil.isSuccess(itemResult) ? itemResult : ResponseMsgUtil.failure();
    }

    @GetMapping("/detail")
    public Result<ExeAssPatrol> detail(@RequestParam String id) {
        return ResponseMsgUtil.success(exeAssPatrolService.get(id));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        exeAssPatrolService.remove(id);
        return ResponseMsgUtil.success();
    }
}
