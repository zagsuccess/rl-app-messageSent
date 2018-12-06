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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public Result<MsSentReports> add(@RequestParam String title,
                                     @RequestParam String reportId,
                                     @RequestParam String region,
                                     @RequestParam String deadline,
                                      String accessoryUrl,
                                     @RequestParam String briefDescription,
                                     HttpServletRequest request
                                     ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsSentReports msSentReports=new MsSentReports();
        msSentReports.setTitle(title);
        msSentReports.setReportId(reportId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msSentReports.setBeginTime(simpleDateFormat.format(new Date()));
        msSentReports.setRegion(region);
        msSentReports.setDeadline(deadline);
        msSentReports.setInitiator(initiator);
        msSentReports.setAccessoryUrl(accessoryUrl);
        msSentReports.setSentState(2);
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


    //保存且上报
    @PostMapping("/addAndSave")
    public Result<MsSentReports> addAndSave(@RequestParam String title,
                                     @RequestParam String reportId,
                                     @RequestParam String region,
                                     @RequestParam String deadline,
                                     String accessoryUrl,
                                     @RequestParam String briefDescription,
                                     HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsSentReports msSentReports=new MsSentReports();
        msSentReports.setTitle(title);
        msSentReports.setReportId(reportId);
        msSentReports.setRegion(region);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msSentReports.setBeginTime(simpleDateFormat.format(new Date()));
        msSentReports.setDeadline(deadline);
        msSentReports.setInitiator(initiator);
        msSentReports.setAccessoryUrl(accessoryUrl);
        msSentReports.setSentState(1);
        msSentReports.setAcceptState(3);
        msSentReports.setBriefDescription(briefDescription);
        msSentReportsService.insert(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msSentReportsService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsSentReports> update(MsSentReports msSentReports) {
        msSentReportsService.update(msSentReports);
        return ResponseMsgUtil.success(msSentReports);
    }


    /*@GetMapping("/detail")
    public Result<MsSentReports> detail(@RequestParam String id) {
        MsSentReports msSentReports = msSentReportsService.get(id);
            String url = msSentReports.getAccessoryUrl();
            msSentReports.setAccessoryUrl(FmConfig.getFmUrl()+url);
        return ResponseMsgUtil.success(msSentReports);
    }*/

    @GetMapping("/detail")
    public Result<MsSentReportsDTO> detail(@RequestParam String id) {
        MsSentReports msSentReports = msSentReportsService.get(id);
        String url=msSentReports.getAccessoryUrl();
        //undercover.setAttand_url(FmConfig.getFmUrl() + name);
        MsSentReportsDTO msSentReportsDTO =new MsSentReportsDTO();
        BeanUtils.copyProperties(msSentReports,msSentReportsDTO);
        String[] str=msSentReportsDTO.getAccessoryUrl().split("_");
        String ren = str[1];
        msSentReportsDTO.setAccessoryUrl(FmConfig.getFmUrl() +url);
        msSentReportsDTO.setDownurl(FmConfig.getFmUrl() + FmConfig.getDownloadUri().substring(0,FmConfig.getDownloadUri().length()-1) + url);
        msSentReportsDTO.setRen(ren);
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
    public Result<MsSentReports> updateAcceptState(@RequestParam String id,@RequestParam Integer acceptState
                                                   /*HttpServletRequest request*/) {
        /*UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());*/
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
