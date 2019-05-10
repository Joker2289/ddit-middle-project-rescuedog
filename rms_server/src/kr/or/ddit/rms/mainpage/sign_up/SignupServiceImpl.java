package kr.or.ddit.rms.mainpage.sign_up;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class SignupServiceImpl extends UnicastRemoteObject implements ISignupService {

	ISignupDao dao;

	public SignupServiceImpl() throws RemoteException {
		dao = SignupDaoImpl.getInstance();
	}

	@Override
	public int insertCustom(CustomVO vo) throws RemoteException {
		return dao.insertCustom(vo);
	}

	@Override
	public int insertMember(MemberVO vo) throws RemoteException {
		return dao.insertMember(vo);
	}

	@Override
	public int insertShelter(ShelterVO vo) throws RemoteException {
		return dao.insertShelter(vo);

	}

	@Override
	public List<ShelterVO> getShelterAll() throws RemoteException {
		return dao.getShelterAll();
	}

	@Override
	public List<CustomVO> getCustomAll() throws RemoteException {
		return dao.getCustomAll();
	}

	@Override
	public List<MemberVO> getMemberAll() throws RemoteException {
		return dao.getMemberAll();
	}
}
