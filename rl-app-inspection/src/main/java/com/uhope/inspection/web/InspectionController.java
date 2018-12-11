package com.uhope.inspection.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.inspection.domain.ScInspection;
import com.uhope.inspection.dto.ScInspectionDTO;
import com.uhope.inspection.service.InspectionService;
import com.uhope.converter.client.Converter;
import com.uhope.inspection.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 督查
 *
 * @author a4994
 * @create 2018-11-06 14:33
 */
@RestController
@RequestMapping("/v1/Inspection")
public class InspectionController {

    @Autowired
    private Converter converter;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private FileManagerClient fileManagerClient;



    @PostMapping("/add")
    public Result<ScInspection> add(
            @RequestParam String title,
            @RequestParam String renumber,
            @RequestParam String printDate,
            @RequestParam String inspectType,
            @RequestParam String sentRegion,
            @RequestParam(required = false) String accessory,
            @RequestParam String content,
            @RequestParam String sentUnit
    ) {
        ScInspection scInspection = new ScInspection();
        scInspection.setContent(content);
        scInspection.setAccessory(accessory);
        if (accessory!=null&&accessory!=""){
            String tempString=accessory.substring(accessory.lastIndexOf(".")+1);
            String pdfUrl=accessory;
            if(tempString.contains("doc")){
                pdfUrl=converter.startConverter(accessory);
            }
            scInspection.setPdfUrl(pdfUrl);
        }
        scInspection.setInspectType(inspectType);
        scInspection.setPrintDate(printDate);
        scInspection.setRenumber(renumber);
        scInspection.setTitle(title);
        scInspection.setSentUnit(sentUnit);
        scInspection.setSentRegion(sentRegion);
        scInspection.setState(1);
        inspectionService.insert(scInspection);
        return ResponseMsgUtil.success(scInspection);
    }

    @GetMapping("/selectList")
    public Result<PageInfo<ScInspection>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                     @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                     String printDate,
                                                     String renumber,
                                                     String title,
                                                     Integer state,
                                                     HttpServletRequest request
    ) {
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }


        if("市河长办".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();

            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
                condition.orderBy("print_date").desc();
            List<ScInspection> list = inspectionService.findByCondition(condition);

            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if("河长办".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit=inspectionService.selectRegion(userDTO.getRegionId());
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            criteria.andCondition("sent_region like '%" + sentUnit+"%'");
            condition.orderBy("print_date").desc();
            List<ScInspection> list = inspectionService.findByCondition(condition);

            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("市环保局".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit="市环保局";
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            if (sentUnit !=null){
                criteria.andCondition("sent_unit like '%" + sentUnit+"%'");
            }
            List<ScInspection> list = inspectionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("市市容园林委".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit="市市容园林委";
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            if (sentUnit !=null){
                criteria.andCondition("sent_unit like '%" + sentUnit+"%'");
            }
            List<ScInspection> list = inspectionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("市农委".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit="市农委";
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            if (sentUnit !=null){
                criteria.andCondition("sent_unit like '%" + sentUnit+"%'");
            }
            List<ScInspection> list = inspectionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("市建委".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit="市建委";
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            if (sentUnit !=null){
                criteria.andCondition("sent_unit like '%" + sentUnit+"%'");
            }
            List<ScInspection> list = inspectionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("市国土房管局".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit="市国土房管局";
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            if (sentUnit !=null){
                criteria.andCondition("sent_unit like '%" + sentUnit+"%'");
            }
            List<ScInspection> list = inspectionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        if ("市财政局".equals(inspectionService.selectRole(userDTO.getId()))){
            PageHelper.startPage(pageNumber, pageSize);
            Condition condition = new Condition(ScInspection.class);
            Example.Criteria criteria = condition.createCriteria();
            String sentUnit="市财政局";
            if (title != null && title != "") {
                criteria.andCondition("title like '%" + title+"%'");
            }
            if (printDate != null && printDate != "") {
                criteria.andCondition("print_date like '%" + printDate+"%'");
            }
            if (renumber != null && renumber != "") {
                criteria.andCondition("renumber = " + renumber);
            }
            if (state != null) {
                criteria.andCondition("state = " + state);
            }
            if (sentUnit !=null){
                criteria.andCondition("sent_unit like '%" + sentUnit+"%'");
            }
            List<ScInspection> list = inspectionService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            return ResponseMsgUtil.success(pageInfo);
        }
        PageHelper.startPage(pageNumber, pageSize);
        Condition condition = new Condition(ScInspection.class);
        Example.Criteria criteria = condition.createCriteria();
        if (title != null && title != "") {
            criteria.andCondition("title like '%" + title+"%'");
        }
        if (printDate != null && printDate != "") {
            criteria.andCondition("print_date like '%" + printDate+"%'");
        }
        if (renumber != null && renumber != "") {
            criteria.andCondition("renumber = " + renumber);
        }
        if (state != null) {
            criteria.andCondition("state = " + state);
        }

        List<ScInspection> list = inspectionService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);

    }

    @GetMapping("/detail")
    public Result<ScInspection> detail(@RequestParam String id) {
        ScInspection scInspection=inspectionService.get(id);
        String url=scInspection.getAccessory();
        String url1=scInspection.getPdfUrl();
        scInspection.setAccessory(FmConfig.getAgentUrl()+url);
        scInspection.setPdfUrl(FmConfig.getAgentUrl()+url1);
        return ResponseMsgUtil.success(scInspection);
    }

    @PutMapping("/update")
    public Result<ScInspection> update(
            @RequestParam String id,
             String title,
             String renumber,
             String printDate,
             String inspectType,
             String content,
            @RequestParam(required = false) String accessory,
            Integer state,
             String sentRegion
    ) {
        ScInspection scInspection = new ScInspection();
        scInspection.setId(id);
        scInspection.setContent(content);
        scInspection.setAccessory(accessory);
        scInspection.setInspectType(inspectType);
        scInspection.setPrintDate(printDate);
        scInspection.setRenumber(renumber);
        scInspection.setTitle(title);
        scInspection.setSentRegion(sentRegion);
        scInspection.setState(state);
        inspectionService.update(scInspection);
        return ResponseMsgUtil.success(inspectionService.findBy("id", id));
    }

    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if ("市环保局".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }

        if ("市市容园林委".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }

        if ("市农委".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }

        if ("市建委".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }

        if ("市国土房管局".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }

        if ("市财政局".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }

        if("河长办".equals(inspectionService.selectRole(userDTO.getId()))){
            grade="05";
        }
        return ResponseMsgUtil.success(grade);
    }



    @PutMapping("/updateStatus0")
    public Result<ScInspection> updateStatus0(@RequestParam String id) {
        ScInspection scInspection = new ScInspection();
        scInspection.setId(id);
        scInspection.setState(0);
        inspectionService.update(scInspection);
        return ResponseMsgUtil.success(scInspection);
    }

    @PutMapping("/updateStatus2")
    public Result<ScInspection> updateStatus2(@RequestParam String id) {
        ScInspection scInspection = new ScInspection();
        scInspection.setId(id);
        scInspection.setState(2);
        inspectionService.update(scInspection);
        return ResponseMsgUtil.success(scInspection);
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
