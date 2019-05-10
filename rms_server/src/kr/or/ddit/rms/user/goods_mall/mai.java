package kr.or.ddit.rms.user.goods_mall;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.ProdVO;

public class mai {
	public static void main(String[] args) {
		try {
			IGoodsMallUService s = new GoodsMallUServiceImpl() ;
//			 List<ProdVO> list = s.getProdAll();
//			 
//			 CartVO cvo = new CartVO();
//			 cvo.setCustom_id("jk1");
//			 List<CartVO> cart_list = s.getSearchCart(cvo);
//			 
//			 String name = cart_list.get(0).getProd_num();
//			 String name2 = cart_list.get(1).getProd_num();
//			 String name3 = cart_list.get(2).getProd_num();
//			 ProdVO pvo = new ProdVO();
//			 
//			 pvo.setProd_name(name);
//			 
//			 ProdVO d = s.getSearchProd(pvo);
//			 
//			 System.out.println(name);
//			 System.out.println(name2);
//			 System.out.println(name3);
			 
			 Buy_LogVO bvo = new Buy_LogVO();
			 
			 bvo.setBuy_num("1");
			 bvo.setBuy_quan("3");
			 bvo.setCustom_id("2");
			 bvo.setProd_num("2");
			// bvo.setCard_num("2");
			 s.insertBuy_log(bvo);
			 
			 
			 //System.out.println(list.get(0).getProd_img());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
