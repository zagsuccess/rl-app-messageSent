package com.uhope.inspection.web;

import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.inspection.domain.ScInspection;
import com.uhope.inspection.domain.ScProblemHandle;
import com.uhope.inspection.service.InspectionService;
import com.uhope.inspection.service.ProblemHandleService;
import com.uhope.converter.client.Converter;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 问题处理
 *
 * @author a4994
 * @create 2018-11-06 15:01
 */
@RestController
@RequestMapping("/v1/ProblemHandle")
public class ProblemHandleController {
    @Autowired
    ProblemHandleService problemHandleService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private Converter converter;
    @Autowired
    InspectionService inspectionService;

    @RequestMapping("/add")
    public Result<ScProblemHandle> add (
            @RequestParam String inspectionid,
            @RequestParam String sentDate,
            @RequestParam(required = false) String accessory,
             Integer state,
             String description
    ){
        ScInspection scInspection = new ScInspection();
        scInspection.setId(inspectionid);
        scInspection.setState(4);
        inspectionService.update(scInspection);
        ScProblemHandle scProblemHandle =new ScProblemHandle();
        scProblemHandle.setAccessory(accessory);
        String tempString=accessory.substring(accessory.lastIndexOf(".")+1);
        String pdfUrl=accessory;
        if(tempString.contains("doc")){
            pdfUrl=converter.startConverter(accessory);
        }
        scProblemHandle.setPdfUrl(pdfUrl);
        scProblemHandle.setDescription(description);
        scProblemHandle.setSentDate(sentDate);
        scProblemHandle.setState(state);
        scProblemHandle.setInspectionid(inspectionid);
        problemHandleService.insert(scProblemHandle);
        return ResponseMsgUtil.success(scProblemHandle);
    }

    @GetMapping("/detail")
    public Result<ScProblemHandle> detail(@RequestParam String id) {
        ScProblemHandle scProblemHandle=problemHandleService.get(id);
        String url=scProblemHandle.getAccessory();
        String url1=scProblemHandle.getPdfUrl();
        scProblemHandle.setAccessory(FmConfig.getFmUrl()+url);
        scProblemHandle.setPdfUrl(FmConfig.getFmUrl()+url1);
        return ResponseMsgUtil.success(scProblemHandle);
    }



    @GetMapping("/selectById")
    public Result<ScProblemHandle> selectById(@RequestParam String inspectionId) {
        ScProblemHandle scProblemHandle=problemHandleService.selectById(inspectionId);
        String url=scProblemHandle.getAccessory();
        scProblemHandle.setAccessory(FmConfig.getAgentUrl()+url);
        return ResponseMsgUtil.success(scProblemHandle);
    }
    @PostMapping("/upload")
    public Result<List<String>> upload(@RequestParam(required = true) MultipartFile files[]) throws IOException {
        List<String>list=new ArrayList<>();
        for (int i=0;i<files.length;i++){
            byte[] bytes = files[i].getBytes();
            String fileName = files[i].getOriginalFilename();
            FileItem fileItem = fileManagerClient.upload(bytes, fileName).getData();
            String filePath = fileItem.getVirtualPath();
            list.add(filePath);
        }
        return ResponseMsgUtil.success(list);
    }
}
