package com.mysiteforme.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * @Description 时间工具类
 * @date  2019年3月17日下午4:14:29
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
public class DateUtil {


    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    // 格式：年－月－日 小时：分钟
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

    // 格式：年月日 小时分钟秒
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

    // 格式：年月日小时分钟秒
    public static final String FORMAT_FOUR = "yyyyMMddHHmmss";

    // 格式：月日小时分钟
    public static final String FORMAT_FIVE = "MM-dd HH:mm";

    // 格式：年－月－日
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

    // 格式：年月日
    public static final String EIGHT_STYLE_DATE_FORMAT = "yyyyMMdd";

    // 格式：月－日
    public static final String SHORT_DATE_FORMAT = "MM-dd";

    // 格式：小时：分钟：秒
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";

    // 格式：年-月
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";

    // 格式: 年/月
    public static final String MONTH_DATE_FORMAT_2 = "yyyy/MM";

    // 年的加减
    public static final int SUB_YEAR = Calendar.YEAR;

    // 月加减
    public static final int SUB_MONTH = Calendar.MONTH;

    // 天的加减
    public static final int SUB_DAY = Calendar.DATE;

    // 小时的加减
    public static final int SUB_HOUR = Calendar.HOUR;

    // 分钟的加减
    public static final int SUB_MINUTE = Calendar.MINUTE;

    // 秒的加减
    public static final int SUB_SECOND = Calendar.SECOND;

    // 格式：年月日小时分钟秒
    public static final String FORMAT_Five_New = "yyyyMMddHHmmssSS";

