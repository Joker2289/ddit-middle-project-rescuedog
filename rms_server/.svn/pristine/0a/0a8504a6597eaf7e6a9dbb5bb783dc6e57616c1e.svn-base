package kr.or.ddit.rms.shelter.donation_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Spon_LogVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class DonationBoardSDaoImpl implements IDonationBoardSDao {

	private static DonationBoardSDaoImpl dao;

	private SqlMapClient smc;

	private DonationBoardSDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static DonationBoardSDaoImpl getInstance() {
		if (dao == null) {
			dao = new DonationBoardSDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertBoard(Board_detailVO vo) {
		int cnt = 0;
		try {

			Object obj = smc.insert("board_detail.insertBoard_detail", vo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(Board_detailVO vo) {
		int cnt = 0;
		try {
			cnt = smc.delete("board_detail.deleteBoard_detail", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(Board_detailVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("board_detail.updateBoard_detail", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Board_detailVO> getBoard_detailAll() {
		List<Board_detailVO> list = new ArrayList<Board_detailVO>();
		try {
			list = smc.queryForList("board_detail.getBoard_detailAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) {
		List<Board_detailVO> list = new ArrayList<Board_detailVO>();
		try {
			list = smc.queryForList("board_detail.getSearchBoard_detail", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Board_detailVO> getSearchBoard_detail_or(Board_detailVO vo) {
		List<Board_detailVO> list = new ArrayList<Board_detailVO>();
		try {
			list = smc.queryForList("board_detail.getSearchBoard_detail_or", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertSpon_log(Spon_LogVO vo) {
		int cnt = 0;
		try {

			Object obj = smc.insert("spon_log.insertSpon_log", vo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateSpon_log(Spon_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("spon_log.updateSpon_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteSpon_log(Spon_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.delete("spon_log.deleteSpon_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Spon_LogVO> getSpon_logAll() {
		List<Spon_LogVO> list = new ArrayList<Spon_LogVO>();
		try {
			list = smc.queryForList("spon_log.getSpon_logAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) {
		List<Spon_LogVO> list = new ArrayList<Spon_LogVO>();
		try {
			list = smc.queryForList("spon_log.getSearchSpon_log", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
