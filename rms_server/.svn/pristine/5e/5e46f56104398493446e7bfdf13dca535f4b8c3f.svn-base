package kr.or.ddit.rms.shelter.donation_board;

import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public interface IDonationBoardSDao{
	
	public int insertBoard(Board_detailVO vo);

	public int deleteBoard(Board_detailVO vo);

	public int updateBoard(Board_detailVO vo);
	
	public int insertSpon_log(Spon_LogVO vo);
	
	public int updateSpon_log(Spon_LogVO vo);
	
	public int deleteSpon_log(Spon_LogVO vo);
	
	public List<Spon_LogVO> getSpon_logAll();
	
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo);
	
	public List<Board_detailVO> getBoard_detailAll();
	
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo);
	
	public List<Board_detailVO> getSearchBoard_detail_or(Board_detailVO vo);

}
