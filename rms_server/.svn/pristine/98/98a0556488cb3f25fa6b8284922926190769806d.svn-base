package kr.or.ddit.rms.user.volunteer_log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Volunteer_LogVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class VolunteerLogUser_DaoImpl implements IVolunteerLogUser_Dao {
	private SqlMapClient smc;
	private static VolunteerLogUser_DaoImpl dao;

	private VolunteerLogUser_DaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static VolunteerLogUser_DaoImpl getInstance() {
		if (dao == null) {
			dao = new VolunteerLogUser_DaoImpl();
		}
		return dao;
	}

	@Override
	public int insertVolunteer_log(Volunteer_LogVO bv) {
		int cnt = 0;
		try {

			Object obj = smc.insert("volunteer_log.insertVolunteer_log", bv);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteVolunteer_log(Volunteer_LogVO bv) {
		int cnt = 0;
		try {
			cnt = smc.delete("volunteer_log.deleteVolunteer_log", bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateVolunteer_log(Volunteer_LogVO bv) {
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

			list = smc.queryForList("volunteer_log.getSearchVolunteer_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public List<Volunteer_LogVO> volunteerlog_serch(Volunteer_LogVO vo) {
		List<Volunteer_LogVO> list = new ArrayList<Volunteer_LogVO>();
		try {
			
			list = smc.queryForList("volunteer_log.volunteerlog_serch", vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
