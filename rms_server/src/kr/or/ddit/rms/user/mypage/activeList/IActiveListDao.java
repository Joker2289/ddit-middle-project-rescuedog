package kr.or.ddit.rms.user.mypage.activeList;

import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public interface IActiveListDao {
	List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo);
	
	List<ShelterVO> getSpon_log_shelname(Spon_LogVO vo);
	
	List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo);
	
	List<RescuedogVO> getSearchRescuedog(RescuedogVO vo);
	
	int deleteAdopt_logThis(Adopt_LogVO vo);
	
	List<ShelterVO> getSearchShelter(ShelterVO vo);
	
}
