package com.dingmouren.lib.util;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.dingmouren.lib.util.ConstantUtils.DAY;
import static com.dingmouren.lib.util.ConstantUtils.HOUR;
import static com.dingmouren.lib.util.ConstantUtils.MIN;
import static com.dingmouren.lib.util.ConstantUtils.MSEC;
import static com.dingmouren.lib.util.ConstantUtils.SEC;

/**
 * Created by dingmouren on 2018/3/19.
 * emial: naildingmouren@gmail.com
 * 日期工具类
 *                          HH:mm    15:44
 *                         h:mm a    3:44 下午
 *                        HH:mm z    15:44 CST
 *                        HH:mm Z    15:44 +0800
 *                     HH:mm zzzz    15:44 中国标准时间
 *                       HH:mm:ss    15:44:40
 *                     yyyy-MM-dd    2016-08-12
 *               yyyy-MM-dd HH:mm    2016-08-12 15:44
 *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
 *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
 *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
 *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
 *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
 *                         K:mm a    3:44 下午
 *               EEE, MMM d, ''yy    星期五, 八月 12, '16
 *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
 *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
 *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
 *                  yyMMddHHmmssZ    160812154440+0800
 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
 * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
 */

public class DateUtils {

    /*日期和时间*/
    private static final SimpleDateFormat DATE_FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /*日期*/
    private static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    /*时间*/
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
    /*默认格式*/
    private static final SimpleDateFormat DEFAULT_FORMAT = DATE_FORMAT_DATE_TIME;

    /*私有构造函数*/
    private DateUtils(){
        throw new UnsupportedOperationException("DateUtils不支持此操作");
    }

    /**
     * 将时间毫秒值转换成String类型日期+时间的格式：2018-01-01 12:00:00
     * @param milliseconds 时间毫秒值
     * @return
     */
    public static String millisecondsToDateTime(long milliseconds){
        return DATE_FORMAT_DATE_TIME.format(new Date(milliseconds));
    }

    /**
     * 将时间毫秒值转换成String类型日期格式：2018-01-01
     * @param milliseconds 时间毫秒值
     * @return
     */
    public static String millisecondsToDate(long milliseconds){
        return DATE_FORMAT_DATE.format(new Date(milliseconds));
    }

    /**
     * 将时间毫秒值转成String类型时间格式：12:00:00
     * @param milliseconds
     * @return
     */
    public static String millisecondsToTime(long milliseconds){
        return DATE_FORMAT_TIME.format(new Date(milliseconds));
    }

    /**
     * 将时间毫秒值转换成自定义的格式形式String类型日期，需要传入自定义格式的SimpleDateFormat对象
     * @param milliseconds
     * @param format 自定义格式
     * @return
     */
    public static String millisecondsToString(long milliseconds,SimpleDateFormat format){
        return format.format(new Date(milliseconds));
    }

    /**
     * 将默认格式的String类型日期转换成毫秒值  默认格式：yyyy-MM-dd HH:mm:ss
     * @param strDate
     * @return
     */
    public static long stringToMilliseconds(String strDate){
        return stringToMilliseconds(strDate,DEFAULT_FORMAT);
    }

