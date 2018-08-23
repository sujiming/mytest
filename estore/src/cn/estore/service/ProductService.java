package cn.estore.service;

import java.util.List;

import cn.estore.dao.ProductDAO;
import cn.estore.domain.Product;

/**
 * 商品业务层
 * 
 * 
 */
public class ProductService {
	public void addProduct(Product product) {
		ProductDAO dao = new ProductDAO();
		dao.insert(product);
	}

	public List<Product> listProduct() {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.findAll();
	}

	public Product showProduct(String id) {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.findById(id);
	}
}
