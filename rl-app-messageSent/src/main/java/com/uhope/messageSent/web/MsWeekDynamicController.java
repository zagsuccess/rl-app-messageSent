package com.uhope.messageSent.web;
import com.google.common.collect.Lists;
import com.uhope.base.constants.Constant;
import com.uhope.messageSent.domain.MsSentDynamis;
import com.uhope.messageSent.domain.MsWeekDynamic;
import com.uhope.messageSent.domain.MsWorkReports;
import com.uhope.messageSent.service.MsMeetingConditionService;
import com.uhope.messageSent.service.MsSentDynamisService;
import com.uhope.messageSent.service.MsWeekDynamicService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.messageSent.service.MsWorkReportsService;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.String;

/**
 * 周动态表-Controller类
 * @author mengaoran on 2018/11/27
 */
@RestController
@RequestMapping("v1/msWeekDynamic")
public class MsWeekDynamicController {
    @Autowired
    private MsWeekDynamicService msWeekDynamicService;

    @Autowired
    private MsMeetingConditionService msMeetingConditionService;

    @Autowired
    private MsWorkReportsService msWorkReportsService;

    @Autowired
    private MsSentDynamisService msSentDynamisService;


    @Autowired
    private TokenService tokenService;

    @GetMapping("/userinfo")
    public Result<String> userinfo(HttpServletRequest request){
        //获取当前用户信息
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }
        //默认是00   （00表示都不是  01表示市环保局  02表示市河长办 ）
        String grade="00";

        if("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            grade="02";
        }

        if("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            grade="05";
        }
        return ResponseMsgUtil.success(grade);
    }


    @PostMapping("/add")
    public Result<MsWeekDynamic> add(@RequestParam String title,
                                     @RequestParam String sentRegion,
                                     @RequestParam String sentTimeStart,
                                     @RequestParam String sentTimeEnd,
                                     @RequestParam String deadline,
                                     HttpServletRequest request
                                     ) {
        MsWeekDynamic msWeekDynamic=new MsWeekDynamic();
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        String initiator=userDTO.getName();
        msWeekDynamic.setInitiator(initiator);
        msWeekDynamic.setTitle(title);
        msWeekDynamic.setSentRegion(sentRegion);
        msWeekDynamic.setSentTimeStart(sentTimeStart);
        msWeekDynamic.setSentTimeEnd(sentTimeEnd);
        msWeekDynamic.setDeadline(deadline);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        msWeekDynamic.setBeginTime(simpleDateFormat.format(new Date()));
        msWeekDynamicService.insert(msWeekDynamic);
        return ResponseMsgUtil.success(msWeekDynamic);
    }



    @GetMapping("/selectList")
    public Result<PageInfo<MsWeekDynamic>> selectList(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      String title,
                                                      String begionTime,
                                                      String deadline,
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
            if (begionTime != null && begionTime != "") {
                criteria.andLike("begionTime",begionTime);
            }
            if (deadline != null && deadline != "") {
                criteria.andLike("deadline",deadline);
            }
            if (sentRegion != null && sentRegion !="") {
                criteria.andLike("sentRegion",sentRegion);
            }

            if (title != null && title !="") {
                criteria.andLike("title",title);
            }
            criteria.andLike("initiator",userDTO.getName());
            List<MsWeekDynamic> list = msWeekDynamicService.findByCondition(condition);
            for (MsWeekDynamic msWeekDynamic:list
            ) {
                msWeekDynamic.setDirection("发起");
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
            if (begionTime != null && begionTime != "") {
                criteria.andLike("begionTime",begionTime);
            }
            if (deadline != null && deadline != "") {
                criteria.andLike("deadline",deadline);
            }
            //String sentUnit2="天津市"+sentUnit;
            if (sentUnit != null && sentUnit !="") {
                criteria.andCondition("sent_region like '%" + sentUnit+"%'");
                /*criteria.andLike("sentRegion",sentUnit);*/
            }
            //查询得到下发区域包含目前登录账号区域的list
            List<MsWeekDynamic> list = msWeekDynamicService.findByCondition(condition);
            //新建一个list1集合来根据天健查询中的是否报送来存放MsWeekDynamic对象
            List<MsWeekDynamic> list1= Lists.newArrayList();
            for (MsWeekDynamic msWeekDynamic:list
            ) {
                //查到的MsWeekDynamic对象方向为"报送"
                msWeekDynamic.setDirection("报送");
                MsSentDynamis msSentDynamis=new MsSentDynamis();
                //根据msWeekDynamic的id以及区域名字来得到报送报表
                msSentDynamis=msSentDynamisService.findByReportId(msWeekDynamic.getId(),sentUnit);
                if (msSentDynamis==null){
                    //weatherSend(1.已上报  2.未上报 3.已退回)
                    msWeekDynamic.setWeatherSent(2);
                    if (sentState != null && sentState==2){
                        list1.add(msWeekDynamic);
                    }
                }
                if (msSentDynamis!=null){
                    //weatherSend(1.已上报  2.未上报 3.已退回)
                    //sentState(1.已上报  2.未上报 3.已退回)
                    if (msSentDynamis.getSentState()==1){
                        msWeekDynamic.setWeatherSent(1);
                        if (sentState!= null && sentState ==1){
                            list1.add(msWeekDynamic);
                        }
                    }
                    if (msSentDynamis.getSentState()==3){
                        msWeekDynamic.setWeatherSent(3);
                        if (sentState!= null && sentState ==3){
                            list1.add(msWeekDynamic);
                        }
                    }
                    //getAcceptState(1.已采纳  2.未采纳 3.无操作)
                    Integer i=msSentDynamis.getAcceptState();
                    if (i==1){
                        msWeekDynamic.setWeatherAccept(1);
                    }
                    if (i==2){
                        msWeekDynamic.setWeatherAccept(2);
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










    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        msWeekDynamicService.remove(id);
        return ResponseMsgUtil.success(null);
    }


    @PutMapping("/update")
    public Result<MsWeekDynamic> update(MsWeekDynamic msWeekDynamic) {
        msWeekDynamicService.update(msWeekDynamic);
        return ResponseMsgUtil.success(msWeekDynamic);
    }


    @GetMapping("/detail")
    public Result<MsWeekDynamic> detail(@RequestParam String id) {
        MsWeekDynamic msWeekDynamic = msWeekDynamicService.get(id);
        return ResponseMsgUtil.success(msWeekDynamic);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<MsWeekDynamic>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if (pageNumber > 0 && pageSize > 0){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<MsWeekDynamic> list = msWeekDynamicService.find();
        PageInfo<MsWeekDynamic> pageInfo = new PageInfo<>(list);
        return ResponseMsgUtil.success(pageInfo);
    }
}
