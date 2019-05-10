package kr.or.ddit.rms.user.mypage.buylist;

import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CustomVO;

public interface IBuyListDao {

	public List<Buy_LogVO> getBuy_logAll();

	public List<Buy_LogVO> getBetweenProd(Buy_LogVO vo);

	public int updateBuy_log(Buy_LogVO vo);

	public int deleteBuy_log(String vo);
	
	public List<Buy_LogVO> getBuyTo(String custom_id);
	
	public List<CustomVO> getCustomAll();
	 
	public List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo); // 구매 기록 추가
}

