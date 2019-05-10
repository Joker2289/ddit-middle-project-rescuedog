package kr.or.ddit.rms.admin.goods_mall;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public interface IGoodsMallADao {
	
	List<ProdVO> getProdAll();
	
	String getProd_num();
	
	int insertProd(ProdVO vo);
	
	ProdVO getSearchProd(ProdVO vo);
	
	List<CartVO> getSearchCart(CartVO vo); 
	
	int deleteCart(String prod_num);
	
	int deleteAllCart(String custom_id);
	
	int insertCart(CartVO vo);
	
	int updateCart(CartVO vo);
	
	int insertBuy_log(Buy_LogVO vo);
	
	List<Buy_LogVO> getBuy_logAll();
	
	List<CardVO> getCardAll();
	
	String getBuy_num();
	
	List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo);
	
	int updateRefund_check(Buy_LogVO vo);
	
	CustomVO getSearchCustom(CustomVO vo);
	
	List<ProdVO> getLikeProd(ProdVO vo);
	
	int deleteProd(ProdVO vo);
	
	List<Buy_LogVO> getBetweenSell(Buy_LogVO vo);
}


