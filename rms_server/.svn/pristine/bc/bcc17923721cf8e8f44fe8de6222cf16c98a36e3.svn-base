package kr.or.ddit.rms.user.donation_board;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.SponVO;
import kr.or.ddit.rms.vo.Spon_LogVO;

public interface IDonationBoardService extends Remote{
	
	public int updateCustom(CustomVO vo) throws RemoteException;
	
	public List<CustomVO> getSearchCustom(CustomVO vo) throws RemoteException;
	
	public int insertSpon_log(Spon_LogVO vo) throws RemoteException; 
	
	public int updateSpon_log(Spon_LogVO vo) throws RemoteException; 
	
	public int deleteSpon_log(Spon_LogVO vo) throws RemoteException; 
	
	public List<Spon_LogVO> getSpon_logAll() throws RemoteException;
	
	public List<Spon_LogVO> getSearchSpon_log(Spon_LogVO vo) throws RemoteException;
	
	public int insertSpon(SponVO vo) throws RemoteException; 
	
	public int updateSpon(SponVO vo) throws RemoteException; 
	
	public int deleteSpon(SponVO vo) throws RemoteException; 
	
	public List<SponVO> getSponAll() throws RemoteException; 
	
	public List<SponVO> getSearchSponText(SponVO vo) throws RemoteException; 

	public List<CardVO> getCardAll() throws RemoteException;


}