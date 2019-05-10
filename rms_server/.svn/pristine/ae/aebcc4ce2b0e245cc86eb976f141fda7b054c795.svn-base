package kr.or.ddit.rms.mainpage.login;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.rms.vo.AdminVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class LoginDaoImpl implements ILoginDao {

	private static LoginDaoImpl dao;
	private SqlMapClient smc;

	private LoginDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static LoginDaoImpl getInstance() {
		if (dao == null) {
			dao = new LoginDaoImpl();
		}
		return dao;
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
	public AdminVO getSearchAdmin(AdminVO vo) {
		AdminVO temp = null;

		try {

			temp = (AdminVO) smc.queryForObject("admin.getSearchAdmin", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

}
