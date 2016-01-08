package com.gaohan.util;

import com.gaohan.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * 
 * @author HLH
 */
public class DateUtil {

    /**
     * 通用格式标识 yyyy-MM-dd HH:mm
     */
    public static final String yyyy_MM_dd_HH_mm          = "yyyy-MM-dd HH:mm";
    /**
     * 通用格式标识 yyyy-MM-dd HH:mm:ss
     */
    public static final String yyyy_MM_dd_HH_mm_ss       = "yyyy-MM-dd HH:mm:ss";
    /**
     * 通用格式标识 yyyy-MM-dd HH
     */
    public static final String yyyy_MM_dd_HH             = "yyyy-MM-dd HH";
    /**
     * 通用格式标识 yyyy/MM/dd
     */
    public static final String yyyy__MM__dd              = "yyyy/MM/dd";
    /**
     * 通用格式标识 yyyy/MM
     */
    public static final String yyyy__MM                  = "yyyy/MM";
    /**
     * 通用格式标识 yyyy-MM-dd
     */
    public static final String yyyy_MM_dd                = "yyyy-MM-dd";
    /**
     * 通用格式标识yyyyMMdd
     */
    public static final String yyyyMMdd                  = "yyyyMMdd";
    /**
     * 通用格式标识 HH:mm
     */
    public static final String HH_mm                     = "HH:mm";
    /**
     * 通用格式标识 HHmm
     */
    public static final String HHmm                      = "HHmm";
    /**
     * 通用格式标识 HHmmss
     */
    public static final String HHmmss                      = "HHmmss";
    /**
     * 通用格式标识yyyyMMddHHmmss
     */
    public static final String yyyyMMddHHmmss                  = "yyyyMMddHHmmss";
    /**
     * 通用格式标识ww
     */
    public static final String ww                        = "ww";
    /**
     * 通用格式标识w
     */
    public static final String w                         = "w";
    /**
     * 通用格式标识yyyy
     */
    public static final String yyyy                      = "yyyy";
    /**
     * 通用格式标识 yyyy年MM月
     */
    public static final String yyyy__MM__                = "yyyy年MM月";
    
    public static final String yyyy__MM__dd__            = "yyyy年MM月dd日";
    /**
     * 通用格式标识 yyyy年MM月dd日 HH:mm
     */
    public static final String yyyy_MM_ddHH_mm           = "yyyy年MM月dd月 HH:mm";
    /**
     * 通用格式标识 yyyy.MM.dd HH:mm
     */
    public static final String yyyy__MM_ddHH_mm          = "yyyy.MM.dd HH:mm";
    /**
     * 通用格式标识MM月dd月 HH:mm
     */
    public static final String MM_ddHH_mm                = "MM月dd月 HH:mm";
    /**
     * 通用格式标识yyyy年ww周
     */
    public static final String yyyy__ww__                = "yyyy年ww周";

    public static final int    SECONDS_TO_MILLS_EQUATION = 1000;

    /**
     * 把日期格式化字符串
     *
     * @param date 要格式化的日期
     * @param pattern 日期的格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = yyyy_MM_dd_HH_mm;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 把int类型日期格式化为字符串
     * 
     * @param timeInt
     * @param pattern
     * @return
     */
    public static String format(Integer timeInt, String pattern) {
        if (timeInt <= 0) {
            return StringUtils.EMPTY;
        }
        long time = timeInt * 1000l;
        Date date = new Date(time);
        return format(date, pattern);
    }

