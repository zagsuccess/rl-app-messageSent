package com.uhope.messageSent.web;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.uhope.base.constants.Constant;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsSentDynamis;
import com.uhope.messageSent.dto.MsSentDynamisDTO;
import com.uhope.messageSent.service.MsSentDynamisService;
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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

/**
 * 周动态报送表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msSentDynamis")
public class MsSentDynamisController {
    @Autowired
    private MsSentDynamisService msSentDynamisService;

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
    public Result<MsSentDynamis> add(@RequestParam String title,
                                     @RequestParam String weekId,
                                     @RequestParam String deadline,
                                     String accessoryUrl,
                                     String patrolCondition,
                                     String meetingCondition,
                                     String problemSolvingCondition,
                                     String otherCondition,
                                     HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsSentDynamis msSentDynamis=new MsSentDynamis();
        msSentDynamis.setTitle(title);
        msSentDynamis.setWeekid(weekId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msSentDynamis.setBeginTime(simpleDateFormat.format(new Date()));
        msSentDynamis.setRegion(msWorkReportsService.selectRegion(userDTO.getRegionId()));
        msSentDynamis.setDeadline(deadline);
        msSentDynamis.setInitiator(initiator);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msSentDynamis.setAccessoryUrl(accessoryUrl);
            msSentDynamis.setPdfUrl(getPdfURL(accessoryUrl));
        }
        msSentDynamis.setOtherCondition(otherCondition);
        msSentDynamis.setSentState(2);
        msSentDynamis.setAcceptState(3);
        msSentDynamis.setReplyState(1);
        msSentDynamis.setPatrolCondition(patrolCondition);
        msSentDynamis.setMeetingCondition(meetingCondition);
        msSentDynamis.setProblemSolvingCondition(problemSolvingCondition);
        msSentDynamisService.insert(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
    }

    @PutMapping("/update")
    public Result<MsSentDynamis> update(@RequestParam String id,String title,String deadline,
                                        String accessoryUrl,String patrolCondition,String meetingCondition,
                                        String problemSolvingCondition,String otherCondition) {
        MsSentDynamis msSentDynamis=new MsSentDynamis();
        msSentDynamis.setId(id);
        msSentDynamis.setTitle(title);
        msSentDynamis.setDeadline(deadline);
        msSentDynamis.setAccessoryUrl(accessoryUrl);
        msSentDynamis.setPatrolCondition(patrolCondition);
        msSentDynamis.setMeetingCondition(meetingCondition);
        msSentDynamis.setProblemSolvingCondition(problemSolvingCondition);
        msSentDynamis.setOtherCondition(otherCondition);
        msSentDynamisService.update(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
    }

    //保存且上报
    @PostMapping("/addAndSave")
    public Result<MsSentDynamis> addAndSave(@RequestParam String title,
                                            @RequestParam String weekId,
                                            @RequestParam String deadline,
                                                          String accessoryUrl,
                                                          String patrolCondition,
                                                          String meetingCondition,
                                                          String problemSolvingCondition,
                                                          String otherCondition,
                                                          HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsSentDynamis msSentDynamis=new MsSentDynamis();
        msSentDynamis.setTitle(title);
        msSentDynamis.setWeekid(weekId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msSentDynamis.setBeginTime(simpleDateFormat.format(new Date()));
        msSentDynamis.setRegion(msWorkReportsService.selectRegion(userDTO.getRegionId()));
        msSentDynamis.setDeadline(deadline);
        msSentDynamis.setInitiator(initiator);

        if (accessoryUrl != null && !"".equals(accessoryUrl)) {
            msSentDynamis.setAccessoryUrl(accessoryUrl);
            msSentDynamis.setPdfUrl(getPdfURL(accessoryUrl));
        }

        msSentDynamis.setSentState(1);
        msSentDynamis.setAcceptState(3);
        msSentDynamis.setReplyState(2);
        msSentDynamis.setOtherCondition(otherCondition);
        msSentDynamis.setPatrolCondition(patrolCondition);
        msSentDynamis.setMeetingCondition(meetingCondition);
        msSentDynamis.setProblemSolvingCondition(problemSolvingCondition);
        msSentDynamisService.insert(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
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

    @GetMapping("/findByReportId")
    public Result<MsSentDynamis> findByReportId(@RequestParam String reportId,
                                                HttpServletRequest request){
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());
        MsSentDynamis msSentDynamis = msSentDynamisService.findByReportId(reportId,region);
        return ResponseMsgUtil.success(msSentDynamis);
    }


    @PutMapping("/updateSentState")
    public Result<MsSentDynamis> updateSentState(@RequestParam String id,@RequestParam Integer sentState,HttpServletRequest request) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());
        MsSentDynamis msSentDynamis = new MsSentDynamis();
        msSentDynamis=msSentDynamisService.findByReportId(id,region);
        msSentDynamis.setId(msSentDynamis.getId());
        msSentDynamis.setSentState(sentState);
        msSentDynamisService.update(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
    }

    @PutMapping("/updateAcceptState")
    public Result<MsSentDynamis> updateAcceptState(@RequestParam String id,@RequestParam Integer acceptState,HttpServletRequest request) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());
        MsSentDynamis msSentDynamis = new MsSentDynamis();
        msSentDynamis=msSentDynamisService.findByReportId(id,region);
        msSentDynamis.setId(msSentDynamis.getId());
        msSentDynamis.setAcceptState(acceptState);
        msSentDynamisService.update(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
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
        msSentDynamisService.remove(id);
        return ResponseMsgUtil.success(null);
    }

    @GetMapping("/detail")
    public Result<MsSentDynamisDTO> detail(@RequestParam String id) {
        MsSentDynamis msSentDynamis = msSentDynamisService.get(id);
        MsSentDynamisDTO msSentDynamisDTO =new MsSentDynamisDTO();
        String url = msSentDynamis.getAccessoryUrl();
        String pdf = msSentDynamis.getPdfUrl();
        BeanUtils.copyProperties(msSentDynamis,msSentDynamisDTO);
        String ren = "";
        String accessoryUrl = "";
        String pdfUrl = "";
        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
        if (msSentDynamisDTO.getAccessoryUrl() != null && !"".equals(msSentDynamisDTO.getAccessoryUrl())) {
            String[] str = msSentDynamisDTO.getAccessoryUrl().split("_");
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

        msSentDynamisDTO.setAccessoryUrl(accessoryUrl);
        msSentDynamisDTO.setPdfUrl(pdfUrl);
        msSentDynamisDTO.setRen(ren);
        msSentDynamisDTO.setFileList(fileList);
        return ResponseMsgUtil.success(msSentDynamisDTO);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsSentDynamis>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsSentDynamis> list = msSentDynamisService.find();
        PageInfo<MsSentDynamis> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<MsSentDynamis>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      @RequestParam String reportId,
                                                      Integer sentState,
                                                      String region,
                                                      Integer acceptState
    ){
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsSentDynamis.class);
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
            criteria.andCondition("weekid like '%" + reportId+"%'");
        }
        List<MsSentDynamis> list = msSentDynamisService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }


    //合并提醒
    @GetMapping("/judge")
    public Result<Integer>judge(@RequestParam String reportId,
                               Integer sentState,
                               String region,
                               Integer acceptState){
        Condition condition = new Condition(MsSentDynamis.class);
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
            criteria.andCondition("weekid like '%" + reportId+"%'");
        }
        List<MsSentDynamis> list = msSentDynamisService.findByCondition(condition);
        Integer judgeResult=1;
        for (MsSentDynamis msSentDynamis:list){
            if (msSentDynamis.getSentState()==2){
                judgeResult=2;
                return ResponseMsgUtil.success(judgeResult);
            }
        }
        return ResponseMsgUtil.success(judgeResult);
    }

    //合并生成PDF
    @GetMapping("/combine")
    public Result<List<String>> combine(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                        @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                        @RequestParam String reportId,
                                        Integer sentState,
                                        String region,
                                        Integer acceptState,
                                        HttpServletResponse response) throws IOException {
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsSentDynamis.class);
        Example.Criteria criteria = condition.createCriteria();
        if (sentState != null) {
            criteria.andCondition("sent_state = " + sentState);
        }
        if (region != null && region != "") {
            criteria.andCondition("region like '%" + region + "%'");
        }
        if (acceptState != null) {
            criteria.andCondition("accept_state = " + acceptState);
        }
        if (reportId != null && reportId != "") {
            criteria.andCondition("weekid like '%" + reportId + "%'");
        }
        List<MsSentDynamis> list = msSentDynamisService.findByCondition(condition);
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph paragraph = xwpfDocument.createParagraph();
        XWPFRun run = paragraph.createRun();
        for (MsSentDynamis msSentDynamis : list) {
            run.setText(msSentDynamis.getRegion());
            run.addCarriageReturn();
            run.setText(msSentDynamis.getPatrolCondition());
            run.addCarriageReturn();
            run.setText(msSentDynamis.getMeetingCondition());
            run.addCarriageReturn();
            run.setText(msSentDynamis.getProblemSolvingCondition());
            run.addCarriageReturn();
            run.setText(msSentDynamis.getOtherCondition());
            run.addCarriageReturn();
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=msSentDynamis.doc");
        response.setContentType("application/x-msdownload");
        xwpfDocument.write(outputStream);
        outputStream.close();
        return ResponseMsgUtil.success();
    }

    @GetMapping("/pdfView")
    public Result<PageInfo<MsSentDynamis>> pdfView(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      @RequestParam String reportId,
                                                      Integer sentState,
                                                      String region,
                                                      Integer acceptState,
                                                   HttpServletResponse response
    ) throws IOException, DocumentException {
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(MsSentDynamis.class);
        Example.Criteria criteria = condition.createCriteria();
        if (sentState != null) {
            criteria.andCondition("sent_state = " + sentState);
        }
        if (region != null && region != "") {
            criteria.andCondition("region like '%" + region + "%'");
        }
        if (acceptState != null) {
            criteria.andCondition("accept_state = " + acceptState);
        }

        System.out.println("-----------reportId-------------:" + reportId);

        if (reportId != null && reportId != "") {
            criteria.andCondition("weekid like '%" + reportId + "%'");
        }
        List<MsSentDynamis> list = msSentDynamisService.findByCondition(condition);
        //--------------------------------------
        //数据组装
        ArrayList<Object[]> dataList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (MsSentDynamis msSentDynamis : list) {
                Object[] obj = {msSentDynamis.getAcceptState(), msSentDynamis.getAccessoryUrl(), msSentDynamis.getBeginTime(), msSentDynamis.getDeadline(), msSentDynamis.getId(), msSentDynamis.getInitiator(), msSentDynamis.getMeetingCondition(), msSentDynamis.getPatrolCondition(), msSentDynamis.getProblemSolvingCondition(), msSentDynamis.getRegion(), msSentDynamis.getSentState(), msSentDynamis.getTitle(), msSentDynamis.getWeekid()};
                dataList.add(obj);
            }
        }
        String[] column = {"acceptState", "accessoryUrl", "beginTime", "deadline", "id", "initiator", "meetingCondition", "patrolCondition", "problemSolvingCondition", "region", "sentState", "title", "weekid"};
        //获得从数据库中查询出来的数据
        if (dataList != null && dataList.size() > 0) {
            String str5 = "";
            //循环list中的数据
            for (int i = 0; i < dataList.size(); i++) {
                Object[] objs = dataList.get(i);
                //HSSFRow dataRow = sheet.createRow(i + 1);
                //HSSFCell data[] = new HSSFCell[column.length];

                String str1 = "";
                String str2 = "";
                String str3 = "";
                String str4 = "";

                for (int j = 0; j < column.length; j++) {
                    //data[j] = dataRow.createCell(j);
                    if (j == 6) {
                        str3 = String.valueOf(objs[j]) + "\n";
                    }
                    if (j == 7) {
                        str2 = String.valueOf(objs[j]) + "\n";
                    }
                    if (j == 8) {
                        str4 = String.valueOf(objs[j]) + "\n";
                    }
                    if (j == 9) {
                        str1 = String.valueOf(objs[j]) + ":" + "\n";
                        str5 += str1 + str2 + str3 + str4 + "\n";
                        //String info = String.valueOf(objs[j]);
                        //System.out.println("-------------------------------");
                        //System.out.println("messagesentstr5-------:" + str5);
                    }
                    //data[j].setCellValue((info == null) ? "" : info);
                }
            }
            System.out.println("totalmessagesentstr5-------:" + str5);
            response.reset();
            response.setHeader("Content-Disposition", "inline; filename=msSentDynamis.pdf");
            response.setContentType("application/pdf");
            ServletOutputStream outputStream = response.getOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 12, Font.NORMAL);
            document.add(new Paragraph(str5, font));
            document.close();
        }
        return ResponseMsgUtil.success();
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

