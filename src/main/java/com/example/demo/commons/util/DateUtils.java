package com.example.demo.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtils {

    public static List<String> getDate(String begindate,String enddate) {
    	List<String> list = new ArrayList<String>();
    	DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
    	
    	if(Utils.assertNotNull(begindate) && Utils.assertNotNull(enddate)){
    		Calendar start = Calendar.getInstance();
            int[] date = parseTime(begindate);
            start.set(date[0], date[1], date[2]);
            start.add(Calendar.MONTH, -1);
            Long startTIme = start.getTimeInMillis();
            
            Calendar end = Calendar.getInstance(); 
            date = parseTime(enddate);
            end.set(date[0], date[1], date[2]);
            end.add(Calendar.MONTH, -1);
            Long endTime = end.getTimeInMillis();
            
            Long oneDay = 1000 * 60 * 60 * 24l;
            Long time = startTIme;
            while (time <= endTime) {
                Date d = new Date(time);
                String str = df.format(d) + "【" + getWeekOfDate(d) + "】";
                list.add(str);
                System.out.println(str);
                time += oneDay;
            }
    	}
    	
        return list;
    }
    
    public static Date dateAdd() {
		// 日期处理模块 (将日期加上某些天或减去天数)返回字符串
		Calendar canlendar = Calendar.getInstance();
		return canlendar.getTime();
	}
    
    /**
     * 字符串后面不带周几的集合格式
     * @param begindate
     * @param enddate
     * @return
     */
    public static List<String> getDates(String begindate,String enddate) {
    	List<String> list = new ArrayList<String>();
    	DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
    	
    	if(Utils.assertNotNull(begindate) && Utils.assertNotNull(enddate)){
    		Calendar start = Calendar.getInstance();
    		int[] date = parseTime(begindate);
    		start.set(date[0], date[1], date[2]);
    		start.add(Calendar.MONTH, -1);
    		Long startTIme = start.getTimeInMillis();
    		
    		Calendar end = Calendar.getInstance(); 
    		date = parseTime(enddate);
    		end.set(date[0], date[1], date[2]);
    		end.add(Calendar.MONTH, -1);
    		Long endTime = end.getTimeInMillis();
    		
    		Long oneDay = 1000 * 60 * 60 * 24l;
    		Long time = startTIme;
    		while (time <= endTime) {
    			Date d = new Date(time);
    			String str = df.format(d);
    			list.add(str);
    			System.out.println(str);
    			time += oneDay;
    		}
    	}
    	
    	return list;
    }
    
    //format : YYYY年MM月DD日    
    private static int[] parseTime(final String timeString){        
    	final int [] ret = new int[3];              
    	String year[] = timeString.split("年");
    	if(year.length == 2 && year[0].length() == 4){
    		ret[0] = Integer.parseInt(year[0]);
    		String month[] = year[1].split("月");
    		if(month.length == 2 && month[0].length() == 2){
    			ret[1] = Integer.parseInt(month[0]);
    			String day[] = month[1].split("日");
    			if(day.length == 1 && day[0].length() == 2){
    				ret[2] = Integer.parseInt(day[0]);
    			}
    		}
    	}       
    	return ret;    
    }
    
    
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    
    /**解析日期字符格式
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date,String pattern){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
    }
    
	public static Date formatStrToDate(String dateStr, String format) {
		return formatStringToDate(dateStr, format, true);
	}

	/**
	 * 功能: 将字符串转换为指定格式的日期返回
	 *
	 * @param dateStr
	 *            要转换的字符串
	 * @param format
	 *            目标日期格式
	 * @return
	 */
	public static Date formatStringToDate(String dateStr, String format, boolean lenient) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat(format);
		sdf1.setLenient(lenient);
		Date date = null;
		try {
			date = sdf1.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
    
	/**
	 * 获取系统当天时间到当天23:59:59秒值
	 * @return
	 */
	public static long getDayEndTime() {
		Calendar curDate = Calendar.getInstance();  
	    Calendar tommorowDate = new GregorianCalendar(curDate  
	            .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate  
	            .get(Calendar.DATE) + 1, 0, 0, 0);  
	    return (tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000; 
	}
	
	/**
	 * 将日期加减n天数。 <br/>
	 * 如传入整型-5 意为将当前日期减去5天的日期 <br/>
	 * 如传入整型5 意为将当前日期加上5天后的日期 <br/>
	 * 
	 * @param days
	 * @return
	 */
	public static Date dateAdd(Date date,int days) {
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(date);
		canlendar.add(Calendar.DAY_OF_MONTH, days);
		return canlendar.getTime();
	}

	public static String strDateAdd(String date,String fmt){
		DateFormat df = new SimpleDateFormat(fmt);
		Calendar calendar = Calendar.getInstance();
		try {
			Date dd = df.parse(date);
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
			return df.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
    
    
    public static void main(String[] args) {
    	String begindate = "2015年06月22日";
    	String enddate = "2015年06月25日";
    	getDates(begindate,enddate);
    }  
    
}
