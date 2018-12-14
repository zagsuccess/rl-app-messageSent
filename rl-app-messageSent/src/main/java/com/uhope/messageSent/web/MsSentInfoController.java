package com.uhope.messageSent.web;

import com.google.common.collect.Lists;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.messageSent.domain.MsSentInfo;
import com.uhope.messageSent.domain.MsSentReports;
import com.uhope.messageSent.domain.MsWorkBulletin;
import com.uhope.messageSent.service.*;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 报送统计
 *
 * @author a4994
 * @create 2018-12-10 10:07
 */
@RestController
@RequestMapping("v1/MsSentInfo")
public class MsSentInfoController {
    @Autowired
    private MsSentInfoService msSentInfoService;

    @Autowired
    private MsMeetingConditionService msMeetingConditionService;

    @Autowired
    private MsSentReportsService msSentReportsService;

    @Autowired
    private MsWorkReportsService msWorkReportsService;

    @Autowired
    private MsWorkBulletinService msWorkBulletinService;

    @Autowired
    private TokenService tokenService;

    //通过时间来查找相应的报送统计
    @GetMapping("/selectList")
    public Result<List<MsSentInfo>> selectList(String sentTime, String endTime, Integer kind, HttpServletRequest request){
        List<Map<String, Object>> regionList = msWorkBulletinService.selectList("3");
        UserDTO userDTO = CommonUtil.getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }

