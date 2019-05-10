package kr.or.ddit.rms.shelter.volunteer_log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Volunteer_LogVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class VolunteerLogShelter_DaoImpl implements IVolunteerLogShelter_Dao {
	private SqlMapClient smc;
	private static VolunteerLogShelter_DaoImpl dao;

	private VolunteerLogShelter_DaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static VolunteerLogShelter_DaoImpl getInstance() {
		if (dao == null) {
			dao = new VolunteerLogShelter_DaoImpl();
		}
		return dao;
	}

	@Override
	public int deleteBoard(Volunteer_LogVO bv) {
		int cnt = 0;
		try {
			cnt = smc.delete("volunteer_log.deleteVolunteer_log", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(Volunteer_LogVO bv) {
		int cnt = 0;
		try {

			cnt = smc.update("volunteer_log.updateVolunteer_log", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Volunteer_LogVO> getAllBoardList() {
		List<Volunteer_LogVO> list = new ArrayList<Volunteer_LogVO>();
		try {

			list = smc.queryForList("volunteer_log.getVolunteer_logAll");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo) {
		List<Volunteer_LogVO> list = new ArrayList<Volunteer_LogVO>();
		try {

			list = smc.queryForList("volunteer_log.volunteerlog_serch", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
