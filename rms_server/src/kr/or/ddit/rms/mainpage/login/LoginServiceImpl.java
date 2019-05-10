package kr.or.ddit.rms.mainpage.login;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.rms.vo.AdminVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class LoginServiceImpl extends UnicastRemoteObject implements ILoginService {

	ILoginDao dao;

	public LoginServiceImpl() throws RemoteException {
		dao = LoginDaoImpl.getInstance();
	}

	@Override
	public MemberVO getSearchMember(MemberVO vo) {
		return dao.getSearchMember(vo);
	}

	@Override
	public CustomVO getSearchCustom(CustomVO vo) {
		return dao.getSearchCustom(vo);
	}

	@Override
	public ShelterVO getSearchShelter(ShelterVO vo) {
		return dao.getSearchShelter(vo);
	}

	@Override
	public AdminVO getSearchAdmin(AdminVO vo) throws RemoteException {
		return dao.getSearchAdmin(vo);
	}

}
