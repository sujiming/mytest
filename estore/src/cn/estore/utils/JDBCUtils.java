package cn.estore.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 工具类 提供数据库连接池 和数据库连接
 * 
 * 
 */
public class JDBCUtils {
	private static DataSource dataSource = new ComboPooledDataSource();

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// 生成随机订单编号
	public static String generateOrderId() {
		String uuid = UUID.randomUUID().toString(); // xxxx-xxx-xxx-xxxx-xxx
		int hashCode = Math.abs(uuid.hashCode());
		return "order-" + hashCode;
	}

	// 生成随机 商品编号
	public static String generateProductId() {
		String uuid = UUID.randomUUID().toString(); // xxxx-xxx-xxx-xxxx-xxx
		int hashCode = Math.abs(uuid.hashCode());
		return "ep-" + hashCode;
	}
}
