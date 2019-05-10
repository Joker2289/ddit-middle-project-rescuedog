package kr.or.ddit.rms.user.free_board;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class Free_boardUDaoImpl implements Free_boardUDao {

	private static Free_boardUDaoImpl dao;

	private SqlMapClient smc;

	private Free_boardUDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static Free_boardUDaoImpl getInstance() {
		if (dao == null) {
			dao = new Free_boardUDaoImpl();
		}
		return dao;
	}

	@Override
	public List<Board_detailVO> getBoard_detailAll() {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("freeboard_detail.getFree_detailAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("freeboard_detail.insertfree_detail", vo);
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
			num = smc.update("freeboard_detail.updatefree_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			num = smc.delete("freeboard_detail.deleteFree_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("freeboard_detail.getSearchFree_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Board_detailVO> getFree_detailTextSearch(Board_detailVO vo) {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("freeboard_detail.getFree_detailTextSearch", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
