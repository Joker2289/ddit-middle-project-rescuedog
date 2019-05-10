package kr.or.ddit.rms.shelter.review_board;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class Review_boardSDaoImpl implements Review_boardSDao {

	private static Review_boardSDaoImpl dao;

	private SqlMapClient smc;

	private Review_boardSDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static Review_boardSDaoImpl getInstance() {
		if (dao == null) {
			dao = new Review_boardSDaoImpl();
		}
		return dao;
	}

	@Override
	public List<Board_detailVO> getBoard_detailAll() {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("Reviewboard_detail.getReview_detailAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("Reviewboard_detail.insertReview_detail", vo);
			if (obj == null) {
				num = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updateBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			num = smc.update("Reviewboard_detail.updateReview_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			num = smc.delete("Reviewboard_detail.deleteReview_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("Reviewboard_detail.getSearchReview_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Board_detailVO> getReview_detailTextSearch(Board_detailVO vo) {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("Reviewboard_detail.getReview_detailTextSearch", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
