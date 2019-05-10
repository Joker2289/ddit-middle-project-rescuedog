package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class CommentsVO implements Serializable {

	String comment_num;
	String board_num;
	String custom_id;
	String bc_num;
	String comment_content;
	String ins_date;
	
	public String getIns_date() {
		return ins_date;
	}
	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getBc_num() {
		return bc_num;
	}
	public void setBc_num(String bc_num) {
		this.bc_num = bc_num;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	
}
