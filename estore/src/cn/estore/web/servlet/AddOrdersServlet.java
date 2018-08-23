package cn.estore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Order;
import cn.estore.domain.Orderitem;
import cn.estore.domain.Product;
import cn.estore.domain.User;
import cn.estore.service.OrderService;

/**
 * 生成订单
 * 
 * 
 */
public class AddOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String receiverinfo = request.getParameter("receiverinfo");
		Order order = new Order(); // 一个订单
		List<Orderitem> orderitems = new ArrayList<Orderitem>();
		order.setReceiverinfo(receiverinfo);
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		order.setUser_id(loginUser.getId());

		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");
		int totalmoney = 0;
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			totalmoney += entry.getKey().getPrice() * entry.getValue();
			Orderitem orderitem = new Orderitem();
			orderitem.setProduct_id(entry.getKey().getId());
			orderitem.setBuynum(entry.getValue()); 
			orderitem.setMoney(entry.getKey().getPrice() * entry.getValue());
			orderitems.add(orderitem);
		}
		order.setTotalmoney(totalmoney); 

		order.setOrderItems(orderitems);

		OrderService orderService = new OrderService();
		orderService.addOrder(order);

		request.getSession().removeAttribute("cart");

		response.getWriter().println("订单已经提交 ! <a href='/listOrders'>查看订单</a>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
