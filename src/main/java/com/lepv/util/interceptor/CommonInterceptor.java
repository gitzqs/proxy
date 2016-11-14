package com.lepv.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lepv.util.http.RequestWrapperUtils;

/**
 * 拦截器
 * 
 * @author qiushi.zhou  
 * @date 2016年11月14日 上午10:58:49
 */
public class CommonInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String path = request.getServletPath();
		RequestWrapperUtils r = new RequestWrapperUtils(request);
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
				+request.getContextPath()+"/forward?url="+path+"&params="+r.getBody();
		response.sendRedirect(url);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
