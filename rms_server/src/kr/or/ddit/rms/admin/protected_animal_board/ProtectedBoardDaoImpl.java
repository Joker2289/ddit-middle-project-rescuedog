package kr.or.ddit.rms.admin.protected_animal_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Protected_boardVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class ProtectedBoardDaoImpl implements IProtectedBoardDao {
	private SqlMapClient smc;
	private static ProtectedBoardDaoImpl dao;

	private ProtectedBoardDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static ProtectedBoardDaoImpl getInstance() {
		if (dao == null) {
			dao = new ProtectedBoardDaoImpl();
		}
		return dao;
	}

	@Override
	public int deleteBoard(Protected_boardVO bv) {
		int cnt = 0;
		try {
			cnt = smc.delete("protected.deleteboard", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Protected_boardVO> getAllBoardList() {
		List<Protected_boardVO> list = new ArrayList<Protected_boardVO>();
		try {

			list = smc.queryForList("protected.getboardAll");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Protected_boardVO> getAllBoard_SerchList(Protected_boardVO vo) {
		List<Protected_boardVO> list = new ArrayList<Protected_boardVO>();
		try {

			list = smc.queryForList("protected.getSearchprotected", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Protected_boardVO> getprotectedTextSearch(Protected_boardVO vo) {
		List<Protected_boardVO> list = new ArrayList<Protected_boardVO>();
		try {

			list = smc.queryForList("protected.getprotectedTextSearch", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
