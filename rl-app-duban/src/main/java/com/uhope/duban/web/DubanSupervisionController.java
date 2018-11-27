package com.uhope.duban.web;

import com.uhope.base.constants.Constant;
import com.uhope.base.dto.PageInfo;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.duban.domain.ScDubanFeedback;
import com.uhope.duban.domain.ScDubanSupervision;
import com.uhope.duban.dto.DubanFeedbackDTO;
import com.uhope.duban.service.DubanFeedbackService;
import com.uhope.duban.service.DubanSupervisionService;
import com.uhope.duban.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.config.FmConfig;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author :shujihui
 * @Date : 2018/11/5
 * @Time : 11:11
 * Aim :
 */

@RestController
@RequestMapping("/v1/DubanSupervision")
public class DubanSupervisionController {
    @Autowired
    private DubanSupervisionService dubanSupervisionService;
    @Autowired
    private DubanFeedbackService dubanFeedbackService;
    @Autowired
    private FileManagerClient fileManagerClient;
    @Autowired
    private Converter converter;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result<ScDubanSupervision> add(ScDubanSupervision dubanSupervision) {
        String[] str=dubanSupervision.getObjectid().split(",");
        for (String s:str) {
            Result<UserDTO> id = userService.getById(s);
            if(id.getData()!=null){
                dubanSupervision.setObjectname(id.getData().getName());
            }
        }
        dubanSupervision.setStatus("1");
        dubanSupervisionService.insert(dubanSupervision);
        for(int i=0;i<str.length;i++){
            ScDubanFeedback scDubanFeedback=new ScDubanFeedback();
            scDubanFeedback.setId(UUID.randomUUID().toString().replaceAll("-",""));
            scDubanFeedback.setObjectid(str[i]);
            scDubanFeedback.setSupervisionid(dubanSupervision.getId());
            scDubanFeedback.setWhether("否");
            scDubanFeedback.setStatus("1");
            scDubanFeedback.setFeedbacktime(new Date());
            dubanFeedbackService.insert(scDubanFeedback);
        }
        return ResponseMsgUtil.success(dubanSupervision);
    }


    @GetMapping("/detail")
    public Result<ScDubanSupervision> detail(@RequestParam String id) {
        ScDubanSupervision dubanSupervision = dubanSupervisionService.get(id);
        if(dubanSupervision!=null){
            dubanSupervision.setAssessory(FmConfig.getAgentUrl() + dubanSupervision.getAssessory());
        }
        return ResponseMsgUtil.success(dubanSupervision);
    }


    //查询所有的区河长办
    @GetMapping("/selectPersonnel")
    public Result<List<UserDTO>> selectPersonnel(){
        String roleId=dubanSupervisionService.selectRoleId("河长办");
        Result<PageInfo<UserDTO>> pageInfoResult = userService.queryUserList(null, null, null, roleId, 0, 0);
        List<UserDTO> userDTOList=new ArrayList<>();
        for (UserDTO userDTO:pageInfoResult.getData().getRecords()){
             if (userDTO.getRegionId()%(1000 * 1000)==0 && userDTO.getRegionId()%(10000 * 10000)!=0){
                 userDTOList.add(userDTO);
             }
        }
        return ResponseMsgUtil.success(userDTOList);
    }


