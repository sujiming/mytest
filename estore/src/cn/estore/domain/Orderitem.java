package cn.estore.domain;

/**
 * ������
 * 
 * 
 * 
 */
public class Orderitem {
	private String order_id;
	private String product_id;
	private int buynum;
	private double money;

	// �ڶ�����ʾ��Ʒ��Ϣ
	private Product product;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String orderId) {
		order_id = orderId;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String productId) {
		product_id = productId;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

}
