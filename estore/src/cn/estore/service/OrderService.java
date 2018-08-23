package cn.estore.service;

import java.util.List;

import cn.estore.dao.OrderDAO;
import cn.estore.domain.Order;
import cn.estore.domain.Orderitem;
import cn.estore.domain.User;

/**
 * 订单 业务层
 * 
 * 
 * 
 */
public class OrderService {
	// 支付订单
	public void pay(String orderid) {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateOrderState(orderid);
	}

	// 生成订单
	public void addOrder(Order order) {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insert(order);
	}

	// 根据用户身份 返回不同订单列表
	public List<Order> listOrders(User loginUser) {
		// 判断身份
		if (loginUser.getRole().equals("admin")) {
			
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orders = orderDAO.findAll();

			
			for (Order order : orders) {
				List<Orderitem> orderitems = orderDAO.findOrderItems(order);
				order.setOrderItems(orderitems);// 将订单项 保存到订单
			}

			return orders;

		} else if (loginUser.getRole().equals("user")) {
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orders = orderDAO.findOrdersByUser(loginUser);

			for (Order order : orders) {
				List<Orderitem> orderitems = orderDAO.findOrderItems(order);
				order.setOrderItems(orderitems);// 将订单项 保存到订单
				order.setUser(loginUser);
			}

			return orders;
		}
		return null;
	}

	// 取消订单 id 订单编号 loginUser取消
	public void cancelOrders(String id, User loginUser) {
		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.findById(id);

		if (order == null) {
			throw new RuntimeException("您取消的订单编号不存在！");
		}
		// 判断订单是不是已支付
		if (order.getState() == 1) {
			// 订单已经支付
			throw new RuntimeException("订单已支付，无法取消！");
		}
		// 判断订单是不是本人的
		if (order.getUser_id() != loginUser.getId()) {
			throw new RuntimeException("用户只能取消自己的订单！");
		}

		orderDAO.deleteOrder(id);
	}
}
