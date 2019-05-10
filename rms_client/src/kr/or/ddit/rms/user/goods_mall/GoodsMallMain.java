package kr.or.ddit.rms.user.goods_mall;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.ProdVO;

public class GoodsMallMain {
	
	public static void main(String[] args) {
		
		
		
		try {
			 List<ProdVO> list = Main.gm_u.getProdAll();
			 
			 CartVO cvo = new CartVO();
			 cvo.setCustom_id("jk1");
			 List<CartVO> cart_list = Main.gm_u.getSearchCart(cvo);
			 
			 String name = cart_list.get(0).getProd_num();
			 String name2 = cart_list.get(1).getProd_num();
			 String name3 = cart_list.get(2).getProd_num();
			 ProdVO pvo = new ProdVO();
			 
			 pvo.setProd_name(name);
			 
			 ProdVO d = Main.gm_u.getSearchProd(pvo);
			 
			 System.out.println(name);
			 System.out.println(name2);
			 System.out.println(name3);
			 
			 
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
