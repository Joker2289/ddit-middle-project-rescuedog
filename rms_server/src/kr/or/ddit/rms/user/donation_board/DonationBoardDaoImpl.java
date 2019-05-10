package kr.or.ddit.rms.user.donation_board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class DonationBoardDaoImpl implements IDonationBoardDao {

	private static DonationBoardDaoImpl dao;

	private SqlMapClient smc;

	private DonationBoardDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static DonationBoardDaoImpl getInstance() {
		if (dao == null) {
			dao = new DonationBoardDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertSpon_log(Spon_LogVO vo) {
		int cnt = 0;
		try {

			Object obj = smc.insert("spon_log.insertSpon_log", vo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateSpon_log(Spon_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("spon_log.updateSpon_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteSpon_log(Spon_LogVO vo) {
		int cnt = 0;
		try {
			cnt = smc.delete("spon_log.deleteSpon_log", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Spon_LogVO> getSpon_logAll() {
		List<Spon_LogVO> list = new ArrayList<Spon_LogVO>();
		try {
			list = smc.queryForList("spon_log.getSpon_logAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) {
		List<Spon_LogVO> list = null;
		try {
			list = smc.queryForList("spon_log.getSearchSpon_log", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateCustom(CustomVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("custom.updateCustom", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertSpon(SponVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("spon.insertSpon", vo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateSpon(SponVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("spon.updateSpon", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteSpon(SponVO vo) {
		int cnt = 0;
		try {
			cnt = smc.delete("spon.deleteSpon", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SponVO> getSponAll() {
		List<SponVO> list = new ArrayList<SponVO>();
		try {
			list = smc.queryForList("spon.getSponAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SponVO> getSearchSponText(SponVO vo) {
		List<SponVO> list = null;
		try {
			list = smc.queryForList("spon.getSearchSponText");
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
	public List<CardVO> getCardAll() {
		List<CardVO> list = new ArrayList<CardVO>();
		try {
			list = smc.queryForList("card.getCardAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
