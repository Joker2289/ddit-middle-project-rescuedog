package kr.or.ddit.rms.user.volunteer_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public interface IVolunteerBoardUser_Service extends Remote{
	public int insertVolunteer_board(Volunteer_BoardVO bv) throws RemoteException;


	public int deleteVolunteer_board(String bv) throws RemoteException;


	public int updateVolunteer_board(Volunteer_BoardVO bv) throws RemoteException;

//	public List<CustomVO> getAllCustom_SerchList(CustomVO vo) throws RemoteException; 
	
	public List<CustomVO> getCustomAll() throws RemoteException; 

	
	public List<Volunteer_BoardVO> getAllBoardList() throws RemoteException;
	
	public List<Volunteer_BoardVO> getAllBoard_SerchList(Volunteer_BoardVO vo) throws RemoteException;
	public List<Volunteer_BoardVO> getSerchList(Volunteer_BoardVO vo) throws RemoteException;

}
