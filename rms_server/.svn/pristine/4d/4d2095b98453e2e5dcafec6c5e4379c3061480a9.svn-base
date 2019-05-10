package kr.or.ddit.rms.member.board_class;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Board_classVO;

public interface BoardClassService extends Remote{
Board_classVO getBoardClass(Board_classVO vo) throws RemoteException;
	
	int insertBoardClass(Board_classVO vo) throws RemoteException;
	List<Board_classVO> getBoardClassAll() throws RemoteException;
	List<Board_classVO> getSearchBoardClass(Board_classVO vo) throws RemoteException;
	int updateBoardClass(Board_classVO vo) throws RemoteException;
	
}