        //首先判断用户的权限(市河长办可以看到所有区的报送记录)
        if ("市河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            //查到包含所有区域名称的list
            List<String> regions = msSentInfoService.selectRegion();


            //定义报送次数、采纳次数、退回次数三个变量
            Integer sentNumber=0;
            Integer acceptNumber=0;
            Integer returnNumber=0;
            //定义一个信息报送list
            List<MsSentInfo> msSentInfoList= new ArrayList<MsSentInfo>();
            //判断信息报送类型是否是“信息报送”
                if (kind == 2){
                    //遍历各个区域的名称
                    for (String region:regions){
                        //创建一个信息报送对象
                        MsSentInfo msSentInfo=new MsSentInfo();
                        //将遍历所得的当前区域名填充至信息报送对象
                        msSentInfo.setRegion(region);
                        Condition condition = new Condition(MsSentReports.class);
                        Example.Criteria criteria = condition.createCriteria();
                        //判断条件是否为空,然后增加查询的限制条件
                        if (sentTime != null && sentTime != ""){
                            criteria.andBetween("beginTime",sentTime,endTime);
                        }
                        //增加查询的区域限制条件
                        criteria.andCondition("region like '%" + region+"%'");
                        //通过条件查询查出信息报送的List
                        List<MsSentReports> msSentReportsList = msSentReportsService.findByCondition(condition);
                        //遍历信息报送的List
                        for (MsSentReports msSentReports:msSentReportsList){
                            //如果当前信息报送对象的报送状态是已报送或者是已退回，那么报送次数+1
                            if (msSentReports.getSentState()==1 || msSentReports.getSentState()==3){
                                sentNumber+=1;
                            }
                            //如果当前信息报送对象的采纳状态是已采纳，那么采纳次数+1
                            if (msSentReports.getAcceptState()==1){
                                acceptNumber+=1;
                            }
                            //如果当前信息报送对象的报送状态是已退回，那么退回次数+1
                            if (msSentReports.getSentState()==3){
                                returnNumber+=1;
                            }
                        }
                        //将报送次数、采纳次数、退回次数填充至信息报送对象
                        msSentInfo.setSentNumber(sentNumber);
                        msSentInfo.setAcceptNumber(acceptNumber);
                        msSentInfo.setReturnNumber(returnNumber);
                        //将当前报送对象加入集合
                        msSentInfoList.add(msSentInfo);
                        sentNumber=0;
                        acceptNumber=0;
                        returnNumber=0;
                    }
                    return ResponseMsgUtil.success(msSentInfoList);
                }
                if (kind == 1){
                    for (String region:regions){
                        //创建一个信息报送对象
                        MsSentInfo msSentInfo=new MsSentInfo();
                        msSentInfo.setRegion(region);
                        String region_code = "";
                        for (Map<String, Object> map : regionList) {
                            if ((map.get("area_name").toString()).equals(region)) {
                                region_code = map.get("area_code").toString();
                            }
                        }
                        Condition condition = new Condition(MsWorkBulletin.class);
                        Example.Criteria criteria = condition.createCriteria();
                        if (sentTime != null && sentTime != ""){
                            criteria.andBetween("beginTime",sentTime,endTime);
                        }
                        criteria.andCondition("region like '%" + region_code+"%'");
                        List<MsWorkBulletin> msWorkBulletinList = msWorkBulletinService.findByCondition(condition);
                        for (MsWorkBulletin msWorkBulletin:msWorkBulletinList){
                            if (msWorkBulletin.getSentState()==1 || msWorkBulletin.getSentState()==3){
                                sentNumber+=1;
                            }
                            if (msWorkBulletin.getAcceptState()==1){
                                acceptNumber+=1;
                            }
                            if (msWorkBulletin.getSentState()==3){
                                returnNumber+=1;
                            }
                        }
                        msSentInfo.setSentNumber(sentNumber);
                        msSentInfo.setAcceptNumber(acceptNumber);
                        msSentInfo.setReturnNumber(returnNumber);
                        msSentInfoList.add(msSentInfo);
                        sentNumber=0;
                        acceptNumber=0;
                        returnNumber=0;
                    }
                    return ResponseMsgUtil.success(msSentInfoList);
                }


            //如果没有涉及分类查询
            if (kind == null || kind == 0){
                //遍历各个区域的名称
                for (String region:regions){
                    //创建一个信息报送对象
                    MsSentInfo msSentInfo=new MsSentInfo();
                    //将当前区域名填充至信息报送对象
                    msSentInfo.setRegion(region);
                    //查询条件一，用于信息报送内层报送表的查询
                    Condition condition1 = new Condition(MsSentReports.class);
                    Example.Criteria criteria1 = condition1.createCriteria();
                    if (sentTime != null && sentTime != ""){
                        criteria1.andBetween("beginTime",sentTime,endTime);
                    }
                    //通过区名的模糊查询来确定哪个区
                    criteria1.andCondition("region like '%" + region+"%'");
                    List<MsSentReports> msSentReportsList = msSentReportsService.findByCondition(condition1);
                    //遍历内层报送表的集合
                    for (MsSentReports msSentReports:msSentReportsList){
                        //如果当前内层报送表的报送状态为1(已报送)，或者报送状态为3(已退回)，
                        if (msSentReports.getSentState()==1 || msSentReports.getSentState()==3){
                            sentNumber=+1;
                        }
                        //如果当前内层报送表的是否采纳状态为1(已采纳)
                        if (msSentReports.getAcceptState()==1){
                            acceptNumber=+1;
                        }
                        //如果当前内层报送表的报送状态为3(已退回)
                        if (msSentReports.getSentState()==3){
                            returnNumber=+1;
                        }
                    }



                    //查询条件而，用于工作简报的查询
                    Condition condition2 = new Condition(MsWorkBulletin.class);
                    Example.Criteria criteria2 = condition2.createCriteria();

                    String region_code = "";
                    for (Map<String, Object> map : regionList) {
                        if ((map.get("area_name").toString()).equals(region)) {
                            region_code = map.get("area_code").toString();
                        }
                    }

                    if (sentTime != null && sentTime != ""){
                        criteria2.andBetween("beginTime",sentTime,endTime);
                    }
                    criteria2.andCondition("region like '%" + region_code+"%'");

                    List<MsWorkBulletin> msWorkBulletinList = msWorkBulletinService.findByCondition(condition2);
                    //遍历工作简报的集合
                    for (MsWorkBulletin msWorkBulletin:msWorkBulletinList){
                        ////如果当前工作简报表的报送状态为1(已报送)，或者报送状态为3(已退回)，那么报送次数+1
                        if (msWorkBulletin.getSentState()==1 || msWorkBulletin.getSentState()==3){
                            sentNumber+=1;
                        }
                        //如果当前工作简报表的是否采纳状态为1(已采纳),那么采纳次数+1
                        if (msWorkBulletin.getAcceptState()==1){
                            acceptNumber+=1;
                        }
                        //如果当前工作简报表表的报送状态为3(已退回)，那么退回次数+1
                        if (msWorkBulletin.getSentState()==3){
                            returnNumber+=1;
                        }
                    }
                    //将报送次数、采纳次数、退回次数填充至信息报送对象
                    msSentInfo.setSentNumber(sentNumber);
                    msSentInfo.setAcceptNumber(acceptNumber);
                    msSentInfo.setReturnNumber(returnNumber);
                    msSentInfoList.add(msSentInfo);

                    sentNumber=0;
                    acceptNumber=0;
                    returnNumber=0;

                }
                return ResponseMsgUtil.success(msSentInfoList);
            }


            return ResponseMsgUtil.success(msSentInfoList);

        }

