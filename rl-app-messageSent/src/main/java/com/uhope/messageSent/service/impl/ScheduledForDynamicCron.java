package com.uhope.messageSent.service.impl;

import com.uhope.messageSent.domain.MsWeekDynamic;
import com.uhope.messageSent.service.MsWeekDynamicService;
import com.uhope.messageSent.utils.CommonUtil;
import com.uhope.uip.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 * testTime
 *
 * @author a4994
 * @create 2018-12-07 14:31
 */
@Service
public class ScheduledForDynamicCron /*implements SchedulingConfigurer*/ {


    /*private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final String DEFAULT_CRON = "0/5 * * * * ?";
    private String cron = DEFAULT_CRON;
    private String sentTimeStart;
    private String deadline;

    @Autowired
    private MsWeekDynamicService msWeekDynamicService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            // 定时任务的业务逻辑
            System.out.println("动态修改定时任务cron参数，当前时间：" + dateFormat.format(new Date()));
            MsWeekDynamic msWeekDynamic=new MsWeekDynamic();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            msWeekDynamic.setInitiator("天津市河长办测试用户1");

            msWeekDynamic.setTitle("");
            String sent=null;
            for (String region:msWeekDynamicService.selectArea()){
                sent=sent+region;
            }
            msWeekDynamic.setSentRegion(sent);
            msWeekDynamic.setSentTimeStart(simpleDateFormat.format(new Date()));
            msWeekDynamic.setDeadline(deadline);
            msWeekDynamicService.insert(msWeekDynamic);
        }, (triggerContext) -> {
            // 定时任务触发，可修改定时任务的执行周期
            CronTrigger trigger = new CronTrigger(cron);
            Date nextExecDate = trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        });
    }
    public void setCron(String cron) {
        System.out.println("当前cron="+this.cron+"->将被改变为："+cron);
        this.cron = cron;
    }
    public void setSentTimeStart(String sentTimeStart) {
        System.out.println("sentTimeStart="+this.sentTimeStart+"->将被改变为："+sentTimeStart);
        this.sentTimeStart = sentTimeStart;
    }
    public void setDeadline(String deadline) {
        System.out.println("deadline="+this.deadline+"->将被改变为："+deadline);
        this.deadline = deadline;
    }*/
}
