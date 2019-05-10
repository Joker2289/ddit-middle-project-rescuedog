package kr.or.ddit.rms.user.mypage.updatecustom;

import java.util.List;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;

public interface IUpdateCustomDao {

	public int updateCustom(CustomVO vo);
	
	public List<MemberVO> getMemberAll();
	
	public int updateMember(MemberVO vo);

}
