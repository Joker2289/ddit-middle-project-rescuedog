package kr.or.ddit.rms.user.volunteer_log;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Volunteer_LogVO;

public interface IVolunteerLogUser_Service extends Remote{
	public int insertVolunteer_log(Volunteer_LogVO bv) throws RemoteException;


	public int deleteVolunteer_log(Volunteer_LogVO bv) throws RemoteException;


	public int updateVolunteer_log(Volunteer_LogVO bv) throws RemoteException;


	public List<Volunteer_LogVO> getAllBoardList() throws RemoteException;
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo) throws RemoteException;
	public List<Volunteer_LogVO> volunteerlog_serch(Volunteer_LogVO vo) throws RemoteException;

}
