package kr.or.ddit.rms.member;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class MemberDaoImpl implements MemberDao {
	private static MemberDaoImpl dao;
	private SqlMapClient smc;

	private MemberDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static MemberDaoImpl getInstance() {
		if (dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	@Override
	public List<MemberVO> getMemberAll() {
		List<MemberVO> list = null;
		try {
			list = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO vo) {
		List<MemberVO> list = null;
		try {
			list = smc.queryForList("member.getSearchMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertMember(MemberVO vo) {
		int num = 0;
		try {
			Object obj = smc.insert("member.insertMember", vo);
			if (obj == null) {
				num = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int updateMember(MemberVO vo) {
		int num = 0;
		try {
			num = smc.update("member.updateMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteMember(MemberVO vo) {
		int num = 0;
		try {
			num = smc.update("member.deleteMember", vo.getMem_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
