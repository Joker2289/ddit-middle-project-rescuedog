package kr.or.ddit.rms.shelter.volunteer_log;

import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public interface IVolunteerLogShelter_Dao {
	public int deleteBoard(Volunteer_LogVO bv);


	public int updateBoard(Volunteer_LogVO bv);


	public List<Volunteer_LogVO> getAllBoardList();
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo);


}
