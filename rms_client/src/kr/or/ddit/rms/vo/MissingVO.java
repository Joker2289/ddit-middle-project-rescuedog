package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class MissingVO implements Serializable {
	
	String board_num;
	String title;
	String upd_date;
	String mem_id;
	String img;
	String com_idx;
	String miss_loc;
	String content;
	String miss_loc_img;
	
	public String getMiss_loc_img() {
		return miss_loc_img;
	}
	public void setMiss_loc_img(String miss_loc_img) {
		this.miss_loc_img = miss_loc_img;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCom_idx() {
		return com_idx;
	}
	public void setCom_idx(String com_idx) {
		this.com_idx = com_idx;
	}
	public String getMiss_loc() {
		return miss_loc;
	}
	public void setMiss_loc(String miss_loc) {
		this.miss_loc = miss_loc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
