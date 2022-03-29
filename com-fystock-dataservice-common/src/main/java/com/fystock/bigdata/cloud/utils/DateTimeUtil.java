package com.fystock.bigdata.cloud.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 *
 * @author He.Yong
 * @since 2021-06-01 11:29:01
 */
public class DateTimeUtil {

    /**
     * 获取前一天日期
     *
     * @return
     */
    public static String getYesterdayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //前一天
        c.add(Calendar.DATE, -1);
        Date yesterday = c.getTime();
        return simpleDateFormat.format(yesterday);
    }

    /**
     * 获取上一周的今天
     *
     * @return
     */
    public static String getLastWeekDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        //将date - 7
        c.add(Calendar.DATE, -7);
        Date lastWeekDay = c.getTime();
        return format.format(lastWeekDay);
    }

    /**
     * 获取前一个月的日期
     *
     * @return
     */
    public static String getLastMonthDate() {
        Date date = new Date();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
        if (month == 0) {
            year -= 1;
            month = 12;
        } else if (day > 28) {
            if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    day = 29;
                } else
                    day = 28;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                day = 30;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10) {
            m = "0" + month;
        } else {
            m = month + "";
        }
        if (day < 10) {
            d = "0" + day;
        } else {
            d = day + "";
        }
        return y + "-" + m + "-" + d;
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author He.Yong
     * @since 2021-05-20 15:34:09
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * 判断当前时间是否在startTime之后,注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @return
     * @author He.Yong
     * @since 2021-05-20 15:34:09
     */
    public static boolean isAfterDate(Date nowTime, Date startTime) {
        if (nowTime.getTime() == startTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar start = Calendar.getInstance();
        start.setTime(startTime);

        return date.after(start);
    }

    /**
     * 判断当前时间是否在startTime之前,注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @return
     * @author He.Yong
     * @since 2021-05-20 15:34:09
     */
    public static boolean isBeforeDate(Date nowTime, Date startTime) {
        if (nowTime.getTime() == startTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar start = Calendar.getInstance();
        start.setTime(startTime);

        return date.before(start);
    }

    /**
     * 判断时间戳是否是今天
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static boolean isToday(long time) throws ParseException {
        boolean isToday = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            Date old = sdf.parse(sdf.format(date));
            Date now = sdf.parse(sdf.format(new Date()));
            long oldTime = old.getTime();
            long nowTime = now.getTime();

            long day = (nowTime - oldTime) / (24 * 60 * 60 * 1000);

            isToday = day < 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isToday;
    }

    /**
     * 判断时间戳是否是今天
     *
     * @param time
     * @return
     */
    public boolean isYesterday(long time) {
        boolean isYesterday = false;
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(sdf.format(new Date()));
            if (time < date.getTime() && time > (date.getTime() - 24 * 60 * 60 * 1000)) {
                isYesterday = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isYesterday;
    }

    /**
     * 判断日期是否是今天
     *
     * @param inputJudgeDate
     * @return
     */
    public static boolean isToday(Date inputJudgeDate) {
        boolean flag = false;
        // 获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        // 定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try {
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取当前系统时间年月日yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateYYYYMMDD() {
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前系统时间年月日yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前系统时间年月日时分秒yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取前一天日期
     *
     * @return
     */
    public static String getYesterdayDateYYYYMMDD() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //前一天
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        return simpleDateFormat.format(start);
    }

    /**
     * 日期String类型转为 LocalDate类型
     *
     * @param date
     * @return
     */
    public static LocalDate getLocalDateByString(String date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, fmt);
    }
}
