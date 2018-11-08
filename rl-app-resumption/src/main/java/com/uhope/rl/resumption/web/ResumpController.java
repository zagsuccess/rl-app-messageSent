package com.uhope.rl.resumption.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.rl.resumption.dto.statistics.*;
import com.uhope.rl.resumption.service.ResumptionService;
import com.uhope.rl.resumption.utils.DateUtil;
import com.uhope.rl.resumption.utils.TimeUtil;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.TokenService;
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
@RequestMapping({"/resumption/v1", "/v1/resumption"})
public class ResumpController {

    private static final Logger log = LoggerFactory.getLogger(ResumpController.class);

    @Autowired
    private ResumptionService resumptionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 根据区域 、时间统计巡查次数 河长巡河统计分析-巡查达标率
     *
     * @param request
     * @param type
     * @param statTime
     * @param endTime
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/listReachPatrolNumStatistic")
    public Result<PageInfo<ReachPatrolNumStatisticDTO>> listReachPatrolNumStatistic(
            HttpServletRequest request
            , @RequestParam(name = "type", defaultValue = "2") Integer type
            , @RequestParam(name = "startTime", required = false) String statTime
            , @RequestParam(name = "endTime", required = false) String endTime
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ) {
        if (statTime == null && endTime == null) {
            Date startTimeDate = TimeUtil.getStartTime(type);
            Date endTimeDate = TimeUtil.getEndTime(type);

            statTime = DateUtil.getDate(startTimeDate);
            endTime = DateUtil.getDate(endTimeDate);
        }
        int intervalMonths = DateUtil.getIntervalDays(statTime, endTime) / 30;
        if (type == 2 || intervalMonths == 0) {
            intervalMonths = 1;
        }

        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }

        //存放返回数据
        List<ReachPatrolNumStatisticDTO> list = new ArrayList<ReachPatrolNumStatisticDTO>();
        //用来存放暂时数据
        List<ReachPatrolNumStatisticDTO> tempList = new ArrayList<ReachPatrolNumStatisticDTO>();

        //获取用户的regionId并解析其河长级别
        //regionid 对应的结构为：22233  省，市，区，镇，村
        //默认是村级
        Integer grade = 5;
        if (userDTO.getRegionId() % (1000) == 0) {
            //镇级
            grade = 4;
        }
        if (userDTO.getRegionId() % (1000 * 1000) == 0) {
            //区级
            grade = 3;
        }
        if (userDTO.getRegionId() % (1000 * 1000 * 100) == 0) {
            //市级
            grade = 2;
        }
        if (grade == 5) {
            //村级
            return ResponseMsgUtil.success();
        }
        if (grade == 4) {
            //镇级
            //查找村级河长应巡次数,相当于给list赋初值
            list = resumptionService.findReachNeedPatrolNumStatistic(type, intervalMonths, 5, pageNumber, pageSize, grade, userDTO.getRegionId());
            //查找村级河长已巡次数
            tempList = resumptionService.findReachHadPatrolNumStatistic(type, statTime, endTime, 5, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 8);
        }
        if (grade == 3) {
            //区级
            //查找村级河长应巡次数,相当于给list赋初值
            list = resumptionService.findReachNeedPatrolNumStatistic(type, intervalMonths, 5, pageNumber, pageSize, grade, userDTO.getRegionId());
            //查找村级河长已巡次数
            tempList = resumptionService.findReachHadPatrolNumStatistic(type, statTime, endTime, 5, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 8);
            //查找镇级河长应巡次数
            tempList = resumptionService.findReachNeedPatrolNumStatistic(type, intervalMonths, 4, pageNumber, pageSize, grade, userDTO.getRegionId());
            //将镇级河长应巡次数赋值给list
            list = assignListField(tempList, list, 4);
            tempList = resumptionService.findReachHadPatrolNumStatistic(type, statTime, endTime, 4, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 7);
        }
        if (grade == 2) {
            //查找村级河长应巡次数,相当于给list赋初值
            list = resumptionService.findReachNeedPatrolNumStatistic(type, intervalMonths, 5, pageNumber, pageSize, grade, userDTO.getRegionId());
            //查找村级河长已巡次数
            tempList = resumptionService.findReachHadPatrolNumStatistic(type, statTime, endTime, 5, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 8);
            //查找镇级河长应巡次数
            tempList = resumptionService.findReachNeedPatrolNumStatistic(type, intervalMonths, 4, pageNumber, pageSize, grade, userDTO.getRegionId());
            //将镇级河长应巡次数赋值给list
            list = assignListField(tempList, list, 4);
            tempList = resumptionService.findReachHadPatrolNumStatistic(type, statTime, endTime, 4, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 7);
            //查找区级河长应巡次数
            tempList = resumptionService.findReachNeedPatrolNumStatistic(type, intervalMonths, 3, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 3);
            tempList = resumptionService.findReachHadPatrolNumStatistic(type, statTime, endTime, 3, pageNumber, pageSize, grade, userDTO.getRegionId());
            list = assignListField(tempList, list, 6);
        }

