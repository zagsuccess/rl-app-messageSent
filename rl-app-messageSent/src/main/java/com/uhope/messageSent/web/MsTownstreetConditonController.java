package com.uhope.messageSent.web;
import com.uhope.base.constants.Constant;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsTownstreetCondition;
// import com.uhope.messageSent.dto.MsSupervisionConditionDTO;
import com.uhope.messageSent.dto.MsTownstreetConditionDTO;
import com.uhope.messageSent.service.MsTownstreetConditionService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.io.BufferedInputStream;
// import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

/**
 * 镇街制度执行情况报表-Controller类
 * @author zhangaiguo on 2018/12/11
 */
@RestController
@RequestMapping("v1/msTownstreetCondition")
public class MsTownstreetConditonController {
    @Autowired
    private MsTownstreetConditionService msTownstreetConditionService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private Converter converter;

    /**
     * 获取当前用户所属角色权限
     * @return
     */
    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request) {
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade = "00";

       if ("市河长办".equals(msTownstreetConditionService.selectRole(userDTO.getId()))) {
            grade = "02";
        }

        if ("河长办".equals(msTownstreetConditionService.selectRole(userDTO.getId()))) {
            grade = "05";
        }
        /*if ("镇河长办".equals(msTownstreetConditionService.selectRole(userDTO.getId()))) {
            grade = "06";
        }
        if ("街河长办".equals(msTownstreetConditionService.selectRole(userDTO.getId()))) {
            grade = "07";
        }*/
        return ResponseMsgUtil.success(grade);
    }

    /**
     * 新增的确认上报
     * @param informationSystem
     * @param executeCircumstance
     * @param remark
     * @param accessoryUrl
     * @return
     * @throws ParseException
     */
    @PostMapping("/add")
    public Result<MsTownstreetCondition> add(@RequestParam Integer informationSystem,
                                              @RequestParam String executeCircumstance,
                                              String remark,
                                              String accessoryUrl,
                                              HttpServletRequest request
    ) throws ParseException {
        String region = null;
        String grade = null;
        String administationlevel = null;
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        region = msTownstreetConditionService.selectRegion(userDTO.getRegionId());
        grade =  msTownstreetConditionService.selectGrade(userDTO.getRegionId());
        if(grade.equals("1") || grade.equals("2")) {
            administationlevel = "市级";
        }
        if(grade.equals("3")) {
            administationlevel = "区级";
        }
        if(grade.equals("4")) {
            administationlevel = "街级";
        }
        if(grade.equals("5")) {
            administationlevel = "居委会级";
        }

        MsTownstreetCondition msTownstreetCondition = new MsTownstreetCondition();
        msTownstreetCondition.setInformationSystem(informationSystem);
        msTownstreetCondition.setExecuteCircumstance(executeCircumstance);
        msTownstreetCondition.setRemark(remark);


        msTownstreetCondition.setAdministrationRegion(region);
        msTownstreetCondition.setAdministrationLevel(administationlevel);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        msTownstreetCondition.setSentTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {

            msTownstreetCondition.setAccessoryURL(accessoryUrl);

            msTownstreetCondition.setPdfURL(getPdfURL(accessoryUrl));

        }

        msTownstreetCondition.setSentState(1);     // 保存而且上报
        // System.out.println("222222222:"+accessoryUrl+"!!!!!!!!!!!!");
        msTownstreetConditionService.insert(msTownstreetCondition);
        return ResponseMsgUtil.success(msTownstreetCondition);
    }

    /**
     * 新增的保存而不上报
     * @param informationSystem
     * @param executeCircumstance
     * @param remark
     * @param accessoryUrl
     * @return
     * @throws ParseException
     */
    @PostMapping("/addTwo")
    public Result<MsTownstreetCondition> addTwo(@RequestParam Integer informationSystem,
                                             @RequestParam String executeCircumstance,
                                             String remark,
                                             String accessoryUrl,
                                             HttpServletRequest request
    ) throws ParseException {
        String region = null;
        String grade = null;
        String administationlevel = null;
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        region = msTownstreetConditionService.selectRegion(userDTO.getRegionId());
        grade =  msTownstreetConditionService.selectGrade(userDTO.getRegionId());
        if(grade.equals("1") || grade.equals("2")) {
            administationlevel = "市级";
        }
        if(grade.equals("3")) {
            administationlevel = "区级";
        }
        if(grade.equals("4")) {
            administationlevel = "街级";
        }
        if(grade.equals("5")) {
            administationlevel = "居委会级";
        }

        MsTownstreetCondition msTownstreetCondition = new MsTownstreetCondition();
        msTownstreetCondition.setInformationSystem(informationSystem);
        msTownstreetCondition.setExecuteCircumstance(executeCircumstance);
        msTownstreetCondition.setRemark(remark);

        msTownstreetCondition.setAdministrationRegion(region);
        msTownstreetCondition.setAdministrationLevel(administationlevel);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        msTownstreetCondition.setSentTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msTownstreetCondition.setAccessoryURL(accessoryUrl);
            msTownstreetCondition.setPdfURL(getPdfURL(accessoryUrl));
        }
        msTownstreetCondition.setSentState(2);     // 保存而不上报

        msTownstreetConditionService.insert(msTownstreetCondition);
        return ResponseMsgUtil.success(msTownstreetCondition);
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
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam String id) {
        msTownstreetConditionService.remove(id);
        return ResponseMsgUtil.success("删除成功！");
    }

    /**
     * 完全更新
     * @return
     */
    @PutMapping("/update")
    public Result<MsTownstreetCondition> update(MsTownstreetCondition msTownstreetCondition) {
        msTownstreetConditionService.update(msTownstreetCondition);
        return ResponseMsgUtil.success(msTownstreetCondition);
    }

    /**
     * 单行记录的查看
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result<MsTownstreetCondition> detail(@RequestParam String id) {
        MsTownstreetCondition msTownstreetCondition = msTownstreetConditionService.get(id);
        if(msTownstreetCondition == null) {
            return ResponseMsgUtil.success(null);
        }
        String url = msTownstreetCondition.getAccessoryURL();
        String pdf = msTownstreetCondition.getPdfURL();
        MsTownstreetConditionDTO msTownstreetConditionDTO = new MsTownstreetConditionDTO();
        BeanUtils.copyProperties(msTownstreetCondition, msTownstreetConditionDTO);

        // String ren = "";
        String accessoryURL = "";
        String pdfURL = "";
        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();

        if (msTownstreetConditionDTO.getAccessoryURL() != null && !"".equals(msTownstreetConditionDTO.getAccessoryURL())) {
            String[] str = msTownstreetConditionDTO.getAccessoryURL().split("_");
            // ren = str[1];
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

        msTownstreetConditionDTO.setAccessoryURL(accessoryURL);
        msTownstreetConditionDTO.setPdfURL(pdfURL);
        // msTownstreetConditionDTO.setRen(ren);
        msTownstreetConditionDTO.setFileList(fileList);

        return ResponseMsgUtil.success(msTownstreetConditionDTO);
        // return ResponseMsgUtil.success(msTownstreetCondition);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    /*@GetMapping("/list")
    public Result<PageInfo<MsTownstreetCondition>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                         @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0) {
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsTownstreetCondition> list = msTownstreetConditionService.find();
        PageInfo<MsTownstreetCondition> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }*/

    /**
     * 条件查询
     * @param pageNumber
     * @param pageSize
     * @param sentTimeStart
     * @param sentTimeEnd
     * @param region
     * @param informationSystem
     * @return
     */
    @GetMapping("/selectList")
    public Result<PageInfo<MsTownstreetCondition>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                               @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                               String sentTimeStart,
                                                               String sentTimeEnd,
                                                               String region,
                                                               Integer informationSystem,
                                                               HttpServletRequest request) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }

        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsTownstreetCondition.class);
        Example.Criteria criteria = condition.createCriteria();
        if (sentTimeStart != null && sentTimeStart != ""
                && sentTimeEnd != null && sentTimeEnd != "") {
            criteria.andBetween("sentTime", sentTimeStart, sentTimeEnd);
        }
        if (region != null && region != "" && !region.trim().equals("天津市")) {
            criteria.andCondition("administration_region like '%" + region + "%'");
        }
        if (informationSystem != null && (informationSystem.intValue() == 1 || informationSystem.intValue() == 2
                || informationSystem.intValue() == 3)) {
            criteria.andCondition("information_system = " + informationSystem);
        }

        List<MsTownstreetCondition> list = msTownstreetConditionService.findByCondition(condition);
        if((sentTimeStart == null || sentTimeStart == "") && (sentTimeEnd == null || sentTimeEnd == "")
                && (region == null && region == "") && (informationSystem == null)) {
            list = msTownstreetConditionService.find();
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);

        // return ResponseMsgUtil.success(null);
    }

    /**
     * 所选行记录的上报
     * @param id
     * @param sentState
     * @return
     */
    @PutMapping("/updateSentState")
    public Result<MsTownstreetCondition> updateSentState(@RequestParam String id, @RequestParam Integer sentState) {
        MsTownstreetCondition msTownstreetCondition = new MsTownstreetCondition();
        msTownstreetCondition.setId(id);
        msTownstreetCondition.setSentState(sentState);
        msTownstreetConditionService.update(msTownstreetCondition);
        return ResponseMsgUtil.success(msTownstreetCondition);
    }

    /**
     * 导出报表成为电子表格
     * @param sentTimeStart
     * @param sentTimeEnd
     * @param region
     * @param informationSystem
     * @return
     * @throws IOException
     */
    @GetMapping("/createExcel")
    public Result<Object> createExcel(HttpServletResponse response,
                                       HttpServletRequest request,
                                       String sentTimeStart,
                                       String sentTimeEnd,
                                       String region,
                                       Integer informationSystem) throws IOException {
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook workbook = new HSSFWorkbook();
        //建立新的sheet对象(excel的表单)
        HSSFSheet sheet = workbook.createSheet("镇街制度执行情况");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        HSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("序号");
        row2.createCell(1).setCellValue("行政区域");
        row2.createCell(2).setCellValue("行政区域级别");
        row2.createCell(3).setCellValue("信息共享的报送制度");
        row2.createCell(4).setCellValue("报送日期");
        row2.createCell(5).setCellValue("执行情况");
        row2.createCell(6).setCellValue("报送状态");
        // row2.createCell(7).setCellValue("报送状态");
        int a = 1;
        int b = 0;
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }
        Condition condition = new Condition(MsTownstreetCondition.class);
        Example.Criteria criteria = condition.createCriteria();
        if (sentTimeStart != null && sentTimeStart != ""
                && sentTimeEnd != null && sentTimeEnd != "") {
            criteria.andBetween("sentTime", sentTimeStart, sentTimeEnd);
        }
        if (region != null && region != "" && !region.trim().equals("天津市")) {
            criteria.andCondition("administration_region like '%" + region + "%'");
        }
        if (informationSystem != null && (informationSystem.intValue() == 1 || informationSystem.intValue() == 2
                || informationSystem.intValue() == 3)) {
            criteria.andCondition("information_system = " + informationSystem);
        }

        List<MsTownstreetCondition> list = msTownstreetConditionService.findByCondition(condition);
        if((sentTimeStart == null || sentTimeStart == "") && (sentTimeEnd == null || sentTimeEnd == "")
                && (region == null && region == "") && (informationSystem == null)) {
            list = msTownstreetConditionService.find();
        }

        for (MsTownstreetCondition msTownstreetCondition : list) {
            a = a + 1;
            b = b + 1;
            HSSFRow row = sheet.createRow(a);
            row.createCell(0).setCellValue(b);
            row.createCell(1).setCellValue(msTownstreetCondition.getAdministrationRegion());
            row.createCell(2).setCellValue(msTownstreetCondition.getAdministrationLevel());
            String inforsystemstr = null;
            if(msTownstreetCondition.getInformationSystem().intValue() == 1 ) {
                inforsystemstr = "工作督查制度";
            }
            if(msTownstreetCondition.getInformationSystem().intValue() == 2 ) {
                inforsystemstr = "考核问责与激励制度";
            }
            if(msTownstreetCondition.getInformationSystem().intValue() == 1 ) {
                inforsystemstr = "验收制度";
            }
            row.createCell(3).setCellValue(inforsystemstr);
            row.createCell(4).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(msTownstreetCondition.getSentTime()));
            row.createCell(5).setCellValue(msTownstreetCondition.getExecuteCircumstance());
            String sentstatestr = null;
            if(msTownstreetCondition.getSentState().intValue() == 1 ) {
                sentstatestr = "已上报";
            }
            else {
                sentstatestr = "未上报";
            }
            row.createCell(6).setCellValue(sentstatestr);
            // row.createCell(7).setCellValue(msTownstreetCondition.getSentState());
        }
        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=townstreetPandect.xls");
        response.setContentType("application/x-msdownload");  // ("multipart/form-data");
        workbook.write(outputStream);
        outputStream.close();
        return ResponseMsgUtil.success("操作成功！");
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

}
