package cn.estore.web.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.estore.domain.User;

/**
 * 权限过滤器
 * 
 * 
 */
public class PrivilegeFilter implements Filter {

	private List<String> adminPathList = new ArrayList<String>();

	private List<String> userPathList = new ArrayList<String>();

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String visitPath = httpServletRequest.getRequestURI().substring(
				httpServletRequest.getContextPath().length());
		if (adminPathList.contains(visitPath)
				|| userPathList.contains(visitPath)) {
			User user = (User) httpServletRequest.getSession().getAttribute(
					"loginUser");
			if (user != null) { 
				if (user.getRole().equals("admin")) {
					if (adminPathList.contains(visitPath)) {
						
						chain.doFilter(httpServletRequest, response);
					} else {
						throw new RuntimeException("您的权限不足！您当前权限是："
								+ user.getRole());
					}
				} else if (user.getRole().equals("user")) {
					if (userPathList.contains(visitPath)) {
		
						chain.doFilter(httpServletRequest, response);
					} else {
						throw new RuntimeException("您的权限不足！您当前权限是："
								+ user.getRole());
					}
				}

			} else {
				
				request.setAttribute("msg", "您访问的资源必须先登陆！");
				request.getRequestDispatcher("/login.jsp").forward(
						httpServletRequest, response);
			}
		} else {
			
			chain.doFilter(httpServletRequest, response);
		}

	}

	
	public void init(FilterConfig filterConfig) throws ServletException {
		// 读取 admin.txt 和 user.txt
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(
					PrivilegeFilter.class.getResource("/admin.txt").getFile()));
			String line1;
			while ((line1 = reader1.readLine()) != null) {
				adminPathList.add(line1);
			}
			reader1.close();

			BufferedReader reader2 = new BufferedReader(new FileReader(
					PrivilegeFilter.class.getResource("/user.txt").getFile()));
			String line2;
			while ((line2 = reader2.readLine()) != null) {
				userPathList.add(line2);
			}
			reader2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
