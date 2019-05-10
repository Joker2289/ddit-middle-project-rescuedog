package kr.or.ddit.rms.shelter.donation_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public interface IDonationBoardSService extends Remote{
	
	public int insertBoard(Board_detailVO vo) throws RemoteException;

	public int updateBoard(Board_detailVO vo) throws RemoteException;
	
	public int deleteBoard(Board_detailVO vo) throws RemoteException;

	public List<Board_detailVO> getBoard_detailAll() throws RemoteException;
	
	public List<Board_detailVO> getSearchBoard_detail(Board_detailVO vo) throws RemoteException;
	
	public List<Board_detailVO> getSearchBoard_detail_or(Board_detailVO vo) throws RemoteException;
	
	public List<Spon_LogVO> getSpon_logAll() throws RemoteException;
}