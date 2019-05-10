package kr.or.ddit.rms.user.goods_mall;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;

public interface IGoodsMallUDao {
	
	List<ProdVO> getProdAll();
	
	ProdVO getSearchProd(ProdVO vo);
	
	List<CartVO> getSearchCart(CartVO vo);
	
	List<Buy_LogVO> getSearchBuy_log(Buy_LogVO vo); // 구매 기록 추가
	
	int updateBuy_log(Buy_LogVO vo); // 환불 했을 때 수정
	
	int deleteCart(String prod_num);
	
	int deleteAllCart(String custom_id);
	
	int insertCart(CartVO vo);
	
	int updateCart(CartVO vo);
	
	int insertBuy_log(Buy_LogVO vo);
	
	List<CardVO> getCardAll();
	
	String getBuy_num();
	
	List<CustomVO> getSearchCustom(CustomVO vo);
	
	int updatePoint(CustomVO vo);
	
	List<ProdVO> getLikeProd(ProdVO vo);
}
