package kr.or.ddit.rms.user.volunteer_log;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public class VolunteerLogUser_ServiceImpl extends UnicastRemoteObject implements IVolunteerLogUser_Service{

	VolunteerLogUser_DaoImpl dao;
	public VolunteerLogUser_ServiceImpl() throws RemoteException {
		dao = VolunteerLogUser_DaoImpl.getInstance();
	}	
	
	@Override
	public int insertVolunteer_log(Volunteer_LogVO bv) throws RemoteException {
		return dao.insertVolunteer_log(bv);
	}

	@Override
	public int deleteVolunteer_log(Volunteer_LogVO logNo) throws RemoteException {
		return dao.deleteVolunteer_log(logNo);
	}

	@Override
	public int updateVolunteer_log(Volunteer_LogVO bv) throws RemoteException {
		return dao.updateVolunteer_log(bv);
	}

	@Override
	public List<Volunteer_LogVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo) throws RemoteException {
		return dao.getAllBoard_SerchList(vo);
	}

	@Override
	public List<Volunteer_LogVO> volunteerlog_serch(Volunteer_LogVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.volunteerlog_serch(vo);
	}


}
