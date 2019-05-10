package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class NoteVO implements Serializable {
	
	String note_id_to;//받는ID
	String note_id_from;//보내는ID
	String note_title;//제목
	String note_content;//내용
	String note_date;//날짜
	String note_cnt;//조회유무
	
	
	
	public String getNote_id_to() {
		return note_id_to;
	}
	public void setNote_id_to(String note_id_to) {
		this.note_id_to = note_id_to;
	}
	public String getNote_id_from() {
		return note_id_from;
	}
	public void setNote_id_from(String note_id_from) {
		this.note_id_from = note_id_from;
	}
	public String getNote_title() {
		return note_title;
	}
	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	public String getNote_date() {
		return note_date;
	}
	public void setNote_date(String note_date) {
		this.note_date = note_date;
	}
	public String getNote_cnt() {
		return note_cnt;
	}
	public void setNote_cnt(String note_cnt) {
		this.note_cnt = note_cnt;
	}
	
	


}
