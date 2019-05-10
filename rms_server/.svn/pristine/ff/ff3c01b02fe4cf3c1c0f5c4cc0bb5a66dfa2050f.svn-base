package kr.or.ddit.rms.shelter.volunteer_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class VolunteerBoardShelter_DaoImpl implements IVolunteerBoardShelter_Dao {
	private SqlMapClient smc;
	private static VolunteerBoardShelter_DaoImpl dao;

	private VolunteerBoardShelter_DaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static VolunteerBoardShelter_DaoImpl getInstance() {
		if (dao == null) {
			dao = new VolunteerBoardShelter_DaoImpl();
		}
		return dao;
	}

	@Override
	public int insertBoard(Volunteer_BoardVO bv) {
		int cnt = 0;
		try {

			Object obj = smc.insert("volunteer_board.insertVolunteer_board", bv);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
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
	public int updateBoard(Volunteer_BoardVO bv) {
		int cnt = 0;
		try {

			cnt = smc.update("volunteer_board.updateVolunteer_board", bv);

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

			list = smc.queryForList("volunteer_board.getSearchVolunteer_board", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public List<Volunteer_BoardVO> getSerchList(Volunteer_BoardVO vo) {
		List<Volunteer_BoardVO> list = new ArrayList<Volunteer_BoardVO>();
		try {

			list = smc.queryForList("volunteer_board.volunteerboard_serch", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
