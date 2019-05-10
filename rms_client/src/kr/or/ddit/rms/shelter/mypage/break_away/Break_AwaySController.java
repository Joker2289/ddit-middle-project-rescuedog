package kr.or.ddit.rms.shelter.mypage.break_away;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.jfoenix.controls.JFXButton;

import aesExam.Aes256;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.main.MainItemsController;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.ChangeScene;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.MemberVO;

public class Break_AwaySController implements Initializable {

	public static MemberVO membervo;
	
	@FXML Label out_id_label;
	@FXML PasswordField out_pw_txt;
	@FXML PasswordField out_pwcheck_txt;
	@FXML JFXButton mypage_out_btn;
	@FXML JFXButton mypage_no_btn;

	@FXML AnchorPane tempPane;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane loadPane;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		membervo = new MemberVO();
		
		membervo.setMem_id(Login_controller.log.getMem_id());
		membervo.setMem_pw(Login_controller.log.getMem_pw());
		
		
		//비밀번호 일치불일치 
		out_id_label.setText(membervo.getMem_id());
		mypage_no_btn.setOnAction(e->{
			ChangePane cp = Main_page_controller.fxmlLoad.getController();
			cp.clearing();
			ChangePane.changePane(MainItemsController.class.getResource("mainItems.fxml"));
			cp.add();
		});

		mypage_out_btn.setOnAction(e -> {

			try {

				String pass = out_pw_txt.getText();
				String pass_check = out_pwcheck_txt.getText();
				String key = "RescuedogManagement"; 
				Aes256 aes256;
				String encText = null;
				String encText_check = null;
				try {
					aes256 = new Aes256(key);
					encText = aes256.aesEncode(pass);
					encText_check = aes256.aesEncode(pass_check);
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
				if (out_pw_txt.getText().isEmpty() != false) {
					alertTest("비밀번호 항목은 필수 입력값입니다.");
					out_pw_txt.requestFocus();
					return;
				
				} else if (out_pwcheck_txt.getText().isEmpty() != false) {
					alertTest("비밀번호 항목은 필수 입력값입니다.");
					out_pwcheck_txt.requestFocus();
					return;

				} else if (!membervo.getMem_pw().equals(encText) || !membervo.getMem_pw().equals(encText_check)) {
					
					alertTest("비밀번호가 일치하지 않습니다. ");
				
				}

				else if (out_pw_txt.getText().isEmpty() || out_pwcheck_txt.getText().isEmpty() != true) {
					
					String id = membervo.getMem_id();
					
					MemberVO memvo = new MemberVO();
					 memvo.setMem_pw(id);
					
					boolean delcheck = alertConfirm("정말 탈퇴하시겠습니까?");
				
					if(delcheck) {
						try {
							int temp1= Main.ba.deleteMember(id);
							infoMsg("웹 페이지 메세지", "회원탈퇴가 완료되었습니다.");
							Class sc = Main.class;
							ChangeScene.ChangeView(sc, "login_page.fxml", true);
							
						} catch (Exception e1) {
							alertInfo("비밀번호가 옳바르지 않습니다.");
						}
					}else {
						alertInfo("탈퇴를 취소합니다.");
					}
				}
			} catch (Exception e1) {
				alertTest("존재 하지 않는 비밀번호입니다.");
				e1.printStackTrace();
			}
		});
	}

	public boolean alertConfirm(String ment) {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		
		alertConfirm.setTitle("웹 페이지 메세지");
		alertConfirm.setContentText(ment);
		
		//Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult==ButtonType.OK) {
			return true;
		}else if(confirmResult==ButtonType.CANCEL) {
			return false;
		}
		return false;
	}
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("비밀번호 입력오류");
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
	public void alertInfo(String temp){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}

}