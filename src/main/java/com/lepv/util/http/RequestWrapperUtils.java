package com.lepv.util.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 从拦截器中获取json格式数据工具类
 * 
 * @author qiushi.zhou  
 * @date 2016年11月14日 下午1:30:20
 */
public class RequestWrapperUtils extends HttpServletRequestWrapper{
	
	private final String body;
	
	public RequestWrapperUtils(HttpServletRequest request) {
		super(request);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try{
			InputStream inputStream = request.getInputStream();
			if(inputStream !=null){
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while((bytesRead = bufferedReader.read(charBuffer)) > 0){
					stringBuilder.append(charBuffer,0,bytesRead);
				}
			}else{
				stringBuilder.append("");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		body = stringBuilder.toString();
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException{
		final ByteArrayInputStream byteArrayInputStream = 
				new ByteArrayInputStream(body.getBytes());
		ServletInputStream servletInputStream = new ServletInputStream(){
			public int read() throws IOException{
				return byteArrayInputStream.read();
			}
		};
		return servletInputStream;
	}
	
	@Override
	public BufferedReader getReader() throws IOException{
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
	
	public String getBody(){
		return this.body;
	}
	
}
