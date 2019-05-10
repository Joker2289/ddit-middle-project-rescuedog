package kr.or.ddit.rms.shelter.mypage.updateshelter;

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

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.MemberVO;

import com.jfoenix.controls.JFXButton;

import aesExam.Aes256;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;

public class UpdatePWSController implements Initializable {
	
public static MemberVO membervo;
	
	@FXML PasswordField out_newpw_txt1;
	@FXML PasswordField out_newpw_txt2;
	@FXML PasswordField out_pw_txt1;
	@FXML JFXButton mypage_ok_btn;
	@FXML JFXButton mypage_no_btn;

	@FXML AnchorPane tempPane;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane loadPane;
	
	@FXML Label mypage_id;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		membervo = new MemberVO();
		membervo.setMem_id(Login_controller.log.getMem_id());
		membervo.setMem_pw(Login_controller.log.getMem_pw());
		membervo.setMem_report(Login_controller.log.getMem_report());
		membervo.setMem_author(Login_controller.log.getMem_author());
		
		mypage_id.setText(membervo.getMem_id());

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

	public void updPassword() {

		try {
			String pass = out_pw_txt1.getText();
			String new_pass = out_newpw_txt1.getText();
			String pass_check = out_newpw_txt2.getText();
			String report = membervo.getMem_report();
			String author = membervo.getMem_author();
			String id = membervo.getMem_id();
			String key = "RescuedogManagement"; 
			Aes256 aes256;
			String encText = null;
			try {
				aes256 = new Aes256(key);
				encText = aes256.aesEncode(pass);
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
			// 아이디 유효성 체크
			Pattern chkpw = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.#?!@$%^&*-]).{8,12}$"); // 영어(대소문자),
			Matcher pwcheck = chkpw.matcher(out_newpw_txt1.getText());

			if (out_pw_txt1.getText().isEmpty() != false) {
				alertTest("비밀번호 항목은 필수 입력값입니다.");
				out_pw_txt1.requestFocus();
				return;

			} else if (out_newpw_txt1.getText().isEmpty() != false) {
				alertTest("비밀번호 항목은 필수 입력값입니다.");
				out_newpw_txt1.requestFocus();
				return;

			} else if (out_newpw_txt2.getText().isEmpty() != false) {
				alertTest("비밀번호 항목은 필수 입력값입니다.");
				out_newpw_txt2.requestFocus();
				return;

			} else if (!membervo.getMem_pw().equals(encText)) {
				alertTest("비밀번호가 일치하지 않습니다.");

			} else if (!out_newpw_txt1.getText().equals(pass_check) ) {
				alertTest("비밀번호가 일치하지 않습니다.");

			} else if (out_pw_txt1.getText().equals(new_pass)) {
				alertTest("현재 사용중인 비밀번호와 동일합니다.");

			} else if (!pwcheck.matches()) {
				alertTest("비밀번호는 영문(대소문자),숫자,특수문자,8~12자로 입력하세요.");

			} else if (out_newpw_txt1.getText().isEmpty() || out_newpw_txt2.getText().isEmpty()
					|| out_pw_txt1.getText().isEmpty() != true) {

				MemberVO vo = new MemberVO();
				try {
					aes256 = new Aes256(key);
					encText = aes256.aesEncode(new_pass);
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
				vo.setMem_pw(encText);
				vo.setMem_author(author);
				vo.setMem_id(id);
				vo.setMem_report(report);

				int temp1 = Main.cus.updateMember(vo);
				if (temp1 > 0) {
					Login_controller.log = vo;
				}
				infoMsg("웹 페이지 메세지", "비밀번호 수정이 완료되었습니다.");
				out_pw_txt1.clear();
				out_newpw_txt1.clear();
				out_newpw_txt2.clear();
				System.out.println(temp1);
			}

		} catch (Exception e3) {
			alertTest("빈칸없이 입력해주세요");
			e3.printStackTrace();
		}
	}

}
