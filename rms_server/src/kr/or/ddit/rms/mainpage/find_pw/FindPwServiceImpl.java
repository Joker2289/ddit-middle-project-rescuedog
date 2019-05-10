package kr.or.ddit.rms.mainpage.find_pw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class FindPwServiceImpl extends UnicastRemoteObject implements IFindPwService {

	IFindPwDao dao;

	public FindPwServiceImpl() throws RemoteException {
		dao = FindPwDaoImpl.getInstance();
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
	public MemberVO getSearchMember(MemberVO vo) {
		return dao.getSearchMember(vo);
	}

	@Override
	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

}
