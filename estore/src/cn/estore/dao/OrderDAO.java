package cn.estore.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.estore.domain.Order;
import cn.estore.domain.Orderitem;
import cn.estore.domain.User;
import cn.estore.utils.JDBCUtils;

/**
 * ���ݲ�
 * 
 * 
 * 
 */
public class OrderDAO {
	// �޶�����״̬ �Ѿ�֧��
	public void updateOrderState(String orderid) {
		String sql = "update orders set state = 1 where id = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			queryRunner.update(sql, orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���ݶ��� ��ѯ������
	public List<Orderitem> findOrderItems(Order order) {
		String sql = "select * from orderitem where order_id = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			List<Orderitem> orderitems = queryRunner.query(sql,
					new BeanListHandler<Orderitem>(Orderitem.class), order
							.getId());
			// ����id ��ѯ��Ʒ��
			for (Orderitem orderitem : orderitems) {
				// ���� ��Ʒid ȥ��ѯ��Ʒ��
				ProductDAO productDAO = new ProductDAO();
				orderitem.setProduct(productDAO.findById(orderitem
						.getProduct_id()));
			}

			return orderitems;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// �鿴��ǰ�û�����
	public List<Order> findOrdersByUser(User user) {
		String sql = "select * from orders where user_id = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			List<Order> orders = queryRunner.query(sql,
					new BeanListHandler<Order>(Order.class), user.getId());
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// �鿴���ж��� --- ��ѯ������
	public List<Order> findAll() {
		String sql = "select * from orders";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			List<Order> orders = queryRunner.query(sql,
					new BeanListHandler<Order>(Order.class));
			for (Order order : orders) {
				UserDAO userDAO = new UserDAO();
				order.setUser(userDAO.findById(order.getUser_id()));
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// ���ɶ��� ������
	public void insert(Order order) {
		
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into orders values(?,?,?,null,0,?)";
			
			String orderid = JDBCUtils.generateOrderId();
			Object[] param = { orderid, order.getTotalmoney(),
					order.getReceiverinfo(), order.getUser_id() };
			QueryRunner queryRunner = new QueryRunner(); 
			queryRunner.update(conn, sql, param);

			
			List<Orderitem> orderitems = order.getOrderItems();
			String sql2 = "insert into orderitem values(?,?,?,?)";
			for (Orderitem orderitem : orderitems) {
				Object[] param2 = { orderid, orderitem.getProduct_id(),
						orderitem.getBuynum(), orderitem.getMoney() };
				queryRunner.update(conn, sql2, param2);
			}

			
			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			throw new RuntimeException("��������ʧ�ܣ�");
		}
	}

	// ���ݱ�Ų�ѯ����
	public Order findById(String id) {
		String sql = "select * from orders where id = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			Order order = queryRunner.query(sql, new BeanHandler<Order>(
					Order.class), id);
			return order;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ���� ���� ��� ȡ������ --- ͬʱɾ�� ������ �Ͷ����� ������
	public void deleteOrder(String id) {
		
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "delete from orderitem where order_id = ?";
			QueryRunner queryRunner = new QueryRunner();
			queryRunner.update(connection, sql, id);
			String sql2 = "delete from orders where id = ?";
			queryRunner.update(connection, sql2, id);

			DbUtils.commitAndCloseQuietly(connection);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(connection);
			e.printStackTrace();
			throw new RuntimeException("ȡ������ʧ�ܣ�");
		}
	}
}
