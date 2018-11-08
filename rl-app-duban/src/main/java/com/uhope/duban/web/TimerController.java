package com.uhope.duban.web;

import com.uhope.duban.domain.DubanSupervision;
import com.uhope.duban.dto.DeadlineDTO;
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

    /**
     * 每天上午8点触发定时任务查询数据库
     */
    @Scheduled(cron = "0 47 9 * * ?")
    private void deadlineUser(){
        //查询期限时间前一天的用户id
        List<String> list= dubanSupervisionService.selectDeadlineUser();
        System.out.println("期限前"+list);
        //查询期限时间后一天的用户id
        List<DeadlineDTO> list1=dubanSupervisionService.selectDeadlineUserh();
        System.out.println("期限后"+list1);
        //修改延期方案的状态为未按期 3
        DubanSupervision dubanSupervision=new DubanSupervision();
        dubanSupervision.setStatus("3");
        for (DeadlineDTO deadlineDTO:list1) {
             dubanSupervision.setId(deadlineDTO.getId());
            dubanSupervisionService.update(dubanSupervision);
        }
    }
}
