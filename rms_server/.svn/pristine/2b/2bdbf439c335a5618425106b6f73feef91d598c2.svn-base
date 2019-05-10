package kr.or.ddit.rms.user.mypage.activeList;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public class ActiveListServiceImpl extends UnicastRemoteObject implements IActiveListService, Serializable {

	IActiveListDao dao;

	public ActiveListServiceImpl() throws RemoteException {
		dao = ActiveListDaoImpl.getInstance();
	}

	@Override
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) throws RemoteException {
		return dao.getSearchSpon_log(vo);
	}

	@Override
	public List<ShelterVO> getSpon_log_shelname(Spon_LogVO vo) throws RemoteException {
		return dao.getSpon_log_shelname(vo);
	}

	
	@Override
	public List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo) throws RemoteException {
		return dao.getSearchAdopt_log(vo);
	}

	@Override
	public List<RescuedogVO> getSearchRescuedog(RescuedogVO vo) throws RemoteException {
		return dao.getSearchRescuedog(vo);
	}

	@Override
	public int deleteAdopt_logThis(Adopt_LogVO vo) throws RemoteException {
		return dao.deleteAdopt_logThis(vo);
	}

	@Override
	public List<ShelterVO> getSearchShelter(ShelterVO vo) throws RemoteException {
		return dao.getSearchShelter(vo);
	}
}
