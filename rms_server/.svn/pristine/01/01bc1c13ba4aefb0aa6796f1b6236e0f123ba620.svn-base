package kr.or.ddit.rms.shelter.mypage.updateshelter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class UpdateShelterServiceImpl extends UnicastRemoteObject implements IUpdateShelterService {

	IUpdateShelterDao dao;

	public UpdateShelterServiceImpl() throws RemoteException {
		dao = UpdateShelterDaoImpl.getInstance();
	}

	@Override
	public List<MemberVO> getMemberAll() throws RemoteException {
		return dao.getMemberAll();
	}

	@Override
	public int updateMember(MemberVO vo) throws RemoteException {
		return dao.updateMember(vo);
	}

	@Override
	public int updateShelter(ShelterVO vo) throws RemoteException {
		return dao.updateShelter(vo);
	}

}
