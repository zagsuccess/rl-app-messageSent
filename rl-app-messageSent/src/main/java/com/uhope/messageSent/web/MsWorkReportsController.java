package com.uhope.messageSent.web;
import com.google.common.collect.Lists;
import com.uhope.base.constants.Constant;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsSentDynamis;
import com.uhope.messageSent.domain.MsSentReports;
import com.uhope.messageSent.domain.MsWorkReports;
import com.uhope.messageSent.dto.MsSentDynamisDTO;
import com.uhope.messageSent.dto.MsWorkReportsDTO;
import com.uhope.messageSent.service.MsMeetingConditionService;
import com.uhope.messageSent.service.MsSentReportsService;
import com.uhope.messageSent.service.MsWorkReportsService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

/**
 * 工作简报表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msWorkReports")
public class MsWorkReportsController {
    @Autowired
    private MsWorkReportsService msWorkReportsService;

    @Autowired
    private MsMeetingConditionService msMeetingConditionService;

    @Autowired
    private MsSentReportsService msSentReportsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Converter converter;


    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));

        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }


        if("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            grade="05";
        }
        return ResponseMsgUtil.success(grade);
    }



    @PostMapping("/add")
    public Result<MsWorkReports> add(@RequestParam String title,
                                     @RequestParam String sentRegion,
                                     @RequestParam String sentTimeStart,
                                     @RequestParam String sentTimeEnd,
                                     @RequestParam String deadline,
                                     String briefDescription,
                                     String accessoryUrl,
                                     HttpServletRequest request) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsWorkReports msWorkReports=new MsWorkReports();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msWorkReports.setBeginTime(simpleDateFormat.format(new Date()));
        msWorkReports.setTitle(title);
        msWorkReports.setSentRegion(sentRegion);
        msWorkReports.setSentTimeStart(sentTimeStart);
        msWorkReports.setSentTimeEnd(sentTimeEnd);
        msWorkReports.setBriefDescription(briefDescription);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msWorkReports.setAccessoryUrl(accessoryUrl);
            msWorkReports.setPdfUrl(getPdfURL(accessoryUrl));
        }

        msWorkReports.setDeadline(deadline);
        msWorkReports.setInitiator(initiator);
        msWorkReportsService.insert(msWorkReports);
        return ResponseMsgUtil.success(msWorkReports);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msWorkReportsService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsWorkReports> update(MsWorkReports msWorkReports) {
        msWorkReportsService.update(msWorkReports);
        return ResponseMsgUtil.success(msWorkReports);
    }


    @GetMapping("/detail")
    public Result<MsWorkReportsDTO> detail(@RequestParam String id) {
        MsWorkReports msWorkReports = msWorkReportsService.get(id);
        String url = msWorkReports.getAccessoryUrl();
        String pdf = msWorkReports.getPdfUrl();
        MsWorkReportsDTO msWorkReportsDTO =new MsWorkReportsDTO();
        String ren = "";
        String accessoryUrl = "";
        String pdfUrl = "";
        BeanUtils.copyProperties(msWorkReports,msWorkReportsDTO);
        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
        if (msWorkReportsDTO.getAccessoryUrl() != null && !"".equals(msWorkReportsDTO.getAccessoryUrl())) {
            String[] str = msWorkReportsDTO.getAccessoryUrl().split("_");
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

        msWorkReportsDTO.setAccessoryUrl(accessoryUrl);
        msWorkReportsDTO.setPdfUrl(pdfUrl);
        msWorkReportsDTO.setRen(ren);
        msWorkReportsDTO.setFileList(fileList);



        return ResponseMsgUtil.success(msWorkReportsDTO);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsWorkReports>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsWorkReports> list = msWorkReportsService.find();
        PageInfo<MsWorkReports> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<MsWorkReports>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                           String title,
                                                           String sentTimeStart,
                                                           String sentTimeEnd,
                                                           String sentRegion,
                                                           Integer sentState,
                                                           String direction,
                                                           HttpServletRequest request
    ){
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        if("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(MsWorkReports.class);
            Example.Criteria criteria = condition.createCriteria();
            if (sentTimeStart != null && sentTimeStart != "") {
                criteria.andLike("sentTimeStart",sentTimeStart);
            }
            if (sentTimeEnd != null && sentTimeEnd != "") {
                criteria.andLike("sentTimeEnd",sentTimeEnd);
            }
            if (sentRegion != null && sentRegion !="") {
                criteria.andLike("sentRegion",sentRegion);
            }

            if (title != null && title !="") {
                criteria.andLike("title",title);
            }
            criteria.andLike("initiator",userDTO.getName());
            List<MsWorkReports> list = msWorkReportsService.findByCondition(condition);
            for (MsWorkReports msWorkReports:list
                 ) {
                msWorkReports.setDirection("发起");
            }
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }

        if("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(MsWorkReports.class);
            Example.Criteria criteria = condition.createCriteria();
            //通过对登录的用户信息进行查找确定是哪个区域
            String sentUnit=msWorkReportsService.selectRegion(userDTO.getRegionId());
            if (title != null && title !="") {
                criteria.andLike("title",title);
            }
            if (sentTimeStart != null && sentTimeStart != "") {
                criteria.andLike("sentTimeStart",sentTimeStart);
            }
            if (sentTimeEnd != null && sentTimeEnd != "") {
                criteria.andLike("sentTimeEnd",sentTimeEnd);
            }
            //String sentUnit2="天津市"+sentUnit;
            if (sentUnit != null && sentUnit !="") {
                criteria.andCondition("sent_region like '%" + sentUnit+"%'");
                /*criteria.andLike("sentRegion",sentUnit);*/
            }
            //查询得到下发区域包含目前登录账号区域的list
            List<MsWorkReports> list = msWorkReportsService.findByCondition(condition);
            //新建一个list1集合来根据天健查询中的是否报送来存放MsWorkReports对象
            List<MsWorkReports> list1= Lists.newArrayList();
            for (MsWorkReports msWorkReports:list
                 ) {
                //查到的MsSentReports对象方向为"报送"
                msWorkReports.setDirection("报送");
                MsSentReports msSentReports=new MsSentReports();
                //根据msWorkReports的id以及区域名字来得到报送报表
                msSentReports=msSentReportsService.findByReportId(msWorkReports.getId(),sentUnit);
                if (msSentReports==null){
                    //weatherSend(1.已上报  2.未上报 3.已退回)
                    msWorkReports.setWeatherSent(2);
                    msWorkReports.setReplyState(2);
                    if (sentState != null && sentState==2){
                        list1.add(msWorkReports);
                    }
                }
                if (msSentReports!=null){
                    msWorkReports.setReplyState(1);
                    //weatherSend(1.已上报  2.未上报 3.已退回)
                    //sentState(1.已上报  2.未上报 3.已退回)
                    if (msSentReports.getSentState()==1){
                        msWorkReports.setWeatherSent(1);
                        if (sentState!= null && sentState ==1){
                            list1.add(msWorkReports);
                        }
                    }
                    if (msSentReports.getSentState()==3){
                        msWorkReports.setWeatherSent(3);
                        if (sentState!= null && sentState ==3){
                            list1.add(msWorkReports);
                        }
                    }
                    if (msSentReports.getSentState()==2){
                        msWorkReports.setWeatherSent(2);
                        if (sentState!= null && sentState ==2){
                            list1.add(msWorkReports);
                        }
                    }
                    //getAcceptState(1.已采纳  2.未采纳 3.无操作)
                    Integer i=msSentReports.getAcceptState();
                    if (i==1){
                        msWorkReports.setWeatherAccept(1);
                    }
                    if (i==2){
                        msWorkReports.setWeatherAccept(2);
                    }
                    //weatherAccpect(1.已采纳  2.未采纳 3.无操作)
                }
            }
            if (list1.size()==0){
                list1=list;
            }
            PageInfo pageInfo = new PageInfo(list1);
            return ResponseMsgUtil.success(pageInfo);
        }
        return ResponseMsgUtil.success(null);
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