    @GetMapping("/list")
    public Result<com.github.pagehelper.PageInfo<ScDubanSupervision>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                                           String issuedtime,String objectname,String status,String objectid) {

        return ResponseMsgUtil.success(dubanSupervisionService.list(pageNumber,pageSize,issuedtime,objectname,status,objectid));
    }

    /*//添加处理信息
    @PostMapping("/addFeedbackcl")
    public Result<ScDubanFeedback> addFeedbackcl(ScDubanFeedback dubanFeedback) throws ParseException {
        dubanFeedback.setStatus("1");
        dubanFeedback.setCreatetime(new Date());
        dubanFeedbackService.insert(dubanFeedback);
        return ResponseMsgUtil.success(dubanFeedback);
    }*/

    //修改处理信息
    @PutMapping("/updateFeedbackcl")
    public Result<ScDubanFeedback> updateFeedbackcl(@RequestParam String id,
                                                    @RequestParam String feedbacktime,
                                                    @RequestParam String whether,
                                                    String description,
                                                    String assessory) throws ParseException {
        ScDubanFeedback scDubanFeedback=new ScDubanFeedback();
        scDubanFeedback.setId(id);
        scDubanFeedback.setFeedbacktime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(feedbacktime));
        scDubanFeedback.setWhether(whether);
        scDubanFeedback.setDescription(description);
        scDubanFeedback.setAssessory(assessory);
        scDubanFeedback.setStatus("1");
        dubanFeedbackService.update(scDubanFeedback);
        return ResponseMsgUtil.success(scDubanFeedback);
    }

    //添加核查信息
    @PostMapping("/addFeedbackhc")
    public Result<ScDubanFeedback> addFeedbackhc(ScDubanFeedback dubanFeedback) throws ParseException {
        dubanFeedback.setSupervisionid(dubanFeedback.getSupervisionid());
        dubanFeedback.setFeedbacktime(dubanFeedback.getFeedbacktime());
        dubanFeedback.setWhether(dubanFeedback.getWhether());
        dubanFeedback.setDescription(dubanFeedback.getDescription());
        dubanFeedback.setAssessory(dubanFeedback.getAssessory());
        dubanFeedback.setStatus("2");
        dubanFeedback.setCreatetime(new Date());
        dubanFeedbackService.insert(dubanFeedback);
        ScDubanSupervision dubanSupervision = new ScDubanSupervision();
        dubanSupervision.setId(dubanFeedback.getSupervisionid());
        if("是".equals(dubanFeedback.getWhether())){
            dubanSupervision.setStatus("4");
        }else{
            dubanSupervision.setStatus("5");
        }
        dubanSupervisionService.update(dubanSupervision);
        return ResponseMsgUtil.success(dubanFeedback);
    }



    /*@GetMapping("/detailFeedbackhf")
    public Result<ScDubanFeedback> detailFeedbackhf(@RequestParam String supervisionid) {
        ScDubanFeedback dubanFeedback = new ScDubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("0");
        return ResponseMsgUtil.success(dubanFeedbackService.selectFeedback(dubanFeedback));
    }*/

    //市河长办可以查看根据督办id查看全部的处理反馈
    /*@GetMapping("/detailFeedbackcls")
    public Result<List<ScDubanFeedback>> detailFeedbackcls(@RequestParam String supervisionid) {
        ScDubanFeedback dubanFeedback = new ScDubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("1");
        List<ScDubanFeedback> scDubanFeedback = dubanFeedbackService.selectFeedbackBys(dubanFeedback);
        for (ScDubanFeedback scDubanFeedback1:scDubanFeedback) {
            scDubanFeedback1.setAssessory(FmConfig.getAgentUrl() + scDubanFeedback1.getAssessory());
        }
        return ResponseMsgUtil.success(scDubanFeedback);
    }*/

    //督办对象得根据对象的id查询处理信息
    @GetMapping("/detailFeedbackcl")
    public Result<List<DubanFeedbackDTO>> detailFeedbackcl(@RequestParam String supervisionid,
                                                          String objectid) {
        ScDubanFeedback dubanFeedback = new ScDubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setObjectid(objectid);
        dubanFeedback.setStatus("1");
        List<DubanFeedbackDTO> scDubanFeedback = dubanFeedbackService.selectFeedbackByobjectid(dubanFeedback);
        if (scDubanFeedback!=null && scDubanFeedback.size()>0 ){
            for (DubanFeedbackDTO scDubanFeedback1:scDubanFeedback) {
                scDubanFeedback1.setAssessory(FmConfig.getAgentUrl() + scDubanFeedback1.getAssessory());
                Result<UserDTO> id = userService.getById(scDubanFeedback1.getObjectid());
                if(id!=null && id.getData()!=null){
                    scDubanFeedback1.setObjectname(id.getData().getName());
                }
            }
        }
        return ResponseMsgUtil.success(scDubanFeedback);
    }


    @GetMapping("/detailFeedbackhc")
    public Result<ScDubanFeedback> detailFeedbackhc(@RequestParam String supervisionid) {
        ScDubanFeedback dubanFeedback = new ScDubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("2");
        ScDubanFeedback scDubanFeedback = dubanFeedbackService.selectFeedback(dubanFeedback);
        scDubanFeedback.setAssessory(FmConfig.getAgentUrl() + scDubanFeedback.getAssessory());
        return ResponseMsgUtil.success(scDubanFeedback);
    }

    //查询是否有核查
    @GetMapping("/selectFeedbackhc")
    public Result<String> selectFeedbackhc(@RequestParam String supervisionid) {
        ScDubanFeedback dubanFeedback = new ScDubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("2");
        ScDubanFeedback dubanFeedback1 = dubanFeedbackService.selectFeedback(dubanFeedback);
        String msg="没有";
        if(dubanFeedback1!=null){
            msg="有";
        }
        return ResponseMsgUtil.success(msg);
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
            if (lastName.contains("doc")|| lastName.contains("xls")){
                filePath = converter.startConverter(fileItem.getVirtualPath());
            }
            list.add(filePath);
        }
        return ResponseMsgUtil.success(list);
    }

    //获取远程服务器资源地址
    @GetMapping("/fileUrl")
    public Result fileUrl(){
        return ResponseMsgUtil.success(FmConfig.getDownloadUri());
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

        if("市河长办".equals(dubanFeedbackService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if("河长办".equals(dubanFeedbackService.selectRole(userDTO.getId()))){
            grade="05";
        }

        return ResponseMsgUtil.success(grade);
    }
    @GetMapping("/userinfo1")
    public Result<String> userinfo1(String id){

        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(dubanFeedbackService.selectRole(id))){
            grade="02";
        }

        if("河长办".equals(dubanFeedbackService.selectRole(id))){
            grade="05";
        }

        return ResponseMsgUtil.success(grade);
    }
}
