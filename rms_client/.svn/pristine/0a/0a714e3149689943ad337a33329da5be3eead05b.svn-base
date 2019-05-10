package kr.or.ddit.rms.user.mypage.activeList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.CustomVO;

public class Active_controller implements Initializable {
	
	@FXML JFXButton active_adopt_btn;
	@FXML JFXButton activeList_volun_btn;
	@FXML JFXButton activeList_dona_btn;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane loadPane;
	@FXML AnchorPane tempPane;
	@FXML JFXButton back;

	@FXML Label mypage_use_label;
	@FXML Label mypage_sum_label;
	
	@FXML JFXButton modify_btn;
	@FXML JFXButton buylist_btn;
	@FXML JFXButton pw_btn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 마이포인트
		CustomVO vo = new CustomVO();
		 vo.setCustom_id(Login_controller.log_c.getCustom_id());  
		 vo.setCustom_point(Login_controller.log_c.getCustom_point());

		 mypage_sum_label.setText(vo.getCustom_point());
		 mypage_use_label.setText(vo.getCustom_point());
		 
		 
		// 후원내역
		active_adopt_btn.setOnAction(e->adoptList());
		
		activeList_dona_btn.setOnAction(e -> donaDetailList());

		activeList_volun_btn.setOnAction(e -> volunterr_log());
		
		modify_btn.setOnAction(e -> modify());
		
		buylist_btn.setOnAction(e -> buylist());
		
		pw_btn.setOnAction(e -> pw_modify());
	}
	
	private void pw_modify() {
		
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("updatePW.fxml"));
		cp.add();
	}
	
	private void modify() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("usermodify.fxml"));
		cp.add();
	}
	
	private void buylist() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("buylist_view.fxml"));
		cp.add();
	}
	
	
	private void adoptList() {
		System.out.println("입양 내역 조회");
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("adopt_log_view.fxml"));
		cp.add();
	}

	private void donaDetailList() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("donation_detail_list.fxml"));
		cp.add();
		System.out.println("기부 상세화면");
	}

	private void volunterr_log() {
		System.out.println("봉사활동내역");
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("VolunteerLog_RegisterPage.fxml"));
		cp.add();
		
	}

}
