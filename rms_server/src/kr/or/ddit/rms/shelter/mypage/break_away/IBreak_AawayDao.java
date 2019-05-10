package kr.or.ddit.rms.shelter.mypage.break_away;

import java.util.List;

import kr.or.ddit.rms.vo.MemberVO;

public interface IBreak_AawayDao{
	
	public int deleteMember(String id);
	
	public List<MemberVO> getMemberAll();
	
}
