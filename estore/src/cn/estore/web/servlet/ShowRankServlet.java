package cn.estore.web.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.estore.domain.Orderitem;
import cn.estore.service.RankService;

/**
 * ²é¿´°ñµ¥
 * 
 * 
 */
public class ShowRankServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RankService rankservice = new RankService();
		List<Orderitem> rank = rankservice.showRank();

		getServletContext().setAttribute("rank", rank);
		request.getRequestDispatcher("/rank.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
