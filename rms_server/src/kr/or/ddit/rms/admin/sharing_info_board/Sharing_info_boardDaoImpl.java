package kr.or.ddit.rms.admin.sharing_info_board;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class Sharing_info_boardDaoImpl implements Sharing_info_boardDao {

	private static Sharing_info_boardDaoImpl dao;
	private SqlMapClient smc;

	private Sharing_info_boardDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static Sharing_info_boardDaoImpl getInstance() {
		if (dao == null) {
			dao = new Sharing_info_boardDaoImpl();
		}
		return dao;
	}

	@Override
	public List<Board_detailVO> getBoard_detailAll() {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("Shareboard_detail.getShare_detailAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("Shareboard_detail.insertShare_detail", vo);
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
			num = smc.update("Shareboard_detail.updateShare_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteBoard_detail(Board_detailVO vo) {
		int num = 0;
		try {
			num = smc.delete("Shareboard_detail.deleteShare_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("Shareboard_detail.getSearchShare_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Board_detailVO> getShare_detailTextSearch(Board_detailVO vo) {
		List<Board_detailVO> list = null;
		try {
			list = smc.queryForList("Shareboard_detail.getShare_detailTextSearch", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
