package kr.or.ddit.rms.user.mypage.volunteer_log;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;

public class VolunteerLogMp_ServiceImpl extends UnicastRemoteObject implements IVolunteerLogMpService{

	VolunteerLogUser_DaoImpl dao;
	public VolunteerLogMp_ServiceImpl() throws RemoteException {
		dao = VolunteerLogUser_DaoImpl.getInstance();
	}	
	


	@Override
	public List<Volunteer_LogVO> getAllBoard_SerchList(Volunteer_LogVO vo) throws RemoteException {
		return dao.getAllBoard_SerchList(vo);
	}


}
