package cn.estore.service;

import java.util.List;

import cn.estore.dao.OrderDAO;
import cn.estore.domain.Order;
import cn.estore.domain.Orderitem;
import cn.estore.domain.User;

/**
 * ���� ҵ���
 * 
 * 
 * 
 */
public class OrderService {
	// ֧������
	public void pay(String orderid) {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateOrderState(orderid);
	}

	// ���ɶ���
	public void addOrder(Order order) {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insert(order);
	}

	// �����û���� ���ز�ͬ�����б�
	public List<Order> listOrders(User loginUser) {
		// �ж����
		if (loginUser.getRole().equals("admin")) {
			
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orders = orderDAO.findAll();

			
			for (Order order : orders) {
				List<Orderitem> orderitems = orderDAO.findOrderItems(order);
				order.setOrderItems(orderitems);// �������� ���浽����
			}

			return orders;

		} else if (loginUser.getRole().equals("user")) {
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orders = orderDAO.findOrdersByUser(loginUser);

			for (Order order : orders) {
				List<Orderitem> orderitems = orderDAO.findOrderItems(order);
				order.setOrderItems(orderitems);// �������� ���浽����
				order.setUser(loginUser);
			}

			return orders;
		}
		return null;
	}

	// ȡ������ id ������� loginUserȡ��
	public void cancelOrders(String id, User loginUser) {
		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.findById(id);

		if (order == null) {
			throw new RuntimeException("��ȡ���Ķ�����Ų����ڣ�");
		}
		// �ж϶����ǲ�����֧��
		if (order.getState() == 1) {
			// �����Ѿ�֧��
			throw new RuntimeException("������֧�����޷�ȡ����");
		}
		// �ж϶����ǲ��Ǳ��˵�
		if (order.getUser_id() != loginUser.getId()) {
			throw new RuntimeException("�û�ֻ��ȡ���Լ��Ķ�����");
		}

		orderDAO.deleteOrder(id);
	}
}
