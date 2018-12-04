package com.uhope.messageSent.web;
import com.uhope.base.constants.Constant;
import com.uhope.converter.client.Converter;
import com.uhope.messageSent.domain.MsSentDynamis;
import com.uhope.messageSent.service.MsSentDynamisService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.messageSent.service.MsWorkReportsService;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.fm.client.FileManagerClient;
import com.uhope.uip.fm.model.FileItem;
import com.uhope.uip.service.TokenService;
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
                                     @RequestParam String region,
                                     @RequestParam String deadline,
                                     String accessoryUrl,
                                     @RequestParam String patrolCondition,
                                     @RequestParam String meetingCondition,
                                     @RequestParam String problemSolvingCondition,
                                     HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsSentDynamis msSentDynamis=new MsSentDynamis();
        msSentDynamis.setTitle(title);
        msSentDynamis.setWeekid(weekId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msSentDynamis.setBeginTime(simpleDateFormat.format(new Date()));
        msSentDynamis.setRegion(region);
        msSentDynamis.setDeadline(deadline);
        msSentDynamis.setInitiator(initiator);
        msSentDynamis.setAccessoryUrl(accessoryUrl);
        msSentDynamis.setSentState(2);
        msSentDynamis.setAcceptState(3);
        msSentDynamis.setPatrolCondition(patrolCondition);
        msSentDynamis.setMeetingCondition(meetingCondition);
        msSentDynamis.setProblemSolvingCondition(problemSolvingCondition);
        msSentDynamisService.insert(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
    }

    //保存且上报
    @PostMapping("/addAndSave")
    public Result<MsSentDynamis> addAndSave(@RequestParam String title,
                                     @RequestParam String weekId,
                                     @RequestParam String region,
                                     @RequestParam String deadline,
                                     String accessoryUrl,
                                     @RequestParam String patrolCondition,
                                     @RequestParam String meetingCondition,
                                     @RequestParam String problemSolvingCondition,
                                     HttpServletRequest request
    ) {
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        MsSentDynamis msSentDynamis=new MsSentDynamis();
        msSentDynamis.setTitle(title);
        msSentDynamis.setWeekid(weekId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msSentDynamis.setBeginTime(simpleDateFormat.format(new Date()));
        msSentDynamis.setRegion(region);
        msSentDynamis.setDeadline(deadline);
        msSentDynamis.setInitiator(initiator);
        msSentDynamis.setAccessoryUrl(accessoryUrl);
        msSentDynamis.setSentState(1);
        msSentDynamis.setAcceptState(3);
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

    @GetMapping("/findByReportId")
    public Result<MsSentDynamis> findByReportId(@RequestParam String reportId,
                                                HttpServletRequest request){
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String region =msWorkReportsService.selectRegion(userDTO.getRegionId());
        MsSentDynamis msSentDynamis = msSentDynamisService.findByReportId(reportId,region);
        return ResponseMsgUtil.success(msSentDynamis);
    }


    @PutMapping("/updateSentState")
    public Result<MsSentDynamis> updateSentState(@RequestParam String id,@RequestParam Integer sentState) {
        MsSentDynamis msSentDynamis = new MsSentDynamis();
        msSentDynamis.setId(id);
        msSentDynamis.setSentState(sentState);
        msSentDynamisService.update(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
    }

    @PutMapping("/updateAcceptState")
    public Result<MsSentDynamis> updateAcceptState(@RequestParam String id,@RequestParam Integer acceptState) {
        MsSentDynamis msSentDynamis = new MsSentDynamis();
        msSentDynamis.setId(id);
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


    @PutMapping("/update")
    public Result<MsSentDynamis> update(MsSentDynamis msSentDynamis) {
        msSentDynamisService.update(msSentDynamis);
        return ResponseMsgUtil.success(msSentDynamis);
    }


    @GetMapping("/detail")
    public Result<MsSentDynamis> detail(@RequestParam String id) {
        MsSentDynamis msSentDynamis = msSentDynamisService.get(id);
        return ResponseMsgUtil.success(msSentDynamis);
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
    public Result<String>judge(@RequestParam String reportId,
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
        String judgeResult="各区域已上报";
        for (MsSentDynamis msSentDynamis:list){
            if (msSentDynamis.getSentState()==2){
                judgeResult="存在未上报区域";
                return ResponseMsgUtil.success(judgeResult);
            }
        }
        return ResponseMsgUtil.success(judgeResult);
    }
}
