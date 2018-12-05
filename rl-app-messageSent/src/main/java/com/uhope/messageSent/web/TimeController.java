package com.uhope.messageSent.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述:
 * 定时器大作战:在入口类中添加注解@EnableScheduling
 *             在有需要定时器的方法(无参方法)上加注解@Scheduled(cron = "5,10 * * * * *")
 *             在有定时器的类上加 @Component
 * @author a4994
 * @create 2018-11-27 10:57
 */
@Component
public class TimeController {
    @Scheduled(cron = "5 08 10 28 11 *")
    private void test(){
        System.out.println(new Date()+"没错，我是定时器");
    }

    /*@Scheduled(cron = "1-10 * * * * *")
    private void test1(){
        System.out.println(new Date()+"没错，我是定时器111111");
    }

    @Scheduled(cron = "11-20 * * * * *")
    private void test2(){
        System.out.println(new Date()+"没错，我是定时器222222");
    }*/
}


/**(cron = "5,10 * * * * *")Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，没一个域代表一个含义
 * 格式一：Seconds Minutes Hours DayofWeek Month DayofWeek Year
 * 格式二：Seconds Minutes Hours DayofWeek Month DayofWeek
 * ,:表示枚举，eg:5,20   在5秒和20秒各触发一次
 *
 * -：表示范围，eg:1-59  从1秒到59秒每秒触发一次
 *
 * *:表示任意值，eg:*    每秒触发一次
 *
 * /:表示何时开始然后每隔多久执行一次，eg：5/20   即5秒触发，25秒触发
 *
 * L:表示最后,只能出现在DayofWeek和DayofMonth域，如果在DayofWeek域使用5L，意味着在最后一个星期四触发
 *
 * W:表示有效工作日（周一至周五），只能出现在DayofMonth域，系统将在离指定日期的最近的有效工作日触发事件。eg:
 *   在DayofMonth使用5W，如果5日是星期六，则将在最近的工作日：星期五，即4日触发。如果5日是星期天，则在6日（周一）
 *   触发；如果5日在星期一到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨月份
 *
 * LW：这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五
 *
 * #：用于确定每个月第几个星期几，只能出现在DayofMonth域。eg：4#2，表示某月的第二个星期三
 *
 * ?：只能用在DayofMonth和DayofWeek两个域。它也匹配域的任意值，但实际不会。因为DayofMonth和DayofWeek会互相影响。
 *    eg：想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法：13 13 15 20 * ? 其中最后一位只能用?，
 *        而不能用*，如果使用*表示不管星期几都会触发，实际上并不是这样。
 *
 */