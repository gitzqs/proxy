package com.lepv.web.proxy;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lepv.util.http.HttpUtils;
import com.lepv.util.json.JacksonUtils;
import com.lepv.util.json.PropertiesUtils;

/**
 * 代理
 * 
 * @author qiushi.zhou  
 * @date 2016年11月14日 下午2:13:23
 */
@Controller
public class ProxyController {
	
	
	/**
	 * 代理跳转
	 * 
	 * @param 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="forward")
	@ResponseBody
	public String forward(String url, String params){
		Map<String,Object> map = (Map<String,Object>) JacksonUtils.json2map(params);
		String result = HttpUtils.httpPost(PropertiesUtils.getPropertyByKey("url.properties", "URL")+url, 
				JacksonUtils.object2json(map));
		return result;
	}
	
}