    /**
     * 把日期字符串转换成日期对象
     *
     * @param dateStr 日期字符串
     * @param pattern 格式串
     * @return 转换后的日期
     */
    public static Date parse(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new BizException("日期转换错误");
        }
    }

    /**
     * 去掉时分秒，毫秒的写法
     *
     * @param date 日期
     * @return 去掉时分秒，毫秒的方法
     */
    public static Date shortDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 计算出两个日期间所有的 [1..7]一星期中周几 的日期
     *
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @param days 星期几的集合
     * @return 日期从 周日开始算的 周日 1， 周一2， 周二3， 周三4， 周四5， 周五6， 周六7
     */
    public static List<Date> getComposeDates(Date startTime, Date endTime, List<Integer> days) {
        List<Date> list = new ArrayList<Date>();
        try {
            int i = 0;
            Date initDate = startTime;
            while (initDate.getTime() <= endTime.getTime()) {
                DateFormat df = new SimpleDateFormat(yyyy_MM_dd);
                Calendar c = Calendar.getInstance();
                c.setTime(df.parse(df.format(initDate)));
                Integer day = c.get(Calendar.DAY_OF_WEEK);
                if (days.contains(day)) {
                    list.add(initDate);
                }
                initDate = getRelateDay(startTime, ++i);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BizException("计算时间段内周几的日期 错误");
        }
        return list;
    }

    /**
     * 计算日期中相对前几天或者后几天的日期
     *
     * @param initialTime 当前日期
     * @param relateDay 往前几天 or 往后几天
     * @return
     */
    public static Date getRelateDay(Date initialTime, int relateDay) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(initialTime);
        cld.add(Calendar.DATE, relateDay);
        return cld.getTime();
    }

    /**
     * 计算日期中，相对前几个小时或者后几个小时
     *
     * @param initialTime
     * @param relateHour
     * @return
     */
    public static Date getRelateHour(Date initialTime, int relateHour) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(initialTime);
        cld.add(Calendar.HOUR, relateHour);
        return cld.getTime();
    }

    /**
     * 计算日期中，相对前几分钟或者后几分钟
     *
     * @param initialTime
     * @param relateMinute
     * @return
     */
    public static Date getRelateMinute(Date initialTime, int relateMinute) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(initialTime);
        cld.add(Calendar.MINUTE, relateMinute);
        return cld.getTime();
    }

    /**
     * 获得根据旧的日期获得新的日期
     *
     * @param date 旧的日期
     * @param field 变动的时间部分
     * @param amount 变动量
     * @return 新的时间
     */
    public static Date add(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 获得天数
     *
     * @param date 日期
     * @return 获得天数
     */
    public static int date(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        return c.get(Calendar.DATE);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获得月份
     *
     * @param date 日期
     * @return 获得天数
     */
    public static int month(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        return c.get(Calendar.MONTH) + 1;//月份从0开始
    }

    /**
     * 获得年份
     *
     * @param date 日期
     * @return 获得年份
     */
    public static int year(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得日期的最大天数
     *
     * @param date 日期
     * @return 最大的天数
     */
    public static int maxDate(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.DATE, -1);
        return c.get(Calendar.DATE);
    }

    /**
     * 将日期转换为同一天的最大值
     *
     * @param date
     * @return
     */
    public static Date getMaxDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 返回给定日期的月开始时间
     *
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setDayOfStart(calendar);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 返回给定日期的月结束时间
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        setDayOfEnd(calendar);
        return calendar.getTime();
    }

    public static void setDayOfStart(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void setDayOfEnd(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public static Date getRelativeDate(Date date, String relateStr) {
        int relate = 0;
        try {
            relate = Integer.parseInt(relateStr);
            return getRelateDay(date, relate);
        } catch (Exception e) {
            Date result = null;
            if (relateStr.endsWith("d")) {
                relate = Integer.parseInt(relateStr.replaceAll("d", ""));
                result = getRelateDay(date, relate);
            } else if (relateStr.endsWith("h")) {
                relate = Integer.parseInt(relateStr.replaceAll("h", ""));
                result = getRelateHour(date, relate);
            } else if (relateStr.endsWith("m")) {
                relate = Integer.parseInt(relateStr.replaceAll("m", ""));
                result = getRelateMinute(date, relate);
            }
            return result;
        }
    }

    /**
     * 时间转化对应格式的Date
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static Date getFormatDate(Date date, String pattern) {
        return parse(format(date, pattern), pattern);
    }

    /**
     * 将日期转换为同一分的最小值
     *
     * @param date
     * @return
     */
    public static Date getMinSecondDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    public static Date getDateTimeForTime(String timeStr) {
        Calendar c = Calendar.getInstance();
        String[] t = timeStr.split(":");
        if (t.length < 3)
            timeStr += ":00";
        Time time = Time.valueOf(timeStr);

        SimpleDateFormat format = new SimpleDateFormat("H");
        Integer hours = Integer.valueOf(format.format(time));

        format = new SimpleDateFormat("m");
        Integer minutes = Integer.valueOf(format.format(time));

        format = new SimpleDateFormat("s");
        Integer seconds = Integer.valueOf(format.format(time));

        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.SECOND, seconds);

        return c.getTime();
    }

    public static long getCurrentTimeSeconds() {
        int equation = 1000;
        return System.currentTimeMillis() / equation;
    }

    public static long getDateSeconds(Date date) {
        int equation = 1000;
        return date.getTime() / equation;
    }

    public static long getTimeDifference(long start, long end) {
        return end - start;
    }

    public static String getTimeDifferenceStr(long start, long end) {
        String totalProcessTime = null;
        long between = getTimeDifference(start, end);
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long minute = between % 3600 / 60;
        // long second = between % 60;
        if (day > 0) {
            totalProcessTime = day + "天" + hour + "小时" + minute + "分";
        } else if (hour > 0) {
            totalProcessTime = hour + "小时" + minute + "分";
        } else if (minute > 0) {
            totalProcessTime = minute + "分";
        } else {
            totalProcessTime = "-";
        }
        return totalProcessTime;
    }

    /**
     * Get the seconds of the calendar
     * 
     * @param calendar 日期
     * @return Long, return the seconds of the calendar
     */
    public static Long getCalendarTimeSeconds(Calendar calendar) {
        return calendar.getTimeInMillis() / SECONDS_TO_MILLS_EQUATION;
    }

    /**
     * @param second
     * @return Date
     */
    public static Date getDateFromSeconds(long second) {
        if (second == 0)
            return null;
        return new Date(second * SECONDS_TO_MILLS_EQUATION);

    }

    /**
     * 万色城特有的年代号</br>
     * 以<font color='red'>2014</font>为起点，用A、B、C...代之
     * @return
     */
    public static char getWsYearAlias(Date date) {
        int curYear = year(date);
        return (char) (curYear - 2014 + 'A');
    }
}
