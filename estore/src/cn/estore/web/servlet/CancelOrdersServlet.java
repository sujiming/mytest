package cn.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.User;
import cn.estore.service.OrderService;

/**
 * È¡Ïû¶©µ¥
 * 
 * @author seawind
 * 
 */
public class CancelOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		OrderService orderService = new OrderService();
		orderService.cancelOrders(id, loginUser);

		response.sendRedirect("/listOrders");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
