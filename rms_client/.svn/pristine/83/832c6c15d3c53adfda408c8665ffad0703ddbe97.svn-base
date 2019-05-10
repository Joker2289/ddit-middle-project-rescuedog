package kr.or.ddit.rms.shelter.volunteer_log;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public interface IVolunteerLogShelter_Serivce extends Remote{
	public int deleteBoard(Volunteer_LogVO bv)  throws RemoteException;


	public int updateBoard(Volunteer_LogVO bv)  throws RemoteException;


	public List<Volunteer_LogVO> getAllBoardList()  throws RemoteException;
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo)  throws RemoteException;


}
