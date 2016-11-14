package com.lepv.util.json;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * 
 * @author qiushi.zhou  
 * @date 2016年11月2日 下午2:44:20
 */
public class PropertiesUtils {
	
	public static String getPropertyByKey(String fileName,String key) {  
	       Properties props = new Properties(); 
	        try {  
	            InputStream inStream = PropertiesUtils.class.getClassLoader()  
	                    .getResourceAsStream(fileName);  
	            props.load(inStream);  
	        } catch (Exception ex) {  
	            throw new RuntimeException("配置文件读取出错" + ex.getMessage(), ex);  
	        }
	        return props.getProperty(key);
	    } 
}
