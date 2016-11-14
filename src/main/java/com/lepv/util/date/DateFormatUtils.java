
package com.lepv.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;

/**
 * 时间格式转换
 * 
 * @author qiushi.zhou  
 * @date 2016年10月21日 下午2:27:43
 */
public class DateFormatUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
    
    public static final String y = "yyyy";
    public static final String ym = "yyyy-MM";
    public static final String ymd = "yyyy-MM-dd";
    public static final String ymd_hm = "yyyy-MM-dd HH:mm";
    public static final String ymd_hms = "yyyy-MM-dd HH:mm:ss";
    
    private static ThreadLocal<Map<String, DateFormat>> dateFormat = new ThreadLocal<Map<String, DateFormat>>();
    
    
    public static DateFormat getDateFormat(String pattern) {
        Map<String, DateFormat> dfMap = dateFormat.get();
        if (dfMap == null) {
            dfMap = new HashMap<String, DateFormat>();
            dateFormat.set(dfMap);
        }
        if (StringUtils.isEmpty(pattern)) throw new IllegalArgumentException("date format patter must not be null or empty!");

        DateFormat df = dfMap.get(pattern);
        if (df == null) {
            df = new SimpleDateFormat(pattern);
            dfMap.put(pattern, df);
        }
        return dfMap.get(pattern);
    }
    
    public static String format(Date date, String pattern) {
        DateFormat df = getDateFormat(pattern);
        return df.format(date);
    }
    
    public static Date parse(String source, String pattern) {
        DateFormat df = getDateFormat(pattern);
        try {
            return df.parse(source);
        } catch (ParseException e) {
            logger.error("can't parse source: [{}] to date! catched ParseException:\n\t{}", new Object[]{source, e.getLocalizedMessage()});
        } catch (Exception e) {
            logger.error("can't parse source: [{}] to date! catched Exception:\n\t{}", new Object[]{source, e.getLocalizedMessage()});
        }
        return null;
    }
    /**
     * 将yyyy-MM-dd HH:mm:ss格式变为 yyyy-MM-dd格式
     * 
     * @param date
     * @return Date
     */
    public static Date date2Date(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ymd);
    	Date returnDate = null;
    	try {
    		returnDate = format.parse(format.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return returnDate;
    }
    
    /**
     * 将yyyy-MM-dd HH:mm:ss格式变为 yyyy-MM-dd格式
     * 
     * @param 
     * @return String
     */
    public static String date2Day(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ymd);
    	return format.format(date);
    }
    
    /**
     * 将yyyy-MM-dd HH:mm:ss格式变为yyyy格式
     * 
     * @param date
     * @return String
     */
    public static String date2Year(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(y);
    	
    	return format.format(date);
    }
    
    /**
     * 将yyyy-MM-dd HH:mm:ss 转变为 yyyy-MM
     * 
     * @param 
     * @return String
     */
    public static String date2Month(Date date){
    	SimpleDateFormat format = new SimpleDateFormat(ym);
    	return format.format(date);
    }

}
