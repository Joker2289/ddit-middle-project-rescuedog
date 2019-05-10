package kr.or.ddit.rms.user.mypage.volunteer_log;

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
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo) {
		List<Volunteer_LogVO> list = new ArrayList<Volunteer_LogVO>();
		try {

			list = smc.queryForList("volunteer_log.getSearchVolunteer_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
