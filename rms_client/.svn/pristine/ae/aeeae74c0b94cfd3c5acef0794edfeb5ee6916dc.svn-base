package kr.or.ddit.rms.shelter.volunteer_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Volunteer_BoardVO;

public interface IVolunteerBoardShelter_Service extends Remote {

	public int insertBoard(Volunteer_BoardVO bv) throws RemoteException;

	public int updateBoard(Volunteer_BoardVO bv) throws RemoteException;

	public int deleteBoard(Volunteer_BoardVO bv) throws RemoteException;

	public List<Volunteer_BoardVO> getAllBoardList() throws RemoteException;

	public List<Volunteer_BoardVO> getAllBoard_SerchList(Volunteer_BoardVO vo) throws RemoteException;
	public List<Volunteer_BoardVO> getSerchList(Volunteer_BoardVO vo) throws RemoteException;


}
