package kr.or.ddit.rms.user.mypage.break_away;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.MemberVO;

public class Break_AwayServiceImpl extends UnicastRemoteObject implements IBreak_AwayService{
	
	IBreak_AawayDao dao;

	public Break_AwayServiceImpl() throws RemoteException {
		dao = Break_AwayDaoImpl.getInstance();
	}

	@Override
	public int deleteMember(String id) throws RemoteException {
		return dao.deleteMember(id);
	}

	@Override
	public List<MemberVO> getMemberAll() throws RemoteException {
		return dao.getMemberAll();
	}




}
