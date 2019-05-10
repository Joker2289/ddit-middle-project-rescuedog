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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import aesExam.Aes256;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class UpdateShelterController implements Initializable {

	public static ShelterVO vo;
	public static MemberVO membervo;

//	@FXML
//	GridPane text;
	@FXML
	JFXTextField modify_name_txt;
	@FXML
	JFXTextField modify_id_txt;
	@FXML
	JFXTextField modify_addr_txt;
	@FXML
	PasswordField modify_pass_txt;
	@FXML
	PasswordField modify_pw_check_txt;
	@FXML
	JFXTextField modify_email_txt;
	@FXML
	JFXTextField modify_tel_txt;
	@FXML
	JFXTextField modify_bsnum_txt;
	@FXML
	JFXButton modify_ok_btn;
	@FXML
	JFXButton modify_no_btn;

	@FXML
	AnchorPane tempPane;
	@FXML
	AnchorPane servicePane;
	@FXML
	AnchorPane loadPane;

	public void initialize(URL location, ResourceBundle resources) {

		vo = new ShelterVO();
		vo.setShel_name(Login_controller.log_s.getShel_name());
		vo.setShel_id(Login_controller.log_s.getShel_id());
		vo.setShel_loc(Login_controller.log_s.getShel_loc());
		vo.setShel_email(Login_controller.log_s.getShel_email());
		vo.setShel_tel(Login_controller.log_s.getShel_tel());
		vo.setShel_bsnum(Login_controller.log_s.getShel_bsnum());

		membervo = new MemberVO();
		membervo.setMem_pw(Login_controller.log.getMem_pw());

		// 텍스트필드에 데이터값 가져오기
		modify_name_txt.setText(vo.getShel_name());
		modify_id_txt.setText(vo.getShel_id());
		modify_addr_txt.setText(vo.getShel_loc());
		modify_email_txt.setText(vo.getShel_email());
		modify_tel_txt.setText(vo.getShel_tel());
		modify_bsnum_txt.setText(vo.getShel_bsnum());

		modify_id_txt.setDisable(true);
		modify_name_txt.setEditable(false);
	}
		public boolean modifyShelter(){

			try {
				String id = modify_id_txt.getText();
				String name = modify_name_txt.getText();
				String addr = modify_addr_txt.getText();
				String pass = modify_pass_txt.getText();
				String pass_check = modify_pw_check_txt.getText();
				String email = modify_email_txt.getText();
				String tel = modify_tel_txt.getText();
				String bsnum = modify_bsnum_txt.getText();
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
				Pattern chkphone = Pattern.compile("^[0-9_]{10,11}"); // 숫자, 10~11자
				Matcher phonecheck = chkphone.matcher(modify_tel_txt.getText());
				
				Pattern chkemail = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]|.*[0-9]).{10,25}$"); // 영어(대소문자),
				Matcher emailcheck = chkemail.matcher(modify_email_txt.getText());

				if (modify_addr_txt.getText().isEmpty() != false) {
					alertTest("보호기관 주소는 필수입력 값 입니다.");
					modify_addr_txt.requestFocus();
					return false;
				} else if (modify_pass_txt.getText().isEmpty() != false) {
					alertTest("비밀번호 항목은 필수 입력 값 입니다.");
					modify_pass_txt.requestFocus();
					return false;
				} else if (modify_pw_check_txt.getText().isEmpty() != false) {
					alertTest("비밀번호 항목은 필수 입력 값 입니다.");
					modify_pw_check_txt.requestFocus();
					return false;
				} else if (modify_email_txt.getText().isEmpty() != false) {
					alertTest("이메일이 입력되지 않았습니다.");
					modify_email_txt.requestFocus();
					return false;
				} else if (modify_tel_txt.getText().isEmpty() != false) {
					alertTest("보호기관 연락처가 입력되지 않았습니다.");
					modify_tel_txt.requestFocus();
					return false;
				}

				else if (modify_addr_txt.getText().isEmpty() != false) {
					alertTest("보호기관 주소가 입력되지 않았습니다.");
					modify_addr_txt.requestFocus();
					return false;
				
				} else if (!membervo.getMem_pw().equals(encText) || !membervo.getMem_pw().equals(new_encText)) {
					alertTest("비밀번호가 일치하지 않습니다.");
				}
				else if (!phonecheck.matches()) {
					alertTest("핸드폰번호는 숫자만 입력하세요.");
				}
				else if (!emailcheck.matches()) {
					alertTest("이메일은  영문(대소문자),10~25자로 입력하세요.");
				}

				else if (modify_addr_txt.getText().isEmpty() || modify_pass_txt.getText().isEmpty()
						|| modify_pw_check_txt.getText().isEmpty() || modify_email_txt.getText().isEmpty()
						|| modify_tel_txt.getText().isEmpty() || modify_addr_txt.getText().isEmpty() != true) {

					ShelterVO svo = new ShelterVO();
					svo.setShel_id(id);
					svo.setShel_name(name);
					svo.setShel_loc(addr);
					svo.setShel_email(email);
					svo.setShel_tel(tel);
					svo.setShel_bsnum(bsnum);

					int temp1 = Main.up_s.updateShelter(svo);
					if (temp1 > 0) {
						infoMsg("웹 페이지 메세지", "회원정보 수정이 완료되었습니다.");
						modify_pass_txt.clear();
						modify_pw_check_txt.clear();
						System.out.println(temp1);
						Login_controller.log_s = svo;
						 return true;
					}
				}

			} catch (Exception e1) {
				alertTest("빈칸없이 입력해주세요");
				e1.printStackTrace();
			}
			return false;
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
