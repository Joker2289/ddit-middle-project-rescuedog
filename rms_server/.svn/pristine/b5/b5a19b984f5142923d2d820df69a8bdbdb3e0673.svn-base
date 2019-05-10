package kr.or.ddit.rms.shelter.adopt_board;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public interface IAdoptBoardShelDao {
	

	public int insertAdopt_log(Adopt_LogVO vo);
	
	public int updateAdopt_log(Adopt_LogVO vo);
	
	public int deleteAdopt_log(Adopt_LogVO vo);

	public List<RescuedogVO> getRescuedogAll();
	
	public List<RescuedogVO> getSearchRescuedog(RescuedogVO vo);
	
	public List<ShelterVO> getSearchShelter(ShelterVO vo);
	
	public String getRd_num();
	
	public int insertRescuedog(RescuedogVO vo);
	
	public List<Adopt_LogVO> getAdopt_logAll();
	
	public List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo);
	
	public List<CustomVO> getSearchCustom(CustomVO vo);
	
	public int Adopt_Request_Ok(Adopt_LogVO vo);
	
	public int Adopt_Request_Cancel(Adopt_LogVO vo);
	
	public int updateRescuedogRd_check(RescuedogVO vo);
	
}
