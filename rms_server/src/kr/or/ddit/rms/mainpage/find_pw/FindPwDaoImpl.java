package kr.or.ddit.rms.mainpage.find_pw;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class FindPwDaoImpl implements IFindPwDao {

	private static FindPwDaoImpl dao;
	private SqlMapClient smc;

	private FindPwDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static FindPwDaoImpl getInstance() {
		if (dao == null) {
			dao = new FindPwDaoImpl();
		}
		return dao;
	}

	@Override
	public CustomVO getSearchCustom(CustomVO vo) {

		CustomVO temp = null;

		try {

			temp = (CustomVO) smc.queryForObject("custom.getSearchCustom", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public ShelterVO getSearchShelter(ShelterVO vo) {
		ShelterVO temp = null;

		try {

			temp = (ShelterVO) smc.queryForObject("shelter.getSearchShelter", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public MemberVO getSearchMember(MemberVO vo) {
		MemberVO temp = null;

		try {
			temp = (MemberVO) smc.queryForObject("member.getSearchMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public int updateMember(MemberVO vo) {
		int temp = 0;

		try {

			temp = smc.update("member.updateMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

}
