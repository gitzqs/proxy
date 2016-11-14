package com.lepv.util.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.lepv.util.json.JacksonUtils;

/**
 * http post通信
 * 
 * @author qiushi.zhou  
 * @date 2016年11月13日 下午1:58:28
 */
public class HttpUtils {
	
	public static String httpPost(String url,String params){
		URL connect;
		StringBuffer data = new StringBuffer();
		
		try{
			connect = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			
			OutputStreamWriter paramout = new OutputStreamWriter(
					connection.getOutputStream(),"UTF-8");
			paramout.write(params);
			paramout.flush();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			String line;
			while((line = reader.readLine()) != null){
				data.append(line);
			}
			paramout.close();
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return data.toString();
	}
	
	public static void main(String[] args){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("email", "qiushi.zhou@le-pv.com");
		params.put("password", "123456");
		String result = HttpUtils.httpPost("Http://localhost:8080/lepv_system/api/login", JacksonUtils.object2json(params));
		System.out.println(result);
	}
}
