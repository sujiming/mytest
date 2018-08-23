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
 * �û�ע��
 * 
 * @author seawind
 * 
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkcode = request.getParameter("checkcode");
		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session");
		request.getSession().removeAttribute("checkcode_session");

		if (checkcode == null || !checkcode.equals(checkcode_session)) {
			request.setAttribute("msg", "��֤���������");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
		} else {
			User user = new User();
			try {
				BeanUtils.populate(user, request.getParameterMap());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			UserService service = new UserService();
			service.regist(user);

			response.getWriter().print("ע��ɹ��������ʼ��ѷ�����ע�����䣬����2Сʱ������˺ż��");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
