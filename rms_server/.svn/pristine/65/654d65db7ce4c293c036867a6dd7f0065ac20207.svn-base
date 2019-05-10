package kr.or.ddit.rms.user.adopt_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public interface IAdoptBoardUserService extends Remote {
	
	//public int insertRescuedog(RescuedogVO vo) throws RemoteException;

	//public int updateRescuedog(RescuedogVO vo) throws RemoteException;
	
	//public int deleterescuedog(RescuedogVO vo) throws RemoteException;
	
	public int insertAdopt_log(Adopt_LogVO vo) throws RemoteException;
	
	public int updateAdopt_log(Adopt_LogVO vo) throws RemoteException;
	
	public int deleteAdopt_log(Adopt_LogVO vo) throws RemoteException;

	public List<RescuedogVO> getRescuedogAll() throws RemoteException;
	
	public List<RescuedogVO> getSearchRescuedog(RescuedogVO vo) throws RemoteException;
	
	public List<ShelterVO> getSearchShelter(ShelterVO vo) throws RemoteException;
	
	public int deleteAdopt_logThis(Adopt_LogVO vo) throws RemoteException;

}
