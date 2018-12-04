package com.uhope.messageSent.web;
import com.uhope.base.constants.Constant;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsMeetingCondition;
import com.uhope.messageSent.service.MsMeetingConditionService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.service.TokenService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.String;

/**
 * 会议制度执行情况表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msMeetingCondition")
public class MsMeetingConditionController {
    @Autowired
    private MsMeetingConditionService msMeetingConditionService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private Converter converter;

    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request) {
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade = "00";

        if ("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))) {
            grade = "02";
        }

        if ("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))) {
            grade = "05";
        }
        return ResponseMsgUtil.success(grade);
    }

    //确认并上报用此接口
    @PostMapping("/add")
    public Result<MsMeetingCondition> add(@RequestParam String compereRole,
                                          @RequestParam String compereName,
                                          @RequestParam String duty,
                                          @RequestParam String category,
                                          @RequestParam String meetingTime,
                                          @RequestParam String topic,
                                          @RequestParam String content,
                                          String remark,
                                          HttpServletRequest request
    ) throws ParseException {
        String region = null;
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        region = msMeetingConditionService.selectRegion(userDTO.getRegionId());
        MsMeetingCondition msMeetingCondition = new MsMeetingCondition();
        msMeetingCondition.setCompereRole(compereRole);
        msMeetingCondition.setCompereName(compereName);
        msMeetingCondition.setDuty(duty);
        msMeetingCondition.setCategory(category);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse(meetingTime);
        msMeetingCondition.setMeetingTime(utilDate);
        msMeetingCondition.setTopic(topic);
        msMeetingCondition.setContent(content);
        msMeetingCondition.setRemark(remark);
        msMeetingCondition.setRegion(region);
        msMeetingCondition.setSentState(1);
        msMeetingConditionService.insert(msMeetingCondition);
        return ResponseMsgUtil.success(msMeetingCondition);
    }

    //仅仅保存不上报
    @PostMapping("/addTwo")
    public Result<MsMeetingCondition> addTwo(@RequestParam String compereRole,
                                             @RequestParam String compereName,
                                             @RequestParam String duty,
                                             @RequestParam String category,
                                             @RequestParam String meetingTime,
                                             @RequestParam String topic,
                                             @RequestParam String content,
                                             String remark,
                                             HttpServletRequest request
    ) throws ParseException {
        String region = null;
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        region = msMeetingConditionService.selectRegion(userDTO.getRegionId());
        MsMeetingCondition msMeetingCondition = new MsMeetingCondition();
        msMeetingCondition.setCompereRole(compereRole);
        msMeetingCondition.setCompereName(compereName);
        msMeetingCondition.setDuty(duty);
        msMeetingCondition.setCategory(category);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse(meetingTime);
        msMeetingCondition.setMeetingTime(utilDate);
        msMeetingCondition.setTopic(topic);
        msMeetingCondition.setContent(content);
        msMeetingCondition.setRemark(remark);
        msMeetingCondition.setRegion(region);
        msMeetingCondition.setSentState(2);
        msMeetingConditionService.insert(msMeetingCondition);
        return ResponseMsgUtil.success(msMeetingCondition);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msMeetingConditionService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsMeetingCondition> update(MsMeetingCondition msMeetingCondition) {
        msMeetingConditionService.update(msMeetingCondition);
        return ResponseMsgUtil.success(msMeetingCondition);
    }


    @GetMapping("/detail")
    public Result<MsMeetingCondition> detail(@RequestParam String id) {
        MsMeetingCondition msMeetingCondition = msMeetingConditionService.get(id);
        return ResponseMsgUtil.success(msMeetingCondition);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsMeetingCondition>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                     @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0) {
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsMeetingCondition> list = msMeetingConditionService.find();
        PageInfo<MsMeetingCondition> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<MsMeetingCondition>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                           String meetingTimeStart,
                                                           String meetingTimeEnd,
                                                           String region,
                                                           HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }
        if ("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))) {
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(MsMeetingCondition.class);
            Example.Criteria criteria = condition.createCriteria();
            if (meetingTimeStart != null && meetingTimeStart != "") {
                criteria.andBetween("meetingTime", meetingTimeStart, meetingTimeEnd);
            }
            if (region != null && region != "") {
                criteria.andCondition("region like '%" + region + "%'");
            }

            List<MsMeetingCondition> list = msMeetingConditionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))) {
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(MsMeetingCondition.class);
            Example.Criteria criteria = condition.createCriteria();
            region = msMeetingConditionService.selectRegion(userDTO.getRegionId());

            if (meetingTimeStart != null && meetingTimeStart != "") {

                criteria.andBetween("meetingTime", meetingTimeStart, meetingTimeEnd);
            }
            if (region != null && region != "") {
                criteria.andCondition("region like '%" + region + "%'");
            }
            List<MsMeetingCondition> list = msMeetingConditionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/updateSentState")
    public Result<MsMeetingCondition> updateSentState(@RequestParam String id, @RequestParam Integer sentState) {
        MsMeetingCondition msMeetingCondition = new MsMeetingCondition();
        msMeetingCondition.setId(id);
        msMeetingCondition.setSentState(sentState);
        msMeetingConditionService.update(msMeetingCondition);
        return ResponseMsgUtil.success(msMeetingCondition);
    }

    @GetMapping("/createExcel")
    public Result<Object> createExcel(HttpServletResponse response,
                                      HttpServletRequest request,
                                      String meetingTimeStart,
                                      String meetingTimeEnd,
                                      String region) throws IOException {
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook workbook = new HSSFWorkbook();
        //建立新的sheet对象(excel的表单)
        HSSFSheet sheet = workbook.createSheet("会议制度执行情况");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        HSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("序号");
        row2.createCell(1).setCellValue("行政区域");
        row2.createCell(2).setCellValue("会议主持角色");
        row2.createCell(3).setCellValue("姓名");
        row2.createCell(4).setCellValue("职务");
        row2.createCell(5).setCellValue("会议类别");
        row2.createCell(6).setCellValue("会议时间");
        row2.createCell(7).setCellValue("会议标题");
        row2.createCell(8).setCellValue("会议内容");
        int a = 1;
        int b = 0;
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if ("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))) {
            Condition condition = new Condition(MsMeetingCondition.class);
            Example.Criteria criteria = condition.createCriteria();
            if (meetingTimeStart != null && meetingTimeStart != "") {
                criteria.andBetween("meetingTime", meetingTimeStart, meetingTimeEnd);
            }
            if (region != null && region != "") {
                criteria.andCondition("region like '%" + region + "%'");
            }
            List<MsMeetingCondition> list = msMeetingConditionService.findByCondition(condition);
            for (MsMeetingCondition msMeetingCondition : list
            ) {
                a = a + 1;
                b = b + 1;
                HSSFRow row = sheet.createRow(a);
                row.createCell(0).setCellValue(b);
                row.createCell(1).setCellValue(msMeetingCondition.getRegion());
                row.createCell(2).setCellValue(msMeetingCondition.getCompereRole());
                row.createCell(3).setCellValue(msMeetingCondition.getCompereName());
                row.createCell(4).setCellValue(msMeetingCondition.getDuty());
                row.createCell(5).setCellValue(msMeetingCondition.getCategory());
                row.createCell(6).setCellValue(msMeetingCondition.getMeetingTime());
                row.createCell(7).setCellValue(msMeetingCondition.getTopic());
                row.createCell(8).setCellValue(msMeetingCondition.getContent());
            }
            OutputStream outputStream = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=meetingPandect.xls");
            response.setContentType("application/x-msdownload");
            workbook.write(outputStream);
            outputStream.close();
            return ResponseMsgUtil.success(null);
        }
        if ("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))) {
            Condition condition = new Condition(MsMeetingCondition.class);
            Example.Criteria criteria = condition.createCriteria();
            region = msMeetingConditionService.selectRegion(userDTO.getRegionId());

            if (meetingTimeStart != null && meetingTimeStart != "") {

                criteria.andBetween("meetingTime", meetingTimeStart, meetingTimeEnd);
            }
            if (region != null && region != "") {
                criteria.andCondition("region like '%" + region + "%'");
            }
            List<MsMeetingCondition> list = msMeetingConditionService.findByCondition(condition);
            for (MsMeetingCondition msMeetingCondition : list
            ) {
                a = a + 1;
                b = b + 1;
                HSSFRow row = sheet.createRow(a);
                row.createCell(0).setCellValue(b);
                row.createCell(1).setCellValue(msMeetingCondition.getRegion());
                row.createCell(2).setCellValue(msMeetingCondition.getCompereRole());
                row.createCell(3).setCellValue(msMeetingCondition.getCompereName());
                row.createCell(4).setCellValue(msMeetingCondition.getDuty());
                row.createCell(5).setCellValue(msMeetingCondition.getCategory());
                row.createCell(6).setCellValue(msMeetingCondition.getMeetingTime());
                row.createCell(7).setCellValue(msMeetingCondition.getTopic());
                row.createCell(8).setCellValue(msMeetingCondition.getContent());
            }
            OutputStream outputStream = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=meetingPandect.xls");
            response.setContentType("application/x-msdownload");
            workbook.write(outputStream);
            outputStream.close();
            return ResponseMsgUtil.success(null);
        }
        return ResponseMsgUtil.success(null);

    }
}