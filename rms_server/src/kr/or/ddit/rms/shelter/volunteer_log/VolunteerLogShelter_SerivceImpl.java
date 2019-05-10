package kr.or.ddit.rms.shelter.volunteer_log;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Volunteer_LogVO;

public class VolunteerLogShelter_SerivceImpl extends UnicastRemoteObject implements IVolunteerLogShelter_Serivce {

	public VolunteerLogShelter_SerivceImpl() throws RemoteException {
		dao = VolunteerLogShelter_DaoImpl.getInstance();
	}

	VolunteerLogShelter_DaoImpl dao;

	@Override
	public int deleteBoard(Volunteer_LogVO bv) throws RemoteException {
		return dao.deleteBoard(bv);
	}

	@Override
	public int updateBoard(Volunteer_LogVO bv) throws RemoteException {
		return dao.updateBoard(bv);
	}

	@Override
	public List<Volunteer_LogVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo) throws RemoteException {
		return dao.getAllBoard_SerchList(vo);
	}

}
