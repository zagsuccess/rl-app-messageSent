package com.uhope.rl.resumption.web;

import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.rl.application.basicdata.dto.AdministrativeRegionDTO;
import com.uhope.rl.application.basicdata.services.AdministrativeRegionService;
import com.uhope.rl.resumption.dto.statistics.ProblemStatisticDTO;
import com.uhope.rl.resumption.dto.statistics.ReachPatrolNumStatisticDTO;
import com.uhope.rl.resumption.dto.statistics.ReachmanPatrolNumStatisticDTO;
import com.uhope.rl.resumption.service.ResumptionService;
import com.uhope.rl.resumption.utils.DateUtil;
import com.uhope.rl.resumption.utils.TimeUtil;
import com.uhope.uip.dto.RoleDTO;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.RoleService;
import com.uhope.uip.service.TokenService;
import com.uhope.uip.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

import static com.uhope.rl.resumption.utils.CommonUtil.getFeigionServiceResultData;

/**
 * @author: Yang Jiaheng
 * @date: 2018/9/11
 * @description: 河长巡河统计信息
 */
@RestController
@RequestMapping({"/resumption/v1","/v1/resumption"})
public class ResumpController {

    private static final Logger log = LoggerFactory.getLogger(ResumpController.class);

    @Autowired
    private ResumptionService resumptionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 根据区域 、时间统计巡查次数 河长巡河统计分析-巡查达标率
     * @param request
     * @param type
     * @param regionId
     * @param statTime
     * @param endTime
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/listReachPatrolNumStatistic")
    public Result<PageInfo<ReachPatrolNumStatisticDTO>> listReachPatrolNumStatistic(
            HttpServletRequest request
            ,@RequestParam(name = "type",defaultValue = "2") Integer type
            ,@RequestParam(name = "regionId", required = false) String regionId
            ,@RequestParam(name = "startTime", required = false) String statTime
            ,@RequestParam(name = "endTime", required = false) String endTime
            ,@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            ,@RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ){
        //没有按时间进行查询，设置默认时间
        if (statTime == null && endTime == null){
            Date startTimeDate = TimeUtil.getStartTime(type);
            Date endTimeDate = TimeUtil.getEndTime(type);

            statTime = DateUtil.getDate(startTimeDate);
            endTime = DateUtil.getDate(endTimeDate);
        }
        int intervalMonths=Math.abs(DateUtil.getIntervalMonths(statTime, endTime));

        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if(userDTO == null ){
            return ResponseMsgUtil.failure("获取用户失败");
        }

        //存放返回数据
        List<ReachPatrolNumStatisticDTO> list = new ArrayList<ReachPatrolNumStatisticDTO>();
        //用来存放暂时数据
        List<ReachPatrolNumStatisticDTO> tempList = new ArrayList<ReachPatrolNumStatisticDTO>();

        //获取用户的regionId并解析其河长级别
        //regionid 对应的结构为：22233  省，市，区，镇，村
        //默认是村级
        int grade = 5;
        if(userDTO.getRegionId()%(1000)==0){
            //镇级
            grade=4;
            //查找村级河长应巡次数,相当于给list赋初值
            list = resumptionService.findReachNeedPatrolNumStatistic(type,regionId,intervalMonths,5, pageNumber, pageSize);
            //查找村级河长已巡次数
            tempList = resumptionService.findReachHadPatrolNumStatistic(type,regionId,statTime, endTime,5, pageNumber, pageSize);
            list = assignListField(tempList, list, 8);
        }
        if(userDTO.getRegionId()%(1000*1000)==0){
            //区级
            grade=3;
            //查找镇级河长应巡次数
            tempList = resumptionService.findReachNeedPatrolNumStatistic(type,regionId,intervalMonths,4, pageNumber, pageSize);
            //将镇级河长应巡次数赋值给list
            list = assignListField(tempList, list, 4);
            tempList = resumptionService.findReachHadPatrolNumStatistic(type,regionId,statTime, endTime,4, pageNumber, pageSize);
            list = assignListField(tempList, list, 7);
        }
        if(userDTO.getRegionId()%(1000*1000*100)==0){
            //市级
            grade=2;
            //查找区级河长应巡次数
            tempList = resumptionService.findReachNeedPatrolNumStatistic(type,regionId,intervalMonths,3, pageNumber, pageSize);
            list = assignListField(tempList, list, 3);
            tempList = resumptionService.findReachHadPatrolNumStatistic(type,regionId,statTime, endTime,3, pageNumber, pageSize);
            list = assignListField(tempList, list, 6);
        }
        if(userDTO.getRegionId()%(1000*1000*100*100)==0){
            //省级
            grade=1;
        }

        list = calc(list, grade);

        //设置type预留字段
        list = setType(list, type);

        request.setAttribute("grade", grade);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 查找区中各个等级河长的巡河情况
     * @param request
     * @param regionId
     * @param grade
     * @param statTime
     * @param endTime
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/findPersonReachNum")
    public Result<PageInfo<ReachmanPatrolNumStatisticDTO>> findPersonReachNum(
            HttpServletRequest request
            ,@RequestParam(name = "regionId") String regionId
            ,@RequestParam(name = "grade",required = true) Integer grade
            ,@RequestParam(name = "type", required = false) Integer type
            ,@RequestParam(name = "startTime", required = false) String statTime
            ,@RequestParam(name = "endTime", required = false) String endTime
            ,@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            ,@RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ){
        //没有按时间进行查询，设置默认时间
        if (statTime == null && endTime == null){
            Date startTimeDate = TimeUtil.getStartTime(type);
            Date endTimeDate = TimeUtil.getEndTime(type);

            statTime = DateUtil.getDate(startTimeDate);
            endTime = DateUtil.getDate(endTimeDate);
        }
        int intervalMonths=Math.abs(DateUtil.getIntervalMonths(statTime, endTime));

        //查找统计数值
        List<ReachmanPatrolNumStatisticDTO> list = resumptionService.findPersonPatrolNum(intervalMonths, regionId, statTime, endTime, grade);
        //计算达标率
        double d=0.0;
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i=0; i<list.size(); i++){
            ReachmanPatrolNumStatisticDTO item = list.get(i);
            Integer hadReachNum = item.getHadReachNum();
            Integer needReachNum = item.getNeedReachNum();
            d = hadReachNum/(double) needReachNum;
            item.setPatrolRate(hadReachNum==0?0.0:Double.valueOf(df.format(d)));
        }

        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/listProblemStatistic")
    public Result<PageInfo<ProblemStatisticDTO>> listProblemStatistic(
            HttpServletRequest request
            ,@RequestParam(name = "regionId") String regionId
            ,@RequestParam(name = "startTime", required = false) String statTime
            ,@RequestParam(name = "endTime", required = false) String endTime
            ,@RequestParam(name = "regionId") String eventSource
    ){
        //查询所有的分类列表
        //将分类列表对应的值赋值到返回list中
        return null;
    }

    private List<ReachPatrolNumStatisticDTO> setType(List<ReachPatrolNumStatisticDTO> list, Integer type){
        for (int i=0; i<list.size(); i++){
            list.get(i).setType(type);
        }
        return list;
    }
    /**
     * 计算综合表未巡次数、达标率
     * @param list
     * @return
     */
    private List<ReachPatrolNumStatisticDTO> calc(List<ReachPatrolNumStatisticDTO> list, int grade){
        //如果是村级,直接返回原列表
        if(grade == 5){
            return list;
        }
        DecimalFormat df = new DecimalFormat("#.####");
        double d=0.0;
        //计算未巡次数、达标率
        for (int i=0; i<list.size(); i++){
            ReachPatrolNumStatisticDTO item = list.get(i);
            Integer needNum = 0;
            Integer hadNum = 0;
            if (grade <= 5) {
                //设置村级达标率
                hadNum = item.getVillageHasPatrolNum();
                needNum = item.getVillageNeedPatrolNum();
                item.setVillageNonePatrolNum(needNum-hadNum);
                d = hadNum/(double)needNum;
                item.setVillagePatrolRate(hadNum==0?0.0:Double.valueOf(df.format(d)));
            }
            if(grade<=4){
                //设置镇级达标率
                hadNum = item.getTownHasPatrolNum();
                needNum = item.getTownNeedPatrolNum();
                item.setTownNonePatrolNum(needNum-hadNum);
                d = hadNum/(double)needNum;
                item.setTownPatrolRate(hadNum==0?0.0:Double.valueOf(df.format(d)));
            }
            if (grade<=3){
                //设置区级达标率
                needNum = item.getCountyNeedPatrolNum();
                hadNum = item.getCountyHasPatrolNum();
                item.setCountyNonePatrolNum(needNum - hadNum);
                d = hadNum / (double) needNum;
                item.setCountyPatrolRate(hadNum == 0 ? 0.0 : Double.valueOf(df.format(d)));
            }
        }

        return list;
    }
    /**
     * @param sourceList 源列表
     * @param targetList 目的列表
     * @param type 3:填充区级河长应巡次数；4：填充镇级河长应巡次数；5：填充村级河长应巡次数
     */
    private List<ReachPatrolNumStatisticDTO> assignListField(List<ReachPatrolNumStatisticDTO> sourceList, List<ReachPatrolNumStatisticDTO> targetList, Integer type){
        int length=targetList.size();
        //如果等于0说明有巡河记录，等于1说明没有巡河记录
        int flag=0;
        if (sourceList.size()==0) {
            flag = 1;
        }
        //type 3-5 区-村 应巡次数
        if (type == 3){
            for (int i=0; i<length; i++){
                if (flag==1){
                    targetList.get(i).setCountyNeedPatrolNum(0);
                    break;
                }
                targetList.get(i).setCountyNeedPatrolNum(sourceList.get(i).getCountyNeedPatrolNum());
            }
        }
        if (type == 4){
            for (int i=0; i<length; i++){
                if (flag==1){
                    targetList.get(i).setTownNeedPatrolNum(0);
                    break;
                }
                targetList.get(i).setTownNeedPatrolNum(sourceList.get(i).getTownNeedPatrolNum());
            }
        }
        if (type == 5){
            for (int i=0; i<length; i++){
                if (flag==1){
                    targetList.get(i).setVillageNeedPatrolNum(0);
                    break;
                }
                targetList.get(i).setVillageNeedPatrolNum(sourceList.get(i).getVillageNeedPatrolNum());
            }
        }
        //type 6-8 区-村 已巡次数
        if (type == 6){
            for (int i=0; i<length; i++){
                if (flag==1){
                    targetList.get(i).setCountyHasPatrolNum(0);
                    break;
                }
                targetList.get(i).setCountyHasPatrolNum(sourceList.get(i).getCountyHasPatrolNum());
            }
        }
        if (type == 7){
            for (int i=0; i<length; i++){
                if (flag==1){
                    targetList.get(i).setTownHasPatrolNum(0);
                    break;
                }
                targetList.get(i).setTownHasPatrolNum(sourceList.get(i).getTownHasPatrolNum());
            }
        }
        if (type == 8){
            for (int i=0; i<length; i++){
                if (flag==1){
                    targetList.get(i).setVillageHasPatrolNum(0);
                    break;
                }
                targetList.get(i).setVillageHasPatrolNum(sourceList.get(i).getVillageHasPatrolNum());
            }
        }
        return targetList;
    }
}
