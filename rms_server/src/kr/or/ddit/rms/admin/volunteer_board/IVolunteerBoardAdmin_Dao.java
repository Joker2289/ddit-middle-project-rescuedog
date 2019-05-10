package kr.or.ddit.rms.admin.volunteer_board;

import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;

public interface IVolunteerBoardAdmin_Dao {
	



	public int deleteBoard(Volunteer_BoardVO bv);




	public List<Volunteer_BoardVO> getAllBoardList();
	public List<Volunteer_BoardVO> getAllBoard_SerchList(Volunteer_BoardVO vo);



}
