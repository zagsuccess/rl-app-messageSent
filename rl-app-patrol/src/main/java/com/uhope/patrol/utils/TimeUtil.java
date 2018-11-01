package com.uhope.patrol.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: zhoujiawen
 * @date:2018/8/17 14:35
 * @description:
 */
public class TimeUtil {


    public static Date obtainEndTime(int day, Date date){
        return obtainEndTime(day, 0, 0, date);
    }

    public static Date obtainEndTime(int day,int hour,Date date){
        return obtainEndTime(day, hour, 0, date);
    }

    public static Date obtainEndTime(int day,int hour,int seconds,Date date){
        Date time = null;
        if(date == null) time = new Date();
        else time = date;

        if(day!=0) time = getDate(time, Calendar.DAY_OF_MONTH, day);
        if(hour!=0) time = getDate(time, Calendar.HOUR_OF_DAY, hour);
        if(seconds!=0) time = getDate(time, Calendar.SECOND, seconds);
        return time;
    }

    public static Date getDate(Date date, int fileds, int  interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(fileds, interval);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * 根据type获取起始时间
     * @param type  需要的时间类型
     */
    public static Date getStartTime(int type){
        if(type == 1) {
            return getNowYearStart();
        } else if(type == 2) {
            return getNearMonth(0);
        } else if(type == 3) {
            return getNearMonth(-1);
        } else if(type == 4) {
            return getThisWeekDay(0,2);
        } else if(type == 5) {
            return getThisWeekDay(-1,2);
        } else if(type == 6) {
            return getNearDay(0,0,0,0,0);
        } else if(type == 7) {
            return getNearDay(-1,0,0,0,0);
        } else {
            return new Date();
        }
    }

    /**
     * 根据type获取截止时间
     * @param type  需要的时间类型
     */
    public static Date getEndTime(int type){
        if(type ==1) return getNearEndMonth(1);
        else if(type ==2) return getNearEndMonth(0);
        else if(type ==3) return getNearEndMonth(-1);
        else if(type ==4) return getThisWeekDay(1,1);
        else if(type ==5) return getThisWeekDay(0,1);
        else if(type ==6) return getNearDay(0,23,59,59,0);
        else if(type ==7) return getNearDay(-1,23,59,59,0);
        else return new Date();
    }


    /**
     * 获取 当天 前后day天 的 hour时 minute分 second秒 millisecond毫秒 的时间
     * @param day           当天需要加减的天数
     */
    public static Date getNearDay(int day, int hour, int minute, int second, int millisecond){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return DateDayInit(calendar,hour,minute,second,millisecond);
    }

    /**
     * 获取 本周  前后week个周 的第day天时间
     * @param day       本周第几天（按照国外每周第一天为周日时间计算）
     * @param week      本周需要加减的周的周数
     */
    public static Date getThisWeekDay(int week, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, day);
        return DateDayInit(calendar,0,0,0,0);
    }

    /**
     * 获取 当前月份 前后Month个月 的月初时间
     * @param Month     当前月份需要加减的月份数
     */
    public static Date getNearMonth(int Month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,Month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateDayInit(calendar,0,0,0,0);
    }

    /**
     * 获得当前月日前后 Month个月的当前日。
     * @Author yanjing
     * @param Month
     * @return
     */
    public static Date getLastMothToday(int Month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,Month);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
        return DateDayInit(calendar,0,0,0,0);
    }

    /**
     * 获取 当前月份 前后Month个月 的月末时间
     * @param Month     当前月份需要加减的月份数
     */
    public static Date getNearEndMonth(int Month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,Month+1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return DateDayInit(calendar,23,59,59,0);
    }

    /**
     * 获取当年起始时间
     */
    public static Date getNowYearStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateDayInit(calendar,0,0,0,0);
    }

    /**
     * 将calender时间设为当天 hour时 minute分 second秒 millisecond毫秒
     */
    private static Date DateDayInit(Calendar calendar,int hour,int minute,int second,int millisecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 获取 当前月份 前后Month个月 的周数
     * @param month     当前月份需要加减的月份数
     */
    public static Integer getNearMonthWeeks(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Integer monthDays = getNearMonthDays(month);
        Integer dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        return (monthDays + dayOfWeek - 7) / 7 + 1;
    }

    /**
     * 获取 当前月份 前后Month个月 的月天数
     * @param month     当前月份需要加减的月份数
     */
    public static Integer getNearMonthDays(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }


    /**
     * 返回当前日期前后多少月的年月信息
     * @author yanjing
     * @param monthChange
     * @return
     */
    public static Integer[] nearNowTime(Integer monthChange){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH,monthChange);
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH)+1;
        Integer[] dateInfo = new Integer[2];
        dateInfo[0]=year;
        dateInfo[1]=month;
        return dateInfo;
    }

    /**
     * 获取目标年月的开始时间
     * @param year      年份
     * @param month     月份
     */
    public static Date getTargetStart(Integer year, Integer month){
        Calendar calendar = Calendar.getInstance();
        if(year != null) { calendar.set(Calendar.YEAR, year); }
        if(month != null) { calendar.set(Calendar.MONTH, month); }
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return DateDayInit(calendar,0,0,0,0);
    }

    /**
     * 获取目标年月的截止时间
     * @param year      年份
     * @param month     月份
     */
    public static Date getTargetEnd(Integer year, Integer month){
        Calendar calendar = Calendar.getInstance();
        if(year != null) { calendar.set(Calendar.YEAR, year); }
        if(month != null) { calendar.set(Calendar.MONTH, month); }
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
        return DateDayInit(calendar,23,59,59,0);
    }
}
