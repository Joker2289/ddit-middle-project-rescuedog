package kr.or.ddit.rms.vo;

import java.io.Serializable;

public class CardVO implements Serializable {
	
	String card_num;
	String card_name;
	String card_tel;
	
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getCard_tel() {
		return card_tel;
	}
	public void setCard_tel(String card_tel) {
		this.card_tel = card_tel;
	}
	


}
