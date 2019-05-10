package kr.or.ddit.rms.member;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.MemberVO;

public class MemberServiceImpl extends UnicastRemoteObject implements MemberService {
	public MemberServiceImpl() throws RemoteException {
		dao = MemberDaoImpl.getInstance();
	}

	MemberDao dao;

	@Override
	public List<MemberVO> getMemberAll() throws RemoteException {
		return dao.getMemberAll();
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO vo) throws RemoteException {
		return dao.getSearchMember(vo);
	}

	@Override
	public int insertMember(MemberVO vo) throws RemoteException {
		return dao.insertMember(vo);
	}

	@Override
	public int updateMember(MemberVO vo) throws RemoteException {
		return dao.updateMember(vo);
	}

	@Override
	public int deleteMember(MemberVO vo) throws RemoteException {
		return dao.deleteMember(vo);
	}

}
