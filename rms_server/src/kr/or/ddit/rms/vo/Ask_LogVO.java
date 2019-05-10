package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class Ask_LogVO implements Serializable {
	
	String custom_id;
	String ask_date;
	String ask_content;
	
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getAsk_date() {
		return ask_date;
	}
	public void setAsk_date(String ask_date) {
		this.ask_date = ask_date;
	}
	public String getAsk_content() {
		return ask_content;
	}
	public void setAsk_content(String ask_content) {
		this.ask_content = ask_content;
	}

	
}
