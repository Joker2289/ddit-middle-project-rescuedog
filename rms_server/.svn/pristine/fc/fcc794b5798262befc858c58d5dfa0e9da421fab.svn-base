package kr.or.ddit.rms.user.goods_mall;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public interface IGoodsMallUService extends Remote {
	
	List<ProdVO> getProdAll() throws RemoteException;
	
	ProdVO getSearchProd(ProdVO vo) throws RemoteException;
	
	List<CartVO> getSearchCart(CartVO vo) throws RemoteException;
	
	List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo) throws RemoteException; // 구매 기록 추가
	
	int deleteCart(String prod_num) throws RemoteException;
	
	int updateBuy_log(Buy_LogVO vo) throws RemoteException;// 환불 했을 때 수정
	
	int deleteAllCart(String custom_id) throws RemoteException;
	
	int insertCart(CartVO vo) throws RemoteException; 
	
	int updateCart(CartVO vo) throws RemoteException;
	
	int insertBuy_log(Buy_LogVO vo) throws RemoteException;
	
	List<CardVO> getCardAll() throws RemoteException;
	
	String getBuy_num() throws RemoteException;
	
	List<CustomVO> getSearchCustom(CustomVO vo) throws RemoteException;
	
	int updatePoint(CustomVO vo) throws RemoteException;
	
	List<ProdVO> getLikeProd(ProdVO vo) throws RemoteException;
}