    static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    public static Date getNextYear(Date date, Integer year) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(date);
        curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + year);
        curr.setTime(curr.getTime());
        curr.set(Calendar.DATE, curr.get(Calendar.DATE) - 1);
        return curr.getTime();
    }
    
    @SuppressWarnings("unused")
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_ONE);

    public DateUtil() {
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */

    public static java.util.Date toDate(String dateStr) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    public static java.util.Date toDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     * 
     * @param date
     * @return
     */
    public static String formatDateTime(java.util.Date date) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    public static String formatDateTime(java.util.Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    /**
     * 获取当前时间的指定格式
     * 
     * @param format
     * @return
     */
    public static String getCurrDate(String format) {
        return formatDateTime(new Date(), format);
    }

    /**
     * 
     * @param dateStr
     * @param amount
     * @return
     */
    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = toDate(dateStr, FORMAT_ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return formatDateTime(calendar.getTime(), FORMAT_ONE);
    }

    /**
     * 两个日期相减
     * 
     * @param firstTime
     * @param secTime
     * @return 相减得到的秒数
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = toDate(firstTime, FORMAT_ONE).getTime();
        long second = toDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    /**
     * 两个日期相减
     * 
     * @param firstTime
     * @param secTime
     * @return 相减得到的秒数
     */
    public static long timeSub(Date firstTime, Date secTime) {
        long first = firstTime.getTime();
        long second = secTime.getTime();
        return (second - first) / 1000;
    }

    /**
     * 获得某月的天数
     * 
     * @param year
     *            int
     * @param month
     *            int
     * @return int
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8") || month.equals("10") || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }

    /**
     * 获取某年某月的天数
     * 
     * @param year
     *            int
     * @param month
     *            int 月份[1-12]
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期
     * 
     * @return int
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前月份
     * 
     * @return int
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     * 
     * @return int
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天
     * 
     * @param date
     *            Date
     * @return int
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的年
     * 
     * @param date
     *            Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的月份，1-12
     * 
     * @param date
     *            Date
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     * 
     * @param date1
     *            Date
     * @param date2
     *            Date
     * @return long
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 比较两个日期的年差
     * 
     * @param befor
     * @param after
     * @return
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = toDate(before, LONG_DATE_FORMAT);
        Date afterDay = toDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }

    /**
     * 比较指定日期与当前日期的差
     * 
     * @param befor
     * @param after
     * @return
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = toDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }

    /**
     * 比较指定日期与当前日期的差
     * 
     * @param before
     * @return
     * @author chenyz
     */
    public static long dayDiffCurr(String before) {
        Date currDate = DateUtil.toDate(currDay(), LONG_DATE_FORMAT);
        Date beforeDate = toDate(before, LONG_DATE_FORMAT);
        return (currDate.getTime() - beforeDate.getTime()) / 86400000;

    }

    /**
     * 获取每月的第一周
     * 
     * @param year
     * @param month
     * @return
     * @author chenyz
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取每月的最后一周
     * 
     * @param year
     * @param month
     * @return
     * @author chenyz
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     * 
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return formatDateTime(today.getTime(), FORMAT_ONE);
    }

    /**
     * 根据生日获取星座
     * 
     * @param birth
     *            YYYY-mm-dd
     * @return
     */
    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }
        if (!isDate(birth)) {
            return "";
        }
        int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1, birth.lastIndexOf("-")));
        int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
        String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
        int[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
        int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
        return s.substring(start, start + 2) + "座";
    }

    /**
     * 判断日期是否有效,包括闰年的情况
     * 
     * @param date
     *            YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    /**
     * 取得指定日期过 months 月后的日期 (当 months 为负数表示指定月之前);
     * 
     * @param date
     *            日期 为null时表示当天
     * @param month
     *            相加(相减)的月数
     */
    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前);
     * 
     * @param date
     *            日期 为null时表示当天
     * @param month
     *            相加(相减)的月数
     */
    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    /**
     * Description: 获取hour后的日期，hour为负数表示之前的时间
     * @Version1.0 2017年10月13日 上午11:25:53 by 代鹏（daipeng.456@gmail.com）创建
     * @param date
     * @param hour
     * @return
     */
    public static Date nextHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * Description:获取minute后的日期，minute为负数表示之前的时间
     * @Version1.0 2017年10月13日 上午11:35:18 by 代鹏（daipeng.456@gmail.com）创建
     * @param date
     * @param minute
     * @return
     */
    public static Date nextMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * Description:获取second后的日期，second为负数表示之前的时间
     * @Version1.0 2017年10月13日 上午11:37:24 by 代鹏（daipeng.456@gmail.com）创建
     * @param date
     * @param second
     * @return
     */
    public static Date nextSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * Description:获取milliSecond后的日期，milliSecond为负数表示之前的时间
     * @Version1.0 2017年10月13日 上午11:39:07 by 代鹏（daipeng.456@gmail.com）创建
     * @param date
     * @param milliSecond
     * @return
     */
    public static Date nextMilliSecond(Date date, int milliSecond) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MILLISECOND, milliSecond);
        return cal.getTime();
    }

    /**
     * 取得距离今天 day 日的日期
     * 
     * @param day
     * @param format
     * @return
     * @author chenyz
     */
    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 取得指定日期过 day 周后的日期 (当 day 为负数表示指定月之前)
     * 
     * @param date
     *            日期 为null时表示当天
     */
    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    /**
     * 获取当前的日期(yyyy-MM-dd) 
     */
    public static String currDay() {
        return DateUtil.formatDateTime(new Date(), DateUtil.LONG_DATE_FORMAT);
    }

    /**
     * 获取当前的日期(yyyy-MM-dd HH:mm:ss ) 
     */
    public static String currDays() {
        return DateUtil.formatDateTime(new Date(), DateUtil.FORMAT_ONE);
    }

    /**
     * 获取昨天的日期
     * 
     * @return
     */
    public static String befoDay() {
        return befoDay(DateUtil.LONG_DATE_FORMAT);
    }

    /**
     * 根据时间类型获取昨天的日期
     * 
     * @param format
     * @return
     * @author chenyz
     */
    public static String befoDay(String format) {
        return DateUtil.formatDateTime(DateUtil.nextDay(new Date(), -1), format);
    }

    /**
     * 根据时间类型获取上月的月份
     * 
     * @param format
     * @return
     * @author chenyz
     */
    public static String befoMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return DateUtil.formatDateTime(cal.getTime(), format);
    }

    /**
     * 获取明天的日期
     */
    public static String afterDay() {

        return DateUtil.formatDateTime(DateUtil.nextDay(new Date(), 1), DateUtil.LONG_DATE_FORMAT);
    }



   

    /** 针对yyyy-MM-dd HH:mm:ss格式,显示yyyymmdd */
    public static String getYmdDateCN(String datestr) {
        if (datestr == null)
            return "";
        if (datestr.length() < 10)
            return "";
        StringBuffer buf = new StringBuffer();
        buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7)).append(datestr.substring(8, 10));
        return buf.toString();
    }

    /**
     * 获取本月第一天
     * 
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获取某月第一天
     * 
     * @param format
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 获取本月第一秒
     * @param date
     * @return
     */
    public static String getFirstSecondOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获取某月第一秒
     * @param date null 为本月
     * @return
     */
    public static Date getFirstSecondOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取本月最后一天
     * 
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获取某月最后一天
     * 
     * @param format
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }

    public static Date getTodayStart() {
        return getStartOfDay(new Date());
    }

    public static Date getBeforeDate(Date mirror, int dayNum) {
        Calendar current = Calendar.getInstance();
        current.setTime(mirror);

        current.add(Calendar.HOUR, -24 * dayNum);
        return current.getTime();
    }

    /**
     * Returns a Date set to the first possible millisecond of the day, just
     * after midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null)
            day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * Returns a Date set to the last possible millisecond of the day, just
     * before midnight. If a null day is passed in, a new Date is created.
     * midnight (00m 00h 00s)
     */
    public static Date getEndOfDay(Date day) {
        return getEndOfDay(day, Calendar.getInstance());
    }

    public static Date getEndOfDay(Date day, Calendar cal) {
        if (day == null)
            day = new Date();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * 判断2个时间相差多少天<br>
     * <br>
     * 
     * @param pBeginTime
     *            请假开始时间<br>
     * @param pEndTime
     *            请假结束时间<br>
     * @return String 计算结果<br>
     * @throws Exception
     * @Exception 发生异常<br>
     */
    public static Long timeDiffForDay(Date pBeginTime, Date pEndTime) throws Exception {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long day = (endL - beginL) / 86400000;
        return day;
    }

    /**
     * 判断2个时间相差多少小时<br>
     * <br>
     * 
     * @param pBeginTime
     *            请假开始时间<br>
     * @param pEndTime
     *            请假结束时间<br>
     * @return String 计算结果<br>
     * @throws Exception
     * @Exception 发生异常<br>
     */
    public static Long timeDiffForHour(Date pBeginTime, Date pEndTime) throws Exception {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long hour = ((endL - beginL) % 86400000) / 3600000;
        return hour;
    }

    /**
     * 判断2个时间相差多少分<br>
     * <br>
     * 
     * @param pBeginTime
     *            请假开始时间<br>
     * @param pEndTime
     *            请假结束时间<br>
     * @return String 计算结果<br>
     * @throws Exception
     * @Exception 发生异常<br>
     */
    public static Long timeDiffForMin(Date pBeginTime, Date pEndTime) throws Exception {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long min = ((endL - beginL) % 86400000 % 3600000) / 60000;
        return min;
    }

    /**
     * 返回指定时间与当前时间差多少天(小时, 分钟, 刚才) 
     * @param time 要比较的时间
     * @return 多少天(小时, 分钟, 刚才)
     */
    public static String getTime(Date time) {
        String result = null;
        Long temp = null;
        final Date currentDate = new Date();

        do {
            try {
                // 看差多少天
                temp = timeDiffForDay(time, currentDate);
                if (temp > 0l) {
                    result = temp + "天前";
                    break;
                }

                // 看差多少时
                temp = timeDiffForHour(time, currentDate);
                if (temp > 0l) {
                    result = temp + "小时前";
                    break;
                }

                // 看差多少分
                temp = timeDiffForMin(time, currentDate);
                if (temp > 0l) {
                    result = temp + "分种前";
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (false);

        return result == null ? "刚才" : result;
    }

    public static Date getMonthDelay(Date currentDay, int month) {
        Calendar current = Calendar.getInstance();
        current.setTime(currentDay);

        current.add(Calendar.MONTH, month);
        current.add(Calendar.HOUR, -24);
        return current.getTime();
    }

    public static Date getStartOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getEndOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getMinEndOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static int getDaysOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取两个日期中间的工作日
     * 
     * @param starttime yyyy-MM-dd
     * @param endtime yyyy-MM-dd
     * @return
     */
    public static int getWorkTime(String starttime, String endtime) {
        // 设置时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT);
        // 开始日期
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = dateFormat.parse(starttime);
            dateTo = dateFormat.parse(endtime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int workdays = 0;
        Calendar cal = null;
        while (dateFrom.before(dateTo) || dateFrom.equals(dateTo)) {
            cal = Calendar.getInstance();
            // 设置日期
            cal.setTime(dateFrom);
            if ((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) && (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                // 进行比较，如果日期不等于周六也不等于周日，工作日+1
                workdays++;
            }
            // 日期加1
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dateFrom = cal.getTime();
        }
        return workdays;
    }

    /**
     * 获取两个日期中间的工作日
     * 
     * @param starttime yyyy-MM-dd
     * @param endtime yyyy-MM-dd
     * @return
     */
    public static int getYearDays(Calendar cal) {
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.roll(Calendar.DAY_OF_YEAR, -1);
        int yearDays = cal.get(Calendar.DAY_OF_YEAR);
        return yearDays;
    }

    /**
     * 获取本周第一天
     * 
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());// Monday
        return cal.getTime();
    }

    /**
     * 获取本周最后一天
     * 
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);// Sunday
        return cal.getTime();
    }

    /**
     * 获取上周第一秒(星期一第一天)
     * @return
     */
    public static Date getStartOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_WEEK, 2);
        cal.add(Calendar.WEDNESDAY, -1);
        return cal.getTime();
    }

    /**
     * 获取上周最后一秒(星期日最后一天)
     * @return
     */
    public static Date getEndOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_WEEK, 1);
        return cal.getTime();
    }

    /**
     * 获取开年第一天
     * @return
     */
    public static Date getStartOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_YEAR, cal.getMinimum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    /**
     * 获取某月次月的最后一天
     * 
     * @return
     */
    public static String getNextMonth0fDay(Date date, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.DATE, -1);
        return formatDateTime(cal.getTime(), format);

    }

    /**
     * 当前时间减去38年的 毫秒数.
     */
    public static long getRecentTimeVal() {
        // 38 年的 毫秒数
        long millisecondsIn38 = 86400 * 365 * 38;

        return System.currentTimeMillis() / 1000 - millisecondsIn38;
    }

    /**
     * 获得某一天是星期几, 默认是当前日期.
     * @return 1 -> SUNDAY 
     *         2 -> MONDAY 
     *         3 -> TUESDAY 
     *         4 -> WEDNESDAY 
     *         5 -> THURSDAY 
     *         6 -> FRIDAY
     *         7 -> SATURDAY
     */
    public static int getDayOfWeek(Date date) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得某一天的下周一的日期, 默认是当天.
     */
    public static Date getNextMonday(Date date) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        cal.add(Calendar.DATE, (7 - dayOfWeek + 2) % 7);

        return cal.getTime();
    }

    /**
     * 拿某一时间的 24小时计时法中的小时数, 默认当前时间,
     * E.g., at 10:04:15.250 PM the HOUR_OF_DAY is 22.
     */
    public static int getHourOfDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDiscrepantDays(Date dateStart, Date dateEnd) {
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 查看d1是否在d2之后
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isAfterDate(Date d1, Date d2) {
        d1 = org.apache.commons.lang.time.DateUtils.truncate(d1, Calendar.SECOND);
        d2 = org.apache.commons.lang.time.DateUtils.truncate(d2, Calendar.SECOND);
        return d1.after(d2);
    }
}
