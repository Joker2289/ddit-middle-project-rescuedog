package kr.or.ddit.rms.user.mypage.updatecustom;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class UpdateCustomDaoImpl implements IUpdateCustomDao {

	private static UpdateCustomDaoImpl dao;
	private SqlMapClient smc;

	private UpdateCustomDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static UpdateCustomDaoImpl getInstance() {
		if (dao == null) {
			dao = new UpdateCustomDaoImpl();
		}
		return dao;
	}

	@Override
	public int updateCustom(CustomVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("custom.updateCustom", vo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<MemberVO> getMemberAll() {

		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			list = smc.queryForList("member.getMemberAll");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updateMember(MemberVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("member.updateMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}

}
