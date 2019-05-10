package kr.or.ddit.rms.user.adopt_board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class AdoptBoardUserServiceImpl extends UnicastRemoteObject implements IAdoptBoardUserService {

	IAdoptBoardUserDao dao;

	public AdoptBoardUserServiceImpl() throws RemoteException {
		dao = AdoptBoardUserDaoImpl.getInstance();
	}

	/*
	 * @Override public int insertRescuedog(RescuedogVO vo) throws RemoteException {
	 * return dao.insertRescuedog(vo); }
	 * 
	 * @Override public int updateRescuedog(RescuedogVO vo) throws RemoteException {
	 * return dao.updateRescuedog(vo); }
	 * 
	 * @Override public int deleterescuedog(RescuedogVO vo) throws RemoteException {
	 * return dao.deleterescuedog(vo); }
	 */
	@Override
	public List<RescuedogVO> getRescuedogAll() throws RemoteException {
		return dao.getRescuedogAll();
	}

	@Override
	public List<RescuedogVO> getSearchRescuedog(RescuedogVO vo) throws RemoteException {
		return dao.getSearchRescuedog(vo);
	}

	@Override
	public int insertAdopt_log(Adopt_LogVO vo) throws RemoteException {
		return dao.insertAdopt_log(vo);
	}

	@Override
	public int updateAdopt_log(Adopt_LogVO vo) throws RemoteException {
		return dao.updateAdopt_log(vo);
	}

	@Override
	public int deleteAdopt_log(Adopt_LogVO vo) throws RemoteException {
		return dao.deleteAdopt_log(vo);
	}

	@Override
	public List<ShelterVO> getSearchShelter(ShelterVO vo) throws RemoteException {
		return dao.getSearchShelter(vo);
	}

	@Override
	public int deleteAdopt_logThis(Adopt_LogVO vo) throws RemoteException {
		return dao.deleteAdopt_logThis(vo);
	}

}
