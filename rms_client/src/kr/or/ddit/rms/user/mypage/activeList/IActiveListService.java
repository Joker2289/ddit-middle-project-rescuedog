package kr.or.ddit.rms.user.mypage.activeList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public interface IActiveListService extends Remote{
	List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) throws RemoteException;
	
	List<ShelterVO> getSpon_log_shelname(Spon_LogVO vo) throws RemoteException;
	
	List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo) throws RemoteException;
	
	List<RescuedogVO> getSearchRescuedog(RescuedogVO vo) throws RemoteException;

	int deleteAdopt_logThis(Adopt_LogVO vo) throws RemoteException;
	
	List<ShelterVO> getSearchShelter(ShelterVO vo) throws RemoteException;	
}
