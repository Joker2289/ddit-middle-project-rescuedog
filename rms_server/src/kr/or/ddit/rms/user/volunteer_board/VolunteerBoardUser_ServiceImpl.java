package kr.or.ddit.rms.user.volunteer_board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;

public class VolunteerBoardUser_ServiceImpl extends UnicastRemoteObject implements IVolunteerBoardUser_Service {

	VolunteerBoardUser_DaoImpl dao;

	public VolunteerBoardUser_ServiceImpl() throws RemoteException {
		dao = VolunteerBoardUser_DaoImpl.getInstance();
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
	public int insertVolunteer_board(Volunteer_BoardVO bv) throws RemoteException {
		return dao.insertVolunteer_board(bv);
	}

	@Override
	public int deleteVolunteer_board(String bv) throws RemoteException {
		return dao.deleteVolunteer_board(bv);
	}

	@Override
	public int updateVolunteer_board(Volunteer_BoardVO bv) throws RemoteException {
		return dao.updateVolunteer_board(bv);
	}

	// @Override
	// public List<CustomVO> getAllCustom_SerchList(CustomVO vo) throws
	// RemoteException {
	// return dao.getAllCustom_SerchList(vo);
	// }

	@Override
	public List<CustomVO> getCustomAll() throws RemoteException {
		return dao.getCustomAll();
	}

	@Override
	public List<Volunteer_BoardVO> getSerchList(Volunteer_BoardVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.getSerchList(vo);
	}




	

}
