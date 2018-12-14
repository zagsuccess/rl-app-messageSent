package com.uhope.messageSent.web;
import com.uhope.base.constants.Constant;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsSentReports;
import com.uhope.messageSent.domain.MsWorkReports;
import com.uhope.messageSent.dto.MsSentReportsDTO;
import com.uhope.messageSent.service.MsSentReportsService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.messageSent.service.MsWorkReportsService;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

/**
 * 工作简报报送表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msSentReports")
public class MsSentReportsController {
    @Autowired
    private MsSentReportsService msSentReportsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FileManagerClient fileManagerClient;

    @Autowired
    MsWorkReportsService msWorkReportsService;

    @Autowired
    private Converter converter;

    //仅仅保存不上报
    @PostMapping("/add")
    public Result<MsSentReports> add(
                                     @RequestParam String reportId,
                                      String accessoryUrl,
                                     @RequestParam String briefDescription,
                                     HttpServletRequest request
                                     ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsWorkReports msWorkReports=msWorkReportsService.get(reportId);
        MsSentReports msSentReports=new MsSentReports();
        msSentReports.setTitle(msWorkReports.getTitle());
        msSentReports.setReportId(reportId);
        msSentReports.setBeginTime(new Date());
        msSentReports.setRegion(msWorkReportsService.selectRegion(userDTO.getRegionId()));
        msSentReports.setDeadline(msWorkReports.getDeadline());
        msSentReports.setInitiator(initiator);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msSentReports.setAccessoryUrl(accessoryUrl);
            msSentReports.setPdfUrl(getPdfURL(accessoryUrl));
        }
        msSentReports.setSentState(2);
        msSentReports.setAcceptState(3);
        msSentReports.setReplyState(1);
        msSentReports.setBriefDescription(briefDescription);
        msSentReportsService.insert(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
    }

    //保存且上报
    @PostMapping("/addAndSave")
    public Result<MsSentReports> addAndSave(
            @RequestParam String reportId,
            String accessoryUrl,
            @RequestParam String briefDescription,
            HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsWorkReports msWorkReports=msWorkReportsService.get(reportId);
        MsSentReports msSentReports=new MsSentReports();
        msSentReports.setTitle(msWorkReports.getTitle());
        msSentReports.setReportId(reportId);
        msSentReports.setBeginTime(new Date());
        msSentReports.setRegion(msWorkReportsService.selectRegion(userDTO.getRegionId()));
        msSentReports.setDeadline(msWorkReports.getDeadline());
        msSentReports.setInitiator(initiator);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msSentReports.setAccessoryUrl(accessoryUrl);
            msSentReports.setPdfUrl(getPdfURL(accessoryUrl));
        }
        msSentReports.setSentState(1);
        msSentReports.setAcceptState(3);
        msSentReports.setReplyState(1);
        msSentReports.setBriefDescription(briefDescription);
        msSentReportsService.insert(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
    }


    @GetMapping("/getRegion")
    public Result<String> getRegion(HttpServletRequest request){
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        return ResponseMsgUtil.success(msWorkReportsService.selectRegion(userDTO.getRegionId()));
    }

    @GetMapping("/getName")
    public Result<String> getName(HttpServletRequest request){
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        return ResponseMsgUtil.success(userDTO.getName());
    }





    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msSentReportsService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsSentReports> update(String title,
                                        String region,
                                        String deadline,
                                        String accessoryUrl,
                                        String briefDescription,
                                        @RequestParam String id) {
        MsSentReports msSentReports = msSentReportsService.get(id);
        msSentReports.setTitle(title);
        msSentReports.setRegion(region);
        msSentReports.setDeadline(deadline);
        msSentReports.setAccessoryUrl(accessoryUrl);
        msSentReports.setBriefDescription(briefDescription);
        msSentReportsService.update(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
    }




    @GetMapping("/detail")
    public Result<MsSentReportsDTO> detail(@RequestParam String id) {
        MsSentReports msSentReports = msSentReportsService.get(id);
        MsSentReportsDTO msSentReportsDTO =new MsSentReportsDTO();
        String url = msSentReports.getAccessoryUrl();
        String pdf = msSentReports.getPdfUrl();
        BeanUtils.copyProperties(msSentReports,msSentReportsDTO);

        String ren = "";
        String accessoryUrl = "";
        String pdfUrl = "";
        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
        if (msSentReportsDTO.getAccessoryUrl() != null && !"".equals(msSentReportsDTO.getAccessoryUrl())) {
            String[] str = msSentReportsDTO.getAccessoryUrl().split("_");
            ren = str[1];
            accessoryUrl = url;
            pdfUrl = pdf;

            // 将文件的预览地址与下载地址对应
            String[] accessoryURLArr = accessoryUrl.split(",");
            String[] pdfURLArr = pdfUrl.split(",");

            int totalLength = accessoryURLArr.length <= pdfURLArr.length ? accessoryURLArr.length : pdfURLArr.length;

            for (int i = 0; i < totalLength; i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("previewURL", pdfURLArr[i]);
                map.put("downloadURL", accessoryURLArr[i]);
                fileList.add(map);
            }

        }
        msSentReportsDTO.setAccessoryUrl(accessoryUrl);
        msSentReportsDTO.setPdfUrl(pdfUrl);
        msSentReportsDTO.setRen(ren);
        msSentReportsDTO.setFileList(fileList);
        return ResponseMsgUtil.success(msSentReportsDTO);
    }



    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsSentReports>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsSentReports> list = msSentReportsService.find();
        PageInfo<MsSentReports> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }


    @GetMapping("/selectList")
    public Result<PageInfo<MsSentReports>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      @RequestParam String reportId,
                                                      Integer sentState,
                                                      String region,
                                                      Integer acceptState
    ){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsSentReports.class);
        Example.Criteria criteria = condition.createCriteria();
        if (sentState != null ) {
            criteria.andCondition("sent_state = " + sentState);
        }
        if (region != null && region !="") {
            criteria.andCondition("region like '%" + region+"%'");
        }
        if (acceptState != null ) {
            criteria.andCondition("accept_state = " + acceptState);
        }
        if (reportId != null && reportId !="") {
            criteria.andCondition("report_id like '%" + reportId+"%'");
        }
        List<MsSentReports> list = msSentReportsService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }


    @GetMapping("/findByReportId")
    public Result<MsSentReports> findByReportId(@RequestParam String reportId,
                                                HttpServletRequest request){
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());
        MsSentReports msSentReports = msSentReportsService.findByReportId(reportId,region);
        return ResponseMsgUtil.success(msSentReports);
    }

    @PutMapping("/updateSentState")
    public Result<MsSentReports> updateSentState(@RequestParam String id,@RequestParam Integer sentState/*,HttpServletRequest request*/) {
        /*UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());*/
        MsSentReports msSentReports = msSentReportsService.get(id);

        msSentReports.setSentState(sentState);
        msSentReportsService.update(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
    }

    @PutMapping("/updateAcceptState")
    public Result<MsSentReports> updateAcceptState(@RequestParam String id,@RequestParam Integer acceptState) {
        MsSentReports msSentReports = msSentReportsService.get(id);
        msSentReports.setAcceptState(acceptState);
        msSentReportsService.update(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
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

    /**
     *  获取对应的 pdfURL 字段值
     * @param accessoryUrl
     * @return
     */
    public String getPdfURL(String accessoryUrl) {

        String pdfURLStr = "";
        StringBuffer pdfURL = new StringBuffer();

        String[] filesArr = accessoryUrl.split(",");
        for (String filePath : filesArr) {
            String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);

            if (suffix.contains("pdf")) {
                pdfURL.append(filePath).append(",");
            } else {
                pdfURL.append(converter.startConverter(filePath)).append(",");
            }
        }

        if (pdfURL.length() > 0) {
            pdfURLStr = pdfURL.substring(0, pdfURL.length() - 1);
        }

        return pdfURLStr;
    }
}
