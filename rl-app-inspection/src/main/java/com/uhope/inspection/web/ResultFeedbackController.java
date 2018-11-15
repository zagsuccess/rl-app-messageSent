package com.uhope.inspection.web;

import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.inspection.domain.ScResultFeedback;
import com.uhope.inspection.service.ResultFeedbackService;
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
 * 结果反馈
 *
 * @author a4994
 * @create 2018-11-06 15:05
 */
@RestController
@RequestMapping("/v1/ResultFeedback")
public class ResultFeedbackController {
    @Autowired
    ResultFeedbackService resultFeedbackService;
    @Autowired
    private FileManagerClient fileManagerClient;

    @Autowired
    private Converter converter;

    @PostMapping("/add")
    public Result<ScResultFeedback> add(
            @RequestParam Integer whether,
            @RequestParam(required = false) String accessory,
            @RequestParam String handleDate,
            @RequestParam String inspectionid
    ){
        ScResultFeedback scResultFeedback =new ScResultFeedback();
        scResultFeedback.setAccessory(accessory);
        scResultFeedback.setHandleDate(handleDate);
        scResultFeedback.setInspectionid(inspectionid);
        scResultFeedback.setWhether(whether);
        resultFeedbackService.insert(scResultFeedback);
        return ResponseMsgUtil.success(scResultFeedback);
    }

    @GetMapping("/detail")
    public Result<ScResultFeedback> detail(@RequestParam String id) {
        ScResultFeedback scResultFeedback=resultFeedbackService.get(id);
        String url=scResultFeedback.getAccessory();
        scResultFeedback.setAccessory(FmConfig.getAgentUrl()+url);
        return ResponseMsgUtil.success(scResultFeedback);
    }


    @GetMapping("/selectById")
    public Result<ScResultFeedback> selectById(@RequestParam String inspectionId) {
        return ResponseMsgUtil.success(resultFeedbackService.selectById(inspectionId));
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
