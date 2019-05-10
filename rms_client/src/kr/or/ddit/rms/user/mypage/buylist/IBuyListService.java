package kr.or.ddit.rms.user.mypage.buylist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CustomVO;

public interface IBuyListService extends Remote{

	public List<Buy_LogVO> getBuy_logAll() throws RemoteException; 

	public List<Buy_LogVO> getBetweenProd(Buy_LogVO vo) throws RemoteException;
	
	public List<Buy_LogVO> getBuyTo(String custom_id) throws RemoteException;

	public int updateBuy_log(Buy_LogVO vo) throws RemoteException;

	public int deleteBuy_log(String vo) throws RemoteException;
	
	public List<CustomVO> getCustomAll() throws RemoteException; 
	
	public List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo) throws RemoteException; // 구매 기록 추가
}
