package cn.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Product;
import cn.estore.service.ProductService;

/**
 * 添加商品 到购物车
 * 
 * 
 */
public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		ProductService productService = new ProductService();
		Product product = productService.showProduct(id);

		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");
		if (cart == null) { 
			cart = new HashMap<Product, Integer>();
		}

		if (cart.containsKey(product)) { 
			int number = cart.get(product);
			cart.put(product, number + 1);
		} else {
			cart.put(product, 1);
		}

		request.getSession().setAttribute("cart", cart);

		request.getRequestDispatcher("/listProduct").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
