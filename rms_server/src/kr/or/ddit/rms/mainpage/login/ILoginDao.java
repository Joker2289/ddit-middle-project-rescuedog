package kr.or.ddit.rms.mainpage.login;

import kr.or.ddit.rms.vo.AdminVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public interface ILoginDao {
	
	MemberVO getSearchMember(MemberVO vo);
	
	CustomVO getSearchCustom(CustomVO vo); 
	
	ShelterVO getSearchShelter(ShelterVO vo);
	
	AdminVO getSearchAdmin(AdminVO vo);
}
