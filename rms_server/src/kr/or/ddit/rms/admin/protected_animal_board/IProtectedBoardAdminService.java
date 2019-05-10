package kr.or.ddit.rms.admin.protected_animal_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Protected_boardVO;

public interface IProtectedBoardAdminService extends Remote{
	




	public int deleteBoard(Protected_boardVO bv) throws RemoteException;
	
	public List<Protected_boardVO> getAllBoardList() throws RemoteException;
	
	public List<Protected_boardVO> getAllBoard_SerchList(Protected_boardVO vo) throws RemoteException;
	public List<Protected_boardVO> getprotectedTextSearch(Protected_boardVO vo) throws RemoteException;


}
