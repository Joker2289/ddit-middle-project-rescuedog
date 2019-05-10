package kr.or.ddit.rms.shelter.adopt_board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.Adopt_LogVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.RescuedogVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class AdoptBoardShelServiceImpl extends UnicastRemoteObject implements IAdoptBoardShelService {

	IAdoptBoardShelDao dao;

	public AdoptBoardShelServiceImpl() throws RemoteException {
		dao = AdoptBoardShelDaoImpl.getInstance();
	}

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
	public String getRd_num() throws RemoteException {
		return dao.getRd_num();
	}

	@Override
	public int insertRescuedog(RescuedogVO vo) throws RemoteException {
		return dao.insertRescuedog(vo);
	}

	@Override
	public List<Adopt_LogVO> getAdopt_logAll() throws RemoteException {
		return dao.getAdopt_logAll();
	}

	@Override
	public List<Adopt_LogVO> getSearchAdopt_log(Adopt_LogVO vo) throws RemoteException {
		return dao.getSearchAdopt_log(vo);
	}

	@Override
	public List<CustomVO> getSearchCustom(CustomVO vo) throws RemoteException {
		return dao.getSearchCustom(vo);
	}

	@Override
	public int Adopt_Request_Ok(Adopt_LogVO vo) throws RemoteException {
		return dao.Adopt_Request_Ok(vo);
	}

	@Override
	public int Adopt_Request_Cancel(Adopt_LogVO vo) throws RemoteException {
		return dao.Adopt_Request_Cancel(vo);
		
	}

	@Override
	public int updateRescuedogRd_check(RescuedogVO vo) throws RemoteException {
		return dao.updateRescuedogRd_check(vo);
	}

}
