package kr.or.ddit.rms.admin.volunteer_board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Volunteer_BoardVO;

public class VolunteerBoardAdmin_ServiceImpl extends UnicastRemoteObject implements IVolunteerBoardAdmin_Service {

	VolunteerBoardAdmin_DaoImpl dao;

	public VolunteerBoardAdmin_ServiceImpl() throws RemoteException {
		dao = VolunteerBoardAdmin_DaoImpl.getInstance();
	}

	@Override
	public int deleteBoard(Volunteer_BoardVO bv) throws RemoteException {
		return dao.deleteBoard(bv);
	}

	@Override
	public List<Volunteer_BoardVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public List<Volunteer_BoardVO> getAllBoard_SerchList(Volunteer_BoardVO vo) throws RemoteException {
		return dao.getAllBoard_SerchList(vo);
	}

}
