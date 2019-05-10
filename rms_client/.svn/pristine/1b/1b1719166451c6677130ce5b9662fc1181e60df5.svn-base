package kr.or.ddit.rms.user.mypage.updatecustom;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import aesExam.Aes256;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.main.MainItemsController;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.user.mypage.activeList.Active_controller;
import kr.or.ddit.rms.user.mypage.buylist.OrderDetailController;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;

public class UpdateCustomController implements Initializable {

	public static CustomVO vo;
	public static MemberVO membervo;
	
	@FXML GridPane text;
	@FXML JFXTextField modify_name_txt;
	@FXML JFXTextField modify_id_txt;
	@FXML JFXTextField modify_brith_txt;
	@FXML PasswordField modify_pass_txt;
	@FXML PasswordField modify_pw_check_txt;
	@FXML JFXTextField modify_email_txt;
	@FXML JFXTextField modify_phone_txt;
	@FXML JFXTextField modify_addr_txt;
	@FXML JFXButton modify_ok_btn;
	@FXML JFXButton modify_no_btn;
	@FXML JFXButton my_outbtn; //탈퇴화면으로 전환
	
	@FXML AnchorPane tempPane;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane loadPane;
	@FXML JFXButton back;

	public void initialize(URL location, ResourceBundle resources) {
		modify_no_btn.setOnAction(e->{
			ChangePane cp = Main_page_controller.fxmlLoad.getController();
			cp.clearing();
			ChangePane.changePane(MainItemsController.class.getResource("mainItems.fxml"));
			cp.add();
		});
		vo = new CustomVO();
		vo.setCustom_name(Login_controller.log_c.getCustom_name());
		vo.setCustom_id(Login_controller.log_c.getCustom_id());
		vo.setCustom_birth(Login_controller.log_c.getCustom_birth());
		vo.setCustom_email(Login_controller.log_c.getCustom_email());
		vo.setCustom_tel(Login_controller.log_c.getCustom_tel());
		vo.setCustom_addr(Login_controller.log_c.getCustom_addr());
		vo.setCustom_point(Login_controller.log_c.getCustom_point());
		
		membervo = new MemberVO();
		membervo.setMem_pw(Login_controller.log.getMem_pw());
		
		// 텍스트필드에 데이터값 가져오기 
	    modify_name_txt.setText(vo.getCustom_name());
		modify_id_txt.setText(vo.getCustom_id());
		modify_brith_txt.setText(vo.getCustom_birth());
		modify_email_txt.setText(vo.getCustom_email());
		modify_phone_txt.setText(vo.getCustom_tel());
		modify_addr_txt.setText(vo.getCustom_addr());
		
		my_outbtn.setOnAction(e-> break_Away());
		modify_ok_btn.setOnAction(e->modifyCutom());
		
	}
	public boolean modifyCutom(){
		try {
			String id 		   = modify_id_txt.getText();
			String name 	   = modify_name_txt.getText();
			String brith	   = modify_brith_txt.getText();
			String pass 	   = modify_pass_txt.getText();
			String pass_check  = modify_pw_check_txt.getText();
			String email 	   = modify_email_txt.getText();
			String tel 		   = modify_phone_txt.getText();
			String addr		   = modify_addr_txt.getText();
			String point 	   = vo.getCustom_point();
			String key = "RescuedogManagement"; 
			Aes256 aes256;
			String encText = null;
			String new_encText =null;
			try {
				aes256 = new Aes256(key);
				encText = aes256.aesEncode(pass);
				new_encText = aes256.aesEncode(pass_check);
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				e1.printStackTrace();
			}
			Pattern chkphone = Pattern.compile("^[0-9_]{10,11}"); //숫자, 10~11자
			Matcher phonecheck = chkphone.matcher(modify_phone_txt.getText());
			
			Pattern chkemail = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]|.*[0-9]).{10,25}$"); //영어(대소문자), 숫자, 특수문자, 10~25자
			Matcher emailcheck = chkemail.matcher(modify_email_txt.getText());
			
			
			if(modify_name_txt.getText().isEmpty() != false) {
				alertTest("이름이 입력되지 않았습니다.");
				modify_name_txt.requestFocus();
				return false;
			}
			else if(modify_id_txt.getText().isEmpty() != false) {
				alertTest("아이가 입력되지 않았습니다.");
				modify_id_txt.requestFocus();
				return false;
			}
			else if(modify_brith_txt.getText().isEmpty() != false) {
				alertTest("생년월일이 입력되지 않았습니다.");
				modify_brith_txt.requestFocus();
				return false;
			}
	        else if(modify_pass_txt.getText().isEmpty() != false) {
				alertTest("비밀번호 항목은 필수 입력값입니다.");
				modify_pass_txt.requestFocus();
				return false;
			}
	        else if(modify_pw_check_txt.getText().isEmpty() != false) {
				alertTest("비밀번호 항목은 필수 입력값입니다.");
				modify_pw_check_txt.requestFocus();
				return false;
			}
	        else if(modify_email_txt.getText().isEmpty() != false) {
				alertTest("이메일이 입력되지 않았습니다.");
				modify_email_txt.requestFocus();
				return false;
			}
		   else if(modify_phone_txt.getText().isEmpty() != false) {
				alertTest("핸드폰 번호가 입력되지 않았습니다.");
				modify_phone_txt.requestFocus();
				return false;
			}
			
