package cn.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Order;
import cn.estore.domain.User;
import cn.estore.service.OrderService;

/**
 * ²é¿´ ¶©µ¥
 * 
 * 
 */
public class ListOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		OrderService orderService = new OrderService();

		List<Order> orders = orderService.listOrders(loginUser);

		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orders.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
