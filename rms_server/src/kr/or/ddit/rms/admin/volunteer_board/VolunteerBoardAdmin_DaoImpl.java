package kr.or.ddit.rms.admin.volunteer_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class VolunteerBoardAdmin_DaoImpl implements IVolunteerBoardAdmin_Dao {
	private SqlMapClient smc;
	private static VolunteerBoardAdmin_DaoImpl dao;

	private VolunteerBoardAdmin_DaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static VolunteerBoardAdmin_DaoImpl getInstance() {
		if (dao == null) {
			dao = new VolunteerBoardAdmin_DaoImpl();
		}
		return dao;
	}

	@Override
	public int deleteBoard(Volunteer_BoardVO bv) {
		int cnt = 0;
		try {
			cnt = smc.delete("volunteer_board.deleteVolunteer_board", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Volunteer_BoardVO> getAllBoardList() {
		List<Volunteer_BoardVO> list = new ArrayList<Volunteer_BoardVO>();
		try {

			list = smc.queryForList("volunteer_board.getVolunteer_boardAll");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Volunteer_BoardVO> getAllBoard_SerchList(Volunteer_BoardVO vo) {
		List<Volunteer_BoardVO> list = new ArrayList<Volunteer_BoardVO>();
		try {

			list = smc.queryForList("volunteer_board.volunteerboard_serch", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
