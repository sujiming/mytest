package cn.estore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码
 * 
 * 
 */
public class CheckcodeServlet extends HttpServlet {

	private List<String> words = new ArrayList<String>();

	@Override
	public void init() throws ServletException {
		String filepath = CheckcodeServlet.class.getResource("/new_words.txt")
				.getFile();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filepath)));
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int width = 120;
		int height = 30;
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics graphics = bufferedImage.getGraphics(); // 通过 graphics对象 绘制图片
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width, height);

		graphics.setColor(Color.BLUE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		Graphics2D graphics2d = (Graphics2D) bufferedImage.getGraphics();
		graphics2d.setColor(Color.RED);
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));
		Random random = new Random();
		int x = 10;
		int y = 20;

		int randomNum = random.nextInt(words.size());
		String checkcode = words.get(randomNum);
		request.getSession().setAttribute("checkcode_session", checkcode);

		for (int i = 0; i < checkcode.length(); i++) { 
			double jiaodu = random.nextInt(60) - 30;
			double theta = jiaodu / 180 * Math.PI;

			char letter = checkcode.charAt(i); 
			graphics2d.rotate(theta, x, y);
			graphics2d.drawString(letter + "", x, y);
			graphics2d.rotate(-theta, x, y);
			x += 30;
		}

		// 绘制随机干扰线
		int x1;
		int x2;
		int y1;
		int y2;
		graphics.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < 10; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(width);
			y1 = random.nextInt(height);
			y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}

		graphics.dispose();

		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
