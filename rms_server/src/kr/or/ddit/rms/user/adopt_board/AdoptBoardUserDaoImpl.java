package kr.or.ddit.rms.user.adopt_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class AdoptBoardUserDaoImpl implements IAdoptBoardUserDao {

	private static AdoptBoardUserDaoImpl dao;
	private SqlMapClient smc;

	private AdoptBoardUserDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static AdoptBoardUserDaoImpl getInstance() {
		if (dao == null) {
			dao = new AdoptBoardUserDaoImpl();
		}
		return dao;
	}

	/*
	 * @Override public int insertRescuedog(RescuedogVO vo) { int cnt = 0; try {
	 * 
	 * Object obj = smc.insert("rescuedog.insertRescuedog", vo); if (obj == null) {
	 * cnt = 1; }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return cnt; }
	 * 
	 * 
	 * 
	 * @Override public int updateRescuedog(RescuedogVO vo) { int cnt = 0; try { cnt
	 * = smc.update("rescuedog.updateRescuedog", vo);
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return cnt; }
	 * 
	 * @Override public int deleterescuedog(RescuedogVO vo) { int cnt = 0; try { cnt
	 * = smc.delete("rescuedog.deleterescuedog", vo);
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return cnt; }
	 */

	@Override
	public List<RescuedogVO> getRescuedogAll() {
		List<RescuedogVO> list = new ArrayList<RescuedogVO>();
		try {
			list = smc.queryForList("rescuedog.getRescuedogAll");
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
	public int insertAdopt_log(Adopt_LogVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("adopt_log.insertAdopt_log", vo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateAdopt_log(Adopt_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("adopt_log.updateAdopt_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteAdopt_log(Adopt_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.delete("adopt_log.deleteAdopt_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<ShelterVO> getSearchShelter(ShelterVO vo) {
		List<ShelterVO> list = null;
		try {
			list = smc.queryForList("shelter.getSearchShelter", vo);
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

}
