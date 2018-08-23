package cn.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;
import cn.estore.domain.Orderitem;

/**
 * 导出榜单
 * 
 * 
 * 
 */
public class ExportRankServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Orderitem> rank = (List<Orderitem>) getServletContext()
				.getAttribute("rank");
		response.setContentType(getServletContext().getMimeType("rank.csv"));
		Date date = new Date(System.currentTimeMillis());
		String fileName = "estore商城销售排行榜_" + date + ".csv";

		String agent = request.getHeader("user-agent");
		if (agent.contains("MSIE")) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else if (agent.contains("Mozilla")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			fileName = "=?UTF-8?B?"
					+ new String(base64Encoder.encode(fileName
							.getBytes("UTF-8"))) + "?=";
		}

		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);

		response.setCharacterEncoding("gbk");

		PrintWriter out = response.getWriter();
		out.println("排名,商品编号,名称,单价,销售数量");
		for (int i = 0; i < rank.size(); i++) {
			Orderitem orderitem = rank.get(i);

			out.println((i + 1) + "," + orderitem.getProduct().getId() + ","
					+ convert(orderitem.getProduct().getName()) + ","
					+ orderitem.getProduct().getPrice() + ","
					+ orderitem.getBuynum());
		}

		out.flush();
	}

	private String convert(String name) {
		name = name.replace("\"", "\"\"");
		return "\"" + name + "\"";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
