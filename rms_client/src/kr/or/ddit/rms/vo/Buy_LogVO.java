package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class Buy_LogVO implements Serializable {
	
	String prod_name;
	String custom_id;
	String price;
	String buy_quan;
	String refund_check;
	String card_name;
	String prod_num;
	String buy_date;
	String buy_num;
	
	String all_price;
	
	String pre_date;
	String post_date;
	
	
	public String getAll_price() {
		return all_price ;
	}
	public void setAll_price(String all_price) {
		this.all_price = all_price;
	}
	public String getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(String buy_num) {
		this.buy_num = buy_num;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBuy_quan() {
		return buy_quan;
	}
	public void setBuy_quan(String buy_quan) {
		this.buy_quan = buy_quan;
	}
	public String getRefund_check() {
		return refund_check;
	}
	public void setRefund_check(String refund_check) {
		this.refund_check = refund_check;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getProd_num() {
		return prod_num;
	}
	public void setProd_num(String prod_num) {
		this.prod_num = prod_num;
	}
	public String getPre_date() {
		return pre_date;
	}
	public void setPre_date(String pre_date) {
		this.pre_date = pre_date;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	
	

}
