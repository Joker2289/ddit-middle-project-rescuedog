package kr.or.ddit.rms.member.report;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rms.vo.BlacklistVO;

public class BlacklistServiceImpl extends UnicastRemoteObject implements BlacklistService {
	BlacklistDao dao;

	public BlacklistServiceImpl() throws RemoteException {
		dao = BlacklistDaoImpl.getInstance();
	}

	@Override
	public List<BlacklistVO> getBlacklistAll() throws RemoteException {
		return dao.getBlacklistAll();
	}

	@Override
	public List<BlacklistVO> getSearchBlacklist(BlacklistVO vo) throws RemoteException {
		return dao.getSearchBlacklist(vo);
	}

	@Override
	public int insertBlacklist(BlacklistVO vo) throws RemoteException {
		return dao.insertBlacklist(vo);
	}

	@Override
	public int updateBlacklist(BlacklistVO vo) throws RemoteException {
		return dao.updateBlacklist(vo);
	}

	@Override
	public int deleteBlacklist(BlacklistVO vo) throws RemoteException {
		return dao.deleteBlacklist(vo);
	}

}
