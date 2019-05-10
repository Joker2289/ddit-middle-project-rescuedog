package kr.or.ddit.rms.mainpage.find_id;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class FindIdServiceImpl extends UnicastRemoteObject implements IFindIdService {

	IFindIdDao dao;

	public FindIdServiceImpl() throws RemoteException {
		dao = FindIdDaoImpl.getInstance();
	}

	@Override
	public CustomVO getSearchCustom(CustomVO vo) {
		return dao.getSearchCustom(vo);
	}

	@Override
	public ShelterVO getSearchShelter(ShelterVO vo) {
		return dao.getSearchShelter(vo);
	}

}
