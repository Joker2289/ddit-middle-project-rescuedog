package kr.or.ddit.rms.user.mypage.activeList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.Spon_LogVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class ActiveListDaoImpl implements IActiveListDao {

	private static ActiveListDaoImpl dao;

	private SqlMapClient smc;

	private ActiveListDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static ActiveListDaoImpl getInstance() {
		if (dao == null) {
			dao = new ActiveListDaoImpl();
		}
		return dao;
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

	@Override
	public List<ShelterVO> getSpon_log_shelname(Spon_LogVO vo) {
		List<ShelterVO> list = null;
		try {
			list = smc.queryForList("spon_log.getSpon_log_shelname", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo) {
		List<Adopt_LogVO> list = new ArrayList<Adopt_LogVO>();
		try {
			list = smc.queryForList("adopt_log.getSearchAdopt_log", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<RescuedogVO> getSearchRescuedog(RescuedogVO vo) {
		List<RescuedogVO> list = new ArrayList<RescuedogVO>();
		try {
			list = smc.queryForList("rescuedog.getSearchRescuedog", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteAdopt_logThis(Adopt_LogVO vo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("adopt_log.deleteAdopt_logThis", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ShelterVO> getSearchShelter(ShelterVO vo) {
		List<ShelterVO> list = new ArrayList<ShelterVO>();
		try {
			list = smc.queryForList("shelter.getSearchShelter", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
