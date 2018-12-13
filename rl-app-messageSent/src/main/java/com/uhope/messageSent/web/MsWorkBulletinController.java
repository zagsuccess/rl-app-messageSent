package com.uhope.messageSent.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsReturnBack;
import com.uhope.messageSent.domain.MsWorkBulletin;
import com.uhope.messageSent.dto.MsWorkBulletinDTO;
import com.uhope.messageSent.service.MsReturnBackService;
import com.uhope.messageSent.service.MsWorkBulletinService;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.messageSent.utils.DateUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 工作简报
 * @author wanglijun
 */
@RestController
@RequestMapping("/v1/msWorkBulletin")
public class MsWorkBulletinController  {

    @Autowired
    private MsWorkBulletinService msWorkBulletinService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Converter converter;

    @Autowired
    private FileManagerClient fileManagerClient;

    @Autowired
    private MsReturnBackService msReturnBackService;



    /**
     *  分页查询   可根据查询条件
     * @param pageNumber  当前页
     * @param pageSize  每页的长度
     * @param title  标题
     * @param reportTime  报送时间
     * @param sentState  报送状态
     * @param acceptState  采纳状态
     * @return
     */
    @GetMapping("/getList")
    public Result<PageInfo<MsWorkBulletin>> getList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                        @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                       String title,
                                                       String reportTime,
                                                       Integer sentState,
                                                       Integer acceptState,
                                                       HttpServletRequest request) {
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsWorkBulletin.class);
        Example.Criteria criteria = condition.createCriteria();
        if (title != null && title != "") {
            criteria.andCondition("title like '%" + title + "%'");
        }
        if (reportTime != null && reportTime != "") {
            criteria.andCondition("report_time = '" + reportTime + "'");
        }
        if (sentState != null) {
            criteria.andCondition("sent_state = " + sentState );
        }
        if (acceptState != null) {
            criteria.andCondition("accept_state = " + acceptState);
        }

        // 判断当前用户是市级还是区级，市级查询所有数据，区级查询当前区报送的工作简报
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (!"120100000000".equals(userDTO.getRegionId().toString()) && !"120000000000".equals(userDTO.getRegionId().toString())) {
            criteria.andCondition("region = '" + userDTO.getRegionId().toString() + "'");
        }


        List<MsWorkBulletin> list = msWorkBulletinService.findByCondition(condition);

        List<Map<String, Object>> regionList = msWorkBulletinService.selectList("3");

        List<MsWorkBulletinDTO> dtoList = new ArrayList<MsWorkBulletinDTO>();

        for (MsWorkBulletin mwb : list) {
            MsWorkBulletinDTO msWorkBulletinDTO = new MsWorkBulletinDTO();
            BeanUtils.copyProperties(mwb, msWorkBulletinDTO);

            String region_name = "";

            for (Map<String, Object> map : regionList) {

                if ((map.get("area_code").toString()).equals(msWorkBulletinDTO.getRegion())) {
                    region_name = map.get("area_name").toString();
                }
            }
            msWorkBulletinDTO.setRegionName(region_name);
            dtoList.add(msWorkBulletinDTO);
        }

        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(dtoList);

        return ResponseMsgUtil.success(pageInfo);

    }

    /**
     * 根据 id 查询某一记录的详细信息
     * @param id
     * @return
     */
    @GetMapping("/getDetailByID")
    public Result<MsWorkBulletinDTO> getDetailByID(@RequestParam String id) {
        MsWorkBulletin msWorkBulletin = msWorkBulletinService.get(id);

        String url = msWorkBulletin.getAccessoryURL();
        String pdf = msWorkBulletin.getPdfURL();
        MsWorkBulletinDTO msWorkBulletinDTO = new MsWorkBulletinDTO();
        BeanUtils.copyProperties(msWorkBulletin, msWorkBulletinDTO);

        String ren = "";
        String accessoryURL = "";
        String pdfURL = "";
        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();

        if (msWorkBulletinDTO.getAccessoryURL() != null && !"".equals(msWorkBulletinDTO.getAccessoryURL())) {
            String[] str = msWorkBulletinDTO.getAccessoryURL().split("_");
            ren = str[1];
            accessoryURL = url;
            pdfURL = pdf;

            // 将文件的预览地址与下载地址对应
            String[] accessoryURLArr = accessoryURL.split(",");
            String[] pdfURLArr = pdfURL.split(",");

            int totalLength = accessoryURLArr.length <= pdfURLArr.length ? accessoryURLArr.length : pdfURLArr.length;

            for (int i = 0; i < totalLength; i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("previewURL", pdfURLArr[i]);
                map.put("downloadURL", accessoryURLArr[i]);
                fileList.add(map);
            }

        }

        msWorkBulletinDTO.setAccessoryURL(accessoryURL);
        msWorkBulletinDTO.setPdfURL(pdfURL);
        msWorkBulletinDTO.setRen(ren);
        msWorkBulletinDTO.setFileList(fileList);

        return ResponseMsgUtil.success(msWorkBulletinDTO);
    }

    /**
     * 获取当前用户所属区域和姓名
     * @return
     */
    @GetMapping("/getRegionAndName")
    public Result<Map<String, String>> getRegionAndName(HttpServletRequest request) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));

        Map<String, Object> regionMap = msWorkBulletinService.selectDetail(userDTO.getRegionId().toString());

        String region = "";
        String region_name = "";
        String region_grade = "";

        if (regionMap != null) {
            region = regionMap.get("area_code") == null ? "" : regionMap.get("area_code").toString();
            region_name = regionMap.get("area_name") == null ? "" : regionMap.get("area_name").toString();
            region_grade = regionMap.get("grade") == null ? "" : regionMap.get("grade").toString();
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("region", region);
        map.put("region_name", region_name);
        map.put("region_grade", region_grade);
        map.put("name", userDTO.getName());
        return ResponseMsgUtil.success(map);
    }

    /**
     * 获取所有的区
     * @return
     */
    @GetMapping("/getRegionList")
    public Result<List<Map<String, Object>>> getRegionList() {
        List<Map<String, Object>> regionList = msWorkBulletinService.selectList("3");
        return ResponseMsgUtil.success(regionList);
    }

    /**
     * 上传文件
     * @param files
     * @return
     */
    @PostMapping("/upload")
    public Result<List<String>> upload(@RequestParam MultipartFile files[]) throws IOException {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < files.length; i++) {
            byte[] bytes = files[i].getBytes();
            String fileName = files[i].getOriginalFilename();
            FileItem fileItem = fileManagerClient.upload(bytes, fileName).getData();
            String filePath = fileItem.getVirtualPath();
            list.add(filePath);
        }

        return ResponseMsgUtil.success(list);
    }

    /**
     * 保存或报送工作简报
     * @param title
     * @param region
     * @param content
     * @param accessoryUrl
     * @return
     */
    @PostMapping("/addOReportInfo")
    public Result<MsWorkBulletin> addOReportInfo(@RequestParam String title,
                          @RequestParam String region,
                          @RequestParam String initiator,
                          @RequestParam String content,
                          @RequestParam String isReport,
                          String accessoryUrl) {

        MsWorkBulletin msWorkBulletin = new MsWorkBulletin();
        msWorkBulletin.setTitle(title);
        msWorkBulletin.setRegion(region);
        msWorkBulletin.setInitiator(initiator);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        msWorkBulletin.setReportTime(simpleDateFormat.format(new Date()));
        msWorkBulletin.setContent(content);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msWorkBulletin.setAccessoryURL(accessoryUrl);
            msWorkBulletin.setPdfURL(getPdfURL(accessoryUrl));
        }

        // isReport  1: 已报送  2： 未报送
        int state = "1".equals(isReport) ? 1 : 2;
        msWorkBulletin.setSentState(state);

        msWorkBulletin.setAcceptState(2);// 未采纳

        msWorkBulletinService.insert(msWorkBulletin);

        return ResponseMsgUtil.success(msWorkBulletin);
    }

    /**
     *  修改后保存或报送工作简报
     * @param id
     * @param title
     * @param region
     * @param initiator
     * @param content
     * @param isReport
     * @param accessoryUrl
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @PutMapping("/saveChanges")
    public Result<MsWorkBulletin> saveChanges(@RequestParam String id,
                                              @RequestParam String title,
                                              @RequestParam String region,
                                              @RequestParam String initiator,
                                              @RequestParam String content,
                                              @RequestParam String isReport,
                                              String accessoryUrl) throws NoSuchFieldException, IllegalAccessException {
        MsWorkBulletin msWorkBulletin = new MsWorkBulletin();
        msWorkBulletin.setTitle(title);
        msWorkBulletin.setRegion(region);
        msWorkBulletin.setInitiator(initiator);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        msWorkBulletin.setReportTime(simpleDateFormat.format(new Date()));
        msWorkBulletin.setContent(content);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msWorkBulletin.setAccessoryURL(accessoryUrl);
            msWorkBulletin.setPdfURL(getPdfURL(accessoryUrl));
        }

        // isReport  1: 已报送  2： 未报送
        int state = "1".equals(isReport) ? 1 : 2;
        msWorkBulletin.setSentState(state);

        msWorkBulletin.setAcceptState(2);// 未采纳

        msWorkBulletin.setId(id);

        msWorkBulletinService.update(msWorkBulletin);

        return ResponseMsgUtil.success(msWorkBulletin);
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

    /**
     *  报送
     * @param id
     * @return
     */
    @PutMapping("/reportInfo")
    public Result<String> reportInfo(@RequestParam String id) {
        MsWorkBulletin msWorkBulletin = new MsWorkBulletin();
        msWorkBulletin.setId(id);
        msWorkBulletin.setSentState(1);

        msWorkBulletinService.update(msWorkBulletin);

        return ResponseMsgUtil.success("操作成功！");
    }

    /**
     * 采纳或不采纳
     * @param id
     * @param acceptState
     * @return
     */
    @PutMapping("/acceptInfo")
    public Result<Map<String, String>> acceptInfo(@RequestParam String id,
                                     @RequestParam String acceptState,
                                     @RequestParam String reportTime) {
        // 判断 采纳的操作是否超过一个月，超过一个月后不可以做
        String currentDate = DateUtil.getDate(new Date());

        String reportTimeMonthLater = DateUtil.addMonth(reportTime, 1);

        Boolean isOverTime = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat.parse(currentDate);
            Date date2 = dateFormat.parse(reportTimeMonthLater);

            if (date1.getTime() > date2.getTime()) {
                isOverTime = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<String, String>();

        if (isOverTime) {
            map.put("returnCode", "0");// 已超时，不可操作
            map.put("returnMsg", "已超过一个月，不可操作！");
        } else {
            MsWorkBulletin msWorkBulletin = new MsWorkBulletin();
            msWorkBulletin.setId(id);
            msWorkBulletin.setAcceptState(Integer.parseInt(acceptState));

            msWorkBulletinService.update(msWorkBulletin);

            map.put("returnCode", "1");
            map.put("returnMsg", "操作成功！");
        }

        return ResponseMsgUtil.success(map);
    }

    /**
     * 退回工作简报
     * @param id
     * @param reason
     * @return
     */
    @PutMapping("/returnBack")
    public Result<String> returnBack(@RequestParam String id,
                                     @RequestParam String reason,
                                     HttpServletRequest request) {

        // 更新表中状态为 已退回
        MsWorkBulletin msWorkBulletin = new MsWorkBulletin();
        msWorkBulletin.setId(id);
        msWorkBulletin.setSentState(3);
        msWorkBulletinService.update(msWorkBulletin);

        // 退回历史表中插入一条记录
        MsReturnBack msReturnBack = new MsReturnBack();
        msReturnBack.setObjid(id);
        msReturnBack.setReason(reason);

        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        msReturnBack.setCreatePerson(userDTO.getName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        msReturnBack.setCreateTime(simpleDateFormat.format(new Date()));

        msReturnBackService.insert(msReturnBack);

        return ResponseMsgUtil.success("操作成功！");
    }

}
