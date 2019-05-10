package kr.or.ddit.rms.shelter.volunteer_board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;

public class VolunteerBoardShelter_ServiceImpl extends UnicastRemoteObject implements IVolunteerBoardShelter_Service{

	VolunteerBoardShelter_DaoImpl dao;
	public VolunteerBoardShelter_ServiceImpl() throws RemoteException {
		dao = VolunteerBoardShelter_DaoImpl.getInstance();
	}	
	
	@Override
	public int insertBoard(Volunteer_BoardVO bv) throws RemoteException {
		return dao.insertBoard(bv);
	}

	@Override
	public int deleteBoard(Volunteer_BoardVO bv) throws RemoteException {
		return dao.deleteBoard(bv);
	}

	@Override
	public int updateBoard(Volunteer_BoardVO bv) throws RemoteException {
		return dao.updateBoard(bv);
	}

	@Override
	public List<Volunteer_BoardVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public List<Volunteer_BoardVO> getAllBoard_SerchList(Volunteer_BoardVO vo) throws RemoteException {
		return dao.getAllBoard_SerchList(vo);
	}

	@Override
	public List<Volunteer_BoardVO> getSerchList(Volunteer_BoardVO vo) throws RemoteException {
		return dao.getSerchList(vo);
	}


}