        list = calc(list, grade);

        //设置type预留字段
        list = setType(list, type);

        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    /**
     * 查找区中各个等级河长的巡河情况
     *
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
            , @RequestParam(name = "regionId", required = true) String regionId
            , @RequestParam(name = "grade", required = true) Integer grade
            , @RequestParam(name = "type", required = false) Integer type
            , @RequestParam(name = "startTime", required = false) String statTime
            , @RequestParam(name = "endTime", required = false) String endTime
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber
            , @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize
    ) {
        PageHelper.startPage(pageNumber, pageSize);
        //没有按时间进行查询，设置默认时间
        if (statTime == null && endTime == null) {
            Date startTimeDate = TimeUtil.getStartTime(type);
            Date endTimeDate = TimeUtil.getEndTime(type);

            statTime = DateUtil.getDate(startTimeDate);
            endTime = DateUtil.getDate(endTimeDate);
        }
        int intervalMonths = DateUtil.getIntervalDays(statTime, endTime) / 30;
        if (type == 2 || intervalMonths == 0) {
            intervalMonths = 1;
        }

        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }

        Integer userGrade = 5;
        if (userDTO.getRegionId() % (1000) == 0) {
            //镇级
            userGrade = 4;
        }
        if (userDTO.getRegionId() % (1000 * 1000) == 0) {
            //区级
            userGrade = 3;
        }
        if (userDTO.getRegionId() % (1000 * 1000 * 100) == 0) {
            //市级
            userGrade = 2;
        }

        //查找统计数值
        List<ReachmanPatrolNumStatisticDTO> list = resumptionService.findPersonPatrolNum(intervalMonths, regionId, statTime, endTime, grade, userGrade);
        //计算达标率
        double d = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < list.size(); i++) {
            ReachmanPatrolNumStatisticDTO item = list.get(i);
            Integer hadReachNum = item.getHadReachNum();
            Integer needReachNum = item.getNeedReachNum();
            d = hadReachNum / (double) needReachNum;
            if (hadReachNum == 0 || needReachNum == 0) {
                d = 0.0;
            } else if (d >= 1.0) {
                d = 1.0 * 100;
            } else {
                d = d * 100;
            }
            item.setPatrolRate(Double.valueOf(df.format(d)));
        }

        PageInfo pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @GetMapping("/listProblemStatistic")
    public Result<List<ProblemStatisticDTO>> listProblemStatistic(
            HttpServletRequest request
            , @RequestParam(name = "startTime", required = false) String startTime
            , @RequestParam(name = "endTime", required = false) String endTime
            , @RequestParam(name = "eventSource", required = false) String eventSource
    ) {
        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户失败");
        }

        List<ProblemStatisticDTO> list = Lists.newArrayList();
        //这里因为120000000000对应的也为市河长办用户，所以将他作为市河长办处理而不是省（写死了，后期需要修改）
        if (userDTO.getRegionId() == 120000000000L) {
            list = resumptionService.findAllRegionProblemStatistic("120100000000");
        } else {
            list = resumptionService.findAllRegionProblemStatistic(userDTO.getRegionId().toString());
        }
        //获取用户的regionId并解析其河长级别
        //regionid 对应的结构为：22233  省，市，区，镇，村
        //默认是村级
        int grade = 0;
        if (userDTO.getRegionId() % (1000 * 1000 * 100) == 0) {
            //市级
            grade = 2;
        } else if (userDTO.getRegionId() % (1000 * 1000) == 0) {
            //区级
            grade = 3;
        } else if (userDTO.getRegionId() % (1000) == 0) {
            //镇级
            grade = 4;
        }
        //填充数据
        for (int i = 0; i < list.size(); i++) {
            String regionId = list.get(i).getRegionId();
            list.get(i).setList(resumptionService.findRegionTypeNumStatistic(regionId, grade, startTime, endTime));
        }

        return ResponseMsgUtil.success(list);
    }

    /**
     * 找到本周问题较多河道，取前10条
     *
     * @return 前10个问题较多河道信息
     */
    @GetMapping("/listWithMoreProblemReach")
    public Result<List<RiverProblemStatistic>> listWithMoreProblemReach() {
        return ResponseMsgUtil.success(resumptionService.findWithMoreProblemReach());
    }