    /**
     * 将特定格式的String类型日期转换成毫秒值
     * @param strDate
     * @param format
     * @return
     */
    public static long stringToMilliseconds(String strDate,SimpleDateFormat format){
        try {
            return format.parse(strDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将String类型日期转换成指定格式的String类型日期 2018-12-12 6:00:00 -> 2018-12-12
     * String result = DateUtils.formatStringDate(dateString,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),new SimpleDateFormat("yyyy-MM-dd"));
     * @param sourceStrDate 需要格式化的时间字符串
     * @param sourceFormat 需要格式化的时间字符串的格式
     * @param targetFormat 指定格式的时间字符串的格式
     * @return
     */
    public static String formatStringDate(String sourceStrDate,SimpleDateFormat sourceFormat,SimpleDateFormat targetFormat){
        String targetStrDate = null;
        try {
            Date date = sourceFormat.parse(sourceStrDate);
            targetStrDate = targetFormat.format(date);
            return targetStrDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "解析错误";
        }

    }

    /**
     * 将String类型日期转换成Date类型，默认格式
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate){
        Date date = null;
        try {
            date = DEFAULT_FORMAT.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将String类型日期转换成Date类型，需要传入这个时间字符串的格式
     * @param strDate
     * @param format
     * @return
     */
    public static Date stringToDate(String strDate,SimpleDateFormat format){
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将时间毫秒值转换成Date类型
     * @param milliseconds
     * @return
     */
    public static Date millisecondsToDateObject(long milliseconds){
        return new Date(milliseconds);

    }

    /**
     * 将Date类型转换成自定义格式的时间字符串
     * @param date
     * @param format 自定义格式的SimpleFormat
     * @return
     */
    public static String dateToString(Date date,SimpleDateFormat format){
        return format.format(date);
    }

    /**
     * 将Date类型转换成时间毫秒值
     * @param date
     * @return
     */
    public static long dateToMilliseconds(Date date){
        return date.getTime();
    }

    /**
     * 将时间毫秒值进行单位格式化，使用的是枚举
     * @param milliseconds
     * @param unit
     * @return
     */
    public static long millisecondsToUnit(long milliseconds, TimeUnit unit){
        switch (unit) {
            case MILLISECONDS:
                return milliseconds / MSEC;
            case SECONDS:
                return milliseconds / SEC;
            case MINUTES:
                return milliseconds / MIN;
            case HOURS:
                return milliseconds / HOUR;
            case DAYS:
                return milliseconds / DAY;
        }
        return -1;
    }

    /**
     * 获取两个String类型日期的时间差
     * 默认的时间格式
     * @param strDate1
     * @param strDate2
     * @param unit
     * @return
     */
    public static long getIntervalTime(String strDate1, String strDate2, TimeUnit unit){
        return getIntervalTime(strDate1,strDate2,unit,DEFAULT_FORMAT);
    }

    /**
     * 获取两个String类型日期的时间差
     * @param strDate1
     * @param strDate2
     * @param unit 时间单位
     * @param format 特定格式
     * @return
     */
    public static long getIntervalTime(String strDate1,String strDate2,TimeUnit unit,SimpleDateFormat format){
        return millisecondsToUnit(Math.abs(stringToMilliseconds(strDate1,format) - stringToMilliseconds(strDate2,format)),unit);
    }

    /**
     * 获取两个Date类型日期的时间差
     * @param date1
     * @param date2
     * @param unit
     * @return
     */
    public static long getIntervalTime(Date date1,Date date2,TimeUnit unit){
        return millisecondsToUnit(Math.abs(dateToMilliseconds(date1) - dateToMilliseconds(date2)),unit);
    }

    /**
     * 获取当前时间毫秒值
     * @return
     */
    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间String类型，默认格式
     * @return
     */
    public static String getCurrentTimeString(){
        return millisecondsToDateTime(getCurrentTimeMillis());
    }

    /**
     * 获取当前时间String类型，自定义格式
     * @param format
     * @return
     */
    public static String getCurrentTimeString(SimpleDateFormat format){
        return millisecondsToString(getCurrentTimeMillis(),format);
    }

    /**
     * 获取当前时间Date类型
     * @return
     */
    public static Date getCurrentTimeDate(){
        return new Date();
    }

    /**
     * 判断闰年
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0;
    }

    /**
     * 从String类型日期中获取星期
     * 格式为yyyy-MM-dd HH:mm:ss
     *
     * @param time 时间字符串
     * @return 星期
     */
    public static String getWeek(String time) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(stringToDate(time));
    }

    /**
     * 从String类型日期中获取星期
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 星期
     */
    public static String getWeek(String time, SimpleDateFormat format) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(stringToDate(time, format));
    }

    /**
     * 从Date类型日期中获取星期
     *
     * @param date Date类型时间
     * @return 星期
     */
    public static String getWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param strDate 时间字符串
     * @return 1...5
     */
    public static int getWeekOfMonth(String strDate) {
        Date date = stringToDate(strDate);
        return getWeekOfMonth(date);
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     *
     * @param strDate   时间字符串
     * @param format 时间格式
     * @return 1...5
     */
    public static int getWeekOfMonth(String strDate, SimpleDateFormat format) {
        Date date = stringToDate(strDate, format);
        return getWeekOfMonth(date);
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     *
     * @param date Date类型时间
     * @return 1...5
     */
    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }




}
