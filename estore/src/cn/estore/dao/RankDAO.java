package cn.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.estore.domain.Orderitem;
import cn.estore.domain.Product;
import cn.estore.utils.JDBCUtils;

/**
 * 查看榜单需要数据
 * 
 * 
 * 
 */
public class RankDAO {
	// 查看榜单数据
	public List<Orderitem> findRankData() {
		String sql = "select product_id , sum(orderitem.buynum) buynum from orders,orderitem where orders.id = orderitem.order_id and orders.state =1 group by orderitem.product_id";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			List<Orderitem> rank = queryRunner.query(sql,
					new BeanListHandler<Orderitem>(Orderitem.class));
			for (Orderitem orderitem : rank) {
				String sql2 = "select * from products where id = ?";
				Product product = queryRunner.query(sql2,
						new BeanHandler<Product>(Product.class), orderitem
								.getProduct_id());

				// 将商品 关联 orderitem
				orderitem.setProduct(product);
			}

			return rank;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
