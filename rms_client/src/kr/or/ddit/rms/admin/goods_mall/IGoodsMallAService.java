package kr.or.ddit.rms.admin.goods_mall;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public interface IGoodsMallAService extends Remote {
	
	List<ProdVO> getProdAll() throws RemoteException;
	
	String getProd_num() throws RemoteException;
	
	int insertProd(ProdVO vo) throws RemoteException;
	
	ProdVO getSearchProd(ProdVO vo) throws RemoteException;
	
	List<CartVO> getSearchCart(CartVO vo) throws RemoteException;
	
	int deleteCart(String prod_num) throws RemoteException;
	
	int deleteAllCart(String custom_id) throws RemoteException;
	
	int insertCart(CartVO vo) throws RemoteException; 
	
	int updateCart(CartVO vo) throws RemoteException;
	
	int insertBuy_log(Buy_LogVO vo) throws RemoteException;
	
	List<Buy_LogVO> getBuy_logAll() throws RemoteException; 
	
	List<CardVO> getCardAll() throws RemoteException;
	
	String getBuy_num() throws RemoteException;
	
	List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo) throws RemoteException;
	
	int updateRefund_check(Buy_LogVO vo) throws RemoteException;
	
	CustomVO getSearchCustom(CustomVO vo) throws RemoteException;
	
	List<ProdVO> getLikeProd(ProdVO vo) throws RemoteException;
	
	int deleteProd(ProdVO vo) throws RemoteException;
	
	List<Buy_LogVO> getBetweenSell(Buy_LogVO vo) throws RemoteException;
}
