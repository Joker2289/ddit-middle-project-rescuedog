package kr.or.ddit.rms.user.mypage.buylist;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.CustomVO;
import com.jfoenix.controls.JFXButton;

public class MyPointController implements Initializable {

	public static CustomVO vo;
	
	@FXML Label mypage_sum_label;
	@FXML Label mypage_use_label;
	@FXML Label mypage_used_label;

	@FXML JFXButton mypage_back_btn;

	public void initialize(URL location, ResourceBundle resources) {
		
		 vo = new CustomVO();
		 vo.setCustom_id(Login_controller.log_c.getCustom_id());  
		 vo.setCustom_point(Login_controller.log_c.getCustom_point());

		 mypage_sum_label.setText(vo.getCustom_point());
		 mypage_use_label.setText(vo.getCustom_point());
	}
}