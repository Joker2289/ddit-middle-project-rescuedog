package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class Spon_LogVO implements Serializable {
	String spon_num;//후원내역번호
	String custom_id;//회원ID
	String price;//금액
	String spon_date;//후원날짜
	String shel_id;//보호기관아이디
	String shel_name;
	
	public String getShel_name() {
		return shel_name;
	}
	public void setShel_name(String shel_name) {
		this.shel_name = shel_name;
	}
	public String getSpon_num() {
		return spon_num;
	}
	public void setSpon_num(String spon_num) {
		this.spon_num = spon_num;
	}
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSpon_date() {
		return spon_date;
	}
	public void setSpon_date(String spon_date) {
		this.spon_date = spon_date;
	}
	public String getShel_id() {
		return shel_id;
	}
	public void setShel_id(String shel_id) {
		this.shel_id = shel_id;
	}

	

}
