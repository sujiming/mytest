package cn.estore.service;

import javax.mail.Message;
import javax.mail.Session;

import cn.estore.dao.UserDAO;
import cn.estore.domain.User;
import cn.estore.utils.MailUtils;

/**
 * 用户操作 业务层
 * 
 * 
 */
public class UserService {
	// 用户注册
	// 这里没写返回值，用户名 或者 邮箱重复 报错
	public void regist(User user) {
		String activecode = MailUtils.generateActivecode();
		user.setActivecode(activecode);

		UserDAO dao = new UserDAO();
		dao.insert(user);

		Session session = MailUtils.createSession();

		try {
			Message message = MailUtils.generateMessage(session, user);
			MailUtils.sendMail(message, session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送激活邮件失败！");
		}

	}

	// 账户激活
	public void active(String activecode) {
		// 1、根据激活码 查询账户 --- 判断激活码是否存在
		UserDAO dao = new UserDAO();
		User user = dao.findByActivecode(activecode);
		if (user == null) {
			// 激活码无效
			throw new RuntimeException("激活码无效！");
		} else {
			// 激活码存在
			// 2 判断激活码是否过期
			if (System.currentTimeMillis() - user.getRegisttime().getTime() > 1000 * 60 * 60 * 2) {
				// 超过两小时
				// TODO 重新发送 --- 更新数据库保存激活码
				throw new RuntimeException("激活码已经过期！");
			} else {
				// 3 可以激活
				user.setState(1);
				dao.updateState(user);
			}
		}
	}

	// 用户登录 --- 返回包含所有用户信息 对象
	public User login(User user) {
		UserDAO userDAO = new UserDAO();
		return userDAO.findByUsernameAndPassword(user.getUsername(), user
				.getPassword());
	}
}
