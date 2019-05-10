package kr.or.ddit.rms.user.missing_animal_board;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.MissingVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class Missing_animalDaoImpl implements IMissing_animalDao {
	private static Missing_animalDaoImpl dao;

	private SqlMapClient smc;

	private Missing_animalDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static Missing_animalDaoImpl getInstance() {
		if (dao == null) {
			dao = new Missing_animalDaoImpl();
		}
		return dao;
	}

	@Override
	public List<MissingVO> getmissingAll() {
		List<MissingVO> list = null;
		try {
			list = smc.queryForList("missing.getmissingAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertmissing(MissingVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("missing.insertmissing", vo);
			if (obj == null) {
				num = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updatemissing(MissingVO vo) {
		int num = 0;
		try {
			num = smc.update("missing.updatemissing", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deletemissing(MissingVO vo) {
		int num = 0;
		try {
			num = smc.delete("missing.deletemissing", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<MissingVO> getSearchmissing(MissingVO vo) {
		List<MissingVO> list = null;
		try {
			list = smc.queryForList("missing.getSearchmissing", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MissingVO> getmissingTextSearch(MissingVO vo) {
		List<MissingVO> list = null;
		try {
			list = smc.queryForList("missing.getmissingTextSearch", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getmissing(MissingVO vo) {
		int num = 0;
		try {
			num = (int) smc.queryForObject("missing.getmissing", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updatemissing_free(MissingVO vo) {
		int num = 0;
		try {
			num = smc.update("missing.updatemissing_free", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public String getSeq() {
		String num = null;
		try {
			String seq = (String) smc.queryForObject("missing.getSeq");
			num=seq;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
