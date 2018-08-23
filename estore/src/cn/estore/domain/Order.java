package cn.estore.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * ����
 * 
 * 
 * 
 */
public class Order {
	private String id;
	private double totalmoney;
	private String receiverinfo;
	private Timestamp ordertime;
	private int state;
	private int user_id;

	// һ������ �����ܶඩ����
	private List<Orderitem> orderItems;

	// ������ʾʱ����ʾ�µ��û���Ϣ
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getReceiverinfo() {
		return receiverinfo;
	}

	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		user_id = userId;
	}

	public void setOrderItems(List<Orderitem> orderItems) {
		this.orderItems = orderItems;
	}

	public List<Orderitem> getOrderItems() {
		return orderItems;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
