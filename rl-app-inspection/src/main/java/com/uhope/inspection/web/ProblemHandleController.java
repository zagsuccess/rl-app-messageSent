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
        scProblemHandle.setAccessory(FmConfig.getAgentUrl()+url);
        return ResponseMsgUtil.success(scProblemHandle);
    }

    @GetMapping("/selectById")
    public Result<ScProblemHandle> selectById(@RequestParam String inspectionId) {
        return ResponseMsgUtil.success(problemHandleService.selectById(inspectionId));
    }
    @PostMapping("/upload")
    public Result<List<String>> upload(@RequestParam(required = true) MultipartFile files[]) throws IOException {
        List<String>list=new ArrayList<>();
        for (int i=0;i<files.length;i++){
            byte[] bytes = files[i].getBytes();
            String fileName = files[i].getOriginalFilename();
            String lastName = fileName.substring(fileName.lastIndexOf(".") + 1);
            FileItem fileItem = fileManagerClient.upload(bytes, fileName).getData();
            String filePath = fileItem.getVirtualPath();
            if (lastName.contains("doc")){
                filePath = converter.startConverter(fileItem.getVirtualPath());
            }
            list.add(filePath);
        }
        return ResponseMsgUtil.success(list);
    }
}
