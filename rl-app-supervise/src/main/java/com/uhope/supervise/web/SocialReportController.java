package com.uhope.supervise.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.supervise.domain.ShSocialReport;
import com.uhope.supervise.service.ShSocialReportService;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Jiaheng
 * @date: 18/10/30
 * @description: 社会监督举报
 */
@RestController
@RequestMapping("v1/socialReport")
public class SocialReportController {
    @Autowired
    private ShSocialReportService socialReportService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private Converter converter;

    @PostMapping("/addReport")
    public Result<ShSocialReport> addReport(ShSocialReport socialReport, HttpServletRequest request){
        socialReport.setCreateTime(new Date());
        socialReport.setCreator(tokenService.getUserIdByRequest(request));
        socialReportService.insert(socialReport);
        return ResponseMsgUtil.success(socialReport);
    }

    @GetMapping("/detailReport")
    public Result<ShSocialReport> detailReport(String id){
        ShSocialReport socialReport = socialReportService.get(id);
        socialReport.setProblemAttant(FmConfig.getAgentUrl() + socialReport.getProblemAttant());
        socialReport.setProcessingResults(FmConfig.getAgentUrl() + socialReport.getProcessingResults());
        socialReport.setProposedTreatment(FmConfig.getAgentUrl() + socialReport.getProposedTreatment());
        return ResponseMsgUtil.success(socialReport);
    }

    @GetMapping("/listReport")
    public Result<PageInfo> listReport(@RequestParam(name = "contactType", required = false) String contactType,
                                       @RequestParam(name = "regionName", required = false) String regionName,
                                       @RequestParam(name = "riverName", required = false) String riverName,
                                       @RequestParam(name = "processingStatus", required = false) String processingStatus,
                                       @RequestParam(name = "startTime", required = false) String startTime,
                                       @RequestParam(name = "endTime", required = false) String endTime,
                                       @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                       @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(ShSocialReport.class);
        Example.Criteria criteria = condition.createCriteria();
        if (contactType != null && contactType != ""){
            criteria.andCondition("contact_type like '%"+contactType+"%'");
        }
        if (regionName != null && regionName != ""){
            criteria.andCondition("region_name like '%"+regionName+"%'");
        }
        if (riverName != null && riverName != ""){
            criteria.andCondition("river_name like '%"+riverName+"%'");
        }
        if (processingStatus != null && processingStatus != ""){
            criteria.andCondition("processing_status like '%"+processingStatus+"%'");
        }
        if (startTime != null && endTime != null && startTime != "" && endTime != ""){
            criteria.andBetween("reportDate", startTime + " 00:00:00", endTime + " 23:59:59");
        }
        condition.orderBy("reportDate");
        List<ShSocialReport> list = socialReportService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @DeleteMapping("/deleteReport")
    public Result<String> deleteReport(String id){
        socialReportService.remove(id);
        return ResponseMsgUtil.success("删除完成");
    }

    @PutMapping("/updateReport")
    public Result<ShSocialReport> updateReport(ShSocialReport socialReport){
        socialReportService.update(socialReport);
        return ResponseMsgUtil.success("修改成功", socialReport);
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        Result<FileItem> fileItemResult = fileManagerClient.upload(file.getBytes(), filename);
        String fileName = file.getOriginalFilename();
        String lastName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String filePath = fileItemResult.getData().getVirtualPath();
        if (lastName.contains("doc") || lastName.contains("xls")){
            filePath = converter.startConverter(filePath);
        }
        return ResponseMsgUtil.success(filePath);
    }
}
