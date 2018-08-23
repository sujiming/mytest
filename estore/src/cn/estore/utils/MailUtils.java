package cn.estore.utils;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.estore.domain.User;

/**
 * 发送邮件 工具方法
 * 
 * 
 */
public class MailUtils {
	// 生成激活码
	public static String generateActivecode() {
		return UUID.randomUUID().toString();
	}

	// 发送邮件
	public static void sendMail(Message message, Session session)
			throws Exception {
		Transport transport = session.getTransport();
		transport.connect("admin", "111");
		transport.sendMessage(message, message.getAllRecipients());
	}

	// 生成邮件
	public static Message generateMessage(Session session, User user)
			throws Exception {
		MimeMessage message = new MimeMessage(session);
		// 邮件头
		message.setFrom(new InternetAddress("admin@estore.com"));// 发件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user
				.getEmail())); // 收件人
		message.setSubject("ESTORE商城 激活邮件");
		// 邮件头
		message
				.setContent(
						"<h2>欢迎"
								+ user.getUsername()
								+ "注册Estore商城，这里可以购买您需要商品！</h2><h3>请于2小时内点击下面链接完成账户激活：</h3><a href='http://www.estore.com/active?activecode="
								+ user.getActivecode()
								+ "'>http://www.estore.com/active?activecode="
								+ user.getActivecode() + "</a>",
						"text/html;charset=utf-8");
		return message;
	}

	// 创建会话
	public static Session createSession() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");

		Session session = Session.getInstance(properties);
		return session;
	}
}
