package cn.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Product;
import cn.estore.service.ProductService;

/**
 * 根据 编号 查询商品
 * 
 * 
 */
public class ShowProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService productService = new ProductService();
		Product product = productService.showProduct(id);

		request.setAttribute("product", product);
		request.getRequestDispatcher("/detail_product.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
