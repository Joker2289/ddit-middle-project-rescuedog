package kr.or.ddit.rms.member.report;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.BlacklistVO;

public interface BlacklistService extends Remote{
	List<BlacklistVO> getBlacklistAll() throws RemoteException;
	List<BlacklistVO> getSearchBlacklist(BlacklistVO vo) throws RemoteException;
	int insertBlacklist(BlacklistVO vo) throws RemoteException;
	int updateBlacklist(BlacklistVO vo) throws RemoteException;
	int deleteBlacklist(BlacklistVO vo) throws RemoteException;
}
