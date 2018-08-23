package cn.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.estore.domain.User;
import cn.estore.service.UserService;

/**
 * 用户登录
 * 
 * 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		UserService service = new UserService();
		User loginUser = service.login(user);

		if (loginUser == null) {
			request.setAttribute("msg", "用户名或者密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			request.getSession().setAttribute("loginUser", loginUser);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
