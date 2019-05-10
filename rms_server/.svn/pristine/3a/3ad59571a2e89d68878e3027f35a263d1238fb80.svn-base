package kr.or.ddit.rms.user.volunteer_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class VolunteerBoardUser_DaoImpl implements IVolunteerBoardUser_Dao {
	private SqlMapClient smc;
	private static VolunteerBoardUser_DaoImpl dao;

	private VolunteerBoardUser_DaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static VolunteerBoardUser_DaoImpl getInstance() {
		if (dao == null) {
			dao = new VolunteerBoardUser_DaoImpl();
		}
		return dao;
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
	public List<CustomVO> getCustomAll() {
		List<CustomVO> list = new ArrayList<CustomVO>();
		try {

			list = smc.queryForList("custom.getCustomAll");

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

	// @Override
	// public List<CustomVO> getAllCustom_SerchList(CustomVO vo) {
	// List<CustomVO> list = new ArrayList<CustomVO>();
	// try {
	//
	// list = smc.queryForList("custom.getSearchCustom",vo);
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return list;
	// }

	@Override
	public int insertVolunteer_board(Volunteer_BoardVO bv) {
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
	public int deleteVolunteer_board(String bv) {
		int cnt = 0;
		try {
			cnt = smc.delete("volunteer_board.deleteVolunteer_board", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateVolunteer_board(Volunteer_BoardVO bv) {
		int cnt = 0;
		try {

			cnt = smc.update("volunteer_board.updateVolunteer_board", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
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
