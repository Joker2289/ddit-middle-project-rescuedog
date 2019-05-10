package kr.or.ddit.rms.shelter.mypage.updateshelter;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class UpdateShelterDaoImpl implements IUpdateShelterDao {

	private static UpdateShelterDaoImpl dao;
	private SqlMapClient smc;

	private UpdateShelterDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static UpdateShelterDaoImpl getInstance() {
		if (dao == null) {
			dao = new UpdateShelterDaoImpl();
		}
		return dao;
	}

	@Override
	public int updateShelter(ShelterVO vo) {
		int cnt = 0;

		try {
			cnt = smc.update("shelter.updateShelter", vo);

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