        //区河长办登录的话
        if ("河长办".equals(msMeetingConditionService.selectRole(userDTO.getId()))){
            MsSentInfo msSentInfo=new MsSentInfo();
            Integer sentNumber=0;
            Integer acceptNumber=0;
            Integer returnNumber=0;
            String region =msWorkReportsService.selectRegion(userDTO.getRegionId());
            List<MsSentInfo> msSentInfoList= new ArrayList<MsSentInfo>();
            //如果类型是“信息报送”
            if (kind == 2){
                //将当前的区名填充至报送统计对象
                msSentInfo.setRegion(region);
                Condition condition = new Condition(MsSentReports.class);
                Example.Criteria criteria = condition.createCriteria();
                if (sentTime != null && sentTime != ""){
                    criteria.andBetween("beginTime",sentTime,endTime);
                }
                criteria.andCondition("region like '%" + region+"%'");
                List<MsSentReports> msSentReportsList = msSentReportsService.findByCondition(condition);
                //遍历条件查询所得的信息报送集合
                for (MsSentReports msSentReports:msSentReportsList){
                    if (msSentReports.getSentState()==1 || msSentReports.getSentState()==3){
                        sentNumber+=1;
                    }
                    if (msSentReports.getAcceptState()==1){
                        acceptNumber+=1;
                    }
                    if (msSentReports.getSentState()==3){
                        returnNumber+=1;
                    }
                }
                msSentInfo.setSentNumber(sentNumber);
                msSentInfo.setAcceptNumber(acceptNumber);
                msSentInfo.setReturnNumber(returnNumber);
                msSentInfoList.add(msSentInfo);
                return ResponseMsgUtil.success(msSentInfoList);
            }

            //如果类型是“工作简报”
            if (kind == 1){
                //将当前的区名填充至报送统计对象
                msSentInfo.setRegion(region);

                String region_code = "";
                for (Map<String, Object> map : regionList) {
                    if ((map.get("area_name").toString()).equals(region)) {
                        region_code = map.get("area_code").toString();
                    }
                }

                Condition condition = new Condition(MsWorkBulletin.class);
                Example.Criteria criteria = condition.createCriteria();
                if (sentTime != null && sentTime != ""){
                    criteria.andBetween("beginTime",sentTime,endTime);
                }
                criteria.andCondition("region like '%" + region_code+"%'");
                List<MsWorkBulletin> msWorkBulletinList = msWorkBulletinService.findByCondition(condition);
                //遍历条件查询所得的工作简报集合
                for (MsWorkBulletin msWorkBulletin:msWorkBulletinList){
                    if (msWorkBulletin.getSentState()==1 || msWorkBulletin.getSentState()==3){
                        sentNumber+=1;
                    }
                    if (msWorkBulletin.getAcceptState()==1){
                        acceptNumber+=1;
                    }
                    if (msWorkBulletin.getSentState()==3){
                        returnNumber+=1;
                    }
                }
                msSentInfo.setSentNumber(sentNumber);
                msSentInfo.setAcceptNumber(acceptNumber);
                msSentInfo.setReturnNumber(returnNumber);
                msSentInfoList.add(msSentInfo);
                return ResponseMsgUtil.success(msSentInfoList);
            }

            //如果没有类别类型
            if (kind == null || kind== 0){
                msSentInfo.setRegion(region);
                //条件一查信息报送
                Condition condition = new Condition(MsSentReports.class);
                Example.Criteria criteria = condition.createCriteria();
                if (sentTime != null && sentTime != ""){
                    criteria.andBetween("beginTime",sentTime,endTime);
                }
                criteria.andCondition("region like '%" + region+"%'");
                List<MsSentReports> msSentReportsList = msSentReportsService.findByCondition(condition);
                for (MsSentReports msSentReports:msSentReportsList){
                    if (msSentReports.getSentState()==1 || msSentReports.getSentState()==3){
                        sentNumber+=1;
                    }
                    if (msSentReports.getAcceptState()==1){
                        acceptNumber+=1;
                    }
                    if (msSentReports.getSentState()==3){
                        returnNumber+=1;
                    }
                }

                //条件二工作简报
                Condition condition2 = new Condition(MsWorkBulletin.class);
                Example.Criteria criteria2 = condition2.createCriteria();
                String region_code = "";
                for (Map<String, Object> map : regionList) {
                    if ((map.get("area_name").toString()).equals(region)) {
                        region_code = map.get("area_code").toString();
                    }
                }
                if (sentTime != null && sentTime != ""){
                    criteria2.andBetween("beginTime",sentTime,endTime);
                }
                criteria2.andCondition("region like '%" + region_code+"%'");
                List<MsWorkBulletin> msWorkBulletinList = msWorkBulletinService.findByCondition(condition2);
                for (MsWorkBulletin msWorkBulletin:msWorkBulletinList){
                    if (msWorkBulletin.getSentState()==1 || msWorkBulletin.getSentState()==3){
                        sentNumber+=1;
                    }
                    if (msWorkBulletin.getAcceptState()==1){
                        acceptNumber+=1;
                    }
                    if (msWorkBulletin.getSentState()==3){
                        returnNumber+=1;
                    }
                }

                //此处的报送次数、采纳次数、退回次数已经包含信息报送+工作简报
                msSentInfo.setSentNumber(sentNumber);
                msSentInfo.setAcceptNumber(acceptNumber);
                msSentInfo.setReturnNumber(returnNumber);
                msSentInfoList.add(msSentInfo);
                return ResponseMsgUtil.success(msSentInfoList);
            }
        }
        return ResponseMsgUtil.success(null);
    }
}
