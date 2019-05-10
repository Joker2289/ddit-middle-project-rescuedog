package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class SponVO implements Serializable{
	
	String spon_num;
	String shel_id;
	String last_date;
	String upd_date;
	String money;
	String content;
	String post_date; // 시작과 끝 날짜 사이
	String title;
	String shel_name;
	
	
	public String getShel_name() {
		return shel_name;
	}
	public void setShel_name(String shel_name) {
		this.shel_name = shel_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	public String getSpon_num() {
		return spon_num;
	}
	public void setSpon_num(String spon_num) {
		this.spon_num = spon_num;
	}
	public String getShel_id() {
		return shel_id;
	}
	public void setShel_id(String shel_id) {
		this.shel_id = shel_id;
	}
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}

}
