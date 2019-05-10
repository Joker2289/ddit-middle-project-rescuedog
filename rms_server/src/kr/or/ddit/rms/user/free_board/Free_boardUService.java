package kr.or.ddit.rms.user.free_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.BlacklistVO;
import kr.or.ddit.rms.vo.Board_detailVO;

public interface Free_boardUService extends Remote{
	List<Board_detailVO> getBoard_detailAll() throws RemoteException;
	List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) throws RemoteException;
	List<Board_detailVO> getFree_detailTextSearch(Board_detailVO vo) throws RemoteException;
	int insertBoard_detail(Board_detailVO vo) throws RemoteException;
	int updateBoard_detail(Board_detailVO vo) throws RemoteException;
	int deleteBoard_detail(Board_detailVO vo) throws RemoteException;
}
