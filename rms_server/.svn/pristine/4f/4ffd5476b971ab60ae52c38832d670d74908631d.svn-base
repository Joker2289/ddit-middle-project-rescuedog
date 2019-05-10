package kr.or.ddit.rms.member.report;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class BlacklistDaoImpl implements BlacklistDao {
	private static BlacklistDaoImpl dao;
	private SqlMapClient smc;

	private BlacklistDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static BlacklistDaoImpl getInstance() {
		if (dao == null) {
			dao = new BlacklistDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BlacklistVO> getBlacklistAll() {
		List<BlacklistVO> list = null;
		try {
			list = smc.queryForList("blacklist.getBlacklistAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BlacklistVO> getSearchBlacklist(BlacklistVO vo) {
		List<BlacklistVO> list = null;
		try {
			list = smc.queryForList("blacklist.getSearchBlacklist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertBlacklist(BlacklistVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("blacklist.insertBlacklist", vo);
			if (obj == null) {
				num = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updateBlacklist(BlacklistVO vo) {
		int num = 0;
		try {
			num = smc.update("blacklist.updateBlacklist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteBlacklist(BlacklistVO vo) {
		int num = 0;
		try {
			num = smc.delete("blacklist.deleteBlacklist", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
