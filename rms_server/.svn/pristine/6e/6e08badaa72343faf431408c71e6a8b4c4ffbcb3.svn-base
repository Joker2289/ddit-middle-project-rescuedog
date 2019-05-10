package kr.or.ddit.rms.shelter.adopt_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class AdoptBoardShelDaoImpl implements IAdoptBoardShelDao {

	private static AdoptBoardShelDaoImpl dao;

	private SqlMapClient smc;

	private AdoptBoardShelDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static AdoptBoardShelDaoImpl getInstance() {
		if (dao == null) {
			dao = new AdoptBoardShelDaoImpl();
		}
		return dao;
	}

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
	public String getRd_num() {
		String log = null;

		try {
			log = (String) smc.queryForObject("rescuedog.getRd_num");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;

	}

	@Override
	public int insertRescuedog(RescuedogVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("rescuedog.insertRescuedog", vo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Adopt_LogVO> getAdopt_logAll() {
		List<Adopt_LogVO> list = null;
		try {
			list = smc.queryForList("adopt_log.getAdopt_logAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo) {
		List<Adopt_LogVO> list = null;
		try {
			list = smc.queryForList("adopt_log.getSearchAdopt_log", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CustomVO> getSearchCustom(CustomVO vo) {
		List<CustomVO> list = null;
		try {
			list = smc.queryForList("custom.getSearchCustom", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int Adopt_Request_Ok(Adopt_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("adopt_log.Adopt_Request_Ok", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int Adopt_Request_Cancel(Adopt_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("adopt_log.Adopt_Request_Cancel", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateRescuedogRd_check(RescuedogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("rescuedog.updateRescuedogRd_check", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
