package kr.or.ddit.rms.shelter.service_center.notice;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.NoticeVO;

public class Notice_Shel_Detail_Controller implements Initializable {
	
	public static NoticeVO vo;
	
	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	
	@FXML JFXTextField notice_newtitle_txtF;
	@FXML JFXTextField notice_id_txtF;
	@FXML Label notice_add_date_label;
	@FXML JFXTextArea notice_content_txtA;
	@FXML JFXButton notice_gotolist_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//보호기관은 수정불가
		notice_newtitle_txtF.setEditable(false);
		notice_id_txtF.setDisable(true);
		notice_content_txtA.setEditable(false);
		notice_gotolist_btn.setOnAction(e->{
			ChangePane cp = Main_page_controller.fxmlLoad.getController();
			cp.clearing();
			ChangePane.changePane(getClass().getResource("notice_table.fxml"));
			cp.add();
		});
		
		//값 불러오기
		notice_newtitle_txtF.setText(vo.getTitle()); //글제목
		notice_add_date_label.setText(vo.getWrite_date()); //글작성일
		notice_id_txtF.setText(vo.getAdmin_id()); //글작성한관리자ID
		notice_content_txtA.setText(vo.getContent()); //글내용
		
	}

}
