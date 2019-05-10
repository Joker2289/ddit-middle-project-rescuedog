package kr.or.ddit.rms.user.mypage.volunteer_log;

import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public interface IVolunteerLogUser_Dao {


	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo);

}
