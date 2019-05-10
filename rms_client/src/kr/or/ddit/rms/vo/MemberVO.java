package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class MemberVO implements Serializable {
	String mem_id; //회원ID
	String mem_author; //권한번호
	String mem_pw;//비밀번호
	String mem_report;//신고횟수
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_author() {
		return mem_author;
	}
	public void setMem_author(String mem_author) {
		this.mem_author = mem_author;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_report() {
		return mem_report;
	}
	public void setMem_report(String mem_report) {
		this.mem_report = mem_report;
	}
	
	


}
