package kr.or.ddit.rms.user.mypage.updatecustom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;


public class UpdateCustomServiceImpl extends UnicastRemoteObject implements IUpdateCustomService{


	IUpdateCustomDao dao;
	
	
	public UpdateCustomServiceImpl() throws RemoteException {
		dao = UpdateCustomDaoImpl.getInstance();
	}	


	@Override
	public int updateCustom(CustomVO vo) {
		return dao.updateCustom(vo);
	}


	@Override
	public List<MemberVO> getMemberAll() throws RemoteException {
		return dao.getMemberAll();
	}


	@Override
	public int updateMember(MemberVO vo) throws RemoteException {
		return dao.updateMember(vo);
	}


}
