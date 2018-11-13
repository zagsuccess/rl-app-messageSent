package com.uhope.ancha.web;
import com.uhope.ancha.domain.AnzhaReport;
import com.uhope.ancha.service.AnzhaReportService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/10/24
 * @Time : 15:24
 * Aim :
 */

@RestController
@RequestMapping("/v1/AnzhaReport")
public class AnzhaReportController {
    @Autowired
    private AnzhaReportService anzhaReportService;
    @Autowired
    private FileManagerClient fileManagerClient;

    @Autowired
    private Converter converter;

    @PostMapping("/add")
    public Result<AnzhaReport> add(@RequestParam String anzhaid,
                                   @RequestParam String issuer,
                                   String regionid,String regionname,
                                   String reachid,String reachname,
                                   @RequestParam String date,
                                   @RequestParam String peoblemType,
                                   @RequestParam String status,
                                   @RequestParam String processLimited,
                                   String peoblemDescription,
                                   String filePath) throws ParseException {
        AnzhaReport anzhaReport = new AnzhaReport();
        anzhaReport.setIssuer(issuer);
        anzhaReport.setRegionid(regionid);
        anzhaReport.setRegionname(regionname);
        anzhaReport.setReachid(reachid);
        anzhaReport.setReachname(reachname);
        anzhaReport.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        anzhaReport.setAnzhaid(anzhaid);
        anzhaReport.setProcessLimited(processLimited);
        anzhaReport.setPeoblemType(peoblemType);
        anzhaReport.setStatus(status);
        anzhaReport.setPeoblemDescription(peoblemDescription);
        /*String img="";
        if(filePath!=null && filePath.length!=0){
            for (String a:filePath) {
                img += a + ",";
            }
            anzhaReport.setImage(img.substring(0,img.length()-1));
        }*/
        anzhaReport.setImage(filePath);
        anzhaReportService.insert(anzhaReport);
        return ResponseMsgUtil.success(anzhaReport);
    }

    /**
     * 文件上传
     *
     * @param files
     * @return
     * @throws IOException
     */
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


    @PostMapping("/uploadapp")
    public Result<String> uploadapp(@RequestParam(required = true) MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        String lastName = fileName.substring(fileName.lastIndexOf(".") + 1);
        FileItem fileItem = fileManagerClient.upload(bytes, fileName).getData();
        String filePath = fileItem.getVirtualPath();
        if (lastName.contains("doc")){
            filePath = converter.startConverter(fileItem.getVirtualPath());
        }
        return ResponseMsgUtil.success(filePath);
    }

    //获取远程服务器资源地址
    @GetMapping("/fileUrl")
    public Result fileUrl(){
        return ResponseMsgUtil.success(FmConfig.getDownloadUri());
    }




    @GetMapping("/detail")
    public Result<AnzhaReport> detail(@RequestParam String id) {
        AnzhaReport anzhaReport = anzhaReportService.get(id);
        return ResponseMsgUtil.success(anzhaReport);
    }

    @PutMapping("/update")
    public Result<AnzhaReport> update(@RequestParam String id,
                                      @RequestParam String issuer,
                                      String regionid,String regionname,
                                      String reachid,String reachname,
                                      @RequestParam String date,
                                      @RequestParam String processLimited,
                                      @RequestParam String peoblemType,
                                      String peoblemDescription,
                                      String filePath) throws ParseException {

        AnzhaReport anzhaReport = new AnzhaReport();
        anzhaReport.setId(id);
        anzhaReport.setIssuer(issuer);
        anzhaReport.setRegionid(regionid);
        anzhaReport.setRegionname(regionname);
        anzhaReport.setReachid(reachid);
        anzhaReport.setReachname(reachname);
        anzhaReport.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        anzhaReport.setPeoblemType(peoblemType);
        anzhaReport.setProcessLimited(processLimited);
        anzhaReport.setPeoblemDescription(peoblemDescription);
        /*String img="";
        for (String a:filePath) {
            img+=a+",";
        }*/
        anzhaReport.setImage(filePath);
        anzhaReportService.update(anzhaReport);
        return ResponseMsgUtil.success(anzhaReport);
    }

    @GetMapping("/list")
    public Result<List<AnzhaReport>> list(@RequestParam String anzhaid ) {

        return ResponseMsgUtil.success(anzhaReportService.list(anzhaid));
    }

}
