package com.uhope.inspection.web;

import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.inspection.domain.ScInspection;
import com.uhope.inspection.domain.ScTrafficList;
import com.uhope.inspection.service.InspectionService;
import com.uhope.inspection.service.TrafficListService;
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
 *
 * @author a4994
 * @create 2018-11-07 9:17
 */
@RestController
@RequestMapping("/v1/TrafficList")
public class TrafficListController {
    @Autowired
    private TrafficListService trafficListService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private Converter converter;
    @Autowired
    private InspectionService inspectionService;

    @RequestMapping("/add")
    public Result<ScTrafficList> add(
             String trafficDate,
            @RequestParam Integer oneregion,
             String trafficContent,
            @RequestParam(required = false) String accessory,
            @RequestParam String inspectionId,
             String region
    ) {
        if (oneregion != 2){
            ScInspection scInspection =new ScInspection();
            scInspection.setId(inspectionId);
            scInspection.setOneregion(oneregion);
            inspectionService.update(scInspection);
        }
        ScInspection scInspection = new ScInspection();
        scInspection.setId(inspectionId);
        scInspection.setState(3);
        inspectionService.update(scInspection);
        ScTrafficList scTrafficList =new ScTrafficList();
        scTrafficList.setAccessory(accessory);
        if (accessory!=null&&accessory!=""){
            String tempString=accessory.substring(accessory.lastIndexOf(".")+1);
            String pdfUrl=accessory;
            if(tempString.contains("doc")){
                pdfUrl=converter.startConverter(accessory);
            }
            scTrafficList.setPdfUrl(pdfUrl);
        }
        scTrafficList.setInspectionId(inspectionId);
        scTrafficList.setOneregion(oneregion);
        scTrafficList.setRegion(region);
        scTrafficList.setTrafficContent(trafficContent);
        scTrafficList.setTrafficDate(trafficDate);
        trafficListService.insert(scTrafficList);
        return ResponseMsgUtil.success(scTrafficList);
    }

    @GetMapping("/selectById")
    public Result<ScTrafficList> selectById(@RequestParam String inspectionId) {
        ScTrafficList scTrafficList=trafficListService.selectById(inspectionId);
        String url=scTrafficList.getAccessory();
        String url1=scTrafficList.getPdfUrl();
        scTrafficList.setAccessory(FmConfig.getAgentUrl()+url);
        scTrafficList.setPdfUrl(FmConfig.getAgentUrl()+url1);
        return ResponseMsgUtil.success(scTrafficList);
    }

    @GetMapping("/detail")
    public Result<ScTrafficList> detail(@RequestParam String id) {
        ScTrafficList scTrafficList=trafficListService.get(id);
        String url=scTrafficList.getAccessory();
        String url1=scTrafficList.getPdfUrl();
        scTrafficList.setAccessory(FmConfig.getAgentUrl()+url);
        scTrafficList.setPdfUrl(FmConfig.getAgentUrl()+url1);
        return ResponseMsgUtil.success(scTrafficList);
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
