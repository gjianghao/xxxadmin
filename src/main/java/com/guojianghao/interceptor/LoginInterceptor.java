package com.guojianghao.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guojianghao.entity.sys.SysUser;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private List<String> excludedUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI().replace(request.getContextPath(), "");

		if (this.excludedUrls != null && this.excludedUrls.size() > 0) {
			for (String url : excludedUrls) {
				if (requestURI.contains(url)) {
					return true;
				}
			}
		}

		HttpSession session = request.getSession();
		SysUser sysUser =  (SysUser)session.getAttribute("user");
		if (sysUser != null) {
			return true;
		} else {
			response.sendRedirect("/sn-admin/index");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	
	public List<String> getExcludedUrls() {
		return excludedUrls;
	}
	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}
