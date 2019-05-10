package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class Volunteer_LogVO implements Serializable {
	String vb_num;//글번호
	String custom_id;//회원아이디
	String vl_date;//신청날짜
	String vl_check;//승인유무
	String vl_title; // 글 제목 (불러올 용도)
	
	public String getVb_num() {
		return vb_num;
	}
	public void setVb_num(String vb_num) {
		this.vb_num = vb_num;
	}
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getVl_date() {
		return vl_date;
	}
	public void setVl_date(String vl_date) {
		this.vl_date = vl_date;
	}
	public String getVl_check() {
		return vl_check;
	}
	public void setVl_check(String vl_check) {
		this.vl_check = vl_check;
	}
	public String getVl_title() {
		return vl_title;
	}
	public void setVl_title(String vl_title) {
		this.vl_title = vl_title;
	}
	
	


}