		   else if(modify_addr_txt.getText().isEmpty() != false) {
				alertTest("주소가 입력되지 않았습니다.");
				modify_addr_txt.requestFocus();
				return false;
			}
		   else if(!membervo.getMem_pw().equals(encText) || !membervo.getMem_pw().equals(new_encText)) {
			   alertTest("비밀번호가 일치하지 않습니다.");
		   }
		   else if (!phonecheck.matches()) {
				alertTest("핸드폰번호는 숫자만 입력하세요.");
			}
		   else if (!emailcheck.matches()) {
				alertTest("이메일은  영문(대소문자),10~25자로 입력하세요.");
			}
		   else if(modify_name_txt.getText().isEmpty() || modify_id_txt.getText().isEmpty() ||
				   modify_brith_txt.getText().isEmpty() || modify_pass_txt.getText().isEmpty() ||
				   modify_pw_check_txt.getText().isEmpty()  || modify_email_txt.getText().isEmpty() ||
				   modify_phone_txt.getText().isEmpty() || modify_addr_txt.getText().isEmpty() != true ) {
			   
			 CustomVO customvo = new CustomVO();
			 customvo.setCustom_id(id);
			 customvo.setCustom_name(name);
			 customvo.setCustom_birth(brith);
			 customvo.setCustom_email(email);
			 customvo.setCustom_tel(tel);
			 customvo.setCustom_addr(addr);
			 customvo.setCustom_point(point);	

			 int temp1 = Main.cus.updateCustom(customvo);
			 if(temp1>0) {
				 infoMsg("웹 페이지 메세지", "회원정보 수정이 완료되었습니다.");
				 modify_pass_txt.clear();
				 modify_pw_check_txt.clear();
				 System.out.println(temp1);
				 Login_controller.log_c=customvo;
				 return true;
			 }
			 
		   }

		} catch (Exception e1) {
			alertTest("빈칸없이 입력해주세요");
			 e1.printStackTrace();
		}
		return false;
	}
	
	private void break_Away() { //탈퇴로 이동
		tempPane.setVisible(false);
		tempPane.setDisable(true);
		try {
			loadPane=FXMLLoader.load(getClass().getResource("break_away.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		servicePane.getChildren().add(loadPane);
		back = (JFXButton) loadPane.lookup("#mypage_back_btn");
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				loadPane.getChildren().clear();
				servicePane.getChildren().remove(loadPane);
				tempPane.setVisible(true);
				tempPane.setDisable(false);
			}
		});
	}
	private void alertTest(String message) {
		  Alert alertErorr = new Alert(AlertType.ERROR);
	      alertErorr.setTitle("ERROR");
	      alertErorr.setContentText(message);
	      alertErorr.showAndWait();
		
	}
	public void infoMsg(String headerText, String msg) {
	    Alert infoAlert = new Alert(AlertType.INFORMATION);
	    infoAlert.setTitle("정보 확인");
	    infoAlert.setHeaderText(headerText);
	    infoAlert.setContentText(msg);
	    infoAlert.showAndWait();
	 }
	
	}
