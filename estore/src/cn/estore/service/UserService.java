package cn.estore.service;

import javax.mail.Message;
import javax.mail.Session;

import cn.estore.dao.UserDAO;
import cn.estore.domain.User;
import cn.estore.utils.MailUtils;

/**
 * �û����� ҵ���
 * 
 * 
 */
public class UserService {
	// �û�ע��
	// ����ûд����ֵ���û��� ���� �����ظ� ����
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
			throw new RuntimeException("���ͼ����ʼ�ʧ�ܣ�");
		}

	}

	// �˻�����
	public void active(String activecode) {
		// 1�����ݼ����� ��ѯ�˻� --- �жϼ������Ƿ����
		UserDAO dao = new UserDAO();
		User user = dao.findByActivecode(activecode);
		if (user == null) {
			// ��������Ч
			throw new RuntimeException("��������Ч��");
		} else {
			// ���������
			// 2 �жϼ������Ƿ����
			if (System.currentTimeMillis() - user.getRegisttime().getTime() > 1000 * 60 * 60 * 2) {
				// ������Сʱ
				// TODO ���·��� --- �������ݿⱣ�漤����
				throw new RuntimeException("�������Ѿ����ڣ�");
			} else {
				// 3 ���Լ���
				user.setState(1);
				dao.updateState(user);
			}
		}
	}

	// �û���¼ --- ���ذ��������û���Ϣ ����
	public User login(User user) {
		UserDAO userDAO = new UserDAO();
		return userDAO.findByUsernameAndPassword(user.getUsername(), user
				.getPassword());
	}
}
