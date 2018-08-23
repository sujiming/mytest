package cn.estore.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.estore.dao.RankDAO;
import cn.estore.domain.Orderitem;

/**
 * �� ҵ����
 * 
 * 
 * 
 */
public class RankService {
	// �鿴��
	public List<Orderitem> showRank() {
		RankDAO rankDAO = new RankDAO();
		List<Orderitem> rank = rankDAO.findRankData();
		// �鿴���� ��һ������� --- ��Ӧ�ð���������������
		Collections.sort(rank, new RankComparator());// sort --- Ordetitem
		return rank;
	}

	class RankComparator implements Comparator<Orderitem> {

		
		public int compare(Orderitem o1, Orderitem o2) {
			// ���� ǰ - �� ���� �� -ǰ
			return o2.getBuynum() - o1.getBuynum();
		}

	}
}
