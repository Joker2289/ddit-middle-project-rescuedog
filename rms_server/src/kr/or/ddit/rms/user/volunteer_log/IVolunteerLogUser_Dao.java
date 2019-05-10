package kr.or.ddit.rms.user.volunteer_log;

import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public interface IVolunteerLogUser_Dao {
	public int insertVolunteer_log(Volunteer_LogVO bv);


	public int deleteVolunteer_log(Volunteer_LogVO bv);


	public int updateVolunteer_log(Volunteer_LogVO bv);


	public List<Volunteer_LogVO> getAllBoardList();
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo);
	public List<Volunteer_LogVO> volunteerlog_serch(Volunteer_LogVO vo);

}
