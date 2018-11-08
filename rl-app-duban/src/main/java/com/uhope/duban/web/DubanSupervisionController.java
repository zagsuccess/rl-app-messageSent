package com.uhope.duban.web;

import com.github.pagehelper.PageHelper;
import com.uhope.base.constants.Constant;
import com.uhope.base.dto.PageInfo;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.converter.client.Converter;
import com.uhope.duban.domain.DubanFeedback;
import com.uhope.duban.domain.DubanSupervision;
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
    public Result<DubanSupervision> add(DubanSupervision dubanSupervision) {
        dubanSupervision.setTitle(dubanSupervision.getTitle());
        dubanSupervision.setProject(dubanSupervision.getProject());
        dubanSupervision.setIssuedtime(dubanSupervision.getIssuedtime());
        dubanSupervision.setDeadlinedate(dubanSupervision.getDeadlinedate());
        dubanSupervision.setReason(dubanSupervision.getReason());
        dubanSupervision.setObjectid(dubanSupervision.getObjectid());
        Result<UserDTO> id = userService.getById(dubanSupervision.getObjectid());
        dubanSupervision.setObjectname(id.getData().getName());
        dubanSupervision.setAssessory(dubanSupervision.getAssessory());
        dubanSupervision.setAssessorydescribe(dubanSupervision.getAssessorydescribe());
        dubanSupervision.setStatus("0");
        dubanSupervisionService.insert(dubanSupervision);
        return ResponseMsgUtil.success(dubanSupervision);
    }


    @GetMapping("/detail")
    public Result<DubanSupervision> detail(@RequestParam String id) {
        DubanSupervision dubanSupervision = dubanSupervisionService.get(id);
        return ResponseMsgUtil.success(dubanSupervision);
    }


    //查询所有的区河长办
    @GetMapping("/selectPersonnel")
    public Result<List<UserDTO>> selectPersonnel(){
        String roleId=dubanSupervisionService.selectRoleId("河长办");
        Result<PageInfo<UserDTO>> pageInfoResult = userService.queryUserList(null, null, null, roleId, 0, 0);
        List<UserDTO> userDTOList=new ArrayList<>();
        for (UserDTO userDTO:pageInfoResult.getData().getRecords()){
             if (userDTO.getRegionId()%(1000 * 1000)==0){
                 userDTOList.add(userDTO);
             }
        }
        return ResponseMsgUtil.success(userDTOList);
    }

    @GetMapping("/list")
    public Result<com.github.pagehelper.PageInfo<DubanSupervision>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                                         @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                                         String issuedtime,String objectname,String status) {

        return ResponseMsgUtil.success(dubanSupervisionService.list(pageNumber,pageSize,issuedtime,objectname,status));
    }

    //添加回复信息
    @PostMapping("/addFeedbackhf")
    public Result<DubanFeedback> addFeedbackhf(DubanFeedback dubanFeedback) throws ParseException {
        dubanFeedback.setSupervisionid(dubanFeedback.getSupervisionid());
        dubanFeedback.setFeedbacktime(dubanFeedback.getFeedbacktime());
        dubanFeedback.setWhether(dubanFeedback.getWhether());
        dubanFeedback.setDescription(dubanFeedback.getDescription());
        dubanFeedback.setAssessory(dubanFeedback.getAssessory());
        dubanFeedback.setStatus("0");
        dubanFeedbackService.insert(dubanFeedback);
        DubanSupervision dubanSupervision = new DubanSupervision();
        dubanSupervision.setId(dubanFeedback.getSupervisionid());
        dubanSupervision.setStatus("1");
        dubanSupervisionService.update(dubanSupervision);
        return ResponseMsgUtil.success(dubanFeedback);
    }

    //添加处理信息
    @PostMapping("/addFeedbackcl")
    public Result<DubanFeedback> addFeedbackcl(DubanFeedback dubanFeedback) throws ParseException {
        dubanFeedback.setSupervisionid(dubanFeedback.getSupervisionid());
        dubanFeedback.setFeedbacktime(dubanFeedback.getFeedbacktime());
        dubanFeedback.setWhether(dubanFeedback.getWhether());
        dubanFeedback.setDescription(dubanFeedback.getDescription());
        dubanFeedback.setAssessory(dubanFeedback.getAssessory());
        dubanFeedback.setStatus("1");
        dubanFeedbackService.insert(dubanFeedback);
        DubanSupervision dubanSupervision = new DubanSupervision();
        dubanSupervision.setId(dubanFeedback.getSupervisionid());
        if("是".equals(dubanFeedback.getWhether())){
            dubanSupervision.setStatus("2");
        }else{
            dubanSupervision.setStatus("3");
        }
        dubanSupervisionService.update(dubanSupervision);
        return ResponseMsgUtil.success(dubanFeedback);
    }

    //添加核查信息
    @PostMapping("/addFeedbackhc")
    public Result<DubanFeedback> addFeedbackhc(DubanFeedback dubanFeedback) throws ParseException {
        dubanFeedback.setSupervisionid(dubanFeedback.getSupervisionid());
        dubanFeedback.setFeedbacktime(dubanFeedback.getFeedbacktime());
        dubanFeedback.setWhether(dubanFeedback.getWhether());
        dubanFeedback.setDescription(dubanFeedback.getDescription());
        dubanFeedback.setAssessory(dubanFeedback.getAssessory());
        dubanFeedback.setStatus("2");
        dubanFeedbackService.insert(dubanFeedback);
        DubanSupervision dubanSupervision = new DubanSupervision();
        dubanSupervision.setId(dubanFeedback.getSupervisionid());
        if("是".equals(dubanFeedback.getWhether())){
            dubanSupervision.setStatus("4");
        }else{
            dubanSupervision.setStatus("5");
        }
        dubanSupervisionService.update(dubanSupervision);
        return ResponseMsgUtil.success(dubanFeedback);
    }



    @GetMapping("/detailFeedbackhf")
    public Result<DubanFeedback> detailFeedbackhf(@RequestParam String supervisionid) {
        DubanFeedback dubanFeedback = new DubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("0");
        return ResponseMsgUtil.success(dubanFeedbackService.selectFeedback(dubanFeedback));
    }

    @GetMapping("/detailFeedbackcl")
    public Result<DubanFeedback> detailFeedbackcl(@RequestParam String supervisionid) {
        DubanFeedback dubanFeedback = new DubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("1");
        return ResponseMsgUtil.success(dubanFeedbackService.selectFeedback(dubanFeedback));
    }


    @GetMapping("/detailFeedbackhc")
    public Result<DubanFeedback> detailFeedbackhc(@RequestParam String supervisionid) {
        DubanFeedback dubanFeedback = new DubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("2");
        return ResponseMsgUtil.success(dubanFeedbackService.selectFeedback(dubanFeedback));
    }

    //查询是否有核查
    @GetMapping("/selectFeedbackhc")
    public Result<String> selectFeedbackhc(@RequestParam String supervisionid) {
        DubanFeedback dubanFeedback = new DubanFeedback();
        dubanFeedback.setSupervisionid(supervisionid);
        dubanFeedback.setStatus("2");
        DubanFeedback dubanFeedback1 = dubanFeedbackService.selectFeedback(dubanFeedback);
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
            if (lastName.contains("doc")){
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
}
