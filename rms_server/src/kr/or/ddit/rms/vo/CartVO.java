package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class CartVO implements Serializable {
	
	String prod_num;	//상품번호
	String cart_cnt;	//수량
	String custom_id;	//회원ID
	
	public String getProd_num() {
		return prod_num;
	}
	public void setProd_num(String prod_num) {
		this.prod_num = prod_num;
	}
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getCart_cnt() {
		return cart_cnt;
	}
	public void setCart_cnt(String cart_cnt) {
		this.cart_cnt = cart_cnt;
	}
	
	

}