    /**
     * 获取当前登陆用户的等级
     *
     * @param request
     * @return
     */
    @GetMapping("/findCurrentUserGrade")
    public Result<Integer> findCurrentUserGrade(HttpServletRequest request) {
        //获取当前用户信息
        UserDTO userDTO = getFeigionServiceResultData(tokenService.getUserDTOByRequest(request));
        if (userDTO == null) {
            return ResponseMsgUtil.failure("获取用户等级失败");
        }

        //默认是村级
        int grade = 5;
        if (userDTO.getRegionId() % (1000) == 0) {
            //镇级
            grade = 4;
        }
        if (userDTO.getRegionId() % (1000 * 1000) == 0) {
            //区级
            grade = 3;
        }
        if (userDTO.getRegionId() % (1000 * 1000 * 100) == 0) {
            //市级
            grade = 2;
        }
        if (userDTO.getRegionId() % (1000 * 1000 * 100 * 100) == 0) {
            //省级
            grade = 1;
        }
        return ResponseMsgUtil.success(grade);
    }

    private List<ReachPatrolNumStatisticDTO> setType(List<ReachPatrolNumStatisticDTO> list, Integer type) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setType(type);
        }
        return list;
    }

    /**
     * 计算综合表未巡次数、达标率
     *
     * @param list
     * @return
     */
    private List<ReachPatrolNumStatisticDTO> calc(List<ReachPatrolNumStatisticDTO> list, int grade) {
        //如果是村级,直接返回原列表
        if (grade == 5) {
            return list;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        double d = 0.0;
        //计算未巡次数、达标率
        for (int i = 0; i < list.size(); i++) {
            ReachPatrolNumStatisticDTO item = list.get(i);
            Integer needNum = 0;
            Integer hadNum = 0;
            if (grade < 5) {
                //设置村级达标率
                hadNum = item.getVillageHasPatrolNum();
                needNum = item.getVillageNeedPatrolNum();
                item.setVillageNonePatrolNum((needNum - hadNum) < 0 ? 0 : (needNum - hadNum));
                d = hadNum / (double) needNum;
                if (hadNum == 0 || needNum == 0) {
                    d = 0.0;
                } else if (d >= 1.0) {
                    d = 1.0 * 100;
                } else {
                    d = d * 100;
                }
                item.setVillagePatrolRate(Double.valueOf(df.format(d)));
            }
            if (grade < 4) {
                //设置镇级达标率
                hadNum = item.getTownHasPatrolNum();
                needNum = item.getTownNeedPatrolNum();
                item.setTownNonePatrolNum((needNum - hadNum) < 0 ? 0 : (needNum - hadNum));
                d = hadNum / (double) needNum;
                if (hadNum == 0 || needNum == 0) {
                    d = 0.0;
                } else if (d >= 1.0) {
                    d = 1.0 * 100;
                } else {
                    d = d * 100;
                }
                item.setTownPatrolRate(Double.valueOf(df.format(d)));
            }
            if (grade < 3) {
                //设置区级达标率
                needNum = item.getCountyNeedPatrolNum();
                hadNum = item.getCountyHasPatrolNum();
                item.setCountyNonePatrolNum((needNum - hadNum) < 0 ? 0 : (needNum - hadNum));
                d = hadNum / (double) needNum;
                if (hadNum == 0 || needNum == 0) {
                    d = 0.0;
                } else if (d >= 1.0) {
                    d = 1.0 * 100;
                } else {
                    d = d * 100;
                }
                item.setCountyPatrolRate(Double.valueOf(df.format(d)));
            }
        }

        return list;
    }

    /**
     * @param sourceList 源列表
     * @param targetList 目的列表
     * @param type       3:填充区级河长应巡次数；4：填充镇级河长应巡次数；5：填充村级河长应巡次数
     */
    private List<ReachPatrolNumStatisticDTO> assignListField(List<ReachPatrolNumStatisticDTO> sourceList, List<ReachPatrolNumStatisticDTO> targetList, Integer type) {
        int length = targetList.size();
        //如果等于0说明有巡河记录，等于1说明没有巡河记录
        int flag = 0;
        if (sourceList.size() == 0) {
            flag = 1;
        }
        //type 3-5 区-村 应巡次数
        if (type == 3) {
            for (int i = 0; i < length; i++) {
                if (flag == 1) {
                    targetList.get(i).setCountyNeedPatrolNum(0);
                    break;
                }
                targetList.get(i).setCountyNeedPatrolNum(sourceList.get(i).getCountyNeedPatrolNum());
            }
        }
        if (type == 4) {
            for (int i = 0; i < length; i++) {
                if (flag == 1) {
                    targetList.get(i).setTownNeedPatrolNum(0);
                    break;
                }
                targetList.get(i).setTownNeedPatrolNum(sourceList.get(i).getTownNeedPatrolNum());
            }
        }
        if (type == 5) {
            for (int i = 0; i < length; i++) {
                if (flag == 1) {
                    targetList.get(i).setVillageNeedPatrolNum(0);
                    break;
                }
                targetList.get(i).setVillageNeedPatrolNum(sourceList.get(i).getVillageNeedPatrolNum());
            }
        }
        //type 6-8 区-村 已巡次数
        if (type == 6) {
            for (int i = 0; i < length; i++) {
                if (flag == 1) {
                    targetList.get(i).setCountyHasPatrolNum(0);
                    break;
                }
                targetList.get(i).setCountyHasPatrolNum(sourceList.get(i).getCountyHasPatrolNum());
            }
        }
        if (type == 7) {
            for (int i = 0; i < length; i++) {
                if (flag == 1) {
                    targetList.get(i).setTownHasPatrolNum(0);
                    break;
                }
                targetList.get(i).setTownHasPatrolNum(sourceList.get(i).getTownHasPatrolNum());
            }
        }
        if (type == 8) {
            for (int i = 0; i < length; i++) {
                if (flag == 1) {
                    targetList.get(i).setVillageHasPatrolNum(0);
                    break;
                }
                targetList.get(i).setVillageHasPatrolNum(sourceList.get(i).getVillageHasPatrolNum());
            }
        }
        return targetList;
    }
}
