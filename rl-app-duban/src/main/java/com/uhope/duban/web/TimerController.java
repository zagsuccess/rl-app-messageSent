package com.uhope.duban.web;

import com.uhope.duban.domain.ScDubanFeedback;
import com.uhope.duban.domain.ScDubanSupervision;
import com.uhope.duban.dto.DeadlineDTO;
import com.uhope.duban.service.DubanFeedbackService;
import com.uhope.duban.service.DubanSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/11/7
 * @Time : 9:21
 * Aim :定时操作数据库
 */

@Component
public class TimerController {
    @Autowired
    private DubanSupervisionService dubanSupervisionService;
    @Autowired
    private DubanFeedbackService dubanFeedbackService;

    /**
     * 每天上午8点触发定时任务查询数据库
     */
    @Scheduled(cron = "0 0 8 * * ?")
    private void deadlineUser(){
        //查询期限时间前一天的用户id
        List<String> list= dubanSupervisionService.selectDeadlineUser();
        System.out.println("期限前"+list);
        //查询期限时间后一天的用户id
        List<ScDubanSupervision> list1=dubanSupervisionService.selectDeadlineUserh();
        System.out.println("期限后"+list1);

    }

    /**
     * 每天凌晨0点触发定时任务查询数据库
     */
    @Scheduled(cron = "0 01 0 * * ?")
    private void updateStatus(){
        //查询期限时间后一天的督办id
        List<ScDubanSupervision> list1=dubanSupervisionService.selectDeadlineUserh();
        System.out.println("期限后"+list1);
        if(list1!=null && list1.size()>0){
            for (ScDubanSupervision scDubanSupervision:list1) {
                //查询出期限时间之前的反馈信息
                List<ScDubanFeedback> scDubanFeedbacks=dubanFeedbackService.selectFeedbackBydubanid(scDubanSupervision.getId(),scDubanSupervision.getDeadlinedate());
                System.out.println("查询出期限时间之前的反馈信息"+scDubanFeedbacks);
                for (ScDubanFeedback scDubanFeedback:scDubanFeedbacks) {
                    if("否".equals(scDubanFeedback.getWhether())){
                        //修改延期方案的状态为未按期 3
                        ScDubanSupervision dubanSupervision=new ScDubanSupervision();
                        dubanSupervision.setStatus("3");
                        dubanSupervision.setId(scDubanFeedback.getSupervisionid());
                        dubanSupervisionService.update(dubanSupervision);
                    }else {
                        ScDubanSupervision dubanSupervision=new ScDubanSupervision();
                        dubanSupervision.setStatus("2");
                        dubanSupervision.setId(scDubanFeedback.getSupervisionid());
                        dubanSupervisionService.update(dubanSupervision);
                    }
                }
            }
        }


    }
}
