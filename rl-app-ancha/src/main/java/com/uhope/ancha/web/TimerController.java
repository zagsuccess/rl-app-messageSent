package com.uhope.ancha.web;

import com.uhope.ancha.domain.AnzhaBulletin;
import com.uhope.ancha.domain.AnzhaFeedback;
import com.uhope.ancha.service.AnzhaBulletinService;
import com.uhope.ancha.service.AnzhaFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author :shujihui
 * @Date : 2018/11/26
 * @Time : 18:37
 * Aim :
 */
@Component
public class TimerController {
    @Autowired
    private AnzhaBulletinService anzhaBulletinService;
    @Autowired
    private AnzhaFeedbackService anzhaFeedbackService;

    /**
     * 每天凌晨0点触发定时任务查询数据库
     */
    @Scheduled(cron = "0 01 0 * * ?")
    private void updateStatus(){
        //查询期限时间后一天的通报id
        List<AnzhaBulletin> list1=anzhaBulletinService.selectDeadlineUserh();
        System.out.println("期限后"+list1);
        if(list1!=null && list1.size()>0){
            for (AnzhaBulletin anzhaBulletin:list1) {
                String[] str=anzhaBulletin.getFeedbackareaid().split(",");
                int yicount=anzhaFeedbackService.selectcountByyinum(anzhaBulletin.getId());
                if(str.length==yicount){
                    //修改延期方案的状态为已反馈 2
                    AnzhaBulletin anzhaBulletin1=new AnzhaBulletin();
                    anzhaBulletin1.setStatus("2");
                    anzhaBulletin1.setId(anzhaBulletin.getId());
                    anzhaBulletinService.update(anzhaBulletin1);
                }

            }
        }


    }
}
