package kr.or.ddit.rms.admin.adopt_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public interface IAdoptBoardAdminService extends Remote {
	
	public int deleterescuedog(RescuedogVO vo) throws RemoteException;
	
	public int insertAdopt_log(Adopt_LogVO vo) throws RemoteException;
	
	public int updateAdopt_log(Adopt_LogVO vo) throws RemoteException;
	
	public int deleteAdopt_log(Adopt_LogVO vo) throws RemoteException;

	public List<RescuedogVO> getRescuedogAll() throws RemoteException;
	
	public List<RescuedogVO> getSearchRescuedog(RescuedogVO vo) throws RemoteException;
	
	public List<ShelterVO> getSearchShelter(ShelterVO vo) throws RemoteException;
	
	public String getRd_num() throws RemoteException;
	
	public int insertRescuedog(RescuedogVO vo) throws RemoteException;
	
	public List<Adopt_LogVO> getAdopt_logAll() throws RemoteException;
	
	public List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo) throws RemoteException;
	
	public List<CustomVO> getSearchCustom(CustomVO vo) throws RemoteException;
	
	public int Adopt_Request_Ok(Adopt_LogVO vo) throws RemoteException;
	
	public int Adopt_Request_Cancel(Adopt_LogVO vo) throws RemoteException;
	
	public int updateRescuedogRd_check(RescuedogVO vo) throws RemoteException;

}
